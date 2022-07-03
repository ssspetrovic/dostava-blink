package com.loznica.blink.controller;

import com.loznica.blink.dto.LoginDto;
import com.loznica.blink.entity.*;
import com.loznica.blink.repository.DostavljacRepository;
import com.loznica.blink.repository.KorisnikRepository;
import com.loznica.blink.repository.KupacRepository;
import com.loznica.blink.repository.MenadzerRepository;
import com.loznica.blink.service.LoginService;
import com.loznica.blink.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.Hashtable;
import java.util.List;

@RestController
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpSession session) {
        Hashtable<String, String> greska = new Hashtable<>();

        if (loginDto.getKorisnickoIme() == null || loginDto.getKorisnickoIme().isEmpty())
            greska.put("korisnickoIme", "Korisnicko ime ne postoji, morate ga uneti!");

        if (loginDto.getLozinka() == null || loginDto.getLozinka().isEmpty())
            greska.put("lozinka", "Lozinka ne postoji, morate je uneti!");

        if (!greska.isEmpty())
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        Korisnik loggedKorisnik = null;

        try {
            loggedKorisnik = loginService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        } catch (AccountNotFoundException e) {
            greska.put("korisnickoIme", e.getMessage());
        }

        if (!greska.isEmpty() || loggedKorisnik == null)
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        session.setAttribute("korisnik", loggedKorisnik);
        session.setAttribute("korisnickoIme", loggedKorisnik.getKorisnickoIme());
        session.setAttribute("uloga", loggedKorisnik.getUloga());

        List<Korisnik> korisnikList = korisnikRepository.findAll();

        for(Korisnik k : korisnikList)
            if(k.getAuth() == true)
                return new ResponseEntity(HttpStatus.FORBIDDEN);


        loggedKorisnik.setAuth(true);

        if(loggedKorisnik.getUloga() == Uloga.ADMIN)
            korisnikRepository.save(loggedKorisnik);

        else if (loggedKorisnik.getUloga() == Uloga.MENADZER) {
            Menadzer menadzer = menadzerRepository.getByKorisnickoIme(loggedKorisnik.getKorisnickoIme());
            menadzer.setAuth(true);
            menadzerRepository.save(menadzer);
        }

        else if (loggedKorisnik.getUloga() == Uloga.DOSTAVLJAC) {
            Dostavljac dostavljac = dostavljacRepository.getById(loggedKorisnik.getId());
            dostavljac.setAuth(true);
            dostavljacRepository.save(dostavljac);
        }

        else if (loggedKorisnik.getUloga() == Uloga.KUPAC) {
            Kupac kupac = kupacRepository.getById(loggedKorisnik.getId());
            kupac.setAuth(true);
            kupacRepository.save(kupac);
        }

        return new ResponseEntity(loggedKorisnik, HttpStatus.OK);
    }

    @PostMapping("api/logout")
    public ResponseEntity logout(HttpSession session, @RequestParam String korisnickoIme) {
//        if (!sessionService.validate(session))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
        
        for(Korisnik k : korisnikRepository.findAll())
            if(k.getAuth() == true) {
                k.setAuth(false);
                korisnikRepository.save(k);
            }

        session.invalidate();
        return new ResponseEntity("Uspesno ste napustili profil, pozdrav!", HttpStatus.OK);
    }

    @PostMapping("api/clearout")
    public ResponseEntity clearout() {
        for(Korisnik k : korisnikRepository.findAll())
            if(k.getAuth() == true) {
                k.setAuth(false);
                korisnikRepository.save(k);
            }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
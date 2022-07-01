package com.loznica.blink.controller;

import com.loznica.blink.dto.LoginDto;
import com.loznica.blink.entity.Korisnik;
import com.loznica.blink.repository.KorisnikRepository;
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
        korisnikRepository.save(loggedKorisnik);


        return new ResponseEntity(loggedKorisnik, HttpStatus.OK);
    }

    @PostMapping("api/logout")
    public ResponseEntity logout(HttpSession session, @RequestParam String korisnickoIme) {
//        if (!sessionService.validate(session))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Korisnik loggedKorisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);
        if(loggedKorisnik == null)
            return new ResponseEntity("Nema ulogovanog korisnika", HttpStatus.NOT_FOUND);

        if(loggedKorisnik.getAuth() == true)
        loggedKorisnik.setAuth(false);
        korisnikRepository.save(loggedKorisnik);

        session.invalidate();
        return new ResponseEntity("Uspesno ste napustili profil, pozdrav!", HttpStatus.OK);
    }
}
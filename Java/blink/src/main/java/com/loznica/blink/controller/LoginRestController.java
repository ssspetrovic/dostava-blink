package com.loznica.blink.controller;

import com.loznica.blink.dto.LoginDto;
import com.loznica.blink.entity.Korisnik;
import com.loznica.blink.service.LoginService;
import com.loznica.blink.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.Hashtable;

@RestController
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SessionService sessionService;

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

        return new ResponseEntity(loggedKorisnik, HttpStatus.OK);
    }

    @PostMapping("api/logout")
    public ResponseEntity logout(HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Uspesno ste napustili profil, pozdrav!", HttpStatus.OK);
    }
}
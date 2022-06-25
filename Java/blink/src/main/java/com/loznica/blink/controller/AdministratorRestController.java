package com.loznica.blink.controller;

import com.loznica.blink.entity.*;
import com.loznica.blink.repository.KorisnikRepository;
import com.loznica.blink.repository.MenadzerRepository;
import com.loznica.blink.repository.PorudzbinaRepository;
import com.loznica.blink.repository.RestoranRepository;
import com.loznica.blink.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdministratorRestController {

    @Autowired
    SessionService sessionService;

    @Autowired
    KorisnikRepository korisnikRepository;

    @Autowired
    RestoranRepository restoranRepository;

    @Autowired
    PorudzbinaRepository porudzbinaRepository;

    @Autowired
    MenadzerRepository menadzerRepository;

    @GetMapping("/api/admin/ispisi")
    public ResponseEntity ispisiKorisnike(HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        if (!sessionService.getUloga(session).equals(Uloga.ADMIN))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<Korisnik> korisnikList = new ArrayList<>();

        for(Korisnik k : korisnikRepository.findAll())
            if(!k.getUloga().equals(Uloga.ADMIN))
                korisnikList.add(k);

        if(korisnikList == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(korisnikList);
    }

    @GetMapping("/api/admin/pretraga")
    public ResponseEntity ispisiKorisnikePoStringu(@RequestParam String string, HttpSession session) {
        if(!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        if(!sessionService.getUloga(session).equals(Uloga.ADMIN))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(string == null || string.isEmpty())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        int size = string.length();

        List<Korisnik> korisnikList = new ArrayList<>();

        for(Korisnik k : korisnikRepository.findAll()) {
            if(k.getKorisnickoIme() != null && string.equalsIgnoreCase(k.getKorisnickoIme().length() > size ? k.getKorisnickoIme().substring(0, size) : k.getKorisnickoIme()))
                korisnikList.add(k);
            else if(k.getIme() != null && string.equalsIgnoreCase(k.getIme().length() > size ? k.getIme().substring(0, size) : k.getIme()))
                korisnikList.add(k);
            else if(k.getPrezime() != null && string.equalsIgnoreCase(k.getPrezime().length() > size ? k.getPrezime().substring(0, size) : k.getPrezime()))
                korisnikList.add(k);
        }

        
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(korisnikList);
    }

    @DeleteMapping("/api/admin/brisiRestoran/{nazivRestorana}")
    public ResponseEntity obrisiRestoran(@PathVariable String nazivRestorana, HttpSession session) {

        if(!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        if(!sessionService.getUloga(session).equals(Uloga.ADMIN))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Restoran restoran = restoranRepository.getByNaziv(nazivRestorana);

        if(restoran == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Neuspesno pronalazenje restorana.");

        List<Porudzbina> plist = porudzbinaRepository.findAll();

        for(Porudzbina p : plist) {
            if(p.getRestoran().equals(restoran))
                p.setRestoran(null);
        }

        Menadzer menadzer = menadzerRepository.getByRestoran(restoran);
        menadzer.setRestoran(null);

        restoranRepository.deleteById(restoran.getId());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Uspesno obrisan restoran!");
    }

}

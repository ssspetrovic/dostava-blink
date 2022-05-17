package com.loznica.blink.controller;

import com.loznica.blink.entity.Artikal;
import com.loznica.blink.entity.Menadzer;
import com.loznica.blink.entity.Uloga;
import com.loznica.blink.repository.ArtikalRepository;
import com.loznica.blink.repository.MenadzerRepository;
import com.loznica.blink.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
public class ArtikalRestController {

    @Autowired
    MenadzerRepository menadzerRepository;

    @Autowired
    ArtikalRepository artikalRepository;

    @Autowired
    SessionService sessionService;

    @PostMapping("/api/artikli/kreiraj-artikal")
    public ResponseEntity<Artikal> kreirajArtikal(@RequestParam String korisnickoIme, @RequestBody Artikal artikal, HttpSession session) {
        if(!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(!sessionService.getUloga(session).equals(Uloga.MENADZER))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        HashMap<String, String> greska = Validate(artikal);

        if(!greska.isEmpty())
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        Menadzer menadzer = menadzerRepository.getByKorisnickoIme(korisnickoIme);
        menadzer.getRestoran().getArtikli().add(artikal);
        artikalRepository.save(artikal);

        return ResponseEntity.ok(artikal);
    }

    private HashMap<String, String> Validate(Artikal artikal) {

        HashMap<String, String> greska = new HashMap<>();

        if(artikal.getNaziv() == null || artikal.getNaziv().isEmpty())
            greska.put("naziv", "OBAVEZNO");
        if(artikal.getCena() == null)
            greska.put("cena", "OBAVEZNO");
        if(artikal.getTip() == null || artikal.getTip().toString().isEmpty())
            greska.put("tip", "OBAVEZNO");

        return greska;
    }

    @PostMapping("/api/artikli/izmena/{uuid}")
    public ResponseEntity<Artikal> setArtikal(@PathVariable(name = "id") Long id, @RequestParam String korisnickoIme, @RequestBody Artikal artikal, HttpSession session) {
        if(!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(!sessionService.getUloga(session).equals(Uloga.MENADZER))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Artikal a = artikalRepository.findById(id);

        a.setNaziv(artikal.getNaziv() == null ? a.getNaziv() : artikal.getNaziv());
        a.setCena(artikal.getCena() == null ? a.getCena() : artikal.getCena());
        a.setTip(artikal.getTip() == null ? a.getTip() : artikal.getTip());
        a.setKolicina(artikal.getKolicina() == 0 ? a.getKolicina() : artikal.getKolicina());
        a.setOpis(artikal.getOpis() == null ? a.getOpis() : artikal.getOpis());
        a.setRestoran(artikal.getRestoran() == null ? a.getRestoran() : artikal.getRestoran());

        artikalRepository.saveAndFlush(a);

        try {
            System.out.println("Uspesna izmena.");
        } catch (Exception e) {
            System.out.println("Neuspesna izmena.");
        }

        return ResponseEntity.ok(a);
    }

}

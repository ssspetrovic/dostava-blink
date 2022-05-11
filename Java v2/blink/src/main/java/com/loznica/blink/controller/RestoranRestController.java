package com.loznica.blink.controller;

import com.loznica.blink.dto.RegisterDto;
import com.loznica.blink.dto.RestoranDto;
import com.loznica.blink.entity.*;
import com.loznica.blink.repository.MenadzerRepository;
import com.loznica.blink.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @GetMapping("/api/restorani/lista")
    public ResponseEntity Get() {
        List<Restoran> restoranList = restoranRepository.findAll();
        List<RestoranDto> restoranInfo = new ArrayList<>();

        for (Restoran tmp : restoranList) {
            RestoranDto currentInfo = new RestoranDto();
            currentInfo.setRestoran(tmp);
            currentInfo.setArtikli(tmp.getArtikli());
            restoranInfo.add(currentInfo);
        }

        return new ResponseEntity(restoranInfo, HttpStatus.OK);
    }

    @GetMapping("/api/restorani/info/{id}")
    public Restoran ispisiRestoran(@PathVariable(name = "id") Long id) {
        List<Restoran> restoranList = restoranRepository.findAll();

        for(Restoran r : restoranList)
            if(id == r.getId())
                return r;

        return null;
    }

    @PostMapping("/api/restorani/info/izmena/")
    public ResponseEntity izmenaRestorana(@RequestParam String korisnickoIme, @RequestBody Artikal artikal) {

        HashMap<String, String> greska = Validate(artikal);

        if(!greska.isEmpty())
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        Menadzer menadzer = menadzerRepository.getByKorisnickoIme(korisnickoIme);

        menadzer.getRestoran().getArtikli().add(artikal);

        if(!greska.isEmpty())
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        return new ResponseEntity("Uspesno dodat artikal!", HttpStatus.OK);
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

}

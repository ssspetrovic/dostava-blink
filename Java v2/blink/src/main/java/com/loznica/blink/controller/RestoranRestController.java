package com.loznica.blink.controller;

import com.loznica.blink.dto.RestoranDto;
import com.loznica.blink.entity.*;
import com.loznica.blink.repository.LokacijaRepository;
import com.loznica.blink.repository.MenadzerRepository;
import com.loznica.blink.repository.RestoranRepository;
import com.loznica.blink.service.RestoranService;
import com.loznica.blink.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @GetMapping("/api/restorani/lista")
    public ResponseEntity listaRestorana() {
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
            if(Objects.equals(id, r.getId()))
                return r;

        return null;
    }

    @PostMapping("/api/admin/kreiraj-restoran")
    public ResponseEntity<?> kreirajRestoran(@RequestBody RestoranDto restoranDto, HttpSession session) {
        if(!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(!sessionService.getUloga(session).equals(Uloga.ADMIN))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        String poruka;
        ResponseEntity<String> kreirajRestoran;
        Restoran restoran = restoranDto.ToRestoran();

        List<Menadzer> menadzerList = menadzerRepository.findAll();
        Menadzer menadzer = new Menadzer();

        for(Menadzer m : menadzerList)
            if(m.getRestoran() == restoran)
                menadzer = m;


        try {
            restoranService.KreirajRestoran(restoran);
        } catch (Exception e) {
            poruka = "Restoran vec postoji.";
            kreirajRestoran = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(poruka);
        }

        try {
            restoranRepository.saveAndFlush(restoran);
            menadzerRepository.saveAndFlush(menadzer);
            poruka = "Restoran uspesno kreiran.";
            kreirajRestoran = ResponseEntity.ok(poruka);
        } catch (Exception e) {
            poruka = "Neuspesno kreiranje restorana, pokusajte ponovo...";
            System.out.println(e.getMessage());
            kreirajRestoran = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(poruka);
        }

        return kreirajRestoran;
    }

    @GetMapping("/api/restoran/pretragaNaziv")
    public ResponseEntity pretraziRestoranPoNazivu(@RequestParam String naziv, HttpSession session) {
        if(!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(naziv == null || naziv.isEmpty())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Restoran restoran = restoranRepository.getByNaziv(naziv);

        if(restoran == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.OK).body(restoran);
    }

    @GetMapping("/api/restoran/pretragaTip")
    public ResponseEntity pretraziRestoranPoTipu(@RequestParam String tipRestorana, HttpSession session) {
        if(!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(tipRestorana == null || tipRestorana.isEmpty())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Restoran restoran = restoranRepository.getByTipRestorana(tipRestorana);

        if(restoran == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.OK).body(restoran);
    }

    @GetMapping("/api/restoran/pretragaLokacija/{id}")
    public ResponseEntity pretraziRestoranPoLokaciji(@PathVariable(name = "id") Long id, HttpSession session) {
        if(!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Lokacija lokacija = lokacijaRepository.getById(id);

        Restoran restoran = null;

        for(Restoran r : restoranRepository.findAll())
            if(r.getLokacija() == lokacija)
                restoran = r;

        System.out.println(restoran.getPorudzbine().toString());


        if(restoran == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.OK).body(restoran);
    }


}

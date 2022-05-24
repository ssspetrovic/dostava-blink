package com.loznica.blink.controller;

import com.loznica.blink.dto.RestoranDto;
import com.loznica.blink.entity.*;
import com.loznica.blink.repository.ArtikalRepository;
import com.loznica.blink.repository.LokacijaRepository;
import com.loznica.blink.repository.MenadzerRepository;
import com.loznica.blink.repository.RestoranRepository;
import com.loznica.blink.service.RestoranService;
import com.loznica.blink.service.SessionService;
import com.loznica.blink.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private ArtikalRepository artikalRepository;

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

        for (Restoran r : restoranList)
            if (Objects.equals(id, r.getId()))
                return r;

        return null;
    }

    @PostMapping("/api/admin/kreiraj-restoran")
    public ResponseEntity<?> kreirajRestoran(@RequestBody RestoranDto restoranDto, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.ADMIN))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        String poruka;
        ResponseEntity<String> kreirajRestoran;
        Restoran restoran = restoranDto.ToRestoran();

        List<Menadzer> menadzerList = menadzerRepository.findAll();
        Menadzer menadzer = new Menadzer();

        for (Menadzer m : menadzerList)
            if (m.getRestoran() == restoran)
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

    @GetMapping("/api/admin/restorani/{id}/postavi-menadzera")
    public ResponseEntity postaviMenadzera(@PathVariable(name = "id") Long id, @RequestParam String korisnickoIme, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.ADMIN))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Restoran restoran = restoranRepository.getById(id);

        if (restoran == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Restoran ne postoji.");
        }

        Menadzer menadzer = menadzerRepository.getByKorisnickoIme(korisnickoIme);
        menadzer.setRestoran(restoran);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Menadzer uspesno postavljen.");
    }

    @GetMapping("/api/restoran/pretragaNaziv")
    public ResponseEntity pretraziRestoranPoNazivu(@RequestParam String naziv, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (naziv == null || naziv.isEmpty())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Restoran restoran = restoranRepository.getByNaziv(naziv);

        if (restoran == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.OK).body(restoran);
    }

    @GetMapping("/api/restoran/pretragaTip")
    public ResponseEntity pretraziRestoranPoTipu(@RequestParam String tipRestorana, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (tipRestorana == null || tipRestorana.isEmpty())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Restoran restoran = restoranRepository.getByTipRestorana(tipRestorana);

        if (restoran == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.OK).body(restoran);
    }

    @GetMapping("/api/restoran/pretragaLokacija/{id}")
    public ResponseEntity pretraziRestoranPoLokaciji(@PathVariable(name = "id") Long id, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Lokacija lokacija = lokacijaRepository.getById(id);

        Restoran restoran = null;

        for (Restoran r : restoranRepository.findAll())
            if (r.getLokacija() == lokacija)
                restoran = r;

        System.out.println(restoran.getPorudzbine().toString());


        if (restoran == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.OK).body(restoran);
    }

    @PostMapping("/api/artikli/kreiraj-artikal")
    public ResponseEntity<Artikal> kreirajArtikal(@RequestParam String korisnickoIme, Artikal artikal, @RequestParam("image") MultipartFile multipartFile, HttpSession session) throws IOException {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.MENADZER))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        HashMap<String, String> greska = Validate(artikal);

        if (!greska.isEmpty())
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        artikal.setSlike(fileName);

        Menadzer menadzer = menadzerRepository.getByKorisnickoIme(korisnickoIme);
        menadzer.getRestoran().getArtikli().add(artikal);
        artikalRepository.save(artikal);

        String uploadDir = "korisnik-slike/" + artikal.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return ResponseEntity.ok(artikal);
    }

    private HashMap<String, String> Validate(Artikal artikal) {
        HashMap<String, String> greska = new HashMap<>();

        if (artikal.getNaziv() == null || artikal.getNaziv().isEmpty())
            greska.put("naziv", "OBAVEZNO");

        if (artikal.getCena() == null)
            greska.put("cena", "OBAVEZNO");

        if (artikal.getTip() == null || artikal.getTip().toString().isEmpty())
            greska.put("tip", "OBAVEZNO");

        return greska;
    }

    @PostMapping("/api/artikli/izmena/{id}")
    public ResponseEntity<Artikal> setArtikal(@PathVariable(name = "id") Long id, @RequestBody Artikal artikal, MultipartFile multipartFile, HttpSession session) throws IOException {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.MENADZER))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Artikal a = artikalRepository.findById(id);

        a.setNaziv(artikal.getNaziv() == null ? a.getNaziv() : artikal.getNaziv());
        a.setCena(artikal.getCena() == null ? a.getCena() : artikal.getCena());
        a.setTip(artikal.getTip() == null ? a.getTip() : artikal.getTip());
        a.setKolicina(artikal.getKolicina() == 0 ? a.getKolicina() : artikal.getKolicina());
        a.setOpis(artikal.getOpis() == null ? a.getOpis() : artikal.getOpis());
        a.setRestoran(artikal.getRestoran() == null ? a.getRestoran() : artikal.getRestoran());

        if (artikal.getSlike() != null) {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            artikal.setSlike(fileName);
            String uploadDir = "korisnik-slike/" + artikal.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } else {
            a.setSlike(a.getSlike());
        }

        artikalRepository.saveAndFlush(a);

        try {
            System.out.println("Uspesna izmena.");
        } catch (Exception e) {
            System.out.println("Neuspesna izmena.");
        }

        return ResponseEntity.ok(a);
    }

}
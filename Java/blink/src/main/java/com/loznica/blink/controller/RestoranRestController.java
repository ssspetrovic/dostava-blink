package com.loznica.blink.controller;

import com.loznica.blink.dto.RestoranDto;
import com.loznica.blink.entity.*;
import com.loznica.blink.repository.*;
import com.loznica.blink.service.RestoranService;
import com.loznica.blink.service.SessionService;
import com.loznica.blink.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
    private PorudzbineArtikliRepository porudzbineArtikliRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @GetMapping("/api/restorani/lista")
    public List<Restoran> listaRestorana() {
        return restoranRepository.findAll();
    }

    @GetMapping("/api/restorani/info/{id}")
    public ResponseEntity ispisiRestoran(@PathVariable(name = "id") Long id) {
        List<Restoran> restoranList = restoranRepository.findAll();

        Restoran restoran = new Restoran();

        for (Restoran r : restoranList)
            if (Objects.equals(id, r.getId()))
                restoran = r;

        if(restoran == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        float ocena = 0;
        int brojac = 0;

        if(restoran.getKomentari() != null)
            for(Komentar komentar : restoran.getKomentari()) {
                ocena += komentar.getOcena();
                brojac++;
            }

        if(brojac == 0 || ocena == 0)
            restoran.setProsek(0);
        else
            restoran.setProsek(ocena/brojac);



        return ResponseEntity.status(HttpStatus.ACCEPTED).body(restoran);
    }

    @GetMapping("/api/artikal/{id}")
    public ResponseEntity ispisiArtikal(@PathVariable(name = "id") Long id) {
        List<Artikal> artikalList = artikalRepository.findAll();

        for (Artikal a : artikalList)
            if (Objects.equals(id, a.getId()))
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(a);

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/admin/kreiraj-restoran")
    public ResponseEntity<?> kreirajRestoran(@RequestBody RestoranDto restoranDto, @RequestParam String korisnickoIme) {
//        if (!sessionService.validate(session))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);

//          if (!sessionService.getUloga(session).equals(Uloga.ADMIN))
//              return new ResponseEntity(HttpStatus.FORBIDDEN);

        Korisnik loggedKorisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);

        if(loggedKorisnik == null)
            return new ResponseEntity("Nema korisnika!", HttpStatus.NOT_FOUND);

        if (!loggedKorisnik.getUloga().equals(Uloga.ADMIN))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        String poruka;
        ResponseEntity<String> kreirajRestoran;
        Restoran restoran = restoranDto.ToRestoran();

        List<Menadzer> menadzerList = menadzerRepository.findAll();
        Menadzer menadzer = new Menadzer();

        for (Menadzer m : menadzerList)
            if (m.getRestoran() == null) {
                m.setRestoran(restoran);
                menadzer = m;
                restoran.setMenadzer(menadzer);
                break;
            }


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

    @GetMapping("/api/admin/restorani/pretragaMenadzer")
    public ResponseEntity pretraziRestoranPoMenadzeru(HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.MENADZER))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Menadzer menadzer = (Menadzer) session.getAttribute("korisnik");

        Restoran restoran = menadzer.getRestoran();

        if(restoran == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        return ResponseEntity.status(HttpStatus.OK).body(restoran.toString());
    }


    @GetMapping("/api/restoran/pretraga")
    public ResponseEntity pretraziPoRestoranu(@RequestParam String string, HttpSession session) {

        if(string == null || string.isEmpty())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        int size = string.length();

        List<Restoran> restoranList = new ArrayList<>();

        for(Restoran r : restoranRepository.findAll()) {
            if(r.getNaziv() != null && string.equalsIgnoreCase(r.getNaziv().length() > size ? r.getNaziv().substring(0, size) : r.getNaziv()))
                restoranList.add(r);
            else if(r.getTipRestorana() != null && string.equalsIgnoreCase(r.getTipRestorana().length() > size ? r.getTipRestorana().substring(0, size) : r.getTipRestorana()))
                restoranList.add(r);
            else if(r.getLokacija() != null && r.getLokacija().getAdresa() != null && string.equalsIgnoreCase(r.getLokacija().getAdresa().length() > size ? r.getLokacija().getAdresa().substring(0, size) : r.getLokacija().getAdresa()))
                restoranList.add(r);
        }

        return ResponseEntity.ok(restoranList);
    }

    @PostMapping("/api/artikli/kreiraj-artikal")
    public ResponseEntity kreirajArtikal(@RequestParam String korisnickoIme, @RequestBody Artikal artikal, /*@RequestParam("slike") MultipartFile multipartFile,*/ HttpSession session) throws IOException {
//        if (!sessionService.validate(session))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
//
//        if (!sessionService.getUloga(session).equals(Uloga.MENADZER))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
        Korisnik loggedKorisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);

        if(loggedKorisnik == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(!loggedKorisnik.getAuth())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(loggedKorisnik.getUloga() != Uloga.MENADZER)
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        Menadzer menadzer = menadzerRepository.getByKorisnickoIme(korisnickoIme);

        HashMap<String, String> greska = Validate(artikal);

        if (!greska.isEmpty())
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        //String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        //artikal.setSlike(fileName);

        for(Artikal a : artikalRepository.findAll())
            if(artikal.getNaziv().equals(a.getNaziv()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Artikal vec postoji!");

        menadzer.getRestoran().getArtikli().add(artikal);
        artikalRepository.save(artikal);

        //String uploadDir = "korisnik-slike/" + artikal.getId();
        //FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

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

//        if (artikal.getSlike() == null || artikal.getSlike().isEmpty())
//            greska.put("slike", "OBAVEZNO");

        return greska;
    }

    @PostMapping("/api/artikli/izmena/{id}")
    public ResponseEntity setArtikal(@PathVariable(name = "id") Long id, @RequestBody Artikal artikal, /*@RequestParam("image") MultipartFile multipartFile,*/ @RequestParam String korisnickoIme) {
//        if (!sessionService.validate(session))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
//
//        if (!sessionService.getUloga(session).equals(Uloga.MENADZER))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Korisnik loggedKorisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);

        if(loggedKorisnik == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        if(!loggedKorisnik.getAuth())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(!loggedKorisnik.getUloga().equals(Uloga.MENADZER))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Artikal a = artikalRepository.getById(id);

        a.setNaziv(artikal.getNaziv() == null ? a.getNaziv() : artikal.getNaziv());
        a.setCena(artikal.getCena() == null ? a.getCena() : artikal.getCena());
        a.setTip(artikal.getTip() == null ? a.getTip() : artikal.getTip());
        a.setKolicina(artikal.getKolicina() == 0 ? a.getKolicina() : artikal.getKolicina());
        a.setOpis(artikal.getOpis() == null ? a.getOpis() : artikal.getOpis());

//        if (artikal.getSlike() == null) {
//            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//            artikal.setSlike(fileName);
//            String uploadDir = "korisnik-slike/" + artikal.getId();
//            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
//        } else {
//            a.setSlike(a.getSlike());
//        }

        try {
            artikalRepository.save(a);
            System.out.println("Uspesna izmena.");
        } catch (Exception e) {
            System.out.println("Neuspesna izmena.");
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/artikal/obrisi/{id}")
    public ResponseEntity obrisiArtikal(@PathVariable Long id, HttpSession session, @RequestParam String korisnickoIme) {
//        if (!sessionService.validate(session))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
//
//        if (!sessionService.getUloga(session).equals(Uloga.MENADZER))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Korisnik loggedKorisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);

        if(loggedKorisnik == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(!loggedKorisnik.getAuth())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(!Objects.equals(loggedKorisnik.getUloga(), Uloga.MENADZER))
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);

        Restoran restoran = ((Menadzer) loggedKorisnik).getRestoran();
        Artikal artikal;
        try {
            artikal = artikalRepository.findById(id).get();
        } catch(Exception e ) {
            return new ResponseEntity("Neispravan id", HttpStatus.BAD_REQUEST);
        }
        if(restoran.getArtikli().contains(artikal)) {
            restoran.getArtikli().remove(artikal);
            restoranService.save(restoran);
            return new ResponseEntity("Uspesno obrisan artikal", HttpStatus.OK);
        }
        return new ResponseEntity("Neuspesno pronalazenje artikla!", HttpStatus.FORBIDDEN);
    }

}
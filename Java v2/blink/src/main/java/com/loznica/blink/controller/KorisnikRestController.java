package com.loznica.blink.controller;

import com.loznica.blink.dto.RestoranDto;
import com.loznica.blink.entity.*;
import com.loznica.blink.repository.DostavljacRepository;
import com.loznica.blink.repository.KorisnikRepository;
import com.loznica.blink.repository.MenadzerRepository;
import com.loznica.blink.repository.RestoranRepository;
import com.loznica.blink.security.RegistrationRequest;
import com.loznica.blink.service.KorisnikService;
import com.loznica.blink.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
public class KorisnikRestController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @GetMapping("/api/")
    public String dobrodoslica() {
        return "Dobrodosli u Blink!";
    }

    @GetMapping("/api/login/info")
    public ResponseEntity<Korisnik> getInfo(HttpSession session){

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            System.out.println("Nema sesije.");
        } else {
            System.out.println(loggedKorisnik);
        }
        return ResponseEntity.ok(loggedKorisnik);
    }

    @PostMapping("/api/login/info/izmena")
    public ResponseEntity<Korisnik> setKorisnik(HttpSession session, @RequestBody RegistrationRequest registrationRequest) {

        Korisnik k = (Korisnik) session.getAttribute("korisnik");

        k.setKorisnickoIme(registrationRequest.getKorisnickoIme() == null ? k.getIme() : registrationRequest.getKorisnickoIme());
        k.setLozinka(registrationRequest.getLozinka() == null ? k.getLozinka() : registrationRequest.getLozinka());
        k.setIme(registrationRequest.getIme() == null ? k.getIme() : registrationRequest.getIme());
        k.setPrezime(registrationRequest.getPrezime() == null ? k.getPrezime() : registrationRequest.getPrezime());
        k.setPol(registrationRequest.getPol() == null ? k.getPol() : registrationRequest.getPol());
        k.setDatumRodjenja(registrationRequest.getDatumRodjenja() == null ? k.getDatumRodjenja() : registrationRequest.getDatumRodjenja());

        try {
            System.out.println("Uspesna izmena.");
        } catch (Exception e) {
            System.out.println("Neuspesna izmena.");
        }

        return ResponseEntity.ok(k);
    }

    @GetMapping("/api/admin/korisnici/{id}")
    public Korisnik getKorisnik(@PathVariable(name = "id") Long id, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            System.out.println("Nema sesije.");
            return null;
        } else if (!loggedKorisnik.getUloga().equals(Uloga.ADMIN)) {
            System.out.println("Pristup nije odobren.");
            return null;
        }

        return korisnikService.findKorisnik(id);
    }

    @PostMapping("/api/admin/sacuvaj-korisnika")
    public ResponseEntity<?> saveKorisnici(@RequestBody Korisnik korisnik, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(null);
        } else if (!loggedKorisnik.getUloga().equals(Uloga.ADMIN)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pristup nije odobren.");
        }

        this.korisnikService.save(korisnik);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Korisnk je uspesno sacuvan!");
    }

    @RequestMapping(value = "/api/admin/obrisi-korisnika/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ResponseEntity<?> deleteKorisnik(@PathVariable(name = "id") Long id, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(loggedKorisnik);
        } else if (!loggedKorisnik.getUloga().equals(Uloga.ADMIN)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pristup nije odobren.");
        }

        korisnikService.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Korisnk je uspesno uklonjen!");
    }

    @GetMapping("/api/admin/korisnici/ispis")
    public ResponseEntity<?> getKorisnici(HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(null);
        } else if (!loggedKorisnik.getUloga().equals(Uloga.ADMIN)) {
            System.out.println("Pristup nije odobren.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pristup nije odobren.");
        }

        List<Korisnik> korisnikList = korisnikRepository.findAll();
        List<RegistrationRequest> registrationRequestList = new ArrayList<>();

        for (Korisnik korisnik : korisnikList) {
            if (!korisnik.getUloga().equals(Uloga.ADMIN)){
                RegistrationRequest r = new RegistrationRequest();
                r.setKorisnickoIme(korisnik.getKorisnickoIme());
                r.setLozinka(korisnik.getLozinka());
                r.setIme(korisnik.getIme());
                r.setPrezime(korisnik.getPrezime());
                r.setPol(korisnik.getPol());
                r.setDatumRodjenja(korisnik.getDatumRodjenja());

                registrationRequestList.add(r);
            }
        }

        return ResponseEntity.ok(registrationRequestList);
    }

    @PostMapping("/api/admin/kreiraj-menadzera")
    public ResponseEntity<?> kreirajMenadzera(@RequestBody RegistrationRequest registrationRequest, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(loggedKorisnik);
        } else if (!loggedKorisnik.getUloga().equals(Uloga.ADMIN)) {
            System.out.println("Pristup nije odobren.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pristup nije odobren.");
        }

        String poruka;
        ResponseEntity<String> registracija;

        if (korisnikRepository.findByKorisnickoIme(registrationRequest.getKorisnickoIme()).isPresent()) {
            poruka = "Korisnik vec postoji.";
            return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(poruka);
        }

        Menadzer m = new Menadzer();
        m.setKorisnickoIme(registrationRequest.getKorisnickoIme());
        m.setLozinka(registrationRequest.getLozinka());
        m.setIme(registrationRequest.getIme());
        m.setPrezime(registrationRequest.getPrezime());
        m.setPol(registrationRequest.getPol());
        m.setDatumRodjenja(registrationRequest.getDatumRodjenja());
        m.setUloga(Uloga.MENADZER);

        try {
            menadzerRepository.save(m);
            poruka = "Hvala Vam na registraciji. Sve najlepse Vama i rodbini.";
            registracija = ResponseEntity.ok(poruka);
        } catch (Exception e) {
            poruka = "Neuspesna registracija, pokusajte ponovo...";
            System.out.println(e.getMessage());
            registracija = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(poruka);
        }

        return registracija;
    }

    @PostMapping("/api/admin/kreiraj-dostavljaca")
    public ResponseEntity<?> kreirajDostavljaca(@RequestBody RegistrationRequest registrationRequest, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(loggedKorisnik);
        } else if (!loggedKorisnik.getUloga().equals(Uloga.ADMIN)) {
            System.out.println("Pristup nije odobren.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pristup nije odobren.");
        }

        String poruka;
        ResponseEntity<String> registracija;

        if (korisnikRepository.findByKorisnickoIme(registrationRequest.getKorisnickoIme()).isPresent()) {
            poruka = "Korisnik vec postoji.";
            return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(poruka);
        }

        Dostavljac d = new Dostavljac();
        d.setKorisnickoIme(registrationRequest.getKorisnickoIme());
        d.setLozinka(registrationRequest.getLozinka());
        d.setIme(registrationRequest.getIme());
        d.setPrezime(registrationRequest.getPrezime());
        d.setPol(registrationRequest.getPol());
        d.setDatumRodjenja(registrationRequest.getDatumRodjenja());
        d.setUloga(Uloga.DOSTAVLJAC);

        try {
            dostavljacRepository.save(d);
            poruka = "Hvala Vam na registraciji. Sve najlepse Vama i rodbini.";
            registracija = ResponseEntity.ok(poruka);
        } catch (Exception e) {
            poruka = "Neuspesna registracija, pokusajte ponovo...";
            System.out.println(e.getMessage());
            registracija = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(poruka);
        }

        return registracija;
    }

    @GetMapping("/api/menadzer/ispis")
    public ResponseEntity<?> listaMenadzmenta(HttpSession session) {

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(loggedKorisnik == null) {
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(null);
        } else if (!loggedKorisnik.getUloga().equals(Uloga.MENADZER)) {
            System.out.println("Pristup nije odobren.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pristup nije odobren.");
        }

        List<Menadzer> menadzerList = menadzerRepository.findAll();
        List<Menadzer> ispis = new ArrayList<>();

        for (Menadzer menadzer : menadzerList) {
            if(menadzer.getUloga().equals(Uloga.MENADZER)) {
                Menadzer m = new Menadzer();
                m.setId(menadzer.getId());
                m.setKorisnickoIme(menadzer.getKorisnickoIme());
                m.setLozinka(menadzer.getLozinka());
                m.setIme(menadzer.getIme());
                m.setPrezime(menadzer.getPrezime());
                m.setDatumRodjenja(menadzer.getDatumRodjenja());
                m.setPol(menadzer.getPol());
                m.setUloga(Uloga.MENADZER);
                m.setRestoran(menadzer.getRestoran());

                if (menadzer.getRestoran() != null) {
                    m.getRestoran().setPorudzbine(menadzer.getRestoran().getPorudzbine());
                }

                ispis.add(m);
            }
        }

        return ResponseEntity.ok(ispis);
    }

    @PostMapping("/api/admin/kreiraj-restoran")
    public ResponseEntity<?> kreirajRestoran(@RequestBody RestoranDto restoranDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(loggedKorisnik);
        } else if (!loggedKorisnik.getUloga().equals(Uloga.ADMIN)) {
            System.out.println("Pristup nije odobren.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pristup nije odobren.");
        }

        String poruka;
        ResponseEntity<String> kreirajRestoran;
        Restoran restoran = restoranDto.ToRestoran();

        List<Menadzer> menadzerList = menadzerRepository.findAll();
        Menadzer menadzer = new Menadzer();

        for(Menadzer m : menadzerList)
            if(m.getRestoran() == restoran) {
                restoran.setMenadzer(m);
                menadzer = m;
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

    @GetMapping("/api/menadzer/info/{id}")
    public Menadzer ispisiMenadzera(@PathVariable(name = "id") Long id, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            System.out.println("Nema sesije.");
            return null;
        } else if (!loggedKorisnik.getId().equals(id)) {
            System.out.println("Pristup nije odobren.");
            return null;
        }

        Menadzer menadzer = (Menadzer) korisnikService.findKorisnik(id);
        return menadzer;
    }

    @GetMapping("/api/admin/restorani/{id}/postavi-menadzera")
    public ResponseEntity<?> postaviMenadzera(@PathVariable(name = "id") Long id, @RequestParam String korisnickoIme, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(null);
        } else if (!loggedKorisnik.getUloga().equals(Uloga.ADMIN)) {
            System.out.println("Pristup nije odobren.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pristup nije odobren.");
        }

        Restoran restoran = restoranRepository.getById(id);

        if (restoran == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Restoran ne postoji.");
        }
        List<Restoran> restoranList = restoranRepository.findAll();
        for(Restoran r : restoranList)
            if(r.getMenadzer().toString().isEmpty())
                r.setMenadzer(menadzerRepository.getByKorisnickoIme(korisnickoIme));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Menadzer uspesno postavljen.");
    }


}


package com.loznica.blink.controller;

import com.loznica.blink.entity.*;
import com.loznica.blink.repository.DostavljacRepository;
import com.loznica.blink.repository.KorisnikRepository;
import com.loznica.blink.repository.MenadzerRepository;
import com.loznica.blink.security.RegistrationRequest;
import com.loznica.blink.service.KorisnikService;
import com.loznica.blink.service.SessionService;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class KorisnikRestController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/api/")
    public String dobrodoslica() {
        return "Dobrodosli u Blink!";
    }

    @GetMapping("/api/login/info")
    public ResponseEntity getInfo(HttpSession session) {

        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.KUPAC))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        return ResponseEntity.status(HttpStatus.OK).body(session.getAttribute("korisnik"));
    }

    @GetMapping("/api/korisnici")
    public List<Korisnik> listaKorisnika() {
        return korisnikRepository.findAll();
    }

    @PostMapping("/api/login/info/izmena")
    public ResponseEntity<Korisnik> setKorisnik(HttpSession session, @RequestBody RegistrationRequest registrationRequest, @RequestParam String korisnickoIme) {

        Korisnik k = korisnikRepository.getByKorisnickoIme(korisnickoIme);

        if(k == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        if(k.getAuth() == false)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        k.setKorisnickoIme(registrationRequest.getKorisnickoIme() == null || registrationRequest.getKorisnickoIme().toString().isEmpty() ? k.getKorisnickoIme() : registrationRequest.getKorisnickoIme());
        k.setLozinka(registrationRequest.getLozinka() == null || registrationRequest.getLozinka().toString().isEmpty()  ?  k.getLozinka() : registrationRequest.getLozinka());
        k.setIme(registrationRequest.getIme() == null || registrationRequest.getIme().toString().isEmpty() ? k.getIme() : registrationRequest.getIme());
        k.setPrezime(registrationRequest.getPrezime() == null || registrationRequest.getPrezime().toString().isEmpty()  ? k.getPrezime() : registrationRequest.getPrezime());
        k.setPol(registrationRequest.getPol() == null || registrationRequest.getPol().toString().isEmpty() ? k.getPol() : registrationRequest.getPol());
        k.setDatumRodjenja(registrationRequest.getDatumRodjenja() == null || registrationRequest.getDatumRodjenja().toString().isEmpty()  ? k.getDatumRodjenja() : registrationRequest.getDatumRodjenja());

        try {
            System.out.println("Uspesna izmena.");
        } catch (Exception e) {
            System.out.println("Neuspesna izmena.");
        }
        korisnikRepository.save(k);

        return ResponseEntity.ok(k);
    }

    @GetMapping("/api/admin/korisnici/{id}")
    public ResponseEntity getKorisnik(@PathVariable(name = "id") Long id, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.ADMIN))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        return ResponseEntity.status(HttpStatus.OK).body(korisnikService.findKorisnik(id));
    }

    @PostMapping("/api/admin/sacuvaj-korisnika")
    public ResponseEntity saveKorisnici(@RequestBody Korisnik korisnik, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.ADMIN))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        this.korisnikService.save(korisnik);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Korisnik je uspesno sacuvan!");
    }

    @DeleteMapping("/api/admin/obrisi-korisnika/{id}")
    public ResponseEntity deleteKorisnik(@PathVariable(name = "id") Long id, @RequestParam String korisnickoIme) {
//        if (!sessionService.validate(session))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
//
//        if (!sessionService.getUloga(session).equals(Uloga.ADMIN))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Korisnik loggedKorisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);

        if(loggedKorisnik == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(!Objects.equals(loggedKorisnik.getId(), id))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        System.out.println(loggedKorisnik);
        korisnikRepository.delete(loggedKorisnik);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Korisnik je uspesno uklonjen!");
    }

    @GetMapping("/api/admin/korisnici/ispis")
    public ResponseEntity getKorisnici(HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.ADMIN))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<Korisnik> listaKorisnika = new ArrayList<>();

        for (Korisnik k : korisnikRepository.findAll())
            if (!(k.getUloga() == Uloga.ADMIN))
                listaKorisnika.add(k);

        return ResponseEntity.status(HttpStatus.OK).body(listaKorisnika);
    }

    @PostMapping("/api/admin/kreiraj-menadzera")
    public ResponseEntity kreirajMenadzera(@RequestBody RegistrationRequest registrationRequest, HttpSession session, @RequestParam String korisnickoIme) {
//        if (!sessionService.validate(session))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
//
//        if (!sessionService.getUloga(session).equals(Uloga.ADMIN))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
        Korisnik loggedKorisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);

        if(loggedKorisnik == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(loggedKorisnik.getUloga() != Uloga.ADMIN)
            return new ResponseEntity(HttpStatus.FORBIDDEN);


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
            poruka = "Hvala Vam na registraciji. Zelimo Vam prijatan dan.";
            registracija = ResponseEntity.ok(poruka);
        } catch (Exception e) {
            poruka = "Neuspesna registracija, pokusajte ponovo...";
            System.out.println(e.getMessage());
            registracija = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(poruka);
        }

        return registracija;
    }

    @PostMapping("/api/admin/kreiraj-dostavljaca")
    public ResponseEntity kreirajDostavljaca(@RequestBody RegistrationRequest registrationRequest, HttpSession session, @RequestParam String korisnickoIme) {
//        if (!sessionService.validate(session))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
//
//        if (!sessionService.getUloga(session).equals(Uloga.ADMIN))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Korisnik loggedKorisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);

        if(loggedKorisnik == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(loggedKorisnik.getUloga() != Uloga.ADMIN)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

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
            poruka = "Hvala Vam na registraciji. Zelimo Vam prijatan dan.";
            registracija = ResponseEntity.ok(poruka);
        } catch (Exception e) {
            poruka = "Neuspesna registracija, pokusajte ponovo...";
            System.out.println(e.getMessage());
            registracija = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(poruka);
        }

        return registracija;
    }

    @GetMapping("/api/menadzer/ispis")
    public ResponseEntity listaMenadzmenta(HttpSession session, @RequestParam String korisnickoIme) {
//        if (!sessionService.validate(session))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
//
//        if (!sessionService.getUloga(session).equals(Uloga.ADMIN))
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
        
        Korisnik loggedKorisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);

        if(loggedKorisnik == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        if(loggedKorisnik.getUloga() != Uloga.ADMIN)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<Menadzer> menadzerList = menadzerRepository.findAll();
        List<Menadzer> ispis = new ArrayList<>();

        for (Menadzer menadzer : menadzerList) {
            if (menadzer.getUloga().equals(Uloga.MENADZER)) {
                ispis.add(menadzer);
            }
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ispis);
    }


    @GetMapping("/api/menadzer/info/{id}")
    public ResponseEntity ispisiMenadzera(@PathVariable(name = "id") Long id, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.ADMIN))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        return ResponseEntity.status(HttpStatus.OK).body(menadzerRepository.findById(id));
    }

    @GetMapping("/api/korisnik/{id}/")
    public ResponseEntity ispisiKorisnika(@PathVariable(name = "id") Long id, @RequestParam String korisnickoIme) {

        Korisnik loggedKorisnik = korisnikRepository.getByKorisnickoIme(korisnickoIme);

        if(loggedKorisnik == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Korisnik k = korisnikRepository.getById(id);

        if(k == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Korisnik temp = new Korisnik();

        temp.setId(k.getId());
        temp.setKorisnickoIme(k.getKorisnickoIme());
        temp.setLozinka(k.getLozinka());
        temp.setIme(k.getIme());
        temp.setPrezime(k.getPrezime());
        temp.setPol(k.getPol());
        temp.setDatumRodjenja(k.getDatumRodjenja());
        temp.setUloga(k.getUloga());

        if(loggedKorisnik.getUloga() == Uloga.ADMIN)
            return ResponseEntity.status(HttpStatus.OK).body(temp);
        else
            if(loggedKorisnik.getId().equals(k.getId()))
                return ResponseEntity.status(HttpStatus.OK).body(k);

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
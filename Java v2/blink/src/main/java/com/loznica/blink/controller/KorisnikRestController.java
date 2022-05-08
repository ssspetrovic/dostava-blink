package com.loznica.blink.controller;

import com.loznica.blink.dto.LoginDto;
import com.loznica.blink.entity.Korisnik;
import com.loznica.blink.entity.Menadzer;
import com.loznica.blink.entity.Uloga;
import com.loznica.blink.repository.KorisnikRepository;
import com.loznica.blink.repository.MenadzerRepository;
import com.loznica.blink.security.RegistrationRequest;
import com.loznica.blink.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class KorisnikRestController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @GetMapping("/api/")
    public String dobrodoslica() {
        return "Dobrodosli u Blink!";
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpSession session) {
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty())
            return new ResponseEntity("Invalid login!", HttpStatus.BAD_REQUEST);
        Korisnik loggedKorisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        if(loggedKorisnik == null)
            return new ResponseEntity<>("Korisnik ne postoji!", HttpStatus.NOT_FOUND);
        session.setAttribute("korisnik", loggedKorisnik);
        return ResponseEntity.ok("Uspesno ste se ulogovali!");
    }

    @GetMapping("/api/login/info")
    public ResponseEntity<Korisnik> getInfo(HttpSession session){

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(loggedKorisnik == null){
            System.out.println("Nema sesije.");
        }
        else{
            System.out.println(loggedKorisnik);
        }
        return ResponseEntity.ok(loggedKorisnik);
    }

    @PostMapping("/api/login/info/izmena")
    public ResponseEntity<Korisnik> setInfo(HttpSession session, @RequestBody RegistrationRequest registrationRequest){

        Korisnik k = (Korisnik) session.getAttribute("korisnik");

        k.setKorisnickoIme(registrationRequest.getKorisnickoIme() == null ? k.getIme() : registrationRequest.getKorisnickoIme());
        k.setLozinka(registrationRequest.getLozinka() == null ? k.getLozinka() : registrationRequest.getLozinka());
        k.setIme(registrationRequest.getIme() == null ? k.getIme() : registrationRequest.getIme());
        k.setPrezime(registrationRequest.getPrezime() == null ? k.getPrezime() : registrationRequest.getPrezime());
        k.setPol(registrationRequest.getPol() == null ? k.getPol() : registrationRequest.getPol());
        k.setDatumRodjenja(registrationRequest.getDatumRodjenja() == null ? k.getDatumRodjenja() : registrationRequest.getDatumRodjenja());

        try{
            System.out.println("Uspesna izmena.");
        }catch (Exception e){
            System.out.println("Neuspesna izmena.");
        }

        return ResponseEntity.ok(k);

    }

    @PostMapping("/api/register")
    public ResponseEntity<?> registracijaKorisnika(@RequestBody RegistrationRequest registrationRequest) {
        String poruka;
        ResponseEntity<String> registracija;

        if(korisnikRepository.findByKorisnickoIme(registrationRequest.getKorisnickoIme()).isPresent()) {
            poruka = "This user already exists";
            return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(poruka);
        }

        Korisnik k = new Korisnik();
        k.setKorisnickoIme(registrationRequest.getKorisnickoIme());
        k.setLozinka(registrationRequest.getLozinka());
        k.setIme(registrationRequest.getIme());
        k.setPrezime(registrationRequest.getPrezime());
        k.setPol(registrationRequest.getPol());
        k.setDatumRodjenja(registrationRequest.getDatumRodjenja());
        k.setUloga(Uloga.KUPAC);

        try{
            korisnikRepository.save(k);
            poruka = "Hvala Vam na registraciji. Sve najlepse Vama i rodbini.";
            registracija = ResponseEntity.ok(poruka);
        }catch (Exception e){
            poruka = "Neuspesna registracija, pokusajte ponovo...";
            System.out.println(e.getMessage());
            registracija = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(poruka);
        }

        return registracija;

    }

    @GetMapping("/api/korisnici")
    public List<Korisnik> getKorisnici() {
        return korisnikService.findAll();
    }

    @GetMapping("/api/korisnici/{id}")
    public Korisnik getKorisnik(@PathVariable(name = "id") Long id) {
        return korisnikService.findKorisnik(id);
    }

    @PostMapping("/api/save-korisnici")
    public String saveKorisnici(@RequestBody Korisnik korisnik) {
        this.korisnikService.save(korisnik);
        return "Uspesno sacuvan korisnik!";
    }

    @RequestMapping(value = "/api/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteKorisnik(@PathVariable(name = "id") Long id) {
        korisnikService.deleteById(id);
        return "Uspesno 'uklonjen' korisnik uwu!";
    }

    @GetMapping("/api/admin/clanovi")
    public ResponseEntity<?> getKorisnici(HttpSession session) {

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(loggedKorisnik == null || !loggedKorisnik.getUloga().equals(Uloga.ADMIN)){
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(loggedKorisnik);
        }
        else{
            List<Korisnik> korisnikList = korisnikRepository.findAll();
            List<RegistrationRequest> registrationRequestList = new ArrayList<>();

            for(Korisnik korisnik : korisnikList){
                if(!korisnik.getUloga().equals(Uloga.ADMIN)){
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

    }

    @PostMapping("/api/menadzeri")
    public ResponseEntity<?> kreirajMenadzera(@RequestBody RegistrationRequest registrationRequest, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(loggedKorisnik == null || !loggedKorisnik.getUloga().equals(Uloga.ADMIN)){
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(loggedKorisnik);
        }
        else {
            String poruka;
            ResponseEntity<String> registracija;

            if (korisnikRepository.findByKorisnickoIme(registrationRequest.getKorisnickoIme()).isPresent()) {
                poruka = "This user already exists";
                return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(poruka);
            }

            Korisnik k = new Korisnik();
            k.setKorisnickoIme(registrationRequest.getKorisnickoIme());
            k.setLozinka(registrationRequest.getLozinka());
            k.setIme(registrationRequest.getIme());
            k.setPrezime(registrationRequest.getPrezime());
            k.setPol(registrationRequest.getPol());
            k.setDatumRodjenja(registrationRequest.getDatumRodjenja());
            k.setUloga(Uloga.MENADZER);

            try {
                korisnikRepository.save(k);
                poruka = "Hvala Vam na registraciji. Sve najlepse Vama i rodbini.";
                registracija = ResponseEntity.ok(poruka);
            } catch (Exception e) {
                poruka = "Neuspesna registracija, pokusajte ponovo...";
                System.out.println(e.getMessage());
                registracija = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(poruka);
            }

            return registracija;
        }
    }

    @PostMapping("/api/dostavljaci")
    public ResponseEntity<?> kreirajDostavljaca(@RequestBody RegistrationRequest registrationRequest, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(loggedKorisnik == null || !loggedKorisnik.getUloga().equals(Uloga.ADMIN)){
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(loggedKorisnik);
        }
        else {
            String poruka;
            ResponseEntity<String> registracija;

            if (korisnikRepository.findByKorisnickoIme(registrationRequest.getKorisnickoIme()).isPresent()) {
                poruka = "This user already exists";
                return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(poruka);
            }

            Korisnik k = new Korisnik();
            k.setKorisnickoIme(registrationRequest.getKorisnickoIme());
            k.setLozinka(registrationRequest.getLozinka());
            k.setIme(registrationRequest.getIme());
            k.setPrezime(registrationRequest.getPrezime());
            k.setPol(registrationRequest.getPol());
            k.setDatumRodjenja(registrationRequest.getDatumRodjenja());
            k.setUloga(Uloga.DOSTAVLJAC);

            try {
                korisnikRepository.save(k);
                poruka = "Hvala Vam na registraciji. Sve najlepse Vama i rodbini.";
                registracija = ResponseEntity.ok(poruka);
            } catch (Exception e) {
                poruka = "Neuspesna registracija, pokusajte ponovo...";
                System.out.println(e.getMessage());
                registracija = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(poruka);
            }

            return registracija;
        }
    }

    @GetMapping("/api/menadzeri/lista")
    public ResponseEntity<?> listaMenadzmenta(HttpSession session) {

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(loggedKorisnik == null || !loggedKorisnik.getUloga().equals(Uloga.MENADZER)){
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(null);
        }
        else{
            List<Menadzer> menadzerList = menadzerRepository.findAll();
            List<Menadzer> ispis = new ArrayList<>();

            for(Menadzer menadzer : menadzerList){
                if(menadzer.getUloga().equals(Uloga.MENADZER)) {
                    Menadzer m = new Menadzer();
                    m.setId(menadzer.getId());
                    m.setKorisnickoIme(menadzer.getKorisnickoIme());
                    m.setLozinka(menadzer.getLozinka());
                    m.setIme(menadzer.getIme());
                    m.setPrezime(menadzer.getPrezime());
                    m.setDatumRodjenja(menadzer.getDatumRodjenja());
                    m.setPol(menadzer.getPol());
                    m.setRestoran(menadzer.getRestoran());
                    m.getRestoran().setPorudzbine(menadzer.getRestoran().getPorudzbine());
                    m.setUloga(Uloga.MENADZER);
                    ispis.add(m);
                }
            }

            return ResponseEntity.ok(ispis);
        }

    }

}


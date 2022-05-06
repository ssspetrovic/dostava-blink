package com.loznica.blink.controller;

import com.loznica.blink.dto.LoginDto;
import com.loznica.blink.entity.Korisnik;
import com.loznica.blink.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class KorisnikRestController {

    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/api/")
    public String dobrodoslica() {
        return "Dobrodosli u Blink!";
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session) {
        if(loginDto.getUsername().isEmpty() || loginDto.getPassword().isEmpty())
            return new ResponseEntity("Invalid login!", HttpStatus.BAD_REQUEST);
        Korisnik loggedKorisnik = korisnikService.login(loginDto.getUsername(), loginDto.getPassword());
        if(loggedKorisnik == null)
            return new ResponseEntity<>("Korisnik ne postoji!", HttpStatus.NOT_FOUND);
        session.setAttribute("korisnik", loggedKorisnik);
        return ResponseEntity.ok("Uspesno ste se ulogovali!");
    }

    @GetMapping("/api/korisnici")
    public List<Korisnik> getKorisnici() {
        List<Korisnik> korisnikList = korisnikService.findAll();
        return korisnikList;
    }

    @GetMapping("/api/korisnici/{id}")
    public Korisnik getKorisnik(@PathVariable(name = "id") Long id) {
        Korisnik korisnik = korisnikService.findKorisnik(id);
        return korisnik;
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

}

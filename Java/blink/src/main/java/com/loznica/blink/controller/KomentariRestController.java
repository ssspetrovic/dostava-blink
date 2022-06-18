package com.loznica.blink.controller;

import com.loznica.blink.entity.*;
import com.loznica.blink.repository.KupacRepository;
import com.loznica.blink.repository.PorudzbineArtikliRepository;
import com.loznica.blink.repository.RestoranRepository;
import com.loznica.blink.service.KomentarService;
import com.loznica.blink.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Set;

@RestController
public class KomentariRestController {

    @Autowired
    RestoranRepository restoranRepository;

    @Autowired
    KomentarService komentarService;

    @Autowired
    SessionService sessionService;

    @Autowired
    KupacRepository kupacRepository;

    @Autowired
    PorudzbineArtikliRepository porudzbineArtikliRepository;

    @PostMapping("/api/komentar/{id}")
    public ResponseEntity komentari(@PathVariable(name = "id") Long id, @RequestBody Komentar komentar, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        if (!sessionService.getUloga(session).equals(Uloga.KUPAC))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(loggedKorisnik == null)
            return ResponseEntity.ok("Nema sesije...");

        Kupac kupac = kupacRepository.getById(loggedKorisnik.getId());

        if(kupac == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        PorudzbineArtikli porudzbina = null;

        for (PorudzbineArtikli pa : porudzbineArtikliRepository.findAll())
            if (Objects.equals(id, pa.getId()))
                porudzbina = pa;

        Porudzbina p = porudzbina.getPorudzbina();

        if (komentar == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        if(p.getStatus().equals(Status.DOSTAVLJENA)) {
            Restoran restoran = porudzbina.getPorudzbina().getRestoran();

            komentar.setKupac(kupac);
            komentar.setRestoran(restoran);
            komentarService.sacuvajKomentar(komentar);

            restoran.setKomentari(Set.of(komentar));
            restoranRepository.save(restoran);

            kupac.setKomentari(Set.of(komentar));
            kupacRepository.save(kupac);

            return ResponseEntity.status(HttpStatus.OK).body(komentar);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

}

package com.loznica.blink.controller;

import com.loznica.blink.dto.NovaPorudzbinaDto;
import com.loznica.blink.dto.NovaPorudzbinaKupcaDto;
import com.loznica.blink.dto.PorudzbinaDto;
import com.loznica.blink.dto.PorudzbinaKupcaDto;
import com.loznica.blink.entity.*;
import com.loznica.blink.repository.DostavljacRepository;
import com.loznica.blink.repository.KupacRepository;
import com.loznica.blink.repository.MenadzerRepository;
import com.loznica.blink.service.PorudzbinaService;
import com.loznica.blink.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@RestController
public class PorudzbinaRestController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private PorudzbinaService porudzbinaService;

    @GetMapping("/api/porudzbine")
    public ResponseEntity vratiPorudzbine(HttpSession session) {

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(null);
        } else if (!loggedKorisnik.getUloga().equals(Uloga.KUPAC)) {
            System.out.println("Pristup nije odobren.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pristup nije odobren.");
        }

        Kupac kupac = kupacRepository.getById(loggedKorisnik.getId());

        if(kupac.getKorisnickoIme() == null || kupac.getKorisnickoIme().toString().isEmpty())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<PorudzbinaDto> porudzbinaDtoList = new ArrayList<>();

        for(Porudzbina p : kupac.getSvePorudzbine()) {
            PorudzbinaDto porudzbinaDto = new PorudzbinaDto(p.getUuid(), p.getDatumPorudzbine());

            List<PorudzbinaKupcaDto> porudzbinaKupcaDtoList = new ArrayList<>();

            for(PorudzbineArtikli pa : p.getArtikli()) {
                Artikal a = pa.getArtikal();
                porudzbinaKupcaDtoList.add(new PorudzbinaKupcaDto(a.getNaziv(), a.getCena(), pa.getKolicina()));
            }

            porudzbinaDto.setPorudzbineKupca(porudzbinaKupcaDtoList);
            porudzbinaDtoList.add(porudzbinaDto);
        }

        return new ResponseEntity(porudzbinaDtoList,HttpStatus.OK);
    }

    @GetMapping("/api/porudzbine/dostava")
    public ResponseEntity zaDostavljanje(HttpSession session) {

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (loggedKorisnik == null) {
            System.out.println("Nema sesije.");
            return ResponseEntity.ok(null);
        } else if (!loggedKorisnik.getUloga().equals(Uloga.DOSTAVLJAC)) {
            System.out.println("Pristup nije odobren.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pristup nije odobren.");
        }

        Dostavljac dostavljac = dostavljacRepository.getById(loggedKorisnik.getId());
        if(dostavljac.getKorisnickoIme() == null || dostavljac.getKorisnickoIme().toString().isEmpty())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<PorudzbinaDto> porudzbinaDtoList = new ArrayList<>();

        for(Porudzbina p : dostavljac.getPorudzbine()) {
            if(p.getStatus() == Status.CEKA_DOSTAVLJACA) {
                PorudzbinaDto porudzbinaDto = new PorudzbinaDto(p.getUuid(), p.getDatumPorudzbine());

                List<PorudzbinaKupcaDto> porudzbinaKupcaDtoList = new ArrayList<>();

                for (PorudzbineArtikli pa : p.getArtikli()) {
                    Artikal a = pa.getArtikal();
                    porudzbinaKupcaDtoList.add(new PorudzbinaKupcaDto(a.getNaziv(), a.getCena(), pa.getKolicina()));
                }

                porudzbinaDto.setPorudzbineKupca(porudzbinaKupcaDtoList);
                porudzbinaDtoList.add(porudzbinaDto);
            }
        }

        return new ResponseEntity(porudzbinaDtoList,HttpStatus.OK);

    }

    @GetMapping("/api/porudzbine/menadzeri")
    public ResponseEntity pregled(HttpSession session) {

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if(!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(!loggedKorisnik.getUloga().equals(Uloga.MENADZER))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Menadzer menadzer = menadzerRepository.getById(loggedKorisnik.getId());
        if(menadzer.getKorisnickoIme() == null || menadzer.getKorisnickoIme().toString().isEmpty())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<PorudzbinaDto> porudzbinaDtoList = new ArrayList<>();

        for(Porudzbina p : menadzer.getRestoran().getPorudzbine()) {

                PorudzbinaDto porudzbinaDto = new PorudzbinaDto(p.getUuid(), p.getDatumPorudzbine());

                List<PorudzbinaKupcaDto> porudzbinaKupcaDtoList = new ArrayList<>();

                for (PorudzbineArtikli pa : p.getArtikli()) {
                    Artikal a = pa.getArtikal();
                    porudzbinaKupcaDtoList.add(new PorudzbinaKupcaDto(a.getNaziv(), a.getCena(), pa.getKolicina()));
                }

                porudzbinaDto.setPorudzbineKupca(porudzbinaKupcaDtoList);
                porudzbinaDtoList.add(porudzbinaDto);

        }

        return new ResponseEntity(porudzbinaDtoList,HttpStatus.OK);


    }

    @PostMapping("/api/porudzbine")
    public ResponseEntity poruci(@RequestBody NovaPorudzbinaDto novaPorudzbinaDto, HttpSession session) {
        if(!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Hashtable<String, String> greska = new Hashtable<>();
        if(novaPorudzbinaDto.getIdRestorana() == 0)
            greska.put("IdRestorana", "ID ne sme biti 0...");
        if(novaPorudzbinaDto.getNovePorudzbine() == null || novaPorudzbinaDto.getNovePorudzbine().size() == 0)
            greska.put("NovePorudzbine", "Korpa ne sme biti prazna!");

        if(novaPorudzbinaDto.getNovePorudzbine() != null)
            for (int i = 0; i < novaPorudzbinaDto.getNovePorudzbine().size(); i++) {
                NovaPorudzbinaKupcaDto artikal = novaPorudzbinaDto.getNovePorudzbine().get(i);

                if (artikal.getId() == null)
                    greska.put("Artikal [" + (i + 1) + "]", "Nedostaje id.");
                if (artikal.getKolicina() == 0)
                    greska.put("Artikal [" + (i + 1) + "]", "Broj artikala ne sme biti 0!");
            }


        if(!greska.isEmpty())
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        try {
            porudzbinaService.sacuvajPorudzbinu(novaPorudzbinaDto, sessionService.getKorisnickoIme(session));
        } catch (Exception e) {
            System.out.println(sessionService.getKorisnickoIme(session));
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).body(novaPorudzbinaDto);
    }

    @DeleteMapping("/api/porudzbine")
    public ResponseEntity skiniIzKorpe(@RequestBody NovaPorudzbinaDto novaPorudzbinaDto, HttpSession session) {
        if(!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Hashtable<String, String> greska = new Hashtable<>();
        if(novaPorudzbinaDto.getIdRestorana() == 0)
            greska.put("IdRestorana", "ID ne sme biti 0...");
        if(novaPorudzbinaDto.getNovePorudzbine() == null || novaPorudzbinaDto.getNovePorudzbine().size() == 0)
            greska.put("NovePorudzbine", "Korpa ne sme biti prazna!");

        if(novaPorudzbinaDto.getNovePorudzbine() != null)
            for(int i = 0; i < novaPorudzbinaDto.getNovePorudzbine().size(); i++) {
                NovaPorudzbinaKupcaDto artikal = novaPorudzbinaDto.getNovePorudzbine().get(i);

                if(artikal.getId() == null)
                    greska.put("Artikal [" + i+1 + "]", "Nedostaje id.");
                if(artikal.getKolicina() == 0)
                    greska.put("Artikal [" + i+1 + "]", "Broj artikala ne sme biti 0!");
            }

        if(!greska.isEmpty())
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        try {
            porudzbinaService.obrisiPorudzbinu(novaPorudzbinaDto, sessionService.getKorisnickoIme(session));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}

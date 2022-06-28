package com.loznica.blink.controller;

import com.loznica.blink.dto.NovaPorudzbinaDto;
import com.loznica.blink.dto.NovaPorudzbinaKupcaDto;
import com.loznica.blink.dto.PorudzbinaDto;
import com.loznica.blink.dto.PorudzbinaKupcaDto;
import com.loznica.blink.entity.*;
import com.loznica.blink.repository.*;
import com.loznica.blink.service.PorudzbinaService;
import com.loznica.blink.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

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

    @Autowired
    private PorudzbineArtikliRepository porudzbineArtikliRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @GetMapping("/api/porudzbine")
    public ResponseEntity vratiPorudzbine(HttpSession session) {

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!loggedKorisnik.getUloga().equals(Uloga.KUPAC))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Kupac kupac = kupacRepository.getById(loggedKorisnik.getId());

        if (kupac.getKorisnickoIme() == null || kupac.getKorisnickoIme().toString().isEmpty())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<PorudzbinaDto> porudzbinaDtoList = new ArrayList<>();

        for (Porudzbina p : kupac.getSvePorudzbine()) {
            PorudzbinaDto porudzbinaDto = new PorudzbinaDto(p.getUuid(), p.getDatumPorudzbine());

            List<PorudzbinaKupcaDto> porudzbinaKupcaDtoList = new ArrayList<>();

            for (PorudzbineArtikli pa : p.getArtikli()) {
                Artikal a = pa.getArtikal();
                porudzbinaKupcaDtoList.add(new PorudzbinaKupcaDto(a.getNaziv(), a.getCena(), pa.getKolicina()));
            }

            porudzbinaDto.setPorudzbineKupca(porudzbinaKupcaDtoList);
            porudzbinaDtoList.add(porudzbinaDto);
        }

        return new ResponseEntity(porudzbinaDtoList, HttpStatus.OK);
    }

    @GetMapping("/api/porudzbine/menadzeri")
    public ResponseEntity pregled(HttpSession session) {

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!loggedKorisnik.getUloga().equals(Uloga.MENADZER))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Menadzer menadzer = menadzerRepository.getById(loggedKorisnik.getId());

        List<Porudzbina> porudzbinaList = new ArrayList<>();

        for(Porudzbina p : porudzbinaRepository.findAll())
            if(menadzer.getRestoran() == p.getRestoran())
                porudzbinaList.add(p);

        if (menadzer.getKorisnickoIme() == null || menadzer.getKorisnickoIme().toString().isEmpty())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<PorudzbinaDto> porudzbinaDtoList = new ArrayList<>();

        for (Porudzbina p : porudzbinaList) {

            PorudzbinaDto porudzbinaDto = new PorudzbinaDto(p.getUuid(), p.getDatumPorudzbine());

            List<PorudzbinaKupcaDto> porudzbinaKupcaDtoList = new ArrayList<>();

            for (PorudzbineArtikli pa : p.getArtikli()) {
                Artikal a = pa.getArtikal();
                porudzbinaKupcaDtoList.add(new PorudzbinaKupcaDto(a.getNaziv(), a.getCena(), pa.getKolicina()));
            }

            porudzbinaDto.setPorudzbineKupca(porudzbinaKupcaDtoList);
            porudzbinaDtoList.add(porudzbinaDto);
        }

        return new ResponseEntity(porudzbinaDtoList, HttpStatus.OK);
    }

    @PostMapping("/api/porudzbine")
    public ResponseEntity poruci(@RequestBody NovaPorudzbinaDto novaPorudzbinaDto, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.KUPAC))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Hashtable<String, String> greska = new Hashtable<>();

        if (novaPorudzbinaDto.getIdRestorana() == 0)
            greska.put("IdRestorana", "ID ne sme biti 0...");

        if (novaPorudzbinaDto.getNovePorudzbine() == null || novaPorudzbinaDto.getNovePorudzbine().size() == 0)
            greska.put("NovePorudzbine", "Korpa ne sme biti prazna!");

        if (novaPorudzbinaDto.getNovePorudzbine() != null)
            for (int i = 0; i < novaPorudzbinaDto.getNovePorudzbine().size(); i++) {
                NovaPorudzbinaKupcaDto artikal = novaPorudzbinaDto.getNovePorudzbine().get(i);

                if (artikal.getId() == null)
                    greska.put("Artikal [" + (i + 1) + "]", "Nedostaje id.");
                if (artikal.getKolicina() == 0)
                    greska.put("Artikal [" + (i + 1) + "]", "Broj artikala ne sme biti 0!");
            }


        if (!greska.isEmpty())
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        try {
            porudzbinaService.sacuvajPorudzbinu(novaPorudzbinaDto, sessionService.getKorisnickoIme(session));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).body(novaPorudzbinaDto);
    }


    @PostMapping("/api/porudzbine/{nazivArtikla}")
    public ResponseEntity skiniIzKorpe(@PathVariable String nazivArtikla, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.KUPAC))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Korisnik korisnik = (Korisnik) session.getAttribute("korisnik");
        Kupac kupac = kupacRepository.getById(korisnik.getId());
        Set<Porudzbina> korpa = porudzbinaService.obrisiPorudzbinu(kupac, nazivArtikla);
        if(korpa == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(korpa);
    }

    @PostMapping("/api/porudzbine/kolicina/{id}")
    public ResponseEntity izmeniKolicinu(@PathVariable(name = "id") Long id, HttpSession session, @RequestParam int kolicina) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.KUPAC))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if(kolicina == 0)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        try {
            porudzbinaService.izmeniPorudzbinu(id, kolicina);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Vasa porudzbina je izmenjena na " + kolicina + " porudzbine.");

    }

    @GetMapping("/api/porudzbine/lista")
    public ResponseEntity listaPorudzbina(HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.KUPAC))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        Kupac kupac = null;

        for (Kupac temp : kupacRepository.findAll())
            if (temp.getKorisnickoIme().equals(loggedKorisnik.getKorisnickoIme()))
                kupac = temp;

        if (kupac == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Set<PorudzbineArtikli> setPorudzbineArtikli = new HashSet<>();

        for (PorudzbineArtikli pa : porudzbineArtikliRepository.findAll())
            for (Porudzbina p : kupac.getSvePorudzbine())
                if (pa.getPorudzbina().equals(p)) {
                    setPorudzbineArtikli.add(pa);
                    break;
                }


        return ResponseEntity.status(HttpStatus.OK).body(setPorudzbineArtikli);
    }

    @GetMapping("/api/porudzbine/naruci/{id}")
    public ResponseEntity poruci(HttpSession session, @PathVariable(name = "id") Long id) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.KUPAC))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");

        Kupac kupac = null;

        for (Kupac temp : kupacRepository.findAll())
            if (temp.getKorisnickoIme().equals(loggedKorisnik.getKorisnickoIme()))
                kupac = temp;

        PorudzbineArtikli pa = null;

        for (PorudzbineArtikli porudzbineArtikli : porudzbineArtikliRepository.findAll())
            if (Objects.equals(porudzbineArtikli.getId(), id))
                pa = porudzbineArtikli;

        if (pa == null || kupac == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        Porudzbina narudzbina = pa.getPorudzbina();
        narudzbina.setStatus(Status.OBRADA);
        porudzbinaRepository.saveAndFlush(narudzbina);

        return ResponseEntity.status(HttpStatus.OK).body("Vasa porudzbina je prihvacena!");

    }

    @GetMapping("/api/porudzbine/priprema/{id}")
    public ResponseEntity uPripremi(@PathVariable(name = "id") Long id, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.MENADZER))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        PorudzbineArtikli porudzbina = null;

        for (PorudzbineArtikli pa : porudzbineArtikliRepository.findAll())
            if (Objects.equals(id, pa.getId()))
                porudzbina = pa;

        Porudzbina p = porudzbina.getPorudzbina();

        if (porudzbina == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (p.getStatus() == Status.OBRADA) {
            p.setStatus(Status.U_PRIPREMI);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        porudzbinaRepository.saveAndFlush(p);

        return ResponseEntity.status(HttpStatus.OK).body("Vasa porudzbina je sada u statusu: " + p.getStatus());
    }

    @GetMapping("/api/porudzbine/ceka/{id}")
    public ResponseEntity cekaDostavljaca(@PathVariable(name = "id") Long id, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.MENADZER))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        PorudzbineArtikli porudzbina = null;

        for (PorudzbineArtikli pa : porudzbineArtikliRepository.findAll())
            if (Objects.equals(id, pa.getId()))
                porudzbina = pa;

        Porudzbina p = porudzbina.getPorudzbina();

        if (porudzbina == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (p.getStatus() == Status.U_PRIPREMI) {
            p.setStatus(Status.CEKA_DOSTAVLJACA);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        porudzbinaRepository.saveAndFlush(p);

        return ResponseEntity.status(HttpStatus.OK).body("Vasa porudzbina je sada u statusu: " + p.getStatus());
    }

    @GetMapping("/api/porudzbine/transport/{id}")
    public ResponseEntity uTransportu(@PathVariable(name = "id") Long id, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.DOSTAVLJAC))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        PorudzbineArtikli porudzbina = null;

        for (PorudzbineArtikli pa : porudzbineArtikliRepository.findAll())
            if (Objects.equals(id, pa.getId()))
                porudzbina = pa;

        Porudzbina p = porudzbina.getPorudzbina();

        if (porudzbina == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (p.getStatus() == Status.CEKA_DOSTAVLJACA) {
            p.setStatus(Status.U_TRANSPORTU);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dostavljac jos uvek ceka porudzbinu.");
        }

        porudzbinaRepository.saveAndFlush(p);

        return ResponseEntity.status(HttpStatus.OK).body("Vasa porudzbina je sada u statusu: " + p.getStatus());
    }

    @GetMapping("/api/porudzbine/dostavljeno/{id}")
    public ResponseEntity dostavljeno(@PathVariable(name = "id") Long id, HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.DOSTAVLJAC))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        PorudzbineArtikli porudzbina = null;

        for (PorudzbineArtikli pa : porudzbineArtikliRepository.findAll())
            if (Objects.equals(id, pa.getId()))
                porudzbina = pa;

        Porudzbina p = porudzbina.getPorudzbina();

        if (porudzbina == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (p.getStatus() == Status.U_TRANSPORTU) {
            p.setStatus(Status.DOSTAVLJENA);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dostavljac je doneo porudzbinu.");
        }

        Kupac kupac = new Kupac();

        for(Kupac k : kupacRepository.findAll())
            for(Porudzbina por : k.getSvePorudzbine())
                if(por.equals(p))
                    kupac = k;

        kupac.setBrojBodova(porudzbina.getUkupnaCena() / 1000 * 133);

        kupacRepository.saveAndFlush(kupac);
        porudzbinaRepository.saveAndFlush(p);


        return ResponseEntity.status(HttpStatus.OK).body("Vasa porudzbina je sada u statusu: " + p.getStatus());
    }

    @GetMapping("/api/porudzbine/sveDostave")
    public ResponseEntity sveDostave(HttpSession session) {
        if (!sessionService.validate(session))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        if (!sessionService.getUloga(session).equals(Uloga.DOSTAVLJAC))
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("korisnik");
        Dostavljac dostavljac = dostavljacRepository.getById(loggedKorisnik.getId());

        if(dostavljac == null)
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dostavljac.getPorudzbine());

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
        if (dostavljac.getKorisnickoIme() == null || dostavljac.getKorisnickoIme().toString().isEmpty())
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        List<PorudzbinaDto> porudzbinaDtoList = new ArrayList<>();

        for (Porudzbina p : dostavljac.getPorudzbine()) {
            if (p.getStatus() == Status.CEKA_DOSTAVLJACA) {
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

        return new ResponseEntity(porudzbinaDtoList, HttpStatus.OK);
    }

}
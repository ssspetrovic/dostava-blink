package com.loznica.blink.configuration;

import com.loznica.blink.entity.*;
import com.loznica.blink.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

@Configuration
public class DatabaseConfiguration {
    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private PorudzbineArtikliRepository porudzbineArtikliRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Bean
    public boolean Instantiate() {
        Calendar c = new GregorianCalendar();
        c.set(2001, Calendar.JULY, 5);
        Korisnik srdjan = new Korisnik("spetrovic", "123", "Srdjan", "Petrovic", "Muski", c.getTime(), Uloga.ADMIN);
        c.set(2001, Calendar.DECEMBER, 27);
        Korisnik nikola = new Korisnik("pale", "456", "Nikola", "Pantic", "Muski", c.getTime(), Uloga.ADMIN);

        korisnikRepository.saveAll(List.of(srdjan, nikola));

        Restoran r = new Restoran("Atrijum", "Meksicki", new Lokacija());
        restoranRepository.save(r);
        Restoran rr = new Restoran("Machiatto", "Albanski", new Lokacija());
        restoranRepository.save(rr);

        c.set(2001, Calendar.JULY, 21);
        Menadzer sime = new Menadzer("sime", "la123", "Aleksa", "Simeunovic", "Muski", c.getTime(), Uloga.MENADZER);
        sime.setRestoran(r);
        menadzerRepository.save(sime);
        Menadzer mm = new Menadzer("mm", "lp100", "Savo", "Savic", "Muski", c.getTime(), Uloga.MENADZER);
        menadzerRepository.save(mm);

        Kupac kupac = new Kupac("aaa", "123", "A", "B", "Muski", c.getTime());
        kupac.setUloga(Uloga.KUPAC);
        kupacRepository.save(kupac);


        Lokacija l = new Lokacija(45.5D, 43.3D, "FTN");
        lokacijaRepository.save(l);
        Lokacija ll = new Lokacija(41D, 42D, "Most Slobode");
        lokacijaRepository.save(ll);

        c.set(2022, Calendar.MAY, 12);

        Artikal a = new Artikal("Monster Energy", 150, Tip.PICE, 200, "Osvezavajuce energetsko pice.");

        Artikal a2 = new Artikal("Pepsi", 80, Tip.PICE, 1500, "Gazirano pice.");

        Artikal a3 = new Artikal("Coca-Cola", 65, Tip.PICE, 2000, "Gazirano pice.");

        artikalRepository.saveAll(List.of(a, a2, a3));

        Porudzbina porudzbina = new Porudzbina(r, kupac);
        Porudzbina porudzbina2 = new Porudzbina(rr, kupac);
        PorudzbineArtikli pa = new PorudzbineArtikli(porudzbina, a, 50, 50 * a.getCena());
        PorudzbineArtikli pa2 = new PorudzbineArtikli(porudzbina2, a2, 20, 20 * a2.getCena());
        PorudzbineArtikli pa3 = new PorudzbineArtikli(porudzbina, a3, 40, 40 * a3.getCena());
        porudzbineArtikliRepository.save(pa);
        porudzbineArtikliRepository.save(pa2);
        porudzbina.setArtikli(Set.of(pa, pa3));
        porudzbina2.setArtikli(Set.of(pa2));
        porudzbina.setStatus(Status.CEKA_DOSTAVLJACA);
        porudzbinaRepository.save(porudzbina);
        porudzbinaRepository.save(porudzbina2);

        Kupac kupac2 = new Kupac("laki", "111", "Lazar", "Lazarevic", "Muski", c.getTime());
        kupac2.setUloga(Uloga.KUPAC);
        kupac.setSvePorudzbine(Set.of(porudzbina, porudzbina2));
        kupac2.setSvePorudzbine(Set.of(porudzbina, porudzbina2));
        kupacRepository.saveAll(Set.of(kupac, kupac2));

        c.set(1996, Calendar.MAY, 12);
        Dostavljac dostavljac = new Dostavljac("damir1", "999", "Damir", "Madzarevic", "Muski", c.getTime(), Uloga.DOSTAVLJAC);
        dostavljac.setPorudzbine(Set.of(porudzbina));
        dostavljacRepository.save(dostavljac);

        r.setArtikli(Set.of(a, a2));
        r.setLokacija(l);
        r.setMenadzer(sime);
        rr.setLokacija(ll);

        restoranRepository.saveAllAndFlush(List.of(r,rr));
        return true;
    }
}

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

    @Bean
    public boolean Instantiate(){
        Calendar c = new GregorianCalendar();
        c.set(2001, Calendar.JULY, 5);
        Korisnik srdjan = new Korisnik("spetrovic", "123", "Srdjan", "Petrovic", "Muski", c.getTime(), Uloga.ADMIN);
        c.set(2001, Calendar.DECEMBER, 27);
        Korisnik nikola = new Korisnik( "pale", "456", "Nikola", "Pantic", "Muski", c.getTime(), Uloga.ADMIN);

        korisnikRepository.saveAll(List.of(srdjan, nikola));

        Restoran r = new Restoran("lmao", "meksicki", new Lokacija());
        restoranRepository.save(r);
        Restoran rr = new Restoran();
        restoranRepository.save(rr);

        c.set(2001, Calendar.JULY, 21);
        Menadzer sime = new Menadzer( "sime", "la123", "Aleksa", "Simeunovic", "Muski", c.getTime(), Uloga.MENADZER);
        sime.setRestoran(r);
        menadzerRepository.save(sime);
        Menadzer mm = new Menadzer( "mm", "la123", "Aleksa", "Simeunovic", "Muski", c.getTime(), Uloga.MENADZER);
        mm.setRestoran(rr);
        menadzerRepository.save(mm);

        Kupac kupac = new Kupac("aaa", "123", "A", "B", "Muski", c.getTime());
        kupac.setUloga(Uloga.KUPAC);
        kupacRepository.save(kupac);


        Lokacija l = new Lokacija();
        lokacijaRepository.save(l);

        c.set(2022, Calendar.MAY, 12);

        Artikal a = new Artikal("Monster Energy", 150, Tip.PICE, 200, "Osvezavajuce energetsko pice.");
        a.setRestoran(r);

        Artikal a2 = new Artikal("Pepsi", 80, Tip.PICE, 1500, "Gazirano pice.");
        a2.setRestoran(rr);

        artikalRepository.saveAll(List.of(a, a2));

        Porudzbina porudzbina = new Porudzbina(r, kupac);
        PorudzbineArtikli pa = new PorudzbineArtikli(porudzbina, a, 4);
        porudzbineArtikliRepository.save(pa);
        porudzbina.setArtikli(Set.of(pa));
        porudzbinaRepository.save(porudzbina);

        Kupac kupac2 = new Kupac("laki", "111", "Lazar", "Lazarevic", "Muski", c.getTime());
        kupac2.setUloga(Uloga.KUPAC);
        kupac2.setSvePorudzbine(Set.of(porudzbina));
        kupacRepository.saveAndFlush(kupac2);

        return true;
    }
}

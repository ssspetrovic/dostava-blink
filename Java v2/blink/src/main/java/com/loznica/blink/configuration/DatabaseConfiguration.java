package com.loznica.blink.configuration;

import com.loznica.blink.entity.*;
import com.loznica.blink.repository.KorisnikRepository;
import com.loznica.blink.repository.LokacijaRepository;
import com.loznica.blink.repository.MenadzerRepository;
import com.loznica.blink.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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

    @Bean
    public boolean Instantiate(){
        Calendar c = new GregorianCalendar();
        c.set(2001, Calendar.JULY, 5);
        Korisnik srdjan = new Korisnik(1L, "spetrovic", "123", "Srdjan", "Petrovic", "Muski", c.getTime(), Uloga.ADMIN);
        c.set(2001, Calendar.DECEMBER, 27);
        Korisnik nikola = new Korisnik(2L, "pale", "456", "Nikola", "Pantic", "Muski", c.getTime(), Uloga.ADMIN);

        korisnikRepository.saveAll(List.of(srdjan, nikola));

        Restoran r = new Restoran();
        r.setNaziv("lmao");
        Restoran rr = new Restoran();

        c.set(2001, Calendar.JULY, 21);
        Menadzer sime = new Menadzer(3L, "sime", "la123", "Aleksa", "Simeunovic", "Muski", c.getTime(), Uloga.MENADZER, r);
        Menadzer mm = new Menadzer(4L, "mm", "la123", "Aleksa", "Simeunovic", "Muski", c.getTime(), Uloga.MENADZER);
        restoranRepository.saveAll(List.of(r, rr));
        menadzerRepository.saveAll(List.of(sime, mm));


        Lokacija l = new Lokacija();
        lokacijaRepository.save(l);

        return true;
    }
}

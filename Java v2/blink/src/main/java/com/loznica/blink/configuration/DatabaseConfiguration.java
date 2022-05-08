package com.loznica.blink.configuration;

import com.loznica.blink.entity.Korisnik;
import com.loznica.blink.entity.Menadzer;
import com.loznica.blink.entity.Restoran;
import com.loznica.blink.entity.Uloga;
import com.loznica.blink.repository.KorisnikRepository;
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

    @Bean
    public boolean Instantiate(){
        Calendar c = new GregorianCalendar();
        c.set(2001, Calendar.JULY, 5);
        Korisnik srdjan = new Korisnik(1L, "spetrovic", "123", "Srdjan", "Petrovic", "Muski", c.getTime(), Uloga.ADMIN);
        c.set(2001, Calendar.DECEMBER, 27);
        Korisnik nikola = new Korisnik(2L, "pale", "456", "Nikola", "Pantic", "Muski", c.getTime(), Uloga.ADMIN);

        korisnikRepository.saveAll(List.of(srdjan, nikola));

        Restoran r = new Restoran();
        restoranRepository.save(r);

        c.set(2001, Calendar.JULY, 21);
        Menadzer sime = new Menadzer(3L, "sime", "la123", "Aleksa", "Simeunovic", "Muski", c.getTime(), Uloga.MENADZER, r);
        menadzerRepository.save(sime);

        return true;
    }
}

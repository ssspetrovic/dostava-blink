package com.loznica.blink.configuration;

import com.loznica.blink.entity.Artikal;
import com.loznica.blink.entity.Korisnik;
import com.loznica.blink.entity.Restoran;
import com.loznica.blink.repository.ArtikalRepository;
import com.loznica.blink.repository.KorisnikRepository;
import com.loznica.blink.repository.MenadzerRepository;
import com.loznica.blink.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class DatabaseConfiguration {
    @Autowired
    private KorisnikRepository korisnikRepository;

    @Bean
    public boolean Instantiate() {
        Calendar c = new GregorianCalendar();
        c.set(2001, Calendar.JULY, 5);
        Korisnik srdjan = new Korisnik(1L, "spetrovic", "123", "Srdjan", "Petrovic", Korisnik.Pol.MUSKI, c.getTime(), Korisnik.Uloga.ADMIN);
        c.set(2002, Calendar.DECEMBER, 27);
        Korisnik nikola = new Korisnik(241L, "npantic", "admin123", "Nikola", "Pantic", Korisnik.Pol.MUSKI, c.getTime(), Korisnik.Uloga.ADMIN);
        c.set(2004, Calendar.OCTOBER, 13);
        Korisnik stefan = new Korisnik(334L, "stefan04", "loznika123", "Stefan", "Nikolic", Korisnik.Pol.MUSKI, c.getTime(), Korisnik.Uloga.MENADZER);
        c.set(2001, Calendar.AUGUST, 24);
        Korisnik sanja = new Korisnik(77L, "sanja01", "blink", "Sanja", "Markovic", Korisnik.Pol.ZENSKI, c.getTime(), Korisnik.Uloga.KUPAC);
        c.set(2001, Calendar.JUNE, 10);
        Korisnik marko = new Korisnik(420L, "markojov", "pw123", "Marko", "Jovanvoic", Korisnik.Pol.MUSKI, c.getTime(), Korisnik.Uloga.DOSTAVLJAC);

        korisnikRepository.saveAll( List.of(srdjan, nikola, stefan, sanja, marko) );

        return true;
    }

}

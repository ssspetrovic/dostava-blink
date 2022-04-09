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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class DatabaseConfiguration {
    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private RestoranRepository restoranRepository;


    @Bean
    public boolean Instantiate() {
        Restoran restoran = new Restoran("Jareban");
        restoranRepository.save(restoran);

        Artikal artikal1 = new Artikal("mleko");
        Artikal artikal2 = new Artikal("secer");
        artikal1.setCena(86.99);
        artikal2.setCena(65);

        Set<Artikal> artikli = new HashSet<>();
        artikli.add(artikal1);
        artikli.add(artikal2);

        restoran.setArtikli(artikli);

        Korisnik srdjan = new Korisnik("Srdjan");
        Korisnik nikola = new Korisnik("Nikola");
        Korisnik maddy = new Korisnik("Madison Beer");

        korisnikRepository.saveAll(
                List.of(srdjan, nikola, maddy)
        );

        return true;
    }

}

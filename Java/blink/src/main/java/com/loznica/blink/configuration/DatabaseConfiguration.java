package com.loznica.blink.configuration;

import com.loznica.blink.entity.*;
import com.loznica.blink.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

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

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private TipKupcaRepository tipKupcaRepository;


    @Bean
    public boolean Instantiate() {

        Restoran r = new Restoran();
        Porudzbina p = new Porudzbina();
        Kupac kp = new Kupac();
        Set<Komentar> komentari = new HashSet<>();
        Set<Porudzbina> porudzbine = new HashSet<>();

        Calendar c = new GregorianCalendar();
        c.set(2001, Calendar.JULY, 5);
        Korisnik srdjan = new Korisnik(1L, "spetrovic", "123", "Srdjan", "Petrovic", Korisnik.Pol.MUSKI, c.getTime(), Korisnik.Uloga.ADMIN);
        c.set(2001, Calendar.DECEMBER, 27);
        Korisnik nikola = new Korisnik(241L, "npantic", "admin123", "Nikola", "Pantic", Korisnik.Pol.MUSKI, c.getTime(), Korisnik.Uloga.ADMIN);

        korisnikRepository.saveAll( List.of(srdjan, nikola) );


        Lokacija l1 = new Lokacija(10L, "0 stepena", "34 stepena", "Dositejeva 16");
        Lokacija l2 = new Lokacija(11L, "12 stepena", "79 stepena", "Fakultet Tehnickih Nauka");

        lokacijaRepository.saveAll(List.of(l1, l2));


        Artikal secer = new Artikal(1024L, "Secer", 250, Artikal.Tip.JELO, Artikal.Kolicina.GRAMI,"1kg secera", r, p);
        Artikal mleko = new Artikal(1025L, "Mleko", 115, Artikal.Tip.PICE, Artikal.Kolicina.MILILITRI,"1l mleka", r, p);

        artikalRepository.saveAll(List.of(secer, mleko));

        Set<Artikal> artikli = new HashSet<>();
        artikli.add(secer);
        artikli.add(mleko);


        c.set(2001, Calendar.JUNE, 10);
        Dostavljac d1 = new Dostavljac(420L, "markojov", "pw123", "Marko", "Jovanovic", Korisnik.Pol.MUSKI, c.getTime(), Korisnik.Uloga.DOSTAVLJAC, porudzbine);

        dostavljacRepository.saveAll(List.of(d1));


        Restoran r1 = new Restoran(11000L, "Ognjiste", Restoran.TipRestorana.DOMACI, artikli, komentari);
        Restoran r2 = new Restoran(10250L, "Atrijum", Restoran.TipRestorana.ITALIJANSKI, artikli, komentari);
        restoranRepository.saveAll(List.of(r1, r2));


        c.set(2004, Calendar.OCTOBER, 13);
        Menadzer stefan = new Menadzer(334L, "stefan04", "loznika123", "Stefan", "Nikolic", Korisnik.Pol.MUSKI, c.getTime(), Korisnik.Uloga.MENADZER, r1);

        menadzerRepository.saveAll(List.of(stefan));

        c.set(2022, Calendar.APRIL, 13);
        Porudzbina p1 = new Porudzbina(15000L, UUID.randomUUID(), artikli, d1, r1, c.getTime(), 1500, "sanja01", Porudzbina.Status.U_PRIPREMI);

        porudzbinaRepository.saveAll(List.of(p1));

        porudzbine.add(p1);

        TipKupca tk1 = new TipKupca(111L, kp, TipKupca.Ime.HARDSTUCK, 10f, 10);

        tipKupcaRepository.saveAll(List.of(tk1));

        c.set(2001, Calendar.AUGUST, 24);
        Kupac kp1 = new Kupac(77L, "sanja01", "blink", "Sanja", "Markovic", Korisnik.Pol.ZENSKI, c.getTime(), Korisnik.Uloga.KUPAC, porudzbine,15, komentari, tk1);

        kupacRepository.saveAll(List.of(kp1));

        Komentar k1 = new Komentar(99L, kp1, r1, "Ne svidja mi se enterijer.", Komentar.Ocena.TRI);

        komentarRepository.saveAll(List.of(k1));

        komentari.add(k1);

        return true;
    }

}

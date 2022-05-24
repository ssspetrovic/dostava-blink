package com.loznica.blink.service;

import com.loznica.blink.entity.Korisnik;
import com.loznica.blink.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class LoginService {
    @Autowired
    private KorisnikRepository korisnikRepository;

    public Korisnik login(String korisnickoIme, String lozinka) throws AccountNotFoundException {
        Korisnik korisnik = nadjiKorisnika(korisnickoIme, (List<Korisnik>) korisnikRepository.findAll());

        if (korisnik == null)
            throw new AccountNotFoundException("Ne postoji unet korisnik!");

        if (!korisnik.getLozinka().equals(lozinka))
            throw new AccountNotFoundException("Pogresna sifra!");

        return korisnik;

    }

    private Korisnik nadjiKorisnika(String korisnickoIme, List<Korisnik> korisnikList) {
        for (Korisnik k : korisnikList) {
            if (k.getKorisnickoIme().equals(korisnickoIme))
                return k;
        }

        return null;
    }
}
package com.loznica.blink.service;

import com.loznica.blink.entity.Korisnik;
import com.loznica.blink.entity.Kupac;
import com.loznica.blink.entity.Uloga;
import com.loznica.blink.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {

    @Autowired
    private KupacRepository kupacRepository;

    public void Registracija(Kupac k, Uloga u) throws Exception {
        invalid(k.getKorisnickoIme(), kupacRepository.findAll());

        k.setUloga(u);
        kupacRepository.save(k);
    }

    private void invalid(String korisnickoIme, List<Kupac> korisnikList) throws Exception {
        for (Korisnik k : korisnikList)
            if (k.getKorisnickoIme().equals(korisnickoIme))
                pogresnoKorisnickoIme(korisnickoIme);
    }

    private void pogresnoKorisnickoIme(String korisnickoIme) throws Exception {
        throw new Exception("Korisnik sa imenom '" + korisnickoIme + "' vec postoji!");
    }

}
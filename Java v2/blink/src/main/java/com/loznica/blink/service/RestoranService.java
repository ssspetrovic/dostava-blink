package com.loznica.blink.service;

import com.loznica.blink.entity.Korisnik;
import com.loznica.blink.entity.Kupac;
import com.loznica.blink.entity.Restoran;
import com.loznica.blink.entity.Uloga;
import com.loznica.blink.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestoranService {
    @Autowired
    private RestoranRepository restoranRepository;

    public void KreirajRestoran(Restoran restoran) throws Exception {
        invalid(restoran.getNaziv(), restoranRepository.findAll());
    }

    private void invalid(String naziv, List<Restoran> restoranList) throws Exception {
        for(Restoran r : restoranList)
            if(r.getNaziv().equals(naziv))
                pogresanNaziv(naziv);
    }

    private void pogresanNaziv(String naziv) throws Exception {
        throw new Exception("Restoran sa imenom '" + naziv + "' vec postoji!");
    }
}

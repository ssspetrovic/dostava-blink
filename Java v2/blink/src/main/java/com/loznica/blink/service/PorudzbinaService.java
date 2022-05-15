package com.loznica.blink.service;

import com.loznica.blink.dto.NovaPorudzbinaDto;
import com.loznica.blink.entity.*;
import com.loznica.blink.repository.KupacRepository;
import com.loznica.blink.repository.PorudzbinaRepository;
import com.loznica.blink.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    public void sacuvajPorudzbinu(NovaPorudzbinaDto novaPorudzbinaDto, String korisncikoIme) throws Exception {
        Kupac kupac = kupacRepository.findByKorisnickoIme(korisncikoIme);
        Optional<Restoran> restoranOptional = restoranRepository.findById(novaPorudzbinaDto.getIdRestorana());

        if(restoranOptional.isEmpty())
            throw new Exception("Restoran ne postoji!");

        Porudzbina porudzbina = new Porudzbina(restoranOptional.get(), kupac);

        porudzbina.setArtikli(nadjiPorudzbinu(porudzbina, restoranOptional));
        porudzbinaRepository.save(porudzbina);

    }

    public void obrisiPorudzbinu(NovaPorudzbinaDto novaPorudzbinaDto, String korisncikoIme) throws Exception {
        Kupac kupac = kupacRepository.findByKorisnickoIme(korisncikoIme);
        Optional<Restoran> restoranOptional = restoranRepository.findById(novaPorudzbinaDto.getIdRestorana());

        if(restoranOptional.isEmpty())
            throw new Exception("Restoran ne postoji!");

        Porudzbina porudzbina = new Porudzbina(restoranOptional.get(), kupac);

        porudzbina.setArtikli(nadjiPorudzbinu(porudzbina, restoranOptional));
        porudzbinaRepository.delete(porudzbina);

    }

    private Set<PorudzbineArtikli> nadjiPorudzbinu(Porudzbina porudzbina, Optional<Restoran> restoranOptional) throws Exception {
        Set<PorudzbineArtikli> porudzbineArtiklis = new HashSet<>();
        for(PorudzbineArtikli artikal : porudzbina.getArtikli()) {
            Artikal pronadjen = null;
            for(Artikal a : restoranOptional.get().getArtikli()) {
                if(a.getId().equals(artikal.getId())) {
                    pronadjen = a;
                    break;
                }
            }

            if(pronadjen == null)
                throw new Exception("Artikal sa id " + artikal.getId() + "nije pronadjen.");

            porudzbineArtiklis.add(new PorudzbineArtikli(porudzbina, pronadjen, artikal.getKolicina()));

        }

        return porudzbineArtiklis;
    }

}

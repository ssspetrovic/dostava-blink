package com.loznica.blink.service;

import com.loznica.blink.dto.NovaPorudzbinaDto;
import com.loznica.blink.dto.NovaPorudzbinaKupcaDto;
import com.loznica.blink.entity.*;
import com.loznica.blink.repository.*;
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

    @Autowired
    private PorudzbineArtikliRepository porudzbineArtikliRepository;



    public void sacuvajPorudzbinu(NovaPorudzbinaDto novaPorudzbinaDto, String korisncikoIme) throws Exception {
        Kupac kupac = kupacRepository.findByKorisnickoIme(korisncikoIme);
        Optional<Restoran> restoranOptional = restoranRepository.findById(novaPorudzbinaDto.getIdRestorana());

        if (restoranOptional.isEmpty())
            throw new Exception("Restoran ne postoji!");

        Porudzbina porudzbina = new Porudzbina(restoranOptional.get(), kupac);

        porudzbina.setArtikli(nadjiPorudzbinu(porudzbina, restoranOptional, novaPorudzbinaDto));
        postaviCenu(porudzbina);
        porudzbinaRepository.saveAndFlush(porudzbina);
    }

    public void postaviCenu (Porudzbina porudzbina) {
        double cena = 0;
        for(PorudzbineArtikli pa : porudzbina.getArtikli())
            cena += pa.getArtikal().getCena()*pa.getKolicina();
        porudzbina.setCena(cena);
    }

    public Set<Porudzbina> obrisiPorudzbinu(Kupac kupac, String nazivArtikla) {

        if(kupac.getSvePorudzbine() == null)
            return null;

        for(Porudzbina p : kupac.getSvePorudzbine())
            for(PorudzbineArtikli pa : p.getArtikli())
                if(pa.getArtikal().getNaziv().equals(nazivArtikla)) {
                    if(pa.getKolicina() != 1) {
                        pa.setKolicina(pa.getKolicina() - 1);
                        pa.getArtikal().setKolicina(pa.getArtikal().getKolicina() + 1);
                        pa.setUkupnaCena(pa.getKolicina(), pa.getArtikal().getCena());
                        p.setCena(pa.getKolicina()*pa.getArtikal().getCena());
                    }
                    else {
                        pa.getArtikal().setKolicina(pa.getArtikal().getKolicina() + 1);
                        p.setCena(0);
                        pa.setUkupnaCena(0, pa.getArtikal().getCena());
                        p.getArtikli().remove(pa.getArtikal());
                    }
                    porudzbinaRepository.save(p);
                    return kupac.getSvePorudzbine();
                }

        return null;
    }

    public void izmeniPorudzbinu(Long id, int kolicina) {
        PorudzbineArtikli porudzbineArtikli = porudzbineArtikliRepository.getById(id);
        porudzbineArtikli.setKolicina(kolicina);
        porudzbineArtikli.setUkupnaCena(kolicina, porudzbineArtikli.getArtikal().getCena());
        porudzbineArtikliRepository.saveAndFlush(porudzbineArtikli);
    }

    private Set<PorudzbineArtikli> nadjiPorudzbinu(Porudzbina porudzbina, Optional<Restoran> restoranOptional, NovaPorudzbinaDto novaPorudzbinaDto) throws Exception {
        Set<PorudzbineArtikli> porudzbineArtiklis = new HashSet<>();

        for (NovaPorudzbinaKupcaDto artikal : novaPorudzbinaDto.getNovePorudzbine()) {
            Artikal pronadjen = null;
            for (Artikal a : restoranOptional.orElse(null).getArtikli()) {
                if (a.getId().equals(artikal.getId())) {
                    pronadjen = a;
                    break;
                }
            }

            if (pronadjen == null)
                throw new Exception("Artikal sa id " + artikal.getId() + "nije pronadjen.");

            NovaPorudzbinaKupcaDto novaPorudzbinaKupcaDto = null;

            for (NovaPorudzbinaKupcaDto np : novaPorudzbinaDto.getNovePorudzbine())
                if (np != null)
                    novaPorudzbinaKupcaDto = np;
            pronadjen.setKolicina(pronadjen.getKolicina() - artikal.getKolicina());
            PorudzbineArtikli pa = new PorudzbineArtikli(porudzbina, pronadjen, artikal.getKolicina(), pronadjen.getCena() * novaPorudzbinaKupcaDto.getKolicina());
            porudzbineArtiklis.add(pa);
            porudzbineArtikliRepository.save(pa);
        }


        return porudzbineArtiklis;
    }

}
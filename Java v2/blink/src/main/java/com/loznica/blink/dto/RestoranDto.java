package com.loznica.blink.dto;

import com.loznica.blink.entity.Artikal;
import com.loznica.blink.entity.Menadzer;
import com.loznica.blink.entity.Restoran;

import java.util.Set;

public class RestoranDto {
    private Restoran restoran;
    private Set<Artikal> artikli;

    private String naziv;

    private String tipRestorana;

    private Menadzer menadzer;

    public RestoranDto() {
        super();
    }

    public RestoranDto(Restoran restoran, Set<Artikal> artikli) {
        this.restoran = restoran;
        this.artikli = artikli;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
        this.artikli = artikli;
    }

    public Restoran ToRestoran() { return new Restoran(naziv, tipRestorana); }

    public Menadzer getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
    }
}

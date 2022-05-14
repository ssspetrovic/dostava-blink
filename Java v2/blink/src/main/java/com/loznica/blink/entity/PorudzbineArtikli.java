package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PorudzbineArtikli implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Porudzbina porudzbina;

    @ManyToOne
    private Artikal artikal;

    private int kolicina;

    public PorudzbineArtikli() { super(); }

    public PorudzbineArtikli(Porudzbina porudzbina, Artikal artikal, int kolicina) {
        this.porudzbina = porudzbina;
        this.artikal = artikal;
        this.kolicina = kolicina;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "PorudzbineArtikli{" +
                "id=" + id +
                ", porudzbina=" + porudzbina +
                ", artikal=" + artikal +
                ", kolicina=" + kolicina +
                '}';
    }

}

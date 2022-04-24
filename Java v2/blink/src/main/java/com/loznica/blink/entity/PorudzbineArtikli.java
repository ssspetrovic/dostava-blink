package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PorudzbineArtikli implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Porudzbina porudzbina;

    @ManyToOne
    private Artikal artikal;

    public PorudzbineArtikli() { super(); }

    public PorudzbineArtikli(Long id, Porudzbina porudzbina, Artikal artikal) {
        this.id = id;
        this.porudzbina = porudzbina;
        this.artikal = artikal;
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

    @Override
    public String toString() {
        return "PorudzbineArtikli{" +
                "id=" + id +
                ", porudzbina=" + porudzbina +
                ", artikal=" + artikal +
                '}';
    }
}

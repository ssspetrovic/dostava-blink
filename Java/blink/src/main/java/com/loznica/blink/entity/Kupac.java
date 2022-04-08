package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Kupac extends Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "porudzbine_kupac_id")
    private Porudzbina porudzbineKupac = new Porudzbina();
    @Column
    private int bodovi;

    @ManyToOne
    @JoinColumn(name = "tk_id")
    private TipKupca tk = new TipKupca();

    public TipKupca getTk() {
        return tk;
    }

    public Porudzbina getPorudzbineKupac() {
        return porudzbineKupac;
    }
}

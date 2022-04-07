package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kupac extends Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Porudzbina porudzbineKupac = new Porudzbina();
    @Column
    private int bodovi;
    @Column
    private TipKupca tk = new TipKupca();
}

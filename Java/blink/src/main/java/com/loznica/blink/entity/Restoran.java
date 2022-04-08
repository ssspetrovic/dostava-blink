package com.loznica.blink.entity;

import java.util.ArrayList;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restoran implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String naziv;
    @Column
    private String tipRestorana;
    @OneToOne
    private Artikal ponude = new Artikal();
    @OneToOne
    private Lokacija adresa = new Lokacija();

    public Lokacija getAdresa() {
        return adresa;
    }

    public Artikal getPonude() {
        return ponude;
    }
}

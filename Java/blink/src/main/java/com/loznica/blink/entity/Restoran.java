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
    private ArrayList<String> tipRestorana = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "ponude_naziv")
    private Artikal ponude = new Artikal();
    @ManyToOne
    @JoinColumn(name = "adresa_geografska_duzina")
    private Lokacija adresa = new Lokacija();

    public Lokacija getAdresa() {
        return adresa;
    }

    public Artikal getPonude() {
        return ponude;
    }
}

package com.loznica.blink.entity;

import java.util.ArrayList;
import javax.persistence.*;
import javax.print.DocFlavor;
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
    @ElementCollection
    private Set<String> tipRestorana = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "ponude_id")
    private Artikal ponude = new Artikal();

    @ManyToOne
    @JoinColumn(name = "adresa_id")
    private Lokacija adresa = new Lokacija();

    public Lokacija getAdresa() {
        return adresa;
    }

    public Artikal getPonude() {
        return ponude;
    }
}

package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Porudzbina implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long uuid;
    @OneToOne
    private Artikal poruceniArtikli = new Artikal();
    @OneToOne
    private Restoran mestoPorudzbine = new Restoran();
    @Column
    private String datumPorudzbine;
    @Column
    private float cena;
    @Column
    private String korisnickoIme;
    private enum status {OBRADA, PRIPREMA, CEKANJE, TRANSPORT, DOSTAVLJENO, OTKAZANO}

    public Restoran getMestoPorudzbine() {
        return mestoPorudzbine;
    }

    public Artikal getPoruceniArtikli() {
        return poruceniArtikli;
    }
}

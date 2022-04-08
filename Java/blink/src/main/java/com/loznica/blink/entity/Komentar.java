package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Komentar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Kupac kupacPorudzbine = new Kupac();
    @OneToOne
    private Restoran restoranKomentar = new Restoran();
    @Column
    private String tekstKomentara;
    private enum ocena {JEDAN, DVA, TRI, CETIRI, PET};

    public Restoran getRestoranKomentar() {
        return restoranKomentar;
    }

}

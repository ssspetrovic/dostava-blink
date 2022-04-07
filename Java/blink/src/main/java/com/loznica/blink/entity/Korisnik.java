package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String korisnickoIme;
    @Column
    private String lozinka;
    @Column
    private String ime;
    @Column
    private String prezime;
    @Column
    private String pol;
    @Column
    private String datumRodjenja;
    private enum uloga {ADMIN, MENADZER, DOSTAVLJAC, KUPAC};

    public String getKorisnickoIme() {
        return korisnickoIme;
    }
}

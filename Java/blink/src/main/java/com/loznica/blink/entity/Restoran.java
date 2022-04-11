package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restoran implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String naziv;
    public enum TipRestorana {KINESKI, ITALIJANSKI, MEKSICKI, DOMACI};

    @OneToMany
    private Set<Artikal> artikli = new HashSet<>();

    @OneToMany
    private Set<Komentar> komentari = new HashSet<>();

    public Restoran() { super(); }

    public Restoran(long id, String naziv, Set<Artikal> artikli, Set<Komentar> komentari) {
        this.id = id;
        this.naziv = naziv;
        this.artikli = artikli;
        this.komentari = komentari;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
        this.artikli = artikli;
    }

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }

    @Override
    public String toString() {
        return "Restoran{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", artikli=" + artikli +
                ", komentari=" + komentari +
                '}';
    }
}

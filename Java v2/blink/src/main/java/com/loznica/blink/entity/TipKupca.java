package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

enum Ime {
    HARDSTUCK,
    GOLD,
    DIAMOND
}

@Entity
public class TipKupca implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Ime ime;

    @Column
    private double popust;

    @Column
    private int trazeniBodovi;

    @OneToMany
    private Set<Kupac> kupci;

    public TipKupca() { super(); }

    public TipKupca(Long id, Ime ime, double popust, int trazeniBodovi, Set<Kupac> kupci) {
        this.id = id;
        this.ime = ime;
        this.popust = popust;
        this.trazeniBodovi = trazeniBodovi;
        this.kupci = kupci;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ime getIme() {
        return ime;
    }

    public void setIme(Ime ime) {
        this.ime = ime;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public int getTrazeniBodovi() {
        return trazeniBodovi;
    }

    public void setTrazeniBodovi(int trazeniBodovi) {
        this.trazeniBodovi = trazeniBodovi;
    }

    public Set<Kupac> getKupci() {
        return kupci;
    }

    public void setKupci(Set<Kupac> kupci) {
        this.kupci = kupci;
    }

    @Override
    public String toString() {
        return "TipKupca{" +
                "id=" + id +
                ", ime=" + ime +
                ", popust=" + popust +
                ", trazeniBodovi=" + trazeniBodovi +
                ", kupci=" + kupci +
                '}';
    }
}

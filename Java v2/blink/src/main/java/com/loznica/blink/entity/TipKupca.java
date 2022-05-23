package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class TipKupca extends Kupac implements Serializable {
    @Column
    private ImeKupca imeKupca;

    @Column
    private double popust;

    @Column
    private int trazeniBodovi;

    @OneToMany
    private Set<Kupac> kupci;

    public TipKupca() {
        super();
    }

    public TipKupca(ImeKupca imeKupca, double popust, int trazeniBodovi, Set<Kupac> kupci) {
        this.imeKupca = imeKupca;
        this.popust = popust;
        this.trazeniBodovi = trazeniBodovi;
        this.kupci = kupci;
    }

    public ImeKupca getImeKupca() {
        return imeKupca;
    }

    public void setImeKupca(ImeKupca imeKupca) {
        this.imeKupca = imeKupca;
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
                ", imeKupca=" + imeKupca +
                ", popust=" + popust +
                ", trazeniBodovi=" + trazeniBodovi +
                ", kupci=" + kupci +
                '}';
    }
}
package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TipKupca implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Kupac kupac;

    public enum Ime {HARDSTUCK, GOLD, DIAMOND};
    private Ime ime;
    private Float popust;
    private Integer trazeniBodovi;

    public TipKupca() {
        super();
    }

    public TipKupca(Long id, Kupac kupac, Ime ime, Float popust, Integer trazeniBodovi) {
        this.id = id;
        this.kupac = kupac;
        this.ime = ime;
        this.popust = popust;
        this.trazeniBodovi = trazeniBodovi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Ime getIme() {
        return ime;
    }

    public void setIme(Ime ime) {
        this.ime = ime;
    }

    public Float getPopust() {
        return popust;
    }

    public void setPopust(Float popust) {
        this.popust = popust;
    }

    public Integer getTrazeniBodovi() {
        return trazeniBodovi;
    }

    public void setTrazeniBodovi(Integer trazeniBodovi) {
        this.trazeniBodovi = trazeniBodovi;
    }

    @Override
    public String toString() {
        return "TipKupca{" +
                "id=" + id +
                ", kupac=" + kupac +
                ", ime=" + ime +
                ", popust=" + popust +
                ", trazeniBodovi=" + trazeniBodovi +
                '}';
    }
}

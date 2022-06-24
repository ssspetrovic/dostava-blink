package com.loznica.blink.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@DiscriminatorValue("Kupac")
public class Kupac extends Korisnik implements Serializable {
    @OneToMany(mappedBy = "kupac", fetch = FetchType.EAGER)
    private Set<Porudzbina> svePorudzbine;

    @Column
    private Double brojBodova;

    @ManyToOne
    private TipKupca tipKupca;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Komentar> komentari;

    public Kupac() {
        super();
        this.brojBodova = 0D;
    }

    public Kupac(Set<Porudzbina> svePorudzbine, Double brojBodova, TipKupca tipKupca, Set<Komentar> komentari) {
        this.svePorudzbine = svePorudzbine;
        this.brojBodova = brojBodova;
        this.tipKupca = tipKupca;
        this.komentari = komentari;
    }

    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja) {
        setKorisnickoIme(korisnickoIme);
        setLozinka(lozinka);
        setIme(ime);
        setPrezime(prezime);
        setPol(pol);
        setDatumRodjenja(datumRodjenja);
        brojBodova = 0D;
    }

    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja, Uloga uloga, Set<Porudzbina> svePorudzbine, Double brojBodova, TipKupca tipKupca, Set<Komentar> komentari) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, uloga);
        this.svePorudzbine = svePorudzbine;
        this.brojBodova = brojBodova;
        this.tipKupca = tipKupca;
        this.komentari = komentari;
    }

    public Set<Porudzbina> getSvePorudzbine() {
        return svePorudzbine;
    }

    public void setSvePorudzbine(Set<Porudzbina> svePorudzbine) {
        this.svePorudzbine = svePorudzbine;
    }

    public Double getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(Double brojBodova) {
        this.brojBodova += brojBodova;
    }

    public TipKupca getTipKupca() {
        return tipKupca;
    }

    public void setTipKupca(TipKupca tipKupca) {
        this.tipKupca = tipKupca;
    }

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }


    @Override
    public String toString() {
        return "Kupac{" +
                "svePorudzbine=" + svePorudzbine +
                ", brojBodova=" + brojBodova +
                ", tipKupca=" + tipKupca +
                ", komentari=" + komentari +
                '}';
    }
}
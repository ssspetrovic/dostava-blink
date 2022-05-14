package com.loznica.blink.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@DiscriminatorValue("Kupac")
public class Kupac extends Korisnik implements Serializable {
    @OneToMany(mappedBy = "kupac")
    @JsonIgnore
    private Set<Porudzbina> svePorudzbine;

    @Column
    private int brojBodova;

    @ManyToOne
    private TipKupca tipKupca;

    @OneToMany
    private Set<Komentar> komentari;

    public Kupac() { super(); }

    public Kupac(Set<Porudzbina> svePorudzbine, int brojBodova, TipKupca tipKupca, Set<Komentar> komentari) {
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
    }

    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja, Uloga uloga, Set<Porudzbina> svePorudzbine, int brojBodova, TipKupca tipKupca, Set<Komentar> komentari) {
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

    public int getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(int brojBodova) {
        this.brojBodova = brojBodova;
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

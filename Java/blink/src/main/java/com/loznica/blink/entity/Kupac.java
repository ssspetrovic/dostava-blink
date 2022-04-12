package com.loznica.blink.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kupac extends Korisnik implements Serializable {

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Porudzbina> svePorudzbine = new HashSet<>();

    private Integer brojBodova;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Komentar> komentari = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private TipKupca tipKupca;

    public Kupac() {
        super();
    }

    public Kupac(Long id, String korisnickoIme, String lozinka, String ime, String prezime, Pol pol, Date datumRodjenja, Uloga uloga, Set<Porudzbina> svePorudzbine, Integer brojBodova, Set<Komentar> komentari, TipKupca tipKupca) {
        super(id, korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, uloga);
        this.svePorudzbine = svePorudzbine;
        this.brojBodova = brojBodova;
        this.komentari = komentari;
        this.tipKupca = tipKupca;
    }

    public Set<Porudzbina> getSvePorudzbine() {
        return svePorudzbine;
    }

    public void setSvePorudzbine(Set<Porudzbina> svePorudzbine) {
        this.svePorudzbine = svePorudzbine;
    }

    public Integer getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(Integer brojBodova) {
        this.brojBodova = brojBodova;
    }

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }

    public TipKupca getTipKupca() {
        return tipKupca;
    }

    public void setTipKupca(TipKupca tipKupca) {
        this.tipKupca = tipKupca;
    }

    @Override
    public String toString() {
        return "Kupac{" +
                "uloga=" + uloga +
                ", svePorudzbine=" + svePorudzbine +
                ", brojBodova=" + brojBodova +
                ", komentari=" + komentari +
                ", tipKupca=" + tipKupca +
                '}';
    }
}

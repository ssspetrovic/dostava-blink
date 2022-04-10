package com.loznica.blink.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kupac extends Korisnik implements Serializable {

    @OneToMany
    private Set<Porudzbina> svePorudzbine = new HashSet<>();

    private Integer brojBodova;

    @OneToMany
    private Set<Komentar> komentari = new HashSet<>();

    @ManyToOne
    private TipKupca tipKupca;

    public Kupac() {
        super();
    }

    public Kupac(Set<Porudzbina> svePorudzbine, Integer brojBodova, TipKupca tipKupca) {
        this.svePorudzbine = svePorudzbine;
        this.brojBodova = brojBodova;
        this.tipKupca = tipKupca;
    }

    public Kupac(String korisnickoIme, Set<Porudzbina> svePorudzbine, Integer brojBodova, TipKupca tipKupca) {
        super(korisnickoIme);
        this.svePorudzbine = svePorudzbine;
        this.brojBodova = brojBodova;
        this.tipKupca = tipKupca;
    }

    public Kupac(Long id, String korisnickoIme, String lozinka, String ime, String pol, Date datumRodjenja, Uloga uloga, Set<Porudzbina> svePorudzbine, Integer brojBodova, TipKupca tipKupca) {
        super(id, korisnickoIme, lozinka, ime, pol, datumRodjenja, uloga);
        this.svePorudzbine = svePorudzbine;
        this.brojBodova = brojBodova;
        this.tipKupca = tipKupca;
    }

    public Set<Porudzbina> getSvePorudzbine() {
        return svePorudzbine;
    }

    public void setSvePorudzbine(Set<Porudzbina> svePorudzbine) {
        this.svePorudzbine = svePorudzbine;
    }

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }
}

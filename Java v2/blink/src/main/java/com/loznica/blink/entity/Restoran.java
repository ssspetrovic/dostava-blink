package com.loznica.blink.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Restoran implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String naziv;

    @Column
    private String tipRestorana;

    @OneToMany
    private Set<Artikal> artikli;

    @OneToMany
    private Set<Komentar> komentari;

    @OneToMany
    @JsonIgnore
    private Set<Porudzbina> porudzbine;

    @OneToOne(cascade =  CascadeType.ALL)
    private Lokacija lokacija;

    public Restoran() { super(); }

    public Restoran(String naziv, String tipRestorana, Lokacija lokacija) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.lokacija = lokacija;
    }

    public Restoran(Long id, String naziv, String tipRestorana, Set<Artikal> artikli, Set<Komentar> komentari, Set<Porudzbina> porudzbine, Lokacija lokacija) {
        this.id = id;
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.artikli = artikli;
        this.komentari = komentari;
        this.porudzbine = porudzbine;
        this.lokacija = lokacija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
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

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    @Override
    public String toString() {
        return "Restoran{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", tipRestorana='" + tipRestorana + '\'' +
                ", artikli=" + artikli +
                ", komentari=" + komentari +
                ", porudzbine=" + porudzbine +
                ", lokacija=" + lokacija +
                '}';
    }
}

package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Artikal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;
    private double cena;
    public enum Tip {JELO, PICE};
    public enum Kolicina {GRAMI, MILIMETRI};
    private Tip tip;
    private Kolicina kolicina;
    private String opis;

    @ManyToOne
    private Restoran restoran;

    @ManyToOne
    private Porudzbina porudzbina;

    public Artikal() { super(); }

    public Artikal(Long id, String naziv, double cena, Tip tip, Kolicina kolicina, String opis, Restoran restoran, Porudzbina porudzbina) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
        this.restoran = restoran;
        this.porudzbina = porudzbina;
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

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public Kolicina getKolicina() {
        return kolicina;
    }

    public void setKolicina(Kolicina kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    @Override
    public String toString() {
        return "Artikal{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", cena=" + cena +
                ", tip=" + tip +
                ", kolicina=" + kolicina +
                ", opis='" + opis + '\'' +
                ", restoran=" + restoran +
                ", porudzbina=" + porudzbina +
                '}';
    }
}

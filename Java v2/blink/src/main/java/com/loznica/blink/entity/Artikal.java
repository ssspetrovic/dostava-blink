package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Artikal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String naziv;

    @Column
    private Double cena;

    @Column
    private Tip tip;

    @Column
    private double kolicina;

    @Column
    private String opis;

    @ManyToOne
    private Restoran restoran;

    @OneToMany
    private Set<Porudzbina>  porudzbine;

    public Artikal() { super(); }

    public Artikal(Long id, String naziv, double cena, Tip tip, double kolicina, String opis, Restoran restoran, Set<Porudzbina> porudzbine) {
        this.id = id;
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
        this.restoran = restoran;
        this.porudzbine = porudzbine;
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

    public Double getCena() {
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

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
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

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
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
                ", porudzbine=" + porudzbine +
                '}';
    }
}

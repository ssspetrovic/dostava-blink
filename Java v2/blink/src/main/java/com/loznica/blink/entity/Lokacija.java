package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Lokacija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double geografskaDuzina;

    @Column
    private double GeografskaSirina;

    @Column
    private String adresa;

    @OneToOne
    private Restoran restoran;

    public Lokacija() { super(); }

    public Lokacija(Long id, double geografskaDuzina, double geografskaSirina, String adresa, Restoran restoran) {
        this.id = id;
        this.geografskaDuzina = geografskaDuzina;
        GeografskaSirina = geografskaSirina;
        this.adresa = adresa;
        this.restoran = restoran;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public void setGeografskaDuzina(double geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public double getGeografskaSirina() {
        return GeografskaSirina;
    }

    public void setGeografskaSirina(double geografskaSirina) {
        GeografskaSirina = geografskaSirina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    @Override
    public String toString() {
        return "Lokacija{" +
                "id=" + id +
                ", geografskaDuzina=" + geografskaDuzina +
                ", GeografskaSirina=" + GeografskaSirina +
                ", adresa='" + adresa + '\'' +
                ", restoran=" + restoran +
                '}';
    }
}

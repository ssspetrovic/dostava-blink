package com.loznica.blink.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lokacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String geografskaDuzina;
    private String geografskaSirina;
    private String lokacija;

    public Lokacija(){

    }

    public Lokacija(Long id, String geografskaDuzina, String geografskaSirina, String lokacija) {
        this.id = id;
        this.geografskaDuzina = geografskaDuzina;
        this.geografskaSirina = geografskaSirina;
        this.lokacija = lokacija;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public void setGeografskaDuzina(String geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public String getGeografskaSirina() {
        return geografskaSirina;
    }

    public void setGeografskaSirina(String geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    @Override
    public String toString() {
        return "Lokacija{" +
                "id=" + id +
                ", geografskaDuzina='" + geografskaDuzina + '\'' +
                ", geografskaSirina='" + geografskaSirina + '\'' +
                ", lokacija='" + lokacija + '\'' +
                '}';
    }
}

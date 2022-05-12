package com.loznica.blink.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Entity
public class Artikal implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column
    private String naziv;

    @Column
    private Double cena;

    @Column
    private Tip tip;

    @Column
    private Double kolicina;

    @Column
    private String opis;

    @ManyToOne
    private Restoran restoran;

    @OneToMany
    private Set<Porudzbina>  porudzbine;

    public Artikal() { super(); }

    public Artikal(UUID uuid, String naziv, double cena, Tip tip, double kolicina, String opis, Restoran restoran, Set<Porudzbina> porudzbine) {
        this.uuid = uuid;
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
        this.restoran = restoran;
        this.porudzbine = porudzbine;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public Double getKolicina() {
        return kolicina;
    }

    public void setKolicina(Double kolicina) {
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
                "uuid=" + uuid +
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

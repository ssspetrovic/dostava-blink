package com.loznica.blink.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;


@Entity
public class Porudzbina implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false)
    private UUID uuid;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PorudzbineArtikli> artikli;

    @ManyToOne
    private Restoran restoran;

    @Column
    private Date datumPorudzbine;

    @Column
    private double cena;

    @ManyToOne
    @JsonIgnore
    private Kupac kupac;

    @Column
    private Status status;

    public Porudzbina() {
        super();
    }

    public Porudzbina(Set<PorudzbineArtikli> artikli, Restoran restoran, Date datumPorudzbine, double cena, Kupac kupac, Status status) {
        this.artikli = artikli;
        this.restoran = restoran;
        this.datumPorudzbine = datumPorudzbine;
        this.cena = cena;
        this.kupac = kupac;
        this.status = status;
    }


    public Porudzbina(Set<PorudzbineArtikli> artikli, Restoran restoran, double cena) {
        this.artikli = artikli;
        this.restoran = restoran;
        this.datumPorudzbine = new Date();
        this.cena = cena;
    }

    public Porudzbina(Restoran restoran, Kupac kupac) {
        this.restoran = restoran;
        this.datumPorudzbine = new Date();
        this.kupac = kupac;
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Set<PorudzbineArtikli> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<PorudzbineArtikli> artikli) {
        this.artikli = artikli;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Date getDatumPorudzbine() {
        return datumPorudzbine;
    }

    public void setDatumPorudzbine(Date datumPorudzbine) {
        this.datumPorudzbine = datumPorudzbine;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Porudzbina{" +
                "uuid=" + uuid +
                ", artikli=" + artikli +
                ", datumPorudzbine=" + datumPorudzbine +
                ", cena=" + cena +
                ", status=" + status +
                '}';
    }
}
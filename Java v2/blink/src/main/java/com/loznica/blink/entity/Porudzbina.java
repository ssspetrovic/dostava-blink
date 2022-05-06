package com.loznica.blink.entity;

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

    @OneToMany
    private Set<Artikal> artikli;

    @ManyToOne
    private Restoran restoran;

    @Column
    private Date datumPorudzbine;

    @Column
    private double cena;

    @Column
    private String kupac;

    @Column
    private Status status;

    @ManyToOne
    private Dostavljac dostavljac;

    public Porudzbina() { super(); }

    public Porudzbina(UUID uuid, Set<Artikal> artikli, Restoran restoran, Date datumPorudzbine, double cena, String kupac, Status status, Dostavljac dostavljac) {
        this.uuid = uuid;
        this.artikli = artikli;
        this.restoran = restoran;
        this.datumPorudzbine = datumPorudzbine;
        this.cena = cena;
        this.kupac = kupac;
        this.status = status;
        this.dostavljac = dostavljac;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
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

    public String getKupac() {
        return kupac;
    }

    public void setKupac(String kupac) {
        this.kupac = kupac;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Dostavljac getDostavljac() {
        return dostavljac;
    }

    public void setDostavljac(Dostavljac dostavljac) {
        this.dostavljac = dostavljac;
    }

    @Override
    public String toString() {
        return "Porudzbina{" +
                "uuid=" + uuid +
                ", artikli=" + artikli +
                ", restoran=" + restoran +
                ", datumPorudzbine=" + datumPorudzbine +
                ", cena=" + cena +
                ", kupac='" + kupac + '\'' +
                ", status=" + status +
                ", dostavljac=" + dostavljac +
                '}';
    }
}

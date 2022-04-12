package com.loznica.blink.entity;

import org.aspectj.util.PartialOrder;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Porudzbina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Artikal> poruceniArtikli = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Dostavljac dostavljac;

    @OneToOne(cascade = CascadeType.ALL)
    private Restoran restoran;

    private Date vremePorudzbine;
    private double cena;
    private String korisnickoIme;
    public enum Status {OBRADA, U_PRIPREMI, CEKA_DOSTAVLJAC, U_TRANSPORTU, DOSTAVLJENA, OTKAZANA};
    @Enumerated(EnumType.STRING)
    private Status status;

    public Porudzbina() {
        super();
    }

    public Porudzbina(Long id, UUID uuid, Set<Artikal> poruceniArtikli, Dostavljac dostavljac, Restoran restoran, Date vremePorudzbine, double cena, String korisnickoIme, Status status) {
        this.id = id;
        this.uuid = uuid;
        this.poruceniArtikli = poruceniArtikli;
        this.dostavljac = dostavljac;
        this.restoran = restoran;
        this.vremePorudzbine = vremePorudzbine;
        this.cena = cena;
        this.korisnickoIme = korisnickoIme;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Set<Artikal> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(Set<Artikal> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }

    public Dostavljac getDostavljac() {
        return dostavljac;
    }

    public void setDostavljac(Dostavljac dostavljac) {
        this.dostavljac = dostavljac;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Date getVremePorudzbine() {
        return vremePorudzbine;
    }

    public void setVremePorudzbine(Date vremePorudzbine) {
        this.vremePorudzbine = vremePorudzbine;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
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
                "id=" + id +
                ", uuid=" + uuid +
                ", poruceniArtikli=" + poruceniArtikli +
                ", dostavljac=" + dostavljac +
                ", restoran=" + restoran +
                ", vremePorudzbine=" + vremePorudzbine +
                ", cena=" + cena +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", status=" + status +
                '}';
    }
}


package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Komentar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Kupac kupac;

    @ManyToOne
    private Restoran restoran;

    @Column
    private String tekstKomentara;

    @Column
    private int ocena;

    public Komentar() { super(); }

    public Komentar(Long id, Kupac kupac, Restoran restoran, String tekstKomentara, int ocena) {
        this.id = id;
        this.kupac = kupac;
        this.restoran = restoran;
        this.tekstKomentara = tekstKomentara;
        this.ocena = ocena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public String getTekstKomentara() {
        return tekstKomentara;
    }

    public void setTekstKomentara(String tekstKomentara) {
        this.tekstKomentara = tekstKomentara;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    @Override
    public String toString() {
        return "Komentar{" +
                "id=" + id +
                ", kupac=" + kupac +
                ", restoran=" + restoran +
                ", tekstKomentara='" + tekstKomentara + '\'' +
                ", ocena=" + ocena +
                '}';
    }
}

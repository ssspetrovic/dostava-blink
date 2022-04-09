package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Menadzer extends Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Restoran restoran;

    public Menadzer() {

    }

    public Menadzer(Restoran restoran) {
        super();
        this.restoran = restoran;
    }

    public Menadzer(Long id, String korisnickoIme, String lozinka, String ime, String pol, Date datumRodjenja, Korisnik.Uloga uloga, Restoran restoran) {
        super(id, korisnickoIme, lozinka, ime, pol, datumRodjenja, uloga);
        this.restoran = restoran;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    @Override
    public String toString() {
        return "Menadzer{" +
                "id=" + id +
                ", uloga=" + uloga +
                ", restoran=" + restoran +
                '}';
    }
}

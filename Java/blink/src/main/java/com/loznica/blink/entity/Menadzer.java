package com.loznica.blink.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Menadzer extends Korisnik implements Serializable {

    @OneToOne
    @JsonIgnore
    private Restoran restoran;

    public Menadzer() {
        super();
    }

    public Menadzer(Restoran restoran) {
        this.restoran = restoran;
    }

    public Menadzer(String korisnickoIme) {
        this.setKorisnickoIme(korisnickoIme);
    }

    public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja, Uloga uloga, Restoran restoran) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, uloga);
        this.restoran = restoran;
    }

    public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja, Uloga uloga) {
        super(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, uloga);
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
                "restoran=" + restoran +
                '}';
    }
}
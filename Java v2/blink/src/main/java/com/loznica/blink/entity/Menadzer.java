package com.loznica.blink.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Menadzer extends Korisnik implements Serializable {
    @OneToOne
    private Restoran restoran;

    public Menadzer() { super(); }

    public Menadzer(Restoran restoran) {
        this.restoran = restoran;
    }

    public Menadzer(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja, Uloga uloga, Restoran restoran) {
        super(id, korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, uloga);
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
                "restoran=" + restoran +
                '}';
    }
}

package com.loznica.blink.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable {
    @OneToMany
    private Set<Porudzbina> porudzbine;

    public Dostavljac() { super(); }

    public Dostavljac(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public Dostavljac(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja, Uloga uloga, Set<Porudzbina> porudzbine) {
        super(id, korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja, uloga);
        this.porudzbine = porudzbine;
    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    @Override
    public String toString() {
        return "Dostavljac{" +
                "porudzbine=" + porudzbine +
                '}';
    }
}

package com.loznica.blink.entity;

import javax.persistence.*;

@Entity
public class Komentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Kupac kupacPorudzbine;

    @ManyToOne
    private Restoran ocenaRestorana;

    private String tekstKomentara;

    public enum Ocena{JEDAN, DVA, TRI, CETIRI, PET};
    public Ocena ocena;

    public Komentar(){

    }

    public Komentar(Long id, Kupac kupacPorudzbine, Restoran ocenaRestorana, String tekstKomentara, Ocena ocena) {
        this.id = id;
        this.kupacPorudzbine = kupacPorudzbine;
        this.ocenaRestorana = ocenaRestorana;
        this.tekstKomentara = tekstKomentara;
        this.ocena = ocena;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kupac getKupacPorudzbine() {
        return kupacPorudzbine;
    }

    public void setKupacPorudzbine(Kupac kupacPorudzbine) {
        this.kupacPorudzbine = kupacPorudzbine;
    }

    public Restoran getOcenaRestorana() {
        return ocenaRestorana;
    }

    public void setOcenaRestorana(Restoran ocenaRestorana) {
        this.ocenaRestorana = ocenaRestorana;
    }

    public String getTekstKomentara() {
        return tekstKomentara;
    }

    public void setTekstKomentara(String tekstKomentara) {
        this.tekstKomentara = tekstKomentara;
    }

    public Ocena getOcena() {
        return ocena;
    }

    public void setOcena(Ocena ocena) {
        this.ocena = ocena;
    }


    @Override
    public String toString() {
        return "Komentar{" +
                "id=" + id +
                ", kupacPorudzbine=" + kupacPorudzbine +
                ", ocenaRestorana=" + ocenaRestorana +
                ", tekstKomentara='" + tekstKomentara + '\'' +
                ", ocena=" + ocena +
                '}';
    }
}

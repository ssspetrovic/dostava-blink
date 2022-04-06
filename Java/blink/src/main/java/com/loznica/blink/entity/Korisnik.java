package com.loznica.blink.entity;

public class Korisnik {
    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;
    private String pol;
    private String datumRodjenja;
    private enum uloga {ADMIN, MENADZER, DOSTAVLJAC, KUPAC};

    public String getKorisnickoIme() {
        return korisnickoIme;
    }
}

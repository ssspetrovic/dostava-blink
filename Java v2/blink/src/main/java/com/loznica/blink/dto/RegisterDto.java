package com.loznica.blink.dto;

import com.loznica.blink.entity.Kupac;

import java.util.Date;

public class RegisterDto {

    private String korisnickoIme;

    private String lozinka;

    private String ime;

    private String prezime;

    private String pol;

    private Date datumRodjenja;

    public RegisterDto() {
    }

    public RegisterDto(String korisnickoIme, String lozinka, String ime, String prezime, String pol, Date datumRodjenja) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Kupac ToKupac() {
        return new Kupac(korisnickoIme, lozinka, ime, prezime, pol, datumRodjenja);
    }
}
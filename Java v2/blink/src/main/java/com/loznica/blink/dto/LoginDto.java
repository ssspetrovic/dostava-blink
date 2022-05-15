package com.loznica.blink.dto;

public class LoginDto {
    private String korisnickoIme;

    private String lozinka;

    public LoginDto() {
        super();
    }

    public LoginDto(String korisnickoIme, String lozinka) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String username) {
        this.korisnickoIme = username;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String password) {
        this.lozinka = password;
    }
}

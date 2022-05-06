package com.loznica.blink.dto;

public class LoginDto {
    private String korisnickoIme;

    private String lozinka;

    public LoginDto() {
    }

    public LoginDto(String korisnickoIme, String lozinka) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public String getUsername() {
        return korisnickoIme;
    }

    public void setUsername(String username) {
        this.korisnickoIme = username;
    }

    public String getPassword() {
        return lozinka;
    }

    public void setPassword(String password) {
        this.lozinka = password;
    }
}

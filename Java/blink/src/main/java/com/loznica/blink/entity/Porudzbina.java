package com.loznica.blink.entity;

public class Porudzbina {
    private long uuid;
    private Artikal poruceniArtikli = new Artikal();
    private Restoran mestoPorudzbine = new Restoran();
    private String datumPorudzbine;
    private float cena;
    private String korisnickoIme;
    private enum status {OBRADA, PRIPREMA, CEKANJE, TRANSPORT, DOSTAVLJENO, OTKAZANO}
}

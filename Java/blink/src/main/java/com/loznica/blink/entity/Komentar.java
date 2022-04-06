package com.loznica.blink.entity;

public class Komentar {
    private Kupac kupacPorudzbine = new Kupac();
    private Restoran restoranKomentar = new Restoran();
    private String tekstKomentara;
    private enum ocena {JEDAN, DVA, TRI, CETIRI, PET};
}

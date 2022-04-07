package com.loznica.blink.entity;

import java.util.ArrayList;

public class Restoran {
    private String naziv;
    private ArrayList<String> tipRestorana = new ArrayList<>();
    private Artikal ponude = new Artikal();
    private Lokacija adresa = new Lokacija();
}

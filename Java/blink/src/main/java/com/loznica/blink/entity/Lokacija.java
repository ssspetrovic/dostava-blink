package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Lokacija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private float geografskaDuzina;
    @Column
    private float geografskaSirina;
    @Column
    private String adresa;
}

package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Artikal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String naziv;
    @Column
    private float cena;
    private enum tip {JELO, PICE};
    private enum kolicina {G, ML};
    @Column
    private String opis;
}

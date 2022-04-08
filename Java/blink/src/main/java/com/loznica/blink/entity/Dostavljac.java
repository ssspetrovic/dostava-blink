package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Dostavljac extends Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Porudzbina porudzbineDostavljac = new Porudzbina();
    @Column

    public Porudzbina getPorudzbineDostavljac() {
        return porudzbineDostavljac;
    }
}

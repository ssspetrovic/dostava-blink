package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "porudzbine_dostavljac_uuid", nullable = false)

    private Porudzbina porudzbineDostavljac = new Porudzbina();
    @Column

    public Porudzbina getPorudzbineDostavljac() {
        return porudzbineDostavljac;
    }
}

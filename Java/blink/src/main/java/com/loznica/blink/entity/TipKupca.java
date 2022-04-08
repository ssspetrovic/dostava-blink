package com.loznica.blink.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class TipKupca implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private enum ime {ISKRA, GROM, TESLA};
    @Column
    private float popust;
    @Column
    private int trazeniBodovi;
}

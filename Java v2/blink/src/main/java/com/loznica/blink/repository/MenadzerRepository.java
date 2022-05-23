package com.loznica.blink.repository;

import com.loznica.blink.entity.Menadzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenadzerRepository extends JpaRepository<Menadzer, Long> {
    Menadzer getByKorisnickoIme(String korisnickoIme);
}
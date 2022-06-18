package com.loznica.blink.repository;

import com.loznica.blink.entity.Artikal;
import com.loznica.blink.entity.PorudzbineArtikli;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PorudzbineArtikliRepository extends JpaRepository<PorudzbineArtikli, Long> {
    PorudzbineArtikli getByArtikal(Artikal artikal);
}
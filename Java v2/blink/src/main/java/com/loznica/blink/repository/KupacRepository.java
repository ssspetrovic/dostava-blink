package com.loznica.blink.repository;

import com.loznica.blink.entity.Kupac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KupacRepository extends JpaRepository<Kupac, Long> {
    Kupac findByKorisnickoIme(@Param("korisnickoIme") String korisnickoIme);
}

package com.loznica.blink.repository;

import com.loznica.blink.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Korisnik getByKorisnickoIme(String korisnickoIme);
}

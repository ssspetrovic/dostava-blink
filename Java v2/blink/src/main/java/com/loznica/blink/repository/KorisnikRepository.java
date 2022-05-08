package com.loznica.blink.repository;

import com.loznica.blink.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Korisnik getByKorisnickoIme(String korisnickoIme);
    Optional<Korisnik> findByKorisnickoIme(@Param("korisnickoIme") String korisnickoIme);

    Korisnik getById(@Param("id") Long id);

}

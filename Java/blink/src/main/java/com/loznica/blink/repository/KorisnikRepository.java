package com.loznica.blink.repository;

import com.loznica.blink.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikRepository extends JpaRepository<Korisnik, String> {
}

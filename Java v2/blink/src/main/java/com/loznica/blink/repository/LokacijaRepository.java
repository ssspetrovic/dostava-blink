package com.loznica.blink.repository;

import com.loznica.blink.entity.Lokacija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LokacijaRepository extends JpaRepository<Lokacija, Long> {
}

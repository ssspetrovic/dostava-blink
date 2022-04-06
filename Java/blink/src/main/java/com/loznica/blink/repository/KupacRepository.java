package com.loznica.blink.repository;

import com.loznica.blink.entity.Kupac;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KupacRepository extends JpaRepository<Kupac, Long> {
}

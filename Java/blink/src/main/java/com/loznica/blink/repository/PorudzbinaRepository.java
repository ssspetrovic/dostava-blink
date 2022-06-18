package com.loznica.blink.repository;

import com.loznica.blink.entity.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, UUID> {
    Porudzbina getByUuid(UUID uuid);
}
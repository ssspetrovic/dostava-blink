package com.loznica.blink.repository;

import com.loznica.blink.entity.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtikalRepository extends JpaRepository<Artikal, UUID> {
}

package com.loznica.blink.repository;

import com.loznica.blink.entity.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ArtikalRepository extends JpaRepository<Artikal, UUID> {
    Artikal findById(Long id);
}
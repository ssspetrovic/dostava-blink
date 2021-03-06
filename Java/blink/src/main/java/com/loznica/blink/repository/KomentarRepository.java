package com.loznica.blink.repository;

import com.loznica.blink.entity.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KomentarRepository extends JpaRepository<Komentar, Long> {
}
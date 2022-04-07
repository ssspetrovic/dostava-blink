package com.loznica.blink.repository;

import com.loznica.blink.entity.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KomentarRepository extends JpaRepository <Komentar, Long> {
}

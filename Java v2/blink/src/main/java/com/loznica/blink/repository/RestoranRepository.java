package com.loznica.blink.repository;

import com.loznica.blink.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestoranRepository extends JpaRepository<Restoran, Long> {

    Optional<Restoran> findByNaziv(@Param("naziv") String naziv);

    Restoran getByNaziv(String naziv);

    Restoran getById(Long id);

}

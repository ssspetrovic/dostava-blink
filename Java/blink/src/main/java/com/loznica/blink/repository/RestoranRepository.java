package com.loznica.blink.repository;
import com.loznica.blink.entity.Lokacija;
import com.loznica.blink.entity.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestoranRepository extends JpaRepository<Restoran, Long> {

    Restoran getByTipRestorana(@Param("tipRestorana") String tipRestorana);

    Restoran getByNaziv(String naziv);

    Restoran getById(Long id);

    Restoran getByLokacija(Lokacija lokacija);

}
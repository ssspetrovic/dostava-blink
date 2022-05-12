package com.loznica.blink.controller;

import com.loznica.blink.dto.RestoranDto;
import com.loznica.blink.entity.*;
import com.loznica.blink.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranRepository restoranRepository;

    @GetMapping("/api/restorani/lista")
    public ResponseEntity Get() {
        List<Restoran> restoranList = restoranRepository.findAll();
        List<RestoranDto> restoranInfo = new ArrayList<>();

        for (Restoran tmp : restoranList) {
            RestoranDto currentInfo = new RestoranDto();
            currentInfo.setRestoran(tmp);
            currentInfo.setArtikli(tmp.getArtikli());
            restoranInfo.add(currentInfo);
        }

        return new ResponseEntity(restoranInfo, HttpStatus.OK);
    }

    @GetMapping("/api/restorani/info/{id}")
    public Restoran ispisiRestoran(@PathVariable(name = "id") Long id) {
        List<Restoran> restoranList = restoranRepository.findAll();

        for(Restoran r : restoranList)
            if(Objects.equals(id, r.getId()))
                return r;

        return null;
    }

}

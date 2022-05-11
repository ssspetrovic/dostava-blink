package com.loznica.blink.controller;

import com.loznica.blink.dto.RestoranDto;
import com.loznica.blink.entity.Restoran;
import com.loznica.blink.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

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
}

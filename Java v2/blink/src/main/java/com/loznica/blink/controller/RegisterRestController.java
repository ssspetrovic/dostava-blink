package com.loznica.blink.controller;

import com.loznica.blink.dto.RegisterDto;
import com.loznica.blink.entity.Kupac;
import com.loznica.blink.entity.Uloga;
import com.loznica.blink.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class RegisterRestController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/api/register")
    public ResponseEntity Register(@RequestBody RegisterDto registerDto) {

        HashMap<String, String> greska = Validate(registerDto);

        if(!greska.isEmpty())
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        Kupac k = registerDto.ToKupac();

        try {
            registerService.Registracija(k, Uloga.KUPAC);
        } catch (Exception e) {
            greska.put("korisnickoIme", e.getMessage());
        }

        if(!greska.isEmpty())
            return new ResponseEntity(greska, HttpStatus.BAD_REQUEST);

        return new ResponseEntity("USPESNA REGISTRACIJA, HVALA VAM", HttpStatus.OK);

    }

    private HashMap<String, String> Validate(RegisterDto registerDto) {
        HashMap<String, String> greska = new HashMap<>();

        if(registerDto.getKorisnickoIme() == null || registerDto.getKorisnickoIme().isEmpty())
            greska.put("korisnickoIme", "OBAVEZNO");
        if(registerDto.getLozinka() == null || registerDto.getLozinka().isEmpty())
            greska.put("lozinka", "OBAVEZNA");
        if(registerDto.getIme() == null || registerDto.getIme().isEmpty())
            greska.put("ime", "OBAVEZNO");
        if(registerDto.getPrezime() == null || registerDto.getPrezime().isEmpty())
            greska.put("prezime", "OBAVEZNO");
        if(registerDto.getPol() == null || registerDto.getPol().isEmpty())
            greska.put("pol", "OBAVEZNO");
        if(registerDto.getDatumRodjenja() == null)
            greska.put("datumRodjenja", "OBAVEZNO");

        return greska;

    }

}

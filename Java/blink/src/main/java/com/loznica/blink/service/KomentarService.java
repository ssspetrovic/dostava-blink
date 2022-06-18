package com.loznica.blink.service;

import com.loznica.blink.entity.Komentar;
import com.loznica.blink.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KomentarService {

    @Autowired
    KomentarRepository komentarRepository;

    public void sacuvajKomentar(Komentar komentar) {
        komentarRepository.save(komentar);
    }

}

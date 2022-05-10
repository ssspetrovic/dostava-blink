package com.loznica.blink.service;

import com.loznica.blink.entity.Uloga;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionService {

    public boolean uloga(HttpSession session, Uloga uloga) {

        Object ulogaSession = session.getAttribute("uloga");

        if(ulogaSession == null)
            return false;
        return ulogaSession.equals(uloga);

    }

    public String getKorisnickoIme(HttpSession session) {

        Object korisnickoIme = session.getAttribute("korisnickoIme");
        if(korisnickoIme == null)
            return "";
        if(korisnickoIme.toString().isEmpty())
            return "";
        return korisnickoIme.toString();

    }

    public Uloga getUloga(HttpSession session) {
        Object uloga = session.getAttribute("uloga");
        if(uloga == null)
            return null;
        if(uloga.toString().isEmpty())
            return null;
        return (Uloga) uloga;
    }

    public boolean validate(HttpSession session) {
        if(session == null)
            return false;

        String korisnickoIme = getKorisnickoIme(session);
        Uloga uloga = getUloga(session);

        if(korisnickoIme == null || korisnickoIme.isEmpty())
            return false;
        return uloga != null && !uloga.toString().isEmpty();

    }

}

package com.loznica.blink;

import com.loznica.blink.entity.Korisnik;
import com.loznica.blink.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BlinkApplication implements CommandLineRunner {

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Override
	public void run(String... args) {

		Korisnik korisnik = new Korisnik();
		korisnik.setKorisnickoIme("Rippah");
		korisnik.setIme("Nikola");
		korisnik.setPrezime("Pantic");
		korisnik.setId(12345L);
		korisnik.setLozinka("admin");
		korisnik.setPol("M");
		korisnik.setDatumRodjenja("27.12.2001.");

		this.korisnikRepository.save(korisnik);

		List<Korisnik> korisnici = this.korisnikRepository.findAll();

		for (Korisnik k : korisnici)
			System.out.println(k);
	}

	public static void main(String[] args) {SpringApplication.run(BlinkApplication.class, args);}

}

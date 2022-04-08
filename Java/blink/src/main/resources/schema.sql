DROP TABLE IF EXISTS TBL_KORISNICI;

CREATE TABLE TBL_KORISNICI (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               korisnickoIme VARCHAR(250) NOT NULL,
                               lozinka VARCHAR(250) NOT NULL,
                               email VARCHAR(250) DEFAULT NULL
);
DROP TABLE IF EXISTS TBL_KORISNICI;

CREATE TABLE TBL_KORISNICI (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               korisnickoIme VARCHAR(250) NOT NULL,
                               lozinka VARCHAR(250) NOT NULL,
                               email VARCHAR(250) DEFAULT NULL
);

INSERT INTO TBL_KORISNICI (korisnickoIme, lozinka, email) VALUES
    ('Rippah', 'rippah', 'd@gmail.com'),
    ('Admin', 'admin', 'g@gmail.com'),
    ('Pass', 'pass', 'p@gmail.com');

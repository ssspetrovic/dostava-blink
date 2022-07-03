import axios from 'axios'

const KORISNIK_API_BASE_URL = 'http://localhost:8080/api/korisnici'

class KorisnikService {
    vratiKorisnike() {
        return axios.get(KORISNIK_API_BASE_URL);
    }
}

export default new KorisnikService()

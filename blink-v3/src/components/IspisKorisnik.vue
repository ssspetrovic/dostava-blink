<template>
  <section>
    <div class="container w-0 vh-100">
      <div class="d-flex justify-content-center table-responsive-sm">
        <table class="table" id="tabela">
          <thead class="table-dark">
            <tr>
              <th colspan="2" class="text-center">Informacije o korisniku</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th>Ime:</th>
              <td>{{ kI }}</td>
            </tr>
            <tr>
              <th>Prezime:</th>
              <td>{{ kP }}</td>
            </tr>
            <tr>
              <th>Korisničko ime:</th>
              <td>{{ kIme }}</td>
            </tr>
            <tr>
              <th>Uloga:</th>
              <td>{{ kU }}</td>
            </tr>
            <tr>
              <th>Restoran</th>
              <!-- <td>{{ kR.naziv }}</td> -->
            </tr>
          </tbody>
        </table>
      </div>
      <div class="d-flex justify-content-center mt-4">
        <router-link to="/edit-profile" class="btn btn-lg btn-dark">
          Uredi profil
        </router-link>
      </div>
    </div>
  </section>
</template>

<style>
#tabela {
  width: 50%;
}
</style>

<script>
import axios from "axios";

export default {
  name: "IspisKorisnik",
  data: function () {
    return {
      korisnik: {
        komentari: {},
        restoran: {},
      },
    };
  },
  computed: {
    kIme() {
      return this.korisnik.korisnickoIme;
    },
    kI() {
      return this.korisnik.ime;
    },
    kP() {
      return this.korisnik.prezime;
    },
    kU() {
      return this.korisnik.uloga;
    },
    komentari() {
      return this.korisnik.komentari;
    },
    kR() {
      return this.korisnik.restoran;
    },
  },
  created() {
    this.$watch(
      () => this.$route.params,
      () => {
        // react to route changes...
        this.fetchKorisnik();
      }
    );
  },
  methods: {
    fetchKorisnik() {
      axios
        .get(
          `http://localhost:8080/api/korisnik/` +
            this.$route.params.id +
            `/?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`
        )
        .then((res) => {
          this.korisnik = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    logout() {
      axios
        .delete(
          `http://localhost:8080/api/admin/obrisi-korisnika/` +
            this.$route.params.id +
            `?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`
        )
        .then((res) => {
          console.log(res);
          this.$router.push("/prijava");
        })
        .catch((err) => {
          console.log(err);
          alert("Something went wrong!");
        });
    },
  },
  mounted: function () {
    this.fetchKorisnik();
    /*
                fetch('http://localhost:8083/api/korisnici/ispis',
                    {
                        credentials: 'include'
                    })
                    .then(response => response.json())
                    .then(data => {
                        console.log("Success:", data);
                        this.korisnik = data
                    })
                    .catch((error) => {
                        console.error("Error:", error)
                    });
            }
        */
  },
};
</script>

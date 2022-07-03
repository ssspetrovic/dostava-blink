<template>
  <section>
    <div class="container vh-100">
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
              <th>Restoran:</th>
              <td>
                <router-link
                  :to="{ path: '/restoran/' + kRid }"
                  class="link-primary"
                >
                  {{ kRnaziv }}</router-link
                >
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <a
        v-on:click="uPripremi"
        href="#"
        class="nav-link"
        v-if="this.korisnik.restoran != null"
        ><h2>STATUS: U PRIPREMI</h2></a
      >
      <a
        v-on:click="cekaDostavljaca"
        href="#"
        class="nav-link"
        v-if="this.korisnik.restoran != null"
        ><h2>STATUS: CEKA DOSTAVLJACA</h2></a
      >
      <a
        v-on:click="uTransportu"
        href="#"
        class="nav-link"
        v-if="this.korisnik.porudzbine != null"
        ><h2>STATUS: U TRANSPORTU</h2></a
      >
      <a
        v-on:click="dostavljeno"
        href="#"
        class="nav-link"
        v-if="this.korisnik.porudzbine != null"
        ><h2>STATUS: DOSTAVLJENO</h2></a
      >
      <router-link
        to="/dostavljac-korpa"
        class="movCR"
        v-if="this.korisnik.porudzbine != null"
        ><h2>Porudzbine Restorana</h2></router-link
      >
      <router-link
        to="/all-orders"
        class="movCR"
        v-if="this.korisnik.porudzbine != null"
        ><h2>Sve Porudzbine Dostavljaca</h2></router-link
      >
      <div class="table-responsive-sm col-md d-flex justify-content-center mt-5">
        <table class="table" id="tabela">
          <thead class="table-dark">
            <tr>
              <th class="text-center">Komentari</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="clan in komentari" :key="clan.id">
              <td>{{ clan.tekstKomentara }} {{ clan.ocena }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="d-flex justify-content-center mt-4">
        <router-link to="/edit-profile" class="btn btn-lg btn-dark m-3">
          Uredi nalog
        </router-link>
        <a v-on:click="logout" href="#" class="btn btn-warning btn-lg m-3"
          >Ukloni nalog</a
        >
      </div>
    </div>
  </section>
</template>

<style scoped>
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
        porudzbine: [{}],
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
      if (this.korisnik.restoran != null) return this.korisnik.restoran;
      return null;
    },
    kRid() {
      if (this.korisnik.restoran != null) return this.korisnik.restoran.id;
      return null;
    },
    kRnaziv() {
      if (this.korisnik.restoran != null) return this.korisnik.restoran.naziv;
      return null;
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
          console.log("Nalog uspešno obrisan.");
          this.$router.push("/");
        })
        .catch((err) => {
          console.log(err);
          alert("Something went wrong!");
        });
    },
    uPripremi() {
      axios
        .get(
          `http://localhost:8080/api/porudzbine/priprema/1?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`
        )
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
          alert("Something went wrong!");
        });
    },
    cekaDostavljaca() {
      axios
        .get(
          `http://localhost:8080/api/porudzbine/ceka/1?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`
        )
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
          alert("Something went wrong!");
        });
    },
    uTransportu() {
      axios
        .get(
          `http://localhost:8080/api/porudzbine/transport/1?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`
        )
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
          alert("Something went wrong!");
        });
    },
    dostavljeno() {
      axios
        .get(
          `http://localhost:8080/api/porudzbine/dostavljeno/1?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`
        )
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
          alert("Something went wrong!");
        });
    },
  },
  mounted: function () {
    this.fetchKorisnik();
  },
};
</script>

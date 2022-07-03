<template>
  <section>
    <div class="container vh-100">
      <div class="display-4 text-center mb-5">{{ naziv }}</div>
      <div class="row">
        <div class="table-responsive-sm col-md">
          <table class="table" id="tabela">
            <thead class="table-dark">
              <tr>
                <th colspan="2" class="text-center">Informacije o restoranu</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th>Tip:</th>
                <td>{{ tip }}</td>
              </tr>
              <tr>
                <th>Lokacija:</th>
                <td>{{ lokacija }}</td>
              </tr>
              <tr>
                <th>Ocena:</th>
                <td>{{ ocena }}</td>
              </tr>
              <tr>
                <th>Menadžer:</th>
                <td>
                  <router-link :to="{ path: '/korisnik/' + menadzerIme.id }">
                    {{ menadzerIme.korisnickoIme }}
                  </router-link>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="table-responsive-sm col-md">
          <table class="table" id="tabela">
            <thead class="table-dark">
              <tr>
                <th class="text-center">Artikli</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="clan in artikli" :key="clan.id">
                <td>
                  <router-link
                    :to="{ path: '/artikal/' + clan.id }"
                    class="link-primary"
                  >
                    {{ clan.naziv }}
                  </router-link>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="table-responsive-sm col-md">
          <table class="table" id="tabela">
            <thead class="table-dark">
              <tr>
                <th class="text-center">Komentari</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="clan in komentari" :key="clan.id">
                <td>
                  {{ clan.tekstKomentara }}
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="d-flex justify-content-center mt-4">
        <router-link to="/create-article" class="btn btn-lg btn-dark m-3">
          Kreiraj artikal
        </router-link>
        <router-link to = "/restoran-korpa" class = "btn btn-lg btn-dark m-3">Porudžbine restorana</router-link>
        <a v-on:click = "deleteRes" href = "#" class="btn btn-dark btn-lg m-3">Obriši restoran</a>
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";

export default {
  name: "RestoraN",
  data: function () {
    return {
      restoran: {
        lokacija: {},
        artikli: {},
        menadzer: {},
        komentari: {},
      },
    };
  },
  computed: {
    naziv() {
      return this.restoran.naziv;
    },
    tip() {
      return this.restoran.tipRestorana;
    },
    lokacija() {
      return this.restoran.lokacija.adresa;
    },
    artikli() {
      if (this.restoran.artikli != null) return this.restoran.artikli;
      return null;
    },
    menadzerIme() {
      if (this.restoran.menadzer != null) return this.restoran.menadzer;
      return "";
    },
    ocena() {
      return this.restoran.prosek;
    },
    komentari() {
      if (this.restoran.komentari != null) return this.restoran.komentari;
      return null;
    },
  },
  created() {
    this.$watch(
      () => this.$route.params,
      () => {
        // react to route changes...
        this.fetchRestoran();
      }
    );
  },
  methods: {
    fetchRestoran() {
      axios
        .get(
          "http://localhost:8080/api/restorani/info/" + this.$route.params.id
        )
        .then((res) => {
          this.restoran = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteRes() {
      axios
        .delete(
          `http://localhost:8080/api/admin/brisiRestoran/` +
            `${this.restoran.naziv}` +
            `?korisnickoIme=pale`
        )
        .then((res) => {
          console.log(res);
          this.$router.push("/restorani");
        })
        .catch((err) => {
          console.log(err);
          alert("Something went wrong!");
        });
    },
  },
  mounted: function () {
    this.fetchRestoran();
  },
};
</script>

<style>
#w40 {
  width: 40%;
}

#w50 {
  width: 50%;
}
#w60 {
  width: 60%;
}
#w70 {
  width: 70%;
}
</style>

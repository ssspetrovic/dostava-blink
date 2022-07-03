<template>
  <div class="container vh-100">
    <div class="text-center display-4 m-5">Lista Korisnika</div>
    <div class="table-responsive">
      <table class="table">
        <thead class="table-dark">
          <tr>
            <th>ID:</th>
            <th>Korisnicko ime:</th>
            <th>Ime:</th>
            <th>Prezime:</th>
            <th>Pol:</th>
            <th>Datum rođenja:</th>
            <th>Uloga:</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="korisnik in korisnici" v-bind:key="korisnik.id">
            <th>{{ korisnik.id }}</th>
            <td>
              <router-link
                :to="{ path: '/korisnik/' + korisnik.id }"
                class="link-primary"
                >{{ korisnik.korisnickoIme }}</router-link
              >
            </td>
            <td>{{ korisnik.ime }}</td>
            <td>{{ korisnik.prezime }}</td>
            <td>{{ korisnik.pol }}</td>
            <td>{{ korisnik.datumRodjenja }}</td>
            <td>{{ korisnik.uloga }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AllManagers",
  data: function () {
    return {
      korisnici: {},
    };
  },
  mounted: function () {
    axios
      .get(
        `http://localhost:8080/api/menadzer/ispis?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`
      )
      .then((res) => {
        this.korisnici = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

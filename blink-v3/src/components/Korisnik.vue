<template>
  <section>
    <div class="container vh-100">
      <h1 class="text-center m-5 lead display-4">Lista Korisnika</h1>
      <div class="table-responsive-sm">
        <table class="table m-3">
          <thead class="table-dark">
            <tr>
              <th scope="col">ID:</th>
              <th scope="col">Korisničko ime:</th>
              <th scope="col">Ime:</th>
              <th scope="col">Prezime:</th>
              <th scope="col">Pol:</th>
              <th scope="col">Datum rođenja:</th>
              <th scope="col">Uloga:</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="clan in korisnik" v-bind:key="clan.id">
              <th scope="row">{{ clan.id }}</th>
              <td>
                <router-link
                  :to="{ path: '/korisnik/' + clan.id }"
                  class="link-primary"
                  >{{ clan.korisnickoIme }}</router-link
                >
              </td>
              <td>{{ clan.ime }}</td>
              <td>{{ clan.prezime }}</td>
              <td>{{ clan.pol }}</td>
              <td>{{ clan.datumRodjenja }}</td>
              <td>{{ clan.uloga }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="d-flex justify-content-center m-5 mb-0">
        <div class="display-6">Administratorske opcije</div>
      </div>
      <div class="d-flex justify-content-center mt-0">
        <router-link to="/create-manager" class="btn btn-dark btn-lg m-4"
          >Kreiraj menadžera</router-link
        >
        <router-link to="/create-dostavljac" class="btn btn-dark btn-lg m-4"
          >Kreiraj dostavljača</router-link
        >
        <router-link to="/all-managers" class="btn btn-dark btn-lg m-4"
          >Svi menadžeri
        </router-link>
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";

export default {
  name: "SviKorisnici",
  data: function () {
    return {
      korisnik: [{}],
    };
  },
  mounted: function () {
    axios
      .get("http://localhost:8080/api/korisnici")
      .then((res) => {
        this.korisnik = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

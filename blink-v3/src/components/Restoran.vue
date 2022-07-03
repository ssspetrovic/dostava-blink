<template>
  <section>
    <div class="container-fluid vh-100">
      <div class="">
        <h2 style="text-align: center">Restoran: {{ naziv }}</h2>
        <h2 style="text-align: center">Tip: {{ tip }}</h2>
        <h2 style="text-align: center">Lokacija: {{ lokacija }}</h2>
        <router-link :to="{ path: '/korisnik/' + menadzerIme.id }">
          <h2 class="text-align:center">
            Menadzer: {{ menadzerIme.korisnickoIme }}
          </h2></router-link
        >
        <h2 style="text-align: center">Ocena Restorana: {{ ocena }}</h2>
      </div>

      <div class="">
        <tr v-for="clan in artikli" :key="clan.id">
          <router-link :to="{ path: '/artikal/' + clan.id }"
            ><td>
              <h2>{{ clan.naziv }}</h2>
            </td></router-link
          >
        </tr>
        <router-link to="/create-article" class="movCR"
          ><h2>Kreiraj novi?</h2></router-link
        >
      </div>
      <div class="">
        <tr v-for="clan in komentari" :key="clan.id">
          <td>
            <h2>{{ clan.tekstKomentara }}</h2>
          </td>
        </tr>
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
  },
  mounted: function () {
    this.fetchRestoran();
  },
};
</script>

<style>
</style>

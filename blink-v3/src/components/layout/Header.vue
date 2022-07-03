<template>
  <nav
    class="
      navbar navbar-expand-lg
      py-3
      sticky-top
      navbar-light
      shadow
      p-3
      mb-5
      bg-white
      rounded
    "
  >
    <div class="container">
      <router-link to="/" class="navbar-brand">
        <img src="../../assets/blink-logo.svg" alt="" width="100" />
      </router-link>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navmenu"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navmenu">
        <form class="d-flex mt-3 mb-1" role="search">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Pretraži"
          />
          <router-link
            to="/search-bar"
            class="btn btn-outline-primary"
            type="submit"
          >
            Pretraga
          </router-link>
        </form>
        <ul class="navbar-nav ms-auto" id="navbar-lista">
          <li class="nav-item">
            <router-link to="/sign-in" class="nav-link">Prijavi se</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/sign-up" class="nav-link"
              >Registruj se</router-link
            >
          </li>
          <li class="nav-item">
            <router-link to="/korpa" class="nav-link">Korpa</router-link>
          </li>
          <li class="nav-item" id="logout">
            <a v-on:click="logout" href="#" class="nav-link">Odjavi se</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import axios from "axios";

export default {
  name: "HeadeR",
  data: function () {
    return {
      korisnik: {},
    };
  },

  mounted: function () {
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
  methods: {
    logout() {
      axios
        .post(
          `http://localhost:8080/api/logout?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`
        )
        .then((res) => {
          console.log(res);
          alert("Uspešno odjavljivanje!");
          this.$router.push("/");
        })
        .catch((err) => {
          console.log(err);
          alert("Something went wrong!");
        });
    },
  },
};
// window.onload = function () {
//   if (this.korisnik.auth == false) {
//     document.getElementById("logout").setAttribute("style=display: block;")
//   }

// }
</script>

<style>
/* #logout {
  display: none;
} */
</style>
import { createApp } from "vue";
import { createStore } from "vuex";
import App from "./App.vue";
import router from "./router";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-icons/font/bootstrap-icons.css";

const app = createApp(App);

const store = createStore({
  state() {
    return {
      korisnik: { korisnickoIme: "", lozinka: "" },
    };
  },
  getters: {
    korisnik(state) {
      // state variable contains our state data
      return state.korisnik;
    },
  },
  mutations: {
    addKorisnik(state, dodajKorisnika) {
      if (
        typeof dodajKorisnika.korisnickoIme == "string" &&
        typeof dodajKorisnika.lozinka == "string"
      ) {
        state.korisnik = {
          korisnickoIme: dodajKorisnika.korisnickoIme,
          lozinka: dodajKorisnika.lozinka,
        };
      }
    },
  },
});

app.use(router).use(store).mount("#app");

import "bootstrap/dist/js/bootstrap.js";

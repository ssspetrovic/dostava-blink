<template>
  <section>
    <div class="container vh-100" id="w30">
      <form>
        <!-- <div class="d-inline row text-center my-2 mt-0">
          <img id="logo-prijava" src="../assets/blink-logo.svg" alt="" />
        </div> -->
        <div class="display-5 text-center">Izmena profila</div>
        <div class="mb-3">
          <label for="ime" class="col-form-label"> Ime: </label>
          <input
            v-model="korisnik.ime"
            type="text"
            class="form-control form-control-lg"
            id="ime"
            placeholder="Izmeniti ime"
          />
        </div>
        <div class="mb-3">
          <label for="prezime" class="col-form-label"> Prezime: </label>
          <input
            v-model="korisnik.prezime"
            type="text"
            class="form-control form-control-lg"
            placeholder="Izmeniti prezime"
            id="prezime"
          />
        </div>
        <div class="mb-3">
          <label for="korisnicko-ime" class="col-form-label">
            Korisničko ime:
          </label>
          <input
            v-model="korisnik.korisnickoIme"
            type="text"
            class="form-control form-control-lg"
            placeholder="Izmeniti korisničko ime"
            id="korisnicko-ime"
          />
        </div>
        <div class="mb-3">
          <label for="lozinka" class="col-form-label"> Lozinka: </label>
          <input
            v-model="korisnik.lozinka"
            type="password"
            class="form-control form-control-lg"
            placeholder="Izmeniti lozinku"
            id="lozinka"
          />
        </div>
        <div class="mb-3">
          <label for="datum" class="col-form-label">Datum rođenja:</label>
          <input
            v-model="korisnik.datumRodjenja"
            type="date"
            class="form-control form-control-lg"
            id="datum"
            min="1900-01-01"
            max="today"
          />
        </div>
        <div class="text-center mb-3">
          <button
            v-on:click="submit()"
            type="button"
            class="btn btn-lg btn-primary mt-4 px-5"
            id="prijava-potvrdi"
          >
            Potvrdi
          </button>
        </div>
      </form>
    </div>
  </section>
</template>

<script>
import axios from "axios";

export default {
  name: "EditProfile",
  data: function () {
    return {
      korisnik: {
        korisnickoIme: "",
        lozinka: "",
        ime: "",
        prezime: "",
        pol: "",
        datumRodjenja: "",
      },
    };
  },
  methods: {
    submit: function () {
      axios
        .post(
          `http://localhost:8080/api/login/info/izmena?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`,
          this.korisnik
        )
        .then((res) => {
          console.log(res);
          this.$router.push("/korisnici");
        })
        .catch((err) => {
          console.log(err);
          alert("Something went wrong!");
        });
    },
  },
  mounted() {},
};
</script>
<style>
#w30 {
  width: 30%;
}
</style>
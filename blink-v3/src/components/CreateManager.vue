<template>
  <section>
    <div class="container vh-100" id="w30">
      <form>
        <!-- <div class="d-inline row text-center my-2 mt-0">
          <img id="logo-prijava" src="../assets/blink-logo.svg" alt="" />
        </div> -->
        <div class="display-5 text-center m-4">Kreiranje menadžera</div>
        <div class="mb-3">
          <label for="ime" class="col-form-label">Ime:</label>
          <input
            v-model="korisnik.ime"
            type="text"
            class="form-control form-control-lg"
            id="ime"
            placeholder="Izmeniti ime"
          />
        </div>
        <div class="mb-3">
          <label for="prezime" class="col-form-label">Prezime:</label>
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
          <label for="lozinka" class="col-form-label">Lozinka:</label>
          <input
            v-model="korisnik.lozinka"
            type="password"
            class="form-control form-control-lg"
            placeholder="Izmeniti lozinku"
            id="lozinka"
          />
        </div>
        <div class="mb-3">
          <label class="col-form-label" for="radio-pol">Pol:</label>
          <div id="radio-pol">
            <div class="form-check">
              <input
                class="form-check-input"
                id="pol-m"
                type="radio"
                name="pol"
                checked
              />
              <label class="form-check-label" for="pol-m">Muški</label>
            </div>
            <div class="form-check">
              <input
                class="form-check-input"
                id="pol-z"
                type="radio"
                name="pol"
              />
              <label class="form-check-label" for="pol-z">Ženski</label>
            </div>
          </div>
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
  name: "CreateManager",
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
      if (document.getElementById("pol-m").checked) {
        this.korisnik.pol = "Muško";
      } else {
        this.korisnik.pol = "Žensko";
      }
      axios
        .post(
          `http://localhost:8080/api/admin/kreiraj-menadzera?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`,
          this.korisnik
        )
        .then((res) => {
          console.log(res);
          this.$router.push("/");
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
</style>
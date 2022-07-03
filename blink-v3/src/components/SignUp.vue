<template>
  <section class="vh-80">
    <div class="container-fluid h-custom">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-md-9 col-lg-6 col-xl-5">
          <img
            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
            class="img-fluid"
            alt="Sample image"
          />
        </div>
        <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
          <form>
            <div class="d-inline row text-center my-2 mt-0">
              <img id="logo-prijava" src="../assets/blink-logo.svg" alt="" />
            </div>
            <div class="mb-3">
              <label for="ime" class="col-form-label">Ime:</label>
              <input
                v-model="korisnik.ime"
                type="text"
                class="form-control form-control-lg"
                id="ime"
                placeholder="Uneti ime"
              />
            </div>
            <div class="mb-3">
              <label for="prezime" class="col-form-label">Prezime:</label>
              <input
                v-model="korisnik.prezime"
                type="text"
                class="form-control form-control-lg"
                placeholder="Uneti prezime"
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
                placeholder="Uneti korisničko ime"
                id="korisnicko-ime"
              />
            </div>
            <div class="mb-3">
              <label for="lozinka" class="col-form-label">Lozinka:</label>
              <input
                v-model="korisnik.lozinka"
                type="password"
                class="form-control form-control-lg"
                placeholder="Uneti lozinku"
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
              <div>
                <p class="small mt-2 pt-0 mb-0">
                  Već imaš nalog?
                  <router-link
                    to="/sign-in"
                    id="registruj-se-link"
                    class="primary-link"
                    >Prijavi se</router-link
                  >
                </p>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import axios from "axios";

export default {
  name: "SignUp",
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
        .post("http://localhost:8080/api/register", this.korisnik)
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
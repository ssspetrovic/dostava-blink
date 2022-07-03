<template>
  <section class="vh-100">
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
            <div class="form-outline my-4">
              <label class="form-label" for="form3Example3"
                >Korisničko ime</label
              >
              <input
                v-model="korisnik.korisnickoIme"
                type="email"
                class="form-control form-control-lg"
                placeholder="Uneti korisničko ime"
              />
            </div>
            <div class="form-outline mb-3">
              <label class="form-label" for="form3Example4">Lozinka</label>
              <input
                v-model="korisnik.lozinka"
                type="password"
                class="form-control form-control-lg"
                placeholder="Uneti lozinku"
              />
            </div>
            <div class="text-center mb-3 mt-4">
              <button
                v-on:click="submit()"
                type="button"
                class="btn btn-lg btn-primary px-5"
                id="prijava-potvrdi"
              >
                Potvrdi
              </button>
              <div>
                <p class="small mt-2 pt-0 mb-0">
                  Nemaš nalog?
                  <router-link
                    to="/sign-up"
                    id="registruj-se-link"
                    class="primary-link"
                    >Registruj se</router-link
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

<style>
.h-custom {
  height: calc(100% - 73px);
}
@media (max-width: 450px) {
  .h-custom {
    height: 100%;
  }
}

#logo-prijava {
  width: 50%;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

#registruj-se-link {
  text-decoration: none;
}
</style>

<script>
import axios from "axios";
export default {
  name: "LogiN",
  data: function () {
    return {
      korisnik: {
        korisnickoIme: "",
        lozinka: "",
      },
    };
  },
  methods: {
    submit: function () {
      axios
        .post("http://localhost:8080/api/login", this.korisnik)
        .then((res) => {
          console.log(res);
          this.$store.commit("addKorisnik", this.korisnik);
          this.$router.push("/korisnici");
        })
        .catch((err) => {
          console.log(err);
          alert("Greška! Pokušajte ponovo!");
        });
    },
  },
};
</script>
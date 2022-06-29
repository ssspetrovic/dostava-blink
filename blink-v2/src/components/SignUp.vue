<template>
  <div class="modal fade" id="registracija-modal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header bg-light">
          <h5 class="modal-title" id="modalLabel">Registracija</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
          ></button>
        </div>
        <div class="modal-body">
          <form>
            <div class="mb-3">
              <label for="ime" class="col-form-label"> Ime: </label>
              <input
                v-model="korisnik.ime"
                type="text"
                class="form-control"
                id="ime"
              />
            </div>
            <div class="mb-3">
              <label for="prezime" class="col-form-label"> Prezime: </label>
              <input
                v-model="korisnik.prezime"
                type="text"
                class="form-control"
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
                class="form-control"
                id="korisnicko-ime"
              />
            </div>
            <div class="mb-3">
              <label for="lozinka" class="col-form-label"> Lozinka: </label>
              <input
                v-model="korisnik.lozinka"
                type="password"
                class="form-control"
                id="lozinka"
              />
            </div>
            <div class="mb-3">
              <label class="col-form-label" for="radio-pol">Pol:</label>
              <div id="radio-pol">
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="pol"
                    id="pol-m"
                  />
                  <label class="form-check-label" for="pol-m"> Muško </label>
                </div>
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="pol"
                    id="pol-z"
                  />
                  <label class="form-check-label" for="pol-z"> Žensko </label>
                </div>
              </div>
            </div>
            <div class="mb-3">
              <label for="datum" class="col-form-label">Datum rođenja:</label>
              <input type="date" class="form-control" id="datum" />
            </div>
          </form>
          <div class="text-center mb-3 pt-3">
            <div class="fs-5 pb-0 mb-0">Već imaš nalog?</div>
            <div>
              <router-link
                to="/prijava"
                class="
                  btn btn-link
                  text-md text-primary text-decoration-none
                  mt-0
                  pt-0
                "
                data-bs-target="#prijava-modal"
                data-bs-toggle="modal"
              >
                Prijavi se
              </router-link>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Odustani
          </button>
          <button
            v-on:click="submit()"
            type="button"
            class="btn btn-primary"
            id="registracija-potvrdi"
          >
            Potvrdi
          </button>
        </div>
      </div>
    </div>
  </div>
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
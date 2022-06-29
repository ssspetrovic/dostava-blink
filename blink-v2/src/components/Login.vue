<template>
  <div class="modal fade" id="prijava-modal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header bg-light">
          <h5 class="modal-title" id="modalLabel">Prijava</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
          ></button>
        </div>
        <div class="modal-body">
          <form>
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
          </form>
          <div class="text-center mb-3 pt-3">
            <div class="fs-5 pb-0 mb-0">Nemaš nalog?</div>
            <div>
              <router-link
                to="/sign-up"
                class="
                  btn btn-link
                  text-md text-primary text-decoration-none
                  mt-0
                  pt-0
                "
                data-bs-target="#registracija-modal"
                data-bs-toggle="modal"
                >Registruj se</router-link
              >
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
            id="prijava-potvrdi"
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
          this.$router.push("/korisnici");
        })
        .catch((err) => {
          console.log(err);
          alert("Greska! Pokusajte ponovo!");
        });
    },
  },
};
</script>
<template>
  <section>
    <div class="container vh-100" id="w30">
      <form>
        <!-- <div class="d-inline row text-center my-2 mt-0">
          <img id="logo-prijava" src="../assets/blink-logo.svg" alt="" />
        </div> -->
        <div class="display-5 text-center m-4">Kreiranje restorana</div>
        <div class="mb-3">
          <label for="naziv" class="col-form-label">Naziv restorana: </label>
          <input
            v-model="restoran.naziv"
            type="text"
            class="form-control form-control-lg"
            id="naziv"
            placeholder="Uneti naziv"
          />
        </div>
        <div class="mb-3">
          <label for="tip" class="col-form-label">Tip restorana:</label>
          <input
            v-model="restoran.tipRestorana"
            type="text"
            class="form-control form-control-lg"
            placeholder="Uneti tip "
            id="tiš"
          />
        </div>
        <div class="mb-3">
          <label for="geografska-duzina" class="col-form-label">
            Geografska dužina:
          </label>
          <input
            v-model.number="restoran.lokacija.geografskaDuzina"
            type="text"
            class="form-control form-control-lg"
            placeholder="Uneti geografsku dužinu"
            id="geografska-duzina"
          />
        </div>
        <div class="mb-3">
          <label for="geografska-sirina" class="col-form-label">
            Geografska širina:
          </label>
          <input
            v-model.number="restoran.lokacija.geografskaSirina"
            type="text"
            class="form-control form-control-lg"
            placeholder="Uneti geografsku širinu"
            id="geografska-sirina"
          />
        </div>
        <div class="mb-3">
          <label for="adresa" class="col-form-label"> Adresa: </label>
          <input
            v-model="restoran.lokacija.adresa"
            type="text"
            class="form-control form-control-lg"
            placeholder="Uneti adresu"
            id="adresa"
          />
        </div>
        <div class="text-center mb-3">
          <button
            v-on:click="redirectToManager()"
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
  name: "CreateRestaurant",
  data: function () {
    return {
      restoran: {
        naziv: "",
        tipRestorana: "",
        lokacija: {
          geografskaDuzina: "",
          geografskaSirina: "",
          adresa: "",
        },
      },
    };
  },
  methods: {
    redirectToManager: function () {
      axios
        .post(
          `http://localhost:8080/api/admin/kreiraj-restoran?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`,
          this.restoran
        )
        .then((res) => {
          console.log(res);
          this.$router.push("/set-manager");
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
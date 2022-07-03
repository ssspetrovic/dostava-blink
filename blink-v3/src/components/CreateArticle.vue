<template>
  <section>
    <div class="container vh-100" id="w30">
      <form>
        <!-- <div class="d-inline row text-center my-2 mt-0">
          <img id="logo-prijava" src="../assets/blink-logo.svg" alt="" />
        </div> -->
        <div class="display-5 text-center m-4">Kreiranje artikla</div>
        <div class="mb-3">
          <label for="naziv" class="col-form-label">Naziv:</label>
          <input
            v-model="artikal.naziv"
            type="text"
            class="form-control form-control-lg"
            id="naziv"
            placeholder="Uneti naziv"
          />
        </div>
        <div class="mb-3">
          <label for="cena" class="col-form-label">Cena:</label>
          <input
            v-model.number="artikal.cena"
            type="text"
            class="form-control form-control-lg"
            placeholder="Uneti cenu"
            id="cena"
          />
        </div>
        <div class="mb-3">
          <label for="tip" class="col-form-label"> Tip: </label>
          <input
            v-model="artikal.tip"
            type="text"
            class="form-control form-control-lg"
            placeholder="Uneti tip"
            id="tip"
          />
        </div>
        <div class="mb-3">
          <label for="kolicina" class="col-form-label"> Količina: </label>
          <input
            v-model.number="artikal.kolicina"
            type="text"
            class="form-control form-control-lg"
            placeholder="Izmeniti količinu"
            id="kolicina"
          />
        </div>
        <div class="mb-3">
          <label for="opis" class="col-form-label"> Opis: </label>
          <input
            v-model="artikal.opis"
            type="text"
            class="form-control form-control-lg"
            placeholder="Izmeniti opis"
            id="opis"
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
  name: "CreateArticle",
  data: function () {
    return {
      artikal: {
        naziv: "",
        cena: "",
        tip: "",
        kolicina: "",
        opis: "",
      },
    };
  },
  methods: {
    submit: function () {
      axios
        .post(
          `http://localhost:8080/api/artikli/kreiraj-artikal?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`,
          this.artikal
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
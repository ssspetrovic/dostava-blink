<template>
  <div class="container vh-100">
    <div class="display-5 text-center">KREIRAJ ARTIKAL</div>
    <div class="register">
      <!--<input v-model="artikal.slike" /><br /> -->
      <label for="naziv">Naziv:</label>
      <input v-model="artikal.naziv" /><br />
      <label for="cena">Cena:</label>
      <input v-model.number="artikal.cena" /><br />
      <label for="tip">Tip:</label>
      <input v-model="artikal.tip" /><br />
      <label for="kolicina">Kolicina:</label>
      <input v-model.number="artikal.kolicina" /><br />
      <label for="opis">Opis:</label>
      <input v-model="artikal.opis" /><br />
      <button class="button" v-on:click="submit()">Kreiraj Artikal!</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "EditArticle",
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
          `http://localhost:8080/api/artikli/izmena/` +
            this.$route.params.id +
            `?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`,
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
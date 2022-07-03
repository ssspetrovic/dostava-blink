<template>
  <section>
    <div class="container vh-100">
      <label for="komentar">Unesite komentar:</label>
      <input v-model="komentar.tekstKomentara" /><br />
      <label for="komentar">Unesite ocenu od 1 do 5:</label>
      <input v-model.number="komentar.ocena" /><br />
      <button class="button" v-on:click="submit()">Postavite komentar!</button>
    </div>
  </section>
</template>

<script>
import axios from "axios";
export default {
  name: "CommenT",
  data: function () {
    return {
      komentar: {
        tesktKomentara: "",
        ocena: "",
      },
    };
  },
  methods: {
    submit: function () {
      axios
        .post("http://localhost:8080/api/komentar/", window.location.href)
        .then((res) => {
          console.log(res);
          this.$router.push("/korisnik" + window.location.href);
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
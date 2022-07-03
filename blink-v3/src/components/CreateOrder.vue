<template>
  <section>
    <div class="container vh-100">
      <div class="display-5 text-center">KREIRAJ ARTIKAL</div>
      <div class="register">
        <!--<input v-model="artikal.slike" /><br /> -->
        <label for="idRestorana">Id Restorana:</label>
        <input v-model.number="novaPorudzbinaDto.idRestorana" /><br />
        <label for="cena">Id Artikla:</label>
        <input v-model.number="novaPorudzbinaDto.novePorudzbine[0].id" /><br />
        <label for="tip">Kolicina:</label>
        <input v-model="novaPorudzbinaDto.novePorudzbine[0].kolicina" /><br />
        <button class="button" v-on:click="submit()">
          Kreiraj Porudzbinu!
        </button>
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";

export default {
  name: "CreateOrder",
  data: function () {
    return {
      novaPorudzbinaDto: {
        idRestorana: "",
        novePorudzbine: [
          {
            id: "",
            kolicina: "",
          },
        ],
      },
    };
  },
  methods: {
    submit: function () {
      axios
        .post(
          `http://localhost:8080/api/porudzbine?korisnickoIme=aaa`,
          this.novaPorudzbinaDto
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
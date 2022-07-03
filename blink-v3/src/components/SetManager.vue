<template>
  <section>
    <div class="container vh-100">
      <h1>Izaberite menadzera:</h1>
      <a
        v-for="clan in menadzer"
        :href="`/`"
        :key="clan.id"
        class="btn btn-primary btn-lg"
        ><td>{{ clan.korisnickoIme }}</td></a
      >
    </div>
  </section>
</template>

<script>
import axios from "axios";
export default {
  name: "SetManager",
  data: function () {
    return {
      menadzer: {},
    };
  },
  mounted: function () {
    axios
      .get(
        `http://localhost:8080/api/menadzer/ispis?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`
      )
      .then((res) => {
        this.menadzer = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

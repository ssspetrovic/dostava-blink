<template>
  <section>
    <div class="container vh-100">
      <a
        v-for="clan in restoran"
        :href="`/restoran/${clan.id}`"
        :key="clan.id"
        class="btn btn-primary btn-lg"
      >
        <td>
          {{ clan.naziv }}
        </td>
      </a>
      <!-- <div class="item error" v-if="input && !restoran.naziv.length">
        <p>No results found!</p>
      </div> -->
    </div>
  </section>
</template>

<script>
import axios from "axios";

export default {
  name: "SearchBar",
  data: function () {
    return {
      restoran: [{}],
    };
  },
  mounted: function () {
    axios
      .get(`http://localhost:8080/api/restoran/pretraga?string=A`)
      .then((res) => {
        this.restoran = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
  // computed: {
  //   stringRest() {
  //     return this.restoran.filter((clan) => {
  //       return clan.naziv.toLowerCase().includes(this.input.toLowerCase());
  //     });
  //   },
  // },
};
</script>

<style>
</style>
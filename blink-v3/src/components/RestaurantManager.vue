<!-- ZA OBRADU -->
<template>
  <section>
    <div class="container vh-100">
      <div>
        <h2 style="text-align: center">Restoran: {{ naziv }}</h2>
        <h2 style="text-align: center">Tip: {{ tip }}</h2>
        <h2 style="text-align: center">Lokacija: {{ lokacija }}</h2>
        <h2 style="text-align: center">Menadzer: {{ menadzerName }}</h2>
      </div>
    </div>
  </section>
</template>
<script>
import axios from "axios";
//import axios from "axios";
export default {
  name: "RestaurantManager",
  data: function () {
    return {
      menadzer: {},
    };
  },
  computed: {
    naziv() {
      return this.menadzer.restoran.naziv;
    },
    tip() {
      return this.menadzer.restoran.tipRestorana;
    },
    lokacija() {
      return this.menadzer.restoran.lokacija;
    },
    menadzerName() {
      return this.menadzer.korisnickoIme;
    },
  },
  created() {
    this.$watch(
      () => this.$route.params,
      () => {
        // react to route changes...
        this.fetchManager();
      }
    );
  },
  methods: {
    fetchManager() {
      axios
        .get(
          `http://localhost:8080/api/restorani/info/` + this.$route.params.id
        )
        .then((res) => {
          this.menadzer = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  mounted: function () {
    this.fetchRestoran();
  },
};
</script>

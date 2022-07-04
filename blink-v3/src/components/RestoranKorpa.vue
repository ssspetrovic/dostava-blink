<template>
  <section>
    <div class="container vh-100">
      <div class="display-4 text-center m-4">Korpa</div>
      <div class="table-responsive-sm">
        <table class="table table-borderless mx-auto" id="w60">
          <thead>
            <th>Naziv porudžbine:</th>
            <th>Cena:</th>
            <th>Količina:</th>
          </thead>
          <!-- <tbody> -->
          <tbody v-for="clan in porudzbinaDtoList" v-bind:key="clan.uuid">
            <tr
              v-for="clan2 in clan.porudzbineKupca"
              v-bind:key="clan2.nazivPorudzbine"
            >
              <th>{{ clan2.nazivPorudzbine }}</th>
              <td>{{ clan2.cenaPorudzbine }}</td>
              <td>{{ clan2.kolicina }}</td>
            </tr>
          </tbody>
          <!-- </tbody> -->
        </table>
      </div>
    </div>
  </section>
</template>
<script>
import axios from "axios";
//import axios from "axios";
export default {
  name: "RestoranKorpa",
  data: function () {
    return {
      porudzbinaDtoList: [
        {
          porudzbineKupca: [{}],
        },
      ],
    };
  },
  computed: {},
  created() {
    this.$watch(
      () => this.$route.params,
      () => {
        // react to route changes...
        this.fetchPorudzbina();
      }
    );
  },
  methods: {
    fetchPorudzbina() {
      axios
        .get(
          `http://localhost:8080/api/porudzbine/menadzeri?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`
        )
        .then((res) => {
          this.porudzbinaDtoList = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  mounted: function () {
    this.fetchPorudzbina();
  },
};
</script>

<template>
  <section>
    <div class="container vh-100">
      <div class="table-responsive-sm">
        <table class="table text-center">
          <thead v-for="clan in porudzbine" v-bind:key="clan.uuid">
            <tr>
              <th colspan="5" class="text-center lead">
                Restoran: {{ clan.restoran.naziv }}
              </th>
            </tr>
            <tr>
              <th class="p-3">Naziv artikla</th>
              <th class="p-3">Cena jednog artikla</th>
              <th class="p-3">Opis artikla</th>
              <th class="p-3">Naručena količina</th>
              <th class="p-3">Ukupna cena</th>
            </tr>
            <tr v-for="clan2 in clan.artikli" v-bind:key="clan2.artikal.id">
              <td class="p-1">{{ clan2.artikal.naziv }}</td>
              <td class="p-1">{{ clan2.artikal.cena }}</td>
              <td class="p-1">{{ clan2.artikal.opis }}</td>
              <td class="p-1">{{ clan2.kolicina }}</td>
              <td class="p-1">{{ clan2.ukupnaCena }}</td>
            </tr>
          </thead>
        </table>
      </div>
    </div>
  </section>
</template>
<script>
import axios from "axios";
//import axios from "axios";
export default {
  name: "ArtikaL",
  data: function () {
    return {
      porudzbine: [
        {
          artikli: [
            {
              artikal: {},
              kolicina: "",
              ukupnaCena: "",
            },
          ],
          restoran: {},
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
        this.fetchOrders();
      }
    );
  },
  methods: {
    fetchOrders() {
      axios
        .get(
          `http://localhost:8080/api/porudzbine/sveDostave?korisnickoIme=damir1`
        )
        .then((res) => {
          this.porudzbine = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  mounted: function () {
    this.fetchOrders();
  },
};
</script>

<template>
  <section>
    <div class="container vh-100">
      <div class="display-4 text-center m-4">Korpa</div>
      <div class="table-responsive-sm">
        <table class="table table-borderless mx-auto" id="w60">
          <thead>
            <th>Naziv porudžbine:</th>
            <th>Cena:</th>
            <th>Kolicčna:</th>
            <th>Opcija:</th>
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
              <td>
                <a v-on:click="smanjiKolicinu" href="#" class="link-primary"
                  >Obriši artikal</a
                >
              </td>
            </tr>
          </tbody>
          <!-- </tbody> -->
        </table>
      </div>

      <div class="d-flex justify-content-center"><a v-on:click="naruciPor" href="#" class="btn btn-dark btn-lg m-4">Naruči</a></div>
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
      porudzbinaDtoList: [
        {
          porudzbineKupca: [{}],
        },
      ],
    };
  },
  computed: {
    // findStr(str) {
    //     if(str != null)
    //     for(let i = 0; i < this.porudzbinaDtoList.length; i++)
    //         for(let j = 0; j < this.porudzbinaDtoList[i].porudzbineKupca.length; j++)
    //             if(str === this.porudzbinaDtoList[i].porudzbineKupca[j].nazivPorudzbine)
    //                 return str;
    //     return null;
    // }
  },
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
          `http://localhost:8080/api/porudzbine?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`
        )
        .then((res) => {
          this.porudzbinaDtoList = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    smanjiKolicinu() {
      axios
        .post(
          `http://localhost:8080/api/porudzbine/` +
            `Monster Energy` +
            `?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`
        )
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
          alert("Something went wrong!");
        });
    },
    naruciPor() {
      axios
        .get(
          `http://localhost:8080/api/porudzbine/naruci/1?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`
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
  mounted: function () {
    this.fetchPorudzbina();
  },
};
</script>

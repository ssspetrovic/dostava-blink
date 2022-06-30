<template>
    <div class = "watafak">
        <h2 style="text-align:center">Restoran: {{ naziv }}</h2>
        <h2 style="text-align:center">Tip: {{ tip }}</h2>
        <h2 style="text-align:center">Lokacija: {{ lokacija }}</h2>
        <h2 style="text-align:center">Menadzer: {{menadzerIme}}</h2>
    </div>
    <div class = "watafak2">
        <tr v-for = "clan in artikli" :key = "clan.id" class = "watafak2"> 
            <router-link :to = "{path: '/artikal/' + clan.id}" ><td><h2>{{clan.naziv}}</h2> </td></router-link>
        </tr>
    </div>
</template>
<script>
import axios from 'axios';
//import axios from "axios";
export default {
    name: 'RestoraN',
    data: function () {
        return {
            restoran: {
                lokacija: {},
                artikli: {},
                menadzer: {},
            },
        }
    },
    computed: {
        naziv() {
            return this.restoran.naziv;
        },
        tip() {
            return this.restoran.tipRestorana;
        },
        lokacija() {
            return this.restoran.lokacija.adresa;
        },
        artikli() {
            return this.restoran.artikli;
        },
        menadzerIme() {
            return this.restoran.menadzer.korisnickoIme;
        }
    },
    created() {
        this.$watch(
            () => this.$route.params,
            () => {
                // react to route changes...
                this.fetchRestoran()
            }
        )
    },
    methods: {
        fetchRestoran() {
            axios
                .get("http://localhost:8080/api/restorani/info/" + this.$route.params.id)
                .then((res) => {
                    this.restoran = res.data;
                    
                })
                .catch((err) => {
                    console.log(err)
                })
        }
    },
    mounted: function () {
        this.fetchRestoran()
        /*
                fetch('http://localhost:8083/api/korisnici/ispis',
                    {
                        credentials: 'include'
                    })
                    .then(response => response.json())
                    .then(data => {
                        console.log("Success:", data);
                        this.korisnik = data
                    })
                    .catch((error) => {
                        console.error("Error:", error)
                    });
            }
        */

    }
}
</script>

<style>
.watafak {
  margin: auto;
  width: 50%;
  padding: 10px;
}
.watafak2 {
  display:none;
  display:block;
  margin: auto;
  width: 50%;
}
</style>

<template>
    <div>
        <h2 style="text-align:center">{{ slike }}</h2>
        <h2 style="text-align:center">Naziv: {{ naziv }}</h2>
        <h2 style="text-align:center">Cena: {{ cena }}</h2>
        <h2 style="text-align:center">Kolicina: {{ kolicina }}</h2>
        <h2 style="text-align:center">Opis: {{ opis }}</h2>
        <router-link :to = "{path: '/edit-article/' + artikal.id}" class = "btn btn-primary btn-lg">MENADZER: Izmeni?</router-link>
        <router-link to = "/create-restaurant" class = "btn btn-primary btn-lg">KUPAC: Naruci?</router-link> <!--KORPA-->
        <a v-on:click = "logout" href = "#" class="nav-link">Delete Profile</a>
    </div>
</template>
<script>
import axios from 'axios';
//import axios from "axios";
export default {
    name: 'ArtikaL',
    data: function () {
        return {
            artikal: {},
        }
    },
    computed: {
        naziv() {
            return this.artikal.naziv;
        },
        cena() {
            return this.artikal.cena;
        },
        kolicina() {
            return this.artikal.kolicina;
        },
        opis() {
            return this.artikal.opis;
        },
        slike() {
            return this.artikal.slike;
        }
    },
    created() {
        this.$watch(
            () => this.$route.params,
            () => {
                // react to route changes...
                this.fetchArtikal()
            }
        )
    },
    methods: {
        fetchArtikal() {
            axios
                .get("http://localhost:8080/api/artikal/" + this.$route.params.id)
                .then((res) => {
                    this.artikal = res.data;
                    
                })
                .catch((err) => {
                    console.log(err)
                })
        },
        logout() {
            axios.delete(`http://localhost:8080/api/artikal/obrisi/` + this.$route.params.id + `?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`)
            .then((res) => {
                    console.log(res);
                    this.$router.push("/restorani");
                })
                .catch((err) => {
                    console.log(err);
                    alert("Something went wrong!");
                });
        }
    },
    mounted: function () {
        this.fetchArtikal()
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

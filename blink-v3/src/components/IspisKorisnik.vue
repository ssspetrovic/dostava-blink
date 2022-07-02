<template>
    <div class="container vh-100">
        <table>
            <thead>

            </thead>
            <tbody>
                
            </tbody>
        </table>

        <h2 style = "text-align:center"> Korisnicko Ime : {{kIme}}</h2>
        <h2 style = "text-align:center"> Ime : {{kI}}</h2>
        <h2 style = "text-align:center"> Prezime : {{kP}}</h2>
        <h2 style = "text-align:center"> Uloga : {{kU}}</h2>
        <tr v-for = "clan in komentari" :key = "clan.id"> 
            <td><router-link :to = "{path: '/restoran/' + clan.restoran.id}"><td><h2 style = "text-align:center">Komentari: {{clan.tekstKomentara}}</h2> </td></router-link></td>
        </tr>
    </div>
</template>

<script>

import axios from 'axios'

//import axios from "axios";
export default {
    name: 'IspisKorisnik',
    data: function () {
        return {
            korisnik: {
                komentari: {},
                restoran: {},
            },
        }
    },
    computed: {
        kIme() {
            return this.korisnik.korisnickoIme;
        },
        kI() {
            return this.korisnik.ime;
        },
        kP() {
            return this.korisnik.prezime;
        },
        kU() {
            return this.korisnik.uloga;
        },
        komentari() {
            return this.korisnik.komentari;
        },
    },
    created() {
        this.$watch(
            () => this.$route.params,
            () => {
                // react to route changes...
                this.fetchKorisnik()
            }
        )
    },
    methods: {
        fetchKorisnik() {
            axios
                .get(`http://localhost:8080/api/korisnik/` + this.$route.params.id + `/?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`)
                .then((res) => {
                    this.korisnik = res.data;
                })
                .catch((err) => {
                    console.log(err)
                })
        },
    },
    mounted: function () {
        this.fetchKorisnik()
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

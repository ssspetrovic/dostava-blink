<template>
        <div class = "container">
        <h1 class = "text-center"> Korpa </h1>
        <table class = "table table-striped">
            <thead>
                <th>Naziv Porudzbine: </th>
                <th>Cena: </th>
                <th>Kolicina: </th>
            </thead>
            <tbody>
                <div v-for = "clan in porudzbinaDtoList" v-bind:key = "clan.uuid">
                <tr v-for = "clan2 in clan.porudzbineKupca" v-bind:key = "clan2.nazivPorudzbine">
                    <td> {{clan2.nazivPorudzbine}}</td>
                    <td> {{clan2.cenaPorudzbine}}</td>
                    <td> {{clan2.kolicina}} </td>
                    <td> <a v-on:click = "smanjiKolicinu" href = "#" class="nav-link">Obrisi artikal?</a></td>

                </tr>
                </div>
            </tbody>
        </table>
        <router-link to = "/narudzbina" class = "btn btn-primary btn-lg">Naruci?</router-link>
    </div>
</template>
<script>
import axios from 'axios';
//import axios from "axios";
export default {
    name: 'ArtikaL',
    data: function () {
        return {
            porudzbinaDtoList: [{
                porudzbineKupca: [{}]
            }],
        }
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
                this.fetchPorudzbina()
            }
        )
    },
    methods: {
        fetchPorudzbina() {
            axios
                .get(`http://localhost:8080/api/porudzbine?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`)
                .then((res) => {
                    this.porudzbinaDtoList = res.data;
                    
                })
                .catch((err) => {
                    console.log(err)
                })
        },
        smanjiKolicinu() {
            axios.post(`http://localhost:8080/api/porudzbine/` + `Monster Energy` + `?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`)
            .then((res) => {
                    console.log(res);
                })
                .catch((err) => {
                    console.log(err);
                    alert("Something went wrong!");
                });
        }
    },
    mounted: function () {
        this.fetchPorudzbina()
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

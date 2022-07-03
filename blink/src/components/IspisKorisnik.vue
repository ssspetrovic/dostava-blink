<template>
    <div>
        <h2 style = "text-align:center"> Korisnicko Ime : {{kIme}}</h2>
        <h2 style = "text-align:center"> Ime : {{kI}}</h2>
        <h2 style = "text-align:center"> Prezime : {{kP}}</h2>
        <h2 style = "text-align:center"> Uloga : {{kU}}</h2>
        <router-link :to = "{path: '/restoran/' + kRid }"> <h2 class = "text-align:center">{{kRnaziv}}</h2></router-link>
        <a v-on:click = "uPripremi" href = "#" class="nav-link" v-if = "this.korisnik.restoran != null">STATUS: U PRIPREMI</a>
        <a v-on:click = "cekaDostavljaca" href = "#" class="nav-link" v-if = "this.korisnik.restoran != null">STATUS: CEKA DOSTAVLJACA</a>
        <a v-on:click = "uTransportu" href = "#" class="nav-link" v-if = "this.korisnik.porudzbine != null">STATUS: U TRANSPORTU</a>
        <a v-on:click = "dostavljeno" href = "#" class="nav-link" v-if = "this.korisnik.porudzbine != null">STATUS: DOSTAVLJENO</a>

        <tr v-for = "clan in komentari" :key = "clan.id"> 
            <td><h2 style = "text-align:center">Komentari: {{clan.tekstKomentara}} Ocena: {{clan.ocena}}</h2></td>
        </tr>
        <router-link to = "/edit-profile" class = "btn btn-primary btn-lg">Edit Profile</router-link>
        <a v-on:click = "logout" href = "#" class="nav-link">Delete Profile</a>
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
                porudzbine: [{}],
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
        kR() {
            if(this.korisnik.restoran != null)
                return this.korisnik.restoran;
            return null;
        },
        kRid() {
            if(this.korisnik.restoran != null)
                return this.korisnik.restoran.id;
            return null;
        },
        kRnaziv() {
            if(this.korisnik.restoran != null)
                return this.korisnik.restoran.naziv;
            return null;
        }
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
        logout() {
            axios.delete(`http://localhost:8080/api/admin/obrisi-korisnika/` + this.$route.params.id + `?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`)
            .then((res) => {
                    console.log(res);
                })
                .catch((err) => {
                    console.log(err);
                    alert("Something went wrong!");
                });
        },
        uPripremi() {
            axios.get(`http://localhost:8080/api/porudzbine/priprema/1?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`)
            .then((res) => {
                    console.log(res);
                })
                .catch((err) => {
                    console.log(err);
                    alert("Something went wrong!");
                });
        },
        cekaDostavljaca() {
            axios.get(`http://localhost:8080/api/porudzbine/ceka/1?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`)
            .then((res) => {
                    console.log(res);
                })
                .catch((err) => {
                    console.log(err);
                    alert("Something went wrong!");
                });
        },
        uTransportu() {
            axios.get(`http://localhost:8080/api/porudzbine/transport/1?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`)
            .then((res) => {
                    console.log(res);
                })
                .catch((err) => {
                    console.log(err);
                    alert("Something went wrong!");
                });
        },
        dostavljeno() {
            axios.get(`http://localhost:8080/api/porudzbine/dostavljeno/1?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`)
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

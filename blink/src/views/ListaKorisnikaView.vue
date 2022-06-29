<template>
    <div class = "container">
        <h2 style = "text-align:center">Nasi Korisnici:</h2>
        <a v-for = "korisnik in korisnici" :href = "`/korisnik/${korisnik.id}`" :key = "korisnik.ime" class = "card">
            <h5>{{korisnik.ime}} {{korisnik.prezime}}</h5>
            <br>
        </a>
    </div>
</template>

<script>

import axios from 'axios';

export default {
    name: 'ListaKorisnikaView',
    data: function() {
        return {
            korisnici: {},
        }
    },
    mounted: function() {
        axios
            .get("http://localhost:8080/api/korisnici")
            .then((res) => {
                this.korisnici = res.data
            })
            .catch((err) => {
                console.log(err)
            })
    }
}

</script>

<style>
</style>
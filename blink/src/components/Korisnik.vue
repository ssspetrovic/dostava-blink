<template>

    <div class = "container">
        <h1 class = "text-center"> Lista Korisnika </h1>
        <table class = "table table-striped">
            <thead>
                <th>ID: </th>
                <th>Korisnicko Ime: </th>
                <th>Ime: </th>
                <th>Prezime: </th>
                <th>Pol: </th>
                <th>Datum Rodjenja: </th>
            </thead>
            <tbody>
                <tr v-for = "korisnik in korisnici" v-bind:key = "korisnik.id">
                    <td> {{korisnik.id}}</td>
                    <router-link :to = "{path: '/korisnik/' + korisnik.id}"><td> {{korisnik.korisnickoIme}}</td></router-link>
                    <td> {{korisnik.ime}}</td>
                    <td> {{korisnik.prezime}}</td>
                    <td> {{korisnik.pol}}</td>
                    <td> {{korisnik.datumRodjenja}}</td>

                </tr>
            </tbody>
        </table>
    </div>

    <div class = "kreAD">
        <router-link to = "/create-manager" class = "btn btn-primary btn-lg">ADMIN: Kreiraj Menadzera</router-link>
        <router-link to = "/create-dostavljac" class = "btn btn-primary btn-lg">ADMIN: Kreiraj Dostavljaca</router-link>
    </div>

</template>

<script>
import axios from "axios"

export default {
    name: 'SviKorisnici',
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

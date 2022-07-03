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
                <th>Uloga: </th>
            </thead>
            <tbody>
                <tr v-for = "clan in korisnik" v-bind:key = "clan.id">
                    <td> {{clan.id}}</td>
                    <router-link :to = "{path: '/korisnik/' + clan.id}"><td> {{clan.korisnickoIme}}</td></router-link>
                    <td> {{clan.ime}}</td>
                    <td> {{clan.prezime}}</td>
                    <td> {{clan.pol}}</td>
                    <td> {{clan.datumRodjenja}}</td>
                    <td> {{clan.uloga}}</td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class = "kreAD">
        <router-link to = "/create-manager" class = "btn btn-primary btn-lg">ADMIN: Kreiraj Menadzera</router-link>
        <router-link to = "/create-dostavljac" class = "btn btn-primary btn-lg">ADMIN: Kreiraj Dostavljaca</router-link>
        <router-link to = "/all-managers" class = "btn btn-primary btn-lg">ADMIN: Svi menadzeri</router-link>
        <router-link to = "/restorani" class = "btn btn-primary btn-lg">Restorani</router-link>
    </div>

</template>

<script>
import axios from "axios"

export default {
    name: 'SviKorisnici',
    data: function() {
        return {
            korisnik: [{}],
        }
    },
    mounted: function() {
        axios
            .get("http://localhost:8080/api/korisnici")
            .then((res) => {
                this.korisnik = res.data
            })
            .catch((err) => {
                console.log(err)
            })
    }
}
</script>

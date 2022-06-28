<template>

    <div class = "container">
        <h1 style = "text-align:center"> Lista Korisnika </h1>
        <table class = "table-striped">
            <thead>
                <th>ID </th>
                <th>Korisnicko Ime </th>
                <th>Ime </th>
                <th>Prezime </th>
                <th>Pol </th>
            </thead>
            <tbody>
                <tr v-for = "korisnik in korisnici" v-bind:key = "korisnik.id">
                    <td> {{korisnik.id}}</td>
                    <td> {{korisnik.korisnickoIme}}</td>
                    <td> {{korisnik.ime}}</td>
                    <td> {{korisnik.prezime}}</td>
                    <td> {{korisnik.pol}}</td>
                </tr>
            </tbody>
        </table>
    </div>

</template>

<script>
import axios from 'axios'
export default {
    name: 'ListaSvihKorisnikaView',
    data: function(){
        return {
           korisnici :  []
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
    },
}
</script>

<style scoped>
.center{
    margin-left: auto;
    margin-right: auto;
}
</style>
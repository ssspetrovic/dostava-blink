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
                    <td> {{korisnik.korisnickoIme}}</td>
                    <td> {{korisnik.ime}}</td>
                    <td> {{korisnik.prezime}}</td>
                    <td> {{korisnik.pol}}</td>
                    <td> {{korisnik.datumRodjenja}}</td>

                </tr>
            </tbody>
        </table>
    </div>

</template>

<script>
import KorisnikService from '@/services/KorisnikService'

export default {
    name: 'SviKorisnici',
    data(){
        return {
           korisnici :  []
        }
    },
    methods: {
        getKorisnici(){
            KorisnikService.vratiKorisnike().then((response) =>{
                this.korisnici = response.data;
            });
        }
    },
    created() {
        this.getKorisnici()
    }
}
</script>
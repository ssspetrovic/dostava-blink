<template>
    <h2>Registracija</h2>
    <div class = register>
        <label for="korisnickoIme">Username:</label>
        <input v-model="korisnik.korisnickoIme" /><br />
        <label for="lozinka">Password:</label>
        <input v-model="korisnik.lozinka" /><br />
        <label for="ime">Ime:</label>
        <input v-model="korisnik.ime" /><br />
        <label for="prezime">Prezime:</label>
        <input v-model="korisnik.prezime" /><br />
        <label for="pol">Pol:</label>
        <input v-model="korisnik.pol" /><br />
        <label for="datumRodjenja">Datum Rodjenja:</label>
        <input type = "date" id = "start" name = "trip-start" min = "1900-01-01" max = today v-model="korisnik.datumRodjenja" /><br />
        <button class = "button" v-on:click="submit()">Prijavite se!</button>
        <div class = "moveRoute">
        <router-link to = "/prijava" class = "btn btn-primary btn-lg">Vec imate profil?</router-link>
        </div>
    </div>
</template>

<script>
import axios from "axios"
export default {
    name: "SignUp",
    data: function () {
        return {
            korisnik: {
                korisnickoIme: "",
                lozinka: "",
                ime: "",
                prezime: "",
                pol: "",
                datumRodjenja: "",  
            },
        };
    },
    methods: {
        submit: function () {

            axios
                .post("http://localhost:8080/api/register", this.korisnik)
                .then((res) => {
                    console.log(res);
                    this.$router.push("/korisnici");
                })
                .catch((err) => {
                    console.log(err);
                    alert("Something went wrong!");
                });

        },
    },
    mounted() {
        
    }
};
</script>


<style>
</style>
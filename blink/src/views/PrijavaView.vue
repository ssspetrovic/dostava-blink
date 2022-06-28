<template>
    <label for="username">Korisnicko Ime:</label>
    <input v-model="korisnik.korisnickoIme" /><br />
    <label for="password">Lozinka:</label>
    <input v-model="korisnik.lozinka" /><br />
    <button v-on:click="submit()">Prijavite se!</button>
</template>

<script>
import axios from "axios"
export default {
    name: "PrijavaView",
    data: function () {
        return {
            korisnik: {
                korisnickoIme: "",
                lozinka: "",
            },
        };
    },
    methods: {
        submit: function () {

            axios
                .post("http://localhost:8080/api/login", this.korisnik)
                .then((res) => {
                    console.log(res);
                    this.$router.push("/jedan-korisnik/" + this.$route.params.id);
                })
                .catch((err) => {
                    console.log(err);
                    alert("Greska! Pokusajte ponovo!");
                });
        },
    },
};
</script>

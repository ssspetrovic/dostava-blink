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
        <button v-on:click="submit()">Prijavite se!</button>
    </div>
</template>

<script>
import axios from "axios"
export default {
    name: "SignUp",
    data: function () {
        return {
            korisnik: {
                ime: "",
                prezime: "",
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
};
</script>


<style>
.register input {
    width: 300px;
    height: 40px;
    padding-left: 20px;
    display: block;
    margin-bottom: 30px;
    margin-right: auto;
    margin-left: auto;
    border: 1px solid skyblue;
}

.register button {
    width: 320px;
    height: 40px;
    border: 1px solid skyblue;
    background: skyblue;
    color: #fff;
    cursor: pointer;
}
</style>
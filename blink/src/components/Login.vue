<template>
  <h2>Login</h2>
  <div class = "register">
  <label for="korisnickoIme">Korisnicko Ime:</label>
  <input v-model="korisnik.korisnickoIme" /><br />
  <label for="lozinka">Lozinka:</label>
  <input v-model="korisnik.lozinka" /><br />
  <button v-on:click="submit()">Prijavite se!</button>
  <p>
        <router-link to = "/sign-up" class = "btn btn-primary btn-lg">Zelite li da napravite profil?</router-link>
  </p>
  </div>
</template>

<script>
import axios from "axios"
export default {
    name: "LogiN",
    data: function () {
        return {
            korisnik: {
                korisnickoIme: "",
                lozinka: ""
            },
        };
    },
    methods: {
        submit: function () {  

            axios
                .post("http://localhost:8080/api/login", this.korisnik)
                .then((res) => {
                    console.log(res);
                    this.$store.commit('addKorisnik', this.korisnik)
                    this.$router.push("/korisnici");
                })
                .catch((err) => {
                    console.log(err);
                    alert("Greska! Pokusajte ponovo!");
                });
        },
    },
};
</script>

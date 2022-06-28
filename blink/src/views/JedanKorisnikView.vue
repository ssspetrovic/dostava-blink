<template>
    <div>
        <h2 style = "text-align:center">Vas Profil</h2>
        <h1>{{korisnik.korisnickoIme}}</h1>
    </div>
</template>

<script>

import axios from 'axios'

export default {
    name: "KorisnikView",
    data: function() {
        return {
            korisnik: {},
        }
    },
    mounted: function() {
        axios.get("http://localhost:8080/api/korisnici/", + this.$route.params.id)
        .then((res) => {
            this.korisnik = res.data
        })
        .catch((err) => {
            console.log(err)
        })
    }
}

</script>

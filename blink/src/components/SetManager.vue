<template>
    <h1>Izaberite menadzera:</h1>
<a v-for = "clan in menadzer" :href = "`/korisnik/${clan.id}`" :key = "clan.id" class = "btn btn-primary btn-lg"><td>{{clan.korisnickoIme}}</td></a>
</template>

<script>
import axios from "axios"
export default {
    name: 'SetManager',
    data: function() {
        return {
            menadzer: {},
        }
    },
    mounted: function() {
        axios
            .get("http://localhost:8080/api/menadzer/ispis")
            .then((res) => {
                this.menadzer = res.data
            })
            .catch((err) => {
                console.log(err)
            })
    }
}
</script>

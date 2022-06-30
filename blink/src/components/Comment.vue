<template>
    <div class = register>
        <label for="komentar">Unesite komentar:</label>
        <input v-model="komentar.tekstKomentara" /><br />
        <label for="komentar">Unesite ocenu od 1 do 5:</label>
        <input v-model.number="komentar.ocena" /><br />
        <button class = "button" v-on:click="submit()">Postavite komentar!</button>
    </div>
</template>

<script>
import axios from "axios"
export default {
    name: "CommenT",
    data: function () {
        return {
            komentar: {
                tesktKomentara: "",
                ocena: ""
            }
        };
    },
    methods: {
        submit: function () {

            axios
                .post("http://localhost:8080/api/komentar", this.$query.params.id)
                .then((res) => {
                    console.log(res);
                    this.$router.push("/korisnik" + this.$query.params.id);
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
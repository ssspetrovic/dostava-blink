<template>
    <h2>KREIRAJ ARTIKAL</h2>
    <div class = register>
        <!--<input v-model="artikal.slike" /><br /> -->
        <label for="idRestorana">Id Restorana:</label>
        <input v-model.number="novaPorudzbinaDto.idRestorana" /><br />
        <label for="cena">Id Artikla:</label>
        <input v-model.number="novaPorudzbinaDto.novePorudzbine.id" /><br />
        <label for="tip">Kolicina:</label>
        <input v-model="novaPorudzbinaDto.novePorudzbine.kolicina" /><br />
        <button class = "button" v-on:click="submit()">Kreiraj Porudzbinu!</button>
    </div>
</template>

<script>
import axios from "axios"

export default {
    name: "CreateOrder",
    data: function () {
        return {
            novaPorudzbinaDto: {
                idRestorana: "",
                novePorudzbine: [{
                    id: "",
                    kolicina: "",
                }]
            }
        };
    },
    methods: {
        submit: function () {

            axios
                .post(`http://localhost:8080/api/artikli/kreiraj-artikal?korisnickoIme=aaa`, this.novaPorudzbinaDto)
                .then((res) => {
                    console.log(res);
                    this.$router.push("/korpa");
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
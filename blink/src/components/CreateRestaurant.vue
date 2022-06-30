<template>
    <h2>Registracija</h2>
    <div class = register>
        <label for="imeRestorana">Ime Restorana:</label>
        <input v-model="restoran.naziv" /><br />
        <label for="tipRestorana">Tip Restorana:</label>
        <input v-model="restoran.tipRestorana" /><br />
        <label for="Lokacija">Geografska Duzina:</label>
        <input v-model.number="restoran.lokacija.geografskaDuzina" /><br />
        <label for="Lokacija">Geografska Sirina:</label>
        <input v-model.number="restoran.lokacija.geografskaSirina" /><br />
        <label for="Lokacija">Adresa:</label>
        <input v-model="restoran.lokacija.adresa" /><br />
        <button class = "button" v-on:click="redirectToManager()">Postavi!</button>
    </div>
</template>

<script>
import axios from "axios"
export default {
    name: "CreateRestaurant",
    data: function () {
        return {
            restoran: {
                naziv: "",
                tipRestorana: "",
                lokacija: {
                    geografskaDuzina: "",
                    geografskaSirina: "",
                    adresa: ""
                }
            }
        };
    },
    methods: {
        redirectToManager: function () {

            axios
                .post("http://localhost:8080/api/admin/kreiraj-restoran", this.restoran)
                .then((res) => {
                    console.log(res);
                    this.$router.push("/set-manager");
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
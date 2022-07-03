<template>
    <div v-for = "clan in porudzbine" v-bind:key = "clan.uuid">
                <tr v-for = "clan2 in clan.artikli" v-bind:key = "clan2.artikal.id">
                    <td> {{clan2.artikal.naziv}}</td>
                    <td> {{clan2.artikal.cena}}</td>
                    <td> {{clan2.artikal.opis}} </td>
                    <td> {{clan.kolicina}} </td>
                    <td> {{clan.ukupnaCena}} </td>
                </tr>
        <h2 style="text-align:center">Restoran: {{ clan.restoran.naziv }}</h2>  
    </div>
    
</template>
<script>
import axios from 'axios';
//import axios from "axios";
export default {
    name: 'ArtikaL',
    data: function () {
        return {
                porudzbine: [{
                    artikli: [{
                        artikal: {},
                    }],
                    restoran: {},
                }],
            
        }
    },
    computed: {
        
    },
    created() {
        this.$watch(
            () => this.$route.params,
            () => {
                // react to route changes...
                this.fetchOrders()
            }
        )
    },
    methods: {
        fetchOrders() {
            axios
                .get(`http://localhost:8080/api/porudzbine/sveDostave?korisnickoIme=damir1`)
                .then((res) => {
                    this.porudzbine = res.data;
                    
                })
                .catch((err) => {
                    console.log(err)
                })
        },
    },
    mounted: function () {
        this.fetchOrders()
    }
}
</script>

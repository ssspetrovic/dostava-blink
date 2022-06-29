<!-- ZA OBRADU -->
<template>
    <div>
        <h2 style="text-align:center">Restoran: {{ naziv }}</h2>
        <h2 style="text-align:center">Tip: {{ tip }}</h2>
        <h2 style="text-align:center">Lokacija: {{ lokacija }}</h2>
        <h2 style="text-align:center">Menadzer: {{ menadzerName }}</h2>


    </div>
</template>
<script>
import axios from 'axios';
//import axios from "axios";
export default {
    name: 'RestaurantManager',
    data: function () {
        return {
            menadzer: {},
        }
    },
    computed: {
        naziv() {
            return this.menadzer.restoran.naziv;
        },
        tip() {
            return this.menadzer.restoran.tipRestorana;
        },
        lokacija() {
            return this.menadzer.restoran.lokacija;
        },
        menadzerName() {
            return this.menadzer.korisnickoIme;
        }
    },
    created() {
        this.$watch(
            () => this.$route.params,
            () => {
                // react to route changes...
                this.fetchManager()
            }
        )
    },
    methods: {
        fetchManager() {
            axios
                .get(`http://localhost:8080/api/admin/restorani/:id/postavi-menadzera?korisnickoIme=` + this.$query.params.korisnickoIme)
                .then((res) => {
                    this.menadzer = res.data;
                    
                })
                .catch((err) => {
                    console.log(err)
                })
        }
    },
    mounted: function () {
        this.fetchRestoran()
        /*
                fetch('http://localhost:8083/api/korisnici/ispis',
                    {
                        credentials: 'include'
                    })
                    .then(response => response.json())
                    .then(data => {
                        console.log("Success:", data);
                        this.korisnik = data
                    })
                    .catch((error) => {
                        console.error("Error:", error)
                    });
            }
        */

    }
}
</script>

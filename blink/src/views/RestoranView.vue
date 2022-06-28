<template>
    <div>
        <h2 style="text-align:center">Restoran {{ naziv }}</h2>
    </div>
</template>
<script>
import axios from 'axios';
//import axios from "axios";
export default {
    name: 'RestoranView',
    data: function () {
        return {
            restoran: {},
        }
    },
    computed: {
        naziv() {
            return this.restoran.naziv;
        }
    },
    created() {
        this.$watch(
            () => this.$route.params,
            () => {
                // react to route changes...
                this.fetchRestoran()
            }
        )
    },
    methods: {
        fetchRestoran() {
            axios
                .get("http://localhost:8080/api/restorani/info/" + this.$route.params.id)
                .then((res) => {
                    this.restoran = res.data;
                    
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

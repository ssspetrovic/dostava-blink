<template>
    <section id="couriers">
        <div class="wrapper">
            <h2 style="text-align:center" class="title">Korisnik Info</h2>
            <ul id="courier" class="slider_couriers">
                <li class="slider_courier">

                    <div class="courier_info">
                        <div>

                            <h1>{{ korisnik.name }}</h1>
                            <h1>{{ korisnik.surname }}</h1>
                            <h1>{{ korisnik.username }}</h1>

                        </div>
                        <a href="" class="btn">More Information</a>
                    </div>
                </li>
            </ul>
            <br>
            <br>
        </div>
    </section>
</template>


<script>
import axios from 'axios';
//import axios from "axios";
export default {
    name: 'SearcH',
    data: function () {
        return {
            korisnik: {},
        }
    },
    mounted: function () {

        axios
            .get("http://localhost:8080/api/admin/korisnik/name/" + this.$route.params.name + `?username=${this.$store.getters.korisnik.username}`)
            .then((res) => {
                this.korisnik = res.data
            })
            .catch((err) => {
                console.log(err)
            })
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
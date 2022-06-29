<template>
    <h1>Vas izbor restorana:</h1>
    <div class = "container">
    <table>
            <a v-for = "clan in restoran" :href = "`/restoran/${clan.id}`" :key = "clan.id" class = "btn btn-primary btn-lg"> 
            <td> {{clan.naziv}} </td>
            </a>
    </table>
    </div>
</template>

<script>
import axios from "axios"
export default {
    name: 'RestoraniList',
    data: function() {
        return {
            restoran: {},
        }
    },
    mounted: function() {
        axios
            .get("http://localhost:8080/api/restorani/lista")
            .then((res) => {
                this.restoran = res.data
            })
            .catch((err) => {
                console.log(err)
            })
    }
}
</script>

<style>
.container{
    margin: 100px;
}
</style>
<template>
  <a v-for = "clan in restoran" :href = "`/restoran/${clan.id}`" :key = "clan.id" class = "btn btn-primary btn-lg"> 
            <td> 
                {{clan.naziv}}
            </td>
  </a>
  <div class="item error" v-if="input&&!restoran.naziv.length">
     <p>No results found!</p>
  </div>
</template>

<script>
import axios from "axios"

export default {
name: 'SearchBar',
    data: function() {
        return {
            restoran: [{}],
        }
    },
    mounted: function() {
        axios
            .get(`http://localhost:8080/api/restoran/pretraga?string=Atr`)
            .then((res) => {
                this.restoran = res.data
            })
            .catch((err) => {
                console.log(err)
            })
    },
    computed: {
          stringRest() {
            return this.restoran.filter(clan => {
              return clan.naziv.toLowerCase().includes(this.input.toLowerCase());
      })
    }
    }
}
</script>

<style>

body {
  padding: 20px;
  min-height: 100vh;
  background-color: rgb(234, 242, 255);
}

input {
  display: block;
  width: 350px;
  margin: 20px auto;
  padding: 10px 45px;
  background: white url("../assets/logo.svg") no-repeat 15px center;
  background-size: 15px 15px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}

.item {
  width: 350px;
  margin: 0 auto 10px auto;
  padding: 10px 20px;
  color: white;
  border-radius: 5px;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 1px 3px 0px,
    rgba(0, 0, 0, 0.06) 0px 1px 2px 0px;
}

.fruit {
  background-color: rgb(97, 62, 252);
  cursor: pointer;
}

.error {
  background-color: tomato;
}
</style>
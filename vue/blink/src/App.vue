<template>
 <div id = Blink>
  <Korisnik />
 </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
import Korisnik from './components/Korisnik.vue'

export default {
  name: 'BlinkApp',
  components: {
    Korisnik
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

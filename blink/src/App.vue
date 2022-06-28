<template>
  <Header />
  <router-view/>
</template>

<script>
import Header from './components/layout/Header';

export default {
  name : "AppBlink",
  components: 
  {
    Header
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #763291;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #492699;
}

nav a.router-link-exact-active {
  color: #9a2da8;
}

.logo{
  width: 100px;
}
</style>

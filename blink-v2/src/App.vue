<template>
  <Header/>
  <FrontPage/>
  <!-- <router-view/> -->
</template>

<script>
  import Header from "./components/layout/Header.vue";
  import FrontPage from "./components/FrontPage.vue"

  export default {
    name: "AppBlink",
    components: {
      Header,
      FrontPage
    }
  }
</script>

/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
} */
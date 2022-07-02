<template>
  <Header/>
  <router-view/>
  <Footer/>
</template>

<style>
  
</style>

<script>
import Header from "./components/layout/Header.vue"
import Footer from "./components/layout/Footer.vue"

export default {
  name: "AppBlink",
  components: {
    Header,
    Footer
  }
};
</script>

<template>
  <Header/>
  <router-view/>
  <Footer/>
</template>

<script>
import Header from "./components/layout/Header.vue"
import Footer from "./components/layout/Footer.vue"
// import FrontPage from "./components/FrontPage.vue"

export default {
  name: "AppBlink",
  components: {
    Header,
    Footer
    // FrontPage
  }
};
</script>

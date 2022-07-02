<template>
    <nav class="navbar navbar-expand-lg py-3 sticky-top navbar-light shadow p-3 mb-5 bg-white rounded">
    <div class="container">
      <router-link to = "/">
				<img class = "logoNav" src="../../assets/blink-logo.svg" alt="" width="100">
			</router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navmenu">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navmenu">
        <form class="d-flex mt-4 mb-1" role="search">
					<input class="form-control me-2" type="search" placeholder="Pretraži">
					<router-link to = "/search-bar" class="btn btn-outline-primary" type="submit">Pretraga</router-link>
				</form>
				<ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <router-link to = "/prijava" href = "#prijava-nodal" class = "nav-link" data-bs-toggle = "modal">Prijavi se</router-link>
          </li>
          <li class="nav-item">
            <router-link to = "/sign-up" href="#registracija-modal" class="nav-link" data-bs-toggle="modal">Registruj se</router-link>
          </li>
					<li class="nav-item">
						<router-link to = "/korpa" href="#korpa-modal" class="nav-link" data-bs-toggle="modal">Korpa</router-link> <!-- ZA OBRADU-->
					</li>
          <li class="log-out">
            <a v-on:click = "logout" href = "#" class="nav-link">Logout</a>
          </li>
          <li class="clearout">
            <a v-on:click = "clearout" href = "#" class="nav-link">Clearout</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

</template>

<script>
import axios from "axios"

export default 
{
    name: "HeadeR",
    methods: {
        logout() {
            axios.post(`http://localhost:8080/api/logout?korisnickoIme=${this.$store.getters.korisnik.korisnickoIme}`)
            .then((res) => {
                    console.log(res);
                    this.$router.push("/prijava");
                })
                .catch((err) => {
                    console.log(err);
                    alert("Something went wrong!");
                });
            
    },
        clearout() {
            axios.post(`http://localhost:8080/api/clearout`)
              .then((res) => {
                    console.log(res);
                    this.$router.push("/prijava");
                })
                .catch((err) => {
                    console.log(err);
                    alert("Something went wrong!");
                });
    }
  }
}
</script>


<style>

.header {
    background: skyblue;
    color: #ffffff;
    text-align: center;
    padding: 15px;
}

.header a {
    color: #ffffff;
    padding-right: 15px;
}

.nav {
    background-color: #333;
    overflow: hidden
}

.logoNav {
    width: 100px;
    float: left;
}

.navbar-toggler {
    float: right;
}

</style>
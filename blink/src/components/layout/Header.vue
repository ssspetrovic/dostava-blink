<template>
    <!-- <header class = "header">
        <nav>
            <img class = "logoNav" src = "../../assets/logo.svg"/> 
            <router-link to = "/">Glavna Strana</router-link>
            <router-link to = "/o-nama">O nama</router-link>
            <router-link to = "/korisnici">Lista Korisnika</router-link>
            <router-link to = "/sign-up">Sign Up</router-link>
            <router-link to = "/prijava">Login</router-link>
            <a v-on:click = "logout" href = "#">Logout</a>
            <router-link to = "/lista-porudzbina">Porudzbina</router-link>
            <router-link to = "/restoran/1">Atrijum</router-link>
            <router-link to = "/restoran/2">Machiatto</router-link>
            <router-link to = "/svi-korisnici">Lista Svih Korisnika</router-link>
        </nav>

    </header> -->

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
						<a href="#" class="nav-link">Korpa</a> <!-- ZA OBRADU-->
					</li>
        </ul>
      </div>
    </div>
  </nav>

</template>

<script>

export default 
{
    name: "HeadeR",
    methods: {
        logout() {
            localStorage.clear();
            this.$router.push({name: 'prijava'})
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
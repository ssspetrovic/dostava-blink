<template>
    <header class = "header">
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

    </header>

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

</style>
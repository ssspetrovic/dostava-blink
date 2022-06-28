<template>
    <tr>
        <td>{{korisnik.ime}}</td>
        <td>{{korisnik.prezime}}</td>
        <td>
            <button class = "btnVeciPregled" v-on:click = "veciPregled">Veci Pregled</button>
        </td>
        <td>
            <button class = "obrisi" v-on:click = "obrisiKorisnika"> Obrisi </button>
        </td>
    </tr>
</template>

<script>
export default {
    name: "KorisnikDef",
    props: ["korisnik"],
    methods: {
        veciPregled: function() {
            this.$router.push("/korisnik?id=" + this.korisnik.id);
        },
        obrisiKorisnika: function() {
            fetch('http://localhost:8080/api/korisnici' + this.korisnik.id, {
                method: "DELETE",
            }).then((res) => {
                if(res.ok) {
                    window.location.reload();
                }
            });
        },
    },
};
</script>

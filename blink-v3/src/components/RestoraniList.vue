<template>
  <section>
    <div class="container vh-100">
      <div class="table-responsive-sm">
        <table class="table table-borderless text-center mb-5">
          <thead>
            <tr>
              <a
                v-for="clan in restoran"
                :href="`/restoran/${clan.id}`"
                :key="clan.id"
                class="link-primary display-3 p-5"
              >
                {{ clan.naziv }}
              </a>
            </tr>
          </thead>
          <tbody></tbody>
        </table>
      </div>
      <div class = "d-flex justify-content-center">
            <a v-for = "clan in restoran" :href = "`/comment/${clan.id}`" :key = "clan.id" class = "btn btn-primary btn-lg"> 
            <td>
               Postavite Komentar!
            </td>
            </a>
            </div>
      <div class="d-flex justify-content-center">
        <router-link
          to="/create-restaurant"
          class="btn btn-primary btn-lg text-center"
          >Kreiraj novi?</router-link
        >
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";

export default {
  name: "RestoraniList",
  data: function () {
    return {
      restoran: {},
    };
  },
  mounted: function () {
    axios
      .get("http://localhost:8080/api/restorani/lista")
      .then((res) => {
        this.restoran = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style>
.link-primary {
  text-decoration: none;
}
</style>
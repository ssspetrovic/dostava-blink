<template>
  <section>
    <div class="container-fluid vh-100">
      <table class="table table-responsive table-borderless text-center">
        <thead>
          <tr>
            <th
              v-for="clan in restoran"
              :href="`/restoran/${clan.id}`"
              :key="clan.id"
            >
            <router-link :to="{path: '/restoran/' + restoran.id}" class="link-primary lead m-5 display-5">
                {{ clan.naziv }}
            </router-link>
            </th>
          </tr>
        </thead>
        <tbody>
          <td
            v-for="clan in restoran"
            :href="`/comment/${clan.id}`"
            :key="clan.id"
            class=""
          >
            <p>
              Lorem ipsum dolor, sit amet consectetur adipisicing elit. Nesciunt
              quisquam suscipit repellat alias laudantium quas, corrupti ad
              iusto quo nam cum blanditiis quae voluptates illo hic magni
              pariatur id dolorum? Ab quasi officiis, quo reiciendis esse autem
              itaque voluptatibus est non veritatis omnis ratione, aliquid,
              fugiat illum praesentium sit voluptates.
            </p>
            {{ clan.komentar }}
          </td>
        </tbody>
      </table>
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
  methods: {
    redirect: function () {
        this.$router.push("/restoran/" + this.restoran.id);
    }
  }
};
</script>

<style>

.link-primary {
  text-decoration: none;
}
</style>
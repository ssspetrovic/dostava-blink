<template>
  <section>
    <div class="container vh-100" id="w30">
        <form>
          <!-- <div class="d-inline row text-center my-2 mt-0">
          <img id="logo-prijava" src="../assets/blink-logo.svg" alt="" />
        </div> -->
          <div class="display-5 text-center m-4">Kreiranje artikla</div>
          <div class="mb-3">
            <label for="id-restorana" class="col-form-label"
              >ID restorana:</label
            >
            <input
              v-model.number="novaPorudzbinaDto.idRestorana"
              type="text"
              class="form-control form-control-lg"
              id="id-restorana"
              placeholder="Uneti ID restorana"
            />
          </div>
          <div class="mb-3">
            <label for="id-artikla" class="col-form-label">ID artikla:</label>
            <input
              v-model.number="novaPorudzbinaDto.novePorudzbine[0].id"
              type="text"
              class="form-control form-control-lg"
              id="id-artikla"
              placeholder="Uneti ID artikla"
            />
          </div>
          <div class="mb-3">
            <label for="kolicina" class="col-form-label">Količina:</label>
            <input
              v-model="novaPorudzbinaDto.novePorudzbine[0].kolicina"
              type="text"
              class="form-control form-control-lg"
              id="kolicina"
              placeholder="Uneti količinu"
            />
          </div>

          <div class="text-center mb-3">
            <button
              v-on:click="submit()"
              type="button"
              class="btn btn-lg btn-primary mt-4 px-5"
              id="prijava-potvrdi"
            >
              Potvrdi
            </button>
          </div>
        </form>
    </div>
  </section>
</template>

<script>
import axios from "axios";

export default {
  name: "CreateOrder",
  data: function () {
    return {
      novaPorudzbinaDto: {
        idRestorana: "",
        novePorudzbine: [
          {
            id: "",
            kolicina: "",
          },
        ],
      },
    };
  },
  methods: {
    submit: function () {
      axios
        .post(
          `http://localhost:8080/api/porudzbine?korisnickoIme=aaa`,
          this.novaPorudzbinaDto
        )
        .then((res) => {
          console.log(res);
          this.$router.push("/");
        })
        .catch((err) => {
          console.log(err);
          alert("Something went wrong!");
        });
    },
  },
  mounted() {},
};
</script>

<style>
</style>
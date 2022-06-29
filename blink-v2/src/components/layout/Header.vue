<template>
  <nav
    class="
      navbar navbar-expand-lg
      py-3
      sticky-top
      navbar-light
      shadow
      p-3
      mb-5
      bg-white
      rounded
    "
  >
    <div class="container">
      <router-link to="/" class="navbar-brand">
        <img src="../../assets/blink-logo.svg" alt="" width="100" />
      </router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navmenu">
        <form class="d-flex mt-3 mb-1" role="search">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Pretraži"
          />
          <router-link
            to="/search-bar"
            class="btn btn-outline-primary"
            type="submit"
            >Pretraga</router-link
          >
        </form>
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <router-link
              to="/prijava-modal"
              class="nav-link"
              data-bs-toggle="modal"
              >Prijavi se
            </router-link>
          </li>
          <li class="nav-item">
            <router-link
              to="/registracija-modal"
              class="nav-link"
              data-bs-toggle="modal"
              >Registruj se
            </router-link>
          </li>
          <li class="nav-item">
            <router-link to="/" class="nav-link">Korpa</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "HeadeR"
};
</script>

<style></style>

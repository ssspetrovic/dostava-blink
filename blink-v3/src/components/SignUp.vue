<template>
  <section class="">
    <div class="container-fluid h-custom">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-md-9 col-lg-6 col-xl-5">
          <img
            src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
            class="img-fluid"
            alt="Sample image"
          />
        </div>
        <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
          <form>
            <div class="d-inline row text-center my-2 mt-0">
              <img id="logo-prijava" src="../assets/blink-logo.svg" alt="" />
            </div>
            <div class="mb-3">
              <label for="ime" class="col-form-label"> Ime: </label>
              <input type="text" class="form-control" placeholder="Unesi ime" id="ime" />
            </div>
            <div class="mb-3">
              <label for="prezime" class="col-form-label"> Prezime: </label>
              <input type="text" class="form-control" id="prezime" />
            </div>
            <div class="mb-3">
              <label for="korisnicko-ime" class="col-form-label">
                Korisničko ime:
              </label>
              <input type="text" class="form-control" id="korisnicko-ime" />
            </div>
            <div class="mb-3">
              <label for="lozinka" class="col-form-label"> Lozinka: </label>
              <input type="password" class="form-control" id="lozinka" />
            </div>
            <div class="mb-3">
              <label class="col-form-label" for="radio-pol">Pol:</label>
              <div id="radio-pol">
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="pol"
                    id="pol-m"
                  />
                  <label class="form-check-label" for="pol-m"> Muško </label>
                </div>
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="pol"
                    id="pol-z"
                  />
                  <label class="form-check-label" for="pol-z"> Žensko </label>
                </div>
              </div>
            </div>
            <div class="mb-3">
              <label for="datum" class="col-form-label">Datum rođenja:</label>
              <input type="date" class="form-control" id="datum" />
            </div>
            <div class="text-center mb-3">
              <button
                type="button"
                class="btn btn-lg btn-primary mt-4 px-5"
                id="prijava-potvrdi"
              >
                Potvrdi
              </button>
              <div>
                <p class="small fw-bold mt-2 pt-1 mb-0">
                  Već imaš nalog?
                  <router-link
                    to="/sign-up"
                    id="registruj-se-link"
                    class="primary-link"
                    >Prijavi se</router-link
                  >
                </p>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
</script>
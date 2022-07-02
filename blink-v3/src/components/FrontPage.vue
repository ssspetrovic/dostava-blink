<template>
  <section class="p-5 text-left text-sm-start">
    <div class="container">
      <div>
        <h1 class="text-center">Gladan si?</h1>
        <p class="lead my-4">
          Ne brini se! Kreirali smo
          <strong class="text-primary"
            ><router-link class="o-nama" to="o-nama">Blink</router-link></strong
          >
          da bismo na brz i efikasan način rešili problem - dok trepneš okom. Sa
          preko X restorana sa kojima sarađujemo, ponuda hrane i pića svuči
          skoro pa nerealna! Probirljiv si i nijedna ponuda iz predstavljenih
          restorana ti se ne dopada? Nikakav problem. Vršimo isporuku čak i iz
          restorana koji nisu navedeni, samo je potrebno da nas kontaktirate i
          ubrzo ćemo rešiti stvar.
        </p>
      </div>
      <div
        id="carouselExampleIndicators"
        class="carousel slide py-3"
        data-bs-ride="true"
      >
        <div class="carousel-indicators">
          <button
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide-to="0"
            class="active"
            aria-current="true"
            aria-label="Slide 1"
          ></button>
          <button
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide-to="1"
            aria-label="Slide 2"
          ></button>
          <button
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide-to="2"
            aria-label="Slide 3"
          ></button>
          <button
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide-to="3"
            aria-label="Slide 4"
          ></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img
              src="../assets/hamburger.svg"
              class="d-block w-100"
              alt="..."
            />
          </div>
          <div class="carousel-item">
            <img src="../assets/pizza.svg" class="d-block w-100" alt="..." />
          </div>
          <div class="carousel-item">
            <img src="../assets/tortilla.svg" class="d-block w-100" alt="..." />
          </div>
          <div class="carousel-item">
            <img
              src="../assets/fruit-salad.svg"
              class="d-block w-100"
              alt="..."
            />
          </div>
        </div>
        <button
          class="carousel-control-prev"
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide="prev"
        >
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button
          class="carousel-control-next"
          type="button"
          data-bs-target="#carouselExampleIndicators"
          data-bs-slide="next"
        >
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
      <div class="text-center pt-3">
        <router-link to="/restorani" class="btn btn-primary btn-lg"
          >Pogledaj ponudu</router-link
        >
      </div>
    </div>
  </section>
  <section class="p-5 text-left text-sm-start">
    <div class="container">
      <p class="lead mb-5">Lorem ipsum dolor sit amet consectetur adipisicing elit. Nihil at ipsam aspernatur nam recusandae, quibusdam facere assumenda eveniet, aperiam labore est? Eligendi dignissimos alias et adipisci incidunt aspernatur soluta inventore facilis atque eum facere commodi itaque odit explicabo, nam aperiam? Exercitationem ipsum delectus quod minima saepe est suscipit soluta quidem?</p>
      <div class="row row-cols-1 row-cols-md-3 g-4">
        <div class="col">
          <div class="card h-100 bg-light shadow p-3 mb-5 bg-white rounded">
            <img
              src="../assets/serbia-flag.svg"
              class="card-img-top shadow bg-white rounded"
              alt="..."
            />
            <div class="card-body pb-0">
              <h5 class="card-title">Srbija</h5>
              <p class="card-text">
                This is a wider card with supporting text below as a natural
                lead-in to additional content. This content is a little bit
                longer.
              </p>
            </div>
            <div class="card-footer text-center" id="card-foot">
                <router-link class="btn btn-primary btn-lg px-5 " to="/restorani">Istraži</router-link>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card h-100 bg-light shadow p-3 mb-5 bg-white rounded">
            <img
              src="../assets/italy-flag.svg"
              class="card-img-top shadow bg-white rounded"
              alt="..."
            />
            <div class="card-body pb-0">
              <h5 class="card-title">Meksiko</h5>
              <p class="card-text">
                This is a wider card with supporting text below as a natural
                lead-in to additional content. This content is a little bit
                longer.
              </p>
            </div>
            <div class="card-footer text-center" id="card-foot">
                <router-link class="btn btn-primary btn-lg istrazi px-5" to="/restorani">Istraži</router-link>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="card h-100 bg-light shadow p-3 mb-5 bg-white rounded">
            <img
              src="../assets/thailand-flag.svg"
              class="card-img-top shadow bg-white rounded"
              alt="..."
            />
            <div class="card-body pb-0">
              <h5 class="card-title">Tajland</h5>
              <p class="card-text">
                This is a wider card with supporting text below as a natural
                lead-in to additional content. This content is a little bit
                longer..
              </p>
            </div>
            <div class="card-footer text-center" id="card-foot">
                <router-link class="btn btn-primary btn-lg istrazi px-5" to="/restorani">Istraži</router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "FrontPage",
};
</script>

<style>
.o-nama {
  text-decoration: none;
}

.istrazi {
    text-decoration: none;
}

#card-foot {
  border: none;
  background: white;
}
</style>
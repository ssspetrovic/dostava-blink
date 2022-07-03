import { createRouter, createWebHistory } from "vue-router";
import FrontPage from "../components/FrontPage.vue";
import Login from "../components/Login.vue";
import SignUp from "../components/SignUp.vue";
import Restoran from "../components/Restoran.vue";
import Restorani from "../components/RestoraniList.vue";
import Korisnik from "../components/Korisnik.vue"
import IspisKorisnik from "../components/IspisKorisnik.vue";

const routes = [
  {
    path: "/",
    name: "front-page",
    component: FrontPage,
  },
  {
    path: "/prijava",
    name: "prijava",
    component: Login,
  },
  {
    path: "/sign-up",
    name: "sign-up",
    component: SignUp,
  },
  {
    path: "/restorani",
    name: "restorani",
    component: Restorani,
  },
  {
    path: "/restoran/:id",
    name: "restoran",
    component: Restoran
  },
  {
    path: "/korisnici",
    name: "korisnici",
    component: Korisnik
  },
  {
    path: "/korisnik/:id",
    name: "korisnik",
    component: IspisKorisnik
  }

];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;

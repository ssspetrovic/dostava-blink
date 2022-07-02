import { createRouter, createWebHistory } from 'vue-router'
import FrontPage from '../components/FrontPage.vue'
import Login from '../components/Login.vue'
import HomeView from "../views/HomeView.vue";
import SignUp from "../components/SignUp.vue"


const routes = [
  {
    path: '/',
    name: 'front-page',
    component: FrontPage
  },
  {
    path: '/prijava',
    name: 'prijava',
    component: Login
  },
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue")
  },
  {
    path: "/sign-up",
    name: "sign-up",
    component: SignUp
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

import { createRouter, createWebHistory } from 'vue-router'
import ListaPorudzbina from '../components/ListaPorudzbina.vue'
import Restoran from '../components/Restoran.vue'
import SignUp from '../components/SignUp'
import Login from '../components/Login.vue'
import FrontPage from '../components/FrontPage.vue'
import IspisKorisnik from '../components/IspisKorisnik.vue'
import RestoraniList from '../components/RestoraniList.vue'
import SearchBar from '../components/SearchBar.vue'
import CreateRestaurant from '../components/CreateRestaurant.vue'
import SetManager from '../components/SetManager.vue'
import RestaurantManager from '../components/RestaurantManager.vue'
import Korisnik from '../components/Korisnik.vue'
import Artikal from '../components/Artikal.vue'
import Comment from '../components/Comment.vue'

const routes = [
    {
        path: '/',
        name: 'front-page',
        component: FrontPage
    },
    {
        path: '/o-nama',
        name: 'o-nama',
        component: () => import('../components/ONama.vue')
    },
    {
        path: '/korisnici',
        name: 'korisnici',
        component: Korisnik
    },
    {
        path: '/sign-up',
        name: 'sign-up',
        component: SignUp
    },
    {
        path: '/lista-porudzbina',
        name: 'lista-porudzbina',
        component: ListaPorudzbina
    },
    {
        path: '/restoran/:id',
        name: 'restoran',
        component: Restoran
    },
    {
        path: '/korisnik/:id',
        name: 'korisnik',
        component: IspisKorisnik
    },
    {
        path: '/prijava',
        name: 'prijava',
        component: Login
    },
    {
        path: '/restorani',
        name: 'restorani',
        component: RestoraniList
    },
    {
        path: '/search-bar',
        name: 'search-bar',
        component: SearchBar
    },
    {
        path: '/create-restaurant',
        name: 'create-restaurant',
        component: CreateRestaurant
    },
    {
        path: '/set-manager',
        name: 'set-manager',
        component: SetManager
    },
    {
        path: '/set-manager',
        name: 'restaurant-manager',
        component: RestaurantManager
    },
    {
        path: '/artikal/:id',
        name: 'artikal',
        component: Artikal
    },
    {
        path: '/comment/:id',
        name: 'comment',
        component: Comment
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router


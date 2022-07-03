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
import CreateManager from '../components/CreateManager.vue'
import CreateDostavljac from '../components/CreateManager.vue'
import CreateArticle from '../components/CreateArticle.vue'
import AllManagers from '../components/AllManagers.vue'
import EditProfile from '../components/EditProfile.vue'
import EditArticle from '../components/EditArticle.vue'
import Korpa from '../components/Korpa.vue'
import Search from '../components/Search.vue'
import RestoranKorpa from '../components/RestoranKorpa.vue'
import CreateOrder from '../components/CreateOrder.vue'

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
    },
    {
        path: '/create-manager',
        name: 'create-manager',
        component: CreateManager
    },
    {
        path: '/create-dostavljac',
        name: 'create-dostavljac',
        component: CreateDostavljac
    },
    {
        path: '/create-article',
        name: 'create-article',
        component: CreateArticle
    },
    {
        path: '/all-managers',
        name: '/all-managers',
        component: AllManagers
    },
    {
        path: '/edit-profile',
        name: 'edit-profile',
        component: EditProfile
    },
    {
        path: '/edit-article/:id',
        name: 'edit-article',
        component: EditArticle
    },
    {
        path: '/korpa',
        name: 'korpa',
        component: Korpa
    },
    {
        path: '/search',
        name: 'search',
        component: Search
    },
    {
        path: '/restoran-korpa',
        name: 'restoran-korpa',
        component: RestoranKorpa
    },
    {
        path: '/create-order',
        name: 'create-order',
        component: CreateOrder
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router


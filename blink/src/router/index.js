import { createRouter, createWebHistory } from 'vue-router'
import ListaKorisnikaView from '../views/ListaKorisnikaView.vue'
import ListaPorudzbinaView from '../views/ListaPorudzbinaView.vue'
import RestoranView from '../views/RestoranView.vue'
import ListaSvihKorisnikaView from '../views/ListaSvihKorisnikaView.vue'
import SignUp from '../components/SignUp'
import Login from '../components/Login.vue'
import FrontPage from '../components/FrontPage.vue'
import IspisKorisnik from '../components/IspisKorisnik.vue'
import RestoraniList from '../components/RestoraniList.vue'
import SearchBar from '../components/SearchBar.vue'


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
        component: ListaKorisnikaView
    },
    {
        path: '/sign-up',
        name: 'sign-up',
        component: SignUp
    },
    {
        path: '/lista-porudzbina',
        name: 'lista-porudzbina',
        component: ListaPorudzbinaView
    },
    {
        path: '/restoran/:id',
        name: 'restoran',
        component: RestoranView
    },
    {
        path: '/korisnik/:id',
        name: 'korisnik',
        component: IspisKorisnik
    },
    {
        path: '/svi-korisnici',
        name: 'svi-korisnici',
        component: ListaSvihKorisnikaView
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
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router


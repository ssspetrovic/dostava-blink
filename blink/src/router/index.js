import { createRouter, createWebHistory } from 'vue-router'
import JedanKorisnikView from '../views/JedanKorisnikView.vue'
import ListaKorisnikaView from '../views/ListaKorisnikaView.vue'
import ListaPorudzbinaView from '../views/ListaPorudzbinaView.vue'
import RestoranView from '../views/RestoranView.vue'
import ListaSvihKorisnikaView from '../views/ListaSvihKorisnikaView.vue'
import SignUp from '../components/SignUp'
import Login from '../components/Login.vue'
import FrontPage from '../components/FrontPage.vue'


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
    // {
    //     path: '/prijava',
    //     name: 'prijava',
    //     component: PrijavaView
    // },
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
        path: '/jedan-korisnik/:id',
        name: 'jedan-korisnik',
        component: JedanKorisnikView
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
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router


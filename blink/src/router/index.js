import { createRouter, createWebHistory } from 'vue-router'
import GlavnaStranaView from '../views/GlavnaStranaView.vue'
import JedanKorisnikView from '../views/JedanKorisnikView.vue'
import ListaKorisnikaView from '../views/ListaKorisnikaView.vue'
import ListaPorudzbinaView from '../views/ListaPorudzbinaView.vue'
import PrijavaView from '../views/PrijavaView.vue'
import RestoranView from '../views/RestoranView.vue'
import ListaSvihKorisnikaView from '../views/ListaSvihKorisnikaView.vue'
import SignUp from '../components/SignUp'
//import Login from './components/Login.vue'


const routes = [
    {
        path: '/',
        name: 'glavna-strana',
        component: GlavnaStranaView
    },
    {
        path: '/o-nama',
        name: 'o-nama',
        component: () => import('../views/ONamaView.vue')
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
        path: '/prijava',
        name: 'prijava',
        component: PrijavaView
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
        path: '/jedan-korisnik/:id',
        name: 'jedan-korisnik',
        component: JedanKorisnikView
    },
    {
        path: '/svi-korisnici',
        name: 'svi-korisnici',
        component: ListaSvihKorisnikaView
    },
    // {
    //     path: '/prijava',
    //     name: 'prijava',
    //     component: Login
    // },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router


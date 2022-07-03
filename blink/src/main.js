import { createApp } from 'vue'
import { createStore } from 'vuex'
import App from './App.vue'
// I'm also using a router
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'


const app = createApp(App);

// Create a store for our to do list items
const store = createStore({
    state () {
        return {
            korisnik: 
                {id: "", korisnickoIme: "", lozinka: "" }
            
        }
    },
    getters: {
        korisnik (state) {
            // state variable contains our state data
            return state.korisnik;
        }
    },
    mutations: {
        addKorisnik(state, dodajKorisnika) {
            if(typeof dodajKorisnika.korisnickoIme == 'string' && typeof dodajKorisnika.lozinka == 'string') {
                state.korisnik = {
                    id: dodajKorisnika.id,
                    korisnickoIme: dodajKorisnika.korisnickoIme,
                    lozinka: dodajKorisnika.lozinka
                }
            }
        }
    }
});

// We can chain use() functions, so our app is now using a router and our Vuex store
app.use(router).use(store).mount('#app')



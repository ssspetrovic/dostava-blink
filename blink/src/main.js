import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'
import "vueperslides/dist/vueperslides.css";

createApp(App).use(router).mount('#app')


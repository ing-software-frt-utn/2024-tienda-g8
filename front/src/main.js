import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './index.css';
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faUserSecret, faPenToSquare, faTrashCan, faTrashAlt } from '@fortawesome/free-solid-svg-icons'
import store from './store'

library.add(faUserSecret, faPenToSquare, faTrashCan, faTrashAlt)

createApp(App).component('font-awesome-icon', FontAwesomeIcon).use(router).use(store).mount('#app');


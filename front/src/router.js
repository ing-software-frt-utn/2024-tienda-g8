import { createRouter, createWebHistory } from 'vue-router';
import Home from './pages/Home.vue';
import RealizarVenta from './pages/RealizarVenta.vue';

const routes = [
  { path: '/', component: Home },
  { path: '/realizar-venta', component: RealizarVenta },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;

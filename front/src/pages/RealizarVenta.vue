<template>
  <div class="flex flex-col min-h-screen overflow-hidden">

    <Header />

    <!-- Aquí está el buscador -->
    <BuscardorVenta />

  </div>
</template>
  
<script setup>
import Header from '../components/Header.vue';
import BuscardorVenta from '../components/BuscardorVenta.vue';
import { ref } from 'vue'
</script>
  
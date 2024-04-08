<template>
    <div className="flex flex-col min-h-screen overflow-hidden">

        <!-- Header -->
        <Header />

        <!-- Content -->
        <div class="grow flex flex-col lg:flex-row">
            <main class="grow">

                <Table />
    
            </main>
        </div>

    </div>
</template>

<script setup>
import Header from '../components/Header.vue'
import Table from '../components/Table.vue'
import { ref } from 'vue'
</script>
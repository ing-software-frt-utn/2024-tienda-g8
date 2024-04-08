<template>
    <div class="relative max-w-full mx-auto px-4 sm:px-6 pt-4 md:pt-0">
        <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
            <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                <thead class="text-xs text-gray-700 uppercase bg-indigo-300 dark:bg-gray-700 dark:text-gray-400">
                    <tr>
                        <th scope="col" class="px-6 py-3 font-bold">
                            Código
                        </th>
                        <th scope="col" class="px-6 py-3 font-bold">
                            Artículo
                        </th>
                        <th scope="col" class="px-6 py-3 font-bold">
                            Categoría
                        </th>
                        <th scope="col" class="px-6 py-3">
                            Precio
                        </th>
                        <th scope="col" class="px-6 py-3">
                            Acciones
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="article in articles" :key="article.id"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-indigo-100 dark:hover:bg-gray-600">
                        <th scope="row" class="px-6 py-4 font-medium text-gray-700 whitespace-nowrap dark:text-white">
                            {{ article.codigo }}
                        </th>
                        <td class="px-6 py-4 font-medium text-gray-700">
                            {{ article.marca.descripcion }} - {{ article.descripcion }}
                        </td>
                        <td class="px-6 py-4 font-medium text-gray-700">
                            {{ article.categoria.descripcion }}
                        </td>
                        <td class="px-6 py-4 font-medium text-green-500">
                            ${{ article.precioDeVenta }}
                        </td>
                        <td class="px-6 py-4 font-medium text-gray-700">
                            <font-awesome-icon
                                @click="openEditModal(article.id, article.brand, article.name, article.size, article.color, article.price)"
                                class="pr-2" :icon="['fas', 'pen-to-square']"
                                :style="{ fontSize: '20px', color: '#fbbf24', cursor: 'pointer' }" />
                            <font-awesome-icon @click="toggleModal(article.id, article.brand, article.name)"
                                :icon="['fas', 'trash-can']"
                                :style="{ fontSize: '20px', color: '#ef4444', cursor: 'pointer' }" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const url = "http://localhost:8080/tfib/articulos";
const articles= ref ([]);

const fetchDataWithAxios = async () => {
    try {
        const response = await axios.get(url);
        console.log('Datos recibidos:', response.data);
        articles.value=response.data;
    } catch (error) {
        console.error('Hubo un problema al obtener los datos:', error.message);
    }
};
fetchDataWithAxios();


const showModal = ref(false);
const articleToDelete = ref({});

const toggleModal = (articleId, brand, name) => {
    articleToDelete.value = { id: articleId, brand, name };
    showModal.value = true;
};

const closeModal = () => {
    showModal.value = false;
};

const deleteArticle = () => {
    const index = articles.findIndex((article) => article.id === articleToDelete.value.id);
    if (index !== -1) {
        articles.splice(index, 1);
    }
    closeModal();
};

const showEditModal = ref(false);
const editedArticle = ref({});

const openEditModal = (articleId, brand, name, size, color, price) => {
    editedArticle.value = { id: articleId, brand, name, size, color, price };
    showEditModal.value = true;
};

const closeEditModal = () => {
    showEditModal.value = false;
};

const saveArticleChanges = () => {
    const index = articles.findIndex((article) => article.id === editedArticle.value.id);
    if (index !== -1) {
        articles[index] = editedArticle.value;
    }
    closeEditModal();
};
/*
const url = "http://localhost:8080/tfib/empleados";

const fetchDataWithAxios = async () => {
    try {
        const response = await axios.get(url);
        console.log('Datos recibidos:', response.data);
    } catch (error) {
        console.error('Hubo un problema al obtener los datos:', error.message);
    }
};

fetchDataWithAxios();
*/
</script>
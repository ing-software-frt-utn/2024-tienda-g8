<template>
    <div class="max-w-6xl mx-auto sm:px-6 sm:pt-6 pb-24 md:pb-0">
        <div class="flex items-center justify-between h-16 md:h-20">
            <nav
                class="bg-white dark:bg-gray-900 fixed w-full z-20 top-0 start-0 border-b border-gray-200 dark:border-gray-600">
                <div class="max-w-screen-xl flex items-center justify-between mx-auto p-4">
                    <div class="flex items-center">
                        <a href="https://vitejs.dev" target="_blank">
                            <img src="/vite.svg" class="logo h-9" alt="Vite logo" width="70px" />
                        </a>
                        <a href="https://vuejs.org/" target="_blank">
                            <img src="../assets/vue.svg" class="logo vue h-8" alt="Vue logo" width="70px" />
                        </a>
                    </div>
                    <div class="flex md:order-2 space-x-3 md:space-x-0 rtl:space-x-reverse">
                        <!-- <button type="button"
                            class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-4 py-2 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Get
                            started</button> -->
                    </div>
                    <div class="items-center justify-between w-full md:flex md:w-auto md:order-1" id="navbar-sticky">
                        <ul
                            class="flex flex-col p-4 md:p-0 mt-4 font-medium border border-gray-100 rounded-lg bg-gray-50 md:space-x-8 rtl:space-x-reverse md:flex-row md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
                            <li class="ml-6">
                                <router-link to="/"
                                    class="font-medium text-base text-slate-600 hover:text-slate-700 transition duration-150 ease-in-out">
                                    Artículos
                                </router-link>
                            </li>
                            <li class="ml-6">
                                <router-link to="/realizar-venta"
                                    class="font-medium text-base text-slate-600 hover:text-slate-700 transition duration-150 ease-in-out">
                                    Realizar ventas
                                </router-link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

        </div>
    </div>
</template>

<script setup>

</script>
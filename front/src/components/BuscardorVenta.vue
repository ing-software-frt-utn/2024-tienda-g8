<template>
    <div class="grid grid-cols-6 gap-4">

        <div class="col-span-4 pl-12 pt-4">
            <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
                <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                    <thead class="text-xs text-gray-700 uppercase bg-indigo-300 dark:bg-gray-700 dark:text-gray-400">
                        <tr>
                            <th scope="col" class="px-6 py-3 font-bold">
                                Artículo
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Talle
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Color
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Cantidad
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Total
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="article in articles" :key="article.id"
                            class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-indigo-100 dark:hover:bg-gray-600">
                            <th scope="row"
                                class="px-6 py-4 font-medium text-gray-700 whitespace-nowrap dark:text-white">
                                {{ article.marca }} - {{ article.descripcion }}
                            </th>
                            <td class="px-6 py-4 font-medium text-gray-700">
                                {{ article.talle.descripcion }}
                            </td>
                            <td class="px-6 py-4 font-medium text-gray-700">
                                {{ article.color.descripcion }}
                            </td>
                            <td class="px-12 py-4 font-medium">
                                {{ article.cantidad }}
                            </td>
                            <td class="px-6 py-4 font-medium">
                                ${{ article.totalVenta }}
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div class="mt-4 text-xl font-bold text-green-600 flex justify-between pr-8 pb-4 pl-4">
                    <fwb-button @click="showModal" type="submit" v-if="totalAcumulado > 0"
                        class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Continuar</fwb-button>
                    <div class="mt-4 text-xl font-bold text-green-600">
                        Total: ${{ totalAcumulado }}
                    </div>
                </div>

            </div>
        </div>
        <div class="col-span-2 pr-12">
            <div class="max-lg:grow flex flex-col w-full lg:w-2/5 lg:mr-auto">
                <div class="w-full max-w-xl mx-auto flex flex-col justify-start px-4 sm:px-6 pb-20 lg:pt-4">
                    <div class="relative overflow-x-auto shadow-md border-2 sm:rounded-lg">

                        <form class="pt-4 pr-4 pl-4 pb-4" @submit.prevent>
                            <label class="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Buscar</label>
                            <div class="relative">
                                <fwb-input type="search" id="default-search" v-model="descripcion"
                                    @input="descripcion && buscarArticulo()" placeholder="Escriba nombre del artículo">
                                </fwb-input>
                                <div v-if="resultados.length > 0"
                                    class="mt-2 w-full absolute left-0 bg-white dark:bg-gray-700 border border-gray-300 rounded-md z-10">
                                    <ul>
                                        <li v-for="resultado in resultados" :key="resultado.id"
                                            @click="seleccionarArticulo(resultado)"
                                            class="p-2 cursor-pointer hover:bg-gray-100 dark:hover:bg-gray-800 text-black">
                                            {{ resultado.descripcion }}
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </form>

                        <form class="max-w-md mx-auto" @submit.prevent="agregarArticulo">
                            <div class="relative z-0 w-full mb-5 group pl-4 pr-4">
                                <input type="text" name="Campo_Nombre_articulo" id="Campo_Nombre_articulo"
                                    class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                    placeholder=" " required />
                                <label
                                    class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 origin-[0] rtl:peer-focus:left-auto peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Nombre
                                    artículo</label>
                            </div>
                            <div class="relative z-0 w-full mb-5 group pl-4 pr-4">
                                <input type="text" name="Campo_Marca" id="Campo_Marca"
                                    class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                    placeholder=" " required />
                                <label
                                    class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Marca</label>
                            </div>
                            <div class="grid md:grid-cols-2 md:gap-6">
                                <div class="relative z-0 w-full mb-5 group pl-4 pr-4">

                                    <label
                                        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Color</label>
                                    <select id="Select_Color" v-model="selectedColor"
                                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                        <option v-for="color in colores" :key="color.id" :value="color">{{
                            color.descripcion
                        }}
                                        </option>
                                    </select>
                                </div>
                                <div class="relative z-0 w-full mb-5 group pl-4 pr-4">
                                    <label
                                        class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Talle</label>
                                    <select id="Select_Talle" v-model="selectedTalle"
                                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                                        <option v-for="talle in talles" :key="talle.id" :value="talle">{{
                            talle.descripcion
                        }}
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="grid md:grid-cols-2 md:gap-6">
                                <div class="relative z-0 w-full mb-5 group lg:col-start-2 pl-4 pr-4">
                                    <input type="text" name="floating_last_name" id="floating_last_name"
                                        class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                                        placeholder=" " />
                                    <label
                                        class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">
                                        Cantidad
                                        <span class="text-lg font-bold cantidad-stock"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="flex justify-end pb-4 pl-4 pr-4">
                                <button type="submit"
                                    class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Agregar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Modal de cliente -->
    <fwb-modal v-if="isShowModal" @close="closeModal" persistent>
        <template #header>
            <div class="flex items-center text-lg font-bold">
                Datos del cliente
            </div>
        </template>

        <template #body>
            <form class="pb-1 pr-4 pl-4" @submit.prevent="buscarCliente">
                <div class="relative">
                    <fwb-input type="search" id="clienteSearch" v-model="clienteSearch"
                        placeholder="Escriba cuit del cliente"></fwb-input>
                </div>
            </form>
            <div v-if="clienteNoEncontrado" class="text-red-500 text-center">No se encontró un cliente con ese CUIT.
            </div>
            <div v-if="clienteEncontrado" class="text-green-500 text-center">Cliente encontrado.</div>

            <form @submit.prevent="agregarArticulo">
                <div class="grid md:grid-cols-2 grid-cols-2 md:gap-2 pt-6">
                    <div class="relative z-0 w-full mb-5 group pl-4 pr-4 col-span-1">
                        <input type="text" name="clienteNombre" id="clienteNombre" v-model="clienteNombre"
                            class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" " required disabled />
                        <label
                            class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 origin-[0] rtl:peer-focus:left-auto peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Nombre
                            o Razón Social</label>
                    </div>

                    <div class="relative z-0 w-full mb-5 group pl-4 pr-4 col-span-1">
                        <input type="text" name="clienteCuit" id="clienteCuit" v-model="clienteCuit"
                            class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" " required disabled />
                        <label for="clienteCuit"
                            class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 origin-[0] rtl:peer-focus:left-auto peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">CUIT</label>
                    </div>

                    <div class="relative z-0 w-full group pl-4 pr-4 col-span-1">
                        <input type="text" name="clienteDireccion" id="clienteDireccion" v-model="clienteDireccion"
                            class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" " required disabled />
                        <label
                            class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 origin-[0] rtl:peer-focus:left-auto peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Dirección</label>
                    </div>
                    <div class="relative z-0 w-full group pl-4 pr-4 col-span-1 pb-4">
                        <select id="condicionTributariaSeleccionada" v-model="condicionTributariaSeleccionada"
                            class="w-full bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                            <option v-for="condicionTributaria in condicionesTributariasLista"
                                :key="condicionTributaria.id" :value="condicionTributaria">{{
                            condicionTributaria.descripcion }}</option>
                        </select>
                    </div>
                    <div class="relative z-0 w-full group pl-4 pr-4 col-span-1"
                        v-if="condicionTributariaSeleccionada && (condicionTributariaSeleccionada.descripcion === 'Responsable inscripto' || condicionTributariaSeleccionada.descripcion === 'Monotributo')">
                        <input type="text" v-model="tipoFactura"
                            class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" " required disabled />
                        <label
                            class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 origin-[0] rtl:peer-focus:left-auto peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Tipo
                            de factura</label>
                    </div>
                    <div class="relative z-0 w-full group pl-4 pr-4 col-span-1" v-else>
                        <input type="text" v-model="tipoFactura"
                            class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer"
                            placeholder=" " required disabled />
                        <label
                            class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 origin-[0] rtl:peer-focus:left-auto peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Tipo
                            de factura</label>
                    </div>
                </div>
            </form>
        </template>

        <template #footer>
            <div class="flex justify-between">
                <fwb-button @click="closeModal" color="red">
                    Cancelar
                </fwb-button>
                <fwb-button @click="showModalPagos" color="green"
                    v-if="clienteCuit != '' && condicionTributariaSeleccionada != ''">
                    Continuar a pago
                </fwb-button>
            </div>
        </template>
    </fwb-modal>

    <!-- Modal de pagos -->
    <fwb-modal v-if="pagoShowModal" persistent>
        <template #header>

            <div class="flex items-center text-lg font-bold">
                Monto a pagar:
                <template v-if="!pagoSeleccionado">
                </template>
                <template v-else>
                    <label class="pl-2 text-2xl text-green-500" v-if="pagoSeleccionado === 'Efectivo'">{{ totalAcumulado
                        }}</label>
                    <label class="pl-2 text-2xl text-green-500" v-if="pagoSeleccionado === 'Tarjeta débito'">{{
                            totalAcumulado }}</label>
                    <label class="pl-2 text-2xl text-green-500" v-if="pagoSeleccionado === 'Tarjeta crédito'">{{
                            totalAcumulado }}</label>
                </template>
            </div>

        </template>

        <template #body>
            <form class="pb-1 pr-4 pl-4">
                <div class="relative">


                    <div class="max-w-sm mx-auto">
                        <label class="block mb-2 text-gray-900 dark:text-white text-lg font-bold pt-2">Tipo de
                            pago</label>
                        <select id="Select_Talle" label="Seleccione método de pago" v-model="pagoSeleccionado"
                            class="w-60 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                            <option v-for="metodoPago in metodosPagos" :key="metodoPago.id" :value="metodoPago">{{
                            metodoPago }}
                            </option>
                        </select>
                    </div>

                    <div v-if="pagoSeleccionado === 'Tarjeta débito' || pagoSeleccionado === 'Tarjeta crédito'">
                        <form class="max-w-sm mx-auto pt-4">
                            <div class="text-lg font-bold pb-2">Número de tarjeta </div>
                            <div class="relative">
                                <input type="text" id="numeroTarjeta" v-model="numeroTarjeta"
                                    @input="numeroTarjetaFormateador"
                                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pe-10 p-2.5  dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    placeholder="4242 4242 4242 4242"
                                    pattern="^[0-9]{4}\s?[0-9]{4}\s?[0-9]{4}\s?[0-9]{4}$" maxlength="19" required />
                                <div
                                    class="absolute inset-y-0 end-0 top-0 flex items-center pe-3.5 pointer-events-none">
                                    <svg fill="none" class="h-6 text-[#1434CB] dark:text-white" viewBox="0 0 36 21">
                                        <path fill="currentColor"
                                            d="M23.315 4.773c-2.542 0-4.813 1.3-4.813 3.705 0 2.756 4.028 2.947 4.028 4.332 0 .583-.676 1.105-1.832 1.105-1.64 0-2.866-.73-2.866-.73l-.524 2.426s1.412.616 3.286.616c2.78 0 4.966-1.365 4.966-3.81 0-2.913-4.045-3.097-4.045-4.383 0-.457.555-.957 1.708-.957 1.3 0 2.36.53 2.36.53l.514-2.343s-1.154-.491-2.782-.491zM.062 4.95L0 5.303s1.07.193 2.032.579c1.24.442 1.329.7 1.537 1.499l2.276 8.664h3.05l4.7-11.095h-3.043l-3.02 7.543L6.3 6.1c-.113-.732-.686-1.15-1.386-1.15H.062zm14.757 0l-2.387 11.095h2.902l2.38-11.096h-2.895zm16.187 0c-.7 0-1.07.37-1.342 1.016L25.41 16.045h3.044l.589-1.68h3.708l.358 1.68h2.685L33.453 4.95h-2.447zm.396 2.997l.902 4.164h-2.417l1.515-4.164z" />
                                    </svg>
                                </div>
                            </div>
                            <div class="text-lg font-bold pt-2">Vencimiento</div>
                            <div class="grid grid-cols-3 gap-4 pt-2">
                                <div class="col-span-1">
                                    <select id="mesSeleccionado" v-model="mesSeleccionado"
                                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pe-10 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                        required>
                                        <option value="">Mes</option>
                                        <option value="01">01 - Enero</option>
                                        <option value="02">02 - Febrero</option>
                                        <option value="03">03 - Marzo</option>
                                        <option value="04">04 - Abril</option>
                                        <option value="05">05 - Mayo</option>
                                        <option value="06">06 - Junio</option>
                                        <option value="07">07 - Julio</option>
                                        <option value="08">08 - Agosto</option>
                                        <option value="09">09 - Septiembre</option>
                                        <option value="10">1O - ctubre</option>
                                        <option value="11">11 - Noviembre</option>
                                        <option value="12">12 - Diciembre</option>
                                    </select>
                                </div>
                                <div class="col-span-1">
                                    <select id="añoSeleccionado" v-model="añoSeleccionado"
                                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full pe-10 p-2.5  dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                        required>
                                        <option value="">Año</option>
                                        <option value="24">2024</option>
                                        <option value="25">2025</option>
                                        <option value="26">2026</option>
                                        <option value="27">2027</option>
                                        <option value="28">2028</option>
                                        <option value="29">2029</option>
                                        <option value="30">2030</option>
                                        <option value="31">2031</option>
                                        <option value="32">2032</option>
                                        <option value="33">2033</option>
                                        <option value="34">2034</option>
                                    </select>
                                </div>
                            </div>
                            <div class="text-lg font-bold pt-2">Código de seguridad</div>
                            <div class="grid grid-cols-3 gap-4 pt-2">
                                <div class="col-span-1">
                                    <input type="number" id="cvvInput" aria-describedby="helper-text-explanation"
                                        v-model="cvvInput"
                                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                        placeholder="CVV" required />
                                </div>
                            </div>
                            <div class="text-lg font-bold pt-2">DNI</div>
                            <div class="grid grid-cols-3 gap-4 pt-2">
                                <div class="col-span-1">
                                    <input type="number" id="dniInput" aria-describedby="helper-text-explanation"
                                        v-model="dniInput"
                                        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                        placeholder="DNI" />
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </form>
        </template>

        <template #footer>
            <div class="flex justify-between">
                <div>
                    <fwb-button @click="closeModal" color="red" class="mr-4">
                        Cancelar
                    </fwb-button>
                    <fwb-button @click="reOpenClientModal" color="blue">
                        Volver a Cliente
                    </fwb-button>
                </div>
                <div>
                    <fwb-button @click="guardarDatos" color="green"
                        v-if="pagoSeleccionado === 'Efectivo' || (pagoSeleccionado !== '' && añoSeleccionado !== '' && mesSeleccionado !== '' && cvvInput !== '')">
                        Aceptar
                    </fwb-button>
                </div>
            </div>
        </template>
    </fwb-modal>
</template>



<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { FwbInput, FwbButton, FwbModal, FwbSelect, FwbAlert } from 'flowbite-vue';
import { watch } from 'vue';

/* Variables para cliente */
const clienteSearch = ref('');
const clienteId = ref();
const clienteNombre = ref('');
const clienteCuit = ref('');
const clienteDireccion = ref('');
const clienteNoEncontrado = ref(false);
const clienteEncontrado = ref(false);
const condicionTributariaSeleccionada = ref('');
const tipoFactura = ref('');

/* Variables para pagos */
const metodosPagos = ref([]);
const condicionesTributariasLista = ref([]);
const pagoSeleccionado = ref('');
const numeroTarjeta = ref("4507 9900 0000 4905");
const añoSeleccionado = ref('');
const mesSeleccionado = ref('');
const cvvInput = ref('');
const dniInput = ref('');

const descripcion = ref('');
const resultados = ref([]);
const articles = ref([]);
const colores = ref([]);
const talles = ref([]);
const selectedColor = ref(null);
const selectedTalle = ref(null);
const cantidadStock = ref(null);
const totalAcumulado = ref(0);
let codigoArticuloSeleccionado = null;
let precioVentaArticuloSeleccionado = 0;

const isShowModal = ref(false)
const pagoShowModal = ref(false)

function closeModal() {
    isShowModal.value = false;
    pagoShowModal.value = false;
}

async function showModal() {
    isShowModal.value = true;
    await metodosDePago();
    await condicionesTributarias();
}

function showModalPagos() {
    pagoShowModal.value = true;
    isShowModal.value = false;
}

function reOpenClientModal() {
    pagoShowModal.value = false;
    isShowModal.value = true;
}

watch(condicionTributariaSeleccionada, (newVal) => {
    if (newVal && (newVal.descripcion === 'Responsable inscripto' || newVal.descripcion === 'Monotributo')) {
        tipoFactura.value = 'Factura A';
    } else {
        tipoFactura.value = 'Factura B';
    }
});

const condicionesTributarias = async () => {
    try {
        const response = await axios.get(`http://localhost:8080/tfib/condiciontributaria`);
        condicionesTributariasLista.value = response.data;
    } catch (error) {
        console.error(error);
    }
};

const metodosDePago = async () => {
    try {
        const response = await axios.get(`http://localhost:8080/tfib/listarTipoPago`);
        metodosPagos.value = response.data;
    } catch (error) {
        console.error(error);
    }
};

function generarComprobante(datosCompletos) {
    const fecha = new Date().toLocaleDateString();
    const hora = new Date().toLocaleTimeString();

    let lineasDeVentaHTML = '';
    for (const linea in datosCompletos.lineasDeVenta) {
        lineasDeVentaHTML += `
            <tr>
                <td>${datosCompletos.lineasDeVenta[linea].descripcion}</td>
                <td>${datosCompletos.lineasDeVenta[linea].color}</td>
                <td>${datosCompletos.lineasDeVenta[linea].talle}</td>
                <td>${datosCompletos.lineasDeVenta[linea].cantidad}</td>
                <td>${datosCompletos.lineasDeVenta[linea].totalVenta}</td>
            </tr>
        `;
    }

    const comprobanteHTML = `
    <!DOCTYPE html>
    <html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Factura</title>
            <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .invoice-header {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
            position: relative;
        }
        .invoice-header::before {
            content: '';
            position: absolute;
            left: 50%;
            transform: translateX(-50%);
            width: 100%;
            top: 50%;
            border-top: 1px solid #ccc;
            z-index: -1;
        }
        .store-name,
        h1 {
            position: relative;
            z-index: 1;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .total {
            margin-top: 20px;
            text-align: right;
        }
        .tipo-factura {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff;
            padding: 5px 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
    </style>

    </head>
    <body>
        <div class="container">
            <div class="invoice-header">
                <h1>La tienda</h1>
                <div class="tipo-factura"><h3>${datosCompletos.tipoFacturaCliente}</h3></div>
                <h1>Factura</h1>
            </div>
            <p><strong>Fecha y Hora:</strong> ${new Date().toLocaleDateString('es-ES', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' })} <strong>Hora:</strong> ${new Date().toLocaleTimeString('es-ES')}</p>
            <hr>
            <p><strong>Nombre cliente:</strong> ${datosCompletos.datosCliente.nombreCliente} <strong>Dirección:</strong> ${datosCompletos.datosCliente.direccionCliente}</p>
            <p><strong>CUIT cliente:</strong> ${datosCompletos.datosCliente.cuitCliente}</p>
            <hr>
            <p><strong>Condición tributaria:</strong> ${datosCompletos.condicionTributaria.descripcion}</p>
            <hr>
            <p><strong>Tipo de pago:</strong> ${pagoSeleccionado.value}</p>
            <table>
                <thead>
                    <tr>
                        <th>Descripción</th>
                        <th>Color</th>
                        <th>Talle</th>
                        <th>Cantidad</th>
                        <th>Sub total</th>
                    </tr>
                </thead>
                <tbody>
                    ${lineasDeVentaHTML}
                </tbody>
            </table>
            <p class="total"><strong>Monto total:</strong> ${datosCompletos.montoTotal}</p>
        </div>
    </body>
    </html>
`;

    const ventanaComprobante = window.open("", "Comprobante de compra", "width=600,height=400");
    ventanaComprobante.document.write(comprobanteHTML);
}

/* Guardar datos de tarjeta */
function guardarDatos() {
    let datosCompletos;
    let tipoFacturaCliente = tipoFactura.value;

    if (pagoSeleccionado.value != 'Efectivo') {
        let numeroAformatear = numeroTarjeta.value;
        let numeroSinEspacios = numeroAformatear.replace(/\s+/g, '');

        const datosTarjeta = {
            numero: numeroSinEspacios,
            vencimiento: {
                mes: mesSeleccionado.value,
                año: añoSeleccionado.value
            },
            cvv: cvvInput.value,
            dni: dniInput.value
        };
        const montoTotal = totalAcumulado.value;
        const idCliente = clienteId.value;
        const cuitCliente = clienteCuit.value;
        const direccionCliente = clienteDireccion.value;
        const nombreCliente = clienteNombre.value;
        const condicionTributaria = condicionTributariaSeleccionada.value;

        const lineasDeVenta = articles.value;

        const lineasDeVentaObjeto = {};

        lineasDeVenta.forEach((item, index) => {
            lineasDeVentaObjeto[`lineaDeVenta${index}`] = {
                cantidad: item.cantidad,
                descripcion: item.descripcion,
                id: item.id,
                idColor: item.color.id,
                color: item.color.descripcion,
                idTalle: item.talle.id,
                talle: item.talle.descripcion,
                marca: item.marca,
                totalVenta: item.totalVenta
            };
        });

        const datosCliente = {
            idCliente: idCliente,
            cuitCliente: cuitCliente,
            direccionCliente: direccionCliente,
            nombreCliente: nombreCliente,
        };

        datosCompletos = {
            condicionTributaria: condicionTributaria,
            datosTarjeta: datosTarjeta,
            montoTotal: montoTotal,
            datosCliente: datosCliente,
            lineasDeVenta: lineasDeVentaObjeto,
            tipoFacturaCliente: tipoFacturaCliente,
            tipoPago: pagoSeleccionado.value
        };

        const datosCompletosJSON = JSON.stringify(datosCompletos);
        guardarVenta(datosCompletosJSON);
        setTimeout(() => {
            generarComprobante(datosCompletos);
        }, 2000);
        console.log(datosCompletosJSON);
    } else {
        const montoTotal = totalAcumulado.value;
        const idCliente = clienteId.value;
        const lineasDeVenta = articles.value;
        const cuitCliente = clienteCuit.value;
        const direccionCliente = clienteDireccion.value;
        const nombreCliente = clienteNombre.value;
        const condicionTributaria = condicionTributariaSeleccionada.value;
        const lineasDeVentaObjeto = {};

        lineasDeVenta.forEach((item, index) => {
            lineasDeVentaObjeto[`lineaDeVenta${index}`] = {
                cantidad: item.cantidad,
                descripcion: item.descripcion,
                id: item.id,
                idColor: item.color.id,
                color: item.color.descripcion,
                idTalle: item.talle.id,
                talle: item.talle.descripcion,
                marca: item.marca,
                totalVenta: item.totalVenta
            };
        });

        const datosCliente = {
            idCliente: idCliente,
            cuitCliente: cuitCliente,
            direccionCliente: direccionCliente,
            nombreCliente: nombreCliente,
        };

        datosCompletos = {
            condicionTributaria: condicionTributaria,
            montoTotal: montoTotal,
            idCliente: idCliente,
            lineasDeVenta: lineasDeVentaObjeto,
            datosCliente: datosCliente,
            tipoFacturaCliente: tipoFacturaCliente,
            tipoPago: pagoSeleccionado.value
        };

        const datosCompletosJSON = JSON.stringify(datosCompletos);
        guardarVenta(datosCompletosJSON);
        setTimeout(() => {
            generarComprobante(datosCompletos);
        }, 2000);
        console.log(datosCompletosJSON);
    }

}

const guardarVenta = async (datos) => {
    try {
        const response = await axios.post(`http://localhost:8080/tfib/realizarPago`, datos, {
            headers: {
                'Content-Type': 'application/json',
            },
        });
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
};

const buscarArticulo = async () => {
    try {
        const response = await axios.get(`http://localhost:8080/tfib/buscarPorDescripcion/${descripcion.value}`);
        resultados.value = response.data;
    } catch (error) {
        console.log('input limpio o error de BD');
    }
};

const seleccionarArticulo = (articulo) => {
    descripcion.value = articulo.descripcion;
    obtenerColoresYTallesPorDescripcion(articulo.descripcion);
    resultados.value = [];
    Campo_Nombre_articulo.value = articulo.descripcion;
    Campo_Marca.value = articulo.marca.descripcion;

    selectedColor.value = articulo.color;
    selectedTalle.value = articulo.talle;
    codigoArticuloSeleccionado = articulo.codigo;

    precioVentaArticuloSeleccionado = parseFloat(articulo.precioDeVenta).toFixed(2);
};

const buscarCliente = async () => {
    try {
        const response = await axios.get(`http://localhost:8080/tfib/buscarCliente/${clienteSearch.value}`);

        if (response.data) {
            const clienteEncontrado = response.data;
            clienteId.value = clienteEncontrado.id;
            clienteNombre.value = clienteEncontrado.nombre;
            clienteCuit.value = clienteEncontrado.cuit;
            clienteDireccion.value = clienteEncontrado.domicilio;
            clienteEncontrado.value = true;
            clienteNoEncontrado.value = false;
        } else {
            console.log('No existe cliente con ese cuit');
            clienteNoEncontrado.value = true;
            clienteEncontrado.value = false;
            clienteNombre.value = '';
            clienteCuit.value = '';
            clienteDireccion.value = '';
        }
    } catch (error) {
        console.log('Ocurrió un error al buscar el cliente:', error);
    }
};

const obtenerColoresYTallesPorDescripcion = (descripcion) => {
    axios.get(`http://localhost:8080/tfib/obtenerTallesPorDescripcion/${descripcion}`)
        .then((tallesResponse) => {
            talles.value = tallesResponse.data;
        })
        .catch((error) => {
            console.error(error);
        });

    axios.get(`http://localhost:8080/tfib/obtenerColoresPorDescripcion/${descripcion}`)
        .then((coloresResponse) => {
            colores.value = coloresResponse.data;
        })
        .catch((error) => {
            console.error(error);
        });
};

watch([selectedColor, selectedTalle], ([newColor, newTalle]) => {
    if (newColor && newTalle) {
        consultarStock();
    }
});

const consultarStock = () => {
    const idArticulo = codigoArticuloSeleccionado;
    const idColor = selectedColor.value.id;
    const idTalle = selectedTalle.value.id;

    axios.get(`http://localhost:8080/tfib/obtenerStock/${idArticulo}/${idColor}/${idTalle}`)
        .then((stockResponse) => {
            cantidadStock.value = stockResponse.data.cantidad;
            console.log(stockResponse.data);
        })
        .catch((error) => {
            console.error('Error al obtener el stock:', error);
        });

};

watch(cantidadStock, (newCantidadStock, oldCantidadStock) => {
    const spanElement = document.querySelector('.cantidad-stock');
    if (spanElement) {
        spanElement.textContent = newCantidadStock >= 1 ? newCantidadStock : 'Sin Stock';
        if (newCantidadStock >= 1) {
            spanElement.classList.remove('text-red-500');
            spanElement.classList.add('text-green-500');
        } else {
            spanElement.classList.remove('text-green-500');
            spanElement.classList.add('text-red-500');
        }
    }
});

const agregarArticulo = () => {
    const cantidadIngresada = parseInt(floating_last_name.value);
    if (cantidadIngresada <= cantidadStock.value) {
        const totalVenta = cantidadIngresada * precioVentaArticuloSeleccionado;
        const nuevoArticulo = {
            id: articles.value.length + 1,
            marca: Campo_Marca.value,
            descripcion: Campo_Nombre_articulo.value,
            talle: selectedTalle.value,
            color: selectedColor.value,
            cantidad: cantidadIngresada,
            totalVenta: totalVenta
        };

        articles.value.push(nuevoArticulo);

        Campo_Nombre_articulo.value = '';
        Campo_Marca.value = '';
        floating_last_name.value = '';
        descripcion.value = '';
        selectedColor.value = null;
        selectedTalle.value = null;
        cantidadStock.value = null;
        totalAcumulado.value += totalVenta;
    } else {
        alert('La cantidad ingresada supera el stock disponible.');
    }

};

const numeroTarjetaFormateador = (event) => {
    const trimmedValue = event.target.value.replace(/\s+/g, '');
    const formattedValue = trimmedValue.replace(/(\d{4})/g, '$1 ').trim();
    numeroTarjeta.value = formattedValue;
};
</script>
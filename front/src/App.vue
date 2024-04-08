<template>
    <router-view />
</template>
<script>
export default {

  mounted() {
    const link = document.createElement('link');
    link.rel = 'stylesheet';
    link.href = 'https://unpkg.com/flowbite@1.4.5/dist/flowbite.min.css';
    document.head.appendChild(link);

    const script = document.createElement('script');
    script.src = 'https://unpkg.com/flowbite@1.4.5/dist/flowbite.js';
    document.body.appendChild(script);
  },
};
</script>
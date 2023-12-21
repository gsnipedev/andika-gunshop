<script setup lang="ts">

import Navbar from "@/components/Navbar.vue";
</script>

<template>
  <div class="grid justify-items-center">
    <div class="container">
      <Navbar class="sticky top-0 z-40"/>

      <br>
      <br>
      <br>
      <br>
      <br>

      <slot/>

    </div>
  </div>
</template>

<style scoped>

</style>
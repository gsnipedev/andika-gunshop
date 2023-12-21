<script setup lang="ts">

import type {ProductEntity} from "@/model/ProductScheme";
import {onBeforeMount, onMounted, Ref, ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import {ProductService} from "@/service/ProductService";
import SimpleLayout from "@/layout/SimpleLayout.vue";


const product: Ref<ProductEntity | null> = ref(null);
const router = useRouter();
const route = useRoute();

onMounted(async () => {
  product.value = await ProductService.GetOne(route.params.id)
})
</script>

<template>
  <SimpleLayout>
    <div v-if="product != null" class="grid justify-center" >
      <div class="grid grid-cols-3 max-w-2xl gap-7">
        <div class="col-span-2">
          <img :src=" `http://localhost:8080/storage/image/${product.imageUrl}` "
               alt="">
        </div>
        <div>
          <div class="font-bold text-lg mb-4">
            {{ product.name }}
            <div class="badge badge-neutral">{{ product.type }}</div>
          </div>
          <div class="mb-4">
            {{ product.description }}
          </div>

          <button class="btn btn-primary self-end mt-2 w-full">add to cart</button>
        </div>
      </div>
    </div>
  </SimpleLayout>
</template>

<style scoped>

</style>
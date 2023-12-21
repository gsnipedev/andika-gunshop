<script setup lang="ts">
import SimpleLayout from "@/layout/SimpleLayout.vue";
import {onMounted, Ref, ref} from "vue";
import type {ProductEntity} from "@/model/ProductScheme";
import type {Pageable} from "@/model/domain/Pageable";
import {ProductService} from "@/service/ProductService";
import {useRouter} from "vue-router";

const products: Ref<ProductEntity[]> = ref([]);
const router = useRouter();

function gotoProductPage(id: number) {
  router.push("/product/" + id)
}

onMounted(async () => {
  const page: Pageable = {
    page: 0,
    size: 10,
    sort: "ASC"
  }
  products.value = await ProductService.GetAll(page);
})
</script>

<template>
  <SimpleLayout>
    <div class="text-5xl text-center font-bold">GUN</div>
    <div class="text-sm text-center">
      our best guns collection, consider to take one
    </div>

    <br>
    <br>
    <br>

    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 2xl:grid-cols-4 gap-4 justify-items-center p-4">
      <div v-for="p in products"
           class="card bg-base-100 shadow-xl hover:cursor-pointer hover:scale-105 transition duration-500"
           @click="() => gotoProductPage(p.id)">
        <figure>
          <img :src=" `http://localhost:8080/storage/image/${p.imageUrl}` " alt="Shoes"/>
        </figure>
        <div class="card-body">
          <h2 class="card-title">
            {{ p.name }}
          </h2>
          <p>{{ p.description }}</p>
          <div class="card-actions justify-end">
            <div class="badge badge-outline">{{ p.type }}</div>
          </div>
        </div>
      </div>
    </div>
  </SimpleLayout>
</template>

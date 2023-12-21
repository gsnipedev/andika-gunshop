<script setup lang="ts">
import {Ref, ref} from "vue";
import {LoginRequest, LoginResponse, RegisterRequest} from "@/model/AuthScheme";
import {AuthService} from "@/service/AuthService";
import type {AccountEntity} from "@/model/AccountScheme";
import router from "@/router";

const firstName: Ref<string> = ref("")
const lastName: Ref<string> = ref("")
const username: Ref<string> = ref("")
const password: Ref<string> = ref("")
const passwordRepeat: Ref<string> = ref("")

async function register(){
  const request: RegisterRequest = {
    firstName: firstName.value,
    lastName: lastName.value,
    username: username.value,
    password: password.value,
    passwordRepeat: passwordRepeat.value
  }

  try{
    const response: AccountEntity = await AuthService.Register(request);
    router.push("/login")
  }catch (err){
    //
  }
}
</script>

<template>
  <div class="h-screen grid place-items-center">
    <div class="w-96">
      <h1>
        I want 2 register 😎
      </h1>

      <form @submit.prevent="register">
        <div class="form-control">
          <input type="text"
                 placeholder="first name"
                 v-model="firstName"
                 class="input input-bordered input-primary w-full mb-3"/>
        </div>
        <div class="form-control">
          <input type="text"
                 placeholder="last name"
                 v-model="lastName"
                 class="input input-bordered input-primary w-full mb-3"/>
        </div>
        <div class="form-control">
          <input type="text"
                 placeholder="username"
                 v-model="username"
                 class="input input-bordered input-primary w-full mb-3"/>
        </div>
        <div class="form-control">
          <input type="password"
                 placeholder="password"
                 v-model="password"
                 class="input input-bordered input-primary w-full mb-3"/>
        </div>
        <div class="form-control">
          <input type="password"
                 v-model="passwordRepeat"
                 placeholder="password (repeat)"
                 class="input input-bordered input-primary w-full mb-3"/>
        </div>
        <button class="btn btn-primary w-full mb-3">register</button>
      </form>

      <div class="text-sm">
        already hab an account🤨? <a href="/login" class="link">login here</a>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
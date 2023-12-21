<script setup lang="ts">
import type {LoginRequest, LoginResponse} from "@/model/AuthScheme";
import type {Ref} from "vue";
import {ref} from "vue";
import {AuthService} from "@/service/AuthService";
import {useRouter} from "vue-router";

const username: Ref<string> = ref("");
const password: Ref<string> = ref("");
const showPassword: Ref<boolean> = ref(false);
const router = useRouter();

async function login() {
  const request: LoginRequest = {
    username: username.value,
    password: password.value
  }

  try{
    const response: LoginResponse = await AuthService.Login(request);
    localStorage.setItem("authToken", response.authToken);


    router.push("/")
  }catch (err){
    alert("ERROR" + err)
  }
}
</script>

<template>
  <div class="h-screen grid place-items-center p-5">
    <div class="w-full max-w-96">
      <h1>
        login first 🫠
      </h1>

      <form @submit.prevent="login">
        <div>
          <input type="text"
                 v-model="username"
                 autocomplete="username"
                 placeholder="username"
                 class="input input-bordered input-primary w-full mb-3"/>
        </div>
        <div>
          <input :type=" showPassword ? 'text' : 'password' "
                 v-model="password"
                 autocomplete="current-password"
                 placeholder="password"
                 class="input input-bordered input-primary w-full mb-3"/>
        </div>
        <div class="form-control w-max mb-3">
          <label class="label cursor-pointer">
            <input type="checkbox"
                   class="checkbox checkbox-primary mr-3"
            v-model="showPassword"/>
            <span class="label-text">show passw d: ✨</span>
          </label>
        </div>
        <button class="btn btn-primary w-full mb-3">login</button>
      </form>

      <div class="text-sm">
        don't hab an account🥹? <a href="/register" class="link">register here</a>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>
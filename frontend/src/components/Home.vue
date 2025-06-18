<script setup>
 import { useRouter } from 'vue-router'

const router = useRouter()

function goToLists() {
  router.push('/items')
}
</script>

<template>
  <div class="home-container">
    <h1 class="title">Welcome to the Shopping App</h1>
    <button class="list-button" @click="goToLists">Lists</button>
  </div>
</template>

<style scoped>
.home-container {
  text-align: center;
  height: 100%; /* Full viewport height */
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
   align-items: center;
  background: linear-gradient(135deg, #001d03 0%, #000320 100%);
  animation: gradientShift 10s ease infinite;
  background-size: 400% 400%;
}
@keyframes gradientShift {
  0% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
  100% { background-position: 0% 50%; }
}

.title {
  font-size: 2.5rem;
  margin-bottom: 1rem;
  color: #6ea9a7;
}

.list-button {
  background-color: #6ea9a7;
  border: none;
  padding: 0.75rem 1.5rem;
  font-size: 1rem;
  width: 30vh;
  border-radius: 80px;
  color: linear-gradient(135deg, #001d03 0%, #000320 100%);
  animation: gradientShift 10s ease infinite;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 2rem;
  box-shadow: 0 4px 8px #334c4c;
}

.list-button:hover {
  background-color: #4b7271;
}
</style>

<style>

#app {
  width: 100%;
  height: 100%;
  padding: 0;
}
/* Global styles to ensure full-height layout */
html, body {
  height: 100%;
  width: 100%;
  margin: 0;
  padding:0;
}
</style>

<template>
  <div class="background">
    <div class="block">
      <div class="header">
        <button class="back-button" @click="goBack">
          <img src="../assets/arrowl.png" class="icon" alt="Add" />
        </button>
        <h1>Item {{ item?.id }}</h1>
        <div class="button-group">
          <button class="action-button" @click="goToEdit(item.id)">Edit</button>
          <button class="action-button" @click="deleteItem(id)">Delete</button>
        </div>
      </div>

      <div v-if="item" class="info">
        <p><strong>Name:</strong> {{ item.name }}</p>
        <p><strong>Quantity:</strong> {{ item.quantity }}</p>
      </div>
      <div v-else>
        <p>Item not found.</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import items from '../data/items.json'

const route = useRoute()
const router = useRouter()
const id = Number(route.params.id)

const item = items.find(i => i.id === id)

function goToEdit(id) {
  router.push(`/items/${id}/edit`);
}

function deleteItem(id) {
  // hier delete logik von backend
  console.log("Item deleted")
}

function goBack() {
  router.push('/items');
}
</script>

<style scoped>
.background {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: linear-gradient(135deg, #d9f9dd 0%, #d3d7fa 100%);
  animation: gradientShift 10s ease infinite;
  background-size: 400% 400%;
}

@keyframes gradientShift {
  0% { background-position: 0% 50%; }
  50% { background-position: 100% 50%; }
  100% { background-position: 0% 50%; }
}

.block {
  width: 50%;
  max-width: 500px;
  padding: 2rem;
  background-color: white;
  border-radius: 25px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  position: relative;
}

.icon {
  width: 24px;
  height: 24px;
}

.header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  position: relative;
  margin-bottom: 1.5rem;
}

h1 {
  flex-grow: 1;
  margin: 0;
  font-size: 1.5rem;
  color: #333;
  text-align: center;
}

.button-group {
  display: flex;
  gap: 0.5rem;
}

.action-button {
  background-color: #87a4a6;
  color: white;
  border: none;
  border-radius: 6px;
  padding: 0.3rem 0.6rem;
  font-size: 0.9rem;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.action-button:hover {
  background-color: #6e8e90;
}

.back-button {
  background: none;
  border: none;
  font-size: 1.5rem;
  color: #555;
  cursor: pointer;
  padding: 0 0.5rem;
  margin-right: 0.5rem;
  transition: color 0.2s ease;
}

.back-button:hover {
  color: #222;
}

.info p {
  margin: 0.5rem 0;
  font-size: 1.1rem;
}
</style>

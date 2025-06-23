<template>
  <div class="background">
    <div class="block">
      <h2>Neues Item hinzufügen</h2>
      <form @submit.prevent="addItem" class="form">
        <label for="name">Name:</label>
        <input id="name" v-model="name" type="text" required />

        <label for="quantity">Menge:</label>
        <input id="quantity" v-model="quantity" type="number" min="1" required />

        <button type="submit" class="add-button">Hinzufügen</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const name = ref('')
const quantity = ref(1)

function addItem() {
  const newItem = {
    id: Date.now(), // einfache eindeutige ID
    name: name.value,
    quantity: quantity.value
  }

  console.log('Neues Item:', newItem)

  // Eingaben zurücksetzen
  name.value = ''
  quantity.value = 1
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
  max-width: 400px;
  padding: 2rem;
  background-color: white;
  border-radius: 25px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h2 {
  margin-bottom: 1rem;
  color: #333;
}

.form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  align-items: stretch;
}

label {
  font-weight: bold;
  text-align: left;
  color: #555;
}

input {
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  font-size: 1rem;
}

.add-button {
  padding: 0.5rem 1rem;
  background-color: #87a4a6;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-weight: bold;
  transition: background-color 0.2s ease;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.add-button:hover {
  background-color: #6e8e90;
}
</style>

<template>
  <div class="background">
    <div class="block">
      <div class="header">
        <h1>Item {{ item?.id }}</h1>
        <div class="button-group">
          <button class="action-button" @click="goToItem(item.id)">Save changes</button>
        </div>
      </div>

      <div v-if="item" class="info">
        <p>
          <strong>Name:</strong>
          <input v-model="editableName" class="editable-input" />
        </p>
        <p>
          <strong>Quantity:</strong>
          <input
            v-model.number="editableQuantity"
            type="number"
            min="0"
            class="editable-input number-input"
          />
        </p>
      </div>
      <div v-else>
        <p>Item not found.</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import items from '../data/items.json'

const route = useRoute()
const router = useRouter()
const id = Number(route.params.id)

const item = items.find(i => i.id === id)
const editableName = ref(item?.name || '')
const editableQuantity = ref(item?.quantity || 0)

function goToItem(id){
    //hier bearbeiten logik vom backend
    item.name = editableName.value;
    item.quantity = editableQuantity.value
    console.log("neue item daten", editableName, editableQuantity);
    router.push(`/items/${id}`);
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

.header {
  position: relative;
  text-align: center;
  margin-bottom: 1.5rem;
}

h1 {
  margin: 0;
  font-size: 1.5rem;
  color: #333;
}

.button-group {
  position: absolute;
  top: 0;
  right: 0;
  display: flex;
  gap: 0.5rem;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
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

.info p {
  margin: 0.5rem 0;
  font-size: 1.1rem;
}

.editable-input {
  font-size: 1.1rem;
  padding: 0.2rem 0.4rem;
  margin-left: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 6px;
}

.number-input {
  width: 80px;
}
</style>

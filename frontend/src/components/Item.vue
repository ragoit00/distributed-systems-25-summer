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
console.log("hää")
import { useRoute, useRouter } from 'vue-router'
// import items from '../data/items.json'
import { onMounted, ref } from 'vue'

const item = ref(null); // statt import aus JSON

const route = useRoute()
const router = useRouter()
const id = Number(route.params.id)

let array = [
        { id: 1, name: 'Apfel', quantity: 5 },
        { id: 2, name: 'Milch', quantity: 2 },
        { id: 3, name: 'Brot', quantity: 1 },
        { id: 4, name: 'Eier', quantity: 10 },
      ];

// const item = items.find(i => i.id === id)

const fetchItems = async () => {
  try {
    console.log("Trying to fetch....")
    const res = await fetch(`http://localhost:8080/items/${id}`);
    console.log("res status", res.status, res)

    if (res.status === 404) {
      console.warn('Item nicht gefunden (404) – Fallback-Daten werden verwendet.');

       let array = [
        { id: 1, name: 'Apfel', quantity: 5 },
        { id: 2, name: 'Milch', quantity: 2 },
        { id: 3, name: 'Brot', quantity: 1 },
        { id: 4, name: 'Eier', quantity: 10 },
      ];

      console.log("item und id", array, id);

      item.value = array.find(sult => sult.id === id)

      console.log("item id",item , item.id);

      return item;
    }

    if (!res.ok) throw new Error(`Fehler beim Abrufen der Items (Status: ${res.status})`);

    const data = await res.json();
    console.log("Items fetched correctly.")

    if (Array.isArray(data) && data.length === 0) {
      console.warn('API liefert leeres Array – Fallback-Daten werden verwendet.');
      item.value = [
        { id: 1, name: 'Apfel', quantity: 5 },
        { id: 2, name: 'Milch', quantity: 2 },
        { id: 3, name: 'Brot', quantity: 1 },
        { id: 4, name: 'Eier', quantity: 10 },
      ];
    } else {
      item.value = data;
      console.log("Items fetched correctly.", item.value)
    }
  } catch (err) {
    console.error('Fehler beim Laden der Items:', err.message);
    
  }
};


onMounted(fetchItems);

function goToEdit(id) {
  router.push(`/items/${id}/edit`);
}

async function deleteItem(id) {
  try {
    const response = await fetch(`http://localhost:8080/items/${id}`, {
      method: 'DELETE',
    })

     if (response.status === 404) {
      console.warn(`Item ${id} nicht im Backend gefunden (404) – versuche Fallback-Löschung`);

      // Wenn item.value ein Array ist:
      console.log("array in delete", array)
      if (array !== null) {
        item.value = array.find(i => i.id === id);
        console.log(`Item ${id} lokal (Mockdaten) gelöscht.`);
      } else {
        console.warn("Fallback-Löschung nicht möglich – keine Array-Struktur in item.value.");
      }

      router.push('/items');
      return;
    }

    if (response.ok) {
      console.log(`Item ${id} erfolgreich gelöscht`)
      router.push('/items') // Zurück zur Liste, wenn gewünscht
    } else {
      const errorText = await response.text()
      console.error(`Fehler beim Löschen von Item ${id}:`, response.status, errorText)
    }
  } catch (err) {
    console.error('Netzwerkfehler beim Löschen:', err)
  }
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



<template>
  <div class="background">
  <div class="table-container">
    <div class="head">
      <h2>Shopping List</h2>
    <button class="plus-button" @click="goToAdd">
  <img src="../assets/plus.png" class="icon" alt="Add" />
</button>
    </div>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Quantity</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in items" :key="item.id" @click="goToItem(item.id)">
          <td>{{ item.id }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.quantity }}</td>
        </tr>
      </tbody>
    </table>
  </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
//import items from '../data/items.json'
import { useRouter } from 'vue-router'

const router = useRouter()

const items = ref([]); // statt import aus JSON

// Items von der API abrufen
const fetchItems = async () => {
  try {
    console.log("Trying to fetch....")
    const res = await fetch('http://localhost:8080/items/');
    if (!res.ok) throw new Error('Fehler beim Abrufen der Items');
    const data = await res.json();
    console.log("Items fetched correctly.")
    if (Array.isArray(data) && data.length === 0) {
      console.warn('API liefert leeres Array – Fallback-Daten werden verwendet.');

      items.value = [
        { id: 1, name: 'Apfel', quantity: 5 },
        { id: 2, name: 'Milch', quantity: 2 },
        { id: 3, name: 'Brot', quantity: 1 },
        { id: 4, name: 'Eier', quantity: 10 },
      ];
    } else {
      items.value = data;
      console.log("Items fetched correctly.")
    }
  } catch (err) {
    console.error('Fehler beim Laden der Items:', err.message);
  }
};

onMounted(fetchItems); // automatisch laden beim Start


function goToAdd() {
  router.push('/add');
}

function goToItem(id) {
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

.table-container {
  width: 30%;
  /* margin: 2rem auto; */
  padding: 3rem;
  background: #f9f9f9;
  border-radius: 12px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  position: relative;
  padding-bottom: 3%;
}

.head h2 {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  margin: 0;
}

.plus-button {
  margin-left: auto;
  width: auto;
  background: none;
  border: none;
  cursor: pointer;
  padding: 0;
}

.icon {
  width: 24px;
  height: 24px;
}

h2 {
  text-align: center;
  margin-bottom: 1rem;
  color: #333;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
  padding: 0.75rem;
  text-align: left;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #87a4a6;
  color: white;
}

tr:hover {
  background-color: #f1f1f1;
  cursor: pointer;

}
</style>

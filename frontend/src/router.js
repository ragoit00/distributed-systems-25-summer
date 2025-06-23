import { createRouter, createWebHistory } from 'vue-router'
import Home from './components/Home.vue'
import List from './components/List.vue'
import Add from './components/Add.vue'
import Item from './components/Item.vue'
import Edit from './components/Edit.vue'

const routes = [
  { path: '/', component: Home },
  { path: '/items', component: List },
  { path: '/add', component: Add},
  { path: '/items/:id', component: Item},
  { path: '/items/:id/edit', component: Edit}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router

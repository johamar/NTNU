import { createRouter, createWebHistory } from 'vue-router'
import Calculator from '@/components/Calculator.vue'
import ContactForm from '@/components/ContactForm.vue'
import LoginView from '@/views/LoginView.vue';
import RegisterView from '@/views/RegisterView.vue';

const routes = [
  { path: '/', component: LoginView },
  { path: '/registrer', component: RegisterView },
  { path: '/kalkulator', component: Calculator },
  { path: '/kontaktskjema', component: ContactForm }
 
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes 
});

export default router

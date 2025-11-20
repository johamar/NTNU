<script setup> 
import { useContactFormStore } from '@/stores/contactFormStore';

const contactFormStore = useContactFormStore();
</script>

<template>
    <nav>
      <router-link to="/kalkulator">Kalkulator</router-link> 
      <router-link to="/kontaktskjema">Kontakskjema</router-link>
    </nav>
    <div class="contact-form">
        <h1>Kontaktskjema</h1>
        <form @submit.prevent="contactFormStore.submitForm">
            <label for="name">Navn:</label>
            <input type="text" id="name" v-model="contactFormStore.name" required>
            <label for="email">E-post:</label>
            <input type="email" id="email" v-model="contactFormStore.email" required>
            <label for="message">Melding:</label>
            <textarea id="message" v-model="contactFormStore.message" required></textarea>
            <button id="submitButton" type="submit" :disabled="!contactFormStore.isFormValid">Send</button>
        </form>
        <p v-if="contactFormStore.statusMessage" :class="contactFormStore.statusClass">{{ contactFormStore.statusMessage }}</p>
    </div>
</template>

<style>
    .contact-form {
        display: grid;
        gap: 20px;
        justify-items: center;
        text-align: center;
        margin: 40px;
    }
    form {
        display: grid;
        gap: 10px;
        justify-items: center;
        text-align: center;
    }
    label {
        font-size: 20px;
    }
    input, textarea {
        width: 300px;
        padding: 10px;
        font-size: 20px;
        background-color: rgb(252, 252, 252);
    }
    #submitButton {
        padding: 10px 20px;
        font-size: 20px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    #submitButton:disabled {
        background-color: lightgray;
        cursor: not-allowed;
    }
    #submitButton:hover:enabled {
        background-color: #0056b3;
    }
    .success {
        color: green;
    }
    .error {
        color: red;
    }
</style>
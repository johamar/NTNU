<script setup>
import { useRegisterStore } from '@/stores/registerStore';

const registerStore = useRegisterStore();
</script>

<template>
    <div id="registerContainer">
        <h1>Registrering</h1>
        <label for="username">Brukernavn:</label>
        <input type="text" id="username" v-model="registerStore.username" required>
        <label for="password">Passord:</label>
        <input type="password" id="password" v-model="registerStore.password" required>
        <label for="passwordRepeat">Gjenta passord:</label>
        <input type="password" id="passwordRepeat" v-model="registerStore.passwordRepeat" required>
        <button id="registerButton" type="submit" :disabled="!registerStore.isFormValid" @click="registerStore.register">Registrer</button>
        <p v-if="registerStore.statusMessage" :class="registerStore.statusClass">{{ registerStore.statusMessage }}</p>
        <button id="loginButton" @click="registerStore.login">Logg inn</button>
    </div>
</template>

<style scoped>
#registerContainer {
    display: grid;
    gap: 20px;
    justify-items: center;
    text-align: center;
    margin: 40px;
}
h1 {
    font-size: 30px;
}
label {
    font-size: 20px;
}
input {
    width: 300px;
    padding: 10px;
    font-size: 20px;
    background-color: rgb(252, 252, 252);
}
#registerButton {
    padding: 10px 20px;
    font-size: 20px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
#registerButton:disabled {
    background-color: lightgray;
    cursor: not-allowed;
}
#registerButton:hover:enabled {
    background-color: #0056b3;
}
#loginButton {
    padding: 10px 20px;
    font-size: 20px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
#loginButton:hover {
    background-color: #0056b3;
}
</style>
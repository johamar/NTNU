<script setup>
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const username = ref('');
const password = ref('');
const statusMessage = ref('');
const statusClass = ref('');
const isLoggedIn = ref(false);

const login = async () => {
    try {
        const response = await fetch('http://localhost:8080/api/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username: username.value, password: password.value })
        });

        if (response.ok) {
            const data = await response.json();
            sessionStorage.setItem('token', data.jwt);
            sessionStorage.setItem('username', username.value);
            isLoggedIn.value = true;
            statusMessage.value = 'Innlogging vellykket';
            statusClass.value = 'success';
            await router.push('/kalkulator');
        } else {
            statusMessage.value = 'Feil brukernavn eller passord';
            statusClass.value = 'error';
        }
    } catch (error) {
        statusMessage.value = 'Noe gikk galt. Prøv igjen senere.';
        statusClass.value = 'error';
    }
};

const register = () => {
    router.push('/registrer');
};

const isFormValid = computed(() => {
    return username.value !== '' && password.value !== '' && password.value.length >= 8;
});
</script>

<template>
    <div id="loginContainer">
        <div id="loginTitle">
            <h1>Logg inn</h1>
        </div>
        <div id="username">
            <label for="username">Brukernavn:</label>
            <input type="text" id="username" v-model="username" required @keyup.enter="login">
        </div>
        <div id="password">
            <label for="password">Passord:</label>
            <input type="password" id="password" v-model="password" required @keyup.enter="login">
        </div>
        <button id="loginButton" type="submit" :disabled="!isFormValid" @click="login">Logg inn</button>
        <p v-if="statusMessage" :class="statusClass">{{ statusMessage }}</p>
        <p>Ingen bruker? <button id="registerButton" @click="register">Registrer deg her</button></p>
    </div>
</template>

<style scoped>
#loginContainer {
    display: grid;
    gap: 20px;
    justify-items: center;
    text-align: center;
    margin: 40px;
}
#loginTitle {
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
#loginButton {
    padding: 10px 20px;
    font-size: 20px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
#loginButton:disabled {
    background-color: lightgray;
    cursor: not-allowed;
}
#loginButton:hover:enabled {
    background-color: #0056b3;
}
#registerButton {
    padding: 10px 20px;
    font-size: 20px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
#registerButton:hover {
    background-color: #218838;
}
.error {
    color: red;
}
.success {
    color: green;
}
</style>
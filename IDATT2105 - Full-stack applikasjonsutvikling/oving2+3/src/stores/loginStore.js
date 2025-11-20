// import { defineStore } from 'pinia';
// import { ref, computed } from 'vue';
// import { useRouter } from 'vue-router';


// export const useLoginStore = defineStore('login', () => {
//     const router = useRouter();
//     const username = ref('');
//     const password = ref('');
//     const statusMessage = ref('');
//     const statusClass = ref('');
//     const isLoggedIn = ref(false);

//     const login = async () => {
//         const response = await fetch('http://localhost:8080/api/auth/login', {
//             method: 'POST',
//             headers: { 'Content-Type': 'application/json' },
//             body: JSON.stringify({ username: username.value, password: password.value })
//         });
//         if (response.ok) {
//             const data = await response.json();
//             sessionStorage.setItem('token', data.jwt);
//             isLoggedIn.value = true;
//             statusMessage.value = 'Innlogging vellykket';
//             statusClass.value = 'success';
//             await router.push('/kalkulator');
//         } else {
//             statusMessage.value = 'Feil brukernavn eller passord';
//             statusClass.value = 'error';
//         }
//     };


//     const register = () => {
//         router.push('/registrer');
//     };

//     const isFormValid = computed(() => {
//         return username.value !== '' && password.value !== '' && password.value.length >= 8;
//     }
// );


//     return {
//         username,
//         password,
//         statusMessage,
//         statusClass,
//         isLoggedIn,
//         isFormValid,
//         login,
//         register
//     };
// });
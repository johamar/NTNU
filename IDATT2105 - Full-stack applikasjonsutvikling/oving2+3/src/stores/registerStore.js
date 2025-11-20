import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';

export const useRegisterStore = defineStore('register', () => {
    const router = useRouter();
    const username = ref('');
    const password = ref('');
    const passwordRepeat = ref('');
    const statusMessage = ref('');
    const statusClass = ref('');

    const isFormValid = computed(() => {
        return username.value !== '' && password.value !== '' && password.value === passwordRepeat.value;
    });

    const register = async () => {
        const formData = {
            username: username.value,
            password: password.value
        };
        try {
            const response = await fetch('http://localhost:8080/api/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });
            if (response.ok) {
                statusMessage.value = 'Bruker registrert';
                statusClass.value = 'success';
                router.push('/');
            } else {
                const errorText = await response.text();
                statusMessage.value = errorText;
                statusClass.value = 'error';
            }
        } catch (error) {
            statusMessage.value = 'Noe gikk galt. Prøv igjen senere.';
            statusClass.value = 'error';
        }
    };

    const login = () => {
        router.push('/');
    };

    return {
        username,
        password,
        passwordRepeat,
        statusMessage,
        statusClass,
        isFormValid,
        register,
        login
    };
});
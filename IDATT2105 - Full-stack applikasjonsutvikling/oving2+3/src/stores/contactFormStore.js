import { defineStore } from 'pinia';
import { ref, computed } from "vue";

export const useContactFormStore = defineStore('contactForm', () => {
    const name = ref('');
    const email = ref('');
    const message = ref('');
    const statusMessage = ref('');
    const statusClass = ref('');

    const isFormValid = computed(() => {
        return name.value !== '' && email.value !== '' && message.value !== '';
    });

    const submitForm = async () => {
        const formData = {
            name: name.value,
            email: email.value,
            message: message.value
        };
        try {
            const response = await fetch('http://localhost:3000/messages', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });
            if (response.ok) {
                statusMessage.value = 'Meldingen ble sendt!';
                statusClass.value = 'success';
            } else {
                statusMessage.value = 'Noe gikk galt. Prøv igjen senere.';
                statusClass.value = 'error';
            }
        } catch (error) {
            statusMessage.value = 'Noe gikk galt. Prøv igjen senere.';
            statusClass.value = 'error';
        }

        name.value = '';
        email.value = '';
        message.value = '';
    };
    return {
        name,
        email,
        message,
        statusMessage,
        statusClass,
        isFormValid,
        submitForm
    };
});
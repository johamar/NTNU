import { defineStore } from 'pinia';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

export const useCalculatorStore = defineStore('calculator', () => {
    const router = useRouter();
    const display = ref('');
    const currentNumber = ref('');
    const previousNumber = ref('');
    const operation = ref(null);
    const log = ref([]);
    const showLog = ref(false);
    const username = ref(sessionStorage.getItem('username'));
    const isLoggedIn = ref(!!sessionStorage.getItem('token'));

    const logOut = async () => {
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('username');
        isLoggedIn.value = false;
        username.value = '';
        await router.push('/');
    };

    const appendNumber = (number) => {
        currentNumber.value += number;
        display.value += number;
    };

    const setOperation = (op) => {
        if (currentNumber.value === '') return;
        if (previousNumber.value !== '') {
            calculate();
        }
        operation.value = op;
        previousNumber.value = currentNumber.value;
        currentNumber.value = '';
        display.value += ` ${op} `;
    };

    const calculate = async () => {
        if (!operation.value || previousNumber.value === '' || currentNumber.value === '') return;

        try {
            const token = sessionStorage.getItem('token');
            if (!token) {
                throw new Error('Du må være logget inn for å bruke kalkulatoren');
            }
            const response = await fetch("http://localhost:8080/api/auth/calculator/calculate", {
                method: "POST",
                headers: { 
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify({
                    number1: parseFloat(previousNumber.value),
                    number2: parseFloat(currentNumber.value),
                    operation: operation.value,
                })
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data = await response.json();
            log.value.push(`${data.number1} ${data.operation} ${data.number2} = ${data.result}`);
            display.value = data.result.toString();
            currentNumber.value = data.result.toString();
            previousNumber.value = '';
            operation.value = null;
        } catch (error) {
            console.error('Error during calculation:', error);
            display.value = 'Error';
            alert(error);
        }
    };

    const fetchHistory = async () => {
        try {
            const token = sessionStorage.getItem('token');
            const response = await fetch("http://localhost:8080/api/auth/calculator/history", {
                method: "GET",
                headers: { 
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                }
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data = await response.json();
            log.value = data.map(calc => `${calc.number1} ${calc.operation} ${calc.number2} = ${calc.result}`);
        } catch (error) {
            console.error('Error fetching history:', error);
        }
    };

    const clear = () => {
        currentNumber.value = '';
        display.value = '';
    };

    const allClear = () => {
        currentNumber.value = '';
        display.value = '';
        operation.value = null;
        previousNumber.value = '';
    };

    const toggleLog = () => {
        showLog.value = !showLog.value;
    };

    const clearLog = () => {
        log.value = [];
    }

    return {
        router,
        display,
        currentNumber,
        previousNumber,
        operation,
        log,
        showLog,
        username,
        isLoggedIn,
        logOut,
        appendNumber,
        setOperation,
        calculate,
        fetchHistory,
        clear,
        allClear,
        toggleLog,
        clearLog
    };
});
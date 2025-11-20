<script setup>
import { onMounted } from 'vue';
import { useCalculatorStore } from '../stores/calculatorStore';


const calculatorStore = useCalculatorStore();

onMounted(() => {
    calculatorStore.fetchHistory();
});

</script>

<template>
    <header>
        <div class="infoHeader">
            <p v-if="calculatorStore.isLoggedIn">Logget inn som: {{ calculatorStore.username }}</p>
            <p v-else>Du er ikke logget inn</p>
            <button id="logOutBtn" @click="calculatorStore.logOut">Logg ut</button>
        </div>
    </header>
    <nav>
      <router-link to="/kalkulator">Kalkulator</router-link> 
      <router-link to="/kontaktskjema">Kontakskjema</router-link>
    </nav>
    <div class="calculator">
        <h1>Kalkulator</h1>
        <input type="text" v-model="calculatorStore.display" @keyup.enter="calculatorStore.calculate" @keyup.esc="calculatorStore.allClear"/>

        <div class="numButtons">
            <div id="row1">
                <button @click="calculatorStore.appendNumber('1')">1</button>
                <button @click="calculatorStore.appendNumber('2')">2</button>
                <button @click="calculatorStore.appendNumber('3')">3</button>
            </div>
            <div id="row2">
                <button @click="calculatorStore.appendNumber('4')">4</button>
                <button @click="calculatorStore.appendNumber('5')">5</button>
                <button @click="calculatorStore.appendNumber('6')">6</button>
            </div>
            <div id="row3">
                <button @click="calculatorStore.appendNumber('7')">7</button>
                <button @click="calculatorStore.appendNumber('8')">8</button>
                <button @click="calculatorStore.appendNumber('9')">9</button>
            </div>
            <div id="row4">
                <button @click="calculatorStore.appendNumber('0')">0</button>
                <button @click="calculatorStore.appendNumber('.')">.</button>
                <button id="equals" @click="calculatorStore.calculate">=</button>
            </div>
        </div>
        <div class="opButtons">
            <div id="col1">
                <button @click="calculatorStore.setOperation('*')">x</button>
                <button @click="calculatorStore.setOperation('/')">÷</button>
                <button @click="calculatorStore.setOperation('+')">+</button>
                <button @click="calculatorStore.setOperation('-')">-</button>
            </div>
            <div id="col2">
                <button @click="calculatorStore.clear">C</button>
                <button @click="calculatorStore.allClear">AC</button>
            </div>
        </div>
    </div>
    <div class="log">
        <button @click="calculatorStore.toggleLog">Vis logg</button>
        <div v-if="calculatorStore.showLog" class="logList">
            <h2>Logg</h2>
            <ul>
                <li v-for="entry in calculatorStore.log" :key="entry">{{ entry }}</li>
            </ul>
            <button id="clearLogBtn" @click="calculatorStore.clearLog">Tøm logg</button>
        </div>
    </div>
</template>

<style scoped>
header {
    display: flex;
    justify-content: flex-end;
    align-items: center;
    padding: 10px;
    border-radius: 10px;
    position: absolute;
    top: 0;
    right: 0;
    width: 100%;
}
.infoHeader {
    display: flex;
    align-items: center;
    gap: 10px;
}
#logOutBtn {
    background-color: rgb(238, 7, 7);
    color: black;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    width: auto;
}
.calculator {
    width: 90%;
    display: grid;
    grid-template-areas: 'h1 h1 h1 h1'
                         'input input input input'
                         'numButtons numButtons numButtons opButtons';
    justify-content: center;
    margin: 40px;
    background-color: aliceblue;
    border-radius: 10px;
}
h1 {
    grid-area: h1;
    width: 100%;
    font-size: 30px;
    text-align: center;
    margin-top: 10px;
    color: black;
}
input {
    grid-area: input;
    width: 300px;
    height: 100%;
    font-size: 18px;
    text-align: right;
    border: none;
    margin-bottom: 20px;
    border-radius: 5px;
    background-color: lightgray;
}
button {
    width: 50px;
    height: 50px;
    font-size: 20px;
    background-color: lightblue;
    border: none;
    border-radius: 5px;
    margin: 10px 0 ;
    cursor: pointer;
}
button:hover {
    grid-area: numButtons;
    background-color: rgb(116, 202, 231);
}
.numButtons {
    display: grid;
    grid-template-rows: 1fr 1fr 1fr;
    gap: 10px;
}
.opButtons {
    grid-area: opButtons;
    display: grid;
    text-align: center;
    grid-template-columns: 1fr 1fr;
    gap: 10px;
}
#row1, #row2, #row3, #row4 {
    text-align: left;
    display: flex;
    gap: 10px;
}
#col2, #col1 {
    display: flex;
    flex-direction: column;
    gap: 10px;
}
.opButtons button {
    width: 50px;
    height: 50px;
    font-size: 20px;
    background-color: lightcoral;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
.opButtons button:hover {
    background-color: rgb(235, 97, 97);
}
#equals {
    background-color: lightgreen;
}
#equals:hover {
    background-color: rgb(98, 216, 98);
}
.log {
    display: flex;
    flex-direction: column;
    width: 90%;
    text-align: left;
    margin-top: 20px;
    background-color: #f9f9f9;
    border: 1px solid #ccc;
    border-radius: 5px;
    padding: 10px;
}
.log button {
    grid-area: top;
    width: 100px;
    background-color: rgb(7, 235, 238);
    place-self: center;
    border-radius: 5px;
    cursor: pointer;
}
.log button:hover {
    background-color: rgb(7, 200, 238);
}
.logList {
    margin-top: 10px;
    color: black;
}
#clearLogBtn {
    background-color: rgb(238, 7, 7);
    height: 30px;
}
#clearLogBtn:hover {
    background-color: rgb(233, 64, 64);
}
</style>

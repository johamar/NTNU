<script setup lang="ts">
import { ref } from 'vue';

const code = ref('');
const output = ref('');

const compileAndRun = async () => {
  try {
    const response = await fetch('http://localhost:3000/run', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ code: code.value }),
    });

    const result = await response.json();
    output.value = result.output;
  } catch (error) {
    output.value = 'Error: Could not connect to server';
  }
  console.log("Code compiled and run");
};
</script>

<template>
  <div class="wrapper">
    <h1>Code Editor</h1>
    <h2>Write code and compile</h2>
    <textarea v-model="code" placeholder="Write your C++ code here..."></textarea>
    <button @click="compileAndRun">Compile and Run</button>
    <h2>Output</h2>
    <textarea v-model="output" readonly></textarea>
  </div>
</template>

<style scoped>
.wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  width: 100%;
}
h1, h2 {
  text-align: center;
  margin-top: 1rem;
}
textarea {
  width: 50%;
  height: 150px;
  margin-top: 1rem;
}
button {
  margin-top: 1rem;
  height: 2rem;
  cursor: pointer;
}
</style>

<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style>

nav {
  display: flex;
  height: 50%;
  width: 100%;
  gap: 20px;
  justify-content: center;
  padding: 20px;
  border-radius: 10px;
  background-color: #f4f4f4;
}
nav a {
  text-decoration: none;
  color: #333;
  font-size: 14px;
  place-self: center;
}
nav a:hover {
  color: #555;
}
#app  {
  font-family: Arial, Helvetica, sans-serif;
  display: grid;
  justify-items: center;
  text-align: center;
  justify-content: center;
  margin-top: 50px;
}
</style>

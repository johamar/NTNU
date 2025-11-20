const addButton = document.getElementById('button1');
const subtractButton = document.getElementById('button2');
const sum = document.getElementById('counter');

let counter = 0;
addButton.addEventListener('click', () => {
    counter++;
    sum.textContent = counter;
});
subtractButton.addEventListener('click', () => {
    counter--;
    sum.textContent = counter;
});

const toggleBtn = document.getElementById('toggle');
const menuCalc = document.getElementById('menu_calc');
toggleBtn.addEventListener('click', () => {
    if (menuCalc.style.display === 'none') {
        menuCalc.style.display = 'block';
    } else {
        menuCalc.style.display = 'none';
    }
});

const keywords = ["Tromsdalen", "Fotballklubb", "Beste laget i Nord", "Tromsdalstinden", "Nordlys", "Fineste plassen i verden"];
const generateListBtn = document.getElementById('generate_list_btn');
const list = document.getElementById('list');

generateListBtn.addEventListener('click', () => {
    list.innerHTML = '';
    keywords.forEach(keyword => {
        const li = document.createElement('li');
        li.textContent = keyword;
        list.appendChild(li);
    });
});

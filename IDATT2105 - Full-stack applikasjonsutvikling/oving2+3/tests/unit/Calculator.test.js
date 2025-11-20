import { mount } from '@vue/test-utils';
import { createPinia, setActivePinia } from 'pinia';
import { describe, it, expect, vi, beforeEach } from 'vitest';
import Calculator from '../../src/components/Calculator.vue';
import { useCalculatorStore } from '../../src/stores/calculatorStore';

describe('Calculator.vue', () => {
  beforeEach(() => {
    setActivePinia(createPinia());
  });

  it('renders the calculator correctly', () => {
    const wrapper = mount(Calculator);
    expect(wrapper.find('h1').text()).toBe('Kalkulator');
  });

  it('appends numbers correctly', async () => {
    const wrapper = mount(Calculator);
    const calculatorStore = useCalculatorStore();
    const buttons = wrapper.findAll('button');
    await buttons.find(button => button.text() === '1').trigger('click');
    await buttons.find(button => button.text() === '2').trigger('click');
    expect(calculatorStore.currentNumber).toBe('12');
    expect(calculatorStore.display).toBe('12');
  });

  it('sets operation correctly', async () => {
    const wrapper = mount(Calculator);
    const calculatorStore = useCalculatorStore();
    const buttons = wrapper.findAll('button');
    await buttons.find(button => button.text() === '1').trigger('click');
    await buttons.find(button => button.text() === '+').trigger('click');
    expect(calculatorStore.operation).toBe('+');
    expect(calculatorStore.previousNumber).toBe('1');
    expect(calculatorStore.currentNumber).toBe('');
    expect(calculatorStore.display).toBe('1 + ');
  });

  it('calculates correctly', async () => {
    const wrapper = mount(Calculator);
    const calculatorStore = useCalculatorStore();
    const buttons = wrapper.findAll('button');
    await buttons.find(button => button.text() === '1').trigger('click');
    await buttons.find(button => button.text() === '+').trigger('click');
    await buttons.find(button => button.text() === '2').trigger('click');
    await buttons.find(button => button.text() === '=').trigger('click');
    expect(calculatorStore.currentNumber).toBe('3');
    expect(calculatorStore.display).toBe('3');
  });

  it('clears correctly', async () => {
    const wrapper = mount(Calculator);
    const calculatorStore = useCalculatorStore();
    const buttons = wrapper.findAll('button');
    await buttons.find(button => button.text() === '1').trigger('click');
    await buttons.find(button => button.text() === 'C').trigger('click');
    expect(calculatorStore.currentNumber).toBe('');
    expect(calculatorStore.display).toBe('');
  });

  it('clears all correctly', async () => {
    const wrapper = mount(Calculator);
    const calculatorStore = useCalculatorStore();
    const buttons = wrapper.findAll('button');
    await buttons.find(button => button.text() === '1').trigger('click');
    await buttons.find(button => button.text() === '+').trigger('click');
    await buttons.find(button => button.text() === '2').trigger('click');
    await buttons.find(button => button.text() === 'AC').trigger('click');
    expect(calculatorStore.currentNumber).toBe('');
    expect(calculatorStore.previousNumber).toBe('');
    expect(calculatorStore.operation).toBe(null);
    expect(calculatorStore.display).toBe('');
  });

  it('toggles log correctly', async () => {
    const wrapper = mount(Calculator);
    const calculatorStore = useCalculatorStore();
    const buttons = wrapper.findAll('button');
    await buttons.find(button => button.text() === 'Hvis logg').trigger('click');
    expect(calculatorStore.showLog).toBe(true);
    await buttons.find(button => button.text() === 'Hvis logg').trigger('click');
    expect(calculatorStore.showLog).toBe(false);
  });
});
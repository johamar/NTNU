import { mount } from '@vue/test-utils';
import { createPinia, setActivePinia } from 'pinia';
import { describe, it, expect, vi, beforeEach } from 'vitest';
import ContactForm from '../../src/components/ContactForm.vue';

describe('ContactForm.vue', () => {
  beforeEach(() => {
    setActivePinia(createPinia());
  });
  
  it('renders the contact form correctly', () => {
    const wrapper = mount(ContactForm);
    expect(wrapper.find(
      'h1').text()).toBe('Kontaktskjema');
    expect(wrapper.find('label[for="name"]').text()).toBe('Navn:');
    expect(wrapper.find('label[for="email"]').text()).toBe('E-post:');
    expect(wrapper.find('label[for="message"]').text()).toBe('Melding:');
  });

  it('disables the submit button when fields are empty', async () => {
    const wrapper = mount(ContactForm);
    const submitButton = wrapper.find('#submitButton');
    expect(submitButton.element.disabled).toBe(true);

    await wrapper.find('#name').setValue('Test Name');
    await wrapper.find('#email').setValue('test@example.com');
    await wrapper.find('#message').setValue('Test message');
    expect(submitButton.element.disabled).toBe(false);
  });

  it('shows success message on successful form submission', async () => {
    const wrapper = mount(ContactForm);
    global.fetch = vi.fn(() =>
      Promise.resolve({
        ok: true,
      })
    );

    await wrapper.find('#name').setValue('Test Name');
    await wrapper.find('#email').setValue('test@example.com');
    await wrapper.find('#message').setValue('Test message');
    await wrapper.find('form').trigger('submit.prevent');

    expect(wrapper.find('p.success').exists()).toBe(true);
    expect(wrapper.find('p.success').text()).toBe('Meldingen ble sendt!');
  });

  it('shows error message on failed form submission', async () => {
    const wrapper = mount(ContactForm);
    global.fetch = vi.fn(() =>
      Promise.resolve({
        ok: false,
      })
    );

    await wrapper.find('#name').setValue('Test Name');
    await wrapper.find('#email').setValue('test@example.com');
    await wrapper.find('#message').setValue('Test message');
    await wrapper.find('form').trigger('submit.prevent');

    expect(wrapper.find('p.error').exists()).toBe(true);
    expect(wrapper.find('p.error').text()).toBe('Noe gikk galt. Prøv igjen senere.');
  });
});
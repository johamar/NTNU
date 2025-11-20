describe('ContactForm.vue', () => {
    beforeEach(() => {
      cy.visit('/kontaktskjema');
    });
  
    it('should disable the submit button when the form is invalid', () => {
      cy.get('#submitButton').should('be.disabled');
  
      cy.get('#name').type('Test Name');
      cy.get('#submitButton').should('be.disabled');
  
      cy.get('#email').type('test@example.com');
      cy.get('#submitButton').should('be.disabled');
  
      cy.get('#message').type('Test message');
      cy.get('#submitButton').should('not.be.disabled');
    });
  
    it('should show success message on successful form submission', () => {
      cy.intercept('POST', 'http://localhost:3000/messages', {
        statusCode: 200,
        body: { message: 'Meldingen ble sendt!' },
      }).as('postMessage');
  
      cy.get('#name').type('Test Name');
      cy.get('#email').type('test@example.com');
      cy.get('#message').type('Test message');
      cy.get('#submitButton').click();
  
      cy.wait('@postMessage');
  
      cy.get('p.success').should('contain', 'Meldingen ble sendt!');
    });
  
    it('should show error message on failed form submission', () => {
      cy.intercept('POST', 'http://localhost:3000/messages', {
        statusCode: 500,
        body: { message: 'Noe gikk galt. Prøv igjen senere.' },
      }).as('postMessage');
  
      cy.get('#name').type('Test Name');
      cy.get('#email').type('test@example.com');
      cy.get('#message').type('Test message');
      cy.get('#submitButton').click();
  
      cy.wait('@postMessage');
  
      cy.get('p.error').should('contain', 'Noe gikk galt. Prøv igjen senere.');
    });
  });
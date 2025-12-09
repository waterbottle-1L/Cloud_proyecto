import { Component } from '@angular/core';

@Component({
  selector: 'app-error500',
  template: `
    <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; height: 100vh; text-align: center; font-family: Arial, sans-serif;">
      <h1 style="font-size: 8rem; color: #dc3545; margin: 0;">500</h1>
      <p style="font-size: 1.5rem; color: #6c757d; margin: 1rem 0;">Internal Server Error</p>
      <button 
        (click)="goHome()" 
        style="padding: 0.75rem 1.5rem; font-size: 1rem; background-color: #007bff; color: white; border: none; border-radius: 0.25rem; cursor: pointer;">
        ← Return to Dashboard
      </button>
    </div>
  `
})
export class Error500Component {
  goHome() {
    window.location.href = '/';
  }
}

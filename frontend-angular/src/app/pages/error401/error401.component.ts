import { Component } from '@angular/core';

@Component({
  selector: 'app-error401',
  template: `
    <div class="error-container">
      <h1>401 Error</h1>
      <h2>Unauthorized</h2>
      <p>You do not have permission to access this page.</p>
      <a routerLink="/" class="btn-return">← Return to Dashboard</a>
    </div>
  `,
  styles: [`
    .error-container {
      text-align: center;
      margin-top: 100px;
      font-family: Arial, sans-serif;
    }
    h1 {
      font-size: 6rem;
      color: #dc3545; /* rojo */
    }
    h2 {
      font-size: 2rem;
      margin-bottom: 20px;
    }
    p {
      font-size: 1.2rem;
      margin-bottom: 30px;
    }
    .btn-return {
      text-decoration: none;
      background-color: #007bff;
      color: white;
      padding: 10px 20px;
      border-radius: 5px;
    }
    .btn-return:hover {
      background-color: #0056b3;
    }
  `]
})
export class Error401Component {}

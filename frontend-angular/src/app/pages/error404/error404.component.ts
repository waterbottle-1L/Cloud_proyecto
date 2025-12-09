import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-error404',
  template: `
    <div style="display:flex; flex-direction:column; align-items:center; justify-content:center; height:100vh; text-align:center;">
      <h1 style="font-size:6rem; color:#ff4c4c;">404</h1>
      <h2 style="margin-bottom:1rem;">Page Not Found</h2>
      <p>The requested URL was not found on this server.</p>
      <button (click)="goHome()" style="margin-top:1rem; padding:0.5rem 1rem; font-size:1rem; cursor:pointer; border:none; background-color:#007bff; color:white; border-radius:5px;">
        ← Return to Dashboard
      </button>
    </div>
  `
})
export class Error404Component {
  constructor(private router: Router) {}

  goHome() {
    this.router.navigate(['/']); // te lleva a la página principal
  }
}

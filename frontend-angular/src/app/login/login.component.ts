import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { NgIf } from '@angular/common'; // 👈 IMPORTANTE para que desaparezca la advertencia

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule, // para formularios
    NgIf                  // para *ngIf
  ],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      remember: [false]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      console.log('Login data:', this.loginForm.value);
      // Aquí iría la lógica real de login
    } else {
      this.loginForm.markAllAsTouched(); // Mostrar errores
    }
  }
}

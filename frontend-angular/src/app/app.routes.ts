// src/app/app.routes.ts
import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { PasswordComponent } from './pages/password/password.component';

import { LayoutComponent } from './layout.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { TablesComponent } from './tables/tables.component';

export const routes: Routes = [
  // Rutas públicas (sin layout)
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'password', component: PasswordComponent },

  // Rutas protegidas / internas (CON LAYOUT)
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'tables', component: TablesComponent },
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
    ]
  },

  // Cualquier ruta inexistente
  { path: '**', redirectTo: 'dashboard' }
];

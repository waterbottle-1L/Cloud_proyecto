import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { ChartsComponent } from './pages/charts/charts.component';
import { Error401Component } from './pages/error401/error401.component';
import { Error404Component } from './pages/error404/error404.component';
import { Error500Component } from './pages/error500/error500.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'charts', component: ChartsComponent },
  { path: '401', component: Error401Component },
  { path: '404', component: Error404Component },
  { path: '500', component: Error500Component },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

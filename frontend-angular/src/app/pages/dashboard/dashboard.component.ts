import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; // IMPORTANTE para ngClass y *ngFor

@Component({
  selector: 'app-dashboard',
  standalone: true,   // <- importante
  imports: [CommonModule], // <- importa CommonModule aquí
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  cards = [
    { title: 'Primary Card', bgClass: 'bg-primary' },
    { title: 'Warning Card', bgClass: 'bg-warning' },
    { title: 'Success Card', bgClass: 'bg-success' },
    { title: 'Danger Card', bgClass: 'bg-danger' }
  ];

  users = [
    { name: 'Tiger Nixon', position: 'System Architect', office: 'Edinburgh', age: 61, startDate: '2011/04/25', salary: '$320,800' },
    { name: 'Garrett Winters', position: 'Accountant', office: 'Tokyo', age: 63, startDate: '2011/07/25', salary: '$170,750' },
    { name: 'Ashton Cox', position: 'Junior Technical Author', office: 'San Francisco', age: 66, startDate: '2009/01/12', salary: '$86,000' },
    { name: 'Cedric Kelly', position: 'Senior Javascript Developer', office: 'Edinburgh', age: 22, startDate: '2012/03/29', salary: '$433,060' }
  ];
}

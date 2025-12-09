import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  standalone: true,
  imports: [CommonModule] // 👈 IMPORTANTE
})
export class TablesComponent {
  data = [
    { name: 'Tiger Nixon', position: 'System Architect', office: 'Edinburgh', age: 61, startDate: '2011/04/25', salary: '$320,800' },
    { name: 'Garrett Winters', position: 'Accountant', office: 'Tokyo', age: 63, startDate: '2011/07/25', salary: '$170,750' },
    { name: 'Ashton Cox', position: 'Junior Technical Author', office: 'San Francisco', age: 66, startDate: '2009/01/12', salary: '$86,000' },
    { name: 'Cedric Kelly', position: 'Senior Javascript Developer', office: 'Edinburgh', age: 22, startDate: '2012/03/29', salary: '$433,060' },
    { name: 'Airi Satou', position: 'Accountant', office: 'Tokyo', age: 33, startDate: '2008/11/28', salary: '$162,700' }
  ];
}

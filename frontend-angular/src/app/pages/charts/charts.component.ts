import { Component, AfterViewInit } from '@angular/core';
import Chart from 'chart.js/auto';

@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})
export class ChartsComponent implements AfterViewInit {

  constructor() { }

  ngAfterViewInit(): void {
    // Área Chart
    const ctxArea = document.getElementById('myAreaChart') as HTMLCanvasElement;
    new Chart(ctxArea, {
      type: 'line',
      data: {
        labels: ["Mar 1", "Mar 2", "Mar 3", "Mar 4", "Mar 5", "Mar 6", "Mar 7", "Mar 8", "Mar 9", "Mar 10", "Mar 11", "Mar 12", "Mar 13"],
        datasets: [{
          label: "Sessions",
          data: [10000, 30162, 26263, 18394, 18287, 28682, 31274, 33259, 25849, 24159, 32651, 31984, 38451],
          backgroundColor: "rgba(2,117,216,0.2)",
          borderColor: "rgba(2,117,216,1)",
          tension: 0.3,
        }]
      },
      options: {}
    });

    // Bar Chart
    const ctxBar = document.getElementById('myBarChart') as HTMLCanvasElement;
    new Chart(ctxBar, {
      type: 'bar',
      data: {
        labels: ["January", "February", "March", "April", "May", "June"],
        datasets: [{
          label: "Revenue",
          data: [4215, 5312, 6251, 7841, 9821, 14984],
          backgroundColor: "rgba(2,117,216,1)",
          borderColor: "rgba(2,117,216,1)"
        }]
      },
      options: {}
    });

    // Pie Chart
    const ctxPie = document.getElementById('myPieChart') as HTMLCanvasElement;
    new Chart(ctxPie, {
      type: 'pie',
      data: {
        labels: ["Blue", "Red", "Yellow", "Green"],
        datasets: [{
          data: [12.21, 15.58, 11.25, 8.32],
          backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745']
        }]
      },
      options: {}
    });
  }

}

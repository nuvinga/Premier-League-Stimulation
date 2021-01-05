import { Component } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})

export class NavBarComponent {

  homeNavLinks = [ // Labeling to show appropriate label in button
    {
      label: 'Home',
      path: ''
    },
    {
      label: 'Clubs',
      path: 'clubs'
    },
    {
      label: 'Matches',
      path: 'matches'
    },
    {
      label: 'Generator',
      path: 'generate'
    },
    {
      label: 'Search',
      path: 'search'
    }
  ];

  constructor() { }

}

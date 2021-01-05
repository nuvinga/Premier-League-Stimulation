import { Component } from '@angular/core';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})

export class LandingPageComponent {

  matchesCardLinks = [ // Labeling to show appropriate label in button - Labels for Matches card
    {
      label: 'VIEW MATCHES',
      path: 'matches'
    },
    {
      label: 'GENERATE',
      path: 'generate'
    },
    {
      label: 'SEARCH',
      path: 'search'
    }
  ];

  clubsCardLinks = [ // Labeling to show appropriate label in button - Labels for Club card
    {
      label: 'VIEW LEADERBOARD',
      path: 'clubs'
    },
  ];

  constructor() { }

}

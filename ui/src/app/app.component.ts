import {Component, HostListener} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})

export class AppComponent {
  @HostListener('window:beforeunload', ['$event'])
  doSomething($event) {

  }
}

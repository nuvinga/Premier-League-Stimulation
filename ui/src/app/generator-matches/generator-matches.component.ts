import { Component } from '@angular/core';
import { MatDialog } from "@angular/material/dialog";
import { MatchPopUpComponent } from "../match-pop-up/match-pop-up.component";

@Component({
  selector: 'app-generator-matches',
  templateUrl: './generator-matches.component.html',
  styleUrls: ['./generator-matches.component.css']
})

export class GeneratorMatchesComponent {

  constructor(public dialog: MatDialog) { }

  openDialog() {
    this.dialog.open(MatchPopUpComponent); //opens the pop-up dialog to show results of the match
  }

}

import { Component } from '@angular/core';
import {FormControl, Validators} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {SearchPopUpComponent} from "../search-pop-up/search-pop-up.component";

@Component({
  selector: 'app-search-matches',
  templateUrl: './search-matches.component.html',
  styleUrls: ['./search-matches.component.css']
})

export class SearchMatchesComponent {

  value='';
  dateFormControl = new FormControl('', [
    Validators.required,
    Validators.min(2020), // straining a minimum year - 2020
    Validators.max(2021), // straining a maximum year - 2021
  ]);

  constructor(public dialog: MatDialog) { }

  openTableDialog(){
    if (this.dateFormControl.valid) { // checking is the input in the text-field is valid
      this.dialog.open(SearchPopUpComponent, {
        data: this.dateFormControl.value // when valid, opens search-pop-up, containing the table
      });
    }else{
      alert("Please enter a valid date"); // if date is invalid, alert the user an error
    }
  }

}

import {AfterViewInit, Component, Inject} from '@angular/core';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { AppService } from "../services/app.service";
import { MatTableDataSource } from "@angular/material/table";

@Component({
  selector: 'app-search-pop-up',
  templateUrl: './search-pop-up.component.html'
})

export class SearchPopUpComponent implements AfterViewInit {

  displayedColumns: string[] = ['date', 'teamOne', 'scoreTeamOne', 'teamTwo', 'scoreTeamTwo', 'winTeam'];
  matchData: any[] = [];
  dataSource = null;
  dataAvailable: boolean = false;
  status: boolean = false;
  dataLoaded: boolean = false;

  constructor(private dataService : AppService, @Inject(MAT_DIALOG_DATA) public date: String) { }

  ngAfterViewInit(): void {
    console.log(this.date);
    // The service send a param to the backend using the GET Params. Therefore, sends a parameter to the service
    this.dataService.searchDate(this.date).subscribe((data : any)=> {
      console.log("~~~ DATA FOR SEARCH MATCHES ~~~");
      console.log("-- RAW RETRIEVED DATA FROM BACKEND --");
      console.log(data);
      this.status = data.status; // Loading status from response
      if (this.status==true) {
        this.matchData = data.response; // Loading array from response
        this.dataAvailable = this.matchData.length > 0; // Checking for availability of data in response array
        this.dataSource = new MatTableDataSource(this.matchData); // Setting the array to the datasource of the table
      }
      console.log("-- SEARCH MATCHES FOR DATE DATA AND STATUS --");
      console.log(this.matchData);
      console.log(this.status);
      this.dataLoaded = true; // notifies that the data has been loaded
    });
  }

}

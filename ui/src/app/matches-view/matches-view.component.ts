import { AfterViewInit, Component } from '@angular/core';
import { AppService } from "../services/app.service";
import { MatTableDataSource } from "@angular/material/table";

@Component({
  selector: 'app-matches-view',
  templateUrl: './matches-view.component.html',
  styleUrls: ['./matches-view.component.css']
})

export class MatchesViewComponent implements AfterViewInit {

  displayedColumns: string[] = ['date', 'teamOne', 'scoreTeamOne', 'teamTwo', 'scoreTeamTwo', 'winTeam'];
  matchData: any[] = [];
  dataSource = null;
  status: boolean = false;
  dataLoaded: boolean = false;

  constructor(private dataService : AppService) { }

  /**
   * HTML initialization will subscribe to the backend as soon as the component is open, however, will wait till the data is received
   *    after initialization
   */
  ngAfterViewInit(): void {
    this.dataService.getMatchesData().subscribe((data : any) => {
      console.log("~~~ DATA FOR MATCHES ~~~");
      console.log("-- RAW RETRIEVED DATA FROM BACKEND --");
      console.log(data);
      this.status = data.status; //accessing status from received response
      if (this.status == true) {
        this.matchData = data.response; //is status is valid, accessing data array from received data
        this.dataSource = new MatTableDataSource(this.matchData); // applying response array as data source
      }
      console.log("-- FINAL MATCHES DATA AND STATUS --");
      console.log(this.matchData);
      console.log(this.status);
      this.dataLoaded = true; // notifies that the data has been loaded
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}

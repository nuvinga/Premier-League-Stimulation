import { AfterViewInit, Component } from '@angular/core';
import { AppService } from "../services/app.service";

export interface Club {
  //This is a interface created for easy data identification when populating the table
  name: string;
  location: string;
  dateOfFormation: string;
  coach: string;
  matchesPlayed: number;
  numWins: number;
  numDraws: number;
  numDefeats: number;
  goalScored: number;
  goalsConceded: number;
  goalsDifference: number;
  points: number;
  comparableDate: string;
}

@Component({
  selector: 'app-clubs-view',
  templateUrl: './clubs-view.component.html',
  styleUrls: ['./clubs-view.component.css']
})

export class ClubsViewComponent implements AfterViewInit {

  displayedColumns: string[] = ['name', 'location', 'dateOfFormation', 'coach', 'matchesPlayed', 'numWins', 'numDraws',
    'numDefeats', 'goalsScored', 'goalsConceded', 'goalDifference', 'points'];
  clubData: Club[] = [];
  status: boolean = false;
  dataLoaded: boolean = false;

  constructor(private dataService : AppService) { }

  ngAfterViewInit(): void {
    this.sortToPoints(); //for the table to be sorted according to points first
  }

  /**
   * Function runs when toggle button selects sort to GOALS
   */
  sortToGoals(): void {
    this.dataService.getGoalsSortClubsData().subscribe((data : any) => {
      console.log("~~~ DATA FOR SORT TO GOALS ~~~");
      console.log("-- RAW RETRIEVED DATA FROM BACKEND --");
      console.log(data);
      this.status = data.status; //accessing status from received response
      if (this.status == true) this.clubData = data.response; //is status is valid, accessing data array from received data
      console.log("-- FINAL CLUB DATA AND STATUS SORTED TO GOALS --");
      console.log(this.clubData);
      console.log(this.status);
      this.dataLoaded = true;
    });
  }

  /**
   * Function runs when toggle button selects sort to POINTS
   */
  sortToPoints(): void {
    this.dataService.getPointsSortClubsData().subscribe((data : any) => {
      console.log("~~~ DATA FOR SORT TO POINTS ~~~");
      console.log("-- RAW RETRIEVED DATA FROM BACKEND --");
      console.log(data);
      this.status = data.status;
      if (this.status == true) this.clubData = data.response;
      console.log("-- FINAL CLUB DATA AND STATUS SORTED TO POINTS --");
      console.log(this.clubData);
      console.log(this.status);
      this.dataLoaded = true;
    });
  }

  /**
   * Function runs when toggle button selects sort to WINS
   */
  sortToWins(): void {
    this.dataService.getWinsSortClubsData().subscribe((data : any) => {
      console.log("~~~ DATA FOR SORT TO WINS ~~~");
      console.log("-- RAW RETRIEVED DATA FROM BACKEND --");
      console.log(data);
      this.status = data.status;
      if (this.status == true) this.clubData = data.response;
      console.log("-- FINAL CLUB DATA AND STATUS SORTED TO WINS --");
      console.log(this.clubData);
      console.log(this.status);
      this.dataLoaded = true;
    });
  }

}

import { AfterViewInit, Component } from '@angular/core';
import { AppService } from "../services/app.service";

@Component({
  selector: 'app-match-pop-up',
  templateUrl: './match-pop-up.component.html'
})

export class MatchPopUpComponent implements AfterViewInit {
  finalData: any = null;

  date: string = "";
  homeTeam: string = "";
  awayTeam: string = "";
  gsHome: string = "";
  gsAway: string = "";
  winner: string = "";
  runnerUp: string = "";

  draw: boolean = false;
  status: boolean = false;
  dataLoaded: boolean = false;

  constructor(private dataService : AppService) {}

  /**
   * Generator will subscribe to the backend as soon as the component is open, however, will wait till the data is received
   *    after initialization
   */
  ngAfterViewInit(): void {
    this.dataService.generateMatch().subscribe((data : any) => {
      console.log("~~~ DATA FOR MATCH GENERATOR ~~~");
      console.log("-- RAW RETRIEVED DATA FROM BACKEND --");
      console.log(data);
      this.status = data.status; //response status
      this.finalData = data.response; // response object
      if (this.status == true) {
        this.date = this.finalData.date; // accessing variables components from object
        this.draw = this.finalData.drawFlag;
        this.winner = this.finalData.winner;
        this.gsHome = this.finalData.winnerScore;
        this.runnerUp = this.finalData.runner;
        this.gsAway = this.finalData.runnerScore;
        this.homeTeam = this.winner;
        this.awayTeam = this.runnerUp;
      }
      console.log("-- FINAL CLUB DATA SHOWN IN LOG BELOW --");
      console.log(this.finalData);
      this.dataLoaded = true;
    });
  }

}

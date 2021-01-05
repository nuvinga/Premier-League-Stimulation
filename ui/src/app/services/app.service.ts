import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

/**
 * This class depicts the URLs
 */
@Injectable()

export class AppService {
  /**
   * The URLS
   * @private
   */
  private clubDataPath = '/api/getClubs';
  private winsSortClubDataPath = '/api/getWinsSortClubs';
  private goalsSortClubDataPath = '/api/getGoalsSortClubs';
  private matchesDataPath = '/api/getMatches';
  private generateMatchPath = '/api/generateMatch';
  private searchDatePath = '/api/searchForDate/date';

  constructor(private http: HttpClient) { }

  /**
   * For clubs-view component
   */
  public getPointsSortClubsData() { return this.http.get(this.clubDataPath); }

  public getWinsSortClubsData() { return this.http.get(this.winsSortClubDataPath); }

  public getGoalsSortClubsData() { return this.http.get(this.goalsSortClubDataPath); }

  /**
   * For matches-view component
   */
  public getMatchesData() { return this.http.get(this.matchesDataPath); }

  /**
   * For generator component
   */
  public generateMatch() { return this.http.get(this.generateMatchPath); }

  /**
   * For search component (Sends a param along with the request)
   * @param data
   */
  public searchDate(data: String) { return this.http.get(this.searchDatePath+data); }

}

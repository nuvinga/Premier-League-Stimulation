package entities.Football;

import java.util.Objects;

public class FootballClub extends entities.SportsClub implements Comparable<FootballClub>{

    private int numWins = 0;
    private int numDraws = 0;
    private int numDefeats = 0;
    private int matchesPlayed = 0;
    private int goalsScored = 0;
    private int goalsConceded = 0;
    private int goalDifference = 0;
    private int points = 0;

    //constructor- new club registration (default)
    public FootballClub(String name, String location, String dateOfFormation, String coach){
        super(name, location, dateOfFormation,coach);
    }

    //constructor- existing club registration
    public FootballClub(String name, String location, String dateOfFormation, String coach, int numWins,
                        int numDraws, int numDefeats, int goalsScored, int goalsConceded) {
        super(name, location, dateOfFormation, coach);
        this.numWins = numWins;
        this.numDraws = numDraws;
        this.numDefeats = numDefeats;
        this.goalsScored = goalsScored;
        this.goalsConceded = goalsConceded;
        this.matchesPlayed = numWins+numDraws+numDefeats;
        this.goalDifference = goalsScored-goalsConceded;
        this.points = (numWins*3)+numDraws;
    }

    public void addWin(int goalsScored, int goalsConceded){
        // when a match is won, calls this function to add wins
        setNumWins(getNumWins() + 1);
        setMatchesPlayed(getMatchesPlayed() + 1);
        setGoalsScored(getGoalsScored() + goalsScored);
        setGoalsConceded(getGoalsConceded() + goalsConceded);
        setGoalDifference( getGoalsScored() - getGoalsConceded());
        setPoints(getPoints() + 3);
    }

    public void setNumWins(int numWins) {
        this.numWins = numWins;
    }

    public int getNumWins(){
        return this.numWins;
    }

    public void addDraw(int goalsScored, int goalsConceded){
        // when a match is draw, calls this function to add to draws and other required
        setNumDraws(getNumDraws() + 1);
        setMatchesPlayed(getMatchesPlayed() + 1);
        setGoalsScored(getGoalsScored() + goalsScored);
        setGoalsConceded(getGoalsConceded() + goalsConceded);
        setGoalDifference(getGoalsScored() - getGoalsConceded());
        setPoints(getPoints() + 1);
    }

    public void setNumDraws(int numDraws) {
        this.numDraws = numDraws;
    }

    public int getNumDraws(){
        return this.numDraws;
    }

    public void addDefeat(int goalsScored, int goalsConceded){
        // when a match is lost, calls this function to add to defeats and other required
        setNumDefeats(getNumDefeats() + 1);
        setMatchesPlayed(getMatchesPlayed() + 1);
        setGoalsScored(getGoalsScored() + goalsScored);
        setGoalsConceded(getGoalsConceded() + goalsConceded);
        setGoalDifference(getGoalsScored() - getGoalsConceded());
    }

    public void setNumDefeats(int numDefeats) {
        this.numDefeats = numDefeats;
    }

    public int getNumDefeats(){
        return this.numDefeats;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getMatchesPlayed(){
        return this.matchesPlayed;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsScored(){
        return this.goalsScored;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public int getGoalsConceded(){
        return this.goalsConceded;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getGoalDifference(){
        return this.goalDifference;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints(){
        return this.points;
    }

    @Override
    public String toString(){
        return
                "\nFootball Club- \nSeasonal Information- "+"Number of wins: "+numWins+
                        "; Number of draws: "+numDraws+"; Number of defeats: "+numDefeats+
                        "; Matches played: "+matchesPlayed+"; Total goals scored: "+goalsScored+
                        "; Total goals conceded: "+goalsConceded+"; Difference of goals: "+goalDifference+
                        "; Total points: "+points+"\n"+super.toString();
    }

    @Override
    public boolean equals(Object club) {
        if (this == club){
            return true;
        }
        if (!(club instanceof FootballClub)){
            return false;
        }
        if (!super.equals(club)){
            return false;
        }
        FootballClub clubTwo = (FootballClub) club;
        return numWins == clubTwo.numWins &&
                numDraws == clubTwo.numDraws &&
                numDefeats == clubTwo.numDefeats &&
                matchesPlayed == clubTwo.matchesPlayed &&
                goalsScored == clubTwo.goalsScored &&
                goalsConceded == clubTwo.goalsConceded &&
                goalDifference == clubTwo.goalDifference &&
                points == clubTwo.points;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numWins, numDraws, numDefeats, matchesPlayed, goalsScored, goalsConceded,
                goalDifference, points);
    }

    @Override
    public int compareTo(FootballClub comparer) {
        if(this.getPoints() > comparer.getPoints()){
            return 1;
        }else{
            if(this.getPoints() < comparer.getPoints()){
                return -1;
            }else{
                int clubOneGoalDifference = this.getGoalsScored() - this.getGoalsConceded();
                int clubTwoGoalDifference = comparer.getGoalsScored() - comparer.getGoalsConceded();
                if (clubOneGoalDifference > clubTwoGoalDifference) {
                    return 1;
                } else if (clubTwoGoalDifference > clubOneGoalDifference) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

}

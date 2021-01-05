package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Match implements Serializable, Comparable<Match>{

    private final Date date;
    private final SportsClub teamOne;
    private final SportsClub teamTwo;
    private final int scoreTeamOne;
    private final int scoreTeamTwo;
    private static final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public Match(String date, SportsClub teamOne, SportsClub teamTwo, int scoreTeamOne, int scoreTeamTwo) throws ParseException {
        this.date = format.parse(date);
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.scoreTeamOne = scoreTeamOne;
        this.scoreTeamTwo = scoreTeamTwo;
    }

    public String getDate(){
        return(format.format(this.date));
    }

    public Date comparableDate(){
        return this.date;
    }

    public String getTeamOne(){
        return this.teamOne.getName();
    }

    public String getTeamTwo(){
        return this.teamTwo.getName();
    }

    public int getScoreTeamOne(){
        return this.scoreTeamOne;
    }

    public int getScoreTeamTwo(){
        return this.scoreTeamTwo;
    }

    public String getWinTeam() {
        if (scoreTeamOne>scoreTeamTwo) return (teamOne.getName()+"- Win");
        else if (scoreTeamOne<scoreTeamTwo) return (teamTwo.getName()+"- Win");
        else return "Match is a draw";
    }

    public String getResult(){
        if (scoreTeamOne>scoreTeamTwo) return "one";
        else if (scoreTeamOne<scoreTeamTwo) return "two";
        else return "draw";
    }

    @Override
    public String toString(){
        return
                "\nMatch-\nDate: "+ getDate()+"; Team One name: "+teamTwo+"; Team Two Name: "+teamTwo+
                        "; Team One Score: "+scoreTeamOne+"; Team Two Score: "+scoreTeamTwo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Match)) return false;
        Match match = (Match) obj;
        return scoreTeamOne == match.scoreTeamOne &&
                scoreTeamTwo == match.scoreTeamTwo &&
                date.equals(match.date) &&
                teamOne.equals(match.teamOne) &&
                teamTwo.equals(match.teamTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, teamOne, teamTwo, scoreTeamOne, scoreTeamTwo);
    }

    @Override
    public int compareTo(Match comparor) {
        if (this.comparableDate().after(comparor.comparableDate())){
            return 1;
        }else return -1;
    }

}

package entities.Football;

public class UniversityFootballClub extends FootballClub{

    private final String universityName;

    //constructor- new club registration (default)
    public UniversityFootballClub(String clubName, String location, String dateOfFormation, String coach, String universityName) {
        super(clubName, location, dateOfFormation, coach);
        this.universityName = universityName;
    }

    //constructor- existing club registration
    public UniversityFootballClub(String name, String location, String dateOfFormation, String coach,
                                  int numWins, int numDraws, int numDefeats, int goalsScored, int goalsConceded, String universityName) {
        super(name, location, dateOfFormation, coach, numWins, numDraws, numDefeats, goalsScored, goalsConceded);
        this.universityName = universityName;
    }

    public String getUniversityName(){
        return universityName;
    }

    @Override
    public String toString(){
        return "University "+super.toString()+"; University Name: "+universityName;
    }

}

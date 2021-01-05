package entities.Football;

public class SchoolFootballClub extends FootballClub{

    private final String schoolName;
    private final String ageCategory;

    //constructor- new club registration (default)
    public SchoolFootballClub(String name, String location, String dateOfFormation, String coach, String schoolName, String ageCategory) {
        super(name, location, dateOfFormation, coach);
        this.schoolName = schoolName;
        this.ageCategory = ageCategory;
    }

    //constructor- existing club registration
    public SchoolFootballClub(String name, String location, String dateOfFormation, String coach, int numWins, int numDraws,
                              int numDefeats, int goalsScored, int goalsConceded, String schoolName, String ageCategory) {
        super(name, location, dateOfFormation, coach, numWins, numDraws, numDefeats, goalsScored, goalsConceded);
        this.schoolName = schoolName;
        this.ageCategory = ageCategory;
    }

    public String getSchoolName(){
        return schoolName;
    }

    public String getAgeCategory(){
        return ageCategory;
    }

    @Override
    public String toString(){
        return "School "+super.toString()+"; School Name: "+schoolName+"; Age Category: "+ageCategory;
    }

}

package entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class SportsClub implements Serializable {

    private String name="";
    private String location="";
    private Date dateOfFormation;
    private String coach="";
    private static final DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    // main constructor
    public SportsClub(String name, String location, String dateOfFormation, String coach){
        this.name = name;
        this.location = location;
        try {
            this.dateOfFormation = dateFormatter.parse(dateOfFormation);
        }catch (ParseException e){
            System.out.println("OOPS THAT'S AN ERROR");
        }
        this.coach = coach;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDateOfFormation(Date dateOfFormation) {
        this.dateOfFormation = dateOfFormation;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    // getters
    public String getName(){
        return(this.name);
    }

    public String getLocation(){
        return(this.location);
    }

    public String getDateOfFormation(){
        return(dateFormatter.format(this.dateOfFormation));
    }

    public String getCoach(){
        return(this.coach);
    }

    public Date getComparableDate(){
        return this.dateOfFormation;
    }

    @Override
    public String toString(){
        return "Club Information- Club Name: "+getName()+"; Location: "+getLocation()+"; Date of Formation: "+getDateOfFormation()+
                "; Coach: "+getCoach();
    }

    @Override
    public boolean equals(Object club) {
        if (this == club){
            return true;
        }
        if (!(club instanceof SportsClub)){
            return false;
        }
        SportsClub clubTwo = (SportsClub) club;
        return name.equals(clubTwo.name) &&
                location.equals(clubTwo.location) &&
                dateOfFormation.equals(clubTwo.dateOfFormation) &&
                coach.equals(clubTwo.coach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, dateOfFormation, coach);
    }

}

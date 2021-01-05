package entities;

import entities.Football.FootballClub;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class SportsClubTest {

    private final SportsClub testClub = new FootballClub("Man United", "Manchester, United",
            "02/01/2020", "J. F. Kennedy");

    @Test
    public void setName() {
        testClub.setName("Arsenal");
        String expectedName = "Arsenal";
        assertEquals(expectedName, testClub.getName());
        System.out.println("Name set verification test - passed");
    }

    @Test
    public void getName() {
        String actualName = testClub.getName();
        String expectedName = "Man United";
        assertEquals(expectedName, actualName);
        System.out.println("Name verification test - passed");
    }

    @Test
    public void setLocation() {
        testClub.setLocation("Arsenal, United");
        String expectedLocation = "Arsenal, United";
        assertEquals(expectedLocation, testClub.getLocation());
        System.out.println("Location set verification test - passed");
    }

    @Test
    public void getLocation() {
        String actualLocation = testClub.getLocation();
        String expectedLocation = "Manchester, United";
        assertEquals(expectedLocation, actualLocation);
        System.out.println("Location verification test - passed");
    }

    @Test
    public void setDateOfFormation() throws ParseException {
        testClub.setDateOfFormation(new SimpleDateFormat("dd/MM/yyy").parse("04/02/2020"));
        String expectedDate = "04/02/2020";
        assertEquals(expectedDate, testClub.getDateOfFormation());
        System.out.println("Date set verification test - passed");
    }

    @Test
    public void getDateOfFormation() {
        String actualDateOfFormation = testClub.getDateOfFormation();
        String expectedDateOfFormation = "02/01/2020";
        assertEquals(expectedDateOfFormation, actualDateOfFormation);
        System.out.println("DOF verification test - passed");
    }

    @Test
    public void setCoach() {
        testClub.setCoach("B. Obama");
        String expectedCoach = "B. Obama";
        assertEquals(expectedCoach, testClub.getCoach());
        System.out.println("Coach set verification test - passed");
    }

    @Test
    public void getCoach() {
        String actualCoach = testClub.getCoach();
        String expectedCoach = "J. F. Kennedy";
        assertEquals(expectedCoach, actualCoach);
        System.out.println("Coach verification test - passed");
    }

    @Test
    public void getComparableDate() throws ParseException {
        Date expectedDate = testClub.getComparableDate();
        Date actualDate = new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2020");
        assertEquals(expectedDate, actualDate);
        System.out.println("Date (comparable) verification test - passed");
    }
}

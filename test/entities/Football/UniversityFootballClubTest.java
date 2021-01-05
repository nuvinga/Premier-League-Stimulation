package entities.Football;

import entities.SportsClub;
import org.junit.Test;

import static org.junit.Assert.*;

public class UniversityFootballClubTest {

    private final SportsClub testClub = new UniversityFootballClub("Man United", "Manchester, United",
            "02/01/2020", "J. F. Kennedy","Westminster");

    @Test
    public void getUniversityName() {
        String actualName = ((UniversityFootballClub)testClub).getUniversityName();
        String expectedName = "Westminster";
        assertEquals(expectedName, actualName);
        System.out.println("Uni Name verification test - passed");
    }

}

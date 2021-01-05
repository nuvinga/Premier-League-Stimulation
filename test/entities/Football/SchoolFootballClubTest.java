package entities.Football;

import entities.SportsClub;
import org.junit.Test;

import static org.junit.Assert.*;

public class SchoolFootballClubTest {

    private final SportsClub testClub = new SchoolFootballClub("Man United", "Manchester, United",
            "02/01/2020", "J. F. Kennedy","Lyceum", "Under 14");

    @Test
    public void getSchoolName() {
        String actualName = ((SchoolFootballClub)testClub).getSchoolName();
        String expectedName = "Lyceum";
        assertEquals(expectedName, actualName);
        System.out.println("School Name verification test - passed");
    }

    @Test
    public void getAgeCategory() {
        String actualCategory = ((SchoolFootballClub)testClub).getAgeCategory();
        String expectedCategory = "Under 14";
        assertEquals(expectedCategory, actualCategory);
        System.out.println("Age category verification test - passed");
    }

}

package utilities;

import org.junit.Test;
import utilities.customErrors.InputFormatError;

import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilityTest {

    @Test
    public void produceDate() throws InputFormatError {
        String actualDate = DateUtility.produceDate("2020", 4,2);
        String expectedDate = "02/04/2020";
        assertEquals(expectedDate, actualDate);
        System.out.println("Date producer test - pass");
    }

    @Test (expected = InputFormatError.class)
    public void testErrorProduceDate() throws InputFormatError {
        String actualDate = DateUtility.produceDate("2020", 2,31);
        String expectedDate = "02/04/2020";
        assertEquals(expectedDate, actualDate);
        System.out.println("Date producer test - pass");
    }
}

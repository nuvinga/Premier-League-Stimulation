package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import play.libs.Json;

import static org.junit.Assert.*;

public class JsonResponseUtilityTest {

    @Test
    public void createResponse() {
        JsonNode jsonNode = Json.toJson("test");
        ObjectNode actualResult = JsonResponseUtility.createResponse(jsonNode, true);

        ObjectNode expectedResult = Json.newObject();
        expectedResult.put("status", true);
        expectedResult.put("response", "test");

        assertEquals(expectedResult, actualResult);
        System.out.println("Response creation- passed");
    }
}

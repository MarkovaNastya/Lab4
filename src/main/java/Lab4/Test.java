package Lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Test {

    private final static String TEST_NAME = "testName";
    private final static String EXPECTED_RESULT = "expectedResult";
    private final static String PARAMS = "params";

    private final String testName;
    private final String expectedResult;
    private final Object[] params;


    @JsonCreator

    public Test(String testName, String expectedResult, Object[] params) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }
}

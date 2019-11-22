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

    public Test(@JsonProperty(TEST_NAME) String testName,
                @JsonProperty(EXPECTED_RESULT) String expectedResult,
                @JsonProperty(PARAMS) Object[] params) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public String getTestName() {
        return testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public Object[] getParams() {
        return params;
    }
}

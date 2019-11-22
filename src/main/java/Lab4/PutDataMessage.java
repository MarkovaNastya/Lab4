package Lab4;

public class PutDataMessage {

    private final int packageId;
    private final String res;
    private final String expectedRes;
    private final boolean isCorrectAnswer;
    private final Object[] params;
    private final String testName;

    public PutDataMessage(int packageId, String res, String expectedRes, Object[] params, String testName) {
        this.packageId = packageId;
        this.res = res;
        this.expectedRes = expectedRes;
        this.isCorrectAnswer = res.equals(expectedRes);
        this.params = params;
        this.testName = testName;
    }

    public int getPackageId() {
        return packageId;
    }

    public String getRes() {
        return res;
    }

    public String getExpectedRes() {
        return expectedRes;
    }

    public boolean isCorrectAnswer() {
        return isCorrectAnswer;
    }

    public Object[] getParams() {
        return params;
    }

    public String getTestName() {
        return testName;
    }
}

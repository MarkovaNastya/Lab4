package Lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class InputJSMessage {

    private final static String PACKAGE_ID = "packageId";
    private final static String JS_SCRIPT = "jsScript";
    private final static String FUNCTION_NAME = "functionName";
    private final static String TESTS = "tests";



//    POST
//    {
//        "packageId":"11",
//            "jsScript":"var divideFn = function(a,b) { return a/b} ",
//            "functionName":"divideFn",
//            "tests": [
//        {"testName":"test1",
//                "expectedResult":"2.0",
//                "params":[2,1]
//        },
//        {"testName":"test2",
//                "expectedResult":"2.0",
//                "params":[4,2]
//        }
//]
//    }


}

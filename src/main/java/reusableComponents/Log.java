package reusableComponents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import testBase.TestBase;

public class Log extends TestBase {
    private static Logger Log = LogManager.getLogger(Log.class.getName());

    public static void startTestCase(String sTestCaseName) {
        Log.info("----------   " + sTestCaseName + "    ----------------");
        test.info("----------   " + sTestCaseName + "    ----------------"); //for logging in extent report
    }

    public static void endTestCase(String sTestCaseName) {
        Log.info("----------   " +sTestCaseName + "Test Case Execution Ends" + "    ----------------");
        test.info("----------   " +sTestCaseName + "Test Case Execution Ends" + "    ----------------");
    }

    public static void info(String message) {
        Log.info(message);
        test.info(message);

    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
        test.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
        test.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
        test.debug(message);
    }

}

package LogFilterTests;

import LogFilter.Run;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * Created by Sander on 18-9-2015.
 */
public class LogFilterRunTest {
    @Test
    public void testOutputIsEqualToExpectedOutputInFixture() throws Exception {
        try {
            String[] args = {"src/test/resources/fixtures", "inputLogs.txt", "filteredLogsOutput.txt", "Error", "InputHandler"};

            // run LogFilter with args
            Run.main(args);

            File fileOutput = new File(args[0], args[2]);
            File fileOutputSample = new File(args[0], "sampleFilteredLogsOutput.txt");

            // the output file should equal the output fixture file
            Assert.assertTrue(FileUtils.contentEquals(fileOutput, fileOutputSample));
        } catch (Exception exception) {
            System.out.println("Exception testOutputIsEqualToExpectedOutputInFixture: " + exception);
            exception.printStackTrace();
        }
    }

    @Test
    public void testOutputStaysSameAfterRepeatRun() throws Exception {
        try {
            String[] args = {"src/test/resources/fixtures", "inputLogs.txt", "filteredLogsOutput.txt", "Error", "InputHandler"};

            // run LogFilter with args
            Run.main(args);

            // now use output as input, should stay the same after repeat run
            args[1] = "filteredLogsOutput.txt";
            args[2] = "filteredLogsOutputAfterRepeatRun.txt";
            Run.main(args);

            File fileOutput = new File(args[0], args[1]);
            File fileOutputAfterRepeatRun = new File(args[0], args[2]);

            // the output file should equal the output fixture file
            Assert.assertTrue(FileUtils.contentEquals(fileOutput, fileOutputAfterRepeatRun));
        } catch (Exception exception) {
            System.out.println("Exception testOutputStaysSameAfterRepeatRun: " + exception);
            exception.printStackTrace();
        }
    }
}
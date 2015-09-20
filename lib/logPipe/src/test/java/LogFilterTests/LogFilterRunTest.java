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
    public void testRun() throws Exception {
        try {
            String[] args = {"src/test/resources/fixtures", "inputLogs.txt", "filteredLogsOutput.txt", "Error", "InputHandler"};

            // run LogFilter with args
            Run.main(args);

            File fileOutput = new File(args[0], args[2]);
            File fileOutputSample = new File(args[0], "sampleFilteredLogsOutput.txt");

            // the output file should equal the output fixture file
            Assert.assertTrue(FileUtils.contentEquals(fileOutput, fileOutputSample));
        } catch (Exception exception) {
            System.out.println("Exception LogFilterRunTest: " + exception);
            exception.printStackTrace();
        }
    }
}
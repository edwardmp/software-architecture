package LogFilterTests;

import LogFilter.Run;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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

            // this strips new line characters, because comparison across different systems failed (due to CR+LF and CR differences)
            List<String> linesFilteredLogsOutput = Files.readAllLines(Paths.get(args[0], args[2]), Charset.forName("UTF-8"));
            List<String> linesFilteredLogsOutputFixture = Files.readAllLines(Paths.get(args[0], "sampleFilteredLogsOutput.txt"), Charset.forName("UTF-8"));

            // the output lines should equal the lines generated from output fixture file
            Assert.assertEquals(linesFilteredLogsOutput, linesFilteredLogsOutputFixture);
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

            // this strips new line characters, because comparison across different systems failed (due to CR+LF and CR differences)
            List<String> linesFilteredLogsOutput = Files.readAllLines(Paths.get(args[0], args[1]), Charset.forName("UTF-8"));
            List<String> linesFilteredLogsOutputAfterRepeatRun = Files.readAllLines(Paths.get(args[0], args[2]), Charset.forName("UTF-8"));

            // the lists with lines should be equal
            Assert.assertEquals(linesFilteredLogsOutput, linesFilteredLogsOutputAfterRepeatRun);
        } catch (Exception exception) {
            System.out.println("Exception testOutputStaysSameAfterRepeatRun: " + exception);
            exception.printStackTrace();
        }
    }
}
package QCRobot;

import LogFilter.Run;

import java.util.Scanner;

/**
 * Created by Sander on 18-9-2015.
 */
public class QCRobotRunTest {
    public void testMain() throws Exception {

        try {
            final Scanner scanner = new Scanner("inputLogs.txt");
            int foundErrorLinesIn = 0;
            while (scanner.hasNextLine()) {
                final String lineFromFile = scanner.nextLine();
                if(lineFromFile.contains("Error")) {
                    foundErrorLinesIn += 1;
                }
            }

            Run.main(new String[0]);
            int foundErrorLinesOut = 0;
            while (scanner.hasNextLine()) {
                final String lineFromFile = scanner.nextLine();
                if(lineFromFile.contains("Error")) {
                    foundErrorLinesOut += 1;
                }
            }
            //assertEquals(1, 0);

        } catch (Exception exception) {
            System.out.println("Exception LogLevelFilter: " + exception);
            exception.printStackTrace();
        }
    }
}
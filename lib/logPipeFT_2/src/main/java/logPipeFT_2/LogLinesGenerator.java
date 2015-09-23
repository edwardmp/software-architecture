package logPipeFT_2;

import java.util.ArrayList;
import java.util.Random;

import com.qualitycare.robot.pipefilter.Generator;
import com.qualitycare.robot.pipefilter.Pipe;

public class LogLinesGenerator extends Generator<ArrayList<LogLine>> {

    public LogLinesGenerator(Pipe<ArrayList<LogLine>> output) {
        super(output);
    }

    @Override
    public void generateInto(Pipe<ArrayList<LogLine>> pipe) {
        System.out.println("");
        System.out.println("Start generating fake log lines:");

        pipe.put(createFakeLogLines());
        pipe.closeForWriting();
    }

    public ArrayList<LogLine> createFakeLogLines() {
        ArrayList<LogLine> logLines = new ArrayList<>();

        Random rand = new Random();

        String[] exampleLogLevels  = {"Info", "Debug"};
        String[] exampleLogModules  = {"InputHandler", "OutputHandler", "ControlCenter"};

        for (int i = 0; i < 20; i++) {
            String randomLogLine = String.format("This is log line sample %d", i);
            LogLine temp = new LogLine(exampleLogLevels[rand.nextInt(10) % exampleLogLevels.length], exampleLogModules[rand.nextInt(10) % exampleLogModules.length], randomLogLine);

            System.out.println(temp);
            logLines.add(i, temp);

        }

        System.out.println("");

        return logLines;
    }
}
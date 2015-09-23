package logPipeFT_2;

import com.qualitycare.robot.pipefilter.Pipe;
import com.qualitycare.robot.pipefilter.SimpleFilter;

import java.util.ArrayList;

public class LogLevelFilter extends SimpleFilter<ArrayList<LogLine>, ArrayList<LogLine>> {

    public LogLevelFilter(Pipe<ArrayList<LogLine>> input, Pipe<ArrayList<LogLine>> output) {
        super(input, output);
    }

    @Override
    protected ArrayList<LogLine> transformOne(ArrayList<LogLine> in) {
        ArrayList<LogLine> out = new ArrayList<>();

        for (int i = 0; i < in.size(); i++) {
            if (in.get(i) != null) {
                if (logLevelContainedInLogLine(in.get(i).getLevel())) {
                    out.add(in.get(i));
                }
            }
        }

        System.out.println("");
        System.out.println("Filter1: filtering logs with level Debug");

        return out;
    }

    public boolean logLevelContainedInLogLine(String logLevel) {
        if (logLevel.equals("Debug")) {
            return true;
        }
        return false;
    }
}
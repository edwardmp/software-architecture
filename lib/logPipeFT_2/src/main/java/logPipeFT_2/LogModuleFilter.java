package logPipeFT_2;

import com.qualitycare.robot.pipefilter.Pipe;
import com.qualitycare.robot.pipefilter.SimpleFilter;

import java.util.ArrayList;

public class LogModuleFilter extends SimpleFilter<ArrayList<LogLine>, String> {

    public LogModuleFilter(Pipe<ArrayList<LogLine>> input, Pipe<String> output) {
        super(input, output);
    }

    @Override
    protected String transformOne(ArrayList<LogLine> in) {
        String out = new String();

        System.out.println("");
        System.out.println("Filter2: filter all logs generated from module InputHandler");

        for (int i = 0; i < in.size(); i++) {
            if (in.get(i) != null) {
                if (logModuleNameContainedInLogLine(in.get(i).getModuleName())) {
                    out += (in.get(i)).toString() + "\n";
                }
            }
        }

        return out;
    }

    public boolean logModuleNameContainedInLogLine(String logModuleName) {
        if (logModuleName.equals("InputHandler")) {
            return true;
        }
        return false;
    }
}
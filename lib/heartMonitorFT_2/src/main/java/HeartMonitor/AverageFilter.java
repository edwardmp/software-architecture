package HeartMonitor;

import com.qualitycare.robot.pipefilter.SimpleFilter;
import com.qualitycare.robot.pipefilter.Pipe;

import java.util.List;

/**
 * Calculates the average of the total input.
 */
public class AverageFilter extends SimpleFilter<List<Integer>, Integer>{

    public AverageFilter(Pipe<List<Integer>> input, Pipe<Integer> output){
        super(input, output);
    }

    @Override
    protected Integer transformOne (List<Integer> in){
        int totalSum = 0;
        for (int i = 0; i < in.size(); i++) {
            totalSum += in.get(i);
        }
        return (totalSum/in.size());
    }
}

package HeartMonitor;

import com.qualitycare.robot.algorithm.health.bloodpressure.Bloodpressure;
import com.qualitycare.robot.pipefilter.SimpleFilter;
import com.qualitycare.robot.pipefilter.Pipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Filter diastolic blood pressure.
 */
public class DiastolicBloodPressureFilter extends SimpleFilter<Bloodpressure[], List<Integer>>{

    public DiastolicBloodPressureFilter(Pipe<Bloodpressure[]> input, Pipe<List<Integer>> output) {
        super(input, output);
    }

    @Override
    protected  List<Integer> transformOne (Bloodpressure[] in){
        List<Integer> out = new ArrayList<Integer>();
        for (int i = 0; i < in.length; i++){
            if (in[i] != null){
                out.add(in[i].getDiastolic());
            }
        }
        return out;
    }
}

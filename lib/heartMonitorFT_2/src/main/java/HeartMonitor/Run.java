package HeartMonitor;

import com.qualitycare.robot.algorithm.health.bloodpressure.Bloodpressure;
import com.qualitycare.robot.pipefilter.PipeImpl;
import com.qualitycare.robot.driver.sensors.SensorsGenerator;
import com.qualitycare.robot.pipefilter.Filter;
import com.qualitycare.robot.pipefilter.Generator;
import com.qualitycare.robot.pipefilter.Pipe;
import com.qualitycare.robot.pipefilter.Sink;

import java.util.List;

public class Run {

    public static void main(String[] args){
        final Pipe<Bloodpressure[]> pipe1 = new PipeImpl<Bloodpressure[]>();
        final Pipe<List<Integer>> pipe2 = new PipeImpl<List<Integer>>();
        final Pipe<Integer> pipe3 = new PipeImpl<Integer>();

        final Generator<Bloodpressure[]> generator = new SensorsGenerator(pipe1);
        final Filter<Bloodpressure[], List<Integer>> filter = new DiastolicBloodPressureFilter(pipe1, pipe2);
        final Filter<List<Integer>, Integer> filter2 = new AverageFilter(pipe2, pipe3);
        final Sink<Integer> sink = new AverageBloodPressureSink(pipe3);

        generator.start();
        filter.start();
        filter2.start();
        sink.start();
    }
}

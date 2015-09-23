package HeartMonitor;

import com.qualitycare.robot.pipefilter.Pipe;
import com.qualitycare.robot.pipefilter.Sink;

public class AverageBloodPressureSink extends Sink<Integer> {

    public AverageBloodPressureSink(Pipe<Integer> input) {
        super(input);
    }

    @Override
    public void takeFrom(Pipe<Integer> pipe) {
        try {
            Integer in;
            while ((in = pipe.nextOrNullIfEmptied()) != null) {
                System.out.println("Average diastolic bloodpressure: " + in);
            }
        }catch (InterruptedException e) {
                System.err.println("interrupted");
                e.printStackTrace();
        } finally {
            System.out.close();
        }
    }
}

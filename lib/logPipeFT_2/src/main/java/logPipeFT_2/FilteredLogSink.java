package logPipeFT_2;

import com.qualitycare.robot.pipefilter.Pipe;
import com.qualitycare.robot.pipefilter.Sink;

public class FilteredLogSink extends Sink<String> {

    public FilteredLogSink(Pipe<String> input) {
        super(input);
    }

    @Override
    public void takeFrom(Pipe<String> pipe) {
        try {
            String in;
            while ((in = pipe.nextOrNullIfEmptied()) != null) {
                System.out.println("");
                System.out.println("The result is that the filtered logs are: ");
                System.out.println(in);
            }
            System.out.println("Pipe filter finished");
        } catch (InterruptedException e) {
            System.err.println("interrupted");
            e.printStackTrace();
        } finally {
            System.out.close();
        }
    }
}
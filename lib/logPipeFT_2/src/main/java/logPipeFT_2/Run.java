package logPipeFT_2;

import com.qualitycare.robot.algorithm.health.EmergencyServicesSink;
import com.qualitycare.robot.algorithm.health.HealthDangerFilter;
import com.qualitycare.robot.algorithm.health.bloodpressure.Bloodpressure;
import com.qualitycare.robot.algorithm.health.bloodpressure.BloodpressureFilter;
import com.qualitycare.robot.driver.sensors.SensorsGenerator;
import com.qualitycare.robot.pipefilter.*;

import java.util.ArrayList;

public class Run
{
    public static void main( String[] args ) {
        System.out.println( "Starting pipe filter pattern example" );

        Run qc = new Run();
        qc.startPipeFilterExample();
    }

    public void startPipeFilterExample() {
        final Pipe<ArrayList<LogLine>> pipe1 = new PipeImpl<>();
        final Pipe<ArrayList<LogLine>> pipe2 = new PipeImpl<>();

        final Pipe<String> pipe3 = new PipeImpl<>();

        // create components that use the pipes
        final Generator<ArrayList<LogLine>> generator = new LogLinesGenerator(pipe1);
        final Filter<ArrayList<LogLine>,ArrayList<LogLine>> filter = new LogLevelFilter(pipe1, pipe2);
        final Filter<ArrayList<LogLine>, String> filter2 = new LogModuleFilter(pipe2, pipe3);
        final Sink<String> sink = new FilteredLogSink(pipe3);

        // start all components
        generator.start();
        filter.start();
        filter2.start();
        sink.start();
    }
}
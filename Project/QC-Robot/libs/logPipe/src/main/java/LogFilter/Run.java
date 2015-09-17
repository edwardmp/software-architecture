package LogFilter;

public class Run {
    public static void main(String args[]) {
        // create pipes to use
        Pipe pipeFirst = new PipeImpl();
        Pipe pipeSecond = new PipeImpl();

        // reads log lines from .txt file, puts them on first pipe
        Thread logFileReader = new LogFileReader(pipeFirst);

        // reads log lines from first pipe and adds them to second pipe
        Thread logLevelFilter = new LogLevelFilter(pipeFirst, pipeSecond, "Error");

        // reads log lines from second pipe and writes result to file
        Thread logModuleFilter = new LogModuleFilter(pipeSecond, "InputHandler");

        // start the reader and the filters
        logFileReader.start();
        logLevelFilter.start();
        logModuleFilter.start();
    }
}


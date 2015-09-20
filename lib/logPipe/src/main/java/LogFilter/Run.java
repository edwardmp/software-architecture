package LogFilter;

public class Run {
    public static void main(String args[]) {
        String filePath = (args != null && args.length != 0 && !args[0].isEmpty() ? args[0] : "");
        String inputFileName = (args != null && args.length != 0 && !args[1].isEmpty() ? args[1] : "");
        String outputFileName = (args != null && args.length != 0 && !args[2].isEmpty() ? args[2] : "");
        String logLevelToFilter = (args != null && args.length != 0 && !args[3].isEmpty() ? args[3] : "Error");
        String logModuleToFilter = (args != null && args.length != 0 && !args[4].isEmpty() ? args[4] : "InputHandler");

        // create pipes to use
        Pipe pipeFirst = new PipeImpl();
        Pipe pipeSecond = new PipeImpl();

        // reads log lines from .txt file, puts them on first pipe
        LogFileReader logFileReader = new LogFileReader(pipeFirst, filePath, inputFileName);

        // reads log lines from first pipe and adds them to second pipe
        LogLevelFilter logLevelFilter = new LogLevelFilter(pipeFirst, pipeSecond, logLevelToFilter);

        // reads log lines from second pipe and writes result to file
        LogModuleFilter logModuleFilter = new LogModuleFilter(pipeSecond, logModuleToFilter, filePath, outputFileName);

        // start the reader and the filters
        logFileReader.run();
        logLevelFilter.run();
        logModuleFilter.run();
    }
}


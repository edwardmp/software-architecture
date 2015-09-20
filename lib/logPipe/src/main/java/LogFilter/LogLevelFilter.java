package LogFilter;

public class LogLevelFilter {
    private Pipe pipeFirst = null;
    private Pipe pipeSecond = null;
    private String levelToFilter = null;

    public LogLevelFilter(Pipe _pipeFirst, Pipe _pipeSecond, String _levelToFilter) {
        if (_pipeFirst == null)
            throw new RuntimeException("No first pipe passed");

        if (_pipeSecond == null )
            throw new RuntimeException("No second pipe passed");

        if (_levelToFilter.equals("") || _levelToFilter.length() == 0)
            throw new RuntimeException("No log level to filter passed");

        pipeFirst = _pipeFirst;
        pipeSecond = _pipeSecond;
        levelToFilter = _levelToFilter;
    }

    public void run() {
        String logLine;

        try {
            // go through all lines and look for lines containing level equal to the levelToFilter
            while ((logLine = (String) pipeFirst.get()) != null)
                if (logLine.contains("[" + levelToFilter + "]"))
                    pipeSecond.put(logLine);

            pipeSecond.put(null);

        } catch (Exception exception) {
            System.out.println("Exception LogLevelFilter: " + exception);
            exception.printStackTrace();
        }
    }
}
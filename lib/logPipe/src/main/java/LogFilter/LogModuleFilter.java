package LogFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogModuleFilter {
    private Pipe pipe = null;
    private String moduleToFilter = null;

    private String filePath;
    private String outputFileName;

    public LogModuleFilter(Pipe _pipe, String _moduleToFilter, String _filePath, String _outputFileName) {
        if (_pipe == null)
            throw new RuntimeException("No pipe passed");

        if (_moduleToFilter.equals("") || _moduleToFilter.length() == 0)
            throw new RuntimeException("No module to filter passed");

        pipe = _pipe;
        moduleToFilter = _moduleToFilter;

        filePath = _filePath;
        outputFileName = _outputFileName;
    }

    public void run() {
        String logLine;

        try {
            File file = new File(filePath, outputFileName);

            if (!file.isFile() && !file.createNewFile()) {
                throw new IOException("Error creating new file: " + file.getAbsolutePath());
            }

            FileWriter fw = new FileWriter(file);

            while ((logLine = (String) pipe.get()) != null)
                if (logLine.contains("[" + moduleToFilter + "]"))
                    fw.write(logLine + "\n"); // print the resulting log list to a file

            fw.close();
        } catch (Exception exception) {
            System.out.println("Exception LogModuleFilter: " + exception);
            exception.printStackTrace();
        }
    }
}
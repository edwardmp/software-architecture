package LogFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogModuleFilter extends Thread {
    private Pipe pipe = null;
    private String moduleToFilter = null;

    public LogModuleFilter(Pipe _pipe, String _moduleToFilter) {
        if (_pipe == null)
            throw new RuntimeException("No pipe passed");

        if (_moduleToFilter.equals("") || _moduleToFilter.length() == 0)
            throw new RuntimeException("No module to filter passed");

        pipe = _pipe;
        moduleToFilter = _moduleToFilter;
    }

    public void run() {
        String logLine;

        try {
            File file = new File(".", "filteredLogs.txt");

            if (!file.isFile() && !file.createNewFile())
            {
                throw new IOException("Error creating new file: " + file.getAbsolutePath());
            }

            FileWriter fw = new FileWriter("filteredLogs.txt");

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
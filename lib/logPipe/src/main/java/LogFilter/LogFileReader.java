package LogFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LogFileReader extends Thread {
    private Pipe pipe = null;

    public LogFileReader(Pipe _pipe) {
        if (_pipe == null)
            throw new RuntimeException("No pipe passed");

        pipe = _pipe;
    }

    public void run() {
        try {
            File file = new File(".", "inputLogs.txt");

            if (!file.isFile() && !file.createNewFile())
            {
                throw new IOException("Error creating new file: " + file.getAbsolutePath());
            }

            BufferedReader br = new BufferedReader(new FileReader("inputLogs.txt"));
            String logLine = null;
            while((logLine = br.readLine()) != null)
                pipe.put(logLine);

            pipe.put(null); // null signals no more input
            br.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
}
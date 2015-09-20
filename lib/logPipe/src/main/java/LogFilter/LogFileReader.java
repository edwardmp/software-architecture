package LogFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LogFileReader {
    private Pipe pipe = null;

    private String filePath;
    private String fileName;

    public LogFileReader(Pipe _pipe, String _filePath, String _fileName) {
        if (_pipe == null)
            throw new RuntimeException("No pipe passed");

        pipe = _pipe;
        filePath = _filePath;
        fileName = _fileName;
    }

    public void run() {
        try {
            File file = new File(filePath, fileName);

            if (!file.isFile() && !file.createNewFile()) {
                throw new IOException("Error creating new file: " + file.getAbsolutePath());
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String logLine = null;
            while((logLine = br.readLine()) != null) {
                pipe.put(logLine);
            }

            pipe.put(null); // null signals no more input
            br.close();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
}
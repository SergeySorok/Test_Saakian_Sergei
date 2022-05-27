package org.example.repo;

import java.io.*;

public class FileWorker {
    File file;
    String filePath;

    public FileWorker() throws IOException {
        requestAndSetFilePath();
        setFile(filePath);
    }

    public void requestAndSetFilePath() throws IOException {
        System.out.println("Specify the path to the file with the extension .json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        this.filePath = bufferedReader.readLine();
        bufferedReader.close();
    }

    public String getFilePath() {
        return filePath;
    }

    private void setFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found");
        } else {
            this.file = file;
        }
    }

    public File getFile() {
        return file;
    }
}

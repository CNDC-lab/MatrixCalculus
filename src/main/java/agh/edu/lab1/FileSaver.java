package agh.edu.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {
    FileWriter writer;
    private final String absolutePath;
    private final String fileName;

    public FileSaver(String fileName) {
        this.fileName = fileName;
        this.absolutePath = new File(".").getAbsolutePath();
        try {
            writer = new FileWriter(fileName);
            writer.write("Dimension(2^x);Time(nanoseconds);Threshold;\n");
            writer.flush();
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found in " + absolutePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred while opening " + fileName);
            e.printStackTrace();
        }
    }

    public void saveRecord(String toWrite) {

        try {
            writer.write(toWrite);
            writer.flush();
        } catch (Exception e) {
            System.out.println("An error occurred (" + fileName + ")");
            e.printStackTrace();
        }
    }

}


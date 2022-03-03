package com.lior.handlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHendler {

    public FileHendler() {

    }

    public void createFile(String fileName) {
        try {
            File file = new File("/Users/liorgal/Desktop/Lior/lior_java/2048/outputdata",fileName);
            file.getParentFile().mkdirs();
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void write(String filename,String data) throws IOException {
        BufferedWriter myWriter = new BufferedWriter(new FileWriter(filename));
        try {
            myWriter.write(data);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }finally {
            myWriter.close();
        }
    }
}

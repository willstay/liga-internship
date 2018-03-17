package ru.liga.songtask.printer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintToFile implements Print {
    private final String fileName;
    public PrintToFile(String fileName){
        this.fileName = fileName;
    }
    public void print(String stringToPrint){
        try (PrintStream out = new PrintStream(new FileOutputStream(fileName))) {
            out.print(stringToPrint);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

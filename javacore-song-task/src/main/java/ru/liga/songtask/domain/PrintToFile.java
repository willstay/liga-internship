package ru.liga.songtask.domain;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintToFile implements Print {
    String fileName;
    String stringToPrint;
    public PrintToFile(String fileName, String stringToPrint){
        this.fileName = fileName;
        this.stringToPrint = stringToPrint;
    }
    public void print(){
        try (PrintStream out = new PrintStream(new FileOutputStream(fileName))) {
            out.print(stringToPrint);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

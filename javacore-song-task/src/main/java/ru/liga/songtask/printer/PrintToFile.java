package ru.liga.songtask.printer;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

@Slf4j
public class PrintToFile implements Print {
    private final String fileName;

    public PrintToFile(String fileName) {
        this.fileName = fileName;
    }

    public void print(String stringToPrint) {
        try (PrintStream out = new PrintStream(new FileOutputStream(fileName))) {
            out.print(stringToPrint);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}

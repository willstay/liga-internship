package ru.liga.songtask.printer;

public class PrintToConsole implements Print {
    public void print(String stringToPrint) {
        System.out.println(stringToPrint);
    }
}

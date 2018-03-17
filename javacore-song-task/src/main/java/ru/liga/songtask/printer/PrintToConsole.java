package ru.liga.songtask.printer;

public class PrintToConsole implements Print {
    String stringToPrint;
    public PrintToConsole(String stringToPrint){
        this.stringToPrint = stringToPrint;
    }

    public void print(){
        System.out.println(stringToPrint);
    }
}

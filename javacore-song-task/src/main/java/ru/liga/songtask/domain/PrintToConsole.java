package ru.liga.songtask.domain;

public class PrintToConsole implements Print {
    String stringToPrint;
    public PrintToConsole(String stringToPrint){
        this.stringToPrint = stringToPrint;
    }

    public void print(){
        System.out.println(stringToPrint);
    }
}

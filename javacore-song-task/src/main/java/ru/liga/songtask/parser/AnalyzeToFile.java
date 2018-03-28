package ru.liga.songtask.parser;

import ru.liga.songtask.analyzer.DoAnalyze;
import ru.liga.songtask.printer.Print;
import ru.liga.songtask.printer.PrintToFile;
import ru.liga.songtask.resources.Resources;

public class AnalyzeToFile implements InterfaceJob {
    private final String fileName;

    AnalyzeToFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void doJob() {
        Print printToFile = new PrintToFile(fileName + ".txt");
        printToFile.print(new DoAnalyze(new Resources(fileName).getFile()).analyze());
    }
}

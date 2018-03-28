package ru.liga.songtask.parser;

import ru.liga.songtask.analyzer.DoAnalyze;
import ru.liga.songtask.printer.Print;
import ru.liga.songtask.printer.PrintToFile;
import ru.liga.songtask.resources.Resources;

public class AnalyzeToFile extends Job {
    AnalyzeToFile(String[] args) {
        super(args);
    }

    @Override
    public void doJob() {
        Print printToFile = new PrintToFile(args[0] + ".txt");
        printToFile.print(new DoAnalyze(new Resources(args[0]).getFile()).analyze());
    }
}

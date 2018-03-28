package ru.liga.songtask.parser;

import lombok.extern.slf4j.Slf4j;
import ru.liga.songtask.analyzer.DoAnalyze;
import ru.liga.songtask.resources.Resources;

@Slf4j
public class AnalyzeToLog implements InterfaceJob {
    private final String fileName;

    AnalyzeToLog(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void doJob() {
        log.info(new DoAnalyze(new Resources(fileName).getFile()).analyze());
    }
}

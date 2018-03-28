package ru.liga.songtask.parser;

import lombok.extern.slf4j.Slf4j;
import ru.liga.songtask.analyzer.DoAnalyze;
import ru.liga.songtask.resources.Resources;

@Slf4j
public class AnalyzeToLog extends Job {
    AnalyzeToLog(String[] args) {
        super(args);
    }

    @Override
    public void doJob() {
        log.info(new DoAnalyze(new Resources(args[0]).getFile()).analyze());
    }
}

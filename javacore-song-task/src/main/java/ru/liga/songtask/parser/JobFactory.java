package ru.liga.songtask.parser;

public class JobFactory {
    public static Job getJob(String[] args){
        if (args.length == 0) {
            throw new NoParameters();
        }

        if (args[1].equals("analyze")) {
            if (args.length == 2) {
                return new AnalyzeToLog(args);
            }
            if (args.length == 3 & args[2].equals("-f")) {
                return new AnalyzeToFile(args);
            }
        }
        if (args[1].equals("change") && args[2].equals("-trans") && args[4].equals("-tempo")) {
            return new ChangeMidi(args);
        }
        throw new RuntimeException();
    }
}

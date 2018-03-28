package ru.liga.songtask.parser;

import ru.liga.songtask.parser.exception.NoParameters;
import ru.liga.songtask.parser.exception.NoCorrectParameters;

public class JobFactory {
    public static InterfaceJob getJob(String[] args) {
        if (args.length == 0) {
            throw new NoParameters();
        }

        if (args[1].equals("analyze")) {
            if (args.length == 2) {
                return new AnalyzeToLog(args[0]);
            }
            if (args.length == 3 & args[2].equals("-f")) {
                return new AnalyzeToFile(args[0]);
            }
        }

        if (args[1].equals("change") && args[2].equals("-trans") && args[4].equals("-tempo")) {
            String outputName = args[0].substring(0, args[0].length() - 4);
            outputName += args[2] + args[3] + args[4] + args[5] + ".mid";

            return new ChangeMidi(args[0], outputName, Integer.valueOf(args[3]), Integer.valueOf(args[5]));
        }
        throw new NoCorrectParameters();
    }
}

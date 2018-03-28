package ru.liga.songtask.parser;

import ru.liga.songtask.midichanger.Transpose;

public class ChangeMidi extends Job {
    ChangeMidi(String[] args) {
        super(args);
    }

    @Override
    public void doJob() {
        String outputName = args[0].substring(0, args[0].length() - 4);
        outputName += args[2].toString() + args[3].toString() + args[4].toString() + args[5].toString() + ".mid";

        Transpose transpose = new Transpose(args[0]);
        transpose.changeTone(Integer.valueOf(args[3]));
        transpose.changeTempo(Integer.valueOf(args[5]));
        transpose.toFile(outputName);
    }
}

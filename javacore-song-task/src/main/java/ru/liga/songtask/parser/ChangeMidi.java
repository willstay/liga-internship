package ru.liga.songtask.parser;

import ru.liga.songtask.midichanger.Transpose;

public class ChangeMidi implements InterfaceJob {
    private final String inputFileName;
    private final String outputFileName;
    private final int tone;
    private final int tempo;

    ChangeMidi(String inputFileName, String outputFileName, int tone, int tempo) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.tone = tone;
        this.tempo = tempo;
    }

    @Override
    public void doJob() {
        Transpose transpose = new Transpose(inputFileName);
        transpose.changeTone(tone);
        transpose.changeTempo(tempo);
        transpose.toFile(outputFileName);
    }
}

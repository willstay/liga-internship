package ru.liga.songtask.analyzer;

import ru.liga.songtask.domain.Note;
import ru.liga.songtask.resources.Resources;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.util.Map;

public class DoAnalyze {
    private final static StringBuilder stringBuilderOut = new StringBuilder();

    public static String analyze(String fileName) {
        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(new Resources(fileName).getFile());
        NotesAnalyzer notesAnalyzer = new NotesAnalyzer(simpleMidiFile.vocalNoteList());

        writeStringLn("Всего нот: " + notesAnalyzer.size());
        //System.out.println("Длительность (сек): " + simpleMidiFile.durationMs() / 1000);
        writeStringLn("<p>");
        writeStringLn("Анализ диапазона:");
        writeStringLn("верхняя: " + notesAnalyzer.getHighestNote().sign().fullName());
        writeStringLn("нижняя: " + notesAnalyzer.getLowestNote().sign().fullName());
        writeStringLn("диапазон: " + notesAnalyzer.getRangeNotes());
        writeStringLn("<p>");
        writeStringLn("Анализ длительности нот (мс):");

        for (Map.Entry<Long, Integer> duration : notesAnalyzer.analyzeDuration().entrySet()) {
            writeStringLn(Math.round(duration.getKey() * simpleMidiFile.tickInMs()) + ": " + duration.getValue());
        }

        writeStringLn("<p>");
        writeStringLn("Анализ нот по высоте:");

        for (Map.Entry<Note, Integer> note : notesAnalyzer.analyzeNotesHeight().entrySet()) {
            writeStringLn(note.getKey().sign().fullName() + ": " + note.getValue());
        }

        writeStringLn("<p>");
        writeStringLn("Анализ интервалов:");

        for (Map.Entry<Integer, Integer> note : notesAnalyzer.analyzeIntervals().entrySet()) {
            writeStringLn(note.getKey() + ": " + note.getValue());
        }

        return stringBuilderOut.toString();
    }

    private static void writeStringLn(String text) {
        stringBuilderOut.append(text).append(System.getProperty("line.separator"));
    }
}

package ru.liga;

import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.NotesAnalyzer;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.util.Map;

/**
 * Всего нот: 15
 * <p>
 * Анализ диапазона:
 * верхняя: E4
 * нижняя: F3
 * диапазон: 11
 * <p>
 * Анализ длительности нот (мс):
 * 4285: 10
 * 2144: 5
 * <p>
 * Анализ нот по высоте:
 * E4: 3
 * D4: 3
 * A3: 3
 * G3: 3
 * F3: 3
 * <p>
 * Анализ интервалов:
 * 2: 9
 * 5: 3
 * 11: 2
 */
public class App {
    public static void main(String[] args) {
        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(Content.ZOMBIE);
        NotesAnalyzer notesAnalyzer = new NotesAnalyzer(simpleMidiFile.vocalNoteList());
        System.out.println("Всего нот: " + notesAnalyzer.size());
        //System.out.println("Длительность (сек): " + simpleMidiFile.durationMs() / 1000);
        System.out.println("<p>");
        System.out.println("Анализ диапазона:");
        System.out.println("верхняя: " + notesAnalyzer.getHighestNote().sign().fullName());
        System.out.println("нижняя: " + notesAnalyzer.getLowestNote().sign().fullName());
        System.out.println("диапазон: " + notesAnalyzer.getRangeNotes());
        System.out.println("<p>");
        System.out.println("Анализ длительности нот (мс):");

        for(Map.Entry<Long,Integer> duration : notesAnalyzer.analyzeDuration().entrySet()){
            System.out.println(Math.round(duration.getKey() * simpleMidiFile.tickInMs()) + ": " + duration.getValue());
        }

        System.out.println("<p>");
        System.out.println("Анализ нот по высоте:");

        for(Map.Entry<Note,Integer> note : notesAnalyzer.analyzeNotesHeight().entrySet()){
            System.out.println(note.getKey().sign().fullName() + ": " + note.getValue());
        }

        System.out.println("<p>");
        System.out.println("Анализ интервалов:");

        for(Map.Entry<Integer,Integer> note : notesAnalyzer.analyzeIntervals().entrySet()){
            System.out.println(note.getKey() + ": " + note.getValue());
        }
    }
}

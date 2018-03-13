package ru.liga.songtask.domain;

import ru.liga.songtask.content.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DoAnalyze {
    public static String analyze(){
        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(Content.ZOMBIE);
        NotesAnalyzer notesAnalyzer = new NotesAnalyzer(simpleMidiFile.vocalNoteList());

        StringBuilder stringBuilderOut = new StringBuilder();

        stringBuilderOut.append("Всего нот: " + notesAnalyzer.size()).append(System.getProperty("line.separator"));
        //System.out.println("Длительность (сек): " + simpleMidiFile.durationMs() / 1000);
        stringBuilderOut.append("<p>").append(System.getProperty("line.separator"));
        stringBuilderOut.append("Анализ диапазона:").append(System.getProperty("line.separator"));
        stringBuilderOut.append("верхняя: " + notesAnalyzer.getHighestNote().sign().fullName()).append(System.getProperty("line.separator"));
        stringBuilderOut.append("нижняя: " + notesAnalyzer.getLowestNote().sign().fullName()).append(System.getProperty("line.separator"));
        stringBuilderOut.append("диапазон: " + notesAnalyzer.getRangeNotes()).append(System.getProperty("line.separator"));
        stringBuilderOut.append("<p>").append(System.getProperty("line.separator"));
        stringBuilderOut.append("Анализ длительности нот (мс):").append(System.getProperty("line.separator"));

        for(Map.Entry<Long,Integer> duration : notesAnalyzer.analyzeDuration().entrySet()){
            stringBuilderOut.append(Math.round(duration.getKey() * simpleMidiFile.tickInMs()) + ": " + duration.getValue()).append(System.getProperty("line.separator"));
        }

        stringBuilderOut.append("<p>").append(System.getProperty("line.separator"));
        stringBuilderOut.append("Анализ нот по высоте:").append(System.getProperty("line.separator"));

        for(Map.Entry<Note,Integer> note : notesAnalyzer.analyzeNotesHeight().entrySet()){
            stringBuilderOut.append(note.getKey().sign().fullName() + ": " + note.getValue()).append(System.getProperty("line.separator"));
        }

        stringBuilderOut.append("<p>").append(System.getProperty("line.separator"));
        stringBuilderOut.append("Анализ интервалов:").append(System.getProperty("line.separator"));

        for(Map.Entry<Integer,Integer> note : notesAnalyzer.analyzeIntervals().entrySet()){
            stringBuilderOut.append(note.getKey() + ": " + note.getValue());
        }

        return stringBuilderOut.toString();
    }
    public static void memory(){
        //set heap's memory to 10m
        List <String> list = new ArrayList<>();
        while (true){
            list.add(new String());
        }
    }
}

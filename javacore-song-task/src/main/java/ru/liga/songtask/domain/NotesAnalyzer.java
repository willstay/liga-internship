package ru.liga.songtask.domain;

import java.util.*;

public class NotesAnalyzer {
    private List<Note> notesList;
    private static final Comparator<Note> NOTE_COMPARATOR = (o1, o2) -> {
        if(o1.sign().getFrequencyHz().equals(o2.sign().getFrequencyHz())){
            return 0;
        }
        return o1.sign().lower(o2.sign()) ? -1 : 1;
    };

    private static final Comparator<Note> NOTE_COMPARATOR_REVERSE = (o1, o2) -> {
        if(o1.sign().getFrequencyHz().equals(o2.sign().getFrequencyHz())){
            return 0;
        }
        return o1.sign().higher(o2.sign()) ? -1 : 1;
    };

    public NotesAnalyzer(List<Note> notesList) {
        this.notesList = notesList;
    }

    public Note getLowestNote(){
        List<Note> sortedList = new ArrayList<>(notesList);
        sortedList.sort(NOTE_COMPARATOR);
        return sortedList.get(0);
    }

    public Note getHighestNote(){
        List<Note> sortedList = new ArrayList<>(notesList);
        sortedList.sort(NOTE_COMPARATOR_REVERSE);
        return sortedList.get(0);
    }

    public int getRangeNotes(){
        Map<Note,Integer> analyzeMap = analyzeNotesHeight();
        return ((TreeMap<Note, Integer>) analyzeMap).firstKey().sign().diffInSemitones(
                ((TreeMap<Note, Integer>) analyzeMap).lastKey().sign());
    }

    public Map<Long,Integer> analyzeDuration(){
        List<Note> sortedList = new ArrayList<>(notesList);
        Map<Long, Integer> analyzeMap = new TreeMap<>(Collections.reverseOrder());

        for(Note note : sortedList){
            if(!analyzeMap.containsKey(note.durationTicks())){
                analyzeMap.put(note.durationTicks(),1);
            } else {
                analyzeMap.put(note.durationTicks(),analyzeMap.get(note.durationTicks())+1);
            }
        }

        return analyzeMap;
    }
    public Map<Note,Integer> analyzeNotesHeight(){
        List<Note> sortedList = new ArrayList<>(notesList);
        Map<Note,Integer> analyzeMap = new TreeMap<>(NOTE_COMPARATOR_REVERSE);

        for(Note note : sortedList){
            if(!analyzeMap.containsKey(note)){
                analyzeMap.put(note,1);
            } else {
                analyzeMap.put(note,analyzeMap.get(note)+1);
            }
        }

        return analyzeMap;
    }

    public Map<Integer,Integer> analyzeIntervals(){
        List<Note> sortedList = new ArrayList<>(notesList);
        List<Integer> intervalList = new ArrayList<>();

        for(int i = 0; i < sortedList.size() - 1; i++){
            intervalList.add(sortedList.get(i).sign().diffInSemitones(sortedList.get(i + 1).sign()));
        }

        Map<Integer,Integer> analyzeMap = new TreeMap<>();

        for(Integer interval : intervalList){
            if(!analyzeMap.containsKey(interval)){
                analyzeMap.put(interval,1);
            } else {
                analyzeMap.put(interval,analyzeMap.get(interval)+1);
            }
        }

        return analyzeMap;
    }

    public int size(){
        return notesList.size();
    }
}

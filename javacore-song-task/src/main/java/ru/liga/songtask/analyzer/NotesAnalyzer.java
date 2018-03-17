package ru.liga.songtask.analyzer;

import ru.liga.songtask.domain.Note;

import java.util.*;

public class NotesAnalyzer {
    private List<Note> notesList;

    private static final Comparator<Note> NOTE_COMPARATOR_FREQUENCY = (o1, o2) -> {
        if(o1.sign().getFrequencyHz().equals(o2.sign().getFrequencyHz())){
            return 0;
        }
        return o1.sign().lower(o2.sign()) ? -1 : 1;
    };

    private static final Comparator<Note> NOTE_COMPARATOR_FREQUENCY_REVERSE = (o1, o2) -> {
        if(o1.sign().getFrequencyHz().equals(o2.sign().getFrequencyHz())){
            return 0;
        }
        return o1.sign().higher(o2.sign()) ? -1 : 1;
    };

    public NotesAnalyzer(List<Note> notesList) {
        this.notesList = notesList;
    }

    public Note getLowestNote(){
        List<Note> notesListCopy = new ArrayList<>(notesList);
        notesListCopy.sort(NOTE_COMPARATOR_FREQUENCY);
        return notesListCopy.get(0);
    }

    public Note getHighestNote(){
        List<Note> notesListCopy = new ArrayList<>(notesList);
        notesListCopy.sort(NOTE_COMPARATOR_FREQUENCY_REVERSE);
        return notesListCopy.get(0);
    }

    public int getRangeNotes(){
        Map<Note,Integer> notesHeightMap = analyzeNotesHeight();
        return ((TreeMap<Note, Integer>) notesHeightMap).firstKey().sign().diffInSemitones(
                ((TreeMap<Note, Integer>) notesHeightMap).lastKey().sign());
    }

    public Map<Long,Integer> analyzeDuration(){
        List<Note> notesListCopy = new ArrayList<>(notesList);
        Map<Long, Integer> durationMap = new TreeMap<>(Collections.reverseOrder());

        for(Note note : notesListCopy){
            if(!durationMap.containsKey(note.durationTicks())){
                durationMap.put(note.durationTicks(),1);
            } else {
                durationMap.put(note.durationTicks(),durationMap.get(note.durationTicks())+1);
            }
        }

        return durationMap;
    }

    public Map<Note,Integer> analyzeNotesHeight(){
        List<Note> notesListCopy = new ArrayList<>(notesList);
        Map<Note,Integer> notesHeightMap = new TreeMap<>(NOTE_COMPARATOR_FREQUENCY_REVERSE);

        for(Note note : notesListCopy){
            if(!notesHeightMap.containsKey(note)){
                notesHeightMap.put(note,1);
            } else {
                notesHeightMap.put(note,notesHeightMap.get(note)+1);
            }
        }

        return notesHeightMap;
    }

    public Map<Integer,Integer> analyzeIntervals(){
        List<Note> notesListCopy = new ArrayList<>(notesList);
        List<Integer> intervalList = new ArrayList<>();

        for(int i = 0; i < notesListCopy.size() - 1; i++){
            intervalList.add(notesListCopy.get(i).sign().diffInSemitones(
                    notesListCopy.get(i + 1).sign()));
        }

        Map<Integer,Integer> intervalMap = new TreeMap<>();

        for(Integer interval : intervalList){
            if(!intervalMap.containsKey(interval)){
                intervalMap.put(interval,1);
            } else {
                intervalMap.put(interval,intervalMap.get(interval)+1);
            }
        }

        return intervalMap;
    }

    public int size(){
        return notesList.size();
    }
}

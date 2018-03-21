package ru.liga.songtask.analyzer;

import ru.liga.songtask.domain.Note;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NotesAnalyzer {
    private final List<Note> notesList;

    private static final Comparator<Note> NOTE_COMPARATOR_FREQUENCY = (o1, o2) -> {
        if (o1.sign().getFrequencyHz().equals(o2.sign().getFrequencyHz())) {
            return 0;
        }
        return o1.sign().lower(o2.sign()) ? -1 : 1;
    };

    public NotesAnalyzer(List<Note> notesList) {
        this.notesList = notesList;
    }

    public Note getLowestNote() {
        return notesList.stream()
                .min(NOTE_COMPARATOR_FREQUENCY)
                .get();
    }

    public Note getHighestNote() {
        return notesList.stream()
                .max(NOTE_COMPARATOR_FREQUENCY)
                .get();
    }

    public int getRangeNotes() {
        Map<Note, Integer> notesHeightMap = analyzeNotesHeight();
        return ((TreeMap<Note, Integer>) notesHeightMap).firstKey().sign().diffInSemitones(
                ((TreeMap<Note, Integer>) notesHeightMap).lastKey().sign());
    }

    public Map<Long, Integer> analyzeDuration() {
         return notesList.stream()
                .map(note -> note.durationTicks())
                .collect(Collectors.toMap(
                        i -> i,
                        i -> 1,
                        (u, u2) -> u + 1,
                        TreeMap::new
                )).descendingMap();
    }

    public Map<Note, Integer> analyzeNotesHeight() {
        List<Note> notesListCopy = new ArrayList<>(notesList);
        Map<Note, Integer> notesHeightMap = new TreeMap<>(NOTE_COMPARATOR_FREQUENCY.reversed());

        notesListCopy.stream()
                .forEach(note -> {
                    if (!notesHeightMap.containsKey(note)) {
                        notesHeightMap.put(note, 1);
                    } else {
                        notesHeightMap.put(note, notesHeightMap.get(note) + 1);
                    }
                });
        return notesHeightMap;
    }

    public Map<Integer, Integer> analyzeIntervals() {
        List<Note> notesListCopy = new ArrayList<>(notesList);
        List<Integer> intervalList = new ArrayList<>();

//        notesListCopy.stream()
//                .map(note -> note.sign())

        for (int i = 0; i < notesListCopy.size() - 1; i++) {
            intervalList.add(notesListCopy.get(i).sign().diffInSemitones(
                    notesListCopy.get(i + 1).sign()));
        }

        Map<Integer, Integer> intervalMap = new TreeMap<>();

        for (Integer interval : intervalList) {
            if (!intervalMap.containsKey(interval)) {
                intervalMap.put(interval, 1);
            } else {
                intervalMap.put(interval, intervalMap.get(interval) + 1);
            }
        }

        return intervalMap;
    }

    public int size() {
        return notesList.size();
    }
}

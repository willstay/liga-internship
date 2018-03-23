package ru.liga.songtask.analyzer;

import ru.liga.songtask.domain.Note;

import java.util.*;
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
                .forEach(note -> notesHeightMap.merge(note, 1, (oldValue, newValue) -> oldValue + newValue));
        return notesHeightMap;
    }

    public Map<Integer, Integer> analyzeIntervals() {
        List<Note> notesListCopy = new ArrayList<>(notesList);
        List<Integer> intervalList = new ArrayList<>();
        for (int i = 0; i < notesListCopy.size() - 1; i++) {
            intervalList.add(notesListCopy.get(i).sign().diffInSemitones(
                    notesListCopy.get(i + 1).sign()));
        }
        Map<Integer, Integer> intervalMap = new TreeMap<>();
        intervalList.stream()
                .forEach(interval -> intervalMap.merge(interval, 1, (oldValue, newValue) -> oldValue + newValue));

        return intervalMap;
    }

    public int size() {
        return notesList.size();
    }
}

package ru.liga.songtask.analyzer;

import org.junit.Test;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.SimpleMidiFile;
import ru.liga.songtask.resources.Resources;

import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

public class AnalyzeTests {
    private SimpleMidiFile simpleMidiFile = new SimpleMidiFile(new Resources("zombie.mid").getFile());
    private NotesAnalyzer notesAnalyzer = new NotesAnalyzer(simpleMidiFile.vocalNoteList());
    @Test
    public void getLowestNoteTest(){
        assertThat(notesAnalyzer.getLowestNote().sign().fullName()).isEqualTo("E4");
    }
    @Test
    public void getHighestNoteTest(){
        assertThat(notesAnalyzer.getHighestNote().sign().fullName()).isEqualTo("A5");
    }
    @Test
    public void numberOfNotesTest(){
        assertThat(notesAnalyzer.size()).isEqualTo(289);
    }
    @Test
    public void getRangeTest(){
        assertThat(notesAnalyzer.getRangeNotes()).isEqualTo(17);
    }
    @Test
    public void analyzeDurationTest(){
        assertThat(notesAnalyzer.analyzeDuration().get(1920l)).isEqualTo(2);
    }
    @Test
    public void analyzeIntervalsTest(){
        assertThat(notesAnalyzer.analyzeIntervals().get(0)).isEqualTo(34);
    }
    @Test
    public void analyzeNotesHeightTest(){
        Note note = (Note)((TreeMap)notesAnalyzer.analyzeNotesHeight()).firstKey();
        assertThat(notesAnalyzer.analyzeNotesHeight().get(note)).isEqualTo(25);
    }
}

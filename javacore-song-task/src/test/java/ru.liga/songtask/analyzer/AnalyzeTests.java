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
    public void When_GetLowestNoteInZombieSong_Expect_E4() {
        assertThat(notesAnalyzer.getLowestNote().sign().fullName()).isEqualTo("E4");
    }

    @Test
    public void When_GetHighestNoteInZombieSong_Expect_A5() {
        assertThat(notesAnalyzer.getHighestNote().sign().fullName()).isEqualTo("A5");
    }

    @Test
    public void When_CountNumberOfNotesInZombieSong_Expect_289() {
        assertThat(notesAnalyzer.size()).isEqualTo(289);
    }

    @Test
    public void When_GetVocalRangeInZombieSong_Expect_17() {
        assertThat(notesAnalyzer.getRangeNotes()).isEqualTo(17);
    }

    @Test
    public void When_GetCountDuration19201InZombieSong_Expect_2() {
        assertThat(notesAnalyzer.analyzeDuration().get(1920l)).isEqualTo(2);
    }

    @Test
    public void When_GetCountZeroDurationInZombieSong_Expect_34() {
        assertThat(notesAnalyzer.analyzeIntervals().get(0)).isEqualTo(34);
    }

    @Test
    public void When_GetCountA5InZombieSong_Expect_25() {
        Note note = (Note) ((TreeMap) notesAnalyzer.analyzeNotesHeight()).firstKey();
        assertThat(notesAnalyzer.analyzeNotesHeight().get(note)).isEqualTo(25);
    }
}

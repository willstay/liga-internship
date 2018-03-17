package ru.liga.songtask.analyzer;

import org.junit.Test;
import ru.liga.songtask.domain.SimpleMidiFile;
import ru.liga.songtask.resources.Resources;

import static org.assertj.core.api.Assertions.assertThat;

public class AnalyzeTests {
    private SimpleMidiFile simpleMidiFile = new SimpleMidiFile(new Resources("zombie.mid").getFile());
    private NotesAnalyzer notesAnalyzer = new NotesAnalyzer(simpleMidiFile.vocalNoteList());
    @Test
    public void testGetLowestNote(){
        assertThat(notesAnalyzer.getLowestNote().sign().fullName()).isEqualTo("E4");
    }
    @Test
    public void testNumberOfNotes(){
        assertThat(notesAnalyzer.size()).isEqualTo(289);
    }
}

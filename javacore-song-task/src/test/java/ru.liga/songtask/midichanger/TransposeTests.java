package ru.liga.songtask.midichanger;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.meta.Tempo;
import org.assertj.core.data.Percentage;
import org.junit.Test;
import ru.liga.songtask.domain.SimpleMidiFile;
import ru.liga.songtask.resources.Resources;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TransposeTests {
    private String fileName = "zombie.mid";
    private String fileNameChanged = "zombieTest.mid";
    private Transpose transpose = new Transpose(fileName);
    @Test
    public void ChangeTempoTest(){
        int tempoChange = 20;
        transpose.changeTempo(tempoChange);
        transpose.toFile(fileNameChanged);

        MidiFile midiFile;

        try {
            midiFile = new MidiFile(new File(fileNameChanged));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        for(MidiTrack midiTrack : midiFile.getTracks()){
            for (MidiEvent midiEvent : midiTrack.getEvents()){
                if (midiEvent instanceof Tempo){
                    Tempo tempo = (Tempo)midiEvent;
                    assertThat(tempo.getBpm()).isCloseTo(82 + 82 * tempoChange / 100, Percentage.withPercentage(10));
                }
            }
        }
    }

    @Test
    public void ChangeToneTest(){
        int tone = 20;
        transpose.changeTone(tone);
        transpose.toFile(fileNameChanged);

        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(new Resources(fileName).getFile());

        SimpleMidiFile simpleMidiFileTest = new SimpleMidiFile(new File(fileNameChanged));

        assertThat(simpleMidiFileTest.noteList(0).get(0).sign().getMidi()).isEqualTo(
                simpleMidiFile.noteList(0).get(0).sign().getMidi() + 20);
    }
}

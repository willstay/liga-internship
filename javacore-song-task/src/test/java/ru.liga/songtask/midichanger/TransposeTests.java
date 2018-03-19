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
    private Transpose transpose = new Transpose(fileName);

    @Test
    public void When_ChangeTempoOn20InZombieSong_Expect_BpmBecome98() {
        int tempoChange = 20;
        transpose.changeTempo(tempoChange);
        MidiFile midiFile = transpose.getMidiFile();

        for (MidiTrack midiTrack : midiFile.getTracks()) {
            for (MidiEvent midiEvent : midiTrack.getEvents()) {
                if (midiEvent instanceof Tempo) {
                    Tempo tempo = (Tempo) midiEvent;
                    assertThat(tempo.getBpm()).isCloseTo(82 + 82 * tempoChange / 100, Percentage.withPercentage(10));
                }
            }
        }
    }

    @Test
    public void When_ChangeToneOn20InZombieSong_Expect_MidiBecome96InFirstNote() {
        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(transpose.getMidiFile());
        transpose.changeTone(20);
        assertThat(simpleMidiFile.noteList(0).get(0).sign().getMidi()).isEqualTo(96);
    }
}

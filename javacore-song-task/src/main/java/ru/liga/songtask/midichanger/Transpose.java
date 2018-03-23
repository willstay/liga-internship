package ru.liga.songtask.midichanger;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import ru.liga.songtask.resources.Resources;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

public class Transpose {
    private MidiFile midiFile;

    public Transpose(String fileName) {
        try {
            midiFile = new MidiFile(new Resources(fileName).getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeTempo(int changeTempo) {
        midiFile.getTracks().stream()
                .flatMap(midiTrack -> midiTrack.getEvents().stream())
                .filter(midiEvent -> midiEvent instanceof Tempo)
                .forEach(tempo -> ((Tempo) tempo)
                        .setBpm(((Tempo) tempo).getBpm() + ((Tempo) tempo).getBpm() * changeTempo / 100));
    }

    public void changeTone(int tone) {
        midiFile.getTracks().stream()
                .flatMap(midiTrack -> midiTrack.getEvents().stream())
                .filter(midiEvent -> midiEvent instanceof NoteOn)
                .forEach(noteOn -> ((NoteOn) noteOn).setNoteValue(((NoteOn) noteOn).getNoteValue() + tone));

        midiFile.getTracks().stream()
                .flatMap(midiTrack -> midiTrack.getEvents().stream())
                .filter(midiEvent -> midiEvent instanceof NoteOff)
                .forEach(noteOff -> ((NoteOff) noteOff).setNoteValue(((NoteOff) noteOff).getNoteValue() + tone));
    }

    public MidiFile getMidiFile() {
        return midiFile;
    }

    public void toFile(String fileName) {
        try {
            midiFile.writeToFile(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

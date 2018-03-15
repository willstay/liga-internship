package ru.liga.songtask.domain;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Transpose {
    MidiFile midiFile;
    public Transpose(String fileName){

        try {
            midiFile = new MidiFile(new Resources(fileName).getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeTempo(int changeTempo){
        for(MidiTrack midiTrack : midiFile.getTracks()){
            for(MidiEvent midiEvent : midiTrack.getEvents()){
                if(midiEvent instanceof Tempo){
                    Tempo tempo = (Tempo)midiEvent;
                    tempo.setBpm(tempo.getBpm() + tempo.getBpm() * changeTempo / 100);
                }
            }
        }
    }

    public void changeTone(int tone){
        for(MidiTrack midiTrack : midiFile.getTracks()){
            for(MidiEvent midiEvent : midiTrack.getEvents()){
                if(midiEvent instanceof NoteOn){
                    NoteOn noteOn = (NoteOn)midiEvent;
                    noteOn.setNoteValue(noteOn.getNoteValue() + tone);
                }
                if(midiEvent instanceof NoteOff){
                    NoteOff NoteOff = (NoteOff)midiEvent;
                    NoteOff.setNoteValue(NoteOff.getNoteValue() + tone);
                }
            }
        }
    }
    public void toFile(String fileName){
        try {
            midiFile.writeToFile(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

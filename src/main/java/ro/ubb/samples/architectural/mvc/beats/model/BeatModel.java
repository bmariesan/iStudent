package ro.ubb.samples.architectural.mvc.beats.model;

import ro.ubb.samples.architectural.mvc.beats.view.BPMObserver;
import ro.ubb.samples.architectural.mvc.beats.view.BeatObserver;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;

public class BeatModel implements BeatModelInterface, MetaEventListener {

    private Sequencer sequencer;
    private List<BeatObserver> beatObservers = new ArrayList<>();
    private List<BPMObserver> bpmObservers = new ArrayList<>();
    private int bpm = 90;
    private Sequence sequence;
    private Track track;

    @Override
    public void initialize() {
        setUpMidi();
        buildTrackAndStart();
    }

    @Override
    public void on() {
        sequencer.start();
        setBPM(90);
    }

    @Override
    public void off() {
        setBPM(0);
        sequencer.stop();
    }

    @Override
    public void setBPM(int bpm) {
        this.bpm = bpm;
        sequencer.setTempoInBPM(getBPM());
        notifyBPMObservers();
    }

    @Override
    public int getBPM() {
        return this.bpm;
    }

    void beatEvent() {
        notifyBeatObservers();
    }

    @Override
    public void registerObserver(BeatObserver o) {
        beatObservers.add(o);
    }

    public void notifyBeatObservers() {
        for (BeatObserver bo : this.beatObservers) {
            bo.updateBeat();
        }
    }

    @Override
    public void registerObserver(BPMObserver o) {
        this.bpmObservers.add(o);
    }

    public void notifyBPMObservers() {
        for (BPMObserver bpmo : this.bpmObservers) {
            bpmo.updateBPM();
        }
    }

    @Override
    public void removeObserver(BeatObserver o) {
        int i = this.beatObservers.indexOf(o);
        if (i >= 0) {
            this.beatObservers.remove(i);
        }
    }

    @Override
    public void removeObserver(BPMObserver o) {
        int i = this.bpmObservers.indexOf(o);
        if (i >= 0) {
            this.bpmObservers.remove(i);
        }
    }

    @Override
    public void meta(MetaMessage meta) {
if(meta.getType()==47) {
    beatEvent();
    sequencer.start();
    setBPM(getBPM());
}
    }

    public void setUpMidi() {
        try{
            this.sequencer = MidiSystem.getSequencer();
            this.sequencer.open();
            this.sequencer.addMetaEventListener(this);
            this.sequence = new Sequence(Sequence.PPQ,4);
            track = this.sequence.createTrack();
            sequencer.setTempoInBPM(getBPM());
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    public void buildTrackAndStart() {
        int[] trackList = {35,0,46,0};

        sequence.deleteTrack(null);
        track = sequence.createTrack();

        makeTracks(trackList);
        track.add(makeEvent(192,9,1,0,4));
    try{
        sequencer.setSequence(this.sequence);
    } catch (InvalidMidiDataException e) {
        e.printStackTrace();
    }
    }

    public void makeTracks(int[] list) {
        for(int i=0; i<list.length ; i++) {
            int key = list[i];
            if(key != 0){
                track.add(makeEvent(144,9,key, 100,i));
                track.add(makeEvent(128,9,key, 100,i+1));
            }
        }
    }
    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            return new MidiEvent(a, tick);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return null;
    }

}

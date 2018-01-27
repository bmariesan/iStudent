package ro.ubb.samples.architectural.mvc.beats.model;

import ro.ubb.samples.architectural.mvc.beats.view.BPMObserver;
import ro.ubb.samples.architectural.mvc.beats.view.BeatObserver;

public interface BeatModelInterface {
    void initialize();
    void on();
    void off();
    void setBPM(int bpm);
    int getBPM();

    void registerObserver(BeatObserver o);
    void removeObserver(BeatObserver o);
    void registerObserver(BPMObserver o);
    void removeObserver(BPMObserver o);

}

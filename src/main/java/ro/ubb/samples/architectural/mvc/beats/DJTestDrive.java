package ro.ubb.samples.architectural.mvc.beats;

import ro.ubb.samples.architectural.mvc.beats.controller.BeatController;
import ro.ubb.samples.architectural.mvc.beats.controller.ControllerInterface;
import ro.ubb.samples.architectural.mvc.beats.model.BeatModel;

public class DJTestDrive {
    public static void main(String[] args) {
        BeatModel model = new BeatModel();
        ControllerInterface controller = new BeatController(model);
    }
}

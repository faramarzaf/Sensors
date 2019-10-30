package com.faramarz.tictacdev.sensors.step_count;

// Will listen to step alerts
public interface StepListener {
    void step(long timeNs);
}

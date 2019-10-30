package com.faramarz.tictacdev.sensors.step_count;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.faramarz.tictacdev.sensors.R;

public class StepCounterActivity extends AppCompatActivity implements SensorEventListener, StepListener, View.OnClickListener {
    TextView textView;
    Button btn_start, btn_stop;
    StepDetector simpleStepDetector;
    SensorManager sensorManager;
    Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        bind();
        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

    }

    private void bind() {
        textView = findViewById(R.id.tv_steps);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }


    @Override
    public void step(long timeNs) {
        numSteps++;
        textView.setText(TEXT_NUM_STEPS + numSteps);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.btn_start:
                startStepCount();
                break;

            case R.id.btn_stop:
                stopStepCount();
                break;

            default:
                break;
        }
    }

    private void startStepCount() {
        numSteps = 0;
        sensorManager.registerListener(StepCounterActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

    }

    private void stopStepCount() {
        sensorManager.unregisterListener(StepCounterActivity.this);

    }

}

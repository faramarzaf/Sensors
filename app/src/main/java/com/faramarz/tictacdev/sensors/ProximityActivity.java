package com.faramarz.tictacdev.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class ProximityActivity extends AppCompatActivity implements SensorEventListener {

    TextView txtProximity;
    SensorManager sensorManager;
    Sensor proximitySensor;
    int currentSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        txtProximity = findViewById(R.id.txtProximity);
        initSensors();
        checkSensorIsAvailable();


    }

    private void initSensors() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

    }

    private void checkSensorIsAvailable() {
        if (proximitySensor == null) {
            finish();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (currentSensor == Sensor.TYPE_PROXIMITY) {
            float distance = event.values[0];
            txtProximity.setText("Proximity " + distance);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }



}
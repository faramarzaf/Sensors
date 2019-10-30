package com.faramarz.tictacdev.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class GyroscopeActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor gyroscopeSensor;
    int currentSensor;
    TextView txtGyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        txtGyroscope = findViewById(R.id.txtGyroscope);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (gyroscopeSensor == null) {
            Log.e("TAG", "gyroscope sensor not available.");
            finish();
        }
        if (checkSensorAvailability(Sensor.TYPE_GYROSCOPE)) {
            currentSensor = Sensor.TYPE_GYROSCOPE;
        } else {
            txtGyroscope.setText("Gyroscope Sensor not available");
        }
    }

    public boolean checkSensorAvailability(int sensorType) {
        boolean isSensor = false;
        if (sensorManager.getDefaultSensor(sensorType) != null) {
            isSensor = true;
        }
        return isSensor;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.values[2] > 0.5f) {
            txtGyroscope.setText("Anti Clock");
        } else if (event.values[2] < -0.5f) {
            txtGyroscope.setText("Clock");
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}

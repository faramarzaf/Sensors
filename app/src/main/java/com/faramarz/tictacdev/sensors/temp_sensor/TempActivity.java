package com.faramarz.tictacdev.sensors.temp_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.faramarz.tictacdev.sensors.R;

public class TempActivity extends AppCompatActivity {

    TextView tempAvailable, tempReading,ambientAvailable,ambientReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        bind();
        initSensor();
    }

    private void bind() {
        tempAvailable = findViewById(R.id.TEMPERATURE_available);
        tempReading = findViewById(R.id.TEMPERATURE_reading);
        ambientAvailable = findViewById(R.id.AMBIENT_TEMPERATURE_available);
        ambientReading = findViewById(R.id.AMBIENT_TEMPERATURE_reading);

    }

    private void initSensor() {
        SensorManager mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor TemperatureSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        if (TemperatureSensor != null) {
            tempAvailable.setText("Sensor.TYPE_TEMPERATURE Available");
            mySensorManager.registerListener(TemperatureSensorListener, TemperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            tempAvailable.setText("Sensor.TYPE_TEMPERATURE NOT Available");
        }

        Sensor AmbientTemperatureSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (AmbientTemperatureSensor != null) {
            ambientAvailable.setText("Sensor.TYPE_AMBIENT_TEMPERATURE Available");
            mySensorManager.registerListener(AmbientTemperatureSensorListener, AmbientTemperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            ambientAvailable.setText("Sensor.TYPE_AMBIENT_TEMPERATURE NOT Available");
        }
    }

    private final SensorEventListener TemperatureSensorListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_TEMPERATURE) {
                tempReading.setText("TEMPERATURE: " + event.values[0]);
            }
        }
    };

    private final SensorEventListener AmbientTemperatureSensorListener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                ambientReading.setText("AMBIENT TEMPERATURE: " + event.values[0]);
            }
        }

    };

}
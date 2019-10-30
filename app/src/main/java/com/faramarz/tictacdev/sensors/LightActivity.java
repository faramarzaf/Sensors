package com.faramarz.tictacdev.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LightActivity extends AppCompatActivity implements SensorEventListener {
    Sensor lightSensor;
    SensorManager sensorManager;
    int currentSensor;
    TextView txtLight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        txtLight = findViewById(R.id.txtLight);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (lightSensor == null) {
            finish();
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
        if (event.sensor.getType() == currentSensor) {
            if (currentSensor == Sensor.TYPE_LIGHT) {
                float valueZ = event.values[0];
                txtLight.setText("Brightness " + valueZ);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void lightSensorOnClick(View view) {
        if (checkSensorAvailability(Sensor.TYPE_LIGHT)) {
            currentSensor = Sensor.TYPE_LIGHT;
        } else {
            txtLight.setText("Light Sensor not available");
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


}

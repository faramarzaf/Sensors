package com.faramarz.tictacdev.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LightActivity extends AppCompatActivity {
    Sensor lightSensor;
    SensorManager sensorManager;
    int currentSensor;
    TextView txtLight;
    SensorEventListener listener;


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

        listener = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                //  Toast.makeText(LightActivity.this, "accuracy changed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                txtLight.setText(String.valueOf(event.values[0]));
                int grayShade = (int) event.values[0];
                if (grayShade > 255) grayShade = 255;

                txtLight.setTextColor(Color.rgb(255 - grayShade, 255 - grayShade, 255 - grayShade));
                txtLight.setBackgroundColor(Color.rgb(grayShade, grayShade, grayShade));
            }
        };

        sensorManager.registerListener(listener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }


    public boolean checkSensorAvailability(int sensorType) {
        boolean isSensor = false;
        if (sensorManager.getDefaultSensor(sensorType) != null) {
            isSensor = true;
        }
        return isSensor;
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
        sensorManager.unregisterListener(listener, lightSensor);
    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(listener, lightSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }


}

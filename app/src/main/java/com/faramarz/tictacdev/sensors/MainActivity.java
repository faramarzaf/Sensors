package com.faramarz.tictacdev.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.faramarz.tictacdev.sensors.gps.GPSActivity;
import com.faramarz.tictacdev.sensors.step_count.StepCounterActivity;
import com.faramarz.tictacdev.sensors.temp_sensor.TempActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    TextView textView;
    Button accelerometerPage, ballSensor, rotatePage, gpsPage, stepCounterPage, btnTempSensor, btnProximity, btnLightSensor, btnGyro;
    private SensorManager sensorManager;
    Sensor accelerometerSensor, gyroscopeSensor;
    private int currentSensor;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD =600;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        clickEvents();
        initSensors();
        checkSensorIsAvailable();
    }

    private void initSensors() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    private void checkSensorIsAvailable() {

    }

    private void bind() {
        textView = findViewById(R.id.tvResult);
        accelerometerPage = findViewById(R.id.accelerometerPage);
        ballSensor = findViewById(R.id.ballSensor);
        rotatePage = findViewById(R.id.rotatePage);
        gpsPage = findViewById(R.id.gpsPage);
        btnTempSensor = findViewById(R.id.btnTempSensor);
        stepCounterPage = findViewById(R.id.stepCounterPage);
        btnProximity = findViewById(R.id.btnProximity);
        btnLightSensor = findViewById(R.id.btnLightSensor);
        btnGyro = findViewById(R.id.btnGyro);
    }

    private void clickEvents() {
        stepCounterPage.setOnClickListener(this);
        gpsPage.setOnClickListener(this);
        ballSensor.setOnClickListener(this);
        accelerometerPage.setOnClickListener(this);
        rotatePage.setOnClickListener(this);
        btnTempSensor.setOnClickListener(this);
        btnProximity.setOnClickListener(this);
        btnLightSensor.setOnClickListener(this);
        btnGyro.setOnClickListener(this);
    }


    public boolean checkSensorAvailability(int sensorType) {
        boolean isSensor = false;
        if (sensorManager.getDefaultSensor(sensorType) != null) {
            isSensor = true;
        }
        return isSensor;
    }


    public void onSensorChanged(SensorEvent event) {


        if (event.sensor.getType() == currentSensor) {
            if (currentSensor == Sensor.TYPE_ACCELEROMETER) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                long curTime = System.currentTimeMillis();
                if ((curTime - lastUpdate) > 100) {

                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;
                    float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
                    if (speed > SHAKE_THRESHOLD) {
                        Toast.makeText(getApplicationContext(), "Your phone just shook", Toast.LENGTH_LONG).show();
                    }

                    last_x = x;
                    last_y = y;
                    last_z = z;
                }

            }

        }

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.accelerometerPage:
                startActivity(new Intent(this, AccelerometerActivity.class));
                break;

            case R.id.ballSensor:
                startActivity(new Intent(this, BallActivity.class));
                break;

            case R.id.rotatePage:
                startActivity(new Intent(this, RotateAndProximityActivity.class));
                break;

            case R.id.gpsPage:
                startActivity(new Intent(this, GPSActivity.class));
                break;

            case R.id.stepCounterPage:
                startActivity(new Intent(this, StepCounterActivity.class));
                break;

            case R.id.btnTempSensor:
                startActivity(new Intent(this, TempActivity.class));
                break;

            case R.id.btnProximity:
                startActivity(new Intent(this, ProximityActivity.class));
                break;

            case R.id.btnLightSensor:
                startActivity(new Intent(this, LightActivity.class));
                break;

            case R.id.btnGyro:
                startActivity(new Intent(this, GyroscopeActivity.class));
                break;

        }


    }
}

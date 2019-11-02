package com.faramarz.tictacdev.sensors;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.faramarz.tictacdev.sensors.gps.GPSActivity;
import com.faramarz.tictacdev.sensors.step_count.StepCounterActivity;
import com.faramarz.tictacdev.sensors.temp_sensor.TempActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button accelerometerPage, ballSensor, rotatePage, gpsPage, stepCounterPage, btnTempSensor, btnProximity, btnLightSensor, btnGyro, btnShock,btnStepCounterSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        clickEvents();

    }


    private void bind() {
        accelerometerPage = findViewById(R.id.accelerometerPage);
        ballSensor = findViewById(R.id.ballSensor);
        rotatePage = findViewById(R.id.rotatePage);
        gpsPage = findViewById(R.id.gpsPage);
        btnTempSensor = findViewById(R.id.btnTempSensor);
        stepCounterPage = findViewById(R.id.stepCounterPage);
        btnProximity = findViewById(R.id.btnProximity);
        btnLightSensor = findViewById(R.id.btnLightSensor);
        btnGyro = findViewById(R.id.btnGyro);
        btnShock = findViewById(R.id.btnShock);
        btnStepCounterSensor = findViewById(R.id.btnStepCounterSensor);
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
        btnShock.setOnClickListener(this);
        btnStepCounterSensor.setOnClickListener(this);
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

            case R.id.btnShock:
                startActivity(new Intent(this, ShakeActivity.class));
                break;

            case R.id.btnStepCounterSensor:
                startActivity(new Intent(this, StepCounterSensor.class));
                break;
        }
    }

}

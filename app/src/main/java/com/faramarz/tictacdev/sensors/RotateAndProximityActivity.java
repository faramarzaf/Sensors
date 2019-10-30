package com.faramarz.tictacdev.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class RotateAndProximityActivity extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor proximitySensor, gyroscopeSensor, rotationVectorSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_and_proximity);
        bind();

    }

    private void bind() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        if (proximitySensor == null) {
            Log.e("TAG", "Proximity sensor not available.");
            finish(); // Close app
        }
    }


    SensorEventListener proximitySensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.values[0] < proximitySensor.getMaximumRange()) {
                // Detected something nearby
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            } else {
                // Nothing is nearby
                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };


    SensorEventListener gyroscopeSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.values[2] > 0.5f) {
                // anticlockwise
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            } else if (sensorEvent.values[2] < -0.5f) {
                // clockwise
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };


    SensorEventListener rvListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] rotationMatrix = new float[16];
            SensorManager.getRotationMatrixFromVector(rotationMatrix, sensorEvent.values);

            float[] remappedRotationMatrix = new float[16];
            SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_X, SensorManager.AXIS_Z, remappedRotationMatrix);

            // Convert to orientations
            float[] orientations = new float[3];
            SensorManager.getOrientation(remappedRotationMatrix, orientations);

            for (int i = 0; i < 3; i++) {
                orientations[i] = (float) (Math.toDegrees(orientations[i]));
            }
            if (orientations[2] > 45) {
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            } else if (orientations[2] < -45) {
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            } else if (Math.abs(orientations[2]) < 10) {
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    @Override
    protected void onResume() {
        sensorManager.registerListener(proximitySensorListener, proximitySensor, 2 * 1000 * 1000);
        sensorManager.registerListener(gyroscopeSensorListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(rvListener, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(proximitySensorListener);
        super.onPause();
    }

}

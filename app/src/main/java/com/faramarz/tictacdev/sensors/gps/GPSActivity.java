package com.faramarz.tictacdev.sensors.gps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.faramarz.tictacdev.sensors.MainActivity;
import com.faramarz.tictacdev.sensors.R;

import java.security.Permissions;

public class GPSActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    // GPSTracker class
    GPSTracker gps;
    Button getGPS;
    TextView txtLat, txtLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        getGPS = findViewById(R.id.getGPS);
        txtLat = findViewById(R.id.txtLat);
        txtLon = findViewById(R.id.txtLon);
        getGPS.setOnClickListener(this);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{mPermission}, REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        // create class object
        gps = new GPSTracker(GPSActivity.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            txtLat.setText("latitude: " + latitude);
            txtLon.setText("longitude: " + longitude);
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //showLocation();
    }

    public void showLocation(){
        // create class object
        gps = new GPSTracker(GPSActivity.this);
        // check if GPS enabled
        if (gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            txtLat.setText("latitude: " + latitude);
            txtLon.setText("longitude: " + longitude);
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }

}

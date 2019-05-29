package com.example.lab4;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView edLong;
    private TextView edLat;
    LocationManager locationManager;
    private TextView internet;

    ConnectivityManager connectivityManager;
    NetworkInfo myWifi,my3G;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edLong = (TextView) findViewById(R.id.edLong);
        edLat = (TextView) findViewById(R.id.edLat);
        internet = (TextView) findViewById(R.id.internet);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //cap nhat vi tri
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);


        //bai2
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        myWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        my3G = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (myWifi.isAvailable() == true) {
            internet.setText("Đang sử dụng wifi");
        }
        else if (my3G.isAvailable() == true) {
            internet.setText("Đang sử dụng 3G");
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double longt = location.getLongitude();
        edLat.setText("Toạ độ Lat: " + String.valueOf(lat));
        edLong.setText("Tọa độ Long: " + String.valueOf(lat));

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

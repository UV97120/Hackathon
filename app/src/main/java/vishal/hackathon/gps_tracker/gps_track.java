package vishal.hackathon.gps_tracker;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by Vishal on 3/20/2017.
 */

public class gps_track extends Service implements LocationListener {

    private Context context = null;
    boolean isGpsEnabled = false;
    boolean canGetLocation = false;
    boolean isNetworkEnabled = false;
    double latitude, longitude;
    Location location;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 10;
    protected LocationManager locationManager;

    public gps_track(Context context) {
        this.context = context;
        getlocation();
    }

    public Location getlocation() {
        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!isGpsEnabled && !isNetworkEnabled) {

            } else {
                this.canGetLocation = true;
                if (!isNetworkEnabled) {

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_DISTANCE_CHANGE_FOR_UPDATES, MIN_TIME_BW_UPDATES, this);

                    }
                }
                if (locationManager != null) {
                    location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        Log.d("get_location ","/"+latitude+"/"+longitude+"/");

                    }
                }
                if (isGpsEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_DISTANCE_CHANGE_FOR_UPDATES, MIN_TIME_BW_UPDATES, this);

                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                                Log.d("get_location ","/"+latitude+"/"+longitude+"/");

                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return location;
    }

    public double getLongitude() {
        if (locationManager != null) {
            longitude = location.getLongitude();

        }
        return longitude;
    }

    public double getLatitude() {
        if (locationManager != null) {
            latitude = location.getLatitude();

        }
        return latitude;
    }

    public double StopUsingGPS() {
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                locationManager.removeUpdates(gps_track.this);
            }


        }
        return latitude;
    }
    @Nullable

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

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

package Utility;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.example.jacob.weatherornot.HomepageActivity;

public class GPS_Utility implements LocationListener {

    Context CURRENT_GPS_LOCATION;

    public GPS_Utility (Context GPS_LOCATION){
        CURRENT_GPS_LOCATION = GPS_LOCATION;
    }

    public Location getGPS_LOCATION(){

//      Need This to use GPS
        if (ContextCompat.checkSelfPermission(CURRENT_GPS_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(CURRENT_GPS_LOCATION, "No GPS Permission", Toast.LENGTH_SHORT).show();
            return null;
        }

        LocationManager LOCATION_OF_USER = (LocationManager)CURRENT_GPS_LOCATION.getSystemService(Context.LOCATION_SERVICE);
        boolean GPS_ENABLED = LOCATION_OF_USER.isProviderEnabled((LocationManager.GPS_PROVIDER));

        if(GPS_ENABLED){
//            AlertDialog.Builder messageBuilder = new AlertDialog.Builder(GPS_Utility.this)
            LOCATION_OF_USER.requestLocationUpdates(LocationManager.GPS_PROVIDER, 7500, 13, this);
            Location LAST_UPDATED_LOCATION = LOCATION_OF_USER.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return LAST_UPDATED_LOCATION;
        }
        else{
            Toast.makeText(CURRENT_GPS_LOCATION, "No GPS Signal", Toast.LENGTH_SHORT).show();
        }
        return null;

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

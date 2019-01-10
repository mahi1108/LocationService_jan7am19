package cubex.mahesh.locationservice_jan7am19

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var status = ContextCompat.checkSelfPermission(
                    this,
            Manifest.permission.ACCESS_COARSE_LOCATION)

        if(status == PackageManager.PERMISSION_GRANTED)
        {
            accessLocation()
        }else{
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION),
                                    123)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

 if(grantResults[0]== PackageManager.PERMISSION_GRANTED
           && grantResults[1]==PackageManager.PERMISSION_GRANTED)
 {
     accessLocation()
 }
    }


    fun accessLocation( )
    {
        val lManager : LocationManager = getSystemService(
            Context.LOCATION_SERVICE) as LocationManager
        /* String provider, long minTime,
        float minDistance, LocationListener listener */
        lManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            1000L,1f,
            object : LocationListener {
                override fun onLocationChanged(loc: Location?) {
                    if(loc!=null)
                    {
                        textView.setText("Latitude  : ${loc.latitude}")
                        textView2.setText("Longitude  : ${loc.longitude}")

                        lManager.removeUpdates(this)
                    }
                }

                override fun onProviderEnabled(p0: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onProviderDisabled(p0: String?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
    }



} // MainActivity

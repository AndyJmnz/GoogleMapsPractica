package com.example.mapsaplicacion

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.mapsaplicacion.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.PolylineOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding


    private var minimumDistance = 30
    private val PERMISSION_LOCATION = 999
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
// Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
        mFusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.create()
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 1000
        locationRequest.smallestDisplacement = minimumDistance.toFloat()
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                Log.e(
                    "APP 06",
                    locationResult.lastLocation?.latitude.toString() + "," +
                            locationResult.lastLocation?.longitude
                )
            }
        }
    }

    fun onMapCLick(latLng: LatLng?) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng!!, 13f))
        mMap.addMarker(
            MarkerOptions()
                .title("Marca personal")
                .snippet("Mi sitio marcado")
                .draggable(true)
                .icon(
                    BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_background)
                )
                .position(latLng)
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_LOCATION) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates()
            }
        }

    }

    private fun startLocationUpdates() {
        try {
            mFusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
            )
        } catch (e: SecurityException) {
        }
    }//startLocationUpdates
    private fun stopLocationUpdates() {
        mFusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }//stopLocationUpdates
    override fun onStart() {
        super.onStart()
        startLocationUpdates()
    }//onStart
    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }//onPause
    fun map(view: View) {
        when (view.id) {
            R.id.activity_maps_map -> mMap.mapType =
                GoogleMap.MAP_TYPE_NORMAL
            R.id.activity_maps_terrain -> mMap.mapType =
                GoogleMap.MAP_TYPE_SATELLITE
            R.id.activity_maps_hybrid -> mMap.mapType =
                GoogleMap.MAP_TYPE_HYBRID
            R.id.activity_maps_polylines -> showPolylines()
        }
    }//map
    private fun showPolylines() {
        if (mMap != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(20.68697, -
            103.35339), 12f))
            mMap.addPolyline(
                PolylineOptions().geodesic(true)
                    .add(LatLng(20.73882, -103.40063))
                    .add(LatLng(20.69676, -103.37541))
                    .add(LatLng(20.67806, -103.34673))
                    .add(LatLng(20.64047, -103.31154))
            )
        }
    }//showPolylines

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun onMapClick(p0: LatLng) {
        TODO("Not yet implemented")
    }
}
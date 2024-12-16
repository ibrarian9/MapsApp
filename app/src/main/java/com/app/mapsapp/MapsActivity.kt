package com.app.mapsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.app.mapsapp.databinding.ActivityMapsBinding
import com.app.mapsapp.model.SPBU
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var nMap: GoogleMap
    private lateinit var bind: ActivityMapsBinding
    private val boundsBuilder = LatLngBounds.Builder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bind = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this@MapsActivity)
    }

    override fun onMapReady(gMap: GoogleMap) {
        nMap = gMap

        nMap.uiSettings.isZoomControlsEnabled = true
        nMap.uiSettings.isIndoorLevelPickerEnabled = true
        nMap.uiSettings.isCompassEnabled = true
        nMap.uiSettings.isMapToolbarEnabled = true

        val dataSPBUList = listOf(
            SPBU(
                namaTempat = "SPBU Rimbo Panjang",
                lat = 0.45696392740985925,
                long = 101.35481293726377
            ),
            SPBU(
                namaTempat = "SPBU Garuda Sakti",
                lat = 0.48102789995699025,
                long = 101.36579390046074
            ),
            SPBU(
                namaTempat = "SPBU Prima Putra panam 2",
                lat = 0.4654696683909263,
                long = 101.37348342577953
            ),
            SPBU(
                namaTempat = "SPBU SM AMIN",
                lat = 0.4695499016502119,
                long = 101.39370560739872
            ),
            SPBU(
                namaTempat = "SPBU Arifin Ahmad",
                lat = 0.4790932731005638,
                long = 101.43072119509851
            )
        )

        // Add markers for each SPBU
        dataSPBUList.forEach {
            val latLng = LatLng(it.lat, it.long)
            nMap.addMarker(MarkerOptions().position(latLng).title(it.namaTempat))
            boundsBuilder.include(latLng) // Include this LatLng in the bounds
        }

        // Move the camera to include all markers
        val bounds = boundsBuilder.build()
        nMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
    }
}
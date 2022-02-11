package com.example.utmobile.ui.weather

import android.Manifest
import android.app.Instrumentation
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.utmobile.databinding.FragmentWeatherBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import java.lang.Exception

class WeatherFragment : Fragment(), LocationListener {

private var _binding: FragmentWeatherBinding? = null
    private lateinit var locationManager: LocationManager
    private lateinit var locationProvider: FusedLocationProviderClient
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val slideshowViewModel =
            ViewModelProvider(this).get(WeatherViewModel::class.java)

    _binding = FragmentWeatherBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textWeather
    slideshowViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }

      val intent = Intent(context, GPSTrackerActivity::class.java)
      startActivityForResult(intent, 1)

      return root
  }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onLocationChanged(p0: Location) {
        TODO("Not implemented yet")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1) {
            var extras = data!!.extras
            var longitude = extras!!.getDouble("Longitude")
            var latitude = extras!!.getDouble("Latitude")
        }
    }
}
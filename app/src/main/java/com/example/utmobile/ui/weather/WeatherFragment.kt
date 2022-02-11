package com.example.utmobile.ui.weather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.utmobile.databinding.FragmentWeatherBinding
import org.json.JSONObject

class WeatherFragment : Fragment() {

private var _binding: FragmentWeatherBinding? = null
  private val binding get() = _binding!!


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentWeatherBinding.inflate(inflater, container, false)
    val root: View = binding.root


    getWeather()

      return root
  }

  @SuppressLint("SetTextI18n")
  private fun getWeather() {

    val weatherJSON = GPSTrackerActivity().execute("").get()


    val main = weatherJSON.get("main") as JSONObject

    binding.tvMinTemp.text = "Low: ${main.get("temp_min")}F"
    binding.tvMaxTemp.text = "High: ${main.get("temp_max")}F"
    binding.tvCurrentTemp.text = "Current: ${main.get("temp")}F"

    binding.tvLocation.text = weatherJSON.get("name").toString()

  }

  override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
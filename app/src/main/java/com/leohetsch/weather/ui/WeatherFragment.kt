package com.leohetsch.weather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.leohetsch.weather.R
import com.leohetsch.weather.model.Weather
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment(R.layout.fragment_weather) {
    val viewModel: WeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.weather.observe(viewLifecycleOwner) { weather ->
            updateWithWeather(weather)
        }
    }

    fun updateWithWeather(weather: Weather) {
        val cityTextView = requireActivity().findViewById<TextView>(R.id.cityNameTextView)
        cityTextView.setText(weather.city)

        val tempTextView = requireActivity().findViewById<TextView>(R.id.tempTextField)
        tempTextView.setText(String.format("%d°", weather.getTemperatureCelcius().toInt()))
    }
}
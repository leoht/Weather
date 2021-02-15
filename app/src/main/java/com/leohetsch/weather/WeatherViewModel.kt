package com.leohetsch.weather

import androidx.lifecycle.*
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
        private val savedStateHandle: SavedStateHandle,
        private val weatherRepository: WeatherRepository
): ViewModel() {
    val city: String = savedStateHandle["city"] ?: "London"

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = _weather

    init {
        viewModelScope.launch {
            _weather.value = weatherRepository.getWeather(city)
        }
    }
}
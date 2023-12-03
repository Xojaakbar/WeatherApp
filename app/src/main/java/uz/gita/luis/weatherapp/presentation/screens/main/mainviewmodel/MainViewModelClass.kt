package uz.gita.luis.weatherapp.presentation.screens.main.mainviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.luis.weatherapp.data.common.ForecastRespons
import uz.gita.luis.weatherapp.domain.repositories.WeatherRepository
import uz.gita.luis.weatherapp.domain.repositories.weatherrepositoryimpl.WeatherRepositoryClass

class MainViewModelClass : ViewModel(), MainViewModel{
    private val weatherRepository: WeatherRepository = WeatherRepositoryClass.getInstance()
    override val loadingLiveData = MutableLiveData<Boolean>()
    override val successLiveData = MutableLiveData<ForecastRespons>()
    override val errorLiveData = MutableLiveData<String>()

    override fun loadWeather(nameOfCity: String, day: Int) {
        loadingLiveData.value = true

        weatherRepository.loadCurrentWeatherByCityByDay(nameOfCity,day)
            .onEach { loadingLiveData.value = false }
            .onEach { it.onSuccess { successLiveData.value = it } }
            .onEach { it.onFailure { errorLiveData.value = it.message } }
            .launchIn(viewModelScope)
    }
}
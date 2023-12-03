package uz.gita.luis.weatherapp.domain.repositories

import kotlinx.coroutines.flow.Flow
import uz.gita.luis.weatherapp.data.common.ForecastRespons

interface WeatherRepository {
    fun loadCurrentWeatherByCityByDay(nameOfCity:String,days:Int):Flow<Result<ForecastRespons>>

}
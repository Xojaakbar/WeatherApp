package uz.gita.luis.weatherapp.domain.repositories.weatherrepositoryimpl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.luis.weatherapp.data.common.ForecastRespons
import uz.gita.luis.weatherapp.data.source.remote.apies.CurrentApi
import uz.gita.luis.weatherapp.domain.repositories.WeatherRepository

class WeatherRepositoryClass private constructor(
    val currentApi: CurrentApi): WeatherRepository{

        private val key = "a65176bafd7d4c71948131641230105"

        companion object{
            private var weatherRepository:WeatherRepository? = null

            fun init(currentApi: CurrentApi){
                if (weatherRepository == null){
                    weatherRepository = WeatherRepositoryClass(currentApi)
                }
            }
            fun getInstance(): WeatherRepository = weatherRepository!!
        }

//    override fun getForecastByCity(city: String, days: Int): Flow<Result<ForecastRespons>> = flow<Result<ForecastRespons>> {
//        val response = currentApi.getWeatherByCityByDays(key, city, days)
//        if(response.isSuccessful) {
//            Log.d("AAA", "Response success")
//            emit(Result.success(response.body()!!))
//        } else {
//            emit(Result.failure(Exception()))
//            Log.d("AAA", "Response failure")
//        }
//    }.flowOn(Dispatchers.IO)

    override fun loadCurrentWeatherByCityByDay(
        nameOfCity: String,
        days: Int
    ): Flow<Result<ForecastRespons>> = flow {
        Log.d("TTT", "Result: Data -> ---------------------------------")
        val response = currentApi.getWeatherByCityByDays(key,nameOfCity,days)
        Log.d("TTT", "Success: ${response.body()}")
        when(response.code()){
            in 200..299 ->{
                Log.d("TTT", "Success: ${response.body()!!.location.country}")
                val forecast: ForecastRespons = response.body()!!
                emit(Result.success(forecast))
            }
            else -> {
                Log.d("TTT", "Error: Data -> error --------------------------------- error")
                emit(Result.failure(java.lang.Exception("no Response")))}
        }
    }.catch { emit(Result.failure(it)) }
        .flowOn(Dispatchers.IO)
}


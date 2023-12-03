package uz.gita.luis.weatherapp.presentation.screens.main.mainviewmodel

import androidx.lifecycle.LiveData
import uz.gita.luis.weatherapp.data.common.ForecastRespons

interface MainViewModel {

     val loadingLiveData:LiveData<Boolean>
     val successLiveData: LiveData<ForecastRespons>
     val errorLiveData: LiveData<String>
     fun loadWeather(nameOfCity:String,day:Int)
}
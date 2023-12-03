package uz.gita.luis.weatherapp.presentation.screens.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import uz.gita.luis.weatherapp.R
import uz.gita.luis.weatherapp.data.common.ForecastRespons
import uz.gita.luis.weatherapp.databinding.ScreenWeatherBinding
import uz.gita.luis.weatherapp.presentation.adapters.MainAdapter
import uz.gita.luis.weatherapp.presentation.screens.main.mainviewmodel.MainViewModel
import uz.gita.luis.weatherapp.presentation.screens.main.mainviewmodel.MainViewModelClass

class MainScreen:Fragment(R.layout.screen_weather){
    private val viewModel: MainViewModel by viewModels<MainViewModelClass>()
    private val binding by viewBinding(ScreenWeatherBinding::bind)
    private lateinit var adapter:MainAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.errorLiveData.observe(viewLifecycleOwner,errorLiveDataObserver)
        viewModel.loadingLiveData.observe(viewLifecycleOwner,loadingLiveDataObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner,successLiveDataObserver)
        viewModel.loadWeather("Tashkent",14)
    }
    val errorLiveDataObserver = Observer<String>{
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
    val loadingLiveDataObserver = Observer<Boolean>{
        val visible = View.VISIBLE
        val invisible = View.GONE
        if (it) {
            binding.progress.visibility = visible
            binding.progress2.visibility = visible
            binding.progress3.visibility = visible
            binding.gradus.visibility =          invisible
            binding.imgWeatherIcon.visibility =  invisible
            binding.nameOfCity.visibility =      invisible
            binding.search.visibility =          invisible
            binding.settings.visibility =        invisible
            binding.textWeatherInfo.visibility = invisible

        }
        else{
            binding.progress.visibility = invisible
            binding.progress2.visibility = invisible
            binding.progress3.visibility = invisible
            binding.gradus.visibility =          visible
            binding.imgWeatherIcon.visibility =  visible
            binding.nameOfCity.visibility =      visible
            binding.search.visibility =          visible
            binding.settings.visibility =        visible
            binding.textWeatherInfo.visibility = visible

        }
    }
    val successLiveDataObserver = Observer<ForecastRespons>{
        binding.gradus.text = "${it.current.temp_c}°C"
        val url = "https:${it.current.condition.icon}"
        Glide.with(binding.root.context).load(url).into(binding.imgWeatherIcon)
        binding.nameOfCity.text =        it.location.name.toString()
        binding.textWeatherInfo.text =   it.current.condition.text
        if(it.current.condition.text=="Sunny")
        binding.backgroundImage.setImageResource(R.drawable.snow2)
        adapter = MainAdapter(it)
        binding.mainRecyclerview.adapter = adapter
//        adapter.notifyDataSetChanged()
     }
    }

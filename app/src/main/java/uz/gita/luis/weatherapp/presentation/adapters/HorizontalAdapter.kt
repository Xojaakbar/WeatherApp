package uz.gita.luis.weatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.luis.weatherapp.data.common.Forecastday
import uz.gita.luis.weatherapp.databinding.ItemHorizontalBinding

class HorizontalAdapter (private val list: List<Forecastday>): RecyclerView.Adapter<HorizontalAdapter.ViewHola>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHola {
            return ViewHola(ItemHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }

        override fun onBindViewHolder(holder: ViewHola, position: Int) {
            holder.bind(list[0].hour[position]) //todo
        }

        override fun getItemCount(): Int = list.size

        inner class ViewHola(private val binding: ItemHorizontalBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(hour: uz.gita.luis.weatherapp.data.common.Hour) {
                binding.time.text = hour.time.toString().removeRange(0,10)
                binding.gradus.text = "${hour.temp_c}°C"
                val url = "https:${hour.condition.icon}"
                Glide.with(binding.root.context).load(url).into(binding.imgWeatherIcon)
            }
        }
    }

package uz.gita.luis.weatherapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import uz.gita.luis.weatherapp.data.common.Forecastday
import uz.gita.luis.weatherapp.databinding.ItemVerticalBinding

class VerticalAdapter(private val list: List<Forecastday>): RecyclerView.Adapter<VerticalAdapter.ViewHola>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHola {
        return ViewHola(ItemVerticalBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHola, position: Int) {
        holder.bind(list[position].day)
    }

    override fun getItemCount(): Int = list.size


    inner class ViewHola(private val binding: ItemVerticalBinding):ViewHolder(binding.root){
        fun bind(dayData: uz.gita.luis.weatherapp.data.common.Day) {
            binding.gradus.text = "${dayData.maxtemp_c}°C"
//            txtTime.text = time.substring(11, time.length)
//            txtTemperature.text = list[adapterPosition].temp_c.toInt().toString() + "℃"

            val url = "https:${dayData.condition.icon}"
            Glide.with(binding.root.context).load(url).into(binding.imgWeatherIcon)
            binding.nameOfDay.text = list[adapterPosition].date
        }
    }
}
package com.example.testexample.presentation.main.adapter

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testexample.databinding.ItemBinding
import com.example.testexample.domain.common.model.SimpleResponseModel

class SimpleAdapter(private val context: Context) :
    RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    var list = ArrayList<SimpleResponseModel>()

    fun set(list: List<SimpleResponseModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    private var itemClickListener: ((id: String, root: CardView, view: ImageView, pos: Int) -> Unit)? =
        null

    fun setItemClickListener(f: (id: String, root: CardView, view: ImageView, pos: Int) -> Unit) {
        itemClickListener = f
    }

    inner class ViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bindData(data: SimpleResponseModel) {
            binding.name.text = data.name
            binding.brand.text = data.brand
            binding.rom.text = data.attributes[1].value
            binding.ram.text = data.attributes[0].value

            Glide.with(context).load(data.image.url).into(binding.image)

            binding.image.run {
                binding.image.layoutParams.width = data.image.width.toInt()
                binding.image.layoutParams.height = data.image.height.toInt()
                binding.image.requestLayout()
            }

            Log.d(
                "spdhsfhskfhjkhd",
                "bindData: ${binding.image.layoutParams.width} ${binding.image.layoutParams.height}"
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bindData(list[position])

    override fun getItemCount(): Int = list.size
}
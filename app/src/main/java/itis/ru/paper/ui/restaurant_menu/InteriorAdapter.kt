package itis.ru.paper.ui.restaurant_menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import itis.ru.paper.R
import kotlinx.android.synthetic.main.item_dish.view.*
import kotlinx.android.synthetic.main.item_restaurant_photo.view.*

class InteriorAdapter(private val clickListener: (String) -> Unit) :
    ListAdapter<String, InteriorAdapter.Holder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(
            inflater.inflate(
                R.layout.item_restaurant_photo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(url: String, clickListener: (String) -> Unit) {
            Glide.with(itemView).load(url).into(itemView.iv_restaurant_photo)
            itemView.setOnClickListener {
                clickListener(url)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
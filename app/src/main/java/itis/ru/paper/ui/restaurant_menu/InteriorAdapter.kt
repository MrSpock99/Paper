package itis.ru.paper.ui.restaurant_menu

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import itis.ru.paper.R
import kotlinx.android.synthetic.main.item_restaurant_photo.view.*

class InteriorAdapter(
    private val clickListener: (String) -> Unit,
    var list: List<String>
) :
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

    override fun submitList(list: MutableList<String>?) {
        super.submitList(list)
        if (list != null) {
            this.list = list
        }
    }


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(url: String, clickListener: (String) -> Unit) {
            Glide.with(itemView).load(url).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    p0: GlideException?,
                    p1: Any?,
                    p2: com.bumptech.glide.request.target.Target<Drawable>?,
                    p3: Boolean
                ): Boolean {
                    itemView.pb_downloading.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    p0: Drawable?,
                    p1: Any?,
                    p2: com.bumptech.glide.request.target.Target<Drawable>?,
                    p3: DataSource?,
                    p4: Boolean
                ): Boolean {
                    itemView.pb_downloading.visibility = View.GONE
                    return false
                }
            }).into(itemView.iv_restaurant_photo)
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
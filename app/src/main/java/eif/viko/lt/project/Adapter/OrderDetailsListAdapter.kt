package eif.viko.lt.project.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import eif.viko.lt.project.Model.Item
import eif.viko.lt.project.R
import kotlinx.android.synthetic.main.order_details_items.view.*

class OrderDetailsListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Item, OrderDetailsListAdapter.ItemViewHolder>(ItemDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.order_details_items, parent, false), interaction
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<Item>) {
        submitList(data.toMutableList())
    }

    inner class ItemViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)
        }

        fun bind(item: Item) = with(itemView) {
            Picasso.get().load(item.imageUrl).into(order_det_item_pick)
            orderDetPrice.text = item.price
            ordertDetTitle.text = item.title
        }
    }

    interface Interaction {

    }

    private class ItemDC : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(
            oldItem: Item,
            newItem: Item
        )= oldItem==newItem

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item
        ) = oldItem.id == newItem.id
    }
}
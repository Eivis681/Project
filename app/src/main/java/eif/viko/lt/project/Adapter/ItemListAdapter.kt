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
import kotlinx.android.synthetic.main.shop_item.view.*

class ItemListAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Item, ItemListAdapter.ItemViewHolder>(ItemDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.shop_item, parent, false), interaction
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
            itemView.addToCartBut.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return
            val clicked = getItem(adapterPosition)
            when (v){
                itemView.addToCartBut -> interaction?.clickOnAddToCart(clicked)
                itemView -> interaction?.clickOnItem(clicked)
            }

        }

        fun bind(item: Item) = with(itemView) {
            titleName.text= item.title
            itemPrice.text=item.price
            Picasso.get().load(item.imageUrl).into(prekesImage)
        }
    }

    interface Interaction {
        fun clickOnItem(item: Item)
        fun clickOnAddToCart(item: Item)
    }

    private class ItemDC : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(
            oldItem: Item,
            newItem: Item
        )= oldItem == newItem

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item
        )=oldItem.id == newItem.id
    }
}
package eif.viko.lt.project.Adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import eif.viko.lt.project.Model.Order
import eif.viko.lt.project.R
import kotlinx.android.synthetic.main.order_item.view.*
import kotlinx.android.synthetic.main.shop_item.view.*

class OrderListAdapter(private val interaction: Interaction? = null) :
        ListAdapter<Order, OrderListAdapter.OrderViewHolder>(OrderDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = OrderViewHolder(
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.order_item, parent, false), interaction
    )

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) = holder.bind(getItem(position))

    fun swapData(data: List<Order>) {
        submitList(data.toMutableList())
    }

    inner class OrderViewHolder(itemView: View,
                                private val interaction: Interaction?) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)
            when (v){
                itemView -> interaction?.clickOnItem(clicked)
            }
        }

        fun bind(item: Order) = with(itemView) {
            orderIdText.text = item.id
            orderPriceText.text = item.totalPrice
        }
    }

    interface Interaction {
        fun clickOnItem(order: Order)
    }

    private class OrderDC : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(
                oldItem: Order,
                newItem: Order
        )= oldItem == newItem

        override fun areContentsTheSame(
                oldItem: Order,
                newItem: Order
        )=oldItem.id == newItem.id
    }
}
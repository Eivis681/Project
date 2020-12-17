package eif.viko.lt.project.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import eif.viko.lt.project.Adapter.OrderListAdapter
import eif.viko.lt.project.Model.Order
import eif.viko.lt.project.R
import eif.viko.lt.project.ViewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_order.*


class OrderFragment : Fragment(R.layout.fragment_order), OrderListAdapter.Interaction {

    private lateinit var itemViewModel: ItemViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        val itemAdapter: OrderListAdapter by lazy {OrderListAdapter(this)}

        itemViewModel.displayOrder().observe(viewLifecycleOwner, Observer {
            itemAdapter.swapData(it)
        })

        order_recicle_view.apply {
            layoutManager = LinearLayoutManager(this@OrderFragment.context)
            adapter = itemAdapter
        }

    }

    override fun clickOnItem(order: Order) {
        val action = OrderFragmentDirections.actionOrderFragmentToOrderDetailsFragment(order)
        findNavController().navigate(action)
    }

}
package eif.viko.lt.project.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import eif.viko.lt.project.Adapter.OrderDetailsListAdapter
import eif.viko.lt.project.Model.Item
import eif.viko.lt.project.R
import kotlinx.android.synthetic.main.fragment_order_details.*

class OrderDetailsFragment : Fragment(R.layout.fragment_order_details) {

    private val args: OrderFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val itemAdapter: OrderDetailsListAdapter by lazy {OrderDetailsListAdapter()}


        //itemAdapter.swapData(args.order!!.itemArray)
        itemAdapter.submitList(args.order?.itemArray)

        oreder_det_recicleView.apply {
            layoutManager = LinearLayoutManager(this@OrderDetailsFragment.context)
            adapter = itemAdapter
        }

    }

}
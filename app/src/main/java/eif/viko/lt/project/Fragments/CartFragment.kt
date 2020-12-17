package eif.viko.lt.project.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import eif.viko.lt.project.Adapter.CartItemListApdapter
import eif.viko.lt.project.Model.Item
import eif.viko.lt.project.Model.Order
import eif.viko.lt.project.R
import eif.viko.lt.project.ViewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_cart.*


class CartFragment : Fragment(R.layout.fragment_cart),CartItemListApdapter.Interaction {

    private lateinit var itemViewModel: ItemViewModel
    private var items: List<Item> = arrayListOf()
    private val db = FirebaseFirestore.getInstance()
    private  val user = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        val itemAdaptep: CartItemListApdapter by lazy { CartItemListApdapter(this) }


        itemViewModel.displayCart().observe(viewLifecycleOwner, Observer {
            itemAdaptep.swapData(it)
            var allItemOrders: List<Item> = it
            items = allItemOrders
            var tot:Double=0.0
            it.forEach{doc->
                var price= doc.price.toDouble()
                tot = tot + price
            }
            totalPriceTexrView.text = tot.toString()
        })

        cartRecicleView.apply {
            layoutManager = LinearLayoutManager(this@CartFragment.context)
            adapter = itemAdaptep
        }

        buyCartBut.setOnClickListener{
            val order = Order(java.util.UUID.randomUUID().toString(), totalPriceTexrView.text.toString(),items)

            itemViewModel.addToOrder(order)
            //db.collection("cart").document("${user.uid}").delete()
            items.forEach{
                itemViewModel.clearCart(it)
            }
            Toast.makeText(context, "Your order has been placed", Toast.LENGTH_LONG).show()

        }

    }

    override fun clickOnItem(item: Item) {
        val action = CartFragmentDirections.actionCartFragmentToCartItemDetailFragment(item)
        findNavController().navigate(action)
    }

    override fun clickOnRemoveFromCart(item: Item) {
        itemViewModel.removeFromCart(item)

    }

}
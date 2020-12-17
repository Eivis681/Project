package eif.viko.lt.project.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import eif.viko.lt.project.Adapter.ItemListAdapter
import eif.viko.lt.project.Model.Item
import eif.viko.lt.project.R
import eif.viko.lt.project.ViewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home), ItemListAdapter.Interaction {

    private lateinit var itemViewModel: ItemViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel=ViewModelProvider(this).get(ItemViewModel::class.java)

        //val item = Item(java.util.UUID.randomUUID().toString(), "ViewSonic Photographer Monitor","https://images-na.ssl-images-amazon.com/images/I/51MnwE7nboL._AC_SL1000_.jpg", "Screen size 24 Inches, Resolution FHD 1080p","€232.43")
        //val item1 = Item(java.util.UUID.randomUUID().toString(), "Multimedia PC with 3 Year Warranty", "https://images-na.ssl-images-amazon.com/images/I/41Z8FWiultL._AC_.jpg", "The device is equipped with a very fast i7 4-core processor and fast 16GB branded memory that provide more than enough power for compute-intensive applications, multitasking and multimedia. The large 512GB SSD + 2TB HDD is ideal for storing all your data. The fast DVD burner reliably secures everything to CD or DVD.", "€419.00")
        //val item2 = Item(java.util.UUID.randomUUID().toString(), "Lenovo Laptop 011", "https://images-na.ssl-images-amazon.com/images/I/413BhtTJ7nL._AC_.jpg", "The Lenovo Laptop is equipped with an AMD dual core processor that provides enough power for the office, home work and the Internet. A large 512 GB SSD provides more than enough space for your data and applications", "€519.90")
        //val item3 = Item(java.util.UUID.randomUUID().toString(), "Razer Kraken Gaming Headset", "https://images-na.ssl-images-amazon.com/images/I/71XwiDoE4pL._AC_SL1500_.jpg", "Designed to be extremely lightweight at just 275g, it delivers premium sound and hours of gaming fun without limiting the gamer by the headset. The cardioid recording pattern records noise from a narrower angle. It ensures that the voice is clearly heard when talking and does not absorb noise from the sides or back of the microphone.","€59.00")
       // val item4 = Item(java.util.UUID.randomUUID().toString(), "Apple Magic Mouse 2", "https://images-na.ssl-images-amazon.com/images/I/41aQ%2B-b%2B86L._AC_SL1000_.jpg", "Original Apple product","€75.90")
        //val item5 = Item(java.util.UUID.randomUUID().toString(), "Logitech M185 wireless mouse", "https://images-na.ssl-images-amazon.com/images/I/61yVOikyr-L._AC_SL1500_.jpg", "Wireless plug and play connection: The freedom of movement of a wireless connection with the reliability of a mouse with a cable - in the office, when gaming, with or without a mouse pad, on Windows and Mac computers", "€11.49")
       /* itemViewModel.addToItem(item)
        itemViewModel.addToItem(item1)
        itemViewModel.addToItem(item2)
        itemViewModel.addToItem(item3)
        itemViewModel.addToItem(item4)
        itemViewModel.addToItem(item5)*/


        val itemListAdapter: ItemListAdapter by lazy { ItemListAdapter(this) }
        itemViewModel.displayItem().observe(viewLifecycleOwner, Observer {
            itemListAdapter.swapData(it)
        })

        home_recicle_view.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            adapter = itemListAdapter
        }
    }

    override fun clickOnItem(item: Item) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item)
        findNavController().navigate(action)
    }

    override fun clickOnAddToCart(item: Item) {
        itemViewModel.addToCart(item)
        Toast.makeText(context, "Your item has been added to cart", Toast.LENGTH_LONG).show()
    }
}
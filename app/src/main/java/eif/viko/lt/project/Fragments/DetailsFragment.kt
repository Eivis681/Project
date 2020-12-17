package eif.viko.lt.project.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import eif.viko.lt.project.Model.Item
import eif.viko.lt.project.R
import eif.viko.lt.project.ViewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment(R.layout.fragment_details) {

    val args: HomeFragmentArgs by navArgs()
    private lateinit var itemViewModel: ItemViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        Picasso.get().load(args.item?.imageUrl).into(imageView2)
        detaislTitle.text = args.item?.title
        detailsDetails.text =args.item?.description
        priceText.text = args.item?.price

        addToCartDetailsBut.setOnClickListener{
            val item = Item(args.item?.id!!, args.item?.title!!, args.item?.imageUrl!!, args.item?.description!!, args.item?.price!!)
            itemViewModel.addToCart(item)
            Toast.makeText(context, "Your item has been added to cart", Toast.LENGTH_LONG).show()
        }
    }
}
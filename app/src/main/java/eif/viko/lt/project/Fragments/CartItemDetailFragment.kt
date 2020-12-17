package eif.viko.lt.project.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import eif.viko.lt.project.R
import kotlinx.android.synthetic.main.fragment_cart_item_detail.*
import kotlinx.android.synthetic.main.fragment_details.*


class CartItemDetailFragment : Fragment(R.layout.fragment_cart_item_detail) {

    val args: HomeFragmentArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Picasso.get().load(args.item?.imageUrl).into(imageView3)
        detaislTitle2.text = args.item?.title
        detailsDetails2.text =args.item?.description
        priceText2.text = args.item?.price

    }
}
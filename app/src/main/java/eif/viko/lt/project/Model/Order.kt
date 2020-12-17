package eif.viko.lt.project.Model

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(
        var id:String ="",
        var totalPrice: String="",
        var itemArray: List<Item>
): Parcelable
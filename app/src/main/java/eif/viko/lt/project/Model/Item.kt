package eif.viko.lt.project.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    var id:String="",
    var title:String="",
    var imageUrl:String="",
    var description:String="",
    var price:String=""
):Parcelable
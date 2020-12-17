package eif.viko.lt.project.Repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import eif.viko.lt.project.Model.Item
import eif.viko.lt.project.Model.Order

object ItemRepository {

    private val db = FirebaseFirestore.getInstance()
    private  val user = FirebaseAuth.getInstance()

    fun addToItem(item: Item):Task<Void>{
        val document = db.collection("items")
            .document("data")
            .collection("items")
            .document(item.id)
        return document.set(item)
    }

    fun displayItem()= db.collection("items/data/items")

    fun addToCart(item: Item):Task<Void>{
        val document = db.collection("cart")
            .document("${user.uid}")
            .collection("items")
            .document(item.id)
        return document.set(item)
    }

    fun displayCart()=db.collection("cart/${user.uid}/items")

    fun removeFromCart(item: Item):Task<Void>{
        val document = db.collection("cart/${user.uid}/items").document(item.id)
        return document.delete()
    }

    fun clearCart(item: Item):Task<Void>{
        val document = db.collection("cart").document("${user.uid}").collection("items").document(item.id)
        return document.delete()
    }

    fun addToOrder(order: Order):Task<Void>{
        val document = db.collection("order")
            .document("${user.uid}")
            .collection("items")
            .document(order.id)
        return document.set(order)
    }

    fun displayOrder()=db.collection("order/${user.uid}/items")


}
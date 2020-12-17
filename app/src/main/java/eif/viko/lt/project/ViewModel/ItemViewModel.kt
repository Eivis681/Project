package eif.viko.lt.project.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import eif.viko.lt.project.Model.Item
import eif.viko.lt.project.Model.Order
import eif.viko.lt.project.Repository.ItemRepository


const val TAG = "ItemViewModel"

class ItemViewModel:ViewModel() {

    private val repository = ItemRepository
    private val items: MutableLiveData<List<Item>> = MutableLiveData()
    private val orders: MutableLiveData<List<Order>> = MutableLiveData()

    fun addToItem(item: Item) {
        repository.addToItem(item).addOnSuccessListener {
            Log.w(TAG,"Sekmingai prideta prie sarasu")
        }.addOnFailureListener{
            Log.w(TAG,"Klaida neprideta")
        }
    }

    fun displayItem():LiveData<List<Item>>{
        repository.displayItem().addSnapshotListener { value, error ->
            val tempList:MutableList<Item> = mutableListOf()
            value?.forEach{doc->
                val data = doc.toObject(Item::class.java)
                tempList.add(data)
            }
            items.value=tempList
        }
        return items
    }

    fun addToCart(item: Item) {
        repository.addToCart(item).addOnSuccessListener {
            Log.w(TAG,"Sekmingai prideta prie sarasu")
        }.addOnFailureListener{
            Log.w(TAG,"Klaida neprideta")
        }
    }

    fun displayCart():LiveData<List<Item>>{
        repository.displayCart().addSnapshotListener { value, error ->
            val tempList:MutableList<Item> = mutableListOf()
            value?.forEach{doc->
                val data = doc.toObject(Item::class.java)
                tempList.add(data)
            }
            items.value=tempList
        }
        return items
    }

    fun removeFromCart(item: Item) {
        repository.removeFromCart(item).addOnFailureListener{
            Log.e(TAG, "Nepavyko istrinti")
        }
    }

    fun clearCart(item: Item){
        repository.clearCart(item).addOnFailureListener{
            Log.e(TAG, "Nepavyko istrinti")
        }
    }

    fun addToOrder(order: Order) {
        repository.addToOrder(order).addOnSuccessListener {
            Log.w(TAG,"Sekmingai prideta prie sarasu")
        }.addOnFailureListener{
            Log.w(TAG,"Klaida neprideta")
        }
    }

    fun displayOrder():LiveData<List<Order>>{
        repository.displayOrder().addSnapshotListener { value, error ->
            val tempList:MutableList<Order> = mutableListOf()
            value?.forEach{doc->
                val name = doc.data["itemArray"] as List<Item>
                val id = doc.data["id"]
                val totalPrice = doc.data["totalPrice"]
                val order = Order(id.toString(), totalPrice.toString(), name)
               tempList.add(order)
            }
            orders.value=tempList
        }
        return orders
    }

}
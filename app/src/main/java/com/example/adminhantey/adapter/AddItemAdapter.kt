package com.example.adminhantey.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminhantey.databinding.ItemItemBinding

class AddItemAdapter(private val MenuItemName:ArrayList<String>, private val MenuItemPrice:ArrayList<String>, private val MenuItemImage:ArrayList<Int>): RecyclerView.Adapter<AddItemAdapter.AddItemViewHolder>() {
private val  quality=IntArray(MenuItemName.size){1}
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
      val binding=ItemItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddItemViewHolder(binding)
    }



    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int=MenuItemName.size
    inner class AddItemViewHolder(private val binding: ItemItemBinding) :RecyclerView.ViewHolder(binding.root){
fun bind(position: Int){
     binding.apply {
         foodnametextview.text=MenuItemName[position]
         pricetextview.text=MenuItemPrice[position]
         foodImageView.setImageResource(MenuItemImage[position])

         minus.setOnClickListener {
             decreaseQuantity()
             increaseQuantity()
             deleteQuantity(position)
         }

     }
    }

        private fun decreaseQuantity() {

        }
        private fun increaseQuantity() {
            TODO("Not yet implemented")
        }
        private fun deleteQuantity(position:Int) {
            MenuItemName.removeAt(position)
            MenuItemPrice.removeAt(position)
            MenuItemImage.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,MenuItemName.size)
        }
    }
}
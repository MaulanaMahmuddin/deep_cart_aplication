package com.example.deepcart.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deepcart.R

class PostAdapter(private val list: List<PostResponse>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private val pathImages: String = "http://192.168.96.29:8000/storage/"

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageItem: ImageView = itemView.findViewById(R.id.imageItem)
        private val textItemName: TextView = itemView.findViewById(R.id.textItemName)
        private val textItemPrice: TextView = itemView.findViewById(R.id.textItemPrice)
        private val textItemQuantity: TextView = itemView.findViewById(R.id.textItemQuantity)

        fun bind(postResponse: PostResponse) {
            with(itemView) {
                val gBarang = postResponse.gBarang ?: ""
                val nBarang = postResponse.nBarang ?: ""
                val hrgBarang = postResponse.hrgBarang ?: ""
                val qtyVKeranjang = postResponse.qtyVKeranjang

                Glide.with(this)
                    .load(pathImages+gBarang)
                    .into(imageItem)

                textItemName.text = nBarang
                textItemPrice.text = hrgBarang
                textItemQuantity.text = qtyVKeranjang.toString() + "x"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}

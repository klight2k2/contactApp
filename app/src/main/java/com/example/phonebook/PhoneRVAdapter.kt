package com.example.phonebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhoneRVAdapter(val items:ArrayList<PhoneModel>,val listener: ItemClickListener?):RecyclerView.Adapter<PhoneRVAdapter.PhoneRVViewHolder> ()  {

    inner class PhoneRVViewHolder(val itemView: View):RecyclerView.ViewHolder(itemView){
        val phoneName= itemView.findViewById<TextView>(R.id.text_name)
        val phoneImage= itemView.findViewById<TextView>(R.id.text_avatar)
        init {
            itemView.setOnClickListener {
                listener?.ItemClick(adapterPosition)
            }
        }


    }

    interface ItemClickListener {
        fun ItemClick(position: Int)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhoneRVAdapter.PhoneRVViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.phone_item, parent, false)

        return PhoneRVViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: PhoneRVAdapter.PhoneRVViewHolder, position: Int) {
        holder.phoneName.text=items[position].name
        val firstLetter:String= items[position].name[0].toString()
        holder.phoneImage.text=firstLetter
    }

    override fun getItemCount(): Int {
        return items.size
    }


}
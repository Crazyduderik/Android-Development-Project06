package com.example.android_development_project06
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokeAdpater(val pokeList: MutableList<String>, val pokeNameList: MutableList<String>,val pokeOrderList: MutableList<Int>) : RecyclerView.Adapter<PokeAdpater.PokeViewHolder>() {
    class PokeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val pokeImg :ImageView
        lateinit var nameTextView : TextView
        lateinit var orderTextView: TextView


        init{
            pokeImg = view.findViewById(R.id.PokemonImg)
            nameTextView = view.findViewById(R.id.PokemonName)
            orderTextView = view.findViewById(R.id.PokemonNum)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeAdpater.PokeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poke_item, parent,false)
        return PokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokeAdpater.PokeViewHolder, position: Int) {

        holder.orderTextView.text ="ID: " + pokeOrderList[position].toString()
        holder.nameTextView.text = pokeNameList[position]


        Glide.with(holder.itemView)
            .load(pokeList[position])
            .centerCrop()
            .into(holder.pokeImg)

    }

    override fun getItemCount(): Int {
        return pokeList.size
    }
}
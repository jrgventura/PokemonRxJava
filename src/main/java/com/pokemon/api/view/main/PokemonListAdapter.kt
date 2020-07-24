package com.pokemon.api.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pokemon.api.R
import com.pokemon.api.data.Model
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonListAdapter (val pokemonList: ArrayList<Model.Pokemon>, val mItemClickListener: ItemClickListener) :
    RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>(){

    interface ItemClickListener{
        fun onItemClick(position: Int)
    }

    fun updatePokemonList(newPokemonList: List<Model.Pokemon>){
        pokemonList.clear()
        pokemonList.addAll(newPokemonList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(
            view
        )
    }

    override fun getItemCount() = pokemonList.size


    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {

        holder.view.textName.text = pokemonList[position].names
        holder.view.textUrl.text = pokemonList[position].url

        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(position)
        }
    }


    class PokemonViewHolder(var view: View) : RecyclerView.ViewHolder(view)


}
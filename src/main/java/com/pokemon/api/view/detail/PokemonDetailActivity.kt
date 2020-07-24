package com.pokemon.api.view.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pokemon.api.R
import kotlinx.android.synthetic.main.activity_detail.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class PokemonDetailActivity : AppCompatActivity(){

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val namePokemon = intent.getStringExtra("namePokemon")

        //Action Bar
        val actionVar = supportActionBar
        actionVar!!.title = namePokemon
        actionVar.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetchFromRemote(namePokemon)


        observerViewModel()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun observerViewModel(){


        val options = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        viewModel.pokemonDetail.observe(this, Observer {pokemon->
            pokemon.let {

                Log.v("POKEMON_DETAIL", pokemon.name)
                Log.v("POKEMON_DETAIL", pokemon.abilities.size.toString())
                Log.v("POKEMON_DETAIL", pokemon.sprites.front_default)


                imgPokemon.let {
                    Glide.with(it)
                        .setDefaultRequestOptions(options)
                        .load(pokemon.sprites.front_default)
                        .into(it)
                }


            }
        })


        viewModel.pokemonLoadError.observe(this, Observer {
            it.let {

            }
        })

        viewModel.loading.observe(this, Observer {
            it.let {

            }
        })

    }

}













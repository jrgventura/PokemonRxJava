package com.pokemon.api.data

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    private val BASE_URL  = "https://pokeapi.co/api/v2/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PokemonApi::class.java)


    fun getPokemon() : Single<Model.Response>{
        return api.getPokemon()
    }

    fun getPokemonDetail(name: String) : Single<Model.PokemonDetail>{
        return api.getPokemonDetail(name)
    }

    /*fun login(email: String, pass:String): Single<Model.Response>{
        return api.login(LoginRequest(email, pass))
    }*/

}
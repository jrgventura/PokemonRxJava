package com.pokemon.api.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon")
    fun getPokemon(): Single<Model.Response>

    @GET("pokemon/{name}")
    fun getPokemonDetail(@Path("name") name:String): Single<Model.PokemonDetail>

    //@POST("auth/login")
    //fun login(@Body loginRequest: LoginRequest): Single<Model.Response>

}


/*data class LoginRequest(
    @SerializedName("email")
    var email : String,
    @SerializedName("password")
    var pass: String
)*/
package com.pokemon.api.data

import com.google.gson.annotations.SerializedName

object Model {

    //List Pokemon

    data class Response(
        @SerializedName("count")
        val count : Int,

        @SerializedName("results")
        val results: List<Pokemon>
    )

    data class Pokemon(
        @SerializedName("name")
        val names: String,

        @SerializedName("url")
        val url: String
    )

    //Detail Pokemon

    data class PokemonDetail (
        @SerializedName("name")
        val name: String ,

        @SerializedName("base_experience")
        val base_experience: Int,

        @SerializedName("abilities")
        val abilities: List<Abilities>,

        @SerializedName("sprites")
        val sprites: Sprites

    )

    data class Abilities(
        @SerializedName("ability")
        val ability: Ability,

        @SerializedName("is_hidden")
        val is_hidden: Boolean,

        @SerializedName("slot")
        val slot: Int
    )

    data class Ability(
        @SerializedName("name")
        val name: String,

        @SerializedName("url")
        val url: String
    )


    data class Sprites(
        @SerializedName("front_default")
        val front_default: String
    )
}














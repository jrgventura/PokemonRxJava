package com.pokemon.api.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokemon.api.data.ApiService
import com.pokemon.api.data.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PokemonViewModel : ViewModel() {

    private val pokemonApi = ApiService()
    private val disposable = CompositeDisposable()

    val pokemonList = MutableLiveData<Model.Response>()
    val pokemonLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun update(){
        loadPokemon()
    }

    private fun loadPokemon(){
        loading.value = true
        disposable.add(
            pokemonApi.getPokemon()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Model.Response>(){
                    override fun onSuccess(response: Model.Response) {
                        pokemonList.value = response
                        pokemonLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        pokemonLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}
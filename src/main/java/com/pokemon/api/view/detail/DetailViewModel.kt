package com.pokemon.api.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pokemon.api.data.ApiService
import com.pokemon.api.data.Model
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DetailViewModel : ViewModel() {

    private val pokemonApi = ApiService()
    private val disposable = CompositeDisposable()

    val pokemonDetail = MutableLiveData<Model.PokemonDetail>()
    val pokemonLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()


    fun fetchFromRemote(name:String){
        loading.value = true
        disposable.add(
            pokemonApi.getPokemonDetail(name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Model.PokemonDetail>(){
                    override fun onSuccess(response: Model.PokemonDetail) {
                        pokemonDetail.value = response
                        pokemonLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        pokemonLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }


}
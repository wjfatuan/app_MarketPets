package com.example.marketpets.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketpets.Domain.repositoryComida
import com.example.marketpets.Models.comida

class comidaViewModel:ViewModel() {

    private val repositoryComida= repositoryComida()

    fun fetchComidaData():LiveData<MutableList<comida>>{
        val mutableData= MutableLiveData<MutableList<comida>>()
        repositoryComida.getComidaData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }

    private val _comidaSeleccionada = MutableLiveData<comida>()
    val comidaSeleccionada: LiveData<comida> get() = _comidaSeleccionada

    fun seleccionarComida(item: comida) {
        _comidaSeleccionada.value = item
    }
}

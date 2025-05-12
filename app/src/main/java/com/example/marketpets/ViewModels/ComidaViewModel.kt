package com.example.marketpets.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketpets.Domain.RepositoryComida
import com.example.marketpets.Models.Comida

class ComidaViewModel:ViewModel() {

    private val repositoryComida= RepositoryComida()

    fun fetchComidaData():LiveData<MutableList<Comida>>{
        val mutableData= MutableLiveData<MutableList<Comida>>()
        repositoryComida.getComidaData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }

    private val _comidaSeleccionada = MutableLiveData<Comida>()
    val comidaSeleccionada: LiveData<Comida> get() = _comidaSeleccionada

    fun seleccionarComida(item: Comida) {
        _comidaSeleccionada.value = item
    }
}

package com.example.marketpets.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketpets.Domain.RepositoryCuidado
import com.example.marketpets.Models.Cuidado

class CuidadoViewModel : ViewModel() {

    private val repositoryCuidado= RepositoryCuidado()

    fun fetchCuidadoData():LiveData<MutableList<Cuidado>>{
        val mutableData= MutableLiveData<MutableList<Cuidado>>()
        repositoryCuidado.getCuidadoData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }

    private val _cuidadoSeleccionada = MutableLiveData<Cuidado>()
    val cuidadoSeleccionada: LiveData<Cuidado> get() = _cuidadoSeleccionada

    fun seleccionarCuidado(item: Cuidado) {
        _cuidadoSeleccionada.value = item
    }
}







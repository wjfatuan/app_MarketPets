package com.example.marketpets.Domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketpets.Models.Cuidado
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log

class RepositoryCuidado {

    fun getCuidadoData(): LiveData<MutableList<Cuidado>> {
        val mutableLiveData = MutableLiveData<MutableList<Cuidado>>()
        FirebaseFirestore.getInstance().collection("Cuidado").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Cuidado>()
            for (document in result.documents) {
                val nombre = document.getString("nombre")
                val descripcion = document.getString("descripcion")
                val disponibilidad = document.getString("disponibilidad")
                val precio = document.getLong("precio")?.toInt()
                val imagen = document.getString("imagen")
                val cuidado = Cuidado(nombre, descripcion, disponibilidad, precio, imagen)
                listData.add(cuidado)
            }
            mutableLiveData.value = listData
        }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Error getting documents: ", exception)
            }
        return mutableLiveData
    }
}
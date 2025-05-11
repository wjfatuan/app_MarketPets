package com.example.marketpets.Domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketpets.Models.comida
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log

class repositoryComida {

    fun getComidaData(): LiveData<MutableList<comida>> {
        val mutableLiveData = MutableLiveData<MutableList<comida>>()
        FirebaseFirestore.getInstance().collection("Comida").get().addOnSuccessListener { result ->
            val listData = mutableListOf<comida>()
            for (document in result.documents) {
                val nombre = document.getString("nombre")
                val descripcion = document.getString("descripcion")
                val marca = document.getString("marca")
                val precio = document.getDouble("precio")
                val tamano = document.getString("tamano")
                val disponibilidad = document.getLong("disponibilidad")?.toInt()
                val imagen = document.getString("imagen")
                val comida = comida(nombre, descripcion, marca, precio, tamano, disponibilidad, imagen)
                listData.add(comida)
            }
            mutableLiveData.value = listData
        }
            .addOnFailureListener { exception ->
                Log.e("FirestoreError", "Error getting documents: ", exception)
            }
        return mutableLiveData
    }
}
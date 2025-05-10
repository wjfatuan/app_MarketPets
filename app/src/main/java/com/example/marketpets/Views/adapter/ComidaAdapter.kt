//package com.example.marketpets.Views.adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//
//class ComidaAdapter(val context: Context): RecyclerView.Adapter<ComidaAdapter.ViewHolder>() {
//
//    var comidaList= mutableListOf<comida>()
//
//    fun setListData(data:MutableList<comida>){
//        comidaList= data
//    }
//
//    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int): ViewHolder {
//        val v= LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_comida,
//            ViewGroup, false)
//        return ViewHolder(v)
//    }
//
//
//

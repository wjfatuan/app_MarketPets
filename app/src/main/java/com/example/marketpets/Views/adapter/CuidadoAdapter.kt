package com.example.marketpets.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketpets.Models.Cuidado
import com.example.marketpets.R
import com.google.android.material.button.MaterialButton
import android.widget.ImageView
import android.widget.TextView

class CuidadoAdapter(

    val context: Context,
    private val onItemClick: (Cuidado) -> Unit
) : RecyclerView.Adapter<CuidadoAdapter.ViewHolder>() {

    private var cuidadoList: List<Cuidado> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cuidado: Cuidado) {

            itemView.findViewById<TextView>(R.id.cuidadorNombre).text = cuidado.nombre ?: "Sin nombre"
            itemView.findViewById<TextView>(R.id.cuidadorDescripcion).text = cuidado.descripcion ?: "Sin descripción"
            itemView.findViewById<TextView>(R.id.cuidadorDisponibilidad).text = cuidado.disponibilidad ?: "horas disponibles"
            itemView.findViewById<TextView>(R.id.cuidadorPrecio).text = "$ ${cuidado.precio ?: 0.0}"
            Glide.with(context).load(cuidado.imagen).into(itemView.findViewById<ImageView>(R.id.cuidadoImage))


            itemView.findViewById<MaterialButton>(R.id.btnDetalles).setOnClickListener {
                onItemClick(cuidado)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_cuidado, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cuidadoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cuidadoList[position])
    }

    fun setData(nuevaLista: List<Cuidado>) {
        cuidadoList = nuevaLista
        notifyDataSetChanged()
    }
}
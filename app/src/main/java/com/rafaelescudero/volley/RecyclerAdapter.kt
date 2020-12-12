package com.rafaelescudero.volley

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafaelescudero.volley.modelo.Personas
import kotlinx.android.synthetic.main.item.view.*
import java.lang.IllegalArgumentException

class RecyclerAdapter(val context : Context, val listaPersonas:List<Personas>, private val itemClickListener:onPersonasClickListener) :
RecyclerView.Adapter<BaseViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {

        return PersonasViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false))

    }

    interface onPersonasClickListener{

        fun onImageClick(foto:String,datos:String)

        fun onItemClick(nombre:String,apellidos:String,direccion:String)

        fun onNumberClick(telefono:Int)

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        if (holder is PersonasViewHolder){

            holder.bind(listaPersonas[position],position)

        } else {
            throw IllegalArgumentException("Error viewholder erroneo")
        }
    }

    override fun getItemCount(): Int {
        return listaPersonas.size
    }

    inner class PersonasViewHolder(itemView: View):BaseViewHolder<Personas>(itemView){

        override fun bind(item: Personas, position: Int) {

            itemView.datos.text="Nombre: " + item.nombre+ " | Apellidos: " + item.apellidos + " | Direccion: " + item.direccion

            var datos:String = "Nombre: " + item.nombre+ " | Apellidos: " + item.apellidos + " | Direccion: " + item.direccion + " | Telefono: " + item.telefono.toString()

            itemView.telefono.text=item.telefono.toString()

            Glide.with(context).load(item.foto).into(itemView.foto)

            itemView.setOnClickListener{
                itemClickListener.onItemClick(item.nombre,item.apellidos,item.direccion)
            }

            itemView.foto.setOnClickListener{
                itemClickListener.onImageClick(item.foto,datos)
            }

            itemView.telefono.setOnClickListener{
                itemClickListener.onNumberClick(item.telefono)
            }

        }

    }
}
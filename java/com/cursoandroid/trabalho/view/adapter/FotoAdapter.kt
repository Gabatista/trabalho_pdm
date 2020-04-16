package com.cursoandroid.trabalho.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.cursoandroid.trabalho.domain.Foto
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.trabalho.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.foto_item.view.*
import kotlinx.android.synthetic.main.foto_item_lista.view.*
import org.w3c.dom.Text
import java.util.ArrayList

class FotoAdapter(private val fotos: ArrayList<Foto>)
        : RecyclerView.Adapter<FotoAdapter.ViewHolder>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotoAdapter.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.foto_item,parent,false)

                return ViewHolder(view)
        }

        override fun getItemCount(): Int {
                return fotos.size
        }

        override fun onBindViewHolder(holder: FotoAdapter.ViewHolder, position: Int) {
                val foto = fotos[position]
                holder.title.text = foto.title
                holder.descricao.text = foto.explanation

                //Picasso.get().load(foto.hdurl).into(holder.hdurl)
        }

     open class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
                val title: TextView = itemView.tv_titulo
                val descricao: TextView = itemView.tv_descricao
                val hdurl: ImageView = itemView.iv_foto
        }

}







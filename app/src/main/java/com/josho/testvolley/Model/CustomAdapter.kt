package com.josho.testvolley.Model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.josho.testvolley.R

class CustomAdapter (var items : ArrayList<Producto>, val context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var listaProductos : ArrayList<Producto>? = null

    init {
        this.items = this.listaProductos!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return this.items.count()
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(vista: View):RecyclerView.ViewHolder(vista){

    }


}
package com.josho.testvolley


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.josho.testvolley.Model.Producto
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    var listaProductos: ArrayList<Producto> = ArrayList<Producto>()
    var lista: ArrayList<String> = ArrayList()
    val url = "http://192.168.62.91:3000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            var intent = Intent(this , Registrar::class.java)
            startActivity(intent)
        }

        val queue = Volley.newRequestQueue(this)

        val stringRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener<JSONArray> { response ->
                for (i in 0 until response.length()) {
                    val item = response.getJSONObject(i)
                    var producto = Producto(
                        item.getInt("id"),
                        item.getString("nombre"),
                        item.getDouble("precio")
                    )
                    listaProductos.add(producto)
                    lista.add("${item.getString("nombre")} $ ${item.getDouble("precio")}")
                }
                var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista)
                lv_productos.adapter = adapter

                searcher.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        adapter.filter.filter(newText)
                        adapter.notifyDataSetChanged()
                        return true
                    }

                })

                lv_productos.setOnItemClickListener { parent, view, position, id ->
                    var pantalla = Intent(this, Modificar::class.java)
                    pantalla.putExtra("productoModificar", listaProductos[position])
                    startActivity(pantalla)
                }

                lv_productos.setOnItemLongClickListener(object : AdapterView.OnItemLongClickListener{
                    override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
                        deleteDialog(lv_productos.getItemAtPosition(position).toString(), position)//Trim: elimina espacio de las orillas
                        return true
                    }
                })


            },
            Response.ErrorListener { error ->
               tv_respuesta.setText(error.toString())
            })


        queue.add(stringRequest)


    }


    fun deleteDialog(elemento:String, posicion:Int){
        val queue = Volley.newRequestQueue(this)
        var alert = AlertDialog.Builder(this)
        var producto = listaProductos[posicion]

        alert.setTitle("Eliminacion")
        alert.setMessage("¿Estás seguro de eliminar  $elemento ?")
        alert.setPositiveButton("Si"){dialog, which ->
            var peticion = JsonObjectRequest(Request.Method.DELETE,url+producto.getId(),null,
                Response.Listener { response ->
                    Toast.makeText(this, "${response.getString("Status")}", Toast.LENGTH_SHORT).show()
                },
                Response.ErrorListener {error->
                    Toast.makeText(this, "$error",Toast.LENGTH_SHORT).show()
                }
            )
            queue.add(peticion)
            finish()
            startActivity(getIntent())
        }
        alert.setNegativeButton("No"){dialog, which ->
            dialog.dismiss()
        }
        alert.show()
    }

}

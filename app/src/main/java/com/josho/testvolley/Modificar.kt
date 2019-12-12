package com.josho.testvolley

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.josho.testvolley.Model.Producto

import kotlinx.android.synthetic.main.activity_modificar.*
import kotlinx.android.synthetic.main.content_modificar.*
import kotlinx.android.synthetic.main.content_registrar.*
import org.json.JSONObject

class Modificar : AppCompatActivity() {
    val url = "http://192.168.62.91:3000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var producto = intent.getSerializableExtra("productoModificar") as Producto

        et_nombreM.setText(producto.getNombre())
        et_precioM.setText(producto.getPrecio().toString())

        btn_modificar.setOnClickListener {

            if (et_nombreM.text.toString() == "" || et_precioM.text.toString() == "") {
                Toast.makeText(this, "RELLENA TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
            } else {
                val peticiones = Volley.newRequestQueue(this)
                var productoNew = JSONObject()
                productoNew.put("nombre", et_nombreM.text.toString())
                productoNew.put("precio", et_precioM.text.toString().toDouble())

                var peticion = JsonObjectRequest(
                    Request.Method.PUT, url + producto.getId(), productoNew,
                    Response.Listener { mensaje ->
                        Toast.makeText(this, "${mensaje.getString("Status")}", Toast.LENGTH_SHORT)
                            .show()
                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }, Response.ErrorListener { error ->
                        Toast.makeText(this, "Error-> ${error}", Toast.LENGTH_SHORT).show()
                    })
                peticiones.add(peticion)
            }
        }


    }

}

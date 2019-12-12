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

import kotlinx.android.synthetic.main.activity_registrar.*
import kotlinx.android.synthetic.main.content_registrar.*
import org.json.JSONObject

class Registrar : AppCompatActivity() {
    val url = "http://192.168.62.91:3000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_registrar.setOnClickListener {

            if (et_productoNombre.text.toString() == "" || et_precio.text.toString() == "") {
                Toast.makeText(this, "RELLENA TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
            } else {
                val peticiones = Volley.newRequestQueue(this)
                var productoNew = JSONObject()
                productoNew.put("nombre", et_productoNombre.text.toString())
                productoNew.put("precio", et_precio.text.toString().toDouble())

                var peticion = JsonObjectRequest(
                    Request.Method.POST, url, productoNew,
                    Response.Listener { mensaje ->
                        var a = mensaje.getString("Status")
                        Toast.makeText(this, "$a", Toast.LENGTH_SHORT).show()
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

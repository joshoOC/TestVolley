package com.josho.testvolley.Model

import java.io.Serializable

class Producto :Serializable{
    private var id: Int = 0
    private var nombre: String = ""
    private var precio: Double = 0.0

    constructor()



    constructor(id: Int, nombre: String, precio: Double) {
        this.id = id
        this.nombre = nombre
        this.precio = precio
    }


    fun getId(): Int {
        return this.id
    }

    fun getNombre(): String {
        return this.nombre
    }

    fun getPrecio(): Double {
        return this.precio
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun setPrecio(precio: Double) {
        this.precio = precio
    }
}
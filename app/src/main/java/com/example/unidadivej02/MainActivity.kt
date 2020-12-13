package com.example.unidadivej02

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.SnackbarContentLayout
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.text.StringBuilder

class MainActivity : AppCompatActivity() {

    private val archivo = "datos.txt"
    private lateinit var edContenido : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edContenido = findViewById(R.id.edContenido)

    }

    fun lectura(v: View) {
        Log.e("ARCHIVOAPP", "Se presionó lectura")
        // se crea el flujo con un File input stream fis
        val fis = openFileInput(archivo)
        val isr = InputStreamReader(fis)
        // para la lectura se utiliza un buffer
        val bur = BufferedReader(isr)// con esto ya se puede hacer las lecturas
        var contenido= StringBuilder()//CString Builder porque trabaja mas rapido
        var linea:String? = "" //? este simbolo indica que puede ser liena o que puede ser null
        while (linea != null){
            linea = bur.readLine()
            if (linea != null) {
                Log.e("ARCHIVOAPP", linea)
                contenido.append(linea)
                contenido.append("\n")// un salto de linea

            }
        }
        edContenido.setText(contenido)//este metodo solo funciona en el EditText
        bur.close() //aquí solo se cierra ya que se hizo la lectura
        isr.close()
        fis.close()

    }

    fun escritura(v: View){
        Log.e("ARCHIVOAPP", "Se presionó escritura")
        //File output Stream fos
        val fos= openFileOutput(archivo, Context.MODE_PRIVATE)
        val contenidoTexto= edContenido.text.toString() // el contenido del cuadro de texto se contiene en esta variable

        fos.write(contenidoTexto.toByteArray())
        fos.close()// se cierra para no tener problemas con el flujo
        edContenido.text.clear()// se limpia el contenido
        Snackbar.make(v,"Se grabó", Snackbar.LENGTH_LONG).show()


    }
}
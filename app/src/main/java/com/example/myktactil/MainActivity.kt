package com.example.myktactil

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myktactil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding // Enlace a vistas XML

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita vista borde a borde
        setContentView(R.layout.activity_main) // Asigna el layouta tarves de su nombre
        binding = ActivityMainBinding.inflate(layoutInflater) // Vincula componentes de kotlin con
        setContentView(binding.root) // Asigna la vista raíz

        val mBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888) // Crea Bitmap de 500x500
        val mCanvas = Canvas(mBitmap) // Crea Canvas para dibujar en el Bitmap

        mCanvas.drawColor(Color.GRAY) // Asigna color de fondo gris al Canvas
        binding.Myimgview.setImageBitmap(mBitmap) // Asigna el Bitmap al ImageView

        val mPaint = Paint() // Crea objeto Paint para dibujar
        mPaint.color = Color.BLACK // Color inicial negro
        mPaint.style = Paint.Style.STROKE // Solo dibuja contornos
        mPaint.strokeWidth = 2F // Grosor de línea
        mPaint.isAntiAlias = true // Suaviza los bordes de las líneas

        var alto = mCanvas.height.toFloat() // Obtiene la altura del Canvas
        var ancho = mCanvas.width.toFloat() // Obtiene el ancho del Canvas

        mCanvas.drawLine(0F, alto / 2, ancho, alto / 2, mPaint) // Dibuja línea horizontal
        mCanvas.drawLine(ancho / 2, 0F, ancho / 2, alto, mPaint) // Dibuja línea vertical
        mPaint.color = Color.RED // Cambia color a rojo
        binding.Myimgview.setImageBitmap(mBitmap) // Actualiza el ImageView con el nuevo Canvas

//        val displayMetrics = DisplayMetrics().also {
//            windowManager.defaultDisplay.getMetrics(it) // Obtiene dimensiones de la pantalla
//        }

        binding.Myimgview.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, e: MotionEvent): Boolean {
                var proporcionancho = binding.Myimgview.width// Ancho de pantalla
                var proporcionalto = binding.Myimgview.height // Alto de pantalla
                var x = e.x * 500 / proporcionancho // Ajusta la coordenada x
                var y = e.y * 500 / proporcionalto // Ajusta la coordenada y

                var mensaje1: String = "(${x.toString()},${y.toString()})" // Formato para mostrar coordenadas
                binding.lblposicion.setText(mensaje1) // Muestra coordenadas absolutas
                var mensaje2: String = "(${(x - 250).toString()},${(250 - y).toString()})" // Coordenadas centradas
                binding.lblcoordenadas.setText(mensaje2) // Muestra coordenadas relativas

                mCanvas.drawCircle(x, y, 2F, mPaint) // Dibuja círculo en las coordenadas tocadas
                binding.Myimgview.setImageBitmap(mBitmap) // Actualiza el ImageView
                return true // Evento táctil procesado
            }
        })

        binding.btnRojo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mPaint.color = Color.RED // Cambia color de pintura a rojo
            }
        })

        binding.btnVerde.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mPaint.color = Color.GREEN // Cambia color de pintura a verde
            }
        })

        binding.btnAzul.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mPaint.color = Color.BLUE // Cambia color de pintura a azul
            }
        })

        /*
        binding.btncambiar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (binding.chkcambiartamno.isChecked) {
                    var valor: String = binding.txtsize.getText().toString() // Obtiene tamaño de entrada
                    var tamano: Float = valor.toFloat() // Convierte a Float
                    mPaint.strokeWidth = tamano // Cambia grosor de la línea
                    var mensaje: String = "Grosor cambiado" // Mensaje de confirmación

                    val myToast = Toast.makeText(applicationContext, mensaje, Toast.LENGTH_LONG)
                    myToast.show() // Muestra mensaje en pantalla
                }
            }
        })
        */

        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Obtiene márgenes del sistema
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom) // Ajusta padding de la vista
            insets // Retorna insets
        }
        */
    }
}

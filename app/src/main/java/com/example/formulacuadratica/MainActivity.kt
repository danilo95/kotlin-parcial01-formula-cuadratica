package com.example.formulacuadratica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

lateinit var valorA: EditText
lateinit var valorB: EditText
lateinit var valorC: EditText
lateinit var calcular: Button
lateinit var limpiar: Button
lateinit var ResultadoLabel: TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        valorA = findViewById(R.id.valorA)
        valorB = findViewById(R.id.valorB)
        valorC = findViewById(R.id.ValorC)
        calcular = findViewById(R.id.calcular)
        limpiar = findViewById(R.id.limpiar)
        limpiar.isEnabled = false
        limpiar.isClickable = false
        ResultadoLabel = findViewById(R.id.resultado)

        calcular.setOnClickListener {
            if (validarDatos()) {
                calcularEcuacion(
                    (valorA.getText().toString()).toDouble(),
                    (valorB.getText().toString()).toDouble(),
                    (valorC.getText().toString()).toDouble()
                )
                limpiar.isEnabled = true
                limpiar.isClickable = true
            }
        }

        limpiar.setOnClickListener {
            valorA.setText("");
            valorB.setText("");
            valorC.setText("");
            ResultadoLabel.setText("");
            limpiar.isEnabled = false
            limpiar.isClickable = false
        }
    }


    private fun validarDatos(): Boolean {
        var esValido: Boolean = true;

        if (valorA.getText().toString().trim()
                .isEmpty()
        ) {
            valorA.setError("Este campo es requerido ")
            esValido = false
        }
        if (valorB.getText().toString().trim()
                .isEmpty()
        ) {
            valorB.setError("Este campo es requerido ")
            esValido = false
        }
        if (valorC.getText().toString().trim()
                .isEmpty()
        ) {
            valorC.setError("Este campo es requerido ")
            esValido = false
        }
        return esValido;
    }

    private fun calcularEcuacion(a: Double, b: Double, c: Double) {
        var discriminante: Double
        var resultado1: Double
        var resultado2: Double
//se calcula el discriminante del polinomio ya que de el depende cuantas soluciones existen si es que las hay
        discriminante = (b * b) - (4 * a * c)

        if (discriminante < 0) {
            ResultadoLabel.setText("Sin soluciones reales")
        } else if (discriminante == 0.0) {
            resultado1 = (-b) / (2 * a)

            ResultadoLabel.setText("El resultado es x1=x2 \nel valor de x=" +  Math.round(resultado1 * 100.0) / 100.0 )
        } else if (discriminante > 0) {
            resultado1 = (-b + Math.sqrt(discriminante)) / (2 * a)
            resultado2 = (-b - Math.sqrt(discriminante)) / (2 * a)
            ResultadoLabel.setText("El resultado para estos valores es \n x1=" +  Math.round(resultado1 * 100.0) / 100.0 + " \n x2=" +  Math.round(resultado2 * 100.0) / 100.0 )

        }
    }
}
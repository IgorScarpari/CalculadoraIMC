package br.edu.satc.imc_calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnInformation = findViewById<Button>(R.id.btnInformation);
        btnInformation.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }

        val btnCalculator = findViewById<Button>(R.id.btnCalculator);
        btnCalculator.setOnClickListener {

            val tbxWeight = findViewById<EditText>(R.id.tbxWeight)
            val tbxHeight = findViewById<EditText>(R.id.tbxHeight)
            val lblResult = findViewById<TextView>(R.id.lblResult)

            if(tbxWeight.text.toString() != "" && tbxHeight.text.toString() != "" ) {
                val imc = calcIMC(tbxWeight.text.toString(), tbxHeight.text.toString())
                val imcResult = "IMC: " + formatNumber(imc) + "\n" + checkIMC(imc)
                lblResult.text = imcResult
            }
            else{
                lblResult.text = "Valores nulos."
            }
        }
    }
    // Cálculo do IMC
    private fun calcIMC(weight: String, height: String): Double  = weight.toDouble() / (height.toDouble() * height.toDouble())

    // Retorna string de acordo com o cálculo
    private fun checkIMC(value: Double): String{

        if(value > 0 && value < 17.1)
            return "Muito abaixo do peso.";
        else if(value >= 17.1 && value < 18.5)
            return "Abaixo do peso.";
        else if(value >= 18.5  && value < 25)
            return "Peso normal.";
        else if(value >= 25  && value < 30)
            return "Acima do peso.";
        else if(value >= 30  && value < 35)
            return "Obesidade I.";
        else if(value >= 35  && value < 40)
            return "Obesidade II(severa).";
        else
            return "Obesidade III(mórbida).";
    }

    private fun formatNumber(random: Double): String {

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        val roundoff = df.format(random)
        return roundoff.toString()
    }
}
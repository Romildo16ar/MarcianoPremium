package com.example.marcianopremium

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MatematicaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matematica)

        val operacao = intent.getStringExtra("operacao") ?: "some"

        val tvTitulo  = findViewById<TextView>(R.id.tvTituloOp)
        val etNumA    = findViewById<EditText>(R.id.etNumA)
        val etNumB    = findViewById<EditText>(R.id.etNumB)
        val btnCalc   = findViewById<Button>(R.id.btnCalcular)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarMat)

        val nomeOp = operacao.replaceFirstChar { it.uppercase() }
        val simbolo = when (operacao) {
            "some"        -> "+"
            "subtraia"    -> "−"
            "multiplique" -> "×"
            "divida"      -> "÷"
            else          -> "?"
        }
        tvTitulo.text = "$nomeOp ($simbolo)"

        btnCalc.setOnClickListener {
            val a = etNumA.text.toString()
            val b = etNumB.text.toString()
            if (a.isBlank() || b.isBlank()) {
                etNumA.error = if (a.isBlank()) "Digite um número" else null
                etNumB.error = if (b.isBlank()) "Digite um número" else null
                return@setOnClickListener
            }
            val mensagem = "$operacao $a $b"
            if (!MainActivity.historicoMensagens.contains(mensagem)) {
                MainActivity.historicoMensagens.add(0, mensagem)
            }
            val intent = Intent(this, RespostaActivity::class.java)
            intent.putExtra("operacao", operacao)
            intent.putExtra("numA", a)
            intent.putExtra("numB", b)
            startActivity(intent)
        }

        btnVoltar.setOnClickListener { finish() }
    }
}

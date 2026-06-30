package com.example.marcianopremium

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var etMensagem: EditText
    private lateinit var btnEnviar: Button
    private lateinit var btnHistorico: ImageButton
    private lateinit var rvHistorico: RecyclerView
    private lateinit var historicoAdapter: HistoricoAdapter

    companion object {
        val historicoMensagens = mutableListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etMensagem   = findViewById(R.id.etMensagem)
        btnEnviar    = findViewById(R.id.btnEnviar)
        btnHistorico = findViewById(R.id.btnHistorico)
        rvHistorico  = findViewById(R.id.rvHistorico)

        // Configura lista de histórico
        historicoAdapter = HistoricoAdapter(historicoMensagens) { mensagem ->
            enviarMensagem(mensagem)
        }
        rvHistorico.layoutManager = LinearLayoutManager(this)
        rvHistorico.adapter = historicoAdapter

        btnEnviar.setOnClickListener {
            val texto = etMensagem.text.toString()
            if (texto.isBlank()) {
                enviarMensagem("")
            } else {
                // Verifica se é operação matemática
                val operacoes = listOf("some", "subtraia", "multiplique", "divida")
                if (texto.trim().lowercase() in operacoes) {
                    val intent = Intent(this, MatematicaActivity::class.java)
                    intent.putExtra("operacao", texto.trim().lowercase())
                    startActivity(intent)
                } else {
                    enviarMensagem(texto)
                }
            }
        }

        btnHistorico.setOnClickListener {
            if (rvHistorico.visibility == RecyclerView.GONE) {
                rvHistorico.visibility = RecyclerView.VISIBLE
                historicoAdapter.notifyDataSetChanged()
            } else {
                rvHistorico.visibility = RecyclerView.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        etMensagem.text.clear()
        rvHistorico.visibility = RecyclerView.GONE
    }

    private fun enviarMensagem(mensagem: String) {
        if (mensagem.isNotBlank()) {
            if (!historicoMensagens.contains(mensagem)) {
                historicoMensagens.add(0, mensagem)
            }
        }
        val intent = Intent(this, RespostaActivity::class.java)
        intent.putExtra("mensagem", mensagem)
        startActivity(intent)
    }
}

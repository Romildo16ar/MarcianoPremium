package com.example.marcianopremium

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RespostaActivity : AppCompatActivity() {

    private val robo = MarcianoPremium(object : AcaoPersonalizada {
        private val piadas = listOf(
            "Por que o computador foi ao médico?\nPorque estava com vírus!",
            "O que o zero disse para o oito?\nQue cinto bonito!",
            "Por que o livro de matemática estava triste?\nTinha muitos problemas.",
            "O que o programador disse para a namorada?\nVocê é um array de perfeição!",
            "Quantos programadores são necessários para trocar uma lâmpada?\nNenhum, é um problema de hardware!",
            "Por que o robô tirou nota baixa?\nPorque só sabia falar em binário — era 0 ou 1!",
            "O que é um robô na chuva?\nUm robô enferrujando!",
            "Por que o Marciano nunca vai a festa?\nPorque já é ultrapassado!"
        )
        private var ultima = -1
        override fun executar(): String {
            var i: Int
            do { i = piadas.indices.random() } while (i == ultima)
            ultima = i
            return "😄 ${piadas[i]}"
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resposta)

        val tvMensagem  = findViewById<TextView>(R.id.tvMensagem)
        val tvResposta  = findViewById<TextView>(R.id.tvResposta)
        val btnVoltar   = findViewById<Button>(R.id.btnVoltar)

        val mensagem = intent.getStringExtra("mensagem") ?: ""
        val numA     = intent.getStringExtra("numA")
        val numB     = intent.getStringExtra("numB")
        val operacao = intent.getStringExtra("operacao")

        val resposta = when {
            operacao != null && numA != null && numB != null ->
                robo.responda(operacao, numA, numB)
            else ->
                robo.responda(mensagem)
        }

        val exibicao = if (operacao != null && numA != null && numB != null)
            "$operacao $numA $numB" else mensagem

        tvMensagem.text = if (exibicao.isBlank()) "(silêncio)" else "\"$exibicao\""
        tvResposta.text = resposta

        btnVoltar.setOnClickListener { finish() }
    }
}

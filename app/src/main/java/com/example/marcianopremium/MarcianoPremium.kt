package com.example.marcianopremium

interface AcaoPersonalizada {
    fun executar(): String
}

class MarcianoPremium(private val acao: AcaoPersonalizada) : MarcianoMatematico() {

    override fun responda(frase: String, vararg extras: Any): String {
        if (frase.trim().equals("agir", ignoreCase = true)) {
            return "É pra já!\n${acao.executar()}"
        }
        return super.responda(frase, *extras)
    }
}

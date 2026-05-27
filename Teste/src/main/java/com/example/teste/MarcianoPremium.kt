package com.example.teste


class MarcianoPremium(private val acao: AcaoPersonalizada) : MarcianoMatematico() {

    override fun responda(frase: String, vararg extras: Any): String {
        val fraseAjustada = frase.trim()

        // Verifica a palavra-chave "agir" antes de qualquer outra regra
        if (fraseAjustada.equals("agir", ignoreCase = true)) {
            val resultadoAcao = acao.executar()
            return "É pra já!\n$resultadoAcao"
        }

        // Delega para MarcianoMatematico (que por sua vez delega para Marciano)
        return super.responda(frase, *extras)
    }
}

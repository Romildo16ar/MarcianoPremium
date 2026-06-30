package com.example.marcianopremium

open class MarcianoMatematico : Marciano() {

    private val operacoes = setOf("some", "subtraia", "multiplique", "divida")

    override fun responda(frase: String, vararg extras: Any): String {
        val chave = frase.trim().lowercase()
        if (chave in operacoes) return calcular(chave, extras)
        return super.responda(frase, *extras)
    }

    fun ehOperacao(frase: String) = frase.trim().lowercase() in operacoes

    private fun calcular(operacao: String, extras: Array<out Any>): String {
        if (extras.size < 2) return "Preciso de dois números para isso!"
        val a = extras[0].toString().toDoubleOrNull() ?: return "Primeiro valor inválido!"
        val b = extras[1].toString().toDoubleOrNull() ?: return "Segundo valor inválido!"
        val resultado = when (operacao) {
            "some"        -> fmt(a + b)
            "subtraia"    -> fmt(a - b)
            "multiplique" -> fmt(a * b)
            "divida"      -> if (b == 0.0) "Não é possível dividir por zero!" else fmt(a / b)
            else          -> "Operação desconhecida!"
        }
        return "Essa eu sei: $resultado"
    }

    private fun fmt(v: Double): String {
        return if (v == kotlin.math.floor(v) && !v.isInfinite()) v.toLong().toString()
        else "%.2f".format(v)
    }
}

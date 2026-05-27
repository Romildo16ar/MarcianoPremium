package com.example.teste

import kotlin.math.floor


open class MarcianoMatematico : Marciano() {


    private val operacoes = setOf("some", "subtraia", "multiplique", "divida")

    override fun responda(frase: String, vararg extras: Any): String {
        val chave = frase.trim().lowercase()

        // Se for uma operação matemática, trata antes das regras base
        if (chave in operacoes) {
            return calcular(chave, extras)
        }

        // Caso contrário, delega para o comportamento base
        return super.responda(frase, *extras)
    }


    private fun calcular(operacao: String, extras: Array<out Any>): String {
        if (extras.size < 2) {
            return "Preciso de dois números para isso!"
        }

        val a = extras[0].toString().toDoubleOrNull()
            ?: return "O primeiro valor não é um número válido!"
        val b = extras[1].toString().toDoubleOrNull()
            ?: return "O segundo valor não é um número válido!"

        val resultado: String = when (operacao) {
            "some"       -> formatarResultado(a + b)
            "subtraia"   -> formatarResultado(a - b)
            "multiplique"-> formatarResultado(a * b)
            "divida"     -> {
                if (b == 0.0) "Não é possível dividir por zero!"
                else formatarResultado(a / b)
            }
            else -> "Operação desconhecida!"
        }

        return "Essa eu sei: $resultado"
    }


    private fun formatarResultado(valor: Double): String {
        return if (valor == floor(valor) && !valor.isInfinite()) {
            valor.toLong().toString()
        } else {
            "%.2f".format(valor)
        }
    }
}

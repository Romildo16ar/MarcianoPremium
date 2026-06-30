package com.example.marcianopremium

open class Marciano {

    open fun responda(frase: String, vararg extras: Any): String {
        val fraseAjustada = frase.trim()
        return when {
            ehSilencio(fraseAjustada)                              -> "Não me incomode"
            ehGrito(fraseAjustada) && ehPergunta(fraseAjustada)   -> "Relaxa, eu sei o que estou fazendo!"
            ehGrito(fraseAjustada)                                 -> "Opa! Calma aí!"
            ehPergunta(fraseAjustada)                              -> "Certamente"
            contemEu(fraseAjustada)                                -> "A responsabilidade é sua"
            else                                                   -> "Tudo bem, como quiser"
        }
    }

    protected fun ehSilencio(frase: String) = frase.isBlank()

    protected fun ehGrito(frase: String): Boolean {
        return frase.split(Regex("\\s+"))
            .filter { token -> token.any { it.isLetter() } }
            .any { token -> token.filter { it.isLetter() }.all { it.isUpperCase() } }
    }

    protected fun ehPergunta(frase: String) = frase.trimEnd().endsWith("?")

    protected fun contemEu(frase: String) =
        Regex("\\beu\\b", RegexOption.IGNORE_CASE).containsMatchIn(frase)
}

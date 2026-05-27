package com.example.teste



fun main() {

    val piadas = listOf(
        "Por que o computador foi ao médico?\nPorque estava com vírus!",
        "O que o zero disse para o oito?\nQue cinto bonito!",
        "Por que o livro de matemática estava triste?\nTinha muitos problemas.",
        "O que o programador disse para a namorada?\nVocê é um array de perfeição — sempre na posição zero do meu coração!",
        "Quantos programadores são necessários para trocar uma lâmpada?\nNenhum, é um problema de hardware!",
        "Por que o robô tirou nota baixa?\nPorque só sabia falar em binário — era 0 ou 1!",
        "O que é um robô na chuva?\nUm robô enferrujando!",
        "Por que o Marciano nunca vai a festa?\nPorque já é ultrapassado!",
        "O que o elétron disse para o próton?\nVocê me atrai!",
        "Por que o desenvolvedor usa óculos escuros?\nPorque não suporta a luz do sol — só a da tela!"
    )

    var ultimaPiada = -1

    val acaoPiada = object : AcaoPersonalizada {
        override fun executar(): String {
            // Garante que a mesma piada não se repita consecutivamente
            var indice: Int
            do { indice = piadas.indices.random() } while (indice == ultimaPiada)
            ultimaPiada = indice
            return "Piada do Marciano:\n${piadas[indice]}"
        }
    }


    val marciano = MarcianoPremium(acaoPiada)

    exibirBoasVindas()


    val leitor = System.`in`.bufferedReader()

    while (true) {
        print("\nVocê: ")
        val entrada = leitor.readLine() ?: break

        if (entrada.trim().equals("FIM", ignoreCase = false)) {
            println("\nMarciano: Tudo bem, como quiser. Até logo!")
            println("[ Encerrando o sistema... ]")
            break
        }

        val resposta = processarEntrada(marciano, entrada)
        println("Marciano: $resposta")
    }
}


fun processarEntrada(marciano: MarcianoPremium, entrada: String): String {
    val partes = entrada.trim().split(Regex("\\s+"))
    val comando = partes[0].lowercase()


    val operacoes = setOf("some", "subtraia", "multiplique", "divida")
    if (comando in operacoes && partes.size >= 3) {
        val a = partes[1]
        val b = partes[2]
        return marciano.responda(comando, a, b)
    }

    // Todos os demais casos (inclusive "agir" e frases normais)
    return marciano.responda(entrada)
}


fun exibirBoasVindas() {
    println("""
        ╔══════════════════════════════════════════════════════════════╗
        ║          🤖  BEM-VINDO AO ROBÔ MARCIANO PREMIUM  🤖         ║
        ╠══════════════════════════════════════════════════════════════╣
        ║  Comandos disponíveis:                                       ║
        ║  • Qualquer frase      → resposta padrão                     ║
        ║  • Pergunta (com ?)    → "Certamente"                        ║
        ║  • GRITO               → "Opa! Calma aí!"                    ║
        ║  • GRITO COM ?         → "Relaxa, eu sei o que estou fazendo"║
        ║  • frase com "eu"      → "A responsabilidade é sua"          ║
        ║  • (vazio)             → "Não me incomode"                   ║
        ║  ─────────────────────────────────────────────────────────── ║
        ║  • some <a> <b>        → soma                                ║
        ║  • subtraia <a> <b>    → subtração                           ║
        ║  • multiplique <a> <b> → multiplicação                       ║
        ║  • divida <a> <b>      → divisão                             ║
        ║  ─────────────────────────────────────────────────────────── ║
        ║  • agir                → conta uma piada aleatória 😄        ║
        ║  • FIM                 → encerra o programa                  ║
        ╚══════════════════════════════════════════════════════════════╝
    """.trimIndent())
}

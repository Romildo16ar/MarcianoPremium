# MarcianoPremium 🤖

Projeto desenvolvido em Kotlin utilizando conceitos de **orientação a objetos**, como herança, interface e injeção de dependência. O sistema simula um robô virtual chamado **Marciano**, capaz de responder perguntas e realizar operações matemáticas via terminal.

## Estrutura do Projeto

| Arquivo | Descrição |
|---|---|
| `AcaoPersonalizada.kt` | Interface que define o contrato do método `executar()` |
| `Marciano.kt` | Classe base com respostas fixas via `when` |
| `MarcianoMatematico.kt` | Herda de `Marciano`, adiciona operações matemáticas |
| `MarcianoPremium.kt` | Classe principal, herda de `MarcianoMatematico` e recebe `AcaoPersonalizada` via construtor |
| `Main.kt` | Ponto de entrada, loop de leitura e função `processarEntrada()` |

## Conceitos Aplicados

- **Herança** — cadeia `Marciano` → `MarcianoMatematico` → `MarcianoPremium`
- **Interface** — `AcaoPersonalizada` como contrato de comportamento
- **Injeção de dependência** — `AcaoPersonalizada` passada via construtor
- **Vararg** — método `responda()` aceita argumentos variáveis
- **Regex** — parsing da entrada com `split(Regex("\\s+"))`
- **Override** — sobrescrita do método `responda()` em dois níveis

## Comandos Disponíveis

| Comando | Exemplo | Resultado |
|---|---|---|
| `some` | `some 10 5` | `15.0` |
| `subtraia` | `subtraia 9 3` | `6.0` |
| `multiplique` | `multiplique 4 3` | `12.0` |
| `divida` | `divida 10 2` | `5.0` |
| `agir` | `agir` | executa a ação personalizada |
| `sair` | `sair` | encerra o programa |


## Tecnologias

- Kotlin
- Android Studio / IntelliJ IDEA

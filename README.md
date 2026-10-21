markdown
# kotlin-calculadora-imc

Projeto 2ª Avaliação Prática LPDM 

O app recebe o peso e a altura do usuário, calcula o IMC após o clique no botão e mostra a classificação correta (como peso normal, sobrepeso, etc). Também disponibiliza uma opção para mostrar o resultado arredondado.

## Tecnologias utilizadas
* Kotlin
* Jetpack Compose 
* Android Studio 2024.2.1

## Como executar o projeto
1. Abra o Android Studio.
2. Vá em `File` > `Open` e selecione a pasta do projeto clonado.
3. Espere o Gradle sincronizar todos os arquivos do projeto
4. Clique no botão`Run` para rodar o app.



## Estrutura do Projeto
* **MainActivity.kt**: É o arquivo principal 
* **LimitesIMC**: Objeto para centralizar as faixas de peso da tabela (18.5, 24.9, 29.9)
* **EditNumberField()**: Componente de campo de texto customizado.
* **calcularIMC()**: Função que faz a conta matemática do IMC dividindo o peso pela altura ao quadrado.
* **formatarIMC()**: Função que cuida de formatar o número final.



## Hierarquia de Composables da Tela
CalculadoraImcLayout
 column (centralizada)
 Text ("Calculador IMC")
 EditNumberField (Peso)
 EditNumberField (Altura)
 Row (Arredondar)
 	Text ("Arredondar resultado")
	Switch
 Button (Calcular)
 Text (Exibe o valor do IMC)
 Text (Exibe a classificacao textual)


* **Nome:** [João Gabriel Lopes Alvarenga]
2° período LPDM
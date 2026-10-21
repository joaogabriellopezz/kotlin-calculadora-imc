package com.example.lpdm_avaliacao2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
/////////////////////////////////////////////////////
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
//corrige o by vermlho
/////////////////////////////////////////////////////
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lpdm_avaliacao2.ui.theme.LPDMavaliacao2Theme
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ceil


object LimitesIMC {
    const val ABAIXO_DO_PESO = 18.5

    const val PESO_NORMAL = 24.9
    const val SOBREPESO = 29.9


}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LPDMavaliacao2Theme {
                TipTimeLayout()



            }
        }
    }
}






@Composable
fun TipTimeLayout(){
    var valorPesoInput by remember { mutableStateOf(value = "") }
    var alturainput by remember { mutableStateOf(value = "") }


    var arredondarResultado by remember { mutableStateOf(false) }


    val peso = valorPesoInput.toDoubleOrNull() ?: 0.0
    val altura = alturainput.toDoubleOrNull() ?: 0.0

    var imc_exibido by remember { mutableStateOf("--") }
    var clasificacao by remember { mutableStateOf("--") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "===========\nCalcular Imc\n===========",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(alignment = Alignment.Start)


        )


        EditNumberField(
            label = "Peso (kg)",
            value = valorPesoInput,
            onValueChange = { valorPesoInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth()
        )



        EditNumberField(
            label = "Altura (m)",
            value = alturainput,
            onValueChange = { alturainput = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.padding(bottom = 24.dp).fillMaxWidth()
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Arredondar resultado  ", fontSize = 16.sp)

            Switch(
                checked = arredondarResultado,
                onCheckedChange = { flag -> arredondarResultado = flag }

            )
        }



        Button(
            onClick = {
                val resultadoImc = calcularIMC(peso, altura, arredondarResultado)
                imc_exibido = formatarIMC(resultadoImc, arredondarResultado)
                clasificacao = obterClassificacao(resultadoImc)
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Calcular")
        }




        Text(
            text = "IMC: $imc_exibido",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 24.dp)
        )

        Text(
            text = "Classificacao: $clasificacao",

            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}


@Composable

fun EditNumberField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
){
    TextField(

        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(label) },
        keyboardOptions = keyboardOptions,
        modifier = modifier


    )
}



fun calcularIMC(
    p: Double,
    a: Double,
    arredonda: Boolean
): Double {
    if (a == 0.0) return 0.0
    var imc_gerado = p / (a * a)
    if (arredonda) {
        imc_gerado = ceil(imc_gerado)
    }

    return imc_gerado
}




fun formatarIMC(imc: Double, check: Boolean): String {
    if (imc == 0.0) return "--"
    return NumberFormat.getNumberInstance(Locale("pt", "BR"))
        .apply {
        val quantCasas = if (check) 0 else 2
        maximumFractionDigits = quantCasas
        minimumFractionDigits = quantCasas
    }
        .format(imc)
}




fun obterClassificacao(val_imc: Double): String {
    if (val_imc == 0.0) return "--"
    return when {
        val_imc < LimitesIMC.ABAIXO_DO_PESO -> "Abaixo do peso"
        val_imc <= LimitesIMC.PESO_NORMAL -> "Peso normal"
        val_imc <= LimitesIMC.SOBREPESO -> "Sobrepeso"
        else -> "Obesidade"







    }
}









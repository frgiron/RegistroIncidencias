package sv.edu.utec.etps1.registroincidencias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sv.edu.utec.etps1.registroincidencias.ui.theme.RegistroIncidenciasTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegistroIncidenciasTheme {
                RegistroIncidenciasApp()
            }
        }
    }
}

@Composable
fun RegistroIncidenciasApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro de Proyectos 2026",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Control de Iniciativas Estrategicas y Evolución "
        )
        Spacer(modifier = Modifier.height(24.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Alcence Iniciatias",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(text = "Generación de alto valor conforme a los objetivos de la organizacion.")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { /* Se implementará en una unidad posterior */  }) {
            Text(text = "Seguimiento")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Prototipo inicial — Unidad 1"
        )
    }
}
@Preview(showBackground = true)
@Composable
fun RegistroIncidenciasPreview() {
    RegistroIncidenciasTheme {
        RegistroIncidenciasApp()
    }
}

@Composable
fun PantallaSeguimiento() {
    // 1. Declarar los estados para el texto y el error
    var textoSeguimiento by remember { mutableStateOf("") }
    var mostrarError by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {

        // 2. Agregar el campo de texto
        OutlinedTextField(
            value = textoSeguimiento,
            onValueChange = {
                textoSeguimiento = it
                mostrarError = false // Oculta el error en cuanto el usuario empieza a escribir
            },
            label = { Text("Ingresa el texto de seguimiento") },
            isError = mostrarError, // Pinta el borde de rojo si hay error
            modifier = Modifier.fillMaxWidth()
        )

        // 3. Mostrar el mensaje de error debajo del campo si el estado es true
        if (mostrarError) {
            Text(
                text = "Error: El texto es obligatorio para el seguimiento.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 4. Actualizar el botón de tu imagen con la lógica de validación
        Button(
            onClick = {
                if (textoSeguimiento.trim().isEmpty()) {
                    mostrarError = true // Activa el mensaje de error visual
                } else {
                    mostrarError = false

                    println("Seguimiento registrado exitosamente.")
                }
            }
        ) {
            Text(text = "Seguimiento")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Prototipo inicial — Unidad 1")
    }
}
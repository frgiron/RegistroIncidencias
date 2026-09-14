package sv.edu.utec.etps1.registroincidencias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sv.edu.utec.etps1.registroincidencias.ui.theme.RegistroIncidenciasTheme

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
    // 1. Estados para capturar el texto del usuario
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    // 2. Estados para la retroalimentación (mensaje y si es un error)
    var mensajeRetroalimentacion by remember { mutableStateOf("") }
    var esError by remember { mutableStateOf(false) }

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
            text = "Control de Iniciativas Estratégicas y Evolución"
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo para el título
        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título del reporte") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo para la descripción breve
        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción breve") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3 // Da la apariencia de un área de texto más amplia
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 3. Botón principal con lógica de validación
        Button(
            onClick = {
                if (titulo.isBlank() || descripcion.isBlank()) {
                    // Estado de error: Campos vacíos
                    mensajeRetroalimentacion = "Error: Por favor completa todos los campos."
                    esError = true
                } else {
                    // Estado de éxito
                    mensajeRetroalimentacion = "¡Reporte registrado exitosamente"
                    esError = false

                    titulo = ""
                    descripcion = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Registrar Reporte")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 4. Mostrar retroalimentación (solo si hay un mensaje)
        if (mensajeRetroalimentacion.isNotEmpty()) {
            Text(
                text = mensajeRetroalimentacion,
                color = if (esError) Color.Red else Color(0xFF4CAF50), // Rojo para error, verde para éxito
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

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

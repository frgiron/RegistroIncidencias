package sv.edu.utec.etps1.registroincidencias


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sv.edu.utec.etps1.registroincidencias.ui.theme.RegistroIncidenciasTheme
import kotlin.math.abs
//Semana 11
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

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

    // Nuevo estado para la interacción táctil (Prioridad)
    var prioridadSeleccionada by remember { mutableStateOf("Media") }

    // Estados y configuración para el Acelerómetro - Semana 11
    val context = LocalContext.current
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val accelerometer = remember { sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) }

    var xVal by remember { mutableStateOf(0f) }
    var yVal by remember { mutableStateOf(0f) }
    var zVal by remember { mutableStateOf(0f) }

    DisposableEffect(accelerometer) {
        if (accelerometer != null) {
            val listener = object : SensorEventListener {
                override fun onSensorChanged(event: SensorEvent) {
                    xVal = event.values[0]
                    yVal = event.values[1]
                    zVal = event.values[2]
                }
                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
            }
            sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
            onDispose {
                sensorManager.unregisterListener(listener)
            }
        } else {
            onDispose {}
        }
    }

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
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Next
        )
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
 //-aqui
        Spacer(modifier = Modifier.height(16.dp))

        // Sección de Interacción Táctil Adicional: Selección de Prioridad[cite: 1]
        Text(
            text = "Selecciona la Prioridad:",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val prioridades = listOf("Baja", "Media", "Alta")
            prioridades.forEach { prioridad ->
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                        .clickable { prioridadSeleccionada = prioridad }, // Interacción táctil de alto nivel[cite: 1]
                    colors = CardDefaults.cardColors(
                        containerColor = if (prioridadSeleccionada == prioridad)
                            MaterialTheme.colorScheme.primaryContainer
                        else
                            MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Text(
                        text = prioridad,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .align(Alignment.CenterHorizontally),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

//-aqui
// Inicio Semana 11
        Spacer(modifier = Modifier.height(16.dp))

        // Sección de Monitoreo de Sensor (Acelerómetro - Semana 11)
        Text(
            text = "Sensor de Movimiento (Acelerómetro):",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                if (accelerometer == null) {
                    Text(
                        text = "El acelerómetro no está disponible en este dispositivo.",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                } else {
                    Text(
                        text = "X: ${String.format("%.2f", xVal)} | Y: ${String.format("%.2f", yVal)} | Z: ${String.format("%.2f", zVal)}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = if (abs(zVal) > 9.0f) "Estado: Dispositivo estable" else "Estado: Dispositivo en movimiento / inclinado",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        // Fin Semana 11

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

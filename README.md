# RegistroIncidencias

## Propósito
Con esta version de la aplicación es posible realizar el reporte colocando Titulo y breve descrcion

## Herramientas utilizadas
Para este proyecto se utilizan las siguientes tecnologías:
Android Studio Quail 3
Kotlin
GitHub

## Estado actual
El proyecto se encuentra en su primera etapa de desarrollo. Actualmente ya funciona la configuración base del proyecto en Android Studio y la interfaz gráfica correspondiente al prototipo inicial.
Se ha realizado la modificación correspondiente que permitre el ingreso de datos para los usuarios-
Semana 10: Teclado Contextual y Pantalla Táctil
* **Configuración del teclado:** Se añadieron KeyboardOptions en el campo del título con capitalización automática de oraciones (KeyboardCapitalization.Sentences) y acción IME de avance (ImeAction.Next).
* **Interacción táctil:** Se implementó una botonera interactiva utilizando componentes Card con Modifier.clickable para seleccionar la prioridad de la incidencia (Baja, Media o Alta), dando retroalimentación visual inmediata al usuario.
  
Semana 11: Integración de Sensores del Dispositivo
* **Uso del acelerómetro:** Se integró la lectura en tiempo real del sensor de movimiento mediante SensorManager, SensorEventListener y un ciclo de vida gestionado por DisposableEffect para capturar los valores de los ejes X, Y y Z.
* **Control de disponibilidad:** Se programó una validación defensiva (accelerometer == null) por si el emulador o dispositivo no cuenta con el sensor.
* **Evaluacion del Sensor:** El Senron se probo rotanto la el dispositivo a pesar de tratarse un dispositivo en emulador.

## Cómo abrir el proyecto
Para revisar o ejecutar este proyecto en un entorno local.
1. Clona este repositorio en una carpeta local en tu maquina.
2. Abre Android Studio.
3. Utiliza la opción para abrir un proyecto existente (Open) y selecciona la carpeta raíz que acabas de clonar.
4. Espera a que la sincronización de Gradle finalice completamente antes de intentar compilar o explorar los archivos.

## Autor
**Franklin R. Giron Martinez**  
Estudiante de Ingeniería en Sistemas  
Universidad Tecnológica de El Salvador  
Asignatura: Técnicas de Producción Industrial de Software I (Ciclo 02-2026)

package com.example.inscripcion
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import android.os.Bundle
import com.example.inscripcion.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val cursos = arrayOf("Computación", "Algoritmos", "Redes Sociales")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cursos)
        binding.cursoSpinner.adapter = adapter

        binding.inscribirButton.setOnClickListener {
            inscribirParticipante()
        }
    }

    private fun inscribirParticipante() {
        val nombre = binding.nombreEditText.text.toString()
        val apellido = binding.apellidoEditText.text.toString()
        val curso = binding.cursoSpinner.selectedItem.toString()

        val formaPago = if (binding.contadoRadioButton.isChecked) "Contado" else "Crédito"
        val cuotas = if (binding.cuotasSwitch.isChecked) 2 else 1

        val costoInicial: Int
        val incremento: Int
        when (curso) {
            "Computación" -> {
                costoInicial = 250
                incremento = 0
            }
            "Algoritmos" -> {
                costoInicial = 300
                incremento = 50
            }
            "Redes Sociales" -> {
                costoInicial = 200
                incremento = -100
            }
            else -> {
                costoInicial = 0
                incremento = 0
            }
        }

        val costoTotal = costoInicial + (cuotas - 1) * incremento
        val montoPagar = costoTotal / cuotas

        val resultado = "Datos del participante:\n" +
                "Nombre: $nombre\n" +
                "Apellido: $apellido\n\n" +
                "Curso seleccionado:\n" +
                "Curso: $curso\n" +
                "Costo inicial del curso: $costoInicial soles\n" +
                "Cuotas: $cuotas\n" +
                "Incremento por cuota adicional: $incremento soles\n" +
                "Costo total del curso: $costoTotal soles\n" +
                "Monto a pagar por cuota: $montoPagar soles"

        binding.resultadoTextView.text = resultado
    }
}
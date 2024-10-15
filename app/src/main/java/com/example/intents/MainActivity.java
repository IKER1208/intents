package com.example.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Habilitar Edge to Edge
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Aplicar Window Insets para manejar los márgenes con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ------------------ Intents Explícitos ------------------
        // 1. Botón para mostrar un mensaje en alerta
        Button btnMostrarAlerta = findViewById(R.id.btnMostrarAlerta);
        btnMostrarAlerta.setOnClickListener(v -> new android.app.AlertDialog.Builder(MainActivity.this)
                .setTitle("Alerta")
                .setMessage("Este es un mensaje de alerta.")
                .setPositiveButton("OK", null)
                .show());

        // 2. Botón para abrir una página de configuración
        Button btnAbrirConfiguracion = findViewById(R.id.btnAbrirConfiguracion);
        btnAbrirConfiguracion.setOnClickListener(v -> {
            Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
            startActivity(intent);
        });

        // 3. Botón para abrir la misma actividad con un mensaje (no funciona;()
        Button btnAbrirConMensaje = findViewById(R.id.btnAbrirConMensaje);
        btnAbrirConMensaje.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.putExtra("mensaje", "Hola desde la misma actividad!");
            startActivity(intent);
        });

        // ------------------ Intents Implícitos ------------------
        // 1. Botón para abrir una página web
        Button btnPagina = findViewById(R.id.btnPagina);
        btnPagina.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://music.youtube.com/"));
            startActivity(intent);
        });

        // 2. Botón para compartir texto
        Button btnCompartir = findViewById(R.id.btnCompartir);
        btnCompartir.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "olaaaaaaaa.");
            startActivity(Intent.createChooser(intent, "Compartir con"));
        });

        // 3. Botón para hacer una llamada telefónica
        Button btnLlamada = findViewById(R.id.btnLlamada);
        btnLlamada.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:8713923040"));
            startActivity(intent);
        });
    }
}

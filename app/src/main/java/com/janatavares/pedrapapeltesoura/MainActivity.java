package com.janatavares.pedrapapeltesoura;

import static kotlin.random.RandomKt.Random;

import android.graphics.Matrix;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.CountDownTimer;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    ImageView imagePedra;
    ImageView imagePapel;
    ImageView imageTesoura;
    ScaleAnimation aumentarAnimacao;
    ScaleAnimation diminuirAnimacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        //Animações
        aumentarAnimacao = new ScaleAnimation(
                1f, 1.2f, // Escala de 100% para 120% na largura
                1f, 1.2f, //Escala de 100% para 120% na altura
                Animation.RELATIVE_TO_SELF, 0.5f, //Ponto de pivo no centro horizontal
                Animation.RELATIVE_TO_SELF, 0.5f //Ponto de pivo no centro vertical
        );
        aumentarAnimacao.setDuration(500); //Duração da animação em milissegundos

        diminuirAnimacao = new ScaleAnimation(
                1.2f,1f,
                1.2f,1f,
                Animation.RELATIVE_TO_SELF, 0.5f, //Ponto de pivo no centro horizontal
                Animation.RELATIVE_TO_SELF, 0.5f //Ponto de pivo no centro vertical
        );
        diminuirAnimacao.setDuration(500); //Duração da animação em milissegundos

        imagePedra = findViewById(R.id.imagePedra);
        imagePapel = findViewById(R.id.imagePapel);
        imageTesoura = findViewById(R.id.imageTesoura);

    }

    public void selecionarPedra(View view){

        imagePedra.startAnimation(aumentarAnimacao);
        new CountDownTimer(500, 500) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                imagePedra.startAnimation(diminuirAnimacao);
            }
        }.start();
        verificarGanhador("pedra");
    }

    public void selecionarPapel(View view){

        imagePapel.startAnimation(aumentarAnimacao);
        new CountDownTimer(500, 500) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                imagePapel.startAnimation(diminuirAnimacao);
            }
        }.start();
        verificarGanhador("papel");
    }

    public void selecionarTesoura(View view){

        imageTesoura.startAnimation(aumentarAnimacao);
        new CountDownTimer(500, 500) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {
                imageTesoura.startAnimation(diminuirAnimacao);
            }
        }.start();
        verificarGanhador("tesoura");
    }
    private String gerarEscolhaAleatoriaApp() {
        String[] opcoes = {"pedra", "papel", "tesoura"};
        int numeroAleatorio = new Random().nextInt(3);
        ImageView imagemApp = findViewById(R.id.imageEscolhaApp);
        String escolhaApp = opcoes[numeroAleatorio];
        switch (escolhaApp){
            case "pedra":
                imagemApp.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imagemApp.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imagemApp.setImageResource(R.drawable.tesoura);
                break;
        }
        return escolhaApp;
    }

    private void verificarGanhador(String escolhaUsuario){
        String escolhaApp = gerarEscolhaAleatoriaApp();
        TextView textoResultado = findViewById(R.id.textResultado);
        if(Objects.equals(escolhaUsuario, escolhaApp)){
            textoResultado.setTextColor(getResources().getColor(R.color.orange, null));
            textoResultado.setText("Empate!");
        } else if(
                (escolhaUsuario == "pedra" && escolhaApp == "tesoura") ||
                (escolhaUsuario == "papel" && escolhaApp == "pedra") ||
                (escolhaUsuario == "tesoura" && escolhaApp == "papel")
        ){
            textoResultado.setTextColor(getResources().getColor(R.color.green, null));
            textoResultado.setText("Você venceu!");
        } else{
            textoResultado.setTextColor(getResources().getColor(R.color.red, null));
            textoResultado.setText("Você perdeu!");
        }
    }

}
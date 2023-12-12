package com.example.duolingo.fragments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duolingo.R;
import com.example.duolingo.bd.MyDatabase;
import com.example.duolingo.databinding.FragmentExercicioSomBinding;
import com.example.duolingo.entidades.Cliente;
import com.example.duolingo.entidades.Conta;
import com.example.duolingo.entidades.MultiplaEscolha;

import java.util.List;
import java.util.Locale;

public class ExercicioSom extends Fragment {

    private FragmentExercicioSomBinding binding;
    private Cliente clienteLogado;
    private MyDatabase db;
    private String textoEscolhido;
    private String respostaUsuario;
    private TextToSpeech textToSpeech;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExercicioSomBinding.inflate(getLayoutInflater());
        db = Room.databaseBuilder(getActivity().getApplicationContext(), MyDatabase.class, "Duolingo").allowMainThreadQueries().build();

        // Receber Bundle de MultiplaEscolha
        Bundle bundle = getArguments();
        clienteLogado = (Cliente) bundle.getSerializable("cliente_logado");


        textToSpeech = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    Locale localeBrasil = new Locale("pt", "BR");
                    Voice voice = new Voice("pt-BR", localeBrasil, Voice.QUALITY_HIGH, Voice.LATENCY_NORMAL, false, null);
                    textToSpeech.setVoice(voice);
                    textToSpeech.setLanguage(localeBrasil);
                }
            }
        });

        textoEscolhido = escolherTexto();
        binding.btnSom.setOnClickListener(new View.OnClickListener() { // tocar o audio
            @Override
            public void onClick(View v) {
                textToSpeech.speak(textoEscolhido, TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        binding.btnConferirSom.setOnClickListener(new View.OnClickListener() {  // conferir a mensagem
            @Override
            public void onClick(View v) {
                final int idCliente = clienteLogado.getId();
                final Conta co = db.contaDao().findByClienteId(idCliente);
                final Conta contaFinal = co;


                respostaUsuario = binding.inputResposta.getText().toString();
                if (respostaUsuario.isEmpty()) {
                    Toast.makeText(requireContext(), "Digite alguma coisa", Toast.LENGTH_SHORT).show();
                    return;
                }

                // pra verificar se ele acertou ou n
                if (respostaUsuario.equalsIgnoreCase(textoEscolhido)) {
                    binding.inputResposta.setBackgroundColor(Color.parseColor("#98FB98"));
                    binding.inputResposta.setTextColor(Color.parseColor("#FFFFFF"));
                    Toast.makeText(requireContext(), "Resposta Correta :)", Toast.LENGTH_SHORT).show();

                    final int totalAcertos = contaFinal.getTotal_acertos();
                    final int totalPerguntas = contaFinal.getTotal_perguntas() + 1;

                    co.setTotal_acertos(totalAcertos);
                    co.setTotal_perguntas(totalPerguntas);
                    db.contaDao().update(co);
                } else {
                    binding.inputResposta.setBackgroundColor(Color.parseColor("#FF5733"));
                    binding.inputResposta.setTextColor(Color.parseColor("#FFFFFF"));
                    Toast.makeText(requireContext(), "Resposta Errada :(", Toast.LENGTH_SHORT).show();

                    final int totalPerguntas = contaFinal.getTotal_perguntas() + 1;
                    co.setTotal_perguntas(totalPerguntas);
                    db.contaDao().update(co);
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        NavController navController = Navigation.findNavController(requireView());
                        Bundle bundleEnviar = new Bundle();
                        bundleEnviar.putSerializable("cliente_logado2", clienteLogado);

                        Log.d("TOTAL", "VALOR 2 = " + contaFinal.getTotal_perguntas());
                        Log.d("ExercicioSom", "Valor enviado: " + clienteLogado.getNome());
                        Log.d("ExercicioSom", "Valor enviado: " + clienteLogado.getEmail());
                        Log.d("ExercicioSom", "Valor enviado: " + clienteLogado.getId());

                        navController.navigate(R.id.action_exercicioSom_to_telaInicial, bundleEnviar);
                    }
                }, 3000);

            }
        });

        return binding.getRoot();
    }

    public String escolherTexto() {
        List<MultiplaEscolha> listaTextos = db.multiplaEscolhaDao().getAll();
        String texto = listaTextos.get(6).getPergunta();
        return texto;
    }


}
package com.example.duolingo.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.duolingo.R;
import com.example.duolingo.bd.MyDatabase;
import com.example.duolingo.databinding.ActivityMainBinding;
import com.example.duolingo.databinding.FragmentExercicioMultiplaEscolhaBinding;
import com.example.duolingo.databinding.FragmentLoginBinding;
import com.example.duolingo.entidades.Cliente;
import com.example.duolingo.entidades.Conta;
import com.example.duolingo.entidades.MultiplaEscolha;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ExercicioMultiplaEscolha extends Fragment {

    private MyDatabase db;
    private FragmentExercicioMultiplaEscolhaBinding binding;
    private Cliente clienteLogado;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getActivity().getApplicationContext(), MyDatabase.class, "Duolingo").allowMainThreadQueries().build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExercicioMultiplaEscolhaBinding.inflate(getLayoutInflater());

        // Recebendo o Cliente de Tela Inicial
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("cliente_logado"))
            clienteLogado = (Cliente) bundle.getSerializable("cliente_logado");


        // escolhendo a questao e adicionando o valor nas alternativas
        List <MultiplaEscolha> questoes = db.multiplaEscolhaDao().getAll();
        MultiplaEscolha questao = questoes.get(0);
        binding.textoPergunta.setText(questao.getPergunta());
        binding.alternativa1.setText(questao.getAlternativa1());
        binding.alternativa2.setText(questao.getAlternativa2());
        binding.alternativa3.setText(questao.getAlternativa3());
        binding.alternativa4.setText(questao.getAlternativa4());

        // variaveis AtomicReference
        final AtomicReference<String> respostaUsuario = new AtomicReference<>("");
        final AtomicReference<Integer> botaoEscolhido = new AtomicReference<>();

        binding.alternativa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostaUsuario.set(binding.alternativa1.getText().toString());
                botaoEscolhido.set(R.id.alternativa_1);
                binding.alternativa1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                binding.alternativa1.setTextColor(Color.parseColor("#800080")); //0x009900FF
                binding.alternativa2.setBackgroundColor(Color.parseColor("#6750a4"));
                binding.alternativa2.setTextColor(Color.parseColor("#FFFFFF")); //0x009900FF
                binding.alternativa3.setBackgroundColor(Color.parseColor("#6750a4"));
                binding.alternativa3.setTextColor(Color.parseColor("#FFFFFF")); //0x009900FF
                binding.alternativa4.setBackgroundColor(Color.parseColor("#6750a4"));
                binding.alternativa4.setTextColor(Color.parseColor("#FFFFFF")); //0x009900FF
            }
        });

        binding.alternativa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostaUsuario.set(binding.alternativa2.getText().toString());
                botaoEscolhido.set(R.id.alternativa_2);
                binding.alternativa2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                binding.alternativa2.setTextColor(Color.parseColor("#800080")); //0x009900FF
                binding.alternativa1.setBackgroundColor(Color.parseColor("#6750a4"));
                binding.alternativa1.setTextColor(Color.parseColor("#FFFFFF")); //0x009900FF
                binding.alternativa3.setBackgroundColor(Color.parseColor("#6750a4"));
                binding.alternativa3.setTextColor(Color.parseColor("#FFFFFF")); //0x009900FF
                binding.alternativa4.setBackgroundColor(Color.parseColor("#6750a4"));
                binding.alternativa4.setTextColor(Color.parseColor("#FFFFFF")); //0x009900FF
            }
        });

        binding.alternativa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostaUsuario.set(binding.alternativa3.getText().toString());
                botaoEscolhido.set(R.id.alternativa_3);
                binding.alternativa3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                binding.alternativa3.setTextColor(Color.parseColor("#800080")); //0x009900FF
                binding.alternativa1.setBackgroundColor(Color.parseColor("#6750a4"));
                binding.alternativa1.setTextColor(Color.parseColor("#FFFFFF")); //0x009900FF
                binding.alternativa2.setBackgroundColor(Color.parseColor("#6750a4"));
                binding.alternativa2.setTextColor(Color.parseColor("#FFFFFF")); //0x009900FF
                binding.alternativa4.setBackgroundColor(Color.parseColor("#6750a4"));
                binding.alternativa4.setTextColor(Color.parseColor("#FFFFFF")); //0x009900FF
            }
        });

        binding.alternativa4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                respostaUsuario.set(binding.alternativa4.getText().toString());
                botaoEscolhido.set(R.id.alternativa_4);
                binding.alternativa4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                binding.alternativa4.setTextColor(Color.parseColor("#800080")); //0x009900FF
                binding.alternativa1.setBackgroundColor(Color.parseColor("#6750a4"));
                binding.alternativa1.setTextColor(Color.parseColor("#FFFFFF")); //0x009900FF
                binding.alternativa2.setBackgroundColor(Color.parseColor("#6750a4"));
                binding.alternativa2.setTextColor(Color.parseColor("#FFFFFF")); //0x009900FF
                binding.alternativa3.setBackgroundColor(Color.parseColor("#6750a4"));
                binding.alternativa3.setTextColor(Color.parseColor("#FFFFFF")); //0x009900FF
            }
        });

        // verificando a resposta
        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int idCliente = clienteLogado.getId();
                final Conta co = db.contaDao().findByClienteId(idCliente);

                // Criando uma variável final para armazenar a referência à instância de Conta
                final Conta contaFinal = co;

                if (respostaUsuario.get().equals(questao.getResposta())) {  // resposta certa
                    Button botao = binding.getRoot().findViewById(botaoEscolhido.get());
                    botao.setBackgroundColor(Color.parseColor("#98FB98"));
                    botao.setTextColor(Color.parseColor("#FFFFFF"));
                    Toast.makeText(requireContext(), "Resposta Correta :)", Toast.LENGTH_SHORT).show();

                    final int totalAcertos = contaFinal.getTotal_acertos() + 1;
                    final int totalPerguntas = contaFinal.getTotal_perguntas() + 1;

                    contaFinal.setTotal_acertos(totalAcertos);
                    contaFinal.setTotal_perguntas(totalPerguntas);

                    db.contaDao().update(contaFinal);
                    Log.d("TOTAL", "VALOR = " + contaFinal.getTotal_perguntas());

                } else {
                    Button botao = binding.getRoot().findViewById(botaoEscolhido.get());
                    botao.setBackgroundColor(Color.parseColor("#FF5733"));
                    botao.setTextColor(Color.parseColor("#FFFFFF"));
                    Toast.makeText(requireContext(), "Resposta Errada", Toast.LENGTH_SHORT).show();

                    final int totalPerguntas = contaFinal.getTotal_perguntas() + 1;
                    contaFinal.setTotal_perguntas(totalPerguntas);

                    db.contaDao().update(contaFinal);
                    Log.d("TOTAL", "VALOR = " + contaFinal.getTotal_perguntas());
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        NavController navController = Navigation.findNavController(requireView());
                        Bundle bundleEnviar = new Bundle();
                        bundleEnviar.putSerializable("cliente_logado", clienteLogado);

                        int id = clienteLogado.getId();
                        Conta teste = db.contaDao().findByClienteId(id);
                        Log.d("TOTAL", "VALOR 2 = " + contaFinal.getTotal_perguntas());

                        navController.navigate(R.id.action_exercicioMultiplaEscolha_to_exercicioSom, bundleEnviar);
                    }
                }, 3000);
            }
        });

        return binding.getRoot();
    }

}
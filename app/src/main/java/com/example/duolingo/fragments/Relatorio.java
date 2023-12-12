package com.example.duolingo.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duolingo.R;
import com.example.duolingo.bd.MyDatabase;
import com.example.duolingo.databinding.FragmentRelatorioBinding;
import com.example.duolingo.entidades.Cliente;
import com.example.duolingo.entidades.Conta;


public class Relatorio extends Fragment {

    private Cliente clienteLogado;
    private Conta co;
    private MyDatabase db;

    private FragmentRelatorioBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRelatorioBinding.inflate(getLayoutInflater());
        db = Room.databaseBuilder(getActivity().getApplicationContext(), MyDatabase.class, "Duolingo").allowMainThreadQueries().build();

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("cliente_logado"))
            clienteLogado = (Cliente) bundle.getSerializable("cliente_logado");

        int idCliente = clienteLogado.getId();
        Conta contaCliente = db.contaDao().findByClienteId(idCliente);
        int totalAcertos = contaCliente.getTotal_acertos();
        int totalPerguntas = contaCliente.getTotal_perguntas();

        // pra usar no grafico que eu vou fazer depois
        double percentualAcertos = (double) totalAcertos / totalPerguntas * 100;
        double percentualErros = 100 - percentualAcertos;

        String texto = "Total de Perguntas respondidas: " + totalPerguntas +
                        "\nTotal de Acertos: " + totalAcertos +
                        "\nTotal de Erros: " + (totalPerguntas - totalAcertos);
        binding.textRelatorio.setText(texto);

        return binding.getRoot();
    }
}
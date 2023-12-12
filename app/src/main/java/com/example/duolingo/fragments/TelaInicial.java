package com.example.duolingo.fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duolingo.R;
import com.example.duolingo.bd.MyDatabase;
import com.example.duolingo.databinding.FragmentTelaInicialBinding;
import com.example.duolingo.entidades.Cliente;

public class TelaInicial extends Fragment {

    private FragmentTelaInicialBinding binding;
    private MyDatabase db;
    private Cliente clienteLogado;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTelaInicialBinding.inflate(getLayoutInflater());
        db = Room.databaseBuilder(getActivity().getApplicationContext(), MyDatabase.class, "Duolingo").allowMainThreadQueries().build();


        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey("cliente_logado2")) {
                clienteLogado = (Cliente) bundle.getSerializable("cliente_logado2");
                Log.d("Bundle2", "Valor enviado: " + clienteLogado.getNome());
            } else if (bundle.containsKey("cliente_logado")){
                clienteLogado = (Cliente) bundle.getSerializable("cliente_logado");
                Log.d("Bundle1", "Valor enviado: " + clienteLogado.getNome());
            }
            binding.textWelcome.setText("Olá " + clienteLogado.getNome());
        }

        // enviando o bundle para o Login
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                Bundle bundleEnviar = new Bundle();
                bundleEnviar.putSerializable("cliente_logado", clienteLogado);
                navController.navigate(R.id.action_telaInicial_to_exercicioMultiplaEscolha, bundleEnviar);
            }
        });

        // enviando para relatorio
        binding.btnRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireView());
                Bundle bundleEnviar = new Bundle();
                bundleEnviar.putSerializable("cliente_logado", clienteLogado);
                navController.navigate(R.id.action_telaInicial_to_relatorio, bundleEnviar);
            }
        });

        return binding.getRoot();
    }


}
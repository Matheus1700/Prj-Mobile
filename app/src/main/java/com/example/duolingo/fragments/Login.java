package com.example.duolingo.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.WorkerThread;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.duolingo.R;
import com.example.duolingo.bd.MyDatabase;
import com.example.duolingo.databinding.FragmentLoginBinding;
import com.example.duolingo.entidades.Cliente;
import com.example.duolingo.entidades.Conta;

import java.util.List;

public class Login extends Fragment {

    private FragmentLoginBinding binding;
    private MyDatabase db;
    private boolean existe = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getActivity().getApplicationContext(), MyDatabase.class, "Duolingo").allowMainThreadQueries().build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater());

        // mostrando o Toast de que o Cliente foi cadastrado
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("mensagemCadastro")) {
            String mensagemCadastro = bundle.getString("mensagemCadastro");
            Toast.makeText(getActivity(), mensagemCadastro, Toast.LENGTH_SHORT).show();
        }

        // pra entrar no sistema
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.loginEmail.getText().toString();
                String senha = binding.loginSenha.getText().toString();

                mostrarBanco();

                Cliente cl = db.clienteDao().findByEmail(email);

                if (cl != null && cl.getSenha().equals(senha)) {
                    NavController navController = Navigation.findNavController(requireView());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("cliente_logado", cl);
                    navController.navigate(R.id.action_login_to_telaInicial, bundle);
                } else {
                    Toast.makeText(requireContext(), "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        binding.btnRegister.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_login_to_cadastro));
        return binding.getRoot();
    }

    public void mostrarBanco() {
        List<Conta> contas = db.contaDao().getAll();
        List<Cliente> clientes = db.clienteDao().getAll();

        for (Conta c : contas) {
            Log.d("Cliente", "ID do Cliente: " + c.getCliente_id());
            Log.d("Cliente", "Total de Perguntas: " + c.getTotal_perguntas());
            Cliente ce = db.clienteDao().findById(c.getCliente_id());
            Log.d("Cliente", "Nome: " + ce.getNome());
        }

        for (Cliente c : clientes) {
            Log.d("Clientes", "Nome: " + c.getNome());
            Log.d("Clientes", "Email: " + c.getEmail());
        }
    }

}
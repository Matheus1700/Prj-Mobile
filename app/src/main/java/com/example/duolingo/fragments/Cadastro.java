package com.example.duolingo.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

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
import com.example.duolingo.databinding.FragmentCadastroBinding;
import com.example.duolingo.databinding.FragmentLoginBinding;
import com.example.duolingo.entidades.Cliente;
import com.example.duolingo.entidades.Conta;
import com.example.duolingo.entidades.MultiplaEscolha;

import java.util.List;

public class Cadastro extends Fragment {

    FragmentCadastroBinding binding;
    private MyDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCadastroBinding.inflate(getLayoutInflater());
        db = Room.databaseBuilder(getActivity().getApplicationContext(), MyDatabase.class, "Duolingo").allowMainThreadQueries().build();

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = binding.registerName.getText().toString();
                String email = binding.registerEmail.getText().toString();
                String senha = binding.registerPassword.getText().toString();
                String senha2 = binding.registerPassword2.getText().toString();
                String mensagem = "";

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    mensagem = "Preencha todos os campos para prosseguir";
                } else if (emailExiste(email)) {
                    mensagem = "Esse email já foi cadastrado";
                } else if (isNomeValido(nome) == false) {
                    mensagem = "Seu nome não pode conter números e deve ter 5 ou mais caracteres";
                } else if (senha.equals(senha2)) {
                    Cliente cl = new Cliente(0, nome, email, senha);

                    new InsertClienteAsyncTask(db).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, cl);
                    mensagem = "Cliente cadastrado com sucesso";

                    listarBancoDados();

                    NavController navController = Navigation.findNavController(v);
                    Bundle bundle = new Bundle();
                    bundle.putString("mensagemCadastro", mensagem);
                    navController.navigate(R.id.action_cadastro_to_login, bundle);

                } else { mensagem = "Verifique se os dados foram preenchidos corretamente"; }

                String finalMensagem = mensagem;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), finalMensagem, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return binding.getRoot();
    }


    public void listarBancoDados() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Cliente> clientes = db.clienteDao().getAll();
                for (Cliente cl : clientes) {
                    Log.i("Dados", "Nome: " + cl.getNome()
                            +"ID: " + cl.getId() + "Senha: " + cl.getSenha()
                            + "Email: " + cl.getEmail());
                }
            }
        }).start();
    }

    private static class InsertClienteAsyncTask extends AsyncTask<Cliente, Void, Void> {
        private MyDatabase db;
        InsertClienteAsyncTask(MyDatabase database) {
            this.db = database;
        }

        @Override
        protected Void doInBackground(Cliente... clientes) {
            db.clienteDao().insert(clientes[0]);

            // buscando o id que ele tem dentro da tabela de fato
            Cliente clienteInserido = db.clienteDao().findByEmail(clientes[0].getEmail());
            int idClienteInserido = clienteInserido.getId();

            Conta co = new Conta(0, idClienteInserido);
            db.contaDao().insert(co);
            return null;
        }
    }

    public boolean emailExiste(String emailInformado) {
        Cliente ce = db.clienteDao().findByEmail(emailInformado);
        if (ce == null) {
            return false;
        }
        return true;
    }

    public boolean isNomeValido(String nome) {
        if (nome.length() >= 5 && contemNumero(nome) == false)
            return true;
        return false;
    }

    public boolean contemNumero (String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c))
                return true;
        }
        return false;
    }

}
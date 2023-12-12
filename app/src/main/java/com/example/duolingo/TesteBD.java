package com.example.duolingo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duolingo.bd.MyDatabase;
import com.example.duolingo.entidades.MultiplaEscolha;

import java.util.ArrayList;
import java.util.List;

public class TesteBD extends Fragment {

    private MyDatabase db;
    private EditText input;
    private Button button;

    public TesteBD() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teste_b_d, container, false);

        input = view.findViewById(R.id.input_cadastro);
        button = view.findViewById(R.id.btn_confirmar);

        db = Room.databaseBuilder(getActivity().getApplicationContext(), MyDatabase.class, "Duolingo").build();


        List <String> perguntas = new ArrayList<>();
        List <String> alt1s = new ArrayList<>();
        List <String> alt2s = new ArrayList<>();
        List <String> alt3s = new ArrayList<>();
        List <String> alt4s = new ArrayList<>();
        List <String> respostas = new ArrayList<>();

        perguntas.add("O sol está brilhando no céu.");
        perguntas.add("As flores são coloridas.");
        perguntas.add("O pássaro está voando no céu.");
        perguntas.add("A água é transparente.");
        perguntas.add("Os peixes vivem na água.");
        perguntas.add("O gato mia quando está feliz.");
        perguntas.add("A chuva molha a terra.");
        perguntas.add("A árvore tem muitas folhas verdes.");
        perguntas.add("O carro se move na estrada.");
        perguntas.add("O relógio marca a hora correta.");
        alt1s.add("O céu está escuro.");
        alt1s.add("As folhas são verdes.");
        alt1s.add("O avião está no céu.");
        alt1s.add("O gelo é transparente.");
        alt1s.add("As aves vivem na água.");
        alt1s.add("O cachorro late quando está feliz.");
        alt1s.add("O sol seca a terra.");
        alt1s.add("A pedra tem muitas folhas verdes.");
        alt1s.add("A casa se move na estrada.");
        alt1s.add("A televisão mostra a hora correta.");
        alt2s.add("As estrelas estão brilhando.");
        alt2s.add("As flores exibem diversas cores.");
        alt2s.add("A borboleta está voando no jardim.");
        alt2s.add("O vidro é sólido.");
        alt2s.add("As plantas crescem na água.");
        alt2s.add("O gato vocaliza emitindo um som específico quando está contente.");
        alt2s.add("O vento molha a terra.");
        alt2s.add("O vento carrega muitas folhas verdes.");
        alt2s.add("O barco se move na estrada.");
        alt2s.add("O telefone toca na hora certa.");
        alt3s.add("As nuvens estão pesadas.");
        alt3s.add("As pedras são cinzentas.");
        alt3s.add("As folhas estão caindo do céu.");
        alt3s.add("É possível ver através da água, pois ela é transparente.");
        alt3s.add("Os peixes residem naturalmente no meio aquático.");
        alt3s.add("O pássaro canta quando está feliz.");
        alt3s.add("A chuva, ao cair, umedece a superfície da terra.");
        alt3s.add("A árvore, uma planta, possui numerosas folhas verdes.");
        alt3s.add("O automóvel desloca-se sobre a superfície da estrada.");
        alt3s.add("A afirmação de que o relógio está marcando a hora correta é verdadeira.");
        alt4s.add("O dia está claro, o sol está brilhando.");
        alt4s.add("As árvores são altas.");
        alt4s.add("O pássaro está alçando voo no céu.");
        alt4s.add("As pedras são opacas.");
        alt4s.add("Os animais vivem na água.");
        alt4s.add("O peixe faz barulho quando está feliz.");
        alt4s.add("A neve aquece a terra.");
        alt4s.add("A montanha tem muitas folhas verdes.");
        alt4s.add("O avião se move na estrada.");
        alt4s.add("O livro conta o tempo corretamente.");
        respostas.add("O dia está claro, o sol está brilhando.");
        respostas.add("As flores exibem diversas cores.");
        respostas.add("O pássaro está alçando voo no céu.");
        respostas.add("É possível ver através da água, pois ela é transparente.");
        respostas.add("Os peixes residem naturalmente no meio aquático.");
        respostas.add("O gato vocaliza emitindo um som específico quando está contente.");
        respostas.add("A chuva, ao cair, umedece a superfície da terra.");
        respostas.add("A árvore, uma planta, possui numerosas folhas verdes.");
        respostas.add("O automóvel desloca-se sobre a superfície da estrada.");
        respostas.add("A afirmação de que o relógio está marcando a hora correta é verdadeira.");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String p1, alt1, alt2, alt3, alt4, r1;
                        MultiplaEscolha m1;
                        for (int i = 0; i < 10; i++) {
                            p1 = perguntas.get(i);
                            alt1 = alt1s.get(i);
                            alt2 = alt2s.get(i);
                            alt3 = alt3s.get(i);
                            alt4 = alt4s.get(i);
                            r1 = respostas.get(i);
                            m1 = new MultiplaEscolha(0, p1, alt1, alt2, alt3, alt4, r1);
                            db.multiplaEscolhaDao().insert(m1);

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getActivity(), "dados salvos", Toast.LENGTH_SHORT).show();
                                    consultaMultiplaEscolha();
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        return view;
    }

    public void consultaMultiplaEscolha() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MultiplaEscolha> perguntas = db.multiplaEscolhaDao().getAll();
                for (MultiplaEscolha me : perguntas) {
                    Log.i("Dados", "Nome: " + me.getPergunta()
                            +"ID: " + me.getId() + "Resposta: " + me.getResposta());
                }
            }
        }).start();
    }

}
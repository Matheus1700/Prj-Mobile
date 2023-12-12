package com.example.duolingo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.example.duolingo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private ConstraintLayout layoutRodape;
    private List <String> listaFragments = new ArrayList<>();
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //redimensionarTela();
        gerarListaFragments();
    }

    public void gerarListaFragments() {
        listaFragments.add("ExercicioArrastar");
        listaFragments.add("ExercicioCompletar");
        listaFragments.add("ExercicioSom");
    }

    /*
    public void redimensionarTela() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        navController = navHostFragment.getNavController();
        layoutRodape = findViewById(R.id.layout_rodape);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.login || destination.getId() == R.id.cadastro || destination.getId() == R.id.telaInicial){
                int rodapeHeight = layoutRodape.getHeight();

                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) findViewById(R.id.fragmentContainerView).getLayoutParams();
                params.height = params.height + rodapeHeight;
                findViewById(R.id.fragmentContainerView).setLayoutParams(params);

                layoutRodape.setVisibility(View.GONE);
            } else {
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) findViewById(R.id.fragmentContainerView).getLayoutParams();
                params.height = ConstraintLayout.LayoutParams.MATCH_PARENT;
                findViewById(R.id.fragmentContainerView).setLayoutParams(params);

                layoutRodape.setVisibility(View.VISIBLE);
            }
        });
    }
    */

    /*
    public List<String> gerarFilaExercicios() {
        List<String> filaExercicios = new ArrayList<>();
        Log.d("opa", "chegou até aqui");

        for (int i = 0; i < 4; i++) {
            int random = new Random().nextInt(3);
            filaExercicios.add(listaFragments.get(random));
        }

        return filaExercicios;
    }


    public void proximoFragment() {

    }

    */
}
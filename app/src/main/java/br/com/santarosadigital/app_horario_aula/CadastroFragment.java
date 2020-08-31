package br.com.santarosadigital.app_horario_aula;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.orm.SugarContext;

import java.util.ArrayList;

public class CadastroFragment extends Fragment {

    public  static CadastroFragment newInstace(){
        CadastroFragment fragment = new CadastroFragment();
        return fragment;
    }

    public CadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmento = inflater.inflate(R.layout.fragment_cadastro, container, false);

        final Spinner spn_dia_semana = (Spinner)fragmento.findViewById(R.id.spn_dia_semana);
        final EditText edt_bloco = (EditText)fragmento.findViewById(R.id.edt_bloco);
        final EditText edt_disciplina = (EditText)fragmento.findViewById(R.id.edt_disciplina);
        final EditText edt_laratorio = (EditText)fragmento.findViewById(R.id.edt_laboratorio);
        Button btn_cadstrar = (Button)fragmento.findViewById(R.id.btn_cadastrar);

        String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(fragmento.getContext(), android.R.layout.simple_list_item_1, dias);
        spn_dia_semana.setAdapter(adapter);

        btn_cadstrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dia_semana = spn_dia_semana.getSelectedItem().toString();
                String bloco = edt_bloco.getText().toString();
                String disciplina = edt_disciplina.getText().toString();
                String laboratorio = edt_laratorio.getText().toString();

                SugarContext.init(fragmento.getContext());

                new Horario(dia_semana, bloco, disciplina, laboratorio).save();

                Toast.makeText(fragmento.getContext(), "CADASTRADO!!!", Toast.LENGTH_SHORT).show();

                edt_bloco.setText("");
                edt_disciplina.setText("");
                edt_laratorio.setText("");

            }
        });

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_cadastro, container, false);
        return fragmento;
    }
}
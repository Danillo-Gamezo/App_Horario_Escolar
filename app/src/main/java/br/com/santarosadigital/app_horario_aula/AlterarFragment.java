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

import br.com.santarosadigital.app_horario_aula.ui.horario.HorarioFragment;

public class AlterarFragment extends Fragment {

    public AlterarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View fragmento = inflater.inflate(R.layout.fragment_alterar, container, false);

        final Spinner spn_dia_semana = (Spinner)fragmento.findViewById(R.id.spn_dia_semana);
        final EditText edt_bloco = (EditText)fragmento.findViewById(R.id.edt_bloco);
        final EditText edt_disciplina = (EditText)fragmento.findViewById(R.id.edt_disciplina);
        final EditText edt_laratorio = (EditText)fragmento.findViewById(R.id.edt_laboratorio);
        Button btn_alterar = (Button)fragmento.findViewById(R.id.btn_alterar);

        String semana = HorarioFragment.horario_escolhido.getDia();
        int indice = 10;

        switch (semana) {
            case "Segunda":
                indice = 0;
                break;
            case "Terça":
                indice = 1;
                break;
            case "Quarta":
                indice = 2;
                break;
            case "Quinta":
                indice = 3;
                break;
            case "Sexta":
                indice = 4;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + semana);
        }


        spn_dia_semana.setSelection(indice);


        String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(fragmento.getContext(), android.R.layout.simple_list_item_1, dias);
        spn_dia_semana.setAdapter(adapter);

        edt_bloco.setText(HorarioFragment.horario_escolhido.getBloco());
        edt_disciplina.setText(HorarioFragment.horario_escolhido.getDisciplina());
        edt_laratorio.setText(HorarioFragment.horario_escolhido.getLaboratorio());

        btn_alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HorarioFragment.horario_escolhido.setDia(spn_dia_semana.getSelectedItem().toString());
                HorarioFragment.horario_escolhido.setBloco(edt_bloco.getText().toString());
                HorarioFragment.horario_escolhido.setDisciplina(edt_disciplina.getText().toString());
                HorarioFragment.horario_escolhido.setLaboratorio(edt_laratorio.getText().toString());

                HorarioFragment.horario_escolhido.save();

                Toast.makeText(fragmento.getContext(), "Aterado!", Toast.LENGTH_SHORT).show();
            }
        });


        // Inflate the layout for this fragment
        return fragmento;
    }
}
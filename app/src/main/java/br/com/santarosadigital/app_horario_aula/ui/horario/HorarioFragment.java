package br.com.santarosadigital.app_horario_aula.ui.horario;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.orm.SugarContext;

import java.util.List;

import br.com.santarosadigital.app_horario_aula.AlterarFragment;
import br.com.santarosadigital.app_horario_aula.Horario;
import br.com.santarosadigital.app_horario_aula.R;

public class HorarioFragment extends Fragment {

    public HorarioFragment() {
        // Required empty public constructor
    }

    public  static  Horario horario_escolhido;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View fragmento = inflater.inflate(R.layout.fragment_horario, container, false);

        final ListView lst_horario = (ListView)fragmento.findViewById(R.id.lst_horario);

        listar(fragmento, lst_horario);

        lst_horario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                horario_escolhido = (Horario) lst_horario.getItemAtPosition(position);

                AlertDialog.Builder dialogo = new AlertDialog.Builder(fragmento.getContext());
                dialogo.setTitle("ATENÇÃO!");
                dialogo.setMessage("O que você deseja fazer?");

                dialogo.setPositiveButton("Alterar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                assert getFragmentManager() != null;

                                FragmentTransaction frame = getFragmentManager().beginTransaction();
                                AlterarFragment tela = new AlterarFragment();
                                frame.replace(R.id.nav_host_fragment, tela);
                                frame.commit();

                            }
                        });
                dialogo.setNegativeButton("Excluir",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                horario_escolhido.delete();
                                listar(fragmento,lst_horario);
                            }
                        });
                dialogo.setNeutralButton("Cancelar", null);

                dialogo.show();
            }
        });

        // Inflate the layout for this fragment
        return fragmento;

    }

    private  void listar(View v, ListView lista){

        SugarContext.init(v.getContext());

        List<Horario> aulas = Horario.find(Horario.class, "dia = ?", Horario.filtro_dia_da_semana);

        ArrayAdapter<Horario> adaptador = new ArrayAdapter<>( v.getContext(),
                android.R.layout.simple_list_item_1,
                aulas);

        lista.setAdapter(adaptador);
    }

}
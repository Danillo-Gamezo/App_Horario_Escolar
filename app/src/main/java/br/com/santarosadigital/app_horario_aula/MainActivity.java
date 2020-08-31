package br.com.santarosadigital.app_horario_aula;

import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Banco.Criar(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                // TODO: sobrescrevendo as telas
                FragmentTransaction frame = getSupportFragmentManager().beginTransaction();
                CadastroFragment tela = new CadastroFragment();
                frame.replace(R.id.nav_host_fragment, tela);
                frame.commit();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
            R.id.day_of_week_monday, R.id.day_of_week_tuesday, R.id.day_of_week_wednesday, R.id.day_of_week_thursday, R.id.day_of_week_friday)
            .setDrawerLayout(drawer)
            .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener( new NavController.OnDestinationChangedListener() {
               @Override
               public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                   switch (destination.getId()) {
                       case R.id.day_of_week_monday:
                           replaceFragment(getResources().getString(R.string.segunda));
                           break;
                       case R.id.day_of_week_tuesday:
                           replaceFragment(getResources().getString(R.string.terca));
                           break;
                       case R.id.day_of_week_wednesday:
                           replaceFragment(getResources().getString(R.string.quarta));
                           break;
                       case R.id.day_of_week_thursday:
                           replaceFragment(getResources().getString(R.string.quinta));
                           break;
                       case R.id.day_of_week_friday:
                           replaceFragment(getResources().getString(R.string.sexta));
                           break;
                       default:
                           break;
                   }
               }
        });
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }



    public void replaceFragment(String dia){
        Horario.filtro_dia_da_semana = dia;
    }
}
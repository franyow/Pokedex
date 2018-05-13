package upc.pe.edu.pokedex;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import upc.pe.edu.pokedex.Fragments.HomeFragment;
import upc.pe.edu.pokedex.Fragments.PokedexFragment;
import upc.pe.edu.pokedex.Fragments.PokemonsFragment;
import upc.pe.edu.pokedex.Model.Pokemon;
import upc.pe.edu.pokedex.Model.PokemonRespuesta;
import upc.pe.edu.pokedex.Network.NetworkAPI;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, PokemonsFragment.OnFragmentInteractionListener, PokedexFragment.OnFragmentInteractionListener{





    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new HomeFragment()).commit();
                    return true;
                case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new PokedexFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new PokemonsFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.main_container,new HomeFragment()).commit();

    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }




}

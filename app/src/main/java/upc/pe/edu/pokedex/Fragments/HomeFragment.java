package upc.pe.edu.pokedex.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import upc.pe.edu.pokedex.Adapters.RvAdapter;
import upc.pe.edu.pokedex.Model.Pokemon;
import upc.pe.edu.pokedex.Model.PokemonPokedex;
import upc.pe.edu.pokedex.Model.PokemonRespuesta;
import upc.pe.edu.pokedex.Network.NetworkAPI;
import upc.pe.edu.pokedex.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements Callback<Pokemon> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Retrofit retrofit;
    TextView nombrePokemon,tipoPokemon;
    ImageView pokefoto;
    Button buttonBuscar,buttonGuardar;
    String pokemonString;
    Pokemon pokemon;
    PokemonPokedex pokemonEnPokedex;

    Random rand = new Random();
    int pokeNumero = rand.nextInt(150);


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        buttonBuscar = view.findViewById(R.id.buttonBuscar);
        nombrePokemon = view.findViewById(R.id.pokemontext);
        pokefoto = view.findViewById(R.id.imageView);
        buttonGuardar = view.findViewById(R.id.buttonSave);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        LoadPokemons();

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pokeNumero = rand.nextInt(150);
                LoadPokemons();



            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if(pokemon.getName()!=null) {
                    guardarPokemon(pokemonEnPokedex);
                    Toast.makeText(getContext(), "Pokemon " + pokemon.getName() + "guardado en Pokedex!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Espere a que aparezca un pokemón", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }

    void LoadPokemons() {



        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        NetworkAPI networkAPI = retrofit.create(NetworkAPI.class);
        Call<Pokemon> call = networkAPI.getApokemon(pokeNumero);
        call.enqueue(this);


    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void guardarPokemon(PokemonPokedex pokemonPokedex){
        pokemonPokedex=new PokemonPokedex(pokemon.getUrl(),pokemon.getName());
        pokemonPokedex.save();

    }

    @Override
    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
        if(response.isSuccessful()) {
            pokemon = response.body();


            nombrePokemon.setText(pokemon.getName());
            pokemonString = pokemon.getName();
            Glide.with(getContext())
                    .load("http://pokeapi.co/media/sprites/pokemon/" + pokeNumero + ".png")
                    .into(pokefoto);
            Toast.makeText(getContext(), "numero "+pokeNumero+" " +pokemon.getName(), Toast.LENGTH_SHORT).show();






        }else {
            Log.e("Pokemon", " onResponse: " + response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Pokemon> call, Throwable t) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

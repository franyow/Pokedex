package upc.pe.edu.pokedex.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import upc.pe.edu.pokedex.Adapters.RvAdapter;
import upc.pe.edu.pokedex.Model.Pokemon;
import upc.pe.edu.pokedex.Model.PokemonRespuesta;
import upc.pe.edu.pokedex.Network.NetworkAPI;
import upc.pe.edu.pokedex.R;


public class PokemonsFragment extends Fragment implements Callback<PokemonRespuesta>{

    private static final String BASE_URL = "http://pokeapi.co/api/v2/";
    private Retrofit retrofit;
    int limit=150, offset=150;


    private RecyclerView recyclerPokemons;
    private RvAdapter adapter;
    private ArrayList<Pokemon> pokemonList= new ArrayList<>();


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PokemonsFragment() {
        // Required empty public constructor
    }



    public static PokemonsFragment newInstance(String param1, String param2) {
        PokemonsFragment fragment = new PokemonsFragment();
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
        View view = inflater.inflate(R.layout.fragment_pokemons, container, false);
        recyclerPokemons = view.findViewById(R.id.recyclerPokemon);
        recyclerPokemons.setLayoutManager(new GridLayoutManager(getContext(),3));
        //RvAdapter adapter = new RvAdapter(pokemonList);
        //recyclerPokemons.setAdapter(adapter);
        recyclerPokemons.setHasFixedSize(true);
        LoadPokemons();


        return view;
    }


     void LoadPokemons() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        NetworkAPI networkAPI = retrofit.create(NetworkAPI.class);
        Call<PokemonRespuesta> call = networkAPI.getAllPokemon(limit,offset);
        call.enqueue(this);


    }

    @Override
    public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {

        if(response.isSuccessful()) {
            PokemonRespuesta pokemonRespuesta = response.body();
            pokemonList = pokemonRespuesta.getResults();
            recyclerPokemons.setAdapter(new RvAdapter(getContext(),pokemonList));




        }else {
            Log.e("Pokemon", " onResponse: " + response.errorBody());
        }


    }

    @Override
    public void onFailure(Call<PokemonRespuesta> call, Throwable t) {

    }





    // TODO: Rename method, update argument and hook method into UI event
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}

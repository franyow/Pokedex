package upc.pe.edu.pokedex.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import upc.pe.edu.pokedex.Adapters.PokedexAdapter;
import upc.pe.edu.pokedex.Model.PokemonPokedex;
import upc.pe.edu.pokedex.R;

public class PokedexFragment extends Fragment {
    RecyclerView recyclerViewPokedex;

    ArrayList<String> pokeNames = new ArrayList<>();
    ArrayList<PokemonPokedex> pokedexPokemonList = new ArrayList<>();



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PokedexFragment() {
        // Required empty public constructor
    }


    public static PokedexFragment newInstance(String param1, String param2) {
        PokedexFragment fragment = new PokedexFragment();
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
        View view = inflater.inflate(R.layout.fragment_pokedex, container, false);
        recyclerViewPokedex = view.findViewById(R.id.pokedexrv);
        recyclerViewPokedex.setLayoutManager(new LinearLayoutManager(getContext()));
        CargarPokemonsPokedex();
        PokedexAdapter adapter = new PokedexAdapter(pokedexPokemonList);
        recyclerViewPokedex.setAdapter(adapter);

        Log.e("TAG","tamaño Arraylist "+pokedexPokemonList.size());




        return view;
    }

    private void CargarPokemonsPokedex() {

        List<PokemonPokedex> pokemonPokedexes = PokemonPokedex.listAll(PokemonPokedex.class);
            for (PokemonPokedex pokedex : pokemonPokedexes) {
                pokedexPokemonList.add(pokedex);
            }

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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

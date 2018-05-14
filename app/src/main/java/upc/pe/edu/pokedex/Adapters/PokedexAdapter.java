package upc.pe.edu.pokedex.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import upc.pe.edu.pokedex.Model.PokemonPokedex;
import upc.pe.edu.pokedex.R;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.ViewHolderPokedex>{

    ArrayList<PokemonPokedex> pokemonPokedexes;
    private Context context;

    public PokedexAdapter(ArrayList<PokemonPokedex> pokemonPokedexes, Context context) {
        this.pokemonPokedexes = pokemonPokedexes;
        this.context = context;
    }

    @Override
    public ViewHolderPokedex onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokedex_item_list,null,false);

        return new ViewHolderPokedex(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderPokedex holder, int position) {
        PokemonPokedex p = pokemonPokedexes.get(position);
        holder.nombrePoke.setText(pokemonPokedexes.get(position).getName());
        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/pokemon/" + p.getNumber() + ".png")
                .into(holder.pokeimage);

    }

    @Override
    public int getItemCount() {
        return  pokemonPokedexes.size();
    }

    public class ViewHolderPokedex extends RecyclerView.ViewHolder {
        TextView nombrePoke;
        ImageView pokeimage;
        public ViewHolderPokedex(View itemView) {
            super(itemView);
            nombrePoke = itemView.findViewById(R.id.pokedexName);
            pokeimage = itemView.findViewById(R.id.rvPokeimg);
        }
    }
}

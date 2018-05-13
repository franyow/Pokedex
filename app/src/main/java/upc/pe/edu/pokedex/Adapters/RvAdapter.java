package upc.pe.edu.pokedex.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import upc.pe.edu.pokedex.Model.Pokemon;
import upc.pe.edu.pokedex.R;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    ArrayList<Pokemon> listPokemon;
    private Context context;

    public RvAdapter(Context context, ArrayList<Pokemon> listPokemon) {
        this.listPokemon = listPokemon;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item_list,null,false);

        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombrepokemon.setText(listPokemon.get(position).getName());

    }


    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        listaPokemon.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listPokemon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombrepokemon;

        public ViewHolder(View itemView) {
            super(itemView);

            nombrepokemon = itemView.findViewById(R.id.pokemonName);
        }
    }
}



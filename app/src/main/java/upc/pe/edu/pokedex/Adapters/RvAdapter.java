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

import upc.pe.edu.pokedex.Model.Pokemon;
import upc.pe.edu.pokedex.R;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    ArrayList<Pokemon> listPokemon;
    private Context context;

    public RvAdapter(ArrayList<Pokemon> listPokemon, Context context) {
        this.listPokemon = listPokemon;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item_list,null,false);

        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = listPokemon.get(position);
        holder.nombrepokemon.setText(listPokemon.get(position).getName());
        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/pokemon/" + p.getNumber() + ".png")
                .into(holder.imgpokemon);

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
        ImageView imgpokemon;

        public ViewHolder(View itemView) {
            super(itemView);

            nombrepokemon = itemView.findViewById(R.id.nombreTextView);
            imgpokemon = itemView.findViewById(R.id.fotoImageView);
        }
    }
}



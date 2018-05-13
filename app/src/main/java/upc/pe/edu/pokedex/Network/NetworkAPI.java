package upc.pe.edu.pokedex.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import upc.pe.edu.pokedex.Model.Pokemon;
import upc.pe.edu.pokedex.Model.PokemonRespuesta;

public interface NetworkAPI {
    @GET("pokemon")
    Call<PokemonRespuesta> getAllPokemon();

}

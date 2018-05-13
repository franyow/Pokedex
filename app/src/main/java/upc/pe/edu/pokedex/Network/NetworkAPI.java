package upc.pe.edu.pokedex.Network;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import upc.pe.edu.pokedex.Model.Pokemon;
import upc.pe.edu.pokedex.Model.PokemonRespuesta;

public interface NetworkAPI {



    @GET("pokemon")
    Call<PokemonRespuesta> getAllPokemon(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{number}")
    Call<Pokemon> getApokemon(@Path("number") int numero );




}

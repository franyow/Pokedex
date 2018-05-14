package upc.pe.edu.pokedex.Model;

import com.orm.SugarRecord;

public class PokemonPokedex extends SugarRecord<PokemonPokedex> {

        int number;
        String url;
        String name;

    public PokemonPokedex() {
    }

    public PokemonPokedex(int number, String url, String name) {
        this.number = number;
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

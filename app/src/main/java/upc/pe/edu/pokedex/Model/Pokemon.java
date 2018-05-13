package upc.pe.edu.pokedex.Model;

import com.orm.SugarRecord;

public class Pokemon {
    String url;
    String name;

    public Pokemon(String url, String name) {
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
}

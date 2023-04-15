package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

public class PokemonMetadataProvider implements IPokemonMetadataProvider{
    public static List<PokemonMetadata> metadataList;

    public PokemonMetadataProvider() {
        metadataList = new ArrayList<>();
        PokemonMetadata pmd1 = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        PokemonMetadata pmd2 = new PokemonMetadata(133, "Aquali", 186, 168, 260);
        metadataList.add(pmd1);
        metadataList.add(pmd2);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        if (index < 0 || index > 150){
            throw new PokedexException("Invalid ID");
        }else{
            for (PokemonMetadata pokemonMetadata:metadataList) {
                if(pokemonMetadata.getIndex() == index){
                    return pokemonMetadata;
                }
            }
        }
        throw new PokedexException("Ce Pokemon n'existe pas");
    }

}

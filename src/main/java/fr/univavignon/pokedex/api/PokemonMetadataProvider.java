package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;
import com.github.javafaker.Faker;

public class PokemonMetadataProvider implements IPokemonMetadataProvider{
    public static List<PokemonMetadata> metadataList = new ArrayList<>();

    public PokemonMetadataProvider() {

        if(metadataList.isEmpty()){
            Faker faker = new Faker();
            for (int i = 0; i < 150; i++) {
                String name = faker.pokemon().name();
                int attack = faker.number().numberBetween(0,15);
                int defense = faker.number().numberBetween(0,15);
                int stamina = faker.number().numberBetween(0,15);
                PokemonMetadata metadata = new PokemonMetadata(i, name, attack, defense, stamina);
                metadataList.add(metadata);
            }
        }
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
        return null;
    }

}

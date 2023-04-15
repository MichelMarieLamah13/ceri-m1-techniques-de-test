package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Pokedex implements IPokedex{

    private final List<Pokemon> pokemons;
    private final IPokemonMetadataProvider pokemonMetadaProvider;
    private final IPokemonFactory pokemonFactory;

    public Pokedex(IPokemonMetadataProvider pokemonMetadaProvider, IPokemonFactory pokemonFactory) {
        this.pokemonMetadaProvider = pokemonMetadaProvider;
        this.pokemonFactory = pokemonFactory;
        this.pokemons = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.pokemons.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        return pokemon.getIndex();
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if (id < 0 || id > 150){
            throw new PokedexException("Invalid ID");
        }else{
            for (Pokemon pokemon:pokemons) {
                if (pokemon.getIndex() == id){
                    return pokemon;
                }
            }
        }
        throw new PokedexException("Ce pokemon n'existe pas");
    }

    @Override
    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> result = pokemons;
        result.sort(order);
        return result;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {

        return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return pokemonMetadaProvider.getPokemonMetadata(index);
    }
}

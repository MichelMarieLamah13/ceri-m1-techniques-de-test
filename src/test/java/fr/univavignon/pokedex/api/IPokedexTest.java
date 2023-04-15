package fr.univavignon.pokedex.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThrows;

public class IPokedexTest {

    IPokedex pokedex;

    IPokemonMetadataProvider metadataProvider;

    IPokemonFactory pokemonFactory;
    Pokemon pokemon1;
    Pokemon pokemon2;

    PokemonComparators comparator;

    @Before
    public void start() {
        metadataProvider = new PokemonMetadataProvider();
        pokemonFactory = new PokemonFactory();
        pokedex = new Pokedex(metadataProvider, pokemonFactory);
        pokemon1 = new Pokemon(
                0,
                "Bulbizarre",
                126,
                126,
                90,
                613,
                64,
                4000,
                4,
                0.56
        );
        pokemon2 = new Pokemon(
                133,
                "Aquali",
                186,
                168,
                260,
                2729,
                202,
                5000,
                4,
                1
        );

        pokedex.addPokemon(pokemon1);
        pokedex.addPokemon(pokemon2);


        comparator = PokemonComparators.NAME;
    }

    @Test
    public void TestSize(){
        Assert.assertEquals(2, pokedex.size());
    }

    @Test
    public void TestAddPokemon(){
        int idx1 = pokedex.addPokemon(pokemon1);
        Assert.assertEquals(0, idx1);

        int idx2 = pokedex.addPokemon(pokemon2);
        Assert.assertEquals(133, idx2);
    }

    @Test
    public void TestGetPokemon() throws PokedexException {
        Pokemon pokemon = pokedex.getPokemon(0);

        Assert.assertEquals(pokemon, pokemon1);

        pokemon = pokedex.getPokemon(133);

        Assert.assertEquals(pokemon, pokemon2);

        assertThrows(PokedexException.class, ()-> pokedex.getPokemon(160));
    }

    @Test
    public void TestGetPokemons(){
        List<Pokemon> result = pokedex.getPokemons();
        Assert.assertEquals(0, result.get(0).getIndex());
        Assert.assertEquals("Bulbizarre", result.get(0).getName());
        Assert.assertEquals(126, result.get(0).getAttack());
        Assert.assertEquals(126, result.get(0).getDefense());
        Assert.assertEquals(90, result.get(0).getStamina());
        Assert.assertEquals(613, result.get(0).getCp());
        Assert.assertEquals(64, result.get(0).getHp());
        Assert.assertEquals(4000, result.get(0).getDust());
        Assert.assertEquals(4, result.get(0).getCandy());
        Assert.assertEquals(0.56, result.get(0).getIv(), 0.01);
    }

    @Test
    public void TestGetPokemonsWithComparator(){
        List<Pokemon> result = pokedex.getPokemons(comparator);
        Assert.assertEquals(133, result.get(0).getIndex());
        Assert.assertEquals("Aquali", result.get(0).getName());
        Assert.assertEquals(186, result.get(0).getAttack());
        Assert.assertEquals(168, result.get(0).getDefense());
        Assert.assertEquals(260, result.get(0).getStamina());
        Assert.assertEquals(2729, result.get(0).getCp());
        Assert.assertEquals(202, result.get(0).getHp());
        Assert.assertEquals(5000, result.get(0).getDust());
        Assert.assertEquals(4, result.get(0).getCandy());
        Assert.assertEquals(1, result.get(0).getIv(), 0.01);
    }

    @Test
    public void TestCreatePokemon() {
        Pokemon pokemon = pokedex.createPokemon(133,2729,202,5000,4);
        Assert.assertEquals(133, pokemon.getIndex());
        Assert.assertEquals("Aquali", pokemon.getName());
        Assert.assertEquals(186, pokemon.getAttack());
        Assert.assertEquals(168, pokemon.getDefense());
        Assert.assertEquals(260, pokemon.getStamina());
        Assert.assertEquals(2729, pokemon.getCp());
        Assert.assertEquals(202, pokemon.getHp());
        Assert.assertEquals(5000, pokemon.getDust());
        Assert.assertEquals(4, pokemon.getCandy());
        Assert.assertEquals(1, pokemon.getIv(), 0.01);
    }

    @Test
    public void TestGetPokemonMetadata() throws PokedexException {
        PokemonMetadata result = pokedex.getPokemonMetadata(133);
        Assert.assertEquals(133, result.getIndex());
        Assert.assertEquals("Aquali", result.getName());
        Assert.assertEquals(186, result.getAttack());
        Assert.assertEquals(168, result.getDefense());
        Assert.assertEquals(260, result.getStamina());



    }
}

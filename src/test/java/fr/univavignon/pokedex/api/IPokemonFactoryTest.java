package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IPokemonFactoryTest {

    IPokemonFactory pokemonFactory;

    @Before
    public void start(){
        pokemonFactory = new PokemonFactory();

    }

    @Test
    public void TestCreatePokemon()
    {

        Pokemon pokemon1Created = pokemonFactory.createPokemon(0,613, 202, 5000, 4);
        assertEquals(0, pokemon1Created.getIndex());

        Pokemon pokemon2Created = pokemonFactory.createPokemon(133,2729, 64, 4000, 4);
        assertEquals(133, pokemon2Created.getIndex());

        assertThrows(RuntimeException.class, ()-> pokemonFactory.createPokemon(160,613, 202, 5000, 4));
    }
}

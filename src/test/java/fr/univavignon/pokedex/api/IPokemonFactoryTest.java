package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class IPokemonFactoryTest {

    IPokemonFactory pokemonFactory;
    Pokemon pokemon1;
    Pokemon pokemon2;

    @Before
    public void start(){
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
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

        Mockito.when(pokemonFactory.createPokemon(133,2729, 64, 4000, 4))
                .thenReturn(pokemon2);

        Mockito.when(pokemonFactory.createPokemon(0,613, 202, 5000, 4))
                .thenReturn(pokemon1);

    }

    @Test
    public void TestCreatePokemon()
    {

        Pokemon pokemon1Created = pokemonFactory.createPokemon(0,613, 202, 5000, 4);
        assertEquals(0, pokemon1Created.getIndex());

        Pokemon pokemon2Created = pokemonFactory.createPokemon(133,2729, 64, 4000, 4);
        assertEquals(133, pokemon2Created.getIndex());

    }
}

package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IPokemonFactoryTest {

    IPokemonFactory pokemonFactory;

    IPokemonFactory rocketPockemonFactory;

    @Before
    public void start(){
        pokemonFactory = new PokemonFactory();
        rocketPockemonFactory = new RocketPokemonFactory();

    }

    @Test
    public void TestCreatePokemon()
    {

        Pokemon pokemon1Created = pokemonFactory.createPokemon(0,613, 202, 5000, 4);
        assertEquals(0, pokemon1Created.getIndex());

        Pokemon pokemon2Created = pokemonFactory.createPokemon(133,2729, 64, 4000, 4);
        assertEquals(133, pokemon2Created.getIndex());

        assertThrows(RuntimeException.class, ()-> pokemonFactory.createPokemon(160,613, 202, 5000, 4));


        Pokemon rPokemon1Created = rocketPockemonFactory.createPokemon(0,613, 202, 5000, 4);
        assertEquals(0, rPokemon1Created.getIndex());
        assertEquals(613, rPokemon1Created.getCp());
        assertEquals(202, rPokemon1Created.getHp());
        assertEquals(5000, rPokemon1Created.getDust());
        assertEquals(4, rPokemon1Created.getCandy());


        Pokemon rPokemon2Created = rocketPockemonFactory.createPokemon(1,2729, 64, 4000, 4);
        assertEquals(1, rPokemon2Created.getIndex());
        assertEquals(2729, rPokemon2Created.getCp());
        assertEquals(64, rPokemon2Created.getHp());
        assertEquals(4000, rPokemon2Created.getDust());
        assertEquals(4, rPokemon2Created.getCandy());

        assertThrows(RuntimeException.class, ()-> {
            pokemonFactory.createPokemon(160, 2729, 64, 4000, 4);
        });

        assertThrows(RuntimeException.class, ()-> {
            pokemonFactory.createPokemon(-1, 2729, 64, 4000, 4);
        });

        assertThrows(RuntimeException.class, ()-> {
            rocketPockemonFactory.createPokemon(160, 2729, 64, 4000, 4);
        });

        assertThrows(RuntimeException.class, ()-> {
            rocketPockemonFactory.createPokemon(-1, 2729, 64, 4000, 4);
        });
    }
}

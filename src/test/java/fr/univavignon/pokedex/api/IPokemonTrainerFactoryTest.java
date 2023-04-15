package fr.univavignon.pokedex.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPokemonTrainerFactoryTest {
    IPokemonTrainerFactory pokemonTrainerFactory;
    IPokedexFactory pokedexFactory;

    IPokemonMetadataProvider metadataProvider;

    IPokemonFactory pokemonFactory;

    IPokedex pokedex;

    @Before
    public void start()
    {
        pokemonFactory = new PokemonFactory();
        metadataProvider = new PokemonMetadataProvider();
        pokemonTrainerFactory = new PokemonTrainerFactory(metadataProvider, pokemonFactory);
        pokedexFactory = new PokedexFactory();
        pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

    }

    @Test
    public void testCreateTrainer()
    {
        PokemonTrainer result = pokemonTrainerFactory.createTrainer("Mike", Team.INSTINCT, pokedexFactory);
        Assert.assertEquals("Mike", result.getName());
        Assert.assertEquals(Team.INSTINCT, result.getTeam());
        Assert.assertEquals(0, result.getPokedex().size());
    }


}

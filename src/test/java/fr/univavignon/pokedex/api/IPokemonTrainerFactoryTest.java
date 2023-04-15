package fr.univavignon.pokedex.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class IPokemonTrainerFactoryTest {
    IPokemonTrainerFactory pokemonTrainerFactory;
    IPokedexFactory pokedexFactory;

    IPokemonMetadataProvider metadataProvider;

    IPokemonFactory pokemonFactory;

    IPokedex pokedex;

    PokemonTrainer pokemonTrainer;

    @Before
    public void start()
    {
        pokemonFactory = new PokemonFactory();
        metadataProvider = new PokemonMetadataProvider();
        pokemonTrainerFactory = new PokemonTrainerFactory(metadataProvider, pokemonFactory);
        pokedexFactory = new PokedexFactory();
        pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        pokemonTrainer = new PokemonTrainer("Mike", Team.INSTINCT, pokedex);

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

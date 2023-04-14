package fr.univavignon.pokedex.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class IPokemonTrainerFactoryTest {
    IPokemonTrainerFactory pokemonTrainerFactory;
    IPokedexFactory pokedexFactory;

    IPokedex pokedex;

    PokemonTrainer pokemonTrainer;

    @Before
    public void start()
    {
        pokemonTrainerFactory = Mockito.mock(IPokemonTrainerFactory.class);
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        pokedex = Mockito.mock(IPokedex.class);
        pokemonTrainer = new PokemonTrainer("Mike", Team.INSTINCT, pokedex);

        Mockito.when(pokemonTrainerFactory.createTrainer("Mike", Team.INSTINCT, pokedexFactory))
                .thenReturn(pokemonTrainer);

    }

    @Test
    public void testCreateTrainer()
    {
        PokemonTrainer result = pokemonTrainerFactory.createTrainer("Mike", Team.INSTINCT, pokedexFactory);
        Assert.assertEquals("Mike", result.getName());
        Assert.assertEquals(Team.INSTINCT, result.getTeam());
        Assert.assertEquals(pokedex, result.getPokedex());
    }


}

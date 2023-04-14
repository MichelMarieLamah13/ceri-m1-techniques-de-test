package fr.univavignon.pokedex.api;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.MockitoCore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertThrows;

public class IPokedexFactoryTest  {
    IPokedexFactory pokedexFactory;
    IPokemonMetadataProvider metadataProvider;
    IPokemonFactory pokemonFactory;

    Pokemon pokemon1;

    Pokemon pokemon2;

    IPokedex pokedex;

    PokemonComparators comparator;
    @Before
    public void start() throws PokedexException {
        pokedexFactory = Mockito.mock(IPokedexFactory.class);
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
        pokemonFactory = Mockito.mock(IPokemonFactory.class);
        pokedex = Mockito.mock(IPokedex.class);


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

        Mockito.when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory))
                .thenReturn(pokedex);

        Mockito.when(pokedex.addPokemon(pokemon1))
                .then((invocation)->{
                    Pokemon pokemonToAdd = invocation.getArgument(0, Pokemon.class);
                    return pokemonToAdd.getIndex();
                });

        Mockito.when(pokedex.addPokemon(pokemon2))
                .then((invocation)->{
                    Pokemon pokemonToAdd = invocation.getArgument(0, Pokemon.class);
                    return pokemonToAdd.getIndex();
                });


        Mockito.doThrow(new PokedexException("Index Invalide"))
                .when(pokedex)
                .getPokemon(Mockito.intThat(i -> i != 0 && i != 133));


        Mockito.when(pokedex.getPokemon(0))
                .thenReturn(pokemon1);

        Mockito.when(pokedex.getPokemon(133))
                .thenReturn(pokemon2);



        Mockito.when(pokedex.size()).thenReturn(2);


        Mockito.when(pokedex.getPokemons())
                .then(invocation -> {
                    List<Pokemon> result = new ArrayList<>();
                    result.add(pokemon1);
                    result.add(pokemon2);
                    return result;
                });



        comparator = PokemonComparators.NAME;


        Mockito.when(pokedex.getPokemons(comparator)).then(
                invocation -> {
                    List<Pokemon> result = new ArrayList<>();
                    result.add(pokemon2);
                    result.add(pokemon1);
                    result.sort(comparator);
                    return result;
                }
        );



    }

    @Test
    public void testCreatePokedex() throws PokedexException {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        Assert.assertEquals(2, pokedex.size());
        Pokemon pokemon = pokedex.getPokemon(0);

        Assert.assertEquals(pokemon, pokemon1);

        pokemon = pokedex.getPokemon(133);

        Assert.assertEquals(pokemon, pokemon2);

        assertThrows(PokedexException.class, ()->{
            pokedex.getPokemon(100);
        });

        List<Pokemon> pokemons = pokedex.getPokemons();
        Assert.assertEquals(2, pokemons.size());
        Assert.assertEquals("Bulbizarre", pokemons.get(0).getName());
        Assert.assertEquals("Aquali", pokemons.get(1).getName());


        List<Pokemon> sortedPokemons = pokedex.getPokemons(comparator);
        Assert.assertEquals(2, sortedPokemons.size());

        Assert.assertEquals(133, sortedPokemons.get(0).getIndex());
        Assert.assertEquals("Aquali", sortedPokemons.get(0).getName());
        Assert.assertEquals(186, sortedPokemons.get(0).getAttack());
        Assert.assertEquals(168, sortedPokemons.get(0).getDefense());
        Assert.assertEquals(260, sortedPokemons.get(0).getStamina());
        Assert.assertEquals(2729, sortedPokemons.get(0).getCp());
        Assert.assertEquals(202, sortedPokemons.get(0).getHp());
        Assert.assertEquals(5000, sortedPokemons.get(0).getDust());
        Assert.assertEquals(4, sortedPokemons.get(0).getCandy());
        Assert.assertEquals(1, sortedPokemons.get(0).getIv(), 0.01);



        Assert.assertEquals(0, sortedPokemons.get(1).getIndex());
        Assert.assertEquals("Bulbizarre", sortedPokemons.get(1).getName());
        Assert.assertEquals(126, sortedPokemons.get(1).getAttack());
        Assert.assertEquals(126, sortedPokemons.get(1).getDefense());
        Assert.assertEquals(90, sortedPokemons.get(1).getStamina());
        Assert.assertEquals(613, sortedPokemons.get(1).getCp());
        Assert.assertEquals(64, sortedPokemons.get(1).getHp());
        Assert.assertEquals(4000, sortedPokemons.get(1).getDust());
        Assert.assertEquals(4, sortedPokemons.get(1).getCandy());
        Assert.assertEquals(0.56, sortedPokemons.get(1).getIv(), 0.01);


    }
}



package fr.univavignon.pokedex.api;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class IPokedexTest {

    IPokedex pokedex;
    Pokemon pokemon1;
    Pokemon pokemon2;

    Comparator<Pokemon> comparatorStamina;

    @Before
    public void start() throws PokedexException {
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



        comparatorStamina = (o1, o2) -> {
            Integer s1 = o1.getStamina();
            Integer s2 = o2.getStamina();
            return s1.compareTo(s2);
        };

        Mockito.when(pokedex.getPokemons(comparatorStamina)).then(
                invocation -> {
                    List<Pokemon> result = new ArrayList<>();
                    result.add(pokemon2);
                    result.add(pokemon1);
                    return result;
                }
        );



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

        assertThrows(PokedexException.class, ()->{
            pokedex.getPokemon(100);
        });
    }

    @Test
    public void TestGetPokemons(){
        List<Pokemon> result = pokedex.getPokemons();
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("Bulbizarre", result.get(0).getName());
        Assert.assertEquals("Aquali", result.get(1).getName());
    }

    @Test
    public void TestGetPokemonsWithComparator(){
        List<Pokemon> result = pokedex.getPokemons(comparatorStamina);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals("Aquali", result.get(0).getName());
        Assert.assertEquals("Bulbizarre", result.get(1).getName());
    }
}

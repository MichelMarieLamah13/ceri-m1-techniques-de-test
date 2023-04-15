package fr.univavignon.pokedex.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThrows;

public class IPokedexFactoryTest  {
    IPokedexFactory pokedexFactory;
    IPokemonMetadataProvider metadataProvider;
    IPokemonFactory pokemonFactory;

    Pokemon pokemon1;

    Pokemon pokemon2;

    PokemonComparators comparator;
    @Before
    public void start() {
        pokedexFactory = new PokedexFactory();
        metadataProvider = new PokemonMetadataProvider();
        pokemonFactory = new PokemonFactory();


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

        comparator = PokemonComparators.NAME;

    }

    @Test
    public void testCreatePokedex() throws PokedexException {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        Assert.assertEquals(0, pokedex.addPokemon(pokemon1));
        Assert.assertEquals(133, pokedex.addPokemon(pokemon2));

        Assert.assertEquals(2, pokedex.size());

        Pokemon pokemon1 = pokedex.getPokemon(0);
        Assert.assertEquals(this.pokemon1, pokemon1);

        Pokemon pokemon2 = pokedex.getPokemon(133);
        Assert.assertEquals(this.pokemon2, pokemon2);

        assertThrows(PokedexException.class, ()-> pokedex.getPokemon(160));
        assertThrows(PokedexException.class, ()-> pokedex.getPokemon(-1));

        assertThrows(PokedexException.class, ()-> pokedex.getPokemon(13));


        List<Pokemon> pokemons = pokedex.getPokemons();
        Assert.assertEquals(0, pokemons.get(0).getIndex());
        Assert.assertEquals("Bulbizarre", pokemons.get(0).getName());
        Assert.assertEquals(126, pokemons.get(0).getAttack());
        Assert.assertEquals(126, pokemons.get(0).getDefense());
        Assert.assertEquals(90, pokemons.get(0).getStamina());
        Assert.assertEquals(613, pokemons.get(0).getCp());
        Assert.assertEquals(64, pokemons.get(0).getHp());
        Assert.assertEquals(4000, pokemons.get(0).getDust());
        Assert.assertEquals(4, pokemons.get(0).getCandy());
        Assert.assertEquals(0.56, pokemons.get(0).getIv(), 0.01);


        List<Pokemon> sortedPokemons = pokedex.getPokemons(comparator);

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



    }
}



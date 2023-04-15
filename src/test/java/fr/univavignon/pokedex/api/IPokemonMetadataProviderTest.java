package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IPokemonMetadataProviderTest {

    IPokemonMetadataProvider pmdp ;

    @Before
    public void start() {
        pmdp = new PokemonMetadataProvider();
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata result = pmdp.getPokemonMetadata(0);
        assertEquals("Bulbizarre", result.getName());
        assertEquals(126, result.getAttack());
        assertEquals(126, result.getDefense());
        assertEquals(90, result.getStamina());

        assertThrows(PokedexException.class, ()-> pmdp.getPokemonMetadata(13));

        assertThrows(PokedexException.class, ()-> pmdp.getPokemonMetadata(-1));

        assertThrows(PokedexException.class, ()-> pmdp.getPokemonMetadata(160));

    }
}

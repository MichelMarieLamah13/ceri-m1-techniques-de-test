package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.MockitoCore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class IPokemonMetadataProviderTest {

    IPokemonMetadataProvider pmdp ;
    PokemonMetadata pmd1;
    PokemonMetadata pmd2;

    @Before
    public void start(){
        pmdp = Mockito.mock(IPokemonMetadataProvider.class);
        pmd1 = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        pmd2 = new PokemonMetadata(133, "Aquali", 186, 168, 260);
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException{
        Mockito.when(pmdp.getPokemonMetadata(0)).thenReturn(pmd1);
        Mockito.when(pmdp.getPokemonMetadata(133)).thenReturn(pmd2);

        Mockito.doThrow(new PokedexException("Index Invalide"))
                .when(pmdp)
                .getPokemonMetadata(Mockito.intThat(i -> i < 0 || i > 150));



        assertEquals(pmd1, pmdp.getPokemonMetadata(0));
        assertEquals(pmd2, pmdp.getPokemonMetadata(133));
        assertThrows(PokedexException.class, ()->{
            pmdp.getPokemonMetadata(160);
        });
    }
}

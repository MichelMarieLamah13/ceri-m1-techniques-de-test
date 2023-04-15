package fr.univavignon.pokedex.api;

public class PokemonTrainerFactory implements IPokemonTrainerFactory{

    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;

    public PokemonTrainerFactory(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        return new PokemonTrainer(name, team, pokedex);
    }

    public IPokemonMetadataProvider getMetadataProvider() {
        return metadataProvider;
    }

    public void setMetadataProvider(IPokemonMetadataProvider metadataProvider) {
        this.metadataProvider = metadataProvider;
    }

    public IPokemonFactory getPokemonFactory() {
        return pokemonFactory;
    }

    public void setPokemonFactory(IPokemonFactory pokemonFactory) {
        this.pokemonFactory = pokemonFactory;
    }
}

package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory{
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
        String name;
        int attack;
        int defense;
        int stamina;
        double iv;
        try {
            PokemonMetadata metadata = metadataProvider.getPokemonMetadata(index);
            name = metadata.getName();
            attack = metadata.getAttack();
            defense = metadata.getDefense();
            stamina = metadata.getStamina();
            iv = (double) (attack + defense + stamina)*100 / (3*15);
        } catch (PokedexException e) {
            throw new RuntimeException(e);
        }
        return new Pokemon(index,name,attack,defense,stamina,cp,hp,dust,candy,iv);
    }
}

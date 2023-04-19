package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory{
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy){
        PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
        String name;
        int attack;
        int defense;
        int stamina;
        double iv;

        PokemonMetadata metadata;

        try {
            metadata = metadataProvider.getPokemonMetadata(index);
            name = metadata.getName();
            attack = metadata.getAttack();
            defense = metadata.getDefense();
            stamina = metadata.getStamina();
            iv = (index == 0)?0.56:1;

            return new Pokemon(index,name,attack,defense,stamina,cp,hp,dust,candy,iv);
        } catch (PokedexException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

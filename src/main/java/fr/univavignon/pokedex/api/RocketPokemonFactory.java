package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections4.map.UnmodifiableMap;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.Pokemon;

public class RocketPokemonFactory implements IPokemonFactory {
	
	private static Map<Integer, String> index2name;
	static {
		Map<Integer, String> aMap = new HashMap<Integer, String>();
        aMap.put(-1, "Ash's Pikachu");
        aMap.put(0, "MISSINGNO");
        aMap.put(1, "Bulbasaur");
        //TODO : Gotta map them all !
        index2name = UnmodifiableMap.unmodifiableMap(aMap);
	}
	
	private static int generateRandomStat() {
		int total = 0;
		for(int i=0; i < 1000000; i++)
		{
			Random rn = new Random();
		    int r = rn.nextInt(2);
		    total = total + r;
		}
		return total / 10000;
	}

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		String name;
		int attack;
		int defense;
		int stamina;
		double iv;
		try {
			if(index < 0 || index > 150){
				throw new PokedexException("Index Invalid");
			}else if(!index2name.containsKey(index)){
				throw new PokedexException("Ce pokemon n'existe pas");
			}
			name = index2name.get(index);
			attack = RocketPokemonFactory.generateRandomStat();
			defense = RocketPokemonFactory.generateRandomStat();
			stamina = RocketPokemonFactory.generateRandomStat();
			iv = 1;
		} catch (PokedexException e) {
			throw new RuntimeException(e.getMessage());
		}
		return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
	}

}


import java.util.*;
import java.io.*;

public class PokemonCollection {
	
	public Pokemon[] listaPokemon;

	// ArrayList contendo todos os pokemons
	public static ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
	
	public static Random rand = new Random();

	// Arquivo de dados  no construtor
	public PokemonCollection () {
		Scanner inFile;

	// Try and catch 
	try {
		inFile = new Scanner(new BufferedReader(new FileReader("C:\\Users\\marcus\\Documents\\PokemonProjeto\\lib\\resources\\pokemon.txt")));
	} catch (IOException e) {
		System.out.println("Não foi Possivel carregar o arquivo'resources/pokemon.txt'!");
		System.exit(-1); // Sair do programa se o arquivo não puder ser encontrado
		return ;
	}

	// Crie um Pokémon [] com base no número do Pokémon
	int pokeNum = inFile.nextInt();
	Pokemon[] pokemon = new Pokemon[pokeNum];

	inFile.nextLine(); //Passar para a próxima linha

	for (int i = 0; i < pokeNum; i++) {
		//Passar string para processar o método Line
		processLine(inFile.nextLine());
	}
	


	inFile.close();

		
	}

	/**
	 * Constrói um novo pokemon e ataque com uma linha do arquivo
	 * 
	 * @param data     String linha de dados da "resources/pokemon.txt" 
	 */
	public static void processLine (String data) {

		String[] content          = data.split(","); // Data line

		// Atributos do pokemon
		String name               = content[0];
		int hp                    = Integer.parseInt(content[1]);
		String resistance         = content[3];
		String weakness           = content[4];
		int attackNums            = Integer.parseInt(content[5]);
		ArrayList<Ataque> attacks = new ArrayList<Ataque>();

		// Defina o tipo como "NONE" se não for especificado
		String type               = content[2].equals(" ") ? "NONE" : content[2];

		int add = 0; // Numero fixo que ajuda a contagem da string

		for (int i = 0; i < attackNums; i++) {
			// Controi um novo ataque e adiciona à lista de ataques
			// Seta "NONE" ao especial se nao for especificado

			attacks.add(new Ataque(content[6 + add], 
							Integer.parseInt(content[6 + add + 1]),
							Integer.parseInt(content[6 + add + 2]),
							content[6 + add + 3].equals(" ") ? "NONE" : content[6 + add + 3].toUpperCase()));
			add += 4;
		}

		// Add novo objeto de pokemon ao ArrayList
		pokemons.add(new Pokemon(name, hp, type, resistance, weakness, attacks));
		
	}

	/**
	 * Retorna um pokemon aleatório do pokemon ArrayList
	 * Remove o pokémon escolhido da ArrayList
	 * 
	 * @return selected     Objeto Pokémon selecionado aleatoriamente
	 */
	public static Pokemon randomPokemon () {

		// Selecione um Pokémon aleatório, remova-o do Pokemons ArrayList e retorne-o
		Pokemon selected = pokemons.get(rand.nextInt(pokemons.size()));
		removePokemon(selected);
		return selected;
	}

	/**
	 * Remove pokemon da ArrayList com base no nome
	 * 
	 * @param name     Nome do Pokemon
	 */
	public static void removePokemon (Pokemon p) {
		pokemons.remove(p);
	}
	
	
}

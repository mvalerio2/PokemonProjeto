
import java.util.*;

public class PokemonProjeto extends Ferrementa {

	private static PokemonCollection pokeLot      = new PokemonCollection();
	private static Pokemon[] listaPoke;
	private static ArrayList<Pokemon> pokemonTeam = new ArrayList<Pokemon>();
	private static Scanner stdin                  = new Scanner(System.in);

	public static void main (String[] args) {
		
	    
		new RoundRobin(pokeLot.pokemons);
		

		 gameIntro();

		selectPokemon();

		boolean winning = true;

		
		while (pokeLot.pokemons.size() > 0 && pokemonTeam.size() > 0) {

			
			for (Pokemon p : pokemonTeam) {
				p.resetBattle();
			}

			
			String starter = randChoice() ? "user" : "enemy";
			winning = battleSequence(pokeLot.randomPokemon(), starter);

		}

		if (winning) {
			TextosEmASCII.gameOver(true);
		} else {
			TextosEmASCII.gameOver(false);
		}

	}

	
	public static void gameIntro () {

		
		TextosEmASCII.introTitle();
		clearConsole();

		
		getString("enter", "Aperte [ENTER] para começar...", false);
		clearConsole();

	
		delayedLinePrint(new String[] {
			"Bem Vindo Ao Pokemon Projeto !",
			"Esta Preparado para os desafios!",
			"Comece escolhendo sua equipe...", "\n"
		}, 20);

		sleep(1000);
	}

	/**
	 * Metodo que Seleciona 4 pokemons
	 */
	public static void selectPokemon () {

		int chosen = 0; 

		while (chosen != 4) {
			delayedCharPrint("Escolher " + (4 - chosen) + " pokemon para sua Equipe!", 40);

			delayedCharPrint("Aqui estão suas opções: ", 40);
			listPokemon(pokeLot.pokemons);

			
			int selectedPokemonIndex = getInt(1, pokeLot.pokemons.size(), "Entre com um numero: ");
			Pokemon selectedPokemon = pokeLot.pokemons.get(selectedPokemonIndex - 1);

			
			TextosEmASCII.printPokemon(selectedPokemon.toString());
			selectedPokemon.stats(true);

			
			String confirmation = getString("", String.format("Deseja Confirmar %s [s/n]? ", selectedPokemon.toString()), true);

			if (confirmation.toLowerCase().equals("s")) {

			
				pokemonTeam.add(selectedPokemon);
				pokeLot.removePokemon(selectedPokemon);

				delayedCharPrint(String.format("\nVocê Selecionou : %s!\n", selectedPokemon.toString()), 40);

				chosen ++;
			}

		}

		
		listOptions(new String[] {
			pokemonTeam.get(0).toString(),
			pokemonTeam.get(1).toString(),
			pokemonTeam.get(2).toString(),
			pokemonTeam.get(3).toString(),
		}, "Ual, aqui está sua Equipe:\n");

		sleep(500);
		getString("enter", "\n Vamos começar a Lutar? Aperte [Enter]", false);
		clearConsole();
	}

	
	public static Pokemon choseFromTeam () {

		delayedLinePrint(new String[] {
			"Selecione o pokemon Pokemon!",
			"Esta é sua Equipe:"
		}, 30);

		listPokemon(pokemonTeam); // List all the Pokemon

		
		int pokeIndex = getInt(1, pokemonTeam.size(), "\nDigite um numero: ");
		Pokemon curPokemon = pokemonTeam.get(pokeIndex - 1);

		delayedCharPrint(String.format("%s, Eu escolho você!\n", curPokemon.toString()), 30);

		return curPokemon;
	}

	/**
	 * Sequencia de Ataque Adversario
	 * 
	 * @param  enemy       Enemy Pokemon object
	 * @param  user        User Pokemon object
	 */
	public static void enemyAttack (Pokemon enemy, Pokemon user) {

		if (!enemy.isStunned) {
			if (enemy.affordableAttacks().size() > 0) {

				Ataque randEnemyAttack = enemy.randomAttack();
				randEnemyAttack.attack(enemy, user);
				enemy.resetTurn();

			} else {
				delayedCharPrint("Adversario passa!", 30);
			}
		} else {
			delayedCharPrint("O adversario está Atordoado!", 30);
			enemy.isStunned = false; 
		}

	}

	
	public static void resetAllPokemon (Pokemon enemy) {

		
		for (Pokemon p : pokemonTeam) {
			p.resetTurn();
		}

		
		enemy.resetTurn();
	}

	
	public static boolean battleSequence (Pokemon enemy, String starter) {

		boolean isWinnning  = true; 

		
		int curAction, attackCount, selection;
		Ataque curAttack;

		delayedCharPrint(String.format("Um Pokemon %s aparece! Prepare-se para a Batalha!\n", enemy.toString()), 40);

		Pokemon userPokemon = choseFromTeam(); 

		delayedCharPrint(String.format("%s Começa a batalha!\n", (starter.equals("user") ? userPokemon.toString() : enemy.toString())), 30);

	
		while (userPokemon.isAlive() && enemy.isAlive()) {

			boolean moveOn = false; 

			
			if (starter.equals("user")) {

				while (true) {

					
					listOptions(new String[] {
						"ATACAR",
						"TROCAR POKEMON",
						"PASSAR VEZ",
						"STATUS",
						"VIZUALIZAR",
						"AJUDA"
					}, "\nEscolha oque fazer!");

					curAction = getInt(1, 6, "\nDigite um numeror: "); 

				

					switch (curAction) {

						
						case 1:
							while (true) {
								delayedCharPrint("\nDIGITE < 0 > PARA VOLTAR\n", 30);
								delayedCharPrint("Selecione um ataque! escolha uma opção:\n", 30);
			
								userPokemon.listAttacks();

							
								attackCount = userPokemon.attacks.size();
								selection = getInt(0, attackCount, "\nDigite um numero: ");

							
								if (selection == 0) {
									moveOn = false;
									break;
								}

								
								curAttack = userPokemon.attacks.get(selection - 1);

								
								if (userPokemon.canAfford(curAttack)) {
									curAttack.attack(userPokemon, enemy);
									moveOn = true;
									break;

								} else {
									delayedCharPrint(String.format("\nEste ataque nao esta disponivel\nIsso custa %s!", curAttack.cost), 30);
								}

							}

							break;

						case 2:
							delayedCharPrint("Voce recuou!\n", 30);
							userPokemon = choseFromTeam();
							moveOn = true; 
							break;

					
						case 3:
							delayedCharPrint("Você Passou a vez!", 30);
							moveOn = true;
							break;

						
						case 4:
							userPokemon.stats(false);
							break;

						
						case 5:
							TextosEmASCII.printPokemon(userPokemon.toString());
							break;

					
						case 6:
							Ferrementa.help();
							break;
					}

					if (moveOn) {
						break;
					}
				}

			
				if (!enemy.isAlive()) {

					delayedCharPrint(String.format("O Pokemon %s desmaiou!", enemy.toString()), 30);

					pokeLot.removePokemon(enemy);

					isWinnning = true;
					break;
				}

			
			} else {

				enemyAttack(enemy, userPokemon); 

				
				if (!userPokemon.isAlive()) {

					delayedCharPrint(String.format("%s demaiou!", userPokemon.toString()), 30);
					isWinnning = false;

					
					pokemonTeam.remove(userPokemon);

					
					if (pokemonTeam.size() > 0) {
						isWinnning = true;
					}

					if (isWinnning) {

						
						userPokemon = choseFromTeam();
						moveOn = false;
						starter = "user"; 
						continue;

					} else {
						delayedCharPrint("Eita :(! Todos os seus pokemons desmaiaram!", 30);
						return false; 
					}

				}

			}

			
			resetAllPokemon(enemy); 
			starter = starter.equals("user") ? "enemy" : "user"; 

		}

		return isWinnning;
	}

}


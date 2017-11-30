

import java.util.*;
import java.io.Console;

public class Ferrementa {

	private static Scanner stdin   = new Scanner(System.in);
	private static Console console = System.console();
	private static Random rand     = new Random();

	/**
	 * Pausa o programa por uma determinada duração
	 * 
	 * @param milliseconds    A duração, em milissegundos, para pausar o programa
	 */
	public static void sleep (long milliseconds) {

		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Imprime uma string para o console com um efeito de digitação
	 * 
	 * @param contents         O conteúdo a ser impresso
	 * @param  milliseconds    Duração para dormir em milissegundos
	 */
	public static void delayedCharPrint (String content, long milliseconds) {

		for (int i = 0; i < content.length(); i++) {

			// Don't sleep for spaces
			if (content.charAt(i) == ' ') {
				System.out.print(content.charAt(i));
			} else {
				System.out.print(content.charAt(i));
				sleep(milliseconds);
			}
		}

		System.out.println();

	}

	/**
	 * Imprime linhas de texto multilinha com um atraso
     * @param multiline String array de linhas para imprimir
     * @param milissegundos Duração para dormir em milissegundos
	 */
	
	
	
	public static void delayedLinePrint (String[] multiline, long milliseconds) {
		for (int i = 0; i < multiline.length; i++) {
			System.out.println(multiline[i]);
			sleep(milliseconds);
		}
	}

	/**
	Exibe e números Pokémon em uma mesa de estilo
	
	@param pokemons ArrayList de Objetos de Pokemon
	 */
	
	
	public static void listPokemon (ArrayList<Pokemon> pokemons) {

		int limit = pokemons.size(); // Numero do Pokemon

		// Exibir dados em uma tabela formatada
		// Formatação especial para espaços necessários para a linha com números de um e dois dígitos
		
		System.out.println(limit != 1 ? "+---------------------------------+" : "+----------------+");

		for (int i = 0; i < limit; i++) {

			// Exibir dois nomes de Pokemons lado a lado
			if (i + 1 < limit) {

				if (i > 9) {
					delayedCharPrint(String.format("| %d. %10s | %d. %10s |", (i + 1), pokemons.get(i).toString(), (i + 2), pokemons.get(i + 1).toString()), 4);
				} else {
					if (i == 8) {
						delayedCharPrint(String.format("| %d. %11s | %d. %10s |", (i + 1), pokemons.get(i).toString(), (i + 2), pokemons.get(i + 1).toString()), 4);
					} else {
						delayedCharPrint(String.format("| %d. %11s | %d. %11s |", (i + 1), pokemons.get(i).toString(), (i + 2), pokemons.get(i + 1).toString()), 4);
					}
				}

				i += 1;
				System.out.println("+---------------------------------+");

			// Exibir uma linha com apenas  nome de 1 Pokémon
			} else {

				if (i > 9) {
					delayedCharPrint(String.format("| %d. %10s |", (i + 1), pokemons.get(i).toString()), 4);
					System.out.println("+----------------+");
				} else {
					delayedCharPrint(String.format("| %d. %11s |", (i + 1), pokemons.get(i).toString()), 4);
					System.out.println("+----------------+");
				}

				break;
			}

		}

	}

	/**
	 * Exibe um conjunto de opções em uma tabela agradável
	 * 
	 * @param options String [] de opções
	 * Mensagem @param Mensagem a ser exibida
	 */
	public static void listOptions (String[] options, String message) {

		int limit = options.length;

		delayedCharPrint(message, 30);

		// Cria Tabela
		System.out.println("+---------------------------------+");
		for (int i = 0; i < limit; i++) {
			if (i + 1 < limit) {
				delayedCharPrint(String.format("| %d. %11s | %d. %11s |", (i + 1), options[i], (i + 2), options[i + 1]), 5);
				i += 1;
				System.out.println("+---------------------------------+");
			}
		}

	}

	/**
	 * Método para obter uma entrada de string
	 * 
	 * 
     *@param toFind Usado para verificar se a verificação da entrada foi pressionada
	 *@param inline Boolean para chamar nova linha
	 *@return            Entrada de  string
	 */
	public static String getString (String toFind, String message, boolean inline) {

		String output = "";

		if (inline) {
			System.out.print(message);
		} else {
			delayedCharPrint(message, 30);
		}
		
		if (toFind.equals("#13")) {
			// Entrada de máscara para aparecer como somente Enter foi pressionado
			char[] hidden = console.readPassword("");
			output = "enter";
		} else {
			output = stdin.nextLine();
		}

		return output;
	}

	/**
	 * Método para obter uma entrada int
	 * 
	 * @param  min          Menor range para um numero
	 * @param  max          Maior range para um numero
	 * @param  message      Mensagem antes da entrada
	 * @return              O número válido entrou
	 */
	public static int getInt (int min, int max, String message) {

		int n; 

		while (true) {

			System.out.print(message);
			String input = stdin.nextLine();

			// Analisa a entrada como um número inteiro
			try {
				n = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Entre com um número!");
				continue; // Ir para a próxima iteração
			}

			// Se o número não for aceitável
			if (n < min || n > max) {
				System.out.printf("Isso não é uma opção! Digite um número no intervalo %d and %d!\n", min, max);
			} else {
				break; // Entrada válida
			}

		}

		return n;
	}

	/**
	 * Retorna verdadeiro ou falso, aleatoriamente
	 * 
	 * @return     verdadeiro ou falso
	 */
	public static boolean randChoice () {
		return rand.nextBoolean();
	}

	/**
	 *Limpa o console com base no sistema operacional
	 */
	public static void clearConsole () {

		// Limpe de forma diferente com base no sistema operacional usando o comando apropriado
		try {

			
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");  
				System.out.flush();
			}

		} catch (Exception e) {

			// Se tudo falhar, imprime algumas linhas em branco kkkkkkkk!
			for (int i = 0; i < 40; i++) {
				System.out.println();
			}
		}

	}

	/**
	 * Exibe opções de comando em um menu de ajuda
	 */
	public static void help () {

		delayedLinePrint(new String[] {
			"1. ATAQUE - Ataque ao inimigo",
			"2. TROCAR - Mude o seu Pokémon",
			"3. PULAR   - Passa a Vez",
			"4. STATUS   - Vizualiza as Estaticadas do pokemon",
			"5. POKEMON    - Visualize seu Pokemon"
		}, 20);

	}

}

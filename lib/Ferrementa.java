

import java.util.*;
import java.io.Console;

public class Ferrementa {

	private static Scanner stdin   = new Scanner(System.in);
	private static Console console = System.console();
	private static Random rand     = new Random();

	/**
	 * Pausa o programa por uma determinada dura��o
	 * 
	 * @param milliseconds    A dura��o, em milissegundos, para pausar o programa
	 */
	public static void sleep (long milliseconds) {

		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Imprime uma string para o console com um efeito de digita��o
	 * 
	 * @param contents         O conte�do a ser impresso
	 * @param  milliseconds    Dura��o para dormir em milissegundos
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
     * @param milissegundos Dura��o para dormir em milissegundos
	 */
	
	
	
	public static void delayedLinePrint (String[] multiline, long milliseconds) {
		for (int i = 0; i < multiline.length; i++) {
			System.out.println(multiline[i]);
			sleep(milliseconds);
		}
	}

	/**
	Exibe e n�meros Pok�mon em uma mesa de estilo
	
	@param pokemons ArrayList de Objetos de Pokemon
	 */
	
	
	public static void listPokemon (ArrayList<Pokemon> pokemons) {

		int limit = pokemons.size(); // Numero do Pokemon

		// Exibir dados em uma tabela formatada
		// Formata��o especial para espa�os necess�rios para a linha com n�meros de um e dois d�gitos
		
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

			// Exibir uma linha com apenas  nome de 1 Pok�mon
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
	 * Exibe um conjunto de op��es em uma tabela agrad�vel
	 * 
	 * @param options String [] de op��es
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
	 * M�todo para obter uma entrada de string
	 * 
	 * 
     *@param toFind Usado para verificar se a verifica��o da entrada foi pressionada
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
			// Entrada de m�scara para aparecer como somente Enter foi pressionado
			char[] hidden = console.readPassword("");
			output = "enter";
		} else {
			output = stdin.nextLine();
		}

		return output;
	}

	/**
	 * M�todo para obter uma entrada int
	 * 
	 * @param  min          Menor range para um numero
	 * @param  max          Maior range para um numero
	 * @param  message      Mensagem antes da entrada
	 * @return              O n�mero v�lido entrou
	 */
	public static int getInt (int min, int max, String message) {

		int n; 

		while (true) {

			System.out.print(message);
			String input = stdin.nextLine();

			// Analisa a entrada como um n�mero inteiro
			try {
				n = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Entre com um n�mero!");
				continue; // Ir para a pr�xima itera��o
			}

			// Se o n�mero n�o for aceit�vel
			if (n < min || n > max) {
				System.out.printf("Isso n�o � uma op��o! Digite um n�mero no intervalo %d and %d!\n", min, max);
			} else {
				break; // Entrada v�lida
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
	 * Exibe op��es de comando em um menu de ajuda
	 */
	public static void help () {

		delayedLinePrint(new String[] {
			"1. ATAQUE - Ataque ao inimigo",
			"2. TROCAR - Mude o seu Pok�mon",
			"3. PULAR   - Passa a Vez",
			"4. STATUS   - Vizualiza as Estaticadas do pokemon",
			"5. POKEMON    - Visualize seu Pokemon"
		}, 20);

	}

}

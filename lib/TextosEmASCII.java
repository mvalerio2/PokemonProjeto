

import java.util.*;
import java.io.*;

public class TextosEmASCII extends Ferrementa {

	// Sbre oque é o jogo
	private static String[] pokeTitle = { 
		"                               .::.                           ",
		"                              .;:**'                          ",
		"                              `                               ",
		"  .:XHHHHk.              db.   .;;.     dH  MX                ",
		"oMMMMMMMMMMM       ~MM  dMMP :MMMMMR   MMM  MR      ~MRMN     ",
		"QMMMMMb  'MMX       MMMMMMP !MX' :M~   MMM MMM  .oo. XMMM 'MMM",
		"  `MMMM.  )M> :X!Hk. MMMM   XMM.o'  .  MMMMMMM X?XMMM MMM>!MMP",
		"   'MMMb.dM! XM M'?M MMMMMX.`MMMMMMMM~ MM MMM XM `' MX MMXXMM ",
		"    ~MMMMM~ XMM. .XM XM`'MMMb.~*?**~ .MMX M t MMbooMM XMMMMMP ",
		"     ?MMM>  YMMMMMM! MM   `?MMRb.    `MM   !L'MMMMM XM IMMM   ",
		"      MMMX   'MMMM'  MM       ~%:           !Mh.''' dMI IMMP  ",
		"      'MMM.                                             IMX   ",
		"       ~M!M                                             IM    " 
	};

	//Nome do Joguinho
	private static String[] pokeSubtitle = {

			" OOOOO   PPPPPP  RRRRRR   OOOOO      JJJ EEEEEEE TTTTTTT  OOOOO",  
			"OO   OO  PP   PP RR   RR OO   OO     JJJ EE        TTT   OO   OO ",
			"OO   OO  PPPPPP  RRRRRR  OO   OO     JJJ EEEEE     TTT   OO   OO ",
			"OO   OO  PP      RR  RR  OO   OO JJ  JJJ EE        TTT   OO   OO ",
			" OOOO0   PP      RR   RR  OOOO0   JJJJJ  EEEEEEE   TTT    OOOO0 " 
	};

	// Parabens
	private static String[] congrats = {
			"PPPPPP    AAA   RRRRRR    AAA   BBBBB   EEEEEEE NN   NN  SSSSS     !!!",               
			"PP   PP  AAAAA  RR   RR  AAAAA  BB   B  EE      NNN  NN SS         !!!",               
			"PPPPPP  AA   AA RRRRRR  AA   AA BBBBBB  EEEEE   NN N NN  SSSSS     !!!",               
			"PP      AAAAAAA RR  RR  AAAAAAA BB   BB EE      NN  NNN      SS       ",               
			"PP      AA   AA RR   RR AA   AA BBBBBB  EEEEEEE NN   NN  SSSSS     !!!/n"   
	};

	//Voce Perdeu 
	private static String[] loser = {
			"VV     VV  OOOOO   CCCCC  EEEEEEE    PPPPPP  EEEEEEE RRRRRR  DDDDD   EEEEEEE UU   UU",
			"VV     VV OO   OO CC    C EE         PP   PP EE      RR   RR DD  DD  EE      UU   UU", 
			" VV   VV  OO   OO CC      EEEEE      PPPPPP  EEEEE   RRRRRR  DD   DD EEEEE   UU   UU", 
			"  VV VV   OO   OO CC    C EE         PP      EE      RR  RR  DD   DD EE      UU   UU", 
			"   VVV     OOOO0   CCCCC  EEEEEEE    PP      EEEEEEE RR   RR DDDDDD  EEEEEEE  UUUUU /n" 
	};

	
	/**
	 * Introdução para Titulos personalizados
	 */
	public static void introTitle () {
		delayedLinePrint(pokeTitle, 40);
		sleep(700);
		delayedLinePrint(pokeSubtitle, 40);
		sleep(500);
		System.out.printf("%27s", " ");
		delayedCharPrint("IFSP, Novembro 2017", 40);
		sleep(1000);
	}

	/**
	 *Imprime o Pokemon ASCII com base no nome do arquivo de dados
	 * 
	 * @param name     Nome do Pokemon
	 */
	public static void printPokemon (String name) {

		Scanner inFile = null;

		try {
			inFile = new Scanner(new BufferedReader(new FileReader(String.format("C:\\Users\\marcus\\Documents\\PokemonProjeto\\lib\\resources\\PokemonArt\\%s.txt", name))));
		} catch (IOException e) {
			delayedCharPrint(String.format("Desculpe, a imagem do  %s não está disponivel!", name), 40);
		}

		while (inFile.hasNextLine()) {
			String curLine = inFile.nextLine();
			delayedCharPrint(curLine, 1);
		}

		inFile.close();

	}

	/**
	 * ASCII Art para ganhar ou perder
	 * 
	 * @param won     Booleano para ver se o usuário ganhou ou não
	 */
	public static void gameOver (boolean won) {
		if (won) {
			delayedLinePrint(congrats, 40);
			delayedCharPrint("Você é o Mestre Pokemon!", 40);
			sleep(300);
		} else {
			delayedLinePrint(loser, 40);
			delayedCharPrint("Você Perdeu ,valeu pela participação!", 40);
			sleep(300);
		}
	}

}


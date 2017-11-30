 


import java.util.*;

public class Ataque extends Ferrementa {

	// Informações sobre cada ataque -> Constantes
	final String name;
	final int cost;
	final int damage;
	final String special;

	public Ataque (String name, int cost, int damage, String special) {
		this.name    = name;
		this.cost    = cost;
		this.damage  = damage;
		this.special = special;
	}

	/**
	 * Muda as estatísticas de Pokemon para ambos os Pokémon após um ataque
	 * 
	 * @param predator    Pokemon objeto de atacar pokemon
	 * @param prey         Pokémon objeto de defesa do pokemon
	 */
	public void attack (Pokemon predator, Pokemon prey) {

		// Armazena factor de dano de ataque em todas as condições
		int attackPower = damage;

		// Display attack name
		delayedCharPrint(String.format("\n%s usa %s contra %s", predator.toString(), name, prey.toString()), 40);

		predator.energy -= cost; // Exibir nome do ataque

		// Se o invasor estiver desativado
		if (predator.isDisabled) {

			// Informe o jogador desta tragédia
			delayedCharPrint(String.format("%s está desativado, então o ataque está enfraquecido!\n", predator.toString()), 40);

			// Tente diminuir 10 do poder de ataque
			attackPower = Math.max(0, attackPower - 10);
		}

		// Se o atacante ou o defensor tiver uma fraqueza
		if (predator.type.equals(prey.resistance)) {
			delayedCharPrint(String.format("\n Eita não :( ! %s é resistente a %s!\nO Ataque não teve efeito!", prey.toString(), name), 40);
			attackPower /= 2; // Corte o poder de ataque pela metade

		} else if (predator.type.equals(prey.weakness)) {
			delayedCharPrint(String.format("\n%s Tem uma fraquesa para  %s!\nO Ataque foi Super Efetivo!", prey.toString(), name), 40);
			attackPower *= 2; // Dobre o poder de ataque
		}
		
		// Use uma opção para lidar com especiais
		switch (special) {

			case "ATORDOAR":

				prey.hp -= attackPower; // Ataque

				// 50% de chance de sucesso para atordoar
				if (randChoice()) {
					prey.isStunned = true;
					delayedCharPrint(String.format("%s ficou atordoado!", prey.toString()), 40);
				}

				break;

			case "CARTÃO SELVAGEM/ WILD CARD":

				// 50% de chances de atacar com sucesso
				if (randChoice()) {
					prey.hp -= attackPower;
					delayedCharPrint(String.format("%s Tirou  %d de danos a %s!", predator.toString(), attackPower, prey.toString()), 40);
				} else {
					delayedCharPrint("O ataque perdeu!", 40);
				}

				break;

			case "TEMPESTADE SELVAGEM":

				// Continue até randChoice () retornar falso
				while (randChoice()) {
					prey.hp -= attackPower; // Ataque
					delayedCharPrint(String.format("%s Tirou %d de danos a %s!", predator.toString(), attackPower, prey.toString()), 40);
				}

				break;

			case "DESATIVAR":

				// Ataque e desative
				prey.hp -= attackPower;
				prey.isDisabled = true;

				delayedCharPrint(String.format("%s foi desabilitado!", prey.toString()), 40);
				delayedCharPrint(String.format("%s Tirou %d de dano a %s!", predator.toString(), attackPower, prey.toString()), 40);

				break;

			case "RECARREGAR":

				prey.hp -= attackPower;
				
				// Adicione 20 energia até o máximo
				predator.energy = Math.min(50, predator.energy + 20);
				delayedCharPrint(String.format("%s ganhou 20 energia!", predator.toString()), 40);

				delayedCharPrint(String.format("%s Tirou  %d de dano a %s!", predator.toString(), attackPower, prey.toString()), 40);

				break;

			// Nenhum Especial
			default:

				prey.hp -= attackPower;
				delayedCharPrint(String.format("%s Tirou %d de dano a %s!", predator.toString(), attackPower, prey.toString()), 40);

				break;

		}
	}

}

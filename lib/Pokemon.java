

import java.util.*;

public class Pokemon extends Ferrementa {

	int hp, energy;

	final String name;
	
	final int maxHP; 
	final String type;
	final String resistance;
	final String weakness;
	final ArrayList<Ataque> attacks;

	boolean isStunned = false;  
	boolean isDisabled = false; 

	public Random rand = new Random();

	// Construtor de Obejtos de Pokemon
	public Pokemon (String name, int hp, String type, String resistance, String weakness, ArrayList<Ataque> attacks) {
		this.name       = name;

		this.hp         = hp;
		this.maxHP      = hp;
		this.energy     = 50;
		
		this.type       = type;
		this.resistance = resistance;
		this.weakness   = weakness;
		
		this.attacks    = attacks;
	}

	/**
	 * Tabela de todos ataques pokemons
	 */
	public void listAttacks () {
		for (int i = 0; i < attacks.size(); i++) {
			delayedLinePrint(new String[] {
				String.format("%d. %s", (i + 1), attacks.get(i).name),
				String.format("COST    : %s", attacks.get(i).cost),
				String.format("DAMAGE  : %s", attacks.get(i).damage),
				String.format("SPECIAL : %s\n", attacks.get(i).special.equals("") ? "NONE" : attacks.get(i).special.toUpperCase())	
			}, 20);
		}
	}

	/**
	 * Verifica se o usuário pode pagar o ataque
	 * 
	 * @param attack     Ataca objeto
	 * @return          Booleano se o ataque puder ser oferecido
	 */
	public boolean canAfford (Ataque attack) {
		return attack.cost <= energy;
	}

	/**
	 * Gerar todos os ataques que Pokémon pode pagar
	 * 
	 * @return     ArrayList de Ataques
	 */
	public ArrayList<Ataque> affordableAttacks () {

		// ArrayList de ataques acessíveis
		ArrayList<Ataque> affordable = new ArrayList<Ataque>();

		for (int i = 0; i < attacks.size(); i++) {
			if (canAfford(attacks.get(i))) {
				affordable.add(attacks.get(i));
			}
		}

		return affordable;
	}

	/**
	 * Escolhe um ataque aleatório e acessível
	 * 
	 * @return     Ataque Randomico
	 */
	public Ataque randomAttack () {
		ArrayList<Ataque> affordables = affordableAttacks();
		return affordables.get(rand.nextInt(affordables.size()));
	}

	/**
	 * Verifica  se Pokémon estiver vivo
	 * 
	 * @return     Returns true  se Pokemon tem mais de 0 hp
	 */
	public boolean isAlive () {
		return hp > 0;
	}

	/**
	 * Restaura as estatísticas de Pokemon no final de uma volta
	 */
	public void resetTurn () {

		//Add 10 energia a um máximo de 50
		energy = Math.min(50, energy + 10);
	}

	/**
	 * Redefine as estatísticas de Pokemon no final de uma batalha
	 */
	public void resetBattle () {

		// Redefina energia para 50 e adicione 20 para hp
		energy = 50;
		hp = Math.min(maxHP, hp + 20);
	}

	/**
	 * Imprime estatísticas sobre Pokemon em uma tabela
	 *
	 * @param fancy     Booleano se a tabela deve ser impressa
	 * @return          String com PV e energia
	 */
	public void stats (boolean fancy) {
		if (fancy) {
			delayedLinePrint(new String[] {
				"\n+-----------------------------------------------+",
				"|                    STATUS                     |",
				"+===============================================+",
				String.format("| PV: %5d | ENERGIA : %5d | TIPO: %10s |", hp, energy, type.toUpperCase()),
				"+-----------------------------------------------+\n"
			}, 20);
		} else {
			delayedCharPrint(String.format("\nPOKEMON : %s\nPV      : %d\nENERGIA  : %d\nTIPO    : %s", name, hp, energy, type.toUpperCase()), 30);
		}
	}

	/**
	 * @return     Nome do Pokemon
	 */
	public String toString () {
		return name;
	}
	
	


}

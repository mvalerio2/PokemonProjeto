import java.util.ArrayList;

public class RoundRobin {

	public RoundRobin(ArrayList<Pokemon> listaPokemon ) {
		
		int ultimoPokemon = listaPokemon.size()-1; 
		for (int i = 0; i < listaPokemon.size()-1;i++) {
			for (int j = 0; j < listaPokemon.size()/2;j++) {
				System.out.println(listaPokemon.get(j).name+ " Versus "+ listaPokemon.get(ultimoPokemon).name );
				
				if (ultimoPokemon > listaPokemon.size()/2) {
					ultimoPokemon--;
				}
				
			}
			
		}
		System.out.println("Acima a Lista Round Robin de Batalhas, Conforme pedido porem O jogo esolhe pokemons Randomicamente para a Batalha");
		
	}
	

}

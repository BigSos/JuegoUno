import java.util.ArrayList;
class Mano{
	private ArrayList<Carta> mano;
	private ArrayList<Integer> manoNumerica;

	Mano(){
		mano = new ArrayList<Carta>();
		manoNumerica = new ArrayList<Integer>();
	}

	public void agregarCarta(Carta carta,int cartaNumero){
		mano.add(carta);
		manoNumerica.add(cartaNumero);
	}
	public void borrarCarta(int posicion){
		mano.remove(posicion);
		manoNumerica.remove(posicion);
	}
	public int buscarCarta(int cartaNumero){
		return manoNumerica.indexOf(cartaNumero);
	}
	public Carta getCarta(int posicion){
		return mano.get(posicion);
	}
	public int getNumeroCarta(int posicion){
		return manoNumerica.get(posicion);
	}
	public int largo(){
		return mano.size();
	}

}
class Uno{
	

	public static void main(String [] args){
		int cantidadJugadores=2;
		Juego partida;

		//Agregar instrucciones para la creación de un juego

		partida = new Juego(cantidadJugadores);
		partida.jugar();

	}
}
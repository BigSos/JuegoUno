class Naipe{
	private Carta [] naipe;
	private int [] jugadorAsignado;

	Naipe(){
		naipe = new Carta[Config.cantidadCartasNaipe];
		jugadorAsignado = new int[Config.cantidadCartasNaipe];

		for(int i=0;i<Config.cantidadCartasNaipe;i++){			
			jugadorAsignado[i]=-1;
		}
		
		int n=0;
		for(int i=0;i<10;i++){			
			naipe[n]=new Carta("Rojo",Integer.toString(i));
			n++;
		}
		for(int i=0;i<10;i++){			
			naipe[n]=new Carta("Azul",Integer.toString(i));
			n++;
		}
		for(int i=0;i<10;i++){			
			naipe[n]=new Carta("Amarillo",Integer.toString(i));
			n++;
		}
		for(int i=0;i<10;i++){			
			naipe[n]=new Carta("Verde",Integer.toString(i));
			n++;
		}
		for(int i=1;i<10;i++){			
			naipe[n]=new Carta("Rojo",Integer.toString(i));
			n++;
		}
		for(int i=1;i<10;i++){			
			naipe[n]=new Carta("Azul",Integer.toString(i));
			n++;
		}
		for(int i=1;i<10;i++){			
			naipe[n]=new Carta("Amarillo",Integer.toString(i));
			n++;
		}
		for(int i=1;i<10;i++){			
			naipe[n]=new Carta("Verde",Integer.toString(i));
			n++;
		}
		for(int i=1;i<5;i++){			
			naipe[n]=new Carta("Especial","Color");
			n++;
		}
		for(int i=1;i<5;i++){			
			naipe[n]=new Carta("Especial","+4");
			n++;
		}
		for(int i=1;i<3;i++){			
			naipe[n]=new Carta("Rojo","+2");
			n++;
		}
		for(int i=1;i<3;i++){			
			naipe[n]=new Carta("Azul","+2");
			n++;
		}
		for(int i=1;i<3;i++){			
			naipe[n]=new Carta("Amarillo","+2");
			n++;
		}
		for(int i=1;i<3;i++){			
			naipe[n]=new Carta("Verde","+2");
			n++;
		}
		for(int i=1;i<3;i++){
			naipe[n]=new Carta("Rojo","Sentido");
			n++;
		}
		for(int i=1;i<3;i++){			
			naipe[n]=new Carta("Azul","Sentido");
			n++;
		}
		for(int i=1;i<3;i++){			
			naipe[n]=new Carta("Amarillo","Sentido");
			n++;
		}
		for(int i=1;i<3;i++){			
			naipe[n]=new Carta("Verde","Sentido");
			n++;
		}
		for(int i=1;i<3;i++){
			naipe[n]=new Carta("Rojo","Bloqueo");
			n++;
		}
		for(int i=1;i<3;i++){			
			naipe[n]=new Carta("Azul","Bloqueo");
			n++;
		}
		for(int i=1;i<3;i++){			
			naipe[n]=new Carta("Amarillo","Bloqueo");
			n++;
		}
		for(int i=1;i<3;i++){			
			naipe[n]=new Carta("Verde","Bloqueo");
			n++;
		}

	}
	public void setJugadorAsignado(int cartaNumero, int jugador){
		this.jugadorAsignado[cartaNumero]=jugador;
	}
	public int getJugadorAsignado(int cartaNumero){
		return this.jugadorAsignado[cartaNumero];
	}
	public Carta getCarta(int cartaNumero){
		return this.naipe[cartaNumero];
	}
	public void setUso(int cartaNumero){
		this.naipe[cartaNumero].setUso(true);
	}
}
import java.util.Scanner;

class Juego{
	private Naipe mazo;
	private int cantidadJugadores;
	private Jugador [] jugadores;
	private int cantidadCartasMano= Config.cantidadCartasMano;
	private int cantidadCartasTotal=Config.cantidadCartasNaipe;
	private int cantidadCartasRestantes;
	private int cartaNumeroPozo;
        private int plusTwo=0;
        private int plusFour=0;

	Juego(int cantidadJugadores){
		this.mazo=new Naipe();
		if(cantidadJugadores>4){
			System.out.println("El máximo es 4 jugadores!");
			this.cantidadJugadores=4;
		}else if(cantidadJugadores<=1){
			System.out.println("El mínimo es 1 jugador contra el computador!");
			this.cantidadJugadores=2;
		}else{
			this.cantidadJugadores=cantidadJugadores;
		}
		this.jugadores = new Jugador[this.cantidadJugadores];
		this.cantidadCartasRestantes=0;

		asignarJugadores();
		repartir();		
	}
	//************************************************************************//
	// Métodos privados asociados a la creación de un Juego
	// Método para asignar turnos a jugadores, se llama al crear un Juego
	private void asignarJugadores(){
		Scanner teclado = new Scanner(System.in);
		boolean [] turnos= new boolean[4];
		boolean flag;
		String nombre;
		String pais;
		int turno;

		for(int i=1;i<=this.cantidadJugadores;i++){
			flag=false;
			turno=1;
			if(i==1){
				System.out.println("Ingrese nombre");
				nombre=teclado.nextLine();
				System.out.println("Ingrese pais");
				pais=teclado.nextLine();
			}else{
				nombre="Jugador"+i;
				pais="Algun lugar del mundo";
			}
			while(!flag){
				turno = (int)(Math.random()*10);
				if(((turno<this.cantidadJugadores)&&(turno>=0))&&(!turnos[turno])){
					flag=true;
					turnos[turno]=true;
					turno=turno+1;
				}
			}
			jugadores[i-1]=new Jugador(nombre,pais,turno);
		}
	}
	// Método que genera las manos iniciales para cada jugador, 
	// se llama al crear un Juego
	private void repartir(){
		boolean flag=false;
		int cartaNumero=-1;

		for(int j=0;j<this.cantidadJugadores;j++){
			for(int i=1;i<=this.cantidadCartasMano;i++){
				flag=false;
				while(!flag){
					cartaNumero = (int)(Math.random()*1000);
					cartaNumero = cartaNumero%this.cantidadCartasTotal;
					if(((cartaNumero<Config.cantidadCartasNaipe)&&(cartaNumero>=0))&&(mazo.getJugadorAsignado(cartaNumero)==-1)){
						flag=true;
						jugadores[j].getMano().agregarCarta(mazo.getCarta(cartaNumero),cartaNumero);
						mazo.setJugadorAsignado(cartaNumero,j);
					}
				}
			}	
		}		
	}
	//************************************************************************//
	// Método que es llamado para jugar un Juego creado
	public void jugar(){
		Scanner teclado = new Scanner(System.in);
		int carta;
		boolean ganador=false;

		this.cartaNumeroPozo=generarCarta();
		// Acá se debe modificar para terminar el juego
		// Por ahora está en un ciclo infinito
		// se muestra la carta del pozo y se juega por el usuario
		// y luego juega el computador, está solo con dos jugadores
                if (mazo.getCarta(cartaNumeroPozo).getColor().equals("Especial")){// Según las reglas si al comenzar aparece una carta de cambio de color se regresa al mazo y se saca otra carta. https://ep01.epimg.net/verne/imagenes/2019/05/11/articulo/1557592044_483049_1557658849_sumario_normal.jpg
                    while (mazo.getCarta(cartaNumeroPozo).getColor().equals("Especial"))
                        this.cartaNumeroPozo=generarCarta();
                }
                if (mazo.getCarta(cartaNumeroPozo).getValor().equals("+2"))
                    this.plusTwo=1;
                                            
		while(!ganador){
			System.out.print("\033[H\033[2J");
                        System.out.flush();                       
			System.out.println("La carta del pozo es:");
			mostrarPozo(mazo.getCarta(this.cartaNumeroPozo));
                        if (jugadores[1].getMano().largo()==0){
                            System.out.println("HAS PERDIDO.");
                            System.out.println("FIN DE LA PARTIDA.");
                            ganador=true;
                            continue;
                        }                        
			System.out.println("Presione enter para continuar");
			teclado.nextLine();



			//Jugada del jugador usuario
                        //Valida si hay una carta de +2 o +4
                        if (this.plusFour>0 || this.plusTwo>0)
                            jugadaSP(0);
                        else
                            jugada(0);                         
			System.out.println("La carta del pozo es:");
			mostrarPozo(mazo.getCarta(this.cartaNumeroPozo));
                        if (jugadores[0].getMano().largo()==0){
                            System.out.println("HAS GANADO!.");
                            System.out.println("FIN DE LA PARTIDA.");
                            ganador=true;
                            continue;
                        }                        
			System.out.println("Presione enter para continuar");			
			teclado.nextLine();


			
			//Jugada del jugador Computador
                        //Valida si hay una carta de +2 o +4
                        if (this.plusFour>0 || this.plusTwo>0)
                            jugadaSP(1);
                        else
                            jugada(1);                        
			//System.out.print("\033[H\033[2J");
        	//System.out.flush();
		}
	}

	// Método que selecciona una carta de forma aleatoria de un mazo
	// retorna el número de la carta entre los valores 0 y 107
	private int generarCarta(){
		int cartaNumero=-1;
		boolean flag=false;

		while(!flag){
			cartaNumero = (int)(Math.random()*1000);
			cartaNumero = cartaNumero%108;
			if(((cartaNumero<Config.cantidadCartasNaipe)&&(cartaNumero>=0))&&(mazo.getJugadorAsignado(cartaNumero)==-1)){
				flag=true;
			}
		}
		return cartaNumero;
	}

	// Método que realiza una jugada para un jugador, ya sea usuario o computador
	// recibe el número del jugador, 0 para el usuario y 1,2 o 3 para computador 
	private void jugada(int jugador){
		int cartaNumero=-1;
		Carta carta;
		boolean flag=false;
		Scanner teclado = new Scanner(System.in);
		//valida que el jugador tenga al menos una carta válida para jugar
		if(validarMano(jugadores[jugador].getMano())){
			System.out.println("Es el turno del jugador "+jugador);
			if(jugador==0){
                            if (jugadores[jugador].getMano().largo()==1)
                                    System.out.println("UNO!");
				while(!flag){ //repite hasta que seleccione una carta válida
					cartaNumero=seleccionarCarta();
					if(validarJugada(cartaNumero)){
                                            if(mazo.getCarta(cartaNumero).getValor().equals("+2"))
                                                this.plusTwo+=1;
                                            if(mazo.getCarta(cartaNumero).getValor().equals("+4"))
                                                this.plusFour+=1;
                                            
                                            flag=true;
					}else{
						System.out.println("No puede jugar esa carta");
					}
				}
			}else{
				if (jugadores[jugador].getMano().largo()==1){
                                    System.out.println("UNO!");
                                }
                                cartaNumero=generarJugada(jugador);
                                if(mazo.getCarta(cartaNumero).getValor().equals("+2"))
                                    this.plusTwo+=1;
                                if(mazo.getCarta(cartaNumero).getValor().equals("+4"))
                                    this.plusFour+=1;
                                
			}
			this.cartaNumeroPozo=cartaNumero;//
			this.mazo.setUso(cartaNumero);//
			this.jugadores[jugador].getMano().borrarCarta(this.jugadores[jugador].getMano().buscarCarta(cartaNumero));
			cantidadCartasRestantes--;
			System.out.println("Presione enter para continuar");
			teclado.nextLine();			
		}else{
			System.out.println("El jugador "+jugador+" no tiene carta para jugar, roba una carta");
			cartaNumero=generarCarta();
			robarCarta(jugador,cartaNumero);
			if(jugador==0)
				mostrarMano(jugador);
		}
		carta=this.mazo.getCarta(this.cartaNumeroPozo);
		//mostrarPozo(carta);		
	}
        
        private void jugadaSP(int jugador){
            int cartaNumero=-1;
            boolean flag=false;
            Scanner teclado = new Scanner(System.in);
            if (validarManoSP(jugadores[jugador].getMano())){
                System.out.println("Es el turno del jugador "+jugador);
		if(jugador==0){
                    if (jugadores[0].getMano().largo()==1)
                        System.out.println("UNO!");
                    while(!flag){ //repite hasta que seleccione una carta válida
                        cartaNumero=seleccionarCarta();
                        if(validarJugadaSP(cartaNumero)){
                            if(mazo.getCarta(cartaNumero).getValor().equals("+2"))
                                this.plusTwo+=1;
                            if(mazo.getCarta(cartaNumero).getValor().equals("+4"))
                                this.plusFour+=1;
                            
                            flag=true;
                        }
                        else{
                            System.out.println("No puede jugar esa carta");
                        }
                    }
                }
                else{
                    if (jugadores[jugador].getMano().largo()==1){
                        System.out.println("UNO!");
                    }
                    cartaNumero=generarJugadaSP(jugador);
                    if(mazo.getCarta(cartaNumero).getValor().equals("+2"))
                        this.plusTwo+=1;
                    if(mazo.getCarta(cartaNumero).getValor().equals("+4"))
                        this.plusFour+=1;
                    
                }
		this.cartaNumeroPozo=cartaNumero;//
		this.mazo.setUso(cartaNumero);//
		this.jugadores[jugador].getMano().borrarCarta(this.jugadores[jugador].getMano().buscarCarta(cartaNumero));
		cantidadCartasRestantes--;
		System.out.println("Presione enter para continuar");
		teclado.nextLine(); 
            } 
            else {
                if (this.plusTwo>0){
                    System.out.println("El jugador "+jugador+" roba "+(2*plusTwo)+" cartas y pierde su turno.");
                    for (int i=0;i<2*plusTwo;i++){
                        cartaNumero=generarCarta();
                        robarCarta(jugador,cartaNumero);
                    }
                    this.plusTwo=0;
                }
                else {
                    if (this.plusFour>0){
                        System.out.println("El jugador "+jugador+" roba "+(4*plusFour)+" cartas y pierde su turno.");
                        for (int i=0;i<4*plusFour;i++){
                            cartaNumero=generarCarta();
                            robarCarta(jugador,cartaNumero);
                        }
                        this.plusFour=0;
                    }
                }
            }
        }
	// Método que escoge una carta de la mano de un jugador de forma aleatoria
	// retorna el número de la carta
	public int generarJugada(int jugador){
		boolean flag=false;
		int posicion;
		int cartaNumero;
		int largo=jugadores[jugador].getMano().largo();

		while(!flag){
			posicion = (int)(Math.random()*1000);
			posicion = posicion%largo;
			cartaNumero = jugadores[jugador].getMano().getNumeroCarta(posicion);
			if(validarJugada(cartaNumero)){
				flag=true;
				return cartaNumero;
			}
		}
		return -1;
	}

	public int generarJugadaSP(int jugador){
		boolean flag=false;
		int posicion;
		int cartaNumero;
		int largo=jugadores[jugador].getMano().largo();

		while(!flag){
			posicion = (int)(Math.random()*1000);
			posicion = posicion%largo;
			cartaNumero = jugadores[jugador].getMano().getNumeroCarta(posicion);
			if(validarJugadaSP(cartaNumero)){
				flag=true;
				return cartaNumero;
			}
		}
		return -1;
	}        
	// Método para mostrar la carta en el tope del pozo
	private void mostrarPozo(Carta carta){
		System.out.println("************************************");
		mostrarCarta(carta,0);
		System.out.println("************************************");
	}

	// Método para mostrar una mano de un jugador
	private void mostrarMano(int jugador){
		String texto;
		int largo;
		System.out.println("Tu mano es:");
		System.out.println("************************************");
		for(int i=0;i<jugadores[jugador].getMano().largo();i++){
			mostrarCarta(jugadores[jugador].getMano().getCarta(i),i+1);
		}
		System.out.println("************************************");
	}

	// Método para mostrar una carta en pantalla
	public void mostrarCarta(Carta carta, int numero){
		String texto;
		int largo;
		texto=numero+". "+carta.getValor()+"-"+carta.getColor();
		largo=texto.length();

			for(int j=0;j<largo+3;j++){
				if(j<4)
					System.out.print(" ");
				else
					System.out.print("-");
			}
			System.out.println("\n"+numero+". | "+carta.getValor()+"-"+carta.getColor()+" |");
			for(int j=0;j<largo+3;j++){
				if(j<4)
					System.out.print(" ");
				else
					System.out.print("-");
			}
			System.out.println("\n");
	}

	// Método para validar si una jugada es válida, se recibe la carta y
	// se revisa si tiene el mismo color, valor o si es carta especial
	// retorna verdadero si se cumple una o más condiciones
	public boolean validarJugada(int cartaNumeroJugada){
		boolean flag=false;
		if(!flag){

			if(mazo.getCarta(cartaNumeroJugada).getValor().compareTo(mazo.getCarta(this.cartaNumeroPozo).getValor())==0){
				//System.out.println("Jugada por valor");
                                flag=true;
			}
			if(mazo.getCarta(cartaNumeroJugada).getColor().compareTo(mazo.getCarta(this.cartaNumeroPozo).getColor())==0){
				//System.out.println("Jugada por color");
                                flag=true;
			}
			if(mazo.getCarta(cartaNumeroJugada).getColor().compareTo("Especial")==0){
				//System.out.println("Jugada por carta especial");
				flag=true;
			}
		}
		return flag;
	}
        
        public boolean validarJugadaSP(int cartaNumeroJugada){
		boolean flag=false;
		if(!flag){

			if(mazo.getCarta(cartaNumeroJugada).getValor().compareTo(mazo.getCarta(this.cartaNumeroPozo).getValor())==0){
				//System.out.println("Jugada por valor");
                                flag=true;
			}
		}
		return flag;
	}
	// Método que valida si el jugador posee al menos una carta en su
	// mano para poder jugar, retorna verdadero si cumple
	private boolean validarMano(Mano mano){
		int largo = mano.largo();
		for(int i=0;i<largo;i++){
			if(validarJugada(mano.getNumeroCarta(i))){
				return true;
			}
		}
		return false;
	}
        
	private boolean validarManoSP(Mano mano){
		int largo = mano.largo();
		for(int i=0;i<largo;i++){
			if(validarJugadaSP(mano.getNumeroCarta(i))){
				return true;
			}
		}
		return false;
	}        
	// Método permite a un jugador no computador seleccionar la carta que desea
	// jugar, se retorna el número de la carta seleccionada de su mano
	private int seleccionarCarta(){//limitar el numero al largo de la mano 
		Scanner teclado = new Scanner(System.in);
		String input;
		int carta;
		//jugador 0 es el jugador no computador
		mostrarMano(0);
		System.out.println("Seleccione la carta a jugar");
		System.out.println("Ingrese el número de la carta");
		input=teclado.nextLine();
		carta = Integer.parseInt(input);
		return jugadores[0].getMano().getNumeroCarta(carta-1);
	}
        
        // Método que asigna una carta a un jugador y la agrega a su mano
        private void robarCarta(int jugador,int cartaNumero){
		this.jugadores[jugador].getMano().agregarCarta(this.mazo.getCarta(cartaNumero),cartaNumero);
		this.mazo.setJugadorAsignado(cartaNumero,jugador);
	}
}

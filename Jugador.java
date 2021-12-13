class Jugador{
	private String nickname;
	private String pais;
	private int turno;
	private Mano mano;
	//private String tipo; //Humano o Computador
	private boolean humano; //Humano o Computador

	Jugador(){
		this.nickname="Jugador 1";
		this.pais="Algún lugar del mundo";
		//this.tipo="Computador";
		this.mano=new Mano();
		this.humano=false;
	}
	Jugador(String nombre, String pais,int turno){
		this.nickname=nombre;
		this.pais=pais;
		this.turno=turno;
		this.mano=new Mano();
		//this.tipo="Humano";
		this.humano=true;
	}
	public void setTurno(int turno){
		this.turno=turno;
	}
	public int getTurno(){
		return this.turno;
	}
	public Mano getMano(){
		return this.mano;
	}


}
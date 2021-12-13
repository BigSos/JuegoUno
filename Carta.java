class Carta{
	private String color;
	private String valor;
	private boolean uso;

	Carta(String color, String valor){
		this.color=color;
		this.valor=valor;
		this.uso=false;
	}

	public String getColor(){
		return this.color;
	}
	public String getValor(){
		return this.valor;
	}
	public boolean getUso(){
		return this.uso;
	}
	public void setColor(String color){
		this.color=color;
	}
	public void setValor(String valor){
		this.valor=valor;
	}
	public void setUso(boolean uso){
		this.uso=uso;
	}
}
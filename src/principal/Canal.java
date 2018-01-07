package principal;

import interfaz.BDConnection;

public class Canal {

	private double valorMedio;
	private double valorInicial;
	private double valorFinal;

	private int id;

	private String nombre; // Nombre de la medida
	private String medida; // Magnitud de la medida

	private CurvaOriginal curva; // Curva a la que pertenece el canal

	//-------Constructor

	public Canal(CurvaOriginal c,int i,double vm, double vi,double vf, String name, String med) throws ClassNotFoundException {
		if(i < 0) {
			throw new RuntimeException("Valor del identificador negativo");
		}
		valorMedio = vm;
		valorInicial = vi;
		valorFinal = vf;
		id = i;
		nombre = name;
		medida = med;
		curva=c;

		BDConnection miBD = new BDConnection();
		miBD.Insert("INSERT INTO CANAL VALUES("+i+","+vi+","+vm+","+vf+",'"+name+"','"+med+"','"+c.getFechaHora()+"','"+c.getCampName()+"','"+c.getModName()+"');");
	}
	
	public Canal(){ // Este carga los valores
		
	}

	//------Getters

	public double getValorMedio() {
		return valorMedio;
	}
	public double getValorInicial() {
		return valorInicial;
	}
	public double getValorFinal() {
		return valorFinal;
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}

	public String getMedida() {
		return medida;
	}

	public CurvaOriginal getCurvaOriginal(){
		return curva;
	}

	//-------Setters

	public void setValorMedio(double valorMedio) throws ClassNotFoundException {
		this.valorMedio = valorMedio;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE CANAL SET valorMedio = "+valorMedio+" WHERE curvaOriginal_fechaHoraCurva LIKE '"+curva.getFechaHora()+"';");
	}

	public void setValorInicial(double valorInicial) throws ClassNotFoundException {
		this.valorInicial = valorInicial;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE CANAL SET valorInicial = "+valorInicial+" WHERE curvaOriginal_fechaHoraCurva LIKE '"+curva.getFechaHora()+"';");
	}

	public void setValorFinal(double valorFinal) throws ClassNotFoundException {
		this.valorFinal = valorFinal;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE CANAL SET valorFinal = "+valorFinal+" WHERE curvaOriginal_fechaHoraCurva LIKE '"+curva.getFechaHora()+"';");
	}

	public void setId(int i) throws ClassNotFoundException {
		if(i < 0) {
			throw new RuntimeException("Valor negativo");
		}
		this.id = i;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE CANAL SET idCanal = "+i+" WHERE curvaOriginal_fechaHoraCurva LIKE '"+curva.getFechaHora()+"';");
	}

	public void setNombre(String nombre) throws ClassNotFoundException {
		this.nombre = nombre;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE CANAL SET nombreMedida = "+nombre+" WHERE curvaOriginal_fechaHoraCurva LIKE '"+curva.getFechaHora()+"';");
	}

	public void setMedida(String medida) throws ClassNotFoundException {
		this.medida = medida;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE CANAL SET magnitudMedida = "+medida+" WHERE curvaOriginal_fechaHoraCurva LIKE '"+curva.getFechaHora()+"';");
	}

	public void setCurvaOriginal(CurvaOriginal c){
		this.curva = c;
	}

}

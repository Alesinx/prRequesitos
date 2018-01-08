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
		miBD.Insert("INSERT INTO Canal VALUES("+i+","+vi+","+vm+","+vf+",'"+name+"','"+med+"','"+c.getFechaHora()+"','"+c.getCampName()+"','"+c.getModName()+"');");
	}

	public Canal(String nombre, String fechaHora, String camp, String mod) throws ClassNotFoundException {

		BDConnection miBD = new BDConnection();
		Object [] aux = miBD.Select("SELECT * FROM Canal WHERE nombreMedida LIKE '"+nombre+"' AND curvaOriginal_fechaHoraCurva LIKE '"+fechaHora+"';").get(0);

		this.id = Integer.parseInt(aux[0].toString());
		this.valorInicial = Double.parseDouble(aux[1].toString());
		this.valorMedio = Double.parseDouble(aux[2].toString());
		this.valorFinal = Double.parseDouble(aux[3].toString());
		this.nombre = aux[4].toString();
		this.medida = aux[5].toString();

		this.curva=null;
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


	// -- BORRAR CANAL

	public void Borrar() throws ClassNotFoundException{
		BDConnection miBD = new BDConnection();
		miBD.Delete("DELETE FROM Canal WHERE idCanal = "+this.id+" AND curvaOriginal_fechaHoraCurva LIKE '"+this.curva.getFechaHora()+"';");

		this.id = -1;
		this.valorInicial = -1;
		this.valorMedio = -1;
		this.valorFinal = -1;
		this.nombre = "";
		this.medida = "";

		this.curva=null;
	}

}

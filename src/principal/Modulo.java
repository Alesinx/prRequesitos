package principal;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import interfaz.BDConnection;

public class Modulo {

	private String nombre;
	private String tecnologia;

	private ArrayList<CurvaOriginal> curvas;

	private double alfa;
	private double beta;
	private double gamma;
	private double kappa;

	private double temperaturaNOCT;
	private double iscNOCT;
	private double vocNOCT;
	private double pmaxNOCT;
	private double ipmaxNOCT;
	private double vpmaxNOCT;

	private double rendimiento;
	private double idealidad;
	private double resistencia;

	private double minISC;
	private double minVOC;
	private double minPMAX;
	private double minFF;

	private int celSerie;
	private int celParalelo;

	public Modulo(){
		nombre = "";
		curvas = null;
	}

	public Modulo(String name) throws ClassNotFoundException { // FALTA CARGAR LOS NUEVOS ATRIBUTOS
		BDConnection baseDatos = new BDConnection();
		nombre=name;
		for(Object[] elemento : baseDatos.Select("SELECT * FROM MODULO WHERE nombreModulo LIKE '"+name+"';")){

			alfa = (Double.parseDouble(elemento[1].toString()));
			beta = (Double.parseDouble(elemento[2].toString()));
			gamma = (Double.parseDouble(elemento[3].toString()));
			kappa = (Double.parseDouble(elemento[4].toString()));
			tecnologia = elemento[5].toString();
			temperaturaNOCT = Double.parseDouble(elemento[6].toString());

			iscNOCT= Double.parseDouble(elemento[7].toString());
			vocNOCT=Double.parseDouble(elemento[8].toString());
			pmaxNOCT = Double.parseDouble(elemento[9].toString());
			ipmaxNOCT=Double.parseDouble(elemento[10].toString());
			vpmaxNOCT=Double.parseDouble(elemento[11].toString());

			rendimiento=Double.parseDouble(elemento[12].toString());
			resistencia=Double.parseDouble(elemento[14].toString());
			idealidad=Double.parseDouble(elemento[13].toString());

			minISC=Double.parseDouble(elemento[15].toString());
			minVOC=Double.parseDouble(elemento[16].toString());
			minFF=Double.parseDouble(elemento[18].toString());
			minPMAX=Double.parseDouble(elemento[17].toString());

			celSerie = Integer.parseInt(elemento[19].toString());
			celSerie =Integer.parseInt(elemento[20].toString());

		}
	}

	public Modulo(String name,double a,double b,double g,double k){
		this.nombre = name;
		this.tecnologia = "";

		this.alfa = a;
		this.beta = b;
		this.gamma = g;
		this.kappa = k;

		curvas = new ArrayList<>();

		this.temperaturaNOCT = 0;
		this.iscNOCT = 0;
		this.vocNOCT = 0;
		this.pmaxNOCT = 0;
		this.ipmaxNOCT = 0;
		this.vpmaxNOCT = 0;

		this.rendimiento = 0;
		this.idealidad = 0;
		this.resistencia = 0;

		this.minISC = 0;
		this.minVOC= 0;
		this.minPMAX = 0;
		this.minFF = 0;

		this.celSerie=0;
		this.celParalelo=0;
	}

	public Modulo(String n, String tec, double a, double b, double g, double k, double tempN,double iscN,double vocN,double pmaxN,double ipmaxN
			, double vpmaxN,double rend,double ide,double res,double iscM,double vocM,double pmaxM,double ffM,int celS,int celP) throws ClassNotFoundException{

		this.nombre = n;
		this.tecnologia = tec;

		this.alfa = a;
		this.beta = b;
		this.gamma = g;
		this.kappa = k;

		curvas = new ArrayList<>();

		this.temperaturaNOCT = tempN;
		this.iscNOCT = iscN;
		this.vocNOCT = vocN;
		this.pmaxNOCT = pmaxN;
		this.ipmaxNOCT = ipmaxN;
		this.vpmaxNOCT = vpmaxN;

		this.rendimiento = rend;
		this.idealidad = ide;
		this.resistencia = res;

		this.minISC = iscM;
		this.minVOC= vocM;
		this.minPMAX = pmaxM;
		this.minFF = ffM;

		this.celSerie=celS;
		this.celParalelo=celP;

		BDConnection baseDatos = new BDConnection();
		baseDatos.Insert("INSERT INTO Modulo values ('"+n+"', '"+a+"', '"+b+"', '"+g+"', '"+k+"' ,'"+tecnologia+"' ,'"+temperaturaNOCT+"' ,'"+iscNOCT+"','"+vocNOCT+"','"+pmaxNOCT+"','"+ipmaxNOCT+"','"+vpmaxNOCT+"','"+rendimiento+"','"+idealidad+"','"+resistencia+"','"+minISC+"','"+minVOC+"','"+minPMAX+"','"+minFF+"','"+celSerie+"','"+celParalelo+"');");

	}
	public Modulo(String n, String tec, double a, double b, double g, double k,ArrayList<CurvaOriginal> list, double tempN,double iscN,double vocN,double pmaxN,double ipmaxN
			, double vpmaxN,double rend,double ide,double res,double iscM,double vocM,double pmaxM,double ffM,int celS,int celP){

		this.nombre = n;
		this.tecnologia = tec;

		this.alfa = a;
		this.beta = b;
		this.gamma = g;
		this.kappa = k;

		this.curvas = list;

		this.temperaturaNOCT = tempN;
		this.iscNOCT = iscN;
		this.vocNOCT = vocN;
		this.pmaxNOCT = pmaxN;
		this.ipmaxNOCT = ipmaxN;
		this.vpmaxNOCT = vpmaxN;

		this.rendimiento = rend;
		this.idealidad = ide;
		this.resistencia = res;

		this.minISC = iscM;
		this.minVOC= vocM;
		this.minPMAX = pmaxM;
		this.minFF = ffM;

		this.celSerie=celS;
		this.celParalelo=celP;


	}
	public Modulo(String name,ArrayList<CurvaOriginal> list) throws ClassNotFoundException{

		this.nombre = name;
		curvas = list;
		BDConnection miBD = new BDConnection();
		Iterator<CurvaOriginal> iter = list.iterator();

		while(iter.hasNext()){
			CurvaOriginal aux = (CurvaOriginal) iter.next();
			double isc = aux.getIsc();
			double voc = aux.getVoc();
			double pmax = aux.getPmax();
			String fecha = aux.getFecha();
			double ff = aux.getFF();
			double ipmax = aux.getIPmax();
			double vpmax = aux.getVPmax();
			miBD.Insert("INSERT INTO MODULO VALUES('"+name+"');");
			miBD.Insert("INSERT INTO CURVAORIGINAL VALUES ('"+name+"','"+fecha+"','"+isc+"','"+voc+"','"+pmax+"','"+ipmax+"','"+vpmax+"','"+ff+"')");
		}

	}

	// ---- Metodo para listar los modulos de la BD

	public static List<Modulo> ListaModulo() throws ClassNotFoundException { // EXTENDER METODO CON LOS NUEVOS ATRIBUTOS
		ArrayList<Modulo> lista = new ArrayList<Modulo>();
		BDConnection baseDatos = new BDConnection();
		for(Object[] elemento : baseDatos.Select("SELECT * FROM MODULO;")){
			Modulo mod = new Modulo();

			mod.setNombre(elemento[0].toString());
			mod.setAlfa(Double.parseDouble(elemento[1].toString()));
			mod.setBeta(Double.parseDouble(elemento[2].toString()));
			mod.setGamma(Double.parseDouble(elemento[3].toString()));
			mod.setKappa(Double.parseDouble(elemento[4].toString()));

			mod.setTecnologia(elemento[5].toString());
			mod.setTemperaturaNOCT(Double.parseDouble(elemento[6].toString()));
			mod.setIscNOCT(Double.parseDouble(elemento[7].toString()));
			mod.setVocNOCT(Double.parseDouble(elemento[8].toString()));
			mod.setPmaxNOCT(Double.parseDouble(elemento[9].toString()));
			mod.setIpmaxNOCT(Double.parseDouble(elemento[10].toString()));
			mod.setVpmaxNOCT(Double.parseDouble(elemento[11].toString()));

			mod.setRendimiento(Double.parseDouble(elemento[12].toString()));
			mod.setIdealidad(Double.parseDouble(elemento[13].toString()));
			mod.setResistencia(Double.parseDouble(elemento[14].toString()));

			mod.setMinISC(Double.parseDouble(elemento[15].toString()));
			mod.setMinVOC(Double.parseDouble(elemento[16].toString()));
			mod.setMinPMAX(Double.parseDouble(elemento[17].toString()));
			mod.setMinFF(Double.parseDouble(elemento[18].toString()));

			mod.setCelSerie(Integer.parseInt(elemento[19].toString()));
			mod.setCelSerie(Integer.parseInt(elemento[20].toString()));

			lista.add(mod);
		}
		return lista;
	}
	public  List<String> ListaCampanyas() throws ClassNotFoundException{
		BDConnection miBD = new BDConnection();
		ArrayList<String> lista = new ArrayList<String>();

		for(Object[] elemento : miBD.Select("SELECT nombreCampanya FROM campanya where Modulo_nombreModulo = '" + nombre + "';")){

			lista.add(elemento[0].toString());


		}
		return lista;
	}

	//---- Getters

	public String getNombre() {
		return nombre;
	}

	public String getTecnologia() {
		return tecnologia;
	}

	public double getTemperaturaNOCT() {
		return temperaturaNOCT;
	}

	public double getIscNOCT() {
		return iscNOCT;
	}

	public double getVocNOCT() {
		return vocNOCT;
	}

	public double getPmaxNOCT() {
		return pmaxNOCT;
	}

	public double getIpmaxNOCT() {
		return ipmaxNOCT;
	}

	public double getVpmaxNOCT() {
		return vpmaxNOCT;
	}

	public double getRendimiento() {
		return rendimiento;
	}

	public double getIdealidad() {
		return idealidad;
	}

	public double getResistencia() {
		return resistencia;
	}

	public double getMinISC() {
		return minISC;
	}

	public double getMinVOC() {
		return minVOC;
	}

	public double getMinPMAX() {
		return minPMAX;
	}

	public double getMinFF() {
		return minFF;
	}

	public int getCelSerie() {
		return celSerie;
	}

	public int getCelParalelo() {
		return celParalelo;
	}

	public double getAlfa() {
		return alfa;
	}

	public double getBeta() {
		return beta;
	}

	public double getGamma() {
		return gamma;
	}

	public double getKappa() {
		return kappa;
	}

	// ----- Setters (FALTAN LOS UPDATES DE LOS NUEVOS ATRIBUTOS)

	public void setNombre(String nombre) throws ClassNotFoundException {
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET NombreModulo ='"+nombre+"' WHERE NombreModulo LIKE'"+this.nombre+"';");
		this.nombre = nombre;
	}

	public void setAlfa(double alfa) throws ClassNotFoundException {
		this.alfa = alfa;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET valorAlpha='"+alfa+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setBeta(double beta) throws ClassNotFoundException {
		this.beta = beta;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET valorBeta='"+beta+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setGamma(double gamma) throws ClassNotFoundException {
		this.gamma = gamma;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET valorGamma='"+gamma+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setKappa(double kappa) throws ClassNotFoundException {
		this.kappa = kappa;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET valorKappa='"+kappa+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setTecnologia(String tecnologia) throws ClassNotFoundException {
		this.tecnologia = tecnologia;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET tecnologia='"+tecnologia+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setTemperaturaNOCT(double temperaturaNOCT) throws ClassNotFoundException {
		this.temperaturaNOCT = temperaturaNOCT;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET temperaturaNOCT='"+temperaturaNOCT+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setIscNOCT(double iscNOCT) throws ClassNotFoundException {
		this.iscNOCT = iscNOCT;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET iscNOCT='"+iscNOCT+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setVocNOCT(double vocNOCT) throws ClassNotFoundException {
		this.vocNOCT = vocNOCT;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET vocNOCT='"+vocNOCT+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setPmaxNOCT(double pmaxNOCT) throws ClassNotFoundException {
		this.pmaxNOCT = pmaxNOCT;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET pmaxNOCT='"+pmaxNOCT+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setIpmaxNOCT(double ipmaxNOCT) throws ClassNotFoundException {
		this.ipmaxNOCT = ipmaxNOCT;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET ipmaxNOCT='"+ipmaxNOCT+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setVpmaxNOCT(double vpmaxNOCT) throws ClassNotFoundException {
		this.vpmaxNOCT = vpmaxNOCT;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET vpmaxNOCT='"+vpmaxNOCT+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setRendimiento(double rendimiento) throws ClassNotFoundException {
		this.rendimiento = rendimiento;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET rendimiento='"+rendimiento+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setIdealidad(double idealidad) throws ClassNotFoundException {
		this.idealidad = idealidad;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET idealidad='"+idealidad+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setResistencia(double resistencia) throws ClassNotFoundException {
		this.resistencia = resistencia;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET resistencia='"+resistencia+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setMinISC(double minISC) throws ClassNotFoundException {
		this.minISC = minISC;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET minISC='"+minISC+"' WHERE NombreModulo LIKE'"+nombre+"';");
	}

	public void setMinVOC(double minVOC) throws ClassNotFoundException {
		this.minVOC = minVOC;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET minVOC='"+minVOC+"' WHERE NombreModulo LIKE'"+nombre+"';");

	}

	public void setMinPMAX(double minPMAX) throws ClassNotFoundException {
		this.minPMAX = minPMAX;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET minPMAX='"+minPMAX+"' WHERE NombreModulo LIKE'"+nombre+"';");

	}

	public void setMinFF(double minFF) throws ClassNotFoundException {
		this.minFF = minFF;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET minFF='"+minFF+"' WHERE NombreModulo LIKE'"+nombre+"';");

	}

	public void setCelSerie(int celSerie) throws ClassNotFoundException {
		this.celSerie = celSerie;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET celulasEnSerie='"+celSerie+"' WHERE NombreModulo LIKE'"+nombre+"';");

	}

	public void setCelParalelo(int celParalelo) throws ClassNotFoundException {
		this.celParalelo = celParalelo;
		BDConnection miBD = new BDConnection();
		miBD.Update("UPDATE MODULO SET celulasEnParalelo='"+celParalelo+"' WHERE NombreModulo LIKE'"+nombre+"';");

	}

	// -----

	public void anyadirCurva (CurvaOriginal c){
		curvas.add(c);
	}
}

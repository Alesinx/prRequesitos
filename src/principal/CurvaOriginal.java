package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.TreeMap;

import interfaz.BDConnection;

public class CurvaOriginal {

	private int idCurva;

	private String modName;
	private String campName;

	private double Isc;
	private double Voc;
	private double Pmax;
	private double IPmax;
	private double VPmax;
	private double FF;

	private Canal velViento,dirViento,humedad,temperatura,irradiancia,rtd,celula;


	private TreeMap<Double,Double> puntos;

	private String fechaHora;

	public CurvaOriginal(double isc, double voc, double pmax,double ipmax, double vpmax, double ff, String vo,String in,String date,String hour,String camp,String mod) throws ClassNotFoundException {

		this.fechaHora = date+";"+hour;
		this.FF = ff;
		this.Pmax=pmax;
		this.IPmax = ipmax;
		this.Isc = isc;
		this.Voc = voc;
		this.VPmax = vpmax;
		this.campName = camp;
		this.modName=mod;

		BDConnection baseDatos = new BDConnection();
		//si la campanya no existe crearla
		if(baseDatos.Select("SELECT * FROM campanya WHERE nombreCampanya = '"+camp+"' AND Modulo_nombreModulo= '"+mod+"';").isEmpty()){
			Campanya campa = new Campanya(camp, new Modulo(mod));

		}
		// baseDatos.Insert("INSERT INTO CURVAORIGINAL VALUES("+nuevoID()+",'"+date+"',"+isc+","+voc+","+pmax+","+ipmax+","+vpmax+","+ff+","+t+","+i+",'"+mod+"');");
		baseDatos.Insert("INSERT INTO curvaOriginal VALUES('"+this.fechaHora+"',"+isc+","+voc+","+pmax+","+ipmax+","+vpmax+","+ff+",'"+in+"','"+vo+"','"+camp+"','"+mod+"');");

		// Creacion del Map 
		puntos = new TreeMap<>();

		Scanner sIn = new Scanner(in);
		Scanner sVo = new Scanner(vo);
		sIn.useDelimiter(";");
		sVo.useDelimiter(";");

		while(sVo.hasNext()){
			double c = Double.parseDouble(sIn.next());
			double v = Double.parseDouble(sVo.next());

			puntos.put(v, c);
		}

		sIn.close();
		sVo.close();

	}


	public CurvaOriginal(String fechaCurva,String mod) throws ClassNotFoundException {
		// dado un id carga el objeto de la base de datos;
		this.fechaHora = fechaCurva;
		this.modName = mod;

		BDConnection baseDatos = new BDConnection();
		for(Object[] elemento : baseDatos.Select("SELECT * FROM curvaOriginal WHERE fechaHoraCurva = '"+fechaCurva+"' AND campanya_nombreModulo = '"+mod+"';")){

			this.Isc = Double.parseDouble(elemento[1].toString());
			this.Voc = Double.parseDouble(elemento[2].toString());
			this.Pmax = Double.parseDouble(elemento[3].toString());
			this.IPmax = Double.parseDouble(elemento[4].toString());
			this.VPmax = Double.parseDouble(elemento[5].toString());
			this.FF = Double.parseDouble(elemento[6].toString());
			String in = elemento[7].toString();
			String vo = elemento[8].toString();


			this.campName = elemento[9].toString();

			// Creacion del Map 
			puntos = new TreeMap<>();

			Scanner sIn = new Scanner(in);
			Scanner sVo = new Scanner(vo);

			sIn.useDelimiter(";");
			sVo.useDelimiter(";");

			while(sVo.hasNext()){

				double c = Double.parseDouble(sIn.next());
				double v = Double.parseDouble(sVo.next());

				puntos.put(v, c);
			}

			sIn.close();
			sVo.close();

			velViento = new Canal("Velocidad viento",this.fechaHora,campName,modName);
			dirViento = new Canal("Direccion viento",this.fechaHora,campName,modName);
			humedad = new Canal("Humedad relativa",this.fechaHora,campName,modName);
			temperatura = new Canal("Temperatura ambiente",this.fechaHora,campName,modName);
			irradiancia = new Canal("Piranometro seguidor",this.fechaHora,campName,modName);
			rtd = new Canal("RTD",this.fechaHora,campName,modName);
			celula = new Canal("Celula isofoton seguidor",this.fechaHora,campName,modName);

			this.setCanal(velViento, dirViento, humedad, temperatura, irradiancia, rtd, celula);
		}
	}

	public  List<CurvaCorregida> listaDeCurvasCorregidas() throws ClassNotFoundException{
		BDConnection miBD = new BDConnection();
		ArrayList<CurvaCorregida> lista = new ArrayList<CurvaCorregida>();

		for(Object[] elemento : miBD.Select("SELECT idCurvaCorregida FROM curvaCorregida where curvaOriginal_fechaHoraCurva = '" + fechaHora + "' AND orig_camp_nModulo = '"+modName+"' ;")){

			lista.add( new CurvaCorregida( Integer.parseInt(elemento[0].toString()) ) );


		}
		return lista;
	}

	public TreeMap<Double,Double> getPts() {
		return puntos;
	}

	public void setPts(String v,String i) {
		Scanner sIn = new Scanner(i);
		Scanner sVo = new Scanner(v);
		sIn.useDelimiter(";");
		sVo.useDelimiter(";");

		while(sVo.hasNext()){
			double c = sIn.nextDouble();
			double vo = sVo.nextDouble();
			puntos.put(vo, c);
		}

		sIn.close();
		sVo.close();
	}

	public double getIsc() {
		return Isc;
	}

	public void setIsc(double isc) {
		Isc = isc;
	}

	public double getVoc() {
		return Voc;
	}

	public void setVoc(double voc) {
		Voc = voc;
	}

	public double getPmax() {
		return Pmax;
	}

	public void setPmax(double pmax) {
		Pmax = pmax;
	}

	public double getIPmax() {
		return IPmax;
	}

	public void setIPmax(double iPmax) {
		IPmax = iPmax;
	}

	public double getVPmax() {
		return VPmax;
	}

	public void setVPmax(double vPmax) {
		VPmax = vPmax;
	}

	public double getFF() {
		return FF;
	}

	public void setFF(double fF) {
		FF = fF;
	}

	public String getFecha() {
		return fechaHora;
	}

	public void setFecha(String fecha) {
		this.fechaHora = fecha;
	}


	public String getModName() {
		return modName;
	}


	public void setModName(String modName) {
		this.modName = modName;
	}


	public String getCampName() {
		return campName;
	}


	public void setCampName(String campName) {
		this.campName = campName;
	}


	public int getIdCurva() {
		return idCurva;
	}


	public void setIdCurva(int idCurva) {
		this.idCurva = idCurva;
	}


	public String getFechaHora() {

		return fechaHora;
	}

	public Canal getVelViento() {
		return velViento;
	}

	public Canal getDirViento() {
		return dirViento;
	}

	public Canal getHumedad() {
		return humedad;
	}

	public Canal getTemperatura() {
		return temperatura;
	}

	public Canal getIrradiancia() {
		return irradiancia;
	}

	public Canal getRtd() {
		return rtd;
	}

	public Canal getCelula() {
		return celula;
	}

	public void setCanal(Canal cVelocidadViento, Canal cDireccionViento, Canal cHumedad, Canal cTemperatura,
			Canal cIrradiancia, Canal cRTD, Canal cCelula) {
		this.velViento=cVelocidadViento;
		this.dirViento=cDireccionViento;
		this.humedad=cHumedad;
		this.temperatura=cTemperatura;
		this.irradiancia=cIrradiancia;
		this.rtd=cRTD;
		this.celula=cCelula;

	}

	public void Borrar() throws ClassNotFoundException{

		// Borra los canales de la BD
		this.velViento.Borrar();
		this.dirViento.Borrar();
		this.humedad.Borrar();
		this.irradiancia.Borrar();
		this.celula.Borrar();
		this.rtd.Borrar();
		this.temperatura.Borrar();

		BDConnection miBD = new BDConnection();
		miBD.Delete("DELETE FROM curvaOriginal WHERE fechaHoraCurva = '"+this.fechaHora+"' AND campanya_nombreModulo = '"+this.modName+"';");
		this.Isc = -1;
		this.Voc = -1;
		this.Pmax = -1;
		this.IPmax = -1;
		this.VPmax = -1;
		this.FF = -1;
		this.puntos = null;
		this.modName = null;
		this.campName = null;


	}


	// CON ESTO NO SE QUE PASA AHORA
	//	private int nuevoID() throws ClassNotFoundException{
	//		BDConnection baseDatos = new BDConnection();
	//		List<Object[]> listaID = baseDatos.Select("SELECT IDCURVA FROM CURVAORIGINAL;");
	//		int max = 0;
	//		for(Object[] aux : listaID){
	//			if(max < (int) aux[0]){
	//				max = (int) aux[0];
	//			}
	//		}
	//		return (max+1);
	//	}
	//	private int nuevoIDiv() throws ClassNotFoundException{
	//		BDConnection baseDatos = new BDConnection();
	//		List<Object[]> listaID = baseDatos.Select("SELECT idParIV FROM ParIV;");
	//		int max = 0;
	//		for(Object[] aux : listaID){
	//			if(max < (int) aux[0]){
	//				max = (int) aux[0];
	//			}
	//		}
	//		return (max+1);
	//	}
}
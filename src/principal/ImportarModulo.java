package principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class ImportarModulo {

	public ImportarModulo(){
	}

	public static Modulo importarModulo(String file) throws FileNotFoundException, ClassNotFoundException{

		Scanner sc = new Scanner(new File(file));

		sc.useLocale(Locale.US);

		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(4);
		nf.setMaximumFractionDigits(4);


		String nom = sc.nextLine(); // Nombre del modulo
		String tec = sc.nextLine(); // Tecnologia
		System.out.println("Nombre "+nom+", tecnologia "+tec);

		sc.nextLine();

		double volInic = transform(sc.nextDouble()); // Tension inicial
		double volInt = transform(sc.nextDouble()); // Tension intermedia
		double volFin = transform(sc.nextDouble()); // Tension final
		System.out.println("Tensiones inicial, intermedia y final: "+volInic+" "+volInt+" "+volFin);

		double intMaxPos = transform(sc.nextDouble()); // Corriente maxima positiva
		double intMaxNeg = transform(sc.nextDouble()); // Corriente maxima negativa
		System.out.println("Corriente maxima positiva y negativa: "+intMaxPos+" "+intMaxNeg);

		int ptsPrimerTramo = sc.nextInt(); // Puntos del primer tramo
		int ptsSegundoTramo = sc.nextInt(); // Puntos del segundo tramo
		System.out.println("Puntos primer tramo: "+ptsPrimerTramo);
		System.out.println("Puntos segundo tramo: "+ptsSegundoTramo);

		double precisionVolt = transform(sc.nextDouble()); // Precision de voltaje
		double precisionInt = transform(sc.nextDouble()); // Precision de intensidad
		double precisionPot = transform(sc.nextDouble()); // Precision de potencia
		System.out.println("Precision voltaje: "+precisionVolt);
		System.out.println("Precision intensidad: "+precisionInt);
		System.out.println("Precision potencia: "+precisionPot);

		sc.nextLine();
		sc.nextLine();

		double al = transform(sc.nextDouble()); // Alpha
		sc.nextLine();
		sc.nextLine();
		double be = transform(sc.nextDouble()); // Beta
		sc.nextLine();
		sc.nextLine();
		double ga = transform(sc.nextDouble()); // Gamma
		sc.nextLine();
		sc.nextLine();
		double ka = transform(sc.nextDouble()); // Kappa
		sc.nextLine();
		sc.nextLine();

		double tempNOCT = transform(sc.nextDouble()); // Temperatura NOCT
		System.out.println("Temperatura Noct: "+tempNOCT);

		System.out.println("Alpha: "+al+" Beta: "+be+" Gamma: "+ga+" Kappa: "+ka);

		double iscSTC = transform(sc.nextDouble()); // ISC STC
		double vocSTC = transform(sc.nextDouble()); // VOC STC
		double pmaxSTC = transform(sc.nextDouble()); // PMAX STC
		double ipmaxSTC = transform(sc.nextDouble()); // IPMAX STC
		double vpmaxSTC = transform(sc.nextDouble()); // VPMAX STC

		sc.nextLine();

		double iscNOCT = transform(sc.nextDouble()); // ISC NOCT
		double vocNOCT = transform(sc.nextDouble()); // VOC NOCT
		double pmaxNOCT = transform(sc.nextDouble()); // PMAX NOCT
		double ipmaxNOCT = transform(sc.nextDouble()); // IPMAX NOCT
		double vpmaxNOCT = transform(sc.nextDouble()); // VPMAX NOCT

		System.out.println("NOCT: "+iscNOCT+" "+vocNOCT+" "+pmaxNOCT+" "+ipmaxNOCT+" "+vpmaxNOCT);

		double rend = transform(sc.nextDouble()); // Rendimiento
		double ideal = transform(sc.nextDouble()); // Idealidad
		System.out.println("Rendimiento: "+rend+" Idealidad: "+ideal);

		sc.nextLine();

		int celSerie = sc.nextInt(); // Celulas en serie
		int celParalelo = sc.nextInt(); // Celulas en paralelo
		System.out.println("Celulaes en serie y en paralelo: "+celSerie+" "+celParalelo);

		double res = transform(sc.nextDouble()); // Resistencia
		System.out.println("Resistencia: "+res);

		double minIsc = transform(sc.nextDouble()); // Minima ISC
		double minVoc = transform(sc.nextDouble()); // Minima VOC
		double minPmax = transform(sc.nextDouble()); // Minima PMAX
		double minFf = transform(sc.nextDouble()); // Minima FF
		System.out.println("Valores minimos (isc,voc,pmax,ff): "+minIsc+" "+minVoc+" "+minPmax+" "+minFf);


		Modulo mod = new Modulo(nom, tec, al, be, ga, ka, tempNOCT, iscNOCT, vocNOCT, pmaxNOCT, ipmaxNOCT, vpmaxNOCT, rend, ideal, res, minIsc, minVoc, minPmax, minFf, celSerie, celParalelo);

		sc.close();
		return mod;
	}

	private static double transform(double d){

		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(3);

		if(d<0){
			double aux = Math.abs(d);
			return (-1)*Double.parseDouble(nf.format(aux));
		}
		else return Double.parseDouble(nf.format(d));
	}

}

package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import principal.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class IntfzListaCurvasCorregidas extends JFrame {

	private JPanel contentPane;
	
	
	private CurvaOriginal origen;
	XYSeriesCollection datasetOriginal;
	JFreeChart chartOriginal;

	/**
	 * Launch the application.
	 */
	public void newScreen(CurvaOriginal or) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntfzListaCurvasCorregidas frame = new IntfzListaCurvasCorregidas(or);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 */
	public IntfzListaCurvasCorregidas(CurvaOriginal or) throws ClassNotFoundException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1068, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		setTitle("Curvas Corregidas");
		setIconImage(Toolkit.getDefaultToolkit().getImage(IntfzModuloElegido.class.getResource("/Images/pyromikLogo.jpeg")));

		
		origen = or;	//le asignamos esto
		
		
//-----PANEL CON LA TABLA DE LAS CURVAS
		JPanel panelTabla = new JPanel();
		panelTabla.setBorder(new TitledBorder(null, "Curvas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTabla.setBounds(0, 11, 446, 459);
		contentPane.add(panelTabla);
		panelTabla.setLayout(null);
		
		//construccion de la tabla
		String[] columnName = {"id","fecha y hora","Isc(A)","Voc(V)",
				"Pmax(W)","IPmax(A)","VPmax(V)","FF(%)"};
		
		List<CurvaCorregida> listaDeCurvas = origen.listaDeCurvasCorregidas() ;
		
		Object [] [] data= new Object [listaDeCurvas.size()] [columnName.length];//array de objetos
		int i=0;
		for( CurvaCorregida c : origen.listaDeCurvasCorregidas()){
			data[i][0]=c.getIdCurvaCorregida();
			data[i][1]=origen.getFecha();
			data[i][2]=c.getIsc();
			data[i][3]=c.getVoc();
			data[i][4]=c.getPmax();
			data[i][5]=c.getIpmax();
			data[i][6]=c.getVpmax();
			data[i][7]=c.getFf();
			
			i++;
		}
		
		DefaultTableModel modelo = new DefaultTableModel(data, columnName);
		JTable tablaCurvas = new JTable(modelo);
		tablaCurvas.setSelectionMode( ListSelectionModel.SINGLE_INTERVAL_SELECTION);	//permite que se seleccionen una unica columna
		tablaCurvas.addMouseListener(new MouseAdapter() {
			//ESTE METODO ACTUALIZA LA VENTANA GRAFICA
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//antes tiene que borrar las que hubiera
				if(datasetOriginal.getSeries().size() > 1){
				datasetOriginal.removeSeries(1);
				}
				//seleccionamos el objeto
				int seleccion = tablaCurvas.rowAtPoint(arg0.getPoint());
				CurvaCorregida Cselec = null;
				try {
					Cselec= new CurvaCorregida( Integer.parseInt(tablaCurvas.getValueAt(seleccion, 0).toString()) );
				} catch (NumberFormatException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				XYSeries seriesSelec = new XYSeries("Curva Corregida");
				for( Entry<Double, Double> pt : Cselec.getPts().entrySet()){
					seriesSelec.add(pt.getKey(), pt.getValue());
				}
				
				datasetOriginal.addSeries(seriesSelec);

				
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(tablaCurvas);
		scrollPane.setBounds(10, 20, 426, 428);
		panelTabla.add(scrollPane);		
//-----PANEL CON LA CURVA ORIGINAL
		//inicializacion del panel
		XYSeries seriesOriginal = new XYSeries("Curva original");
		for( Entry<Double, Double> pt : origen.getPts().entrySet()){
			seriesOriginal.add(pt.getKey(), pt.getValue());
		}
		 	datasetOriginal = new XYSeriesCollection();
	        datasetOriginal.addSeries(seriesOriginal);

	        chartOriginal = ChartFactory.createXYLineChart(
	                "curvaFoltovoltaica", // Título
	                "Corriente (A)", // Etiqueta Coordenada X
	                "Tension (V)", // Etiqueta Coordenada Y
	                datasetOriginal, // Datos
	                PlotOrientation.VERTICAL,
	                true, // Muestra la leyenda de los productos (Producto A)
	                false,
	                false
	        );
		JPanel panelCurvaOriginal = new JPanel();
		panelCurvaOriginal.setBorder(new TitledBorder(null, "Curva Original", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCurvaOriginal.setBounds(456, 11, 586, 459);
		contentPane.add(panelCurvaOriginal);
		panelCurvaOriginal.setLayout(null);
		
		ChartPanel panelgraficaOriginal = new ChartPanel(chartOriginal);
		panelgraficaOriginal.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelgraficaOriginal.setBounds(10, 20, 566, 428);
		panelCurvaOriginal.add(panelgraficaOriginal);
	}
}

package interfaz;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import principal.*;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Choice;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TabableView;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import javax.swing.border.EtchedBorder;
import java.awt.ScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.JMenuItem;

import java.awt.Toolkit;

public class IntfzModuloElegido {

	private JFrame frame;
	//EL MODULO OBTENIDO
	private Modulo mod = new Modulo();
	private JTable tablaCurvas;
	private ArrayList<CurvaOriginal> listaDeCurvas; 		//curvas seleccionadas en la campaña
	private ArrayList<CurvaOriginal> listaDeCurvasACorregir;//curvas seleccionadas para corregir
	

	JPanel panelCurva;
	JPanel panelCorreccion;

	private JTextField txtfAlpha;
	private JTextField txtfBetta;
	private JTextField txtfRs;
	private JTextField txtfKappa;
	private JTextField txtfT2;
	private JTextField txtfIrr2;
	
	private JLayeredPane layeredPane;
	
	ChartPanel panelGrafica;
	private JTable tablaGraf;
	private JScrollPane scrollPane_1;
	
	Choice choiceTemp= new Choice();;
	Choice choiceIrra= new Choice();;

	/**
	 * Launch the application.
	 */
	public void newScreen(String nombreModulo) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//inicializacion

					IntfzModuloElegido window = new IntfzModuloElegido(nombreModulo);
					window.frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ClassNotFoundException 
	 */
	public IntfzModuloElegido( String nombreModulo) throws ClassNotFoundException {

		initialize(nombreModulo);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ClassNotFoundException 
	 */
	private void initialize(String nombreModulo) throws ClassNotFoundException {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(IntfzModuloElegido.class.getResource("/Images/pyromikLogo.jpeg")));
		frame.setBounds(100, 100, 1366, 691);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.setLocationRelativeTo(null);
		//carga los datos del modulo
		mod = new Modulo(nombreModulo);
		listaDeCurvasACorregir = new ArrayList<CurvaOriginal>();
		
		
		
		frame.setTitle("Modulo :"+mod.getNombre());

		//CONSTRUCCION DE LA TABLA
		
		String[] columnName = {"fecha y hora","Isc(A)","Voc(V)",
				"Pmax(W)","IPmax(A)","VPmax(V)","FF(%)","velViento(m/s)","dirViento(�)","Humedad(%)","Temperatura(�C)","Irradiancia(W/m^2)","RTD(�C)","C�lula(A)"};
		Object [] [] data= new Object [0] [columnName.length];//array de objetos
		frame.getContentPane().setLayout(null);
		
		
//------PANEL DE CAMPA�AS
		JPanel panelCamp = new JPanel();
		panelCamp.setBounds(0, 0, 905, 112);
		panelCamp.setBorder(new TitledBorder(null, "Campanyas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panelCamp);
		panelCamp.setLayout(null);
		
		JList<String> list = new JList<String>();
		list.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (list.getSelectedValue() != null) {	//si seleccionas la patnalla vacia se activa
					//cogemos el modelo y borramos sus datos anteriores
					DefaultTableModel temp = (DefaultTableModel) tablaCurvas.getModel();
					int total = temp.getRowCount() + 1;
					for (int w = 0; w < total - 1; w++) {
						temp.removeRow(0);
					}
					//insertamos cada elementos de la tabla
					try {

						int i = 0;
						
						for (CurvaOriginal co : Campanya.getCurvas(mod.getNombre(), list.getSelectedValue())) {
							Canal vv = co.getVelViento();
							Canal dv = co.getDirViento();
							Canal hr = co.getHumedad();
							Canal t = co.getTemperatura();
							Canal ir = co.getIrradiancia();
							Canal rtd = co.getRtd();
							Canal cel = co.getCelula();
							temp.addRow(new Object[] { co.getFecha(), co.getIsc(), co.getVoc(), co.getPmax(),
									co.getIPmax(), co.getVPmax(), co.getFF(),
									vv.getValorInicial()+"/"+vv.getValorMedio()+"/"+vv.getValorFinal(),
									dv.getValorInicial()+"/"+dv.getValorMedio()+"/"+dv.getValorFinal(),
									hr.getValorInicial()+"/"+hr.getValorMedio()+"/"+hr.getValorFinal(),
									t.getValorInicial()+"/"+t.getValorMedio()+"/"+t.getValorFinal(),
									ir.getValorInicial()+"/"+ir.getValorMedio()+"/"+ir.getValorFinal(),
									rtd.getValorInicial()+"/"+rtd.getValorMedio()+"/"+rtd.getValorFinal(),
									cel.getValorInicial()+"/"+cel.getValorMedio()+"/"+cel.getValorFinal()
							});
							i++;
						}
					} catch (ClassNotFoundException e) {
						
						JOptionPane.showMessageDialog(null, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					} //end try/catch
				}//end if
				
			}
		});
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setBounds(43, 25, 562, 76);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);	//solo se puede seleccionar uno
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);						//se despliega en columnas
		//creamos el modelo
		DefaultListModel<String> modeloCamp = new DefaultListModel<String>();
		for(String cmp : mod.ListaCampanyas()){
			modeloCamp.addElement(cmp);
		}
		
		list.setModel(modeloCamp);//le asiganmos el modelo con los valores
		panelCamp.add(list);
	
			
		
//------PANEL CURVAS	
		panelCurva = new JPanel();
		panelCurva.setBounds(10, 123, 895, 518);
		panelCurva.setBorder(new TitledBorder(null, "Curvas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panelCurva);
		panelCurva.setLayout(null);
		DefaultTableModel model = new DefaultTableModel(data, columnName);
		tablaCurvas = new JTable(model);
		tablaCurvas.setSelectionMode( ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);	//permite que se seleccionen varias columnas
		tablaCurvas.addMouseListener(new MouseAdapter() {
			//ESTE METODO ACTUALIZA LA VENTANA GRAFICA
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//seleccionamos el objeto
				int seleccion = tablaCurvas.rowAtPoint(arg0.getPoint());
				CurvaOriginal a = null;
				//obtenemos la curva
				try {
					a = new CurvaOriginal( tablaCurvas.getValueAt(seleccion, 0).toString(),mod.getNombre()); //obtiene la curva
				} catch (ClassNotFoundException e) {
					
					JOptionPane.showMessageDialog(null, e.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
				}
				XYSeries seriesA = new XYSeries("par i-v");
				//datos para la tabla
				
				//cogemos el modelo y borramos sus datos anteriores
				DefaultTableModel temp = (DefaultTableModel) tablaGraf.getModel();	
				int total = temp.getRowCount()+1;
				for(int w =0; w<total-1;w++){
					temp.removeRow(0);
				}
		        // Introduccion de datos
				int i = 0;
				for( Entry<Double, Double> pt : a.getPts().entrySet()){
					//parte de la tabla
					double pot = pt.getKey()*pt.getValue();	//potencia
					temp.addRow(new Object[]{i+1,
											ImportarModulo.transform(pt.getKey())
											,ImportarModulo.transform( pt.getValue() )
											,ImportarModulo.transform(pot)});
					
					i++;
					
					//parte grafica
					seriesA.add(pt.getKey(), pt.getValue());
				}
				

		        XYSeriesCollection datasetA = new XYSeriesCollection();
		        datasetA.addSeries(seriesA);

		        JFreeChart chartA = ChartFactory.createXYLineChart(
		                "curvaFoltovoltaica", // T�tulo
		                "Corriente (A)", // Etiqueta Coordenada X
		                "Tension (V)", // Etiqueta Coordenada Y
		                datasetA, // Datos
		                PlotOrientation.VERTICAL,
		                false, // Muestra la leyenda de los productos (Producto A)
		                false,
		                false
		        );
				panelGrafica.setChart(chartA);
				
			}//end void del click del raton
		});
		tablaCurvas.setBounds(20, 11, 414, 183);
		
		//CREACION DEL POPUPMENU!
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem menuItemVer = new JMenuItem("Ver curvas corregidas");
		menuItemVer.addActionListener(new ActionListener() {
			//que se hace al seleccionar este item
            public void actionPerformed(ActionEvent e) {
            	int seleccion = tablaCurvas.getSelectedRow();
            	CurvaOriginal a;
				try {
					a = new CurvaOriginal(tablaCurvas.getValueAt(seleccion, 0).toString(), mod.getNombre() );
					System.out.println(a.listaDeCurvasCorregidas());
					IntfzListaCurvasCorregidas lcc = new IntfzListaCurvasCorregidas(a);
					lcc.newScreen(a);
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
            	
                
            }
        });
		popupMenu.add(menuItemVer);
		tablaCurvas.setComponentPopupMenu(popupMenu);//se lo asociamos a la tabla
		
		JScrollPane scrollPane = new JScrollPane(tablaCurvas);
		scrollPane.setBounds(10, 22, 875, 447);
		panelCurva.add(scrollPane);
		
		//-----BOTON CORREGIR
		JButton btnCorregir = new JButton("Corregir");
		btnCorregir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//obtener todas los indices que se han seleccionado
				int[] curvasSelecionadas = tablaCurvas.getSelectedRows();
							
				//Si no se selecciona ninguno
				if(curvasSelecionadas.length == 0) {
					JOptionPane.showMessageDialog(null, "No se han seleccionado curvas", "Aviso",JOptionPane.WARNING_MESSAGE);
				}else {
					for(int w=0;w<curvasSelecionadas.length;w++){
						try {
								//Basicamente metemos en la lista de curvas corregidas las seleccionasa
								listaDeCurvasACorregir.add(new CurvaOriginal( tablaCurvas.getValueAt(curvasSelecionadas[w], 0).toString(),mod.getNombre() ) );
									
								} catch (ClassNotFoundException e1) {
									
									JOptionPane.showMessageDialog(null, e1.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
								}
							}//end for
					//coegmos los valores de la primera curva seleccionada
					Iterator<CurvaOriginal> ic = listaDeCurvasACorregir.iterator();
					CurvaOriginal temporal = ic.next();
					//obtener los canales y asiganrselos a los combobox
					Canal IrrTemporal = temporal.getIrradiancia();
					Canal tempTemporal = temporal.getTemperatura();
					Canal tempTemporal_ = temporal.getRtd();//michel pide los que tengan las mismas unidades
					
					choiceIrra.add( String.valueOf(IrrTemporal.getValorInicial()) );choiceIrra.add( String.valueOf(IrrTemporal.getValorMedio()) );choiceIrra.add( String.valueOf(IrrTemporal.getValorFinal()) );
					choiceTemp.add( String.valueOf(tempTemporal.getValorInicial()) );choiceTemp.add( String.valueOf(tempTemporal.getValorMedio()) );choiceTemp.add( String.valueOf(tempTemporal.getValorFinal()) );
					choiceTemp.add( String.valueOf(tempTemporal_.getValorInicial()) );choiceTemp.add( String.valueOf(tempTemporal_.getValorMedio()) );choiceTemp.add( String.valueOf(tempTemporal_.getValorFinal()) );

					panelCurva.setVisible(false);
					panelCamp.setVisible(false);
					panelCorreccion.setVisible(true);
							
				}//end else
			}
		});

		btnCorregir.setBounds(796, 480, 89, 23);
		panelCurva.add(btnCorregir);	
		
		//------BOTON ELIMINAR		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (list.getSelectedValue() != null) {	//si seleccionas la patnalla vacia se activa
					
					DefaultTableModel temp = (DefaultTableModel) tablaCurvas.getModel();
					
					while(tablaCurvas.getSelectedRows().length > 0){
						int selected = tablaCurvas.getSelectedRow();
						try {
							CurvaOriginal curvaDeleted = new CurvaOriginal(tablaCurvas.getValueAt(selected, 1).toString(), mod.getNombre());
							curvaDeleted.Borrar();
							temp.removeRow(selected);
						} catch (ClassNotFoundException e) {
							
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Se ha borrado la curva/s seleccionada/s","Info",JOptionPane.INFORMATION_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "No hay curva seleccionada","Ups",JOptionPane.WARNING_MESSAGE);
				}//end if/else
				
			}
		});
		btnEliminar.setBounds(20, 480, 89, 23);
		panelCurva.add(btnEliminar);
									

//------PANEL CORRECCION
		panelCorreccion = new JPanel();
		panelCorreccion.setBounds(0, 0, 823, 652);
		frame.getContentPane().add(panelCorreccion);
		panelCorreccion.setLayout(null);

		JLabel lblSeleccionarElMtodo = new JLabel("Seleccionar el m�todo de correci�n");
		lblSeleccionarElMtodo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarElMtodo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSeleccionarElMtodo.setBounds(10, 11, 589, 54);
		panelCorreccion.add(lblSeleccionarElMtodo);

		JLabel lblMtodosDeCorrecin = new JLabel("M�todos de correci�n");
		lblMtodosDeCorrecin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMtodosDeCorrecin.setBounds(10, 76, 250, 14);
		panelCorreccion.add(lblMtodosDeCorrecin);
		//-----seleccionador
		Choice choiceMetodo = new Choice();
		choiceMetodo.add("IEC60891 m�todo 1");
		choiceMetodo.setBounds(10, 96, 250, 20);
		panelCorreccion.add(choiceMetodo);

		JLabel lblAlpha = new JLabel("Alpha");
		lblAlpha.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlpha.setBounds(10, 185, 46, 14);
		panelCorreccion.add(lblAlpha);

		JLabel lblBetta = new JLabel("Betta");
		lblBetta.setHorizontalAlignment(SwingConstants.CENTER);
		lblBetta.setBounds(10, 216, 46, 14);
		panelCorreccion.add(lblBetta);

		JLabel lblRs = new JLabel("Rs");
		lblRs.setHorizontalAlignment(SwingConstants.CENTER);
		lblRs.setBounds(10, 244, 46, 14);
		panelCorreccion.add(lblRs);

		JLabel lblKappa = new JLabel("Kappa");
		lblKappa.setHorizontalAlignment(SwingConstants.CENTER);
		lblKappa.setBounds(10, 272, 46, 14);
		panelCorreccion.add(lblKappa);

		txtfAlpha = new JTextField();
		txtfAlpha.setBounds(103, 182, 109, 20);
		txtfAlpha.setText(String.valueOf(mod.getAlfa()));	//valor por defecto del módulo
		panelCorreccion.add(txtfAlpha);
		txtfAlpha.setColumns(10);

		txtfBetta = new JTextField();
		txtfBetta.setBounds(103, 213, 109, 20);
		txtfBetta.setText(String.valueOf(mod.getBeta()));	//valor por defecto del módulo
		panelCorreccion.add(txtfBetta);
		txtfBetta.setColumns(10);

		txtfRs = new JTextField();
		txtfRs.setText("0.0");
		txtfRs.setBounds(103, 241, 109, 20);
		panelCorreccion.add(txtfRs);
		txtfRs.setColumns(10);

		txtfKappa = new JTextField();
		txtfKappa.setBounds(103, 269, 109, 20);
		txtfKappa.setText(String.valueOf(mod.getKappa()));	//valor por defecto del módulo
		panelCorreccion.add(txtfKappa);
		txtfKappa.setColumns(10);

		JLabel lblMedidaAlpha = new JLabel("mA/\u00BAC");
		lblMedidaAlpha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedidaAlpha.setBounds(214, 185, 46, 14);
		panelCorreccion.add(lblMedidaAlpha);

		JLabel lblMedidaBetta = new JLabel("mV/\u00BAC");
		lblMedidaBetta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedidaBetta.setBounds(214, 216, 46, 14);
		panelCorreccion.add(lblMedidaBetta);

		JLabel lblMedidaRs = new JLabel("\u03A9");
		lblMedidaRs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedidaRs.setBounds(214, 244, 46, 14);
		panelCorreccion.add(lblMedidaRs);

		JLabel lblMedidaKappa = new JLabel("m\u03A9/\u00BAC");
		lblMedidaKappa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedidaKappa.setBounds(214, 272, 46, 14);
		panelCorreccion.add(lblMedidaKappa);
		//COMBOBOX DE LA TEMPERATURA
		choiceTemp.setBounds(347, 185, 109, 20);
		panelCorreccion.add(choiceTemp);
		//COMBOBOX DE LA IRRADIENCIA
		
		choiceIrra.setBounds(347, 241, 109, 20);
		panelCorreccion.add(choiceIrra);
		//-----BOTON CORREGIR
		JButton btnCorregir_1 = new JButton("Corregir");
		btnCorregir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					if(choiceMetodo.getSelectedItem().equals("IEC60891 m�todo 1")) {

						//obtenemos la priemra curva
						Iterator <CurvaOriginal> it = listaDeCurvasACorregir.iterator();
						CurvaOriginal is = it.next();
						
						CurvaCorregida curva_corregida = 
						Metodo.Metodo1(Double.parseDouble(txtfAlpha.getText()),
										Double.parseDouble(txtfBetta.getText()), 
										Double.parseDouble(txtfRs.getText()),
										Double.parseDouble(txtfKappa.getText()), 
										is.getIsc(),
										Double.parseDouble(choiceIrra.getSelectedItem()),
										Double.parseDouble(txtfIrr2.getText()),
										Double.parseDouble(choiceTemp.getSelectedItem()),
										Double.parseDouble(txtfT2.getText()),
										is);	
						
						
						IntfzCurvaCorregida cci = new IntfzCurvaCorregida(curva_corregida);
						cci.newScreen(curva_corregida);
					}
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR!",JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}

			}
		});
		btnCorregir_1.setBounds(510, 216, 89, 42);
		panelCorreccion.add(btnCorregir_1);
		//------BOTON ATRAS
		JButton btnAtras_correccion = new JButton("Atr�s");
		btnAtras_correccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tambien tiene que vaciar las curvas seleccionadas
				listaDeCurvasACorregir.clear();

				panelCorreccion.setVisible(false);
				panelCamp.setVisible(true);
				panelCurva.setVisible(true);
			}
		});
		btnAtras_correccion.setBounds(10, 618, 89, 23);
		panelCorreccion.add(btnAtras_correccion);
		
		JLabel lblT1 = new JLabel("tempOriginal");
		lblT1.setHorizontalAlignment(SwingConstants.CENTER);
		lblT1.setBounds(270, 185, 67, 14);
		panelCorreccion.add(lblT1);
		
		JLabel lblT2 = new JLabel("tempNuevo");
		lblT2.setHorizontalAlignment(SwingConstants.CENTER);
		lblT2.setBounds(270, 216, 67, 14);
		panelCorreccion.add(lblT2);
		
		JLabel lblIrr1 = new JLabel("IrraOriginal");
		lblIrr1.setHorizontalAlignment(SwingConstants.CENTER);
		lblIrr1.setBounds(270, 244, 67, 14);
		panelCorreccion.add(lblIrr1);
								
		JLabel lblIrr2 = new JLabel("IrraNueva");
		lblIrr2.setHorizontalAlignment(SwingConstants.CENTER);
		lblIrr2.setBounds(270, 272, 67, 14);
		panelCorreccion.add(lblIrr2);
								
		txtfT2 = new JTextField();
		txtfT2.setText("0.0");
		txtfT2.setColumns(10);
		txtfT2.setBounds(347, 213, 109, 20);
		panelCorreccion.add(txtfT2);
								
		txtfIrr2 = new JTextField();
		txtfIrr2.setText("0.0");
		txtfIrr2.setColumns(10);
		txtfIrr2.setBounds(347, 269, 109, 20);
		panelCorreccion.add(txtfIrr2);
								
		JLabel lblMedidaT1 = new JLabel("\u00BAC");
		lblMedidaT1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedidaT1.setBounds(457, 185, 46, 14);
		panelCorreccion.add(lblMedidaT1);
								
		JLabel lblMedidaT2 = new JLabel("\u00BAC");
		lblMedidaT2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedidaT2.setBounds(457, 216, 46, 14);
		panelCorreccion.add(lblMedidaT2);
								
		JLabel lblMedidaIrr1 = new JLabel("W/m^2");
		lblMedidaIrr1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedidaIrr1.setBounds(457, 244, 46, 14);
		panelCorreccion.add(lblMedidaIrr1);
								
		JLabel lblMedidaIrr2 = new JLabel("W/m^2");
		lblMedidaIrr2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedidaIrr2.setBounds(457, 272, 46, 14);
		panelCorreccion.add(lblMedidaIrr2);
		
		
		
		
//---------PANEL GRAFICO
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(915, 0, 435, 641);
		layeredPane.setBorder(BorderFactory.createTitledBorder(
                "Representaci�n gr�fica"));
		frame.getContentPane().add(layeredPane);
		
		
		
		
		XYSeries series = new XYSeries("par i-v");

        // Introduccion de datos
        series.add(1, 1);
        series.add(2, 6);
        series.add(3, 3);
        series.add(4, 10);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "curvaFoltovoltaica", // T�tulo
                "Tension (V)", // Etiqueta Coordenada X
                "Corriente (A)", // Etiqueta Coordenada Y
                dataset, // Datos
                PlotOrientation.VERTICAL,
                false, // Muestra la leyenda de los productos (Producto A)
                false,
                false
        );
		
		
		
        panelGrafica = new ChartPanel(chart);
        panelGrafica.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panelGrafica.setBounds(10, 21, 415, 251);
        layeredPane.add(panelGrafica);
        panelGrafica.setName("A");
        
        String[] columaGraf = {"N","Tensi�n(V)","Corriente(A)","Potencia(W)"};
		
		
		DefaultTableModel modelo = new DefaultTableModel(columaGraf, 0);
        tablaGraf = new JTable(modelo);
        tablaGraf.setBounds(71, 235, 274, 205);
        layeredPane.add(tablaGraf);       
        
        scrollPane_1 = new JScrollPane(tablaGraf);
        scrollPane_1.setBounds(10, 283, 415, 347);
        layeredPane.add(scrollPane_1);
        
		
		
//-----necesario para la inicializacion
        panelCamp.setVisible(true);
		panelCurva.setVisible(true);
		panelCorreccion.setVisible(false);
	}
}

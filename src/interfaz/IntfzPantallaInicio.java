package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import principal.Modulo;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JDesktopPane;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

public class IntfzPantallaInicio extends JFrame {
	//esto es Miguel haciendo una prueba de git, please ignore
	private JLayeredPane contentPane;
	private JLabel lblLogoPyromik;
	private JLabel lblPvtranslator;
	private JButton btnListaModulo;
	private JButton btnImportarModulo;
	private JButton btnImportarCurva;

	private JPanel panelModulo;
	private JPanel panelEditar;
	private JLabel ListaModuloTitulo;
	private JButton button;
	private JButton Elegir;
	/**
	 * Launch the application.
	 */
	
	private JList<String> list;
	private JTextField textField_alpha;
	private JTextField textField_beta;
	private JTextField textField_gamma;
	private JTextField textField_kappa;
	private JTextField textField_tecnologia;
	private JLabel lblTemperaturaNocturna;
	private JTextField textField_tempNOCT;
	private JLabel lblIscNOCT;
	private JTextField textField_iscNOCT;
	private JLabel lblVocNOCT;
	private JTextField textField_vocNOCT;
	private JLabel lblpmaxNOCT;
	private JTextField textField_pmaxNOCT;
	private JLabel lvlIpmax_NOCT;
	private JTextField textField_ipmaxNOCT;
	private JLabel lblVpmax;
	private JTextField textField_vpmaxNOCT;
	private JLabel lblRendimiento;
	private JTextField textField_rendimiento;
	private JLabel lblIdealidad;
	private JLabel lblResistencia;
	private JLabel lblMinIsc;
	private JLabel lblMinVOC;
	private JLabel lblMinPmax;
	private JLabel lblMinFf;
	private JLabel lblCelulasEnSerie;
	private JLabel lblCelulasEnParalelo;
	private JTextField textField_celulasEnParalelo;
	private JTextField textField_celulasEnSerie;
	private JTextField textField_minFF;
	private JTextField textField_minPMAX;
	private JTextField textField_minVOC;
	private JTextField textField_minISC;
	private JTextField textField_resistencia;
	private JTextField textField_idealidad;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					IntfzPantallaInicio frame = new IntfzPantallaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IntfzPantallaInicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 591, 420);
		contentPane = new JLayeredPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		this.setTitle("PVTRANSLATOR");
		Image logoPyromik = new ImageIcon(this.getClass().getResource("/Images/pyromikLogo.jpeg")).getImage();
								
								panelEditar = new JPanel();
								panelEditar.setBounds(0, 0, 575, 381);
								contentPane.add(panelEditar);
								panelEditar.setLayout(null);
								
								textField_alpha = new JTextField();
								textField_alpha.setBounds(127, 33, 86, 20);
								panelEditar.add(textField_alpha);
								textField_alpha.setColumns(10);
								
								textField_beta = new JTextField();
								textField_beta.setBounds(127, 58, 86, 20);
								panelEditar.add(textField_beta);
								textField_beta.setColumns(10);
								
								textField_gamma = new JTextField();
								textField_gamma.setBounds(127, 83, 86, 20);
								panelEditar.add(textField_gamma);
								textField_gamma.setColumns(10);
								
								textField_kappa = new JTextField();
								textField_kappa.setBounds(127, 108, 86, 20);
								panelEditar.add(textField_kappa);
								textField_kappa.setColumns(10);
								
								JLabel lblNombre = new JLabel("Nombre:");
								lblNombre.setBounds(10, 11, 46, 14);
								panelEditar.add(lblNombre);
								
								JLabel lblAlpha = new JLabel("Alpha:");
								lblAlpha.setBounds(10, 36, 46, 14);
								panelEditar.add(lblAlpha);
								
								JLabel lblBeta = new JLabel("Beta:");
								lblBeta.setBounds(10, 61, 46, 14);
								panelEditar.add(lblBeta);
								
								JLabel lblGamma = new JLabel("Gamma:");
								lblGamma.setBounds(10, 86, 46, 14);
								panelEditar.add(lblGamma);
								
								JLabel lblKappa = new JLabel("Kappa:");
								lblKappa.setBounds(10, 111, 46, 14);
								panelEditar.add(lblKappa);
								
								JButton btnGuardar = new JButton("Guardar");
								btnGuardar.setBounds(352, 347, 89, 23);
								btnGuardar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(list.getSelectedValue() != null) {
											try {
												
												Modulo mod = new Modulo(list.getSelectedValue());
												mod.setAlfa(Double.parseDouble(textField_alpha.getText()));
												mod.setBeta(Double.parseDouble(textField_beta.getText()));
												mod.setGamma(Double.parseDouble(textField_gamma.getText()));
												mod.setKappa(Double.parseDouble(textField_kappa.getText()));
												mod.setTecnologia(textField_tecnologia.getText()); //es un string(?)
												mod.setTemperaturaNOCT(Double.parseDouble(textField_tempNOCT.getText()));
												mod.setIscNOCT(Double.parseDouble(textField_iscNOCT.getText()));
												mod.setVocNOCT(Double.parseDouble(textField_vocNOCT.getText()));
												mod.setPmaxNOCT(Double.parseDouble(textField_pmaxNOCT.getText()));
												mod.setIpmaxNOCT(Double.parseDouble(textField_ipmaxNOCT.getText()));
												mod.setVpmaxNOCT(Double.parseDouble(textField_vpmaxNOCT.getText()));
												mod.setRendimiento(Double.parseDouble(textField_rendimiento.getText()));
												mod.setIdealidad(Double.parseDouble(textField_idealidad.getText()));
												mod.setResistencia(Double.parseDouble(textField_resistencia.getText()));
												mod.setMinISC(Double.parseDouble(textField_minISC.getText()));
												mod.setMinVOC(Double.parseDouble(textField_minVOC.getText()));
												mod.setMinPMAX(Double.parseDouble(textField_minPMAX.getText()));
												mod.setMinFF(Double.parseDouble(textField_minFF.getText()));
												mod.setCelSerie(Integer.parseInt(textField_celulasEnSerie.getText()));
												mod.setCelParalelo(Integer.parseInt(textField_celulasEnParalelo.getText()));
												
												panelModulo.setVisible(true);
												panelEditar.setVisible(false);
												
											} catch (ClassNotFoundException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
										}
									}
								});
								panelEditar.add(btnGuardar);
								
								
								JButton btnAtrs = new JButton("Atr\u00E1s");
								btnAtrs.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										panelModulo.setVisible(true);
										panelEditar.setVisible(false);
									}
								});
								btnAtrs.setBounds(140, 347, 89, 23);
								panelEditar.add(btnAtrs);
								
								textField_tecnologia = new JTextField();
								textField_tecnologia.setColumns(10);
								textField_tecnologia.setBounds(127, 133, 86, 20);
								panelEditar.add(textField_tecnologia);
								
								JLabel lblTecnologia = new JLabel("tecnologia:");
								lblTecnologia.setBounds(10, 136, 71, 14);
								panelEditar.add(lblTecnologia);
								
								lblTemperaturaNocturna = new JLabel("Temperatura NOCT:");
								lblTemperaturaNocturna.setBounds(10, 161, 118, 14);
								panelEditar.add(lblTemperaturaNocturna);
								
								textField_tempNOCT = new JTextField();
								textField_tempNOCT.setColumns(10);
								textField_tempNOCT.setBounds(127, 158, 86, 20);
								panelEditar.add(textField_tempNOCT);
								
								lblIscNOCT = new JLabel("ISC NOCT:");
								lblIscNOCT.setBounds(10, 186, 71, 14);
								panelEditar.add(lblIscNOCT);
								
								textField_iscNOCT = new JTextField();
								textField_iscNOCT.setColumns(10);
								textField_iscNOCT.setBounds(127, 183, 86, 20);
								panelEditar.add(textField_iscNOCT);
								
								lblVocNOCT = new JLabel("VOC NOCT:");
								lblVocNOCT.setBounds(10, 211, 86, 14);
								panelEditar.add(lblVocNOCT);
								
								textField_vocNOCT = new JTextField();
								textField_vocNOCT.setColumns(10);
								textField_vocNOCT.setBounds(127, 208, 86, 20);
								panelEditar.add(textField_vocNOCT);
								
								lblpmaxNOCT = new JLabel("PMAX NOCT:");
								lblpmaxNOCT.setBounds(10, 236, 71, 14);
								panelEditar.add(lblpmaxNOCT);
								
								textField_pmaxNOCT = new JTextField();
								textField_pmaxNOCT.setColumns(10);
								textField_pmaxNOCT.setBounds(127, 233, 86, 20);
								panelEditar.add(textField_pmaxNOCT);
								
								lvlIpmax_NOCT = new JLabel("IPMAX NOCT:");
								lvlIpmax_NOCT.setBounds(10, 261, 71, 14);
								panelEditar.add(lvlIpmax_NOCT);
								
								textField_ipmaxNOCT = new JTextField();
								textField_ipmaxNOCT.setColumns(10);
								textField_ipmaxNOCT.setBounds(127, 258, 86, 20);
								panelEditar.add(textField_ipmaxNOCT);
								
								lblVpmax = new JLabel("VPMAX NOCT:");
								lblVpmax.setBounds(10, 286, 86, 14);
								panelEditar.add(lblVpmax);
								
								textField_vpmaxNOCT = new JTextField();
								textField_vpmaxNOCT.setColumns(10);
								textField_vpmaxNOCT.setBounds(127, 283, 86, 20);
								panelEditar.add(textField_vpmaxNOCT);
								
								lblRendimiento = new JLabel("Rendimiento:");
								lblRendimiento.setBounds(238, 36, 71, 14);
								panelEditar.add(lblRendimiento);
								
								textField_rendimiento = new JTextField();
								textField_rendimiento.setColumns(10);
								textField_rendimiento.setBounds(352, 33, 86, 20);
								panelEditar.add(textField_rendimiento);
								
								lblIdealidad = new JLabel("Idealidad:");
								lblIdealidad.setBounds(238, 61, 71, 14);
								panelEditar.add(lblIdealidad);
								
								lblResistencia = new JLabel("Resistencia:");
								lblResistencia.setBounds(238, 86, 71, 14);
								panelEditar.add(lblResistencia);
								
								lblMinIsc = new JLabel("min ISC");
								lblMinIsc.setBounds(238, 111, 59, 14);
								panelEditar.add(lblMinIsc);
								
								lblMinVOC = new JLabel("min VOC:");
								lblMinVOC.setBounds(238, 136, 71, 14);
								panelEditar.add(lblMinVOC);
								
								lblMinPmax = new JLabel("min PMAX:");
								lblMinPmax.setBounds(238, 161, 71, 14);
								panelEditar.add(lblMinPmax);
								
								lblMinFf = new JLabel("min FF:");
								lblMinFf.setBounds(238, 186, 46, 14);
								panelEditar.add(lblMinFf);
								
								lblCelulasEnSerie = new JLabel("celulas en serie:");
								lblCelulasEnSerie.setBounds(238, 211, 86, 14);
								panelEditar.add(lblCelulasEnSerie);
								
								lblCelulasEnParalelo = new JLabel("celulas en paralelo:");
								lblCelulasEnParalelo.setBounds(238, 236, 99, 14);
								panelEditar.add(lblCelulasEnParalelo);
								
								textField_celulasEnParalelo = new JTextField();
								textField_celulasEnParalelo.setColumns(10);
								textField_celulasEnParalelo.setBounds(352, 233, 86, 20);
								panelEditar.add(textField_celulasEnParalelo);
								
								textField_celulasEnSerie = new JTextField();
								textField_celulasEnSerie.setColumns(10);
								textField_celulasEnSerie.setBounds(352, 208, 86, 20);
								panelEditar.add(textField_celulasEnSerie);
								
								textField_minFF = new JTextField();
								textField_minFF.setColumns(10);
								textField_minFF.setBounds(352, 183, 86, 20);
								panelEditar.add(textField_minFF);
								
								textField_minPMAX = new JTextField();
								textField_minPMAX.setColumns(10);
								textField_minPMAX.setBounds(352, 158, 86, 20);
								panelEditar.add(textField_minPMAX);
								
								textField_minVOC = new JTextField();
								textField_minVOC.setColumns(10);
								textField_minVOC.setBounds(352, 133, 86, 20);
								panelEditar.add(textField_minVOC);
								
								textField_minISC = new JTextField();
								textField_minISC.setColumns(10);
								textField_minISC.setBounds(352, 108, 86, 20);
								panelEditar.add(textField_minISC);
								
								textField_resistencia = new JTextField();
								textField_resistencia.setColumns(10);
								textField_resistencia.setBounds(352, 83, 86, 20);
								panelEditar.add(textField_resistencia);
								
								textField_idealidad = new JTextField();
								textField_idealidad.setColumns(10);
								textField_idealidad.setBounds(352, 58, 86, 20);
								panelEditar.add(textField_idealidad);
								panelEditar.setVisible(false);
						
								JPanel panelInicial = new JPanel();
								panelInicial.setBounds(0, 0, 575, 336);
								contentPane.add(panelInicial);
								panelInicial.setLayout(null);
								//------TEXTO DE ABAJO		
								JTextPane txtpnBalBlaBla = new JTextPane();
								txtpnBalBlaBla.setBounds(188, 81, 161, 23);
								panelInicial.add(txtpnBalBlaBla);
								txtpnBalBlaBla.setBackground(UIManager.getColor("Button.background"));
								txtpnBalBlaBla.setText("Bienvenido a PVTranslator");
								//-----BOTON LISTA MODULOS
								btnListaModulo = new JButton("Lista Módulos");
								btnListaModulo.setBounds(200, 117, 134, 23);
								btnListaModulo.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {


										panelInicial.setVisible(false);
										panelModulo.setVisible(true);

									}
								});
								panelInicial.add(btnListaModulo);
								//------BOTON IMPORTAR MODULOS
								btnImportarModulo = new JButton("Importar Modulo");
								btnImportarModulo.setBounds(200, 165, 134, 23);
								btnImportarModulo.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {

										IntfzImportarModulo im = new IntfzImportarModulo();
										im.newScreen();
										dispose();
									}
								});
								panelInicial.add(btnImportarModulo);
								//------BOTON IMPORTAR CURVA
								btnImportarCurva = new JButton("Importar curva");
								btnImportarCurva.setBounds(200, 212, 134, 23);
								btnImportarCurva.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {

										IntfzImportarCurva im = new IntfzImportarCurva();
										im.newScreen();
										dispose();
									}
								});
								panelInicial.add(btnImportarCurva);
								
										//-----TITULO
										lblPvtranslator = new JLabel("PVTRANSLATOR");
										lblPvtranslator.setBounds(134, 6, 291, 85);
										panelInicial.add(lblPvtranslator);
										lblPvtranslator.setFont(new Font("Times New Roman", Font.PLAIN, 35));
										//-----IMAGEN LOGO
										lblLogoPyromik = new JLabel("");
										lblLogoPyromik.setBounds(10, 6, 90, 90);
										panelInicial.add(lblLogoPyromik);
										lblLogoPyromik.setIcon(new ImageIcon(logoPyromik));
										
										
												panelInicial.setVisible(true);
						//------PANEL DE MODULO
						panelModulo = new JPanel();
						panelModulo.setBounds(0, 0, 575, 336);
						contentPane.add(panelModulo);
						panelModulo.setLayout(null);
						panelModulo.setBorder(new EmptyBorder(5, 5, 5, 5));
						
								ListaModuloTitulo = new JLabel("LISTADO DE M\u00D3DULOS ");
								ListaModuloTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
								ListaModuloTitulo.setBounds(10, 0, 414, 42);
								panelModulo.add(ListaModuloTitulo);
								
										button = new JButton("Atr\u00E1s");
										button.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												panelModulo.setVisible(false);
												panelInicial.setVisible(true);
											}
										});
										button.setBounds(10, 258, 89, 23);
										panelModulo.add(button);
										

										Elegir = new JButton("Elegir");
										Elegir.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												try {
													
													if (list.getSelectedValue() != null) {
														IntfzModuloElegido me = new IntfzModuloElegido(list.getSelectedValue());
														me.newScreen(list.getSelectedValue());
													}
													
												} catch (ClassNotFoundException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												//-------
											}
										});
										
										
												Elegir.setBounds(430, 258, 89, 23);
												panelModulo.add(Elegir);
												
												list = new JList<String>();
												list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
												list.setBounds(20, 46, 475, 180);
												list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);    //solo se puede seleccionar uno
												list.setModel(modeloCamp);
												panelModulo.add(list);
												
												JButton btnEditar = new JButton("Editar");
												btnEditar.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														if(list.getSelectedValue() != null) {
															try {
																
																Modulo mod = new Modulo(list.getSelectedValue());
																//para que se vea el valor al entrar
																
																//textField_nombre.setText(mod.getNombre());
																textField_alpha.setText(String.valueOf(mod.getAlfa()));
																textField_beta.setText(String.valueOf(mod.getBeta()));
																textField_gamma.setText(String.valueOf(mod.getGamma()));
																textField_kappa.setText(String.valueOf(mod.getKappa()));
																textField_tecnologia.setText(String.valueOf(mod.getTecnologia()));
																textField_tempNOCT.setText(String.valueOf(mod.getTemperaturaNOCT()));
																textField_iscNOCT.setText(String.valueOf(mod.getIscNOCT()));
																textField_vocNOCT.setText(String.valueOf(mod.getVocNOCT()));
																textField_pmaxNOCT.setText(String.valueOf(mod.getPmaxNOCT()));
																textField_ipmaxNOCT.setText(String.valueOf(mod.getIpmaxNOCT()));
																textField_vpmaxNOCT.setText(String.valueOf(mod.getVpmaxNOCT()));
																textField_rendimiento.setText(String.valueOf(mod.getRendimiento()));
																textField_idealidad.setText(String.valueOf(mod.getIdealidad()));
																textField_resistencia.setText(String.valueOf(mod.getResistencia()));
																textField_minISC.setText(String.valueOf(mod.getMinISC()));
																textField_minVOC.setText(String.valueOf(mod.getMinVOC()));
																textField_minPMAX.setText(String.valueOf(mod.getMinPMAX()));
																textField_minFF.setText(String.valueOf(mod.getMinFF()));
																textField_celulasEnSerie.setText(String.valueOf(mod.getCelSerie()));
																textField_celulasEnParalelo.setText(String.valueOf(mod.getCelParalelo()));
																
																
																panelModulo.setVisible(false);
																panelEditar.setVisible(true);
																
															} catch (ClassNotFoundException e1) {
																// TODO Auto-generated catch block
																e1.printStackTrace();
															}
														}
													}
												});
												btnEditar.setBounds(326, 258, 89, 23);
												panelModulo.add(btnEditar);
												
												
		panelModulo.setVisible(false);
				//creamos el modelo
		        DefaultListModel<String> modeloCamp = new DefaultListModel<String>();
		        try {
					for(Modulo mod : Modulo.ListaModulo()) {
						modeloCamp.addElement(mod.getNombre());
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


	}
}


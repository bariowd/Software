import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import java.awt.Font;
import java.awt.Image;

public class caracteristicasReserva extends JDialog {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	Connection connection=conexionSQL.dbConector();
	/**
	 * Create the dialog.
	 */
	JRadioButton rdbtnNoQuieroParking;
	JRadioButton rdbtnReservaConPlaza;
	JCheckBox chckbxServicioDeHabitaciones;
	JCheckBox cbTV;
	JCheckBox cbMinibar;
	JCheckBox cbCaja;
	JCheckBox cbCama;
	JCheckBox chckbxMasaje;
	JCheckBox chckbxSauna;
	JCheckBox chckbxBaoTurco;
	JCheckBox chckbxJacuzzi;
	JCheckBox chckbxGimnasio;
	JSpinner numNinos;
	JSpinner numAdultos;
	JCheckBox cbAlcohol;
	JRadioButton rdbtnPensinMedia;
	JRadioButton rdbtnPensinCompleta;
	JRadioButton rdbtnRenaultMegane;
	JRadioButton rdbtnOpelAstra;
	JRadioButton rdbtnCitrenC;
	JRadioButton rdbtnNissanz;
	
	String dniCliente;
	int idReserva;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	
	/**
	 * @wbp.parser.constructor
	 */
	public caracteristicasReserva(String dniC) {

		dniCliente=dniC;
		iniciar();
	}
	
	public caracteristicasReserva(String dniC, int idRes){
		dniCliente=dniC;
		idReserva=idRes;
		iniciar();		
	}
	
	public void iniciar(){


		setBounds(400, 175, 502, 331);
		setResizable(false);
		setTitle("Características de la Reserva");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		JTabbedPane tabbedPane;
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 269, 496, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						MetodosCaracteristicasR met=new MetodosCaracteristicasR(dniCliente,idReserva);
					/*	
						rdbtnNoQuieroParking;
						rdbtnReservaConPlaza;
						chckbxServicioDeHabitaciones;
						cbTV;
						cbMinibar;
					*/
						if(rdbtnReservaConPlaza.isSelected()){
							met.insertaExtra("Plaza de garaje", 15);
						}
						
						if(rdbtnCitrenC.isSelected()){
							met.insertaExtra("Alquiler Citroen C2", 32);
						}
						if(rdbtnNissanz.isSelected()){
							met.insertaExtra("Alquiler Nissan 350Z", 50);													
						}
						
						if(rdbtnOpelAstra.isSelected()){
							met.insertaExtra("Alquiler Opel Astra", 35);
						}
						
						if(rdbtnRenaultMegane.isSelected()){
							met.insertaExtra("Alquiler Renault Megane", 35);
						}
						
						if(chckbxServicioDeHabitaciones.isSelected()){
							met.insertaExtra("Servicio de habitaciones", 18);
						}
						if(cbTV.isSelected()){
							met.insertaExtra("Television de pago", 8);
						}
						if(cbMinibar.isSelected()){
							met.insertaExtra("Minibar", 20);
						}
						if(cbCaja.isSelected()){
							met.insertaExtra("Caja fuerte", 12);
						}
						if(cbCama.isSelected()){
							met.insertaExtra("Cama supletoria", 12);
						}
						if(chckbxMasaje.isSelected()){
							met.insertaExtra("Masaje", 35);
						}
						if(chckbxSauna.isSelected()){
							met.insertaExtra("Sauna", 18);
						}
						if(chckbxBaoTurco.isSelected()){
							met.insertaExtra("Bano Turco", 18);
						}
						if(chckbxJacuzzi.isSelected()){
							met.insertaExtra("Jacuzzi", 20);
						}
						if(chckbxGimnasio.isSelected()){
							met.insertaExtra("Gimnasio", 5);
						}
						
						if(cbAlcohol.isSelected()){
							met.insertaExtra("Pension con bebidas alcoholicas", 15);
						}
						if(rdbtnPensinCompleta.isSelected()){
							met.insertaExtra("Pension completa", 30);
	
						}
						if(rdbtnPensinMedia.isSelected()){
							met.insertaExtra("Media pension", 15);
						}
						
						met.insertaExtra("Numero de ninos", Integer.parseInt(numNinos.getValue().toString()));
						met.insertaExtra("Numero de adultos", Integer.parseInt(numAdultos.getValue().toString()));
						
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 496, 269);
			getContentPane().add(tabbedPane);
			
			JPanel panel1=new JPanel();
			panel1.setLayout(null);
			
			tabbedPane.addTab("Comedor", panel1);
			
			rdbtnPensinCompleta = new JRadioButton("Pensi\u00F3n completa");
			buttonGroup.add(rdbtnPensinCompleta);
			rdbtnPensinCompleta.setBounds(35, 89, 174, 23);
			panel1.add(rdbtnPensinCompleta);
			
			rdbtnPensinMedia = new JRadioButton("Pensi\u00F3n media");
			rdbtnPensinMedia.setSelected(true);
			buttonGroup.add(rdbtnPensinMedia);
			rdbtnPensinMedia.setBounds(35, 123, 174, 23);
			panel1.add(rdbtnPensinMedia);
			
			JPanel panel = new JPanel();
			panel.setBounds(10, 63, 231, 100);
			panel1.add(panel);
			panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione su tipo de pensión:"));
			panel.setLayout(null);
			
			cbAlcohol = new JCheckBox("Extra: Bebidas alcoh\u00F3licas. (15\u20AC)");
			cbAlcohol.setBounds(20, 170, 221, 23);
			panel1.add(cbAlcohol);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(263, 63, 218, 100);
			panel_1.setBorder(javax.swing.BorderFactory.createTitledBorder("Número de personas:"));
			panel1.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblAdultos = new JLabel("Adultos:");
			lblAdultos.setBounds(27, 29, 93, 14);
			panel_1.add(lblAdultos);
			
			JLabel lblNios = new JLabel("Ni\u00F1os:");
			lblNios.setBounds(27, 61, 93, 14);
			panel_1.add(lblNios);
			
			SpinnerModel smA = new SpinnerNumberModel(1, 1, 4, 1);
			SpinnerModel smN = new SpinnerNumberModel(0, 0, 4, 1);
			
			numAdultos = new JSpinner(smA);
			numAdultos.setBounds(115, 26, 93, 20);
			panel_1.add(numAdultos);
			
			numNinos = new JSpinner(smN);
			numNinos.setBounds(115, 58, 93, 20);
			panel_1.add(numNinos);
			
			JLabel lblPrecios = new JLabel("Precios:");
			lblPrecios.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblPrecios.setBounds(247, 170, 234, 23);
			panel1.add(lblPrecios);
			
			JLabel lblPensionCompletaAdultos = new JLabel("Pension completa: Adultos 30, Ni\u00F1os 20");
			lblPensionCompletaAdultos.setBounds(247, 195, 234, 14);
			panel1.add(lblPensionCompletaAdultos);
			
			JLabel lblNewLabel = new JLabel("Pensi\u00F3n media: Adultos 15, Ni\u00F1os 10");
			lblNewLabel.setBounds(247, 216, 234, 14);
			panel1.add(lblNewLabel);
			
			JLabel lblSeleccioneSuTipo = new JLabel("Seleccione su tipo de pensi\u00F3n durante su estancia en el hotel:");
			lblSeleccioneSuTipo.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSeleccioneSuTipo.setBounds(10, 11, 471, 32);
			panel1.add(lblSeleccioneSuTipo);
			
			
			
			JPanel panel2=new JPanel();
			panel2.setLayout(null);
			
			tabbedPane.addTab("Gimnasio & Balneario", panel2);
			
			chckbxGimnasio = new JCheckBox("Gimnasio. (5\u20AC)");
			chckbxGimnasio.setBounds(20, 63, 124, 23);
			panel2.add(chckbxGimnasio);
			
			chckbxJacuzzi = new JCheckBox("Jacuzzi. (20\u20AC)");
			chckbxJacuzzi.setBounds(20, 117, 124, 23);
			panel2.add(chckbxJacuzzi);
			
			chckbxBaoTurco = new JCheckBox("Ba\u00F1o Turco. (18\u20AC)");
			chckbxBaoTurco.setBounds(253, 63, 175, 23);
			panel2.add(chckbxBaoTurco);
			
			chckbxSauna = new JCheckBox("Sauna. (18\u20AC)");
			chckbxSauna.setBounds(253, 103, 97, 23);
			panel2.add(chckbxSauna);
			
			chckbxMasaje = new JCheckBox("Masajes. (35\u20AC)");
			chckbxMasaje.setBounds(20, 169, 124, 23);
			panel2.add(chckbxMasaje);
			
			JLabel lblParaHacerMs = new JLabel("<html><body>Para hacer m\u00E1s agradable su estancia, dispone de<br>un gimnasio y un balneario:</body></html>");
			lblParaHacerMs.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblParaHacerMs.setBounds(10, 11, 471, 45);
			panel2.add(lblParaHacerMs);
			
			JLabel lblGYM = new JLabel("");
			Image img3G=new ImageIcon(this.getClass().getResource("/gym.png")).getImage();
			lblGYM.setIcon(new ImageIcon(img3G));
			lblGYM.setBounds(150, 48, 75, 56);
			panel2.add(lblGYM);
			
			JLabel lblMSJ = new JLabel("");
			Image imgM=new ImageIcon(this.getClass().getResource("/masaje.png")).getImage();
			lblMSJ.setIcon(new ImageIcon(imgM));
			lblMSJ.setBounds(152, 130, 91, 86);
			panel2.add(lblMSJ);
			
			JLabel lblSPA = new JLabel("");
			Image imgS=new ImageIcon(this.getClass().getResource("/spa.jpg")).getImage();
			lblSPA.setIcon(new ImageIcon(imgS));
			lblSPA.setBounds(253, 144, 197, 86);
			panel2.add(lblSPA);
			
			JPanel panel3=new JPanel();
			panel3.setLayout(null);
			JLabel et_p3=new JLabel("<html><body>Dispone de una serie de complementos para mejorar su estancia en <br> el hotel, seleccione los que desee  durante su estancia: </body></html>");
			et_p3.setFont(new Font("Tahoma", Font.BOLD, 13));
			et_p3.setBounds(20, 11, 447, 56);
			panel3.add(et_p3);
			
			tabbedPane.addTab("Complementos de la habitación", panel3);
			
			cbCama = new JCheckBox("Cama Supletoria. (12\u20AC)");
			cbCama.setBounds(20, 74, 157, 23);
			panel3.add(cbCama);
			
			cbCaja = new JCheckBox("Caja fuerte. (12\u20AC)");
			cbCaja.setBounds(20, 110, 139, 23);
			panel3.add(cbCaja);
			
			cbMinibar = new JCheckBox("Minibar. (20\u20AC)");
			cbMinibar.setBounds(179, 74, 97, 23);
			panel3.add(cbMinibar);
			
			cbTV = new JCheckBox("TV de pago. (8\u20AC)");
			cbTV.setBounds(179, 110, 125, 23);
			panel3.add(cbTV);
			
			chckbxServicioDeHabitaciones = new JCheckBox("Servicio de habitaciones. (18\u20AC)");
			chckbxServicioDeHabitaciones.setBounds(296, 74, 189, 23);
			panel3.add(chckbxServicioDeHabitaciones);
			
			JLabel lblH = new JLabel("");
			Image imgH=new ImageIcon(this.getClass().getResource("/habitacion.jpg")).getImage();
			lblH.setIcon(new ImageIcon(imgH));
			lblH.setBounds(20, 140, 256, 90);
			panel3.add(lblH);
			
			JLabel lblB = new JLabel("");
			Image imgB=new ImageIcon(this.getClass().getResource("/bano.jpg")).getImage();
			lblB.setIcon(new ImageIcon(imgB));
			lblB.setBounds(296, 110, 185, 120);
			panel3.add(lblB);
			
			JPanel panel4=new JPanel();
			panel4.setLayout(null);
			JLabel et_p4=new JLabel("<html><body>\u00BFDispone ya de un veh\u00EDculo? Si no es as\u00ED puede alquilar <br>uno proporcionado por el hotel.</body></html>");
			et_p4.setFont(new Font("Tahoma", Font.BOLD, 13));
			et_p4.setBounds(10, 11, 471, 49);
			panel4.add(et_p4);
			
			tabbedPane.addTab("Parking", panel4);
			
			JLabel lblSiTieneVehculo = new JLabel("Si tiene veh\u00EDculo, reserve ahora su plaza de parking:");
			lblSiTieneVehculo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblSiTieneVehculo.setBounds(10, 161, 471, 30);
			panel4.add(lblSiTieneVehculo);
			
			rdbtnReservaConPlaza = new JRadioButton("Reservar plaza de parking. (15\u20AC)");
			buttonGroup_3.add(rdbtnReservaConPlaza);
			rdbtnReservaConPlaza.setBounds(32, 198, 210, 23);
			panel4.add(rdbtnReservaConPlaza);
			
			rdbtnNoQuieroParking = new JRadioButton("No quiero parking...");
			buttonGroup_3.add(rdbtnNoQuieroParking);
			rdbtnNoQuieroParking.setSelected(true);
			rdbtnNoQuieroParking.setBounds(283, 198, 165, 23);
			panel4.add(rdbtnNoQuieroParking);
			
			JLabel lblP = new JLabel("");
			Image imgP=new ImageIcon(this.getClass().getResource("/parking.jpg")).getImage();
			lblP.setIcon(new ImageIcon(imgP));
			lblP.setBounds(238, 42, 243, 106);
			panel4.add(lblP);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(10, 57, 220, 91);
			panel4.add(panel_2);
			panel_2.setLayout(null);
			
			rdbtnRenaultMegane = new JRadioButton("Ren.  Megane");
			buttonGroup_3.add(rdbtnRenaultMegane);
			rdbtnRenaultMegane.setBounds(0, 17, 109, 23);
			panel_2.add(rdbtnRenaultMegane);
			
			rdbtnOpelAstra = new JRadioButton("Opel Astra");
			buttonGroup_3.add(rdbtnOpelAstra);
			rdbtnOpelAstra.setBounds(0, 53, 109, 23);
			panel_2.add(rdbtnOpelAstra);
			
			rdbtnCitrenC = new JRadioButton("Citr\u00F6en C2");
			buttonGroup_3.add(rdbtnCitrenC);
			rdbtnCitrenC.setBounds(111, 17, 109, 23);
			panel_2.add(rdbtnCitrenC);
			
			rdbtnNissanz = new JRadioButton("Nissan 350Z");
			buttonGroup_3.add(rdbtnNissanz);
			rdbtnNissanz.setBounds(111, 53, 109, 23);
			panel_2.add(rdbtnNissanz);
			
		}
		
		
	
	}
}

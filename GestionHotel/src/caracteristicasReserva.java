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
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import java.awt.Font;

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
	String dniCliente;
	int idReserva;
	
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
							met.insertaExtra("Bano Turco", 20);
						}
						if(chckbxJacuzzi.isSelected()){
							met.insertaExtra("Jacuzzi", 30);
						}
						if(chckbxGimnasio.isSelected()){
							met.insertaExtra("Gimnasio", 10);
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
			
			cbAlcohol = new JCheckBox("Extra: Bebidas alcoh\u00F3licas");
			cbAlcohol.setBounds(35, 170, 206, 23);
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
			
			
			
			JPanel panel2=new JPanel();
			panel2.setLayout(null);
			
			tabbedPane.addTab("Gimnasio & Balneario", panel2);
			
			chckbxGimnasio = new JCheckBox("Gimnasio");
			chckbxGimnasio.setBounds(51, 63, 97, 23);
			panel2.add(chckbxGimnasio);
			
			chckbxJacuzzi = new JCheckBox("Jacuzzi");
			chckbxJacuzzi.setBounds(51, 117, 97, 23);
			panel2.add(chckbxJacuzzi);
			
			chckbxBaoTurco = new JCheckBox("Ba\u00F1o Turco");
			chckbxBaoTurco.setBounds(236, 63, 97, 23);
			panel2.add(chckbxBaoTurco);
			
			chckbxSauna = new JCheckBox("Sauna");
			chckbxSauna.setBounds(236, 117, 97, 23);
			panel2.add(chckbxSauna);
			
			chckbxMasaje = new JCheckBox("Masajes");
			chckbxMasaje.setBounds(51, 175, 97, 23);
			panel2.add(chckbxMasaje);
			
			JPanel panel3=new JPanel();
			panel3.setLayout(null);
			JLabel et_p3=new JLabel("<html><body>Dispone de una serie de complementos para mejorar su estancia en <br> el hotel, seleccione los que desee  durante su estancia: </body></html>");
			et_p3.setFont(new Font("Tahoma", Font.PLAIN, 12));
			et_p3.setBounds(20, 11, 447, 56);
			panel3.add(et_p3);
			
			tabbedPane.addTab("Complementos de la habitación", panel3);
			
			cbCama = new JCheckBox("Cama Supletoria");
			cbCama.setBounds(20, 100, 139, 23);
			panel3.add(cbCama);
			
			cbCaja = new JCheckBox("Caja fuerte");
			cbCaja.setBounds(20, 156, 97, 23);
			panel3.add(cbCaja);
			
			cbMinibar = new JCheckBox("Minibar");
			cbMinibar.setBounds(192, 100, 97, 23);
			panel3.add(cbMinibar);
			
			cbTV = new JCheckBox("TV de pago");
			cbTV.setBounds(192, 156, 97, 23);
			panel3.add(cbTV);
			
			chckbxServicioDeHabitaciones = new JCheckBox("Servicio de habitaciones");
			chckbxServicioDeHabitaciones.setBounds(334, 100, 151, 23);
			panel3.add(chckbxServicioDeHabitaciones);
			
			JPanel panel4=new JPanel();
			panel4.setLayout(null);
			JLabel et_p4=new JLabel("<html><body>\u00BFDispone ya de un veh\u00EDculo? Si no es as\u00ED puede alquilar <br>uno proporcionado por el hotel.</body></html>");
			et_p4.setFont(new Font("Tahoma", Font.BOLD, 14));
			et_p4.setBounds(10, 11, 471, 49);
			panel4.add(et_p4);
			
			tabbedPane.addTab("Parking", panel4);
			
			JLabel lblSiTieneVehculo = new JLabel("Si tiene veh\u00EDculo, reserve ahora su plaza de parking:");
			lblSiTieneVehculo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblSiTieneVehculo.setBounds(10, 130, 471, 30);
			panel4.add(lblSiTieneVehculo);
			
			rdbtnReservaConPlaza = new JRadioButton("Reservar plaza de parking.");
			buttonGroup_1.add(rdbtnReservaConPlaza);
			rdbtnReservaConPlaza.setBounds(33, 178, 210, 23);
			panel4.add(rdbtnReservaConPlaza);
			
			rdbtnNoQuieroParking = new JRadioButton("No quiero parking...");
			rdbtnNoQuieroParking.setSelected(true);
			buttonGroup_1.add(rdbtnNoQuieroParking);
			rdbtnNoQuieroParking.setBounds(284, 178, 165, 23);
			panel4.add(rdbtnNoQuieroParking);
			
		}
		
		
	
	}
}

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
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
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

	/**
	 * Create the dialog.
	 */
	public caracteristicasReserva() {

		setBounds(400, 175, 502, 331);
		setResizable(false);
		setTitle("Caracter�sticas de la Reserva");
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
			JLabel et_p1=new JLabel("Estas en el panel 1");
			et_p1.setBounds(200, 5, 165, 14);
			panel1.add(et_p1);
			
			tabbedPane.addTab("Comedor", panel1);
			
			JRadioButton rdbtnPensinCompleta = new JRadioButton("Pensi\u00F3n completa");
			buttonGroup.add(rdbtnPensinCompleta);
			rdbtnPensinCompleta.setBounds(35, 89, 174, 23);
			panel1.add(rdbtnPensinCompleta);
			
			JRadioButton rdbtnPensinMedia = new JRadioButton("Pensi\u00F3n media");
			rdbtnPensinMedia.setSelected(true);
			buttonGroup.add(rdbtnPensinMedia);
			rdbtnPensinMedia.setBounds(35, 123, 174, 23);
			panel1.add(rdbtnPensinMedia);
			
			JPanel panel = new JPanel();
			panel.setBounds(10, 63, 231, 100);
			panel1.add(panel);
			panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione su tipo de pensi�n:"));
			
			JCheckBox chckbxNewCheckBox = new JCheckBox("Extra: Bebidas alcoh\u00F3licas");
			chckbxNewCheckBox.setBounds(35, 170, 206, 23);
			panel1.add(chckbxNewCheckBox);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(263, 63, 218, 100);
			panel_1.setBorder(javax.swing.BorderFactory.createTitledBorder("N�mero de personas:"));
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
			
			JSpinner numAdultos = new JSpinner(smA);
			numAdultos.setBounds(115, 26, 93, 20);
			panel_1.add(numAdultos);
			
			JSpinner numNinos = new JSpinner(smN);
			numNinos.setBounds(115, 58, 93, 20);
			panel_1.add(numNinos);
			
			
			
			JPanel panel2=new JPanel();
			panel2.setLayout(null);
			
			tabbedPane.addTab("Gimnasio & Balneario", panel2);
			
			JCheckBox chckbxGimnasio = new JCheckBox("Gimnasio");
			chckbxGimnasio.setBounds(51, 63, 97, 23);
			panel2.add(chckbxGimnasio);
			
			JCheckBox chckbxJakuzzi = new JCheckBox("Jakuzzi");
			chckbxJakuzzi.setBounds(51, 117, 97, 23);
			panel2.add(chckbxJakuzzi);
			
			JCheckBox chckbxBaoTurco = new JCheckBox("Ba\u00F1o Turco");
			chckbxBaoTurco.setBounds(236, 63, 97, 23);
			panel2.add(chckbxBaoTurco);
			
			JCheckBox chckbxSauna = new JCheckBox("Sauna");
			chckbxSauna.setBounds(236, 117, 97, 23);
			panel2.add(chckbxSauna);
			
			JCheckBox chckbxMasaje = new JCheckBox("Masajes");
			chckbxMasaje.setBounds(51, 175, 97, 23);
			panel2.add(chckbxMasaje);
			
			JPanel panel3=new JPanel();
			panel3.setLayout(null);
			JLabel et_p3=new JLabel("<html><body>Dispone de una serie de complementos para mejorar su estancia en <br> el hotel, seleccione los que desee  durante su estancia: </body></html>");
			et_p3.setFont(new Font("Tahoma", Font.PLAIN, 12));
			et_p3.setBounds(20, 11, 447, 56);
			panel3.add(et_p3);
			
			tabbedPane.addTab("Complementos de la habitaci�n", panel3);
			
			JCheckBox cbCama = new JCheckBox("Cama Supletoria");
			cbCama.setBounds(20, 100, 139, 23);
			panel3.add(cbCama);
			
			JCheckBox cbCaja = new JCheckBox("Caja fuerte");
			cbCaja.setBounds(20, 156, 97, 23);
			panel3.add(cbCaja);
			
			JCheckBox cbMinibar = new JCheckBox("Minibar");
			cbMinibar.setBounds(192, 100, 97, 23);
			panel3.add(cbMinibar);
			
			JCheckBox cbTV = new JCheckBox("TV de pago");
			cbTV.setBounds(192, 156, 97, 23);
			panel3.add(cbTV);
			
			JCheckBox chckbxServicioDeHabitaciones = new JCheckBox("Servicio de habitaciones");
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
			
			JRadioButton rdbtnReservaConPlaza = new JRadioButton("Reservar plaza de parking.");
			buttonGroup_1.add(rdbtnReservaConPlaza);
			rdbtnReservaConPlaza.setBounds(33, 178, 210, 23);
			panel4.add(rdbtnReservaConPlaza);
			
			JRadioButton rdbtnNoQuieroParking = new JRadioButton("No quiero parking...");
			rdbtnNoQuieroParking.setSelected(true);
			buttonGroup_1.add(rdbtnNoQuieroParking);
			rdbtnNoQuieroParking.setBounds(284, 178, 165, 23);
			panel4.add(rdbtnNoQuieroParking);
			
		}
		
		
	}
}

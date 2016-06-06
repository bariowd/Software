import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;


@SuppressWarnings("serial")
public class modificarReserva extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String idRes;
	String hab;
	Connection connection=conexionSQL.dbConector();
	private JTextField textDNI;
	private JTextField textFechaE;
	private JTextField textFechaS;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox;

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("rawtypes")
	public modificarReserva(String idReserva) {
		idRes=idReserva;
		setBounds(400, 175, 367, 358);
		setResizable(false);
		setTitle("Modificación de reservas:");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(27, 33, 46, 14);
		contentPanel.add(lblDni);
		
		JLabel lblFechaDeEntrada = new JLabel("Fecha de entrada:");
		lblFechaDeEntrada.setBounds(27, 58, 108, 14);
		contentPanel.add(lblFechaDeEntrada);
		
		JLabel lblFechaDeSalida = new JLabel("Fecha de salida: ");
		lblFechaDeSalida.setBounds(27, 83, 108, 14);
		contentPanel.add(lblFechaDeSalida);
		
		JLabel lblHabitacin = new JLabel("Habitaci\u00F3n: ");
		lblHabitacin.setBounds(27, 108, 108, 14);
		contentPanel.add(lblHabitacin);
		
		textDNI = new JTextField();
		textDNI.setBounds(150, 30, 86, 20);
		contentPanel.add(textDNI);
		textDNI.setColumns(10);
		textDNI.setEditable(false);
		
		textFechaE = new JTextField();
		textFechaE.setBounds(150, 55, 86, 20);
		contentPanel.add(textFechaE);
		textFechaE.setColumns(10);
		textFechaE.setEditable(false);
		
		textFechaS = new JTextField();
		textFechaS.setBounds(150, 80, 86, 20);
		contentPanel.add(textFechaS);
		textFechaS.setColumns(10);
		textFechaS.setEditable(false);
		
		comboBox = new JComboBox();
		comboBox.setBounds(150, 105, 86, 20);
		contentPanel.add(comboBox);
		
		JButton btnModificarE = new JButton("Modificar");
		btnModificarE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarFecha mFecha=new modificarFecha("Modificar fecha de entrada");
				mFecha.setModal(true);
				mFecha.setVisible(true);
				String newFecha=mFecha.getFecha();
				if(newFecha!=null){
					textFechaE.setText(newFecha);
				}
			}
		});
		btnModificarE.setBounds(256, 54, 89, 23);
		contentPanel.add(btnModificarE);
		
		JButton btnModificarS = new JButton("Modificar");
		btnModificarS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarFecha mFecha=new modificarFecha("Modificar fecha de salida");
				mFecha.setModal(true);
				mFecha.setVisible(true);
				String newFecha=mFecha.getFecha();
				if(newFecha!=null){
					textFechaS.setText(newFecha);
				}
			}
		});
		btnModificarS.setBounds(256, 83, 89, 23);
		contentPanel.add(btnModificarS);
		
		JButton btnHelp = new JButton("");
		Image img4=new ImageIcon(this.getClass().getResource("/interrogante.png")).getImage();
		btnHelp.setIcon(new ImageIcon(img4));
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda ay=new ayuda("ayudaModificarReserva");
				ay.setVisible(true);
			}
		});
		btnHelp.setBounds(306, 11, 39, 36);
		contentPanel.add(btnHelp);
		muestraDatosActuales();
		comboHabitaciones();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(textDNI.getText().length()==0){
							JOptionPane.showMessageDialog(null, "El DNI no puede estar vacio.");
						}else{
							try{
								String query="update Reservas set DNI='"+textDNI.getText()+"' ,fecha_entrada='"+textFechaE.getText()+"' ,fecha_salida='"+textFechaS.getText()+"' ,nHabitacion='"+comboBox.getSelectedItem()+"' where idReserva='"+idRes+"'";
								PreparedStatement pst=connection.prepareStatement(query);
								
								
								pst.execute();
								
								pst.close();
							}catch(Exception e1){
								e1.printStackTrace();
							}
							
								if(hab!=comboBox.getSelectedItem()){
									try{
										String query="update Habitaciones set estado='ocupado' where nHabitacion='"+comboBox.getSelectedItem()+"'";
										PreparedStatement pst=connection.prepareStatement(query);
										pst.execute();
										
										pst.close();
									}catch(Exception e1){
										e1.printStackTrace();
									}
									
									try{
										String query="update Habitaciones set estado='libre' where nHabitacion='"+hab+"'";
										PreparedStatement pst=connection.prepareStatement(query);
										pst.execute();
										
										pst.close();
									}catch(Exception e1){
										e1.printStackTrace();
									}
								}
							}
							
							dispose();
						}
						
					}
				);
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void muestraDatosActuales(){
		try{
			String query="SELECT * FROM Reservas where idReserva='"+idRes+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			
				textDNI.setText(rs.getString("DNI"));
				textFechaE.setText(rs.getString("fecha_entrada"));
				textFechaS.setText(rs.getString("fecha_salida"));
				hab=rs.getString("nHabitacion");
				

			rs.close();
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void comboHabitaciones(){
		comboBox.setVisible(true);
		comboBox.addItem(hab);
		try{
			String query="SELECT * FROM Habitaciones where estado='libre'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				comboBox.addItem(rs.getString("nHabitacion"));
			}

			rs.close();
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}

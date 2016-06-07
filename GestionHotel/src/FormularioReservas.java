import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import javax.swing.JComboBox;

import java.awt.Label;
import java.awt.Font;


@SuppressWarnings("serial")
public class FormularioReservas extends JFrame {

	
	Connection connection=conexionSQL.dbConector();
	
	metodosReservas metodosR=new metodosReservas();
	
	String DNIcliente=null;
	int idAdmin=0;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textDNI;
	JLabel lblSeleccioneHabitacion ;
	@SuppressWarnings("rawtypes")
	JComboBox comboBox;
	@SuppressWarnings("rawtypes")
	JComboBox comboBoxDNI;
	String fechaentrada;
	String fechasalida;
	
	int contador=0;//contador para distinguir si es la fecha deentrada si es un 0, o un 1 si es fecha de salida

	public FormularioReservas(String DNI){
		DNIcliente=DNI;
		inicializar();
	}
	/**
	 * Create the dialog.
	 */
	public FormularioReservas(int idAd){
		idAdmin=idAd;
		inicializar();
	}
	
	public FormularioReservas(){
		inicializar();
	}
	@SuppressWarnings("rawtypes")
	public void inicializar() {
		setBounds(400, 175, 423, 520);
		setResizable(false);
		setTitle("Formulario para la Reserva");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblDni;
		
		textDNI = new JTextField();
		textDNI.setBounds(72, 51, 86, 20);
		contentPanel.add(textDNI);
		textDNI.setColumns(10);
		if(DNIcliente!=null){
			textDNI.setText(DNIcliente);
			textDNI.setEditable(false);
		}
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(30, 157, 345, 200);
		contentPanel.add(calendar);
		
		JLabel lblSeleccioneFechaDe = new JLabel("Seleccione fecha de entrada:");
		lblSeleccioneFechaDe.setBounds(32, 129, 173, 14);
		contentPanel.add(lblSeleccioneFechaDe);
		
		lblSeleccioneHabitacion = new JLabel("Habitaciones disponibles para el d\u00EDa:");
		lblSeleccioneHabitacion.setBounds(32, 93, 281, 14);
		contentPanel.add(lblSeleccioneHabitacion);
		lblSeleccioneHabitacion.setVisible(false);
		
		comboBox = new JComboBox();
		comboBox.setBounds(271, 92, 105, 20);
		contentPanel.add(comboBox);
		comboBox.setVisible(false);
		
		Label label = new Label("DNI:");
		label.setBounds(32, 49, 24, 22);
		contentPanel.add(label);
		
		Label label_1 = new Label("DNI registrados:");
		label_1.setBounds(181, 49, 88, 22);
		contentPanel.add(label_1);
		
		JButton btnHelp = new JButton("");
		Image img4=new ImageIcon(this.getClass().getResource("/interrogante.png")).getImage();
		btnHelp.setIcon(new ImageIcon(img4));
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda ay=new ayuda("ayudaReserva");
				ay.setVisible(true);
			}
		});
		btnHelp.setBounds(33, 445, 33, 30);
		contentPanel.add(btnHelp);
		{
			JButton okButton = new JButton("Siguiente");
			okButton.setBounds(163, 454, 106, 23);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
																	
					if(textDNI.getText().length()==0&&comboBoxDNI.getSelectedItem().toString()=="<Vacio>"){
						JOptionPane.showMessageDialog(null, "El DNI del cliente no puede ser un campo vacio.");
					}else{
						
						
						lblSeleccioneFechaDe.setVisible(false);
						JLabel label_2 = new JLabel("Seleccione fecha de salida:");
						label_2.setBounds(32, 129, 173, 14);
						contentPanel.add(label_2);
						
						if(contador==0){
							int day=calendar.getCalendar().get(Calendar.DAY_OF_MONTH);
							int month=calendar.getCalendar().get(Calendar.MONTH);
							month=month+1;
							int year=calendar.getCalendar().get(Calendar.YEAR);
							fechaentrada=day+"/"+month+"/"+year;
							System.out.println(fechaentrada);
							contador++;
							okButton.setText("Reservar");
							
							JOptionPane.showMessageDialog(null, "Seleccione fecha de salida y habitación");
							textDNI.setEditable(false);
							
							if(DNIcliente==null){
								comboBoxDNI.setEditable(false);
							}
							comboHabitaciones();
						}else{
							int day=calendar.getCalendar().get(Calendar.DAY_OF_MONTH);
							int month=calendar.getCalendar().get(Calendar.MONTH);
							month=month+1;
							int year=calendar.getCalendar().get(Calendar.YEAR);
							fechasalida=day+"/"+month+"/"+year;
							String dniC;
							if(DNIcliente!=null){
								dniC=DNIcliente;
							}else{
								if(textDNI.getText().length()==0){
									dniC=comboBoxDNI.getSelectedItem().toString();
								}else{
									dniC=textDNI.getText();
								}
							}
							
							metodosR.Reservar(fechaentrada, fechasalida, comboBox.getSelectedItem().toString(), idAdmin, dniC);
							dispose();
						}
						
						
						
					}
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.setBounds(274, 454, 101, 23);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		
		JLabel NotaPropiedadesReserva = new JLabel("Seleccione las caracteristicas de su reserva:");
		NotaPropiedadesReserva.setBounds(30, 357, 345, 20);
		contentPanel.add(NotaPropiedadesReserva);
		
		JButton btnComponentesR = new JButton("Caracter\u00EDsticas");
		btnComponentesR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Codigo para abrir las características de la reserva
				caracteristicasReserva car=new caracteristicasReserva();
				car.setVisible(true);
				car.setModal(true);

			}
		});
		btnComponentesR.setBounds(30, 388, 345, 21);
		contentPanel.add(btnComponentesR);
		
		JLabel lblNewLabel = new JLabel("(En caso contrario se le asignaran unas por defecto)");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblNewLabel.setBounds(30, 420, 345, 14);
		contentPanel.add(lblNewLabel);
		if(DNIcliente!=null){
			label_1.setVisible(false);
			 lblDni= new JLabel("Rellene con atención los datos requeridos para la reserva:");
				lblDni.setBounds(30, 11, 356, 20);
				contentPanel.add(lblDni);
		}else{
			 lblDni= new JLabel("Introduzca DNI o seleccione uno de los clientes ya registrados:");
				lblDni.setBounds(30, 11, 356, 20);
				contentPanel.add(lblDni);
		}
		
		if(DNIcliente==null){
			comboBoxDNI = new JComboBox();
			comboBoxDNI.setBounds(297, 51, 91, 20);
			contentPanel.add(comboBoxDNI);
			comboDNI();
		}
	}
	

	@SuppressWarnings("unchecked")
	public void comboHabitaciones(){
		
		lblSeleccioneHabitacion.setVisible(true);
		comboBox.setVisible(true);
		
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
	
	@SuppressWarnings("unchecked")
	public void comboDNI(){
		
	
		comboBoxDNI.setVisible(true);
		
		try{
			String query="SELECT * FROM Clientes";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			comboBoxDNI.addItem("<Vacio>");
			
			while(rs.next()){
				comboBoxDNI.addItem(rs.getString("DNI"));
			}

			rs.close();
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@SuppressWarnings("serial")
public class registroSistema extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField password2;
	private JTextField textUsuario;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textDNI;
	private JTextField textTelefono;
	private JPasswordField password1;
	Connection connection=conexionSQL.dbConector();

	/**
	 * Create the dialog.
	 */
	public registroSistema() {
		setBounds(375, 200, 422, 338);
		setResizable(false);
		setTitle("Formulario para registrarse en la aplicación");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Introduzca contrase\u00F1a:*");
		lblNewLabel.setBounds(10, 85, 221, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Repita la contrase\u00F1a:*");
		lblNewLabel_1.setBounds(10, 110, 221, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(11, 135, 220, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Apellidos:");
		lblNewLabel_3.setBounds(11, 160, 220, 14);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("DNI*");
		lblNewLabel_4.setBounds(11, 185, 220, 14);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Introduzca nombre usuario:*");
		lblNewLabel_5.setBounds(10, 58, 221, 14);
		contentPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tel\u00E9fono");
		lblNewLabel_6.setBounds(11, 210, 220, 14);
		contentPanel.add(lblNewLabel_6);
		
		JLabel lbllosCamposCon = new JLabel("NOTA: Los campos con (*) son obligatorios.");
		lbllosCamposCon.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lbllosCamposCon.setBounds(202, 242, 204, 14);
		contentPanel.add(lbllosCamposCon);
		
		password2 = new JPasswordField();
		password2.setBounds(205, 110, 190, 20);
		contentPanel.add(password2);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(205, 58, 190, 20);
		contentPanel.add(textUsuario);
		textUsuario.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(205, 135, 190, 20);
		contentPanel.add(textNombre);
		textNombre.setColumns(10);
		
		textApellidos = new JTextField();
		textApellidos.setBounds(205, 160, 190, 20);
		contentPanel.add(textApellidos);
		textApellidos.setColumns(10);
		
		textDNI = new JTextField();
		textDNI.setBounds(205, 185, 190, 20);
		contentPanel.add(textDNI);
		textDNI.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(205, 210, 190, 20);
		contentPanel.add(textTelefono);
		textTelefono.setColumns(10);
		
		password1 = new JPasswordField();
		password1.setBounds(205, 82, 190, 20);
		contentPanel.add(password1);
		
		JLabel lblRelleneLos = new JLabel("Rellene los siguientes datos para  registrarse en la aplicaci\u00F3n:");
		lblRelleneLos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRelleneLos.setBounds(21, 11, 385, 30);
		contentPanel.add(lblRelleneLos);
		
		JLabel lblWarning = new JLabel("");
		Image img3=new ImageIcon(this.getClass().getResource("/warning.png")).getImage();
		lblWarning.setIcon(new ImageIcon(img3));
		lblWarning.setBounds(184, 242, 15, 14);
		contentPanel.add(lblWarning);
		
		JButton btnHelp = new JButton("");
		Image img4=new ImageIcon(this.getClass().getResource("/interrogante.png")).getImage();
		btnHelp.setIcon(new ImageIcon(img4));
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ayuda ay=new ayuda("ayudaRegistro");
				ay.setVisible(true);
			}
		});
		btnHelp.setBounds(10, 235, 29, 30);
		contentPanel.add(btnHelp);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrarse");
				okButton.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
						public void actionPerformed(ActionEvent arg0) {
						
							int count=0;
								try{
		
									String query="SELECT * FROM Administradores WHERE usuario='"+textUsuario.getText()+"'";
									
									PreparedStatement pst=connection.prepareStatement(query);
									ResultSet rs=pst.executeQuery();
										while(rs.next()){
											count++;
										}
										
									query="SELECT * FROM Clientes WHERE usuario='"+textUsuario.getText()+"'";
									pst=connection.prepareStatement(query);
									rs=pst.executeQuery();
									
									while(rs.next()){
										count++;
									}
			
										rs.close();
										pst.close();
								}catch(Exception e){
									JOptionPane.showMessageDialog(null, e);
								}
										
								if(textUsuario.getText().length()==0||password1.getText().length()==0||password2.getText().length()==0||textDNI.getText().length()==0){
									JOptionPane.showMessageDialog(null, "Los campos obligatorios no pueden estar vacios");
									password1.setText("");
									password2.setText("");
								}else if(!isPasswordCorrect(password1.getPassword(), password2.getPassword())){
									JOptionPane.showMessageDialog(null, "¡Las contraseñas no coinciden!");
									password1.setText("");
									password2.setText("");
								}else if(count!=0){

									JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe dentro del sistema");
								}/*else if(textDNI.getText().length() != 9){
									JOptionPane.showMessageDialog(null, "El DNI no es válido.");
								}*/else{
									try{
										String query="insert into Clientes (usuario, pass, nombre, apellidos, DNI, telefono) values (?,?,?,?,?,?)";
										PreparedStatement pst=connection.prepareStatement(query);
										pst.setString(1, textUsuario.getText());
										pst.setString(2, password1.getText());
										pst.setString(3, textNombre.getText());
										pst.setString(4, textApellidos.getText());
										pst.setString(5, textDNI.getText());
										pst.setString(6, textTelefono.getText());
										
										pst.execute();
										
										pst.close();
										JOptionPane.showMessageDialog(null, "Se le ha registrado correctamente.");
										dispose();
										
									}catch(Exception e1){
										e1.printStackTrace();
									}
								}
						}
					
				});
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
	

		private boolean isPasswordCorrect(char[] j1,char[] j2) {
			boolean valor = true;
			int puntero = 0;
			if (j1.length != j2.length){
					valor = false;
			}
			else{
				while((valor)&&(puntero < j1.length)){
					if (j1[puntero] != j2[puntero]){
						valor = false;
					}	
					puntero++;
				}
			}
			return valor;
		}
	}

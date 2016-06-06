import java.awt.Image;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;

	Connection connect=conexionSQL.dbConector();;
	metodosLogin loggear=new metodosLogin();
	static JTextField txtUsuario;
	static JPasswordField jpassClave;
	static int tipoUsuario=-1;
	private JTextField textField;
	private JPasswordField passwordField;
	
	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(375, 200, 595, 326);
		setResizable(false);
		setTitle("Like at Home");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("USUARIO:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(310, 105, 109, 14);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(429, 97, 150, 25);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("CONTRASE\u00D1A:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(310, 139, 139, 14);
		contentPane.add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(429, 134, 150, 25);
		contentPane.add(passwordField);
		
		JLabel label_2 = new JLabel("");
		Image img2=new ImageIcon(this.getClass().getResource("/hotel.png")).getImage();
		label_2.setIcon(new ImageIcon(img2));
		label_2.setBounds(22, 11, 290, 240);
		contentPane.add(label_2);
		
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
	
					tipoUsuario = loggear.validar_ingreso(textField.getText(), passwordField.getText());
					if(tipoUsuario==1){
						adminInterfaz vista=new adminInterfaz(loggear.dameIDAdmin(textField.getText()));
						vista.setVisible(true);
						dispose();
						
					}else if(tipoUsuario==2){
						clienteInterfaz vista=new clienteInterfaz(loggear.dameDNI(textField.getText()));
						vista.setVisible(true);
						dispose();
					}
					textField.setText("");
					passwordField.setText("");
					
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 20));
		Image img=new ImageIcon(this.getClass().getResource("/check-icon.png")).getImage();
		button.setIcon(new ImageIcon(img));
		button.setBounds(450, 191, 129, 41);
		contentPane.add(button);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registroSistema reg=new registroSistema();
				reg.setVisible(true);
			}
		});
		btnRegistrarse.setBounds(450, 267, 129, 23);
		contentPane.add(btnRegistrarse);
		
		JButton btnAyuda = new JButton("");
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda ay=new ayuda("ayudaLogin");
				ay.setVisible(true);
			}
		});
		Image img9=new ImageIcon(this.getClass().getResource("/help.png")).getImage();
		btnAyuda.setIcon(new ImageIcon(img9));
		btnAyuda.setBounds(10, 257, 36, 33);
		contentPane.add(btnAyuda);
		
		JLabel lblanNoTienes = new JLabel("\u00BFA\u00FAn no tienes cuenta? \u00A1Reg\u00EDstrate!");
		lblanNoTienes.setBounds(236, 271, 213, 14);
		contentPane.add(lblanNoTienes);
		
		JLabel lblLikeAtHome = new JLabel("LIKE AT HOME:");
		lblLikeAtHome.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblLikeAtHome.setBounds(322, 27, 257, 59);
		contentPane.add(lblLikeAtHome);
		
		
		
	}
}

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class ayuda extends JDialog {

	private JPanel contentPane;
	String tipoAyuda;
	JTextPane textoAyuda;
	private JScrollPane scrollPane;
	/**
	 * Create the frame.
	 */
	public ayuda(String text) {
		tipoAyuda=text;
		setModal(true);
		//super(this, true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(375, 200, 240, 231);
		setResizable(false);
		setTitle("Ayuda");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 215, 146);
		contentPane.add(scrollPane);
		
		textoAyuda = new JTextPane();
		scrollPane.setViewportView(textoAyuda);
		textoAyuda.setEditable(false);
		
		
		JButton btnOK = new JButton("Entendido");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnOK.setBounds(104, 168, 121, 23);
		contentPane.add(btnOK);
		setTextAyuda();
	}
	
	public void setTextAyuda(){
		if(tipoAyuda.equals("ayudaLogin")){
			textoAyuda.setText("Desarrollado por Hoteles LikeAtHome. \n \n Versión 1.0 \n \n (c) Copyright LikeAtHome contributors and others  2015/2016.  All rights reserved.");
		}else if(tipoAyuda.equals("ayudaRegistro")){
			textoAyuda.setText("Se deben introducir todos los campos marcados con un '*' para realizar el registro. \n \n En caso"
					+ "de tener problemas contacte con nosotros. \n \n LikeAtHome@hoteles.com \n\n teléfono 987596021 ");
			
		}else if(tipoAyuda.equals("ayudaAdmin")){
			textoAyuda.setText("Dispone de las funciones de:\n 1-dar de baja a un cliente.\n 2-modificar una reserva.\n 3-realizar una reserva.\n 4-eliminar una reserva. \n \n "
					+ "No puede acceder directamente a la base de datos, en caso de problemas contacte con el desarrollador. ");
			
			
		}else if(tipoAyuda.equals("ayudaCliente")){
			textoAyuda.setText("Dispone de cuatro botones.\n 1- Realizar reserva\n 2- Modificar una reserva realizada previamente.\n "
					+ "3- Puede darse de baja en el sistema cuando usted desee.\n "
					+ "4- Eliminar una reserva que haya hecho anteriormente.\n\n"
					+ "En caso de tener problemas contacte con el administrador.");	
			
			
		}else if(tipoAyuda.equals("ayudaReserva")){
			textoAyuda.setText("Realice la reserva seleccionando la duración de la estancia y el número de habitación"
					+ "y el tipo que prefiere; puede elegir una suite y una habitación normal.\n\n"
					+ "En caso de tener problemas contacte con el administrador.");
		}else if(tipoAyuda.equals("ayudaModificarReserva")){
			textoAyuda.setText("Puede modificar la estancia en el hotel modificando las fechas y la habitación"
					+ " entre las disponibles.\n\n"
					+ "En caso de querer cambiar la reserva a nombre de otra persona contacte con el administrador. ");
		}
	}
}

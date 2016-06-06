
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class tiposHabitacion extends JFrame {

	private JPanel contentPane;
	
	public tiposHabitacion() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 387, 264);
		setTitle("Tipos de habitación");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHotel = new JLabel("");
		lblHotel.setBounds(10, 31, 354, 200);
		contentPane.add(lblHotel);
		Image img4=new ImageIcon(this.getClass().getResource("/tablaHab.PNG")).getImage();
		lblHotel.setIcon(new ImageIcon(img4));
		
		JLabel lblTipoDeHabitacin = new JLabel("Tipo de habitaci\u00F3n y su precio en Euros:");
		lblTipoDeHabitacin.setBounds(10, 11, 232, 14);
		contentPane.add(lblTipoDeHabitacin);
		
		
	}
}

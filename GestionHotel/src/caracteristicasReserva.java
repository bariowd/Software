import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class caracteristicasReserva extends JDialog {

	/**
	 * Create the dialog.
	 */
	public caracteristicasReserva() {

		setBounds(400, 175, 502, 331);
		setResizable(false);
		setTitle("Características de la Reserva");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 496, 269);
			getContentPane().add(tabbedPane);
			
			JPanel panel1=new JPanel();
			panel1.setLayout(null);
			JLabel et_p1=new JLabel("Estas en el panel 1");
			et_p1.setBounds(200, 5, 90, 14);
			panel1.add(et_p1);
			
			tabbedPane.addTab("Comedor", panel1);
			
			JPanel panel2=new JPanel();
			JLabel et_p2=new JLabel("Estas en el panel 2");
			panel2.add(et_p2);
			
			tabbedPane.addTab("Gimnasio & Valneario", panel2);
			

			//tabbedPane.addTab("Menu1", );
		}
		
	}
}

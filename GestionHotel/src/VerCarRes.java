import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VerCarRes extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public VerCarRes(String dniC, int idRes) {
		setBounds(100, 100, 450, 300);
		MetodosCaracteristicasR verC=new MetodosCaracteristicasR(dniC, idRes);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Características de la reserva");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTable caracteristicas=new JTable();
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 57, 414, 171);
			contentPanel.add(scrollPane);
			
			scrollPane.setViewportView(caracteristicas);
			caracteristicas.setModel(DbUtils.resultSetToTableModel(verC.verCaracteristicas()));
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}

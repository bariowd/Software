import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;


@SuppressWarnings("serial")
public class modificarFecha extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String fecha=null;


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	/**
	 * Create the dialog.
	 */
	public modificarFecha(String titulo) {
		setBounds(400, 175, 340, 352);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setTitle(titulo);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(30, 30, 250, 250);
		contentPanel.add(calendar);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Modificar fecha");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int day=calendar.getCalendar().get(Calendar.DAY_OF_MONTH);
						int month=calendar.getCalendar().get(Calendar.MONTH);
						month=month+1;
						int year=calendar.getCalendar().get(Calendar.YEAR);
						fecha=""+day+"/"+month+"/"+year+"";
						dispose();
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

}

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;

import net.proteanit.sql.DbUtils;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class clienteInterfaz extends JFrame {

	private JPanel contentPane;
	private JTable tableReservas;
	metodosCliente cliente;
	private JScrollPane scrollPane;
	private JButton btnRealizarEserva;
	tiposHabitacion habitaciones;

	/**
	 * Create the frame.
	 */
	public clienteInterfaz(String DNI) {

		cliente=new metodosCliente(DNI);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(375, 200, 717, 280);
		setResizable(false);
		setTitle("Mis reservas");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 692, 104);
		contentPane.add(scrollPane);
		
		tableReservas = new JTable();
		scrollPane.setViewportView(tableReservas);
		tableReservas.setModel(DbUtils.resultSetToTableModel(cliente.verReservas()));
		
		
		JLabel lblMisReservas = new JLabel("Mis reservas:");
		lblMisReservas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMisReservas.setBounds(41, 36, 136, 14);
		contentPane.add(lblMisReservas);
		
		JButton btnEliminarReserva = new JButton("Eliminar Reserva");
		btnEliminarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row;
				int resp;
				String idRes,nHab;
				try{
					row=tableReservas.getSelectedRow();
					if(row==-1){
						JOptionPane.showMessageDialog(null, "Debe seleccionar la reserva a eliminar.","Advertencia",JOptionPane.WARNING_MESSAGE);
					}else{
						resp=JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta reserva?","Eliminar reserva",JOptionPane.YES_NO_OPTION);
						if(resp==JOptionPane.YES_OPTION){
							idRes=tableReservas.getValueAt(row,0).toString();
							nHab=tableReservas.getValueAt(row,3).toString();
							System.out.println(idRes+"              "+nHab);
							cliente.eliminarReserva(idRes,nHab);
							tableReservas.setModel(DbUtils.resultSetToTableModel(cliente.verReservas()));
						}
					}
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		btnEliminarReserva.setBounds(364, 176, 167, 23);
		contentPane.add(btnEliminarReserva);
		
		btnRealizarEserva = new JButton("Realizar Reserva");
		btnRealizarEserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormularioReservas fr=new FormularioReservas(DNI);
				fr.setVisible(true);
				tableReservas.setModel(DbUtils.resultSetToTableModel(cliente.verReservas()));
			}
		});
		btnRealizarEserva.setBounds(10, 176, 161, 23);
		contentPane.add(btnRealizarEserva);
		
		JButton btnbajaSist = new JButton("Darse de baja en el sistema");
		btnbajaSist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				   	int resp=JOptionPane.showConfirmDialog(null, "¿Está seguro de darse de baja en el sistema? Con ello se borrarán también sus reservas.","Baja en el sistema",JOptionPane.YES_NO_OPTION);
						if(resp==JOptionPane.YES_OPTION){
							cliente.eliminarCliente();
							//////////////////////////////////////////////////////borrar todas las reservass de este cliente
							JOptionPane.showMessageDialog(null, "Se cerrará la aplicación");
							System.exit(0);
					}

				
			}
		});
		btnbajaSist.setBounds(330, 11, 205, 23);
		contentPane.add(btnbajaSist);
		
		JLabel label = new JLabel("");
		Image img3=new ImageIcon(this.getClass().getResource("/signature.png")).getImage();
		label.setIcon(new ImageIcon(img3));
		label.setBounds(10, 22, 33, 32);
		contentPane.add(label);
		
		JButton btnModificarReserva = new JButton("Modificar reserva");
		btnModificarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row;
				String idRes;
					row=tableReservas.getSelectedRow();
					if(row==-1){
						JOptionPane.showMessageDialog(null, "Debe seleccionar la reserva a modificar.","Advertencia",JOptionPane.WARNING_MESSAGE);
					}else{
						
						idRes=tableReservas.getValueAt(row,0).toString();
						modificarReserva mRes=new modificarReserva(idRes);
						mRes.setModal(true);
						mRes.setVisible(true);
						
						tableReservas.setModel(DbUtils.resultSetToTableModel(cliente.verReservas()));
						
					}
			}
		});
		btnModificarReserva.setBounds(181, 176, 167, 23);
		contentPane.add(btnModificarReserva);
		
		JButton btnHelp = new JButton("");
		Image img4=new ImageIcon(this.getClass().getResource("/interrogante.png")).getImage();
		btnHelp.setIcon(new ImageIcon(img4));
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda ay=new ayuda("ayudaCliente");
				ay.setVisible(true);
			}
		});
		btnHelp.setBounds(669, 11, 33, 32);
		contentPane.add(btnHelp);
		
		JButton btnTipoHabi = new JButton("Tipos de habitaci\u00F3n");
		btnTipoHabi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habitaciones = new tiposHabitacion();
				habitaciones.setVisible(true);
			}
		});
		btnTipoHabi.setBounds(541, 176, 161, 23);
		contentPane.add(btnTipoHabi);
		
		JButton btnCerrarSesin = new JButton("Cerrar sesi\u00F3n");
		btnCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp;
				resp=JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión?","Cierre de sesión",JOptionPane.YES_NO_OPTION);
				if(resp==JOptionPane.YES_OPTION){
					Login log=new Login();
					log.setVisible(true);
					dispose();
				}
			}
		});
		btnCerrarSesin.setBounds(545, 11, 119, 23);
		contentPane.add(btnCerrarSesin);
		
		JButton btnCaractersticasDeMi = new JButton("Caracter\u00EDsticas de mi reserva");
		btnCaractersticasDeMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row;
				int idRes;
					row=tableReservas.getSelectedRow();
					if(row==-1){
						JOptionPane.showMessageDialog(null, "Debe seleccionar la reserva a modificar.","Advertencia",JOptionPane.WARNING_MESSAGE);
					}else{
						
						idRes=Integer.parseInt(tableReservas.getValueAt(row,0).toString());
						caracteristicasReserva r=new caracteristicasReserva(DNI,idRes);
						r.setModal(true);
						r.setVisible(true);
						
					}
			}
		});
		btnCaractersticasDeMi.setBounds(10, 217, 246, 23);
		contentPane.add(btnCaractersticasDeMi);
		
		JButton btnActualizar = new JButton("Actualizar mis reservas");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				tableReservas.setModel(DbUtils.resultSetToTableModel(cliente.verReservas()));	
			}
		});
		btnActualizar.setBounds(293, 217, 205, 23);
		contentPane.add(btnActualizar);
		
	}
}

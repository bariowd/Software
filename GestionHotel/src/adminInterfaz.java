import java.awt.Image;
import java.nio.channels.FileChannel;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@SuppressWarnings("serial")
public class adminInterfaz extends JFrame {

	private JPanel contentPane;
	metodosAdmin admin=new metodosAdmin();
	String idAdministradorNow="0";
	Connection connection=conexionSQL.dbConector();
	private JTable tablaClientes_1;
	private JTable tablaReservas_1;
	/**
	 * Create the frame. holasadas
	 * 
	 */
	public adminInterfaz(String idAd) {
		idAdministradorNow=idAd;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(325, 150, 734, 500);
		setResizable(false);
		setTitle("Vista Administrador");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Clientes en el hotel:");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(49, 24, 194, 25);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Reservas en el hotel:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(49, 238, 194, 25);
		contentPane.add(label_1);
		
		JButton button = new JButton("Baja del cliente");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row;
				int resp;
				String idCliente;
				try{
					row=tablaClientes_1.getSelectedRow();
					if(row==-1){
						JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente para eliminar.","Advertencia",JOptionPane.WARNING_MESSAGE);
					}else{
						resp=JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este cliente del sistema? Con ello se eliminarán tambien sus reservas","Eliminar Cliente",JOptionPane.YES_NO_OPTION);
						if(resp==JOptionPane.YES_OPTION){
							idCliente=tablaClientes_1.getValueAt(row,0).toString();
							
							admin.eliminarCliente(idCliente);
							tablaClientes_1.setModel(DbUtils.resultSetToTableModel(admin.verClientes()));
							tablaReservas_1.setModel(DbUtils.resultSetToTableModel(admin.verReservas()));
						}
					}
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			
			}
		});
		button.setBounds(575, 225, 131, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Modifica reservas:");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row;
				String idRes;
					row=tablaReservas_1.getSelectedRow();
					if(row==-1){
						JOptionPane.showMessageDialog(null, "Debe seleccionar la reserva a modificar.","Advertencia",JOptionPane.WARNING_MESSAGE);
					}else{
						
						idRes=tablaReservas_1.getValueAt(row,0).toString();
						modificarReserva mRes=new modificarReserva(idRes);
						mRes.setModal(true);
						mRes.setVisible(true);
						
						tablaReservas_1.setModel(DbUtils.resultSetToTableModel(admin.verReservas()));
						
					}
				
			}
		});
		button_1.setBounds(295, 438, 158, 23);
		contentPane.add(button_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 60, 686, 154);
		contentPane.add(scrollPane_1);
		
		tablaClientes_1 = new JTable();
		scrollPane_1.setViewportView(tablaClientes_1);

		tablaClientes_1.setModel(DbUtils.resultSetToTableModel(admin.verClientes()));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 274, 686, 154);
		contentPane.add(scrollPane);
		
		tablaReservas_1 = new JTable();
		scrollPane.setViewportView(tablaReservas_1);
		tablaReservas_1.setModel(DbUtils.resultSetToTableModel(admin.verReservas()));
		
		JButton btnEliminarReserva = new JButton("Eliminar Reserva");
		btnEliminarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row;
				int resp;
				String idRes, nHab;
				try{
					row=tablaReservas_1.getSelectedRow();
					if(row==-1){
						JOptionPane.showMessageDialog(null, "Debe seleccionar la reserva a eliminar.","Advertencia",JOptionPane.WARNING_MESSAGE);
					}else{
						resp=JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta reserva?","Eliminar reserva",JOptionPane.YES_NO_OPTION);
						if(resp==JOptionPane.YES_OPTION){
							idRes=tablaReservas_1.getValueAt(row,0).toString();
							nHab=tablaReservas_1.getValueAt(row,3).toString();
							
							admin.eliminarReserva(idRes, nHab);
							tablaReservas_1.setModel(DbUtils.resultSetToTableModel(admin.verReservas()));
						}
					}
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		});
		
		btnEliminarReserva.setBounds(575, 439, 131, 23);
		contentPane.add(btnEliminarReserva);
		
		JButton btnNewButton = new JButton("Realizar Reserva");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				metodosReservas metodosR=new metodosReservas();
				if(!metodosR.existenHabitacionesLibres()){
					JOptionPane.showMessageDialog(null, "Lo sentimos, en este momento no existen habitaciones disponibles.");
				}else{
					FormularioReservas fr=new FormularioReservas(Integer.parseInt(idAdministradorNow));
					fr.setVisible(true);
					tablaReservas_1.setModel(DbUtils.resultSetToTableModel(admin.verReservas()));
				}
			}
		});
		btnNewButton.setBounds(20, 439, 158, 23);
		contentPane.add(btnNewButton);
		
		JLabel clientslbl = new JLabel("");
		Image img2=new ImageIcon(this.getClass().getResource("/clients.png")).getImage();
		clientslbl.setIcon(new ImageIcon(img2));
		clientslbl.setBounds(20, 17, 33, 32);
		contentPane.add(clientslbl);
		
		JLabel firmalbl = new JLabel("");
		Image img3=new ImageIcon(this.getClass().getResource("/signature.png")).getImage();
		firmalbl.setIcon(new ImageIcon(img3));
		firmalbl.setBounds(20, 231, 33, 32);
		contentPane.add(firmalbl);
		
		JButton btnHelp = new JButton("");
		Image img4=new ImageIcon(this.getClass().getResource("/interrogante.png")).getImage();
		btnHelp.setIcon(new ImageIcon(img4));
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda ay=new ayuda("ayudaAdmin");
				ay.setVisible(true);
			}
		});
		btnHelp.setBounds(675, 11, 31, 32);
		contentPane.add(btnHelp);
		
		JButton btnCerrarSesion = new JButton("Cerrar sesi\u00F3n");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resp;
				resp=JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión?","Cierre de sesión",JOptionPane.YES_NO_OPTION);
				if(resp==JOptionPane.YES_OPTION){
					Login log=new Login();
					log.setVisible(true);
					dispose();
				}
			}
		});
		btnCerrarSesion.setBounds(534, 17, 131, 23);
		contentPane.add(btnCerrarSesion);
		
		JButton btnCrearCopiaDe = new JButton("Crear copia de seguridad");
		btnCrearCopiaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				// TODO Auto-generated method stub
				
				// Creamos el objeto JFileChooser
				JFileChooser fichero_guardar = new JFileChooser();
				// Abrimos la ventana y guardamos la opcion seleccionada por el usuario
				fichero_guardar.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int seleccion = fichero_guardar.showSaveDialog(null);
				// Si el usuario pincha en aceptar
				if(seleccion == JFileChooser.APPROVE_OPTION) {
					// Seleccionamos el fichero
					File fichero = fichero_guardar.getSelectedFile();
				}
				
				/*try {
					// Creamos el objeto JFileChooser
					JFileChooser fichero_guardar = new JFileChooser();
					// Abrimos la ventana y guardamos la opcion seleccionada por el usuario
					fichero_guardar.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int seleccion = fichero_guardar.showSaveDialog(null);
					// Si el usuario pincha en aceptar
					if(seleccion == JFileChooser.APPROVE_OPTION) {
						// Seleccionamos el fichero
						File fichero = fichero_guardar.getSelectedFile();
						System.out.println(fichero);
						File ficheroBBDD = "C:\\hotelDB.sqlite";
						if (ficheroBBDD.compareTo(fichero.toString()) == 0) {
							int guardar = JOptionPane.showOptionDialog(null, "¿Desea sobreescribir el juego actual?", 
									"Salir", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[] {"Sí", "No"}, "Sí");
							if(JOptionPane.OK_OPTION == guardar) {
								copyFile("C:\\hotelDB.sqlite", fichero.toString());
							}
						} else {
							FileWriter escribir_fichero = new FileWriter(fichero);
							// Escribimos el texto en el fichero
							escribir_fichero.write(tableroAux.convertirSalida());
							escribir_fichero.close();
							ficheroGuardado = true;
						}
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}*/
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
			}
		});
		btnCrearCopiaDe.setBounds(319, 17, 194, 23);
		contentPane.add(btnCrearCopiaDe);
		
	}
	
	@SuppressWarnings("resource")
	public static void copyFile(File sourceFile, File destFile) throws IOException {
	    if(!destFile.exists()) {
	        destFile.createNewFile();
	    }
	 
	    FileChannel origen = null;
	    FileChannel destino = null;
	    try {
	        origen = new FileInputStream(sourceFile).getChannel();
	        destino = new FileOutputStream(destFile).getChannel();
	 
	        long count = 0;
	        long size = origen.size();              
	        while((count += destino.transferFrom(origen, count, size-count))<size);
	    }
	    finally {
	        if(origen != null) {
	            origen.close();
	        }
	        if(destino != null) {
	            destino.close();
	        }
	    }
	}
	
}

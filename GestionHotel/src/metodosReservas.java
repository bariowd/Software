import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class metodosReservas {
	
	Connection connection=conexionSQL.dbConector();
	
	public void Reservar(String fechaentrada, String fechasalida, String nHab, int idAdmin,String DNIcliente){
		
		try{
			String query="insert into Reservas (fecha_entrada, fecha_salida, nHabitacion, idAdministrador, DNI) values (?,?,?,?,?)";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setString(1, fechaentrada);
			pst.setString(2, fechasalida);
			pst.setString(3, nHab);
			pst.setString(4, ""+idAdmin+"");
			pst.setString(5, DNIcliente);

			pst.execute();
			
			pst.close();
			JOptionPane.showMessageDialog(null, "Reserva realizada");
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
		try{
			String query="update Habitaciones set estado='ocupado' where nHabitacion='"+nHab+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.execute();
			
			pst.close();
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
	
	public boolean existenHabitacionesLibres(){
		boolean bool=false;
		
		try{
			String query="SELECT * FROM Habitaciones where estado='libre'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				bool=true;
			}

			rs.close();
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return bool;
	}
}

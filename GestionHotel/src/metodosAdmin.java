import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;




public class metodosAdmin{
	
	Connection connection=conexionSQL.dbConector();

	
	public ResultSet verClientes(){
		ResultSet rs;
		
		try{
			String query="SELECT * FROM Clientes";
			PreparedStatement pst=connection.prepareStatement(query);
			rs=pst.executeQuery();
			return rs;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ResultSet verReservas(){

		try{
			String query="SELECT * FROM Reservas";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();

			//devolver=rs;                                                    ATENCION PERRADA
			
			return rs;
			//rs.close();
			//pst.close();
		}catch(Exception e){
			e.printStackTrace();

			
			return null;
		}

		//JOptionPane.showMessageDialog(null, devolver);
		
	}
	

	public int eliminarReserva(String idRes, String nHab){
		String dniborrar = null;
		try{

			String query="SELECT * FROM Reservas WHERE idReserva='"+idRes+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			dniborrar=rs.getString("DNI");
			rs.close();
			pst.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		try{
			String query="delete from Reservas where idReserva='"+idRes+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.execute();
			
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		try{
			String query="delete from Caracteristicasr where DNI='"+dniborrar+"' AND idRes='"+idRes+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.execute();
			
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			String query="update Habitaciones set estado='libre' where nHabitacion='"+nHab+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			
			pst.execute();
			
			pst.close();
		}catch(Exception e1){
			e1.printStackTrace();
		}
		return 0;
	}
	
	public int eliminarCliente(String idCliente){
		String dniborrar = null;
		try{

			String query="SELECT * FROM Clientes WHERE idCliente='"+idCliente+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			dniborrar=rs.getString("DNI");
			rs.close();
			pst.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		try{
			String query="delete from Clientes where idCliente='"+idCliente+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.execute();
			
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{

			String query="SELECT * FROM Reservas WHERE DNI='"+dniborrar+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
				while(rs.next()){
					liberarHabitaciones(rs.getString("nHabitacion"));
				}
			rs.close();
			pst.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		try{
			String query="delete from Reservas where DNI='"+dniborrar+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.execute();
			
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return 0;
	}
	
	public void liberarHabitaciones(String numHab){
		try{
			String query="update Habitaciones set estado='libre' where nHabitacion='"+numHab+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			
			pst.execute();
			pst.close();
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
}





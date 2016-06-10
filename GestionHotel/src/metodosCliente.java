import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;



public class metodosCliente extends metodosAdmin{
	Connection connection=conexionSQL.dbConector();
	String DNI;
	
	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}
	
	public metodosCliente(String DNI){
		setDNI(DNI);
	}

	public ResultSet verReservas(){

		try{
			String query="SELECT * FROM Reservas where DNI='"+getDNI()+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();

			//devolver=rs;                                                    ATENCION PERRADA
			
			return rs;
		}catch(Exception e){
			e.printStackTrace();

			
			return null;
		}

		//JOptionPane.showMessageDialog(null, devolver);
		
	}
	
	
	public int eliminarCliente(){
		try{
			String query="delete from Clientes where DNI='"+getDNI()+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.execute();
			
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			String query="delete from Caracteristicasr where DNI='"+getDNI()+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.execute();
			
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			String query="SELECT * FROM Reservas WHERE DNI='"+getDNI()+"'";
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
			String query="delete from Reservas where DNI='"+getDNI()+"'";
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

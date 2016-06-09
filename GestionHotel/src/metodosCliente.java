import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



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
			String query="delete from Reservas where DNI='"+getDNI()+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.execute();
			
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		

		
		return 0;
	}

}

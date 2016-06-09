import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class MetodosCaracteristicasR {

	Connection connection=conexionSQL.dbConector();
	String dniC;
	int idRes;
	public MetodosCaracteristicasR(String dniCliente) {
		// TODO Auto-generated constructor stub
		dniC=dniCliente;
	}
	
	public MetodosCaracteristicasR(String dniCliente, int idReserva) {
		// TODO Auto-generated constructor stub
		dniC=dniCliente;
		idRes=idReserva;
	}
	
	public void insertaExtra(String extra, int euros){
		try{
			String query="insert into Caracteristicasr (DNI, tipo, precio, idRes) values (?,?,?,?)";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setString(1, dniC);
			pst.setString(2, extra);
			pst.setFloat(3, euros);
			pst.setFloat(4,idRes);
			pst.execute();
			pst.close();
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
	
}

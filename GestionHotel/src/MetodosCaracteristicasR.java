import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class MetodosCaracteristicasR {

	Connection connection=conexionSQL.dbConector();
	String dniC;
	public MetodosCaracteristicasR(String dniCliente) {
		// TODO Auto-generated constructor stub
		dniC=dniCliente;
	}
	
	public void insertaExtra(String extra, int euros){
		try{
			String query="insert into Caracteristicasr (DNI, tipo, precio) values (?,?,?)";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setString(1, dniC);
			pst.setString(2, extra);
			pst.setFloat(3, euros);
			pst.execute();
			pst.close();
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
	
}

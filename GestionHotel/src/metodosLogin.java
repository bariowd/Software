import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class metodosLogin {

	public int validar_ingreso(String usuario,String clavedef){

		int resultado=0;
		Connection connection=conexionSQL.dbConector();
		

		try{

			String query="SELECT * FROM Administradores WHERE usuario='"+usuario+"' AND pass='"+clavedef+"'";
			//String query="SELECT * FROM Administradores WHERE usuario=? AND pass=? ";

			//preparamos consulta
			PreparedStatement pst=connection.prepareStatement(query);
			
			//si en la propia consulta ponemos ? en vez de usuario o clavedef, pondriamos lo sguiente:  //pst=setString(1,txtUsuario);  //pst=setString(2,jpassClave);
			
			//respuesta a la consulta
			ResultSet rs=pst.executeQuery();
			int count=0;
				while(rs.next()){
					count++;
				}
				
				if(count==1){
					JOptionPane.showMessageDialog(null, "Validación realizada correctamente.");
					resultado=1;
					count++;
				}else{
					query="SELECT * FROM Clientes WHERE usuario='"+usuario+"' AND pass='"+clavedef+"'";
					pst=connection.prepareStatement(query);
					rs=pst.executeQuery();
					while(rs.next()){
						count++;
					}
				}
				
				if(count==1){
					JOptionPane.showMessageDialog(null, "Validación realizada correctamente.");
					resultado=2;
				}else if(count==0){
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
				}
				
				rs.close();
				pst.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		return resultado;
	}
	
	public String dameDNI(String usuario){
		Connection connection=conexionSQL.dbConector();
			String devolver=null;
		try{

			String query="SELECT * FROM Clientes WHERE usuario='"+usuario+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			devolver=rs.getString("DNI");
			rs.close();
			pst.close();
			return devolver;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return devolver;
	}
	
	public String dameIDAdmin(String usuario){
		Connection connection=conexionSQL.dbConector();
			String devolver=null;
		try{

			String query="SELECT * FROM Administradores WHERE usuario='"+usuario+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			devolver=rs.getString("idAdministrador");
			rs.close();
			pst.close();
			return devolver;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return devolver;
	}
}

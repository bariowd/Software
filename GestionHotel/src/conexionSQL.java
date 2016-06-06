import java.sql.*;

import javax.swing.*;

public class conexionSQL {
	Connection conn=null;
	static int count=0;
	
	public static Connection dbConector(){
		
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Jake\\Desktop\\HOTEL\\hotelDB.sqlite");
			//"jdbc:sqlite:C:\\Users\\Jake\\Desktop\\3º Curso\\Ingeniería del Software1\\hotelDB.sqlite"
			///////borrar IF no lo de dentro
			if(count==0){
				
				JOptionPane.showMessageDialog(null, "Conexion realizada.");
				count++;
			}
			////////
			
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	

}















/* File fichero = new File("fichero.txt");

System.out.println(fichero.getAbsolutePath());
System.out.println(fichero.getPath());
*/
/*String directorioActual = System.getProperty("user.dir");
System.out.println(directorioActual);
String lugarBBDD="\\BBDD\\hotelDB.sqlite";


		
		String Direccion=System.getProperty("user.dir")+lugarBBDD;
		String DireccionNew="";
	
		
System.out.println(Direccion);

for (int i = 0; i < Direccion.length(); i++) {
	String hola =Direccion.substring(i);
	DireccionNew+=Direccion.charAt(i);
	
char mola = hola.charAt(0);
	if(mola==(char)92){
		
		char Barra=(char)92;
		DireccionNew+= (char)92;
	}
	
}

System.out.println(DireccionNew);*/


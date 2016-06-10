
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import referenteNominas.Trabajador;

public class Factura {

	String idRes;
	Connection connection=conexionSQL.dbConector();
	String DNI;
	ArrayList<String> tipoCaracteristica=new ArrayList<String>();
	ArrayList<Float> precios=new ArrayList<Float>();
	
	public Factura(String idReserva){
		idRes=idReserva;
	}

	public int hazFactura(){
		int funciona=0;
		
		try {
		   Document document = new Document(PageSize.A4, 35, 30, 50, 50);
		   FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Jake\\Desktop\\Nominas_2016_Grupo1\\C\\DefensaC.pdf");
		  // 311927
		   
		   Trabajador t;
		   PdfWriter.getInstance(document, fileOutputStream);

		   document.open();
		   
		   
		 
		   
		   document.close();
		   
		   
			Runtime.getRuntime().exec("cmd /c start "+"C:\\Users\\Jake\\Desktop\\Nominas_2016_Grupo1\\C\\DefensaC.pdf");
			funciona=1;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return funciona;
	}
	
	public void verCaracteristicas(){
		
		try{
			String query="SELECT * FROM Caracteristicasr where idRes='"+idRes+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				//rellenar array de caracteristicas
				rs.getString("DNI");
				//precios.add(rs.getString("");
			}

			//rs.close();
			//pst.close();
		}catch(Exception e){
			e.printStackTrace();

		}	
	}
	
}

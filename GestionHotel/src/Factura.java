
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
		pillaCaracteristicas();
		try {
		   Document document = new Document(PageSize.A4, 35, 30, 50, 50);
		   FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Jake\\Desktop\\Nominas_2016_Grupo1\\A\\DefensaA.pdf");
		  // 311927
		   
		   Trabajador t;
		   PdfWriter.getInstance(document, fileOutputStream);

		   document.open();
	/////////////////////////////////////////////////////////////////////////////////////////////////CABECERA	   
		   PdfPTable tabla2 = new PdfPTable(2);
		   PdfPCell celda2 = new PdfPCell();
		   
		   PdfPCell celda3 = new PdfPCell();
		   Font fuente=new Font();
		   fuente.setSize(30);
		   fuente.setStyle(1);
		   Paragraph titulo=new Paragraph("- FACTURA -",fuente);
		   titulo.setAlignment(Element.ALIGN_CENTER);
		   document.add(titulo);
		   
		   document.add(new Paragraph(" "));
		   titulo.setAlignment(Element.ALIGN_CENTER);
		   Image imagen = null;
		try {
			imagen = Image.getInstance("logotipo.png");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			celda3.addElement(new Chunk(" "));
		   celda3.addElement(imagen);
		   Paragraph nombre=new Paragraph("LIKE AT HOME S.L.");
		   nombre.setAlignment(Element.ALIGN_LEFT);
		   celda2.addElement(nombre);
		   Paragraph calle=new Paragraph("Calle Lapicero, n45º");
		   calle.setAlignment(Element.ALIGN_LEFT);
		   celda2.addElement(new Paragraph(calle));
		   Paragraph cat=new Paragraph("79900 León");
		   cat.setAlignment(Element.ALIGN_LEFT);
		   celda2.addElement(new Paragraph(cat));
		   Paragraph nif=new Paragraph("NIF 98767856T");
		   nif.setAlignment(Element.ALIGN_LEFT);
		   celda2.addElement(new Paragraph(nif));
		   Paragraph telf=new Paragraph("Telf: 676-453-453");
		   telf.setAlignment(Element.ALIGN_LEFT);
		   celda2.addElement(telf);
		   tabla2.addCell(celda2);
		   tabla2.addCell(celda3);
		   tabla2.setWidthPercentage(100);
		   
		   document.add(tabla2);
		   
		   document.add(new Paragraph(" "));

		   //////////////////////////////////////////////////////////////////////////////////7FIN CABECERA
		   
		   
		   //////////////////////////////////////////////////////////////////////////////////// Calculo de las nominas
		   
		   
		   			//////Meter datos del cliente haciendo una pequeña consulta a la BBDD
		   
		   PdfPTable table = new PdfPTable(4); 
		   
		   int TOTAL=0;
		   
		   for(int i=0;i<=tipoCaracteristica.size();i++){
			   
			   if(i==0){
				   table.addCell("Concepto");
				   table.addCell("Unidades");
				   table.addCell("IVA 21%");
				   table.addCell("TOTAL");
			   }
			   if(i==1){
				   //meter coste de la habitación * dias
			   }
			   
			   
			   ///calculos TENIENDO EN CUENTA EL COSTE DE LA HABITACION!!!!!!!
			   
			   if(i==tipoCaracteristica.size()){
				   PdfPCell celdaFinal = new PdfPCell(new Paragraph("TOTAL:      "+TOTAL));
				   table.addCell(celdaFinal);
			   }
			}
		   
		   document.add(table);
			   
		   
		   
		   document.close();
		   
		   
			Runtime.getRuntime().exec("cmd /c start "+"C:\\Users\\Jake\\Desktop\\Nominas_2016_Grupo1\\A\\DefensaA.pdf");
			funciona=1;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return funciona;
	}
	
	public void pillaCaracteristicas(){
		
		try{
			String query="SELECT * FROM Caracteristicasr where idRes='"+idRes+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				//rellenar array de caracteristicas
				rs.getString("DNI");
				tipoCaracteristica.add(rs.getString("tipo"));
				precios.add(Float.parseFloat(rs.getString("precio")));
			}

			//rs.close();
			//pst.close();
		}catch(Exception e){
			e.printStackTrace();

		}	
	}
	
}

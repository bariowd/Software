
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
	
	String tipoHab;
	float pHab;
	int numHabitacion;
	
	String nombreCliente;
	String apellidosCliente;
	String telfCliente;
	
	String fechaEntrada;
	String fechaSalida;
	
	int numDias;
	
	String tipoPension;
	float costePension;
	float costePensionN;

	
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
		   fuente.setSize(16);
		   fuente.setStyle(1);
		   document.add(new Paragraph("_______________________________________________________________________________"));
		   document.add(new Paragraph("Datos del cliente: ",fuente));
		   document.add(new Paragraph("Nombre: "+nombreCliente));
		   document.add(new Paragraph("Apellidos: "+apellidosCliente));
		   document.add(new Paragraph("DNI: "+DNI));
		   document.add(new Paragraph("Teléfono: "+telfCliente));
		   document.add(new Paragraph("_______________________________________________________________________________"));
		   
		   //////////////////////////////////////////////////////////////////////////////////// 
		   document.add(new Paragraph(" "));
		   document.add(new Paragraph("Consumo: ",fuente));
		   document.add(new Paragraph(" "));
		   
		   			//////Meter datos del cliente haciendo una pequeña consulta a la BBDD
		   
		   PdfPTable table = new PdfPTable(4); 
		   
		   fuente.setSize(12);
		   fuente.setStyle(1);
		   Chunk con=new Chunk("Concepto",fuente);
		   PdfPCell concepto=new PdfPCell();
		   concepto.addElement(con);
		   Chunk uni=new Chunk("Unidades",fuente);
		   PdfPCell unidades=new PdfPCell();
		   unidades.addElement(uni);
		   Chunk ivac=new Chunk("IVA 21%",fuente);
		   PdfPCell iva=new PdfPCell();
		   iva.addElement(ivac);
		   Chunk tot=new Chunk("TOTAL (€)",fuente);
		   PdfPCell total=new PdfPCell();
		   total.addElement(tot);
		   
		   float TOTAL=0;
			DecimalFormat df = new DecimalFormat("0.00");
		   float imprimir=0;
		   String aux;
		   
		   for(int i=0;i<tipoCaracteristica.size();i++){
			   
			   if(i==0){
				   table.addCell(concepto);
				   table.addCell(unidades);
				   table.addCell(iva);
				   table.addCell(total);
				   
				   
				   //coste habitacion
				   table.addCell("Habitacion "+tipoHab);
				   table.addCell(numDias+" días");
				   imprimir=(float) (pHab*0.21*numDias);
				   aux=df.format(imprimir).toString();
				   table.addCell(aux);
				   aux=df.format(pHab*numDias).toString();
				   table.addCell(aux);
				   TOTAL=TOTAL+pHab*numDias;
			   }
			   
			   if(tipoCaracteristica.get(i).equals("Pension completa")){
				   tipoPension="pension completa";
				   costePension=30;
				   costePensionN=20;
		   	   }else if(tipoCaracteristica.get(i).equals("Media pension")){
		   		   tipoPension="pension media";
		   		   costePension=15;
		   		   costePensionN=10;
		   	   }else if(tipoCaracteristica.get(i).equals("Numero de ninos")){
				   table.addCell("Niños a "+tipoPension);
				   int num=Math.round(precios.get(i));
				   table.addCell(num+ "");
				   imprimir=(float) (precios.get(i)*0.21*costePensionN*numDias);
				   aux=df.format(imprimir).toString();
				   table.addCell(aux);
				   imprimir=precios.get(i)*costePensionN*numDias;
				   aux=df.format(imprimir).toString();
				   table.addCell(aux);
				   TOTAL=TOTAL+precios.get(i)*costePensionN*numDias;
			   }else if(tipoCaracteristica.get(i).equals("Numero de adultos")){
				   table.addCell("Adultos a "+tipoPension);
				   int num=Math.round(precios.get(i));
				   table.addCell(num+"");
				   imprimir=(float) (precios.get(i)*0.21*costePension*numDias);
				   aux=df.format(imprimir).toString();
				   table.addCell(aux);
				   imprimir=precios.get(i)*costePension*numDias;
				   aux=df.format(imprimir).toString();
				   table.addCell(aux);
				   TOTAL=TOTAL+precios.get(i)*costePension*numDias;
			   }else{
			   
				   table.addCell(tipoCaracteristica.get(i));
				   table.addCell("1");
				   imprimir=(float) (precios.get(i)*0.21);
				   aux=df.format(imprimir).toString();
				   table.addCell(aux);
				   table.addCell(precios.get(i).toString());
				   TOTAL=TOTAL+precios.get(i);
			   }
			}
		   document.add(table);
		   
		   document.add(new Chunk("_______________________________________________________________________________"));
		   document.add(new Chunk("                                                                                                                  TOTAL: "+df.format(TOTAL)+" €"));
			   
		   
		   
		   document.close();
		   
		   
			Runtime.getRuntime().exec("cmd /c start "+"C:\\Users\\Jake\\Desktop\\Nominas_2016_Grupo1\\A\\DefensaA.pdf");
			metodosAdmin admin=new metodosAdmin();
			admin.eliminarReserva(idRes, ""+numHabitacion+"");
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
				DNI=rs.getString("DNI");
				tipoCaracteristica.add(rs.getString("tipo"));
				precios.add(Float.parseFloat(rs.getString("precio")));
			}

			//rs.close();
			//pst.close();
		}catch(Exception e){
			e.printStackTrace();

		}
		
		
		try{
			String query="SELECT * FROM Reservas where idReserva='"+idRes+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				//rellenar array de caracteristicas
				numHabitacion=Integer.parseInt(rs.getString("nHabitacion"));
				
				fechaEntrada=rs.getString("fecha_entrada");
				fechaSalida=rs.getString("fecha_salida");
			}

			//rs.close();
			//pst.close();
		}catch(Exception e){
			e.printStackTrace();

		}
		
		
		
		try{
			String query="SELECT * FROM Habitaciones where nHabitacion='"+numHabitacion+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				//rellenar array de caracteristicas
				tipoHab=rs.getString("categoria");
				pHab=Float.parseFloat(rs.getString("importe"));
			}

			//rs.close();
			//pst.close();
		}catch(Exception e){
			e.printStackTrace();

		}
		
		
		
		try{
			String query="SELECT * FROM Clientes where DNI='"+DNI+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				//rellenar array de caracteristicas
				nombreCliente=rs.getString("nombre");
				apellidosCliente=rs.getString("apellidos");
				telfCliente=rs.getString("telefono");
			}

			//rs.close();
			//pst.close();
		}catch(Exception e){
			e.printStackTrace();

		}
		
		
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
		Date fechaE = null;
		Date fechaS = null;
		try {
			fechaE=formato.parse(fechaEntrada);
			fechaS = formato.parse(fechaSalida);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		numDias=diferenciaEnDias2(fechaS, fechaE);
	}
	
	public int diferenciaEnDias2(Date fechaMayor, Date fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
		return (int) dias;
	}
	
}

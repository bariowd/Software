package referenteNominas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
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

public class Nomina {
	ArrayList<Trabajador> listaTrabajadores=new ArrayList<>();
	
	cuotasTrabajadores cuotas=new cuotasTrabajadores();
	String fechaProfesor;
	Nomina(ArrayList<Trabajador> trabajadores, String fecha) throws ParseException, IOException, DocumentException{
		fechaProfesor="01/"+fecha;
		listaTrabajadores=trabajadores;
		hazPDF();
	}
	
	public void hazPDF() throws DocumentException, FileNotFoundException, ParseException{
		   Document document = new Document(PageSize.A4, 35, 30, 50, 50);
		   FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Jake\\Desktop\\PDFhotel\\Nominas.pdf");
		  // 311927
		   
		   Trabajador t;
		   PdfWriter.getInstance(document, fileOutputStream);

		   document.open();
		   
			SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
			Date fechaAlta;
			Date fechaPantalla;
			String rutaImagen;
		       rutaImagen="logotipo.png";
			//System.out.println(miDir.getAbsolutePath());
		       
			
		 for(int i=0;i<listaTrabajadores.size();i++){

			 t=listaTrabajadores.get(i);
			 fechaAlta=formato.parse(t.getFechaAlta());
			 fechaPantalla=formato.parse(fechaProfesor);
			 
			 if(fechaAlta.before(fechaPantalla)){
				 
			   //Chunk porcionTexto = new Chunk("NÓMINAS DE LOS TRABAJADORES:");
			   //document.add(porcionTexto);
			   Paragraph parrafo = new Paragraph();
			   document.add(parrafo);
			   document.add(parrafo);
			   PdfPTable tabla = new PdfPTable(1);
			   PdfPCell celda1 = new PdfPCell();
			   celda1.addElement(new Paragraph("LikeAtHome S.L."));
			   celda1.addElement(new Paragraph("CIF: P2472623l"));
			   celda1.addElement(new Paragraph("Avenida de la facultad - 6"));
			   celda1.addElement(new Paragraph("24001 León"));
			   tabla.setWidthPercentage(50);
			   tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
			   tabla.addCell(celda1);
			   document.add(tabla);
			   
			   
			   document.add(new Chunk(" "));
			   document.add(parrafo);
			   
			   PdfPTable tabla2 = new PdfPTable(2);
			   PdfPCell celda2 = new PdfPCell();
			   
			   PdfPCell celda3 = new PdfPCell();
			   Font fuente=new Font();
			   fuente.setSize(30);
			   fuente.setStyle(1);
			   Paragraph titulo=new Paragraph("TECNOPROYECT S.L.",fuente);
			   titulo.setAlignment(Element.ALIGN_LEFT);
			   Image imagen = null;
			try {
				imagen = Image.getInstance(rutaImagen);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				celda3.addElement(new Chunk(" "));
			   celda3.addElement(imagen);
			   tabla2.addCell(celda3);
			   celda2.addElement(new Paragraph("Destinatario: "));
			   Paragraph nombre=new Paragraph(t.getNombre()+" "+t.getApellido1()+" "+t.getApellido2());
			   nombre.setAlignment(Element.ALIGN_RIGHT);
			   celda2.addElement(nombre);
			   Paragraph dnI=new Paragraph("DNI: "+t.getDNI());
			   dnI.setAlignment(Element.ALIGN_RIGHT);
			   celda2.addElement(new Paragraph(dnI));
			   Paragraph cat=new Paragraph("Categoría: "+t.getCategoria());
			   cat.setAlignment(Element.ALIGN_RIGHT);
			   celda2.addElement(new Paragraph(cat));
			   Paragraph antiguedad=new Paragraph("Antigüedad: "+t.getFechaAlta());
			   antiguedad.setAlignment(Element.ALIGN_RIGHT);
			   celda2.addElement(new Paragraph(antiguedad));
			   Paragraph direccion=new Paragraph("Avenida de la facultad - 24001 León");
			   direccion.setAlignment(Element.ALIGN_RIGHT);
			   celda2.addElement(direccion);
			   tabla2.addCell(celda2);
			   tabla2.setWidthPercentage(100);
			   
			   document.add(tabla2);
			   
			   float salarioBaseMes;
				float complementoMes;
				float prorrateoMes=0;
				float salario;
				float contingenciasGeneralesMes;
				float desempleo;
				float formacion;
				float IRPFmes;
				float liquidoMes;
				float salarioAux;
				
				if(t.getProrrata().toLowerCase().equals("si")){//en 12 nominas
					//salario base
					salarioBaseMes=(float)t.getSalarioBase()/14;
					complementoMes=(float)t.getComplementos()/14;
					prorrateoMes=(float)((salarioBaseMes+complementoMes+t.getTrienioDeAntiguedad())*2)/12;
					salario=salarioBaseMes+complementoMes+prorrateoMes+t.getTrienioDeAntiguedad();
					salarioAux=salario;
					
					contingenciasGeneralesMes=(float)salario*(cuotas.cuotaObreraGeneralTrabajador/100);
					desempleo=salario*(cuotas.cuotaDesempleoTrabajador/100);
					formacion=salario*(cuotas.cuotaFormacionTrabajador/100);
					IRPFmes=salario*(t.getRetencionDelBrutoAnual()/100);
					
					liquidoMes=salario-(contingenciasGeneralesMes+desempleo+formacion+IRPFmes);
				
				}else{//en 14 nominas
					salarioBaseMes=(float)t.getSalarioBase()/14;
					complementoMes=(float)t.getComplementos()/14;
					salario=(float)salarioBaseMes+complementoMes+t.getTrienioDeAntiguedad();
					prorrateoMes=(float)((salarioBaseMes+complementoMes+t.getTrienioDeAntiguedad())*2)/12;
					salarioAux=salarioBaseMes+complementoMes+prorrateoMes+t.getTrienioDeAntiguedad();
					
					contingenciasGeneralesMes=salarioAux*(cuotas.cuotaObreraGeneralTrabajador/100);
					desempleo=salarioAux*(cuotas.cuotaDesempleoTrabajador/100);
					formacion=salarioAux*(cuotas.cuotaFormacionTrabajador/100);
					IRPFmes=salario*(t.getRetencionDelBrutoAnual()/100);
					liquidoMes=salario-(contingenciasGeneralesMes+desempleo+formacion+IRPFmes);
				}
			   
				DecimalFormat df = new DecimalFormat("0.00");
			   document.add(new Chunk(" "));
			   fuente.setSize(12);
			   fuente.setStyle(1);
			   Paragraph rotulo=new Paragraph("Periodo liquidado "+fechaProfesor,fuente);
			   rotulo.setAlignment(Element.ALIGN_CENTER);
			   document.add(rotulo);
			   document.add(new Chunk("_______________________________________________________________________________"));
			   document.add(new Chunk("                                               cant.                        Imp. Unit.                    Dev.                     Deducc.       "));
			   document.add(new Chunk("_______________________________________________________________________________"));
			   document.add(parrafo);
			   document.add(new Chunk("Salario base                       30 días                       "+df.format((float)salarioBaseMes/30)+"                         "+df.format((float)salarioBaseMes)));
			   document.add(parrafo);
			   document.add(new Chunk("Complemento                     30 días                       "+df.format((float)complementoMes/30)+"                            "+df.format((float)complementoMes)));
			   document.add(parrafo);
			   document.add(new Chunk("Antigüedad                         30 días                       "+df.format((float)t.getTrienioDeAntiguedad()/30)+"                           "+df.format((float)t.getTrienioDeAntiguedad())));
			   document.add(parrafo);
			   document.add(new Chunk(" "));
			   document.add(parrafo);
			   document.add(new Chunk("Contingencias Generales     "+cuotas.cuotaObreraGeneralTrabajador+"%                                                                                           "+df.format(contingenciasGeneralesMes)));
			   document.add(parrafo);
			   document.add(new Chunk("Desempleo                           "+cuotas.cuotaDesempleoTrabajador+"%                                                                                           "+df.format(desempleo)));
			   document.add(parrafo);
			   document.add(new Chunk("Cuota forma                         "+cuotas.cuotaFormacionTrabajador+"%                                                                                           "+df.format(formacion)));
			   document.add(parrafo);
			   document.add(new Chunk("IRPF                                    "+t.getRetencionDelBrutoAnual()+"%                                                                                        "+df.format(IRPFmes)));
			   document.add(parrafo);
			   document.add(new Chunk("_______________________________________________________________________________"));
			   document.add(parrafo);
			   document.add(new Chunk("Total Deducciones                                                                                                                  "+df.format((contingenciasGeneralesMes+desempleo+formacion+IRPFmes))));
			   document.add(parrafo);
			   document.add(new Chunk("Total Devengos                                                                                      "+df.format((float)salario)));
			   document.add(parrafo);
			   document.add(new Chunk("_______________________________________________________________________________"));
			   document.add(parrafo);
			   fuente.setStyle(3);
			   Paragraph liq=new Paragraph("                                                                                                               Líquido a percibir: "+df.format(liquidoMes),fuente);
			   document.add(liq);
			   document.add(parrafo);
			   document.add(new Chunk(" "));
			   document.add(parrafo);
			   document.add(new Chunk("_______________________________________________________________________________"));
			   document.add(parrafo);
			   document.add(new Chunk("A ingresar en cuenta:                                 "+t.getIBAN()+"-"+t.getCuenta()));
			   document.add(parrafo);
			   
			   document.add(new Paragraph(" "));
			   document.add(new Chunk("EMPRESARIO",fuente));
			   document.add(parrafo);
			   document.add(new Chunk("_______________________________________________________________________________"));
			   document.add(parrafo);
			   document.add(new Chunk("Contingencias comunes empresario                                                                                   "+df.format(salarioAux*cuotas.contingenciasComunesEmpresario/100)));
			   document.add(parrafo);
			   document.add(new Chunk("Desempleo empresario                                                                                                       "+df.format(salarioAux*cuotas.desempleoEmpresario/100)));
			   document.add(parrafo);
			   document.add(new Chunk("Formación empresario                                                                                                        "+df.format(salarioAux*cuotas.formacionEmpresario/100)));
			   document.add(parrafo);
			   document.add(new Chunk("Accidentes trabajo empresario                                                                                            "+df.format(salarioAux*cuotas.accidentesTrabajoEmpresario/100)));
			   document.add(parrafo);
			   document.add(new Chunk("FOGASA empresario                                                                                                           "+df.format(salarioAux*cuotas.fogasaEmpresario/100)));
			   document.add(parrafo);
			   document.add(new Chunk("_______________________________________________________________________________"));
			   document.add(parrafo);
			   document.add(new Chunk("TOTAL empresario                                                                                                            "+df.format(salarioAux*cuotas.contingenciasComunesEmpresario/100+salarioAux*cuotas.desempleoEmpresario/100+salarioAux*cuotas.formacionEmpresario/100+salarioAux*cuotas.accidentesTrabajoEmpresario/100+salarioAux*cuotas.fogasaEmpresario/100)));
			   document.add(parrafo);
			   
			   document.newPage();
		}
	}    
		   
		   document.close();
		   
		   try {
			Runtime.getRuntime().exec("cmd /c start "+"C:\\Users\\Jake\\Desktop\\PDFhotel\\Nominas.pdf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

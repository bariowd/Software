package referenteNominas;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.DocumentException;



public class CrearNominas {

	public void generar(){
		ArrayList<ArrayList<XSSFCell>> listaDatos = new ArrayList<>();
		String rutaLecturaTXT="c.xlsx";
		Scanner lee=new Scanner(System.in);
		
		FileInputStream file = null;
		
		Calculos central = new Calculos();
		
		String fecha="06/2016";
		
		try {
		     file = new FileInputStream(new File(rutaLecturaTXT));
		 
		    @SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file);
		    XSSFSheet sheet = workbook.getSheetAt(0);
		    //Cell cell = null;
		    Cell celdita = null;
		    Cell celdota = null;
		    Cell celdon = null;
		    Cell c1 = null;
		    String cuentaParaCalcularIBAN;
		    
		    @SuppressWarnings("rawtypes")
			Iterator rows = sheet.rowIterator();
		    int fila0=0;
		    int numcolumnas=0;
		    while(rows.hasNext()){
		    	XSSFRow row = (XSSFRow) rows.next();	
		    	if(fila0==0){
		    		numcolumnas=row.getLastCellNum();
		    	}
		    	@SuppressWarnings("rawtypes")
				Iterator cells = row.cellIterator();
		    	ArrayList<XSSFCell> datos = new ArrayList<XSSFCell>();
		    	XSSFCell cell;
		    	for(int a=0;a<numcolumnas;a++){
		    		try{
		    			cell = (XSSFCell) cells.next();
		    		}catch(Exception e){
		    			cell=null;
		    		}	
		    		datos.add(cell);
		    	}
		    	listaDatos.add(datos);
		    	fila0++;
		    }

		    ///PRACTICA3
	/////////////////////////////////////////////////////////////////////////////////////////////////
			ArrayList<Trabajador> listaTrabajadores=new ArrayList<>();
			Trabajador aux;
			int numempleados=-1;
			Cell  cell=null;
			for(int i=0; i<listaDatos.size();i++){
				aux = new Trabajador(fecha);
				if(numempleados!=-1){
					
					for(int numCelda=0;numCelda<16;numCelda++){
						try{
							cell = sheet.getRow(i).getCell(numCelda);
								if(numCelda == 0){//Nombre
									aux.setNombre(cell.getStringCellValue());
								}else if(numCelda == 1){//Apellido1
									aux.setApellido1(cell.getStringCellValue());
								}else if(numCelda == 2){//Apellido2
									aux.setApellido2(cell.getStringCellValue());
								}else if(numCelda == 3){//DNI
									aux.setDNI(cell.getStringCellValue());
								}else if(numCelda == 4){//fechaAlta
									aux.setFechaAlta(String.valueOf(cell));
								}else if(numCelda == 5){//categoria
									aux.setCategoria(cell.getStringCellValue());
								}else if(numCelda == 8){//cuenta
									aux.setCuenta(NumberToTextConverter.toText(cell.getNumericCellValue()));
								}else if(numCelda == 10){//prorrata SI o NO
									aux.setProrrata(String.valueOf(cell).toLowerCase());
								}else if(numCelda == 11){//fecha de baja laboral
									aux.setFechaBajaLaboral(String.valueOf(cell));
								}else if(numCelda == 12){//fecha de alta laboral
									/*if(DateUtil.isCellDateFormatted(cell)){
										SimpleDateFormat simpl=new SimpleDateFormat("dd/MM/yyyy");
										aux.setFechaAltaLaboral(simpl.format(cell.getDateCellValue()));
									}*/
									aux.setFechaAltaLaboral(String.valueOf(cell));
								}
						}catch(Exception e){	
						}
					}
					Calculos.correosCreados.clear();
				}
				if(numempleados!=-1){
					listaTrabajadores.add(aux);
				}
				numempleados++;
				
				
			}
		    
			/*
		    for(int i=0;i<listaTrabajadores.size();i++){
		    	System.out.println(i+"-"+listaTrabajadores.get(i).getNombre()+" DNI:"+listaTrabajadores.get(i).getDNI()+" Prorrateo: "+listaTrabajadores.get(i).getProrrata()+" FechaBajaLaboral: "+listaTrabajadores.get(i).getFechaBajaLaboral());
		    }*/
		    
////////////////////////////////////////////////////////////////////////////////////////////////		    
		    
		    
			/*for(int i=0; i<listaDatos.size();i++){
				@SuppressWarnings("rawtypes")
				List lista = (List) listaDatos.get(i);
				for(int j=0;j<lista.size();j++){

					if(i!= 0 && j==3){	
						
						celdita = sheet.getRow(i).getCell(3);
						String DNI = celdita.getStringCellValue();
						if(DNI != "")
						celdita.setCellValue(central.numerosDNI(DNI));
					}
					
					//////////////////////////////////////////////////////////////////////////////////////////////
					//Practica2
					/////////////////////////////////////////////////////////////////////////////////////////////
					String preIBAN = "";
					String preCuenta="";
					if(i!= 0 && j==8){
					celdita = sheet.getRow(i).getCell(8);
					celdota = sheet.getRow(i).getCell(9);
				
					double cuentaN = celdita.getNumericCellValue();
					
					
					preIBAN =NumberToTextConverter.toText(cuentaN);
					if(celdota == null){
						celdota = sheet.getRow(i).createCell(9);
					}
					

					preCuenta=NumberToTextConverter.toText(cuentaN);
					cuentaParaCalcularIBAN=Calculos.calcularControl(preCuenta);
					celdita.setCellValue(cuentaParaCalcularIBAN);
					
					celdota.setCellValue(Calculos.dimeIBAN(cuentaParaCalcularIBAN));
					
					
					}
					
					if(i!=0 && j==15){
						celdita = sheet.getRow(i).getCell(0);
						celdota = sheet.getRow(i).getCell(1);
						celdon = sheet.getRow(i).getCell(2);
						
						String nombre = celdita.getStringCellValue();
						String apellido1 = celdota.getStringCellValue();
						String apellido2 = celdon.getStringCellValue();
						
						
						String correo = Calculos.dimeCorreo(nombre, apellido1, apellido2);
						
						
						Cell c2 = null;
						c2 = sheet.getRow(i).getCell(15);
						
						if(c2==null){
							celdota = sheet.getRow(i).createCell(15);
						}
						
						celdota.setCellValue(correo);
						if(c1 == null){
							c1 = sheet.getRow(i).createCell(15);
							c1.setCellType(Cell.CELL_TYPE_STRING);
						}
						
						c1.setCellValue(correo);
						
					}
				}
			}*/
		    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7
			listaDatos.clear();
			
			 sheet = workbook.getSheetAt(1);
			    //Cell cell = null;
			    
			    @SuppressWarnings("rawtypes")
				Iterator rows1 = sheet.rowIterator();
			    fila0=0;
			    numcolumnas=0;
			    while(rows1.hasNext()){
			    	XSSFRow row = (XSSFRow) rows1.next();	
			    	if(fila0==0){
			    		numcolumnas=row.getLastCellNum();
			    	}
			    	@SuppressWarnings("rawtypes")
					Iterator cells = row.cellIterator();
			    	ArrayList<XSSFCell> datos = new ArrayList<XSSFCell>();
			    	XSSFCell cell1;
			    	for(int a=0;a<numcolumnas;a++){
			    		try{
			    			cell1 = (XSSFCell) cells.next();
			    		}catch(Exception e){
			    			cell1=null;
			    		}	
			    		datos.add(cell1);
			    	}
			    	listaDatos.add(datos);
			    	fila0++;
			    }
			
			    
			 cell=null;
			 for(int i=0;i<listaTrabajadores.size();i++){//pARA CADA TRABAJADOR
				 //rellenar categorias
				 String catTrabajador=listaTrabajadores.get(i).getCategoria();
				 int codCotizacion=listaTrabajadores.get(i).getCodCotizacion();
				 int antiguedadTrabajador = 0;
				try {
					antiguedadTrabajador = listaTrabajadores.get(i).dimeAntguedad();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 boolean hayCambioDeTrienio=false;
				 if(antiguedadTrabajador%3==0){
					 hayCambioDeTrienio=true;
				 }
				 antiguedadTrabajador=antiguedadTrabajador/3;
				 for (int j = 0; j < listaDatos.size(); j++) {
					for(int numCelda=0;numCelda<11;numCelda++){
						try{
							cell = sheet.getRow(j).getCell(numCelda);
								if(numCelda == 0&&catTrabajador.equals(cell.getStringCellValue())){//Nombre
									
									cell = sheet.getRow(j).getCell(numCelda+1);
									listaTrabajadores.get(i).setSalarioBase((int)(cell.getNumericCellValue()));
									
									cell = sheet.getRow(j).getCell(numCelda+2);
									listaTrabajadores.get(i).setComplementos((int)(cell.getNumericCellValue()));
									
									cell = sheet.getRow(j).getCell(numCelda+3);
									listaTrabajadores.get(i).setCodCotizacion((int)(cell.getNumericCellValue()));
						
								}
								
								if(numCelda==9&&codCotizacion==(int)cell.getNumericCellValue()){
									cell=sheet.getRow(j).getCell(numCelda+1);
									listaTrabajadores.get(i).setCosteSeguridadSocial((int)cell.getNumericCellValue());
								}
								
								if(numCelda==3&&j>17&&j<36){
									
									if((int)cell.getNumericCellValue()==antiguedadTrabajador){
										cell=sheet.getRow(j).getCell(numCelda+1);
										listaTrabajadores.get(i).setTrienioDeAntiguedad((int)cell.getNumericCellValue());
										if(hayCambioDeTrienio==true){
											cell=sheet.getRow(j-1).getCell(numCelda+1);
											listaTrabajadores.get(i).setTrienioAnterior(((int)cell.getNumericCellValue()));
										}

									}
								}
								
						}catch(Exception e){	
						}
					}
				}
					float imp = 0;
					try {
						imp = listaTrabajadores.get(i).calcularBrutoAnual();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String.valueOf(imp).substring(0, 2);
					int auxBruto;//variable para comparar con el bruto anual del excel y seleccionar una retencion
					if(imp%1000==0){
						auxBruto=(int)imp;
					}else{
						auxBruto=Integer.parseInt(String.valueOf(imp).substring(0, 2)+"000")+1000;
					}
					
					for(int a=1;a<listaDatos.size()-1;a++){
						cell=sheet.getRow(a).getCell(5);
						if((int)cell.getNumericCellValue()==auxBruto){
							listaTrabajadores.get(i).setRetencionDelBrutoAnual((float)sheet.getRow(a).getCell(6).getNumericCellValue());
						}
					}
					
					
					
					
			 
			 }
			
			 
			 
			try {
				Nomina imprimirNominas=new Nomina(listaTrabajadores,fecha);
			} catch (ParseException | DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		    file.close();
		     
		    FileOutputStream outFile =new FileOutputStream(new File("C:\\Users\\Jake\\Desktop\\Practica22.xlsx"));
		    workbook.write(outFile);
		    outFile.close();
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}
	
}

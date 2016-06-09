package referenteNominas;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

public class Calculos {
	
	
	
    public static String numerosDNI(String dni){
        String dniN = null;
        
        if(dni.codePointAt(0)>64){
            if(dni.codePointAt(0)==88||dni.codePointAt(0)==120){
                dniN = "0"+dni.substring(1,8);
                dniN=dniN+calculaLetra(dniN);
                dniN="X"+dniN.subSequence(1, dniN.length());
            }else if(dni.codePointAt(0)==89||dni.codePointAt(0)==121){
                dniN = "1"+dni.substring(1,8);
                dniN=dniN+calculaLetra(dniN);
                dniN="Y"+dniN.subSequence(1, dniN.length());
            }else if(dni.codePointAt(0)==90||dni.codePointAt(0)==122){
                dniN = "2"+dni.substring(1,8);
                dniN=dniN+calculaLetra(dniN);
                dniN="Z"+dniN.subSequence(1, dniN.length());
            }    
        }
        else{
            dniN = dni.substring(0,8);
            dniN=dniN+calculaLetra(dniN);
        }
        
        return dniN ;
    }

static char calculaLetra(String dni)
    {
	int dni1=Integer.parseInt(dni);
    String juegoCaracteres="TRWAGMYFPDXBNJZSQVHLCKE";
    int modulo= dni1 % 23;
    char letra = juegoCaracteres.charAt(modulo);
    return letra; 
    } 

 /////////////////////////////////////////////////////////////////////////////////////////////////
//Practica2
/////////////////////////////////////////////////////////////////////////////////////////////////)

	static ArrayList<String> correosCreados=new ArrayList<String>();

public static String calcularControl(String cExcel){
		Cuenta c =new Cuenta();
		if(cExcel.length()!=20){
			int resto=20-cExcel.length();
			for(int i=0;i<resto;i++){
				cExcel="0"+cExcel;
			}
		}
		c.entidad=cExcel.substring(0,4);
		c.oficina=cExcel.substring(4,8);
		c.numCuenta=cExcel.substring(10,cExcel.length());
		
		return ""+c.entidad+""+c.oficina+""+validar(c)+""+c.numCuenta+"";
}
	
public static String validar(Cuenta c){
	// Habría que comprobar que sólo se han escrito números.
	// Todo correcto, calculamos el dígito de control
	
		int[] valores= {1, 2, 4, 8, 5, 10, 9, 7, 3, 6}; 
		
		int parte1=0;
		for(int i=2;i<valores.length;i++){
		
			if(i<6){
				parte1=parte1+(valores[i]*Integer.parseInt(c.entidad.substring(i-2, i-1)));
			}else{
				parte1=parte1+(valores[i]*Integer.parseInt(c.oficina.substring(i-6, i-5)));
			}
		}
		
		parte1=parte1%11;
		parte1=11-parte1;
		
		if(parte1==10){
			parte1=1;
		}
		if(parte1==11){
			parte1=0;
		}
		
		int parte2=0;
		for(int i=0;i<valores.length;i++){
			parte2=parte2+(valores[i]*Integer.parseInt(c.numCuenta.substring(i, i+1)));
		}
		
		parte2=parte2%11;
		parte2=11-parte2;
		if(parte2==10){
			parte2=1;
		}
		if(parte2==11){
			parte2=0;
		}
		
		return ""+parte1+""+parte2+"";
}
	
public static String dimeIBAN(String c){
	BigInteger IBAN;
	BigInteger noventaysiete=new BigInteger("97");
	
	String IBANs;
	String cTotal=c+"142800";
	
	IBAN=new BigInteger(cTotal);
	IBAN=IBAN.mod(noventaysiete);
	
	IBANs=""+(98-IBAN.intValue())+"";
	
	if(IBANs.length()==1){
		IBANs="ES"+"0"+IBANs;
	}else{
		IBANs="ES"+IBANs;
	}
	return IBANs;
}

public static String dimeCorreo(String name, String lastname1, String lastname2){
	
	String correo;
	int numCorreo=0;
	correo=name.substring(0, 2);
	correo=correo+lastname1.subSequence(0, 2);
	correo=correo+lastname2.substring(0, 2);
	
	correo=correo+"00";
	
	correo=correo.toLowerCase();
	
	boolean existe=verificarCorreo(correo);
	
		while(existe==true){
			numCorreo=numCorreo+1;
			if(numCorreo<10){
				correo=correo.substring(0, 6)+"0"+Integer.toString(numCorreo);
			}else{
				correo=correo.substring(0, 6)+Integer.toString(numCorreo);
			}
			existe=verificarCorreo(correo);
		}
	
	correosCreados.add(correo);
	
	correo=correo+"@TecnoProyect.es";
	
	return correo;
}

public static boolean verificarCorreo(String correo){
	boolean existe=false;
	
	for(int i=0;i<correosCreados.size();i++){
		if(correosCreados.get(i).equals(correo)){
			existe=true;
			break;
		}
	}
	return existe;
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Practica3
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static int diferenciaEnDias2(Date fechaMayor, Date fechaMenor) {
	long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
	long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
	return (int) dias;
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

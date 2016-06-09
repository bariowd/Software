package referenteNominas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Trabajador {
	
	String fechaProfesor;
	
	Trabajador(String fecha){
		fechaProfesor="01/"+fecha;
	}
	
	String nombre;
	String apellido1;
	String apellido2;
	String DNI;
	String fechaAlta;
	String categoria;
	String cuenta;
	String IBAN;
	String prorrata;
	String fechaBajaLaboral;
	String fechaAltaLaboral;
	String email;
	
	int trienioAnterior=0;
	
	public int getTrienioAnterior() {
		return trienioAnterior;
	}
	public void setTrienioAnterior(int trienioAnterior) {
		this.trienioAnterior = trienioAnterior;
	}

	int salarioBase;
	int complementos;
	int codCotizacion=0;
	int costeSeguridadSocial; //dependiendo del codigo cotizacion es un coste de la seguridad social..	
	float retencionDelBrutoAnual;	
	float brutoAnual;
	
	/////////////////////////////////////// Datos para la nomina
	
	float impProrrateo;
	
	public float getImpProrrateo() {
		return impProrrateo;
	}
	public void setImpProrrateo(float impProrrateo) {
		this.impProrrateo = impProrrateo;
	}
	//////////////////////////////////////
	

	public float getBrutoAnual() {
		return brutoAnual;
	}
	public void setBrutoAnual(float brutoAnual) {
		this.brutoAnual = brutoAnual;
	}

	int trienioDeAntiguedad;
	
	
	public int getSalarioBase() {
		return salarioBase;
	}
	public void setSalarioBase(int salarioBase) {
		this.salarioBase = salarioBase;
	}
	public int getComplementos() {
		return complementos;
	}
	public void setComplementos(int complementos) {
		this.complementos = complementos;
	}
	public int getCodCotizacion() {
		return codCotizacion;
	}
	public void setCodCotizacion(int codCotizacion) {
		this.codCotizacion = codCotizacion;
	}
	public int getCosteSeguridadSocial() {
		return costeSeguridadSocial;
	}
	public void setCosteSeguridadSocial(int costeSeguridadSocial) {
		this.costeSeguridadSocial = costeSeguridadSocial;
	}
	public float getRetencionDelBrutoAnual() {
		return retencionDelBrutoAnual;
	}
	public void setRetencionDelBrutoAnual(float retencionDelBrutoAnual) {
		this.retencionDelBrutoAnual = retencionDelBrutoAnual;
	}
	public int getTrienioDeAntiguedad() {
		return trienioDeAntiguedad;
	}
	public void setTrienioDeAntiguedad(int trienioDeAntiguedad) {
		this.trienioDeAntiguedad = trienioDeAntiguedad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
	
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		setEmail(Calculos.dimeCorreo(getNombre(), getApellido1(), apellido2));
		this.apellido2 = apellido2;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = Calculos.numerosDNI(dNI);
	}
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaNuestra(fechaAlta);
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta1) {
		this.cuenta = Calculos.calcularControl(cuenta1);
		setIBAN(cuenta);
	}
	public String getIBAN() {
		return IBAN;
	}
	public void setIBAN(String iBAN) {
		IBAN = Calculos.dimeIBAN(getCuenta());
	}
	public String getProrrata() {
		return prorrata;
	}
	public void setProrrata(String prorrata) {
		this.prorrata = prorrata;
	}
	public String getFechaBajaLaboral() {
		return fechaBajaLaboral;
	}
	public void setFechaBajaLaboral(String fechaBajaLaboral) {
		this.fechaBajaLaboral = fechaNuestra(fechaBajaLaboral);
	}
	public String getFechaAltaLaboral() {
		return fechaAltaLaboral;
	}
	public void setFechaAltaLaboral(String fechaAltaLaboral) {
		this.fechaAltaLaboral = fechaNuestra(fechaAltaLaboral);
	}
	
	private String fechaNuestra(String f){
		String fecha;
		fecha=f.substring(0,2)+"/";
		
		switch(f.substring(3,6)){
			case "ene":
				fecha=fecha+"01/";
				break;
			case "feb":
				fecha=fecha+"02/";
				break;
			case "mar":
				fecha=fecha+"03/";
				break;
			case "abr":
				fecha=fecha+"04/";
				break;
			case "may":
				fecha=fecha+"05/";
				break;
			case "jun":
				fecha=fecha+"06/";
				break;
			case "jul":
				fecha=fecha+"07/";
				break;
			case "ago":
				fecha=fecha+"08/";
				break;
			case "sep":
				fecha=fecha+"09/";
				break;
			case "oct":
				fecha=fecha+"10/";
				break;
			case "nov":
				fecha=fecha+"11/";
				break;
			case "dic":
				fecha=fecha+"12/";
				break;
		}
		fecha=fecha+f.substring(7,f.length());
		
		return fecha;
	}
	
	public int dimeAntguedad() throws ParseException{
		
		int antiguedad=-1;
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
		Date fechaAlta=formato.parse(getFechaAlta());
		Date fechaPantalla=formato.parse(fechaProfesor);
		
		if(fechaAlta.before(fechaPantalla)){
			//fechaPantalla es mayor
			if(Integer.parseInt(fechaProfesor.substring(3,5))-Integer.parseInt(getFechaAlta().substring(3,5))<0){
				antiguedad=Integer.parseInt(fechaProfesor.substring(6,fechaProfesor.length()))-Integer.parseInt(getFechaAlta().substring(6,getFechaAlta().length()))-1;
				//////////////////////////////////////////////////////////////////////////////////
				//CALCULADO EL NUMERO DE MESES
				/*
				int nummeses=0;
				int mes1=Integer.parseInt(getFechaAlta().substring(3,5));
				int mes2=Integer.parseInt(fechaProfesor.substring(3,5));
				while(true){
					nummeses++;
					if(mes1==12){
						mes1=0;
					}
					
					if(mes1==mes2){
						mes1++;
						break;
					}

					mes1++;
				}
				System.out.println("numero de meses:"+nummeses);*/
				////////////////////////////////////////////////////////////////////////////////////////
			}else{
				antiguedad=Integer.parseInt(fechaProfesor.substring(6,fechaProfesor.length()))-Integer.parseInt(getFechaAlta().substring(6,getFechaAlta().length()));
			}
			
		}else{
			//fechaAtla es mmayor
			antiguedad=0;
		}

		
		return (int )antiguedad;
	}
	
	///////////////////////////////////////////////////////REVISAR
	public float calcularBrutoAnual() throws ParseException{

		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
		Date fechaAlta=formato.parse(getFechaAlta());
		Date fechaPantalla=formato.parse(fechaProfesor);
		
		
		if(getFechaBajaLaboral()==null){
			if(fechaAlta.before(fechaPantalla)){
				if(getTrienioAnterior()==0){
					setBrutoAnual(getSalarioBase()+getComplementos()+(14*getTrienioDeAntiguedad()));
				}else{
					int nummesesTrienioAnterior=Integer.parseInt(getFechaAlta().substring(3, 5))-1;
					int nummesesTrienioActual=12-nummesesTrienioAnterior;
	
					float div1=(float)getTrienioAnterior()/12;
					float div2=(float)getTrienioDeAntiguedad()/12;
					setBrutoAnual(getSalarioBase()+getComplementos()+(nummesesTrienioAnterior*14*(div1)+nummesesTrienioActual*14*(div2)));
				}
			}
		}else{
			calcularBajaLaboral();
		}
		
		return getBrutoAnual();
	}
	
	public void calcularBajaLaboral() throws ParseException{
		
		//BORRAR CUANDO RESPONDA SARITA
		
		if(getFechaAltaLaboral()==null)
			return;
		
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
		Date fechaBaja=formato.parse(getFechaBajaLaboral());
		Date fechaPantalla=formato.parse(fechaProfesor);
		if(fechaPantalla.before(fechaBaja)){
			fechaBajaLaboral=null;//ponemos una fecha de baja laboral igual de null para que no entre en el  	if(getFechaBajaLaboral()==null)
														 //del calculo bruto anual, y asi haga un calculo normal de ese año
			calcularBrutoAnual();
		}else{
			Date fechaAlta=formato.parse(getFechaAltaLaboral());
			int diasDeBaja=Calculos.diferenciaEnDias2(fechaAlta, fechaBaja);
			fechaBajaLaboral=null;
			
			float brutoDiario=(calcularBrutoAnual()/12)/30;
			int diasAl50=0;
			int diasAl75=0;
			
			for(int i=1;i<=diasDeBaja;i++){
				if(i<=3){
					diasAl50++;
				}else if(i<21){
					diasAl75++;
				}
			}
			
			
			float descuentoBaja=(float) (brutoDiario*diasAl50*(0.5)+(brutoDiario*diasAl75*(0.25)));
			setBrutoAnual(getBrutoAnual()-descuentoBaja);
			
			
		}
	}
	
	
}

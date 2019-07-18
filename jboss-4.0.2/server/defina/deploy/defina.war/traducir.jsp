<%!

public java.util.HashMap inicializarTabla(){
	
	
	java.util.HashMap tablaLiterales = new java.util.HashMap();
	
	tablaLiterales.put("ESCENARIOS","SCENARIOS");
	tablaLiterales.put("AUTORIZACIONES","AUTHORIZATIONS");
	tablaLiterales.put("PERFILES","PROFILES");
	tablaLiterales.put("VERSIONES","VERSIONS");
	tablaLiterales.put("JERARQUIA","HIERARCHY");
	tablaLiterales.put("ICONOS","ICONS");
	tablaLiterales.put("C�digo","Code");
	tablaLiterales.put("Nombre","Name");
	tablaLiterales.put("Autorizaci�n","Authorization");
	tablaLiterales.put("Icono","Icon");
	tablaLiterales.put("Fecha modificaci�n","Modification Date");
	tablaLiterales.put("Descripci�n","Description");
	tablaLiterales.put("Orden","Order");
	tablaLiterales.put("C�digo de operaci�n padre","Father`s Operation Code");
	tablaLiterales.put("C�digo de operaci�n hijo","Child`s Operation Code");
	tablaLiterales.put("N�mero de orden","Order Number");
	
	return tablaLiterales;
	
}
public String traducirEspanolAlIngles(String cadena){

	java.util.HashMap tablaLiterales = inicializarTabla();
	if(tablaLiterales.get(cadena)!=null)
		cadena = (String)tablaLiterales.get(cadena);
	return cadena;
}

%>
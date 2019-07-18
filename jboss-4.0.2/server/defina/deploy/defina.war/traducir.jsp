<%!

public java.util.HashMap inicializarTabla(){
	
	
	java.util.HashMap tablaLiterales = new java.util.HashMap();
	
	tablaLiterales.put("ESCENARIOS","SCENARIOS");
	tablaLiterales.put("AUTORIZACIONES","AUTHORIZATIONS");
	tablaLiterales.put("PERFILES","PROFILES");
	tablaLiterales.put("VERSIONES","VERSIONS");
	tablaLiterales.put("JERARQUIA","HIERARCHY");
	tablaLiterales.put("ICONOS","ICONS");
	tablaLiterales.put("Código","Code");
	tablaLiterales.put("Nombre","Name");
	tablaLiterales.put("Autorización","Authorization");
	tablaLiterales.put("Icono","Icon");
	tablaLiterales.put("Fecha modificación","Modification Date");
	tablaLiterales.put("Descripción","Description");
	tablaLiterales.put("Orden","Order");
	tablaLiterales.put("Código de operación padre","Father`s Operation Code");
	tablaLiterales.put("Código de operación hijo","Child`s Operation Code");
	tablaLiterales.put("Número de orden","Order Number");
	
	return tablaLiterales;
	
}
public String traducirEspanolAlIngles(String cadena){

	java.util.HashMap tablaLiterales = inicializarTabla();
	if(tablaLiterales.get(cadena)!=null)
		cadena = (String)tablaLiterales.get(cadena);
	return cadena;
}

%>
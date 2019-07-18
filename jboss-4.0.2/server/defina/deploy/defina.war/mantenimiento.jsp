<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="js/defina.js"></script>
<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
<%
	boolean dialog = "true".equalsIgnoreCase(request.getParameter("dialog"));
	if (dialog) out.println("<script language=\"javascript\" type=\"text/javascript\" src=\"js/dialogs.js\"></script>");
	// Recupera el tipo de perfil de la sesión
	String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();
%>
</head>

<%
	String menu = (String) request.getParameter("menu");
	Object accion = request.getParameter("accion");
	String campostr = (String) request.getParameter("campos");
	String [][] campos = atad.defina.pres.Utils.getCampos(request);
	String[] iconos = null;

	Object [][] data = null;
	atad.defina.pres.Paginacion p = (atad.defina.pres.Paginacion) session.getValue("paginacion." + menu + ".jsp");
	atad.defina.datos.AccesoDatosBase dao = null;
	if ("BUSCAR".equals(accion) || accion == null) {
		if (p != null) {
			session.removeValue("paginacion." + menu + ".jsp");
			p.liberar();
			p = null;
		}

		if ("VERSIONES".equals(menu)) {
			dao = atad.defina.datos.AccesoDatosBase.getInstanceVersiones(session);
			data = dao.getTablaVersiones((request.getParameter("CódigoBusc")!=null?request.getParameter("CódigoBusc"):(campos[0][1]!=null?campos[0][1]:"")), 
							     (request.getParameter("DescripciónBusc")!=null?request.getParameter("DescripciónBusc"):(campos[1][1]!=null?campos[1][1]:""))); 	
		} else if ("PERFILES".equals(menu)){
			dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
			data = dao.getTablaPerfiles((request.getParameter("CódigoBusc")!=null?request.getParameter("CódigoBusc"):(campos[0][1]!=null?campos[0][1]:"")), 
							    (request.getParameter("DescripciónBusc")!=null?request.getParameter("DescripciónBusc"):(campos[1][1]!=null?campos[1][1]:"")));
		} else if ("AUTORIZACIONES".equals(menu)) {
			dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
			data = dao.getTablaAutorizaciones((request.getParameter("CódigoBusc")!=null?request.getParameter("CódigoBusc"):(campos[0][1]!=null?campos[0][1]:"")), 
								    (request.getParameter("DescripciónBusc")!=null?request.getParameter("DescripciónBusc"):(campos[1][1]!=null?campos[1][1]:"")));
		} else if ("JERARQUIA".equals(menu)) {
			dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
			data = dao.getTablaJerarquia(session.getValue("entorno"), 
							     (request.getParameter("Código de operación padreBusc")!=null?request.getParameter("Código de operación padreBusc"):(campos[0][1]!=null?campos[0][1]:"")), 
							     (request.getParameter("Código de operación hijoBusc")!=null?request.getParameter("Código de operación hijoBusc"):(campos[1][1]!=null?campos[1][1]:"")),
							     (request.getParameter("Número de ordenBusc")!=null?request.getParameter("Número de ordenBusc"):(campos[2][1]!=null?campos[2][1]:"")));
		} else if ("ESCENARIOS".equals(menu)) {
			dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
			Object[] dat = new Object[]{(request.getParameter("CódigoBusc")!=null?request.getParameter("CódigoBusc"):(campos[0][1]!=null?campos[0][1]:"")),
							    (request.getParameter("NombreBusc")!=null?request.getParameter("NombreBusc"):(campos[1][1]!=null?campos[1][1]:"")),
							    (request.getParameter("AutorizaciónBusc")!=null?request.getParameter("AutorizaciónBusc"):(campos[2][1]!=null?campos[2][1]:"")),
							    (request.getParameter("IconoBusc")!=null?request.getParameter("IconoBusc"):(campos[3][1]!=null?campos[3][1]:"")),
							    (request.getParameter("OrdenBusc")!=null?request.getParameter("OrdenBusc"):(campos[4][1]!=null?campos[4][1]:"")),
							    (request.getParameter("Fecha modificaciónBusc")!=null?request.getParameter("Fecha modificaciónBusc"):(campos[5][1]!=null?campos[5][1]:"")),
							    session.getValue("entorno"),session.getValue("canal")};
			//data = dao.getEscenarios(dat);
			// 
			// Evt Escenarios publicos  privados
			// Se realiza una query u otra en funcion de si voy a listar o buscar
			// Si voy a listar escenarios			--> no vienen informados ni el parametro accion ni el parametro dialog
			// Si voy a modificar escenarios	--> accion == BUSCAR y no viene informado el parametro dialog
			// Si voy a buscar escenarios			--> accion == BUSCAR y viene informado el parametro dialog	
			boolean informadoDialogo = "true".equalsIgnoreCase(request.getParameter("dialog")) || "false".equalsIgnoreCase(request.getParameter("dialog")) ;
			data = ("BUSCAR".equals(accion) && informadoDialogo)?dao.buscarEscenarios(dat):dao.getEscenarios(dat); 			 
		} else if ("ICONOS".equals(menu)){
			// En el caso de tratar con los iconos, la tabla debe rellenarse con la información extraida del sistema de ficheros.
	  	// Se rellena el array de datos con la información obtenida considerando nombre / ruta del icono
	  	dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
	  	data = dao.getDatosIconos((String)atad.defina.entorno.Configuracion.getCfg("cfg.directorio.iconos"),campos);
		}
		if (data != null) out.println("<script>window.status='Número total de registros: " + data.length + "';</script>");
		p = new atad.defina.pres.Paginacion();
		if (data != null && data.length > 0){
			p.setDatos(data);
		} else {
			session.removeValue("paginacion." + menu + ".jsp");
			p.setDatos(null);
		}
		p.setFilasPorPagina(Integer.parseInt((String)atad.defina.entorno.Configuracion.getCfg("cfg.paginacion.filasPorPagina")));
		data = p.getPaginaPrimera();
		session.putValue("paginacion." + menu + ".jsp", p);		
	} else if ("PAGINAR".equals(accion) && p != null) {
		int pagina = 1;
		try {
			pagina = Integer.parseInt(request.getParameter("pagina"));
		} catch(NumberFormatException e) {
		}
		data = p.getPagina(pagina - 1);
	} else if ("ORDENAR".equals(accion) && p!= null){
		int columna = 0;
		try {
			columna = Integer.parseInt(request.getParameter("columna"));
		} catch(NumberFormatException e) {
		}
		boolean ascendente=true;
		if ("false".equals(request.getParameter("ascendente").toString())){
			ascendente=false;
		}	
		p.ordenar(columna,ascendente);
		int pagina = 1;
		try {
			pagina = Integer.parseInt(request.getParameter("pagina"));
		} catch(NumberFormatException e) {
		}
		data = p.getPagina(0);
	}
	if (data != null && "ESCENARIOS".equals(menu)) {
		iconos = new String[data.length];
		for (int i=0; i<data.length; i++) {
			if (data[i][2] == null) {
				//data[i][2] = "Sin código de autorización";
				data[i][2] = "";
			}
			iconos[i] = "<img border=\"0\" src=\"images/" + data[i][3] + ".gif\" width=\"17\" height=\"17\">";
		}
	}
%>

<script>
var filaSel = null;

function ordenarTabla(col,ascendente){
	var pagina = <%=(p==null?1:p.getPaginaActual())%>;
	var tmp = 'mantenimiento.jsp?menu=<%=menu%>&campos=<%=campostr%>&accion=ORDENAR&columna='+col+'&ascendente='+ascendente;
	tmp = tmp + '&pagina=' + Number(pagina);
	tmp = tmp + '&dialog=' + <%=dialog%>;
	<%
	for(int i=0; i<campos.length; i++) {
		if ("ICONOS".equals(menu) && "Icono".equals(campos[i][0])){
			continue;
		}
		//
		// Evt Escenarios Publicos - Privados
		// Cuando se pagina en el listado de escenarios se va informa el estado de la combo de tipo para que se mantenga
		if ("ESCENARIOS".equals(menu) && "Autorización".equals(campos[i][0])) {
					out.println("var combo = document.getElementById(\"tipoAutorizacion\");");										
					out.println("if ((\"Autorizado\" == combo.options[combo.selectedIndex].value))");					
					out.println("tmp = tmp + '&tipoAutorizacion=Autorizado';");					
					out.println("if ((\"Publico\" == combo.options[combo.selectedIndex].value))");										
					out.println("tmp = tmp + '&tipoAutorizacion=Publico';");
					out.println("if ((\"Privado\" == combo.options[combo.selectedIndex].value))");										
					out.println("tmp = tmp + '&tipoAutorizacion=Privado';");
					out.println("if ((\"Sin tipo\" == combo.options[combo.selectedIndex].value)) ");					
					out.println("tmp = tmp + '&tipoAutorizacion=Sin tipo';");
					out.println("tmp = tmp + '&" + campos[i][0] + "=' + document.getElementById(\"" + campos[i][0] + "\").value;");
		}
		else	
		out.println("tmp = tmp + '&" + campos[i][0] + "=' + document.getElementById(\"" + campos[i][0] + "\").value;");
	}
	%>
	window.navigate(tmp);	
}

function seleccionarAutorizacion() {
	var rc = window.showModalDialog('<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.autorizaciones.link")+"&dialog=true"%>','','dialogWidth:600px;dialogHeight:680px');
	if (rc != null) document.getElementById("Autorización").value = rc[0];
}

function seleccionarIcono() {
	var rc = window.showModalDialog('<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.iconos.link")+"&dialog=true"%>','','dialogWidth:500px;dialogHeight:580px');
	if (rc != null) document.getElementById("Icono").value = rc[0];
}

function ordenar() {
<%
	String referrer = request.getRequestURI() + "?" + request.getQueryString();
%>
	location.href='<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.escenarios.ordenar.link") + "&referrer=" + java.net.URLEncoder.encode(referrer)%>';
}

function verArbol() {
	forzarSeleccion();
	if (filaSel == null) {
		alert('Seleccione un escenario, por favor');
	} else {
		location.href='<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.escenarios.arbol.link")%>&codigo=' + filaSel[0] + '&nombre=' + filaSel[1] + '&autorizacion=' + filaSel[2] + '&icono=' + filaSel[3] + '&orden=' + filaSel[4];
	}
}

function nuevo() {
	location.href='detalle.jsp?menu=<%=menu%>&accion=nuevo&campos=<%=("ESCENARIOS".equals(menu)?campostr.substring(0, campostr.lastIndexOf(",")):campostr)%>';
}

function modificar() {
	forzarSeleccion();
	if (filaSel == null) {
		alert('Debe seleccionar un registro');
	} else {
		var tmp = 'detalle.jsp?menu=<%=menu%>&accion=modificar&campos=<%=campostr%>';
		<%
		for (int i=0; i<campos.length; i++) {
			out.println("tmp = tmp + '&" + campos[i][0] + "=' + filaSel[" + i + "];");
		}
		%>
		<%
		for (int i=0;i<campos.length;i++){
			out.println("tmp = tmp + '&" + campos[i][0] + "Busc=' + document.getElementById(\"" + campos[i][0] + "\").value;");
		}
		%>
		location.href = tmp;
	}
}

function seleccion(str) {
	filaSel = str.split("<sep>");
}

function borrar() {
	forzarSeleccion();
	if (filaSel == null) {
		alert('Debe seleccionar un registro');
	} else if (confirm('¿Desea borrar el registro ' + filaSel[0] + '?')) {
		<%
		String [][] msg = null;
		if ("JERARQUIA".equals(menu)) {
			msg = new String[][] {{"0", "filaSel[0]"}, {"1", "filaSel[1]"}};
		} else {
			msg = new String[][] {{"0", "filaSel[0]"}};
		}
		out.println(atad.defina.pres.ProcesadorAjax.getJavascript(menu, false, true, msg));
		%>
	}
}

function buscar() {
	var tmp = 'mantenimiento.jsp?menu=<%=menu%>&campos=<%=campostr%>&accion=BUSCAR&dialog=<%=dialog%>';
	<%
	for(int i=0; i<campos.length; i++) {
		if ("ICONOS".equals(menu) && "Icono".equals(campos[i][0])){
		} else {
			//
			// Evt Escenarios Publicos - Privados
			// En funcion de lo q haya establecido en la combo y en el campo de autorizacion se realizara una busqueda u otra
			// Se informa como parametro el estado de la combo para que se mantenga
			if ("ESCENARIOS".equals(menu) && "Autorización".equals(campos[i][0])) {
				out.println("var combo = document.getElementById('tipoAutorizacion');");
				out.println("var ctexto = document.getElementById('Autorización');");			
				// Busqueda sin tipo --> se buscan cualquier escenario, publicos, privados y autorizados
				out.println("if ((combo != null) && ('Sin tipo' == combo.options[combo.selectedIndex].value)) {"); 				
				out.println("tmp = tmp + '&AutorizaciónBusc=CUALQUIERA'");
				out.println("tmp = tmp + '&tipoAutorizacion=Sin tipo'");
				out.println("}");
				// Busqueda esc privados --> se buscan todos los escenarios privados, ie, los q tienen por codigo: ""
				out.println("if ((combo != null) && ('Privado' == combo.options[combo.selectedIndex].value)) {"); 				
				out.println("tmp = tmp + '&AutorizaciónBusc='");
				out.println("tmp = tmp + '&tipoAutorizacion=Privado'");
				out.println("}");
				// Busqueda esc publicos --> se buscan todos los escenarios publicos, ie, los q tienen por codigo: "PUBLICO"
				out.println("if ((combo != null) && ('Publico' == combo.options[combo.selectedIndex].value)) {"); 				
				out.println("tmp = tmp + '&AutorizaciónBusc=PUBLICO'");
				out.println("tmp = tmp + '&tipoAutorizacion=Publico'");
				out.println("}");
				// Busqueda esc autorizados --> se buscan todos los escenarios autorizados, ie, los que tiene ALGUN codigo de auotizacion asignado por el usuario
				out.print("if ((combo != null) && ('Autorizado' == combo.options[combo.selectedIndex].value) && (ctexto!=null) && (ctexto.value == '')) { "); 
				out.println("tmp = tmp + '&AutorizaciónBusc=AUTORIZADO'");
				out.println("tmp = tmp + '&tipoAutorizacion=Autorizado'");
				out.println("}");
				// Busqueda de esc con un codigo de autorizacion determinado --> se buscan los escenarios autorizados con el codigo que ha elegido el usuario
				out.print("if ((combo != null) && ('Autorizado' == combo.options[combo.selectedIndex].value) && (ctexto!=null) && (ctexto.value != '')) { "); 
				out.println("tmp = tmp + '&AutorizaciónBusc=' +  ctexto.value");
				out.println("tmp = tmp + '&tipoAutorizacion=Autorizado'");
				out.println("}");
			//
			// Incidencia : Excepcion en busquedas de orden con valores no numericos
			} else if("Orden".equals(campos[i][0]))		{	
				out.println("var orden = document.getElementById(\"Orden\").value;");
				out.println("if(isNaN(orden)){");
				out.println("alert('El campo orden debe ser un valor numérico');");				
				out.println("return; }");						
				out.println("else ");
				out.println("tmp = tmp + '&" + campos[i][0] + "=' + document.getElementById(\"" + campos[i][0] + "\").value;");
			} else 
				out.println("tmp = tmp + '&" + campos[i][0] + "=' + document.getElementById(\"" + campos[i][0] + "\").value;");
		}
	}
	%>
	window.navigate(tmp);
}

function paginar(pagina) {
	var tmp = 'mantenimiento.jsp?menu=<%=menu%>&campos=<%=campostr%>&accion=PAGINAR';
	tmp = tmp + '&pagina=' + Number(pagina);
	tmp = tmp + '&dialog=' + <%=dialog%>;
	<%
	for(int i=0; i<campos.length; i++) {
		if ("ICONOS".equals(menu) && "Icono".equals(campos[i][0])){
			continue;
		}
		//
		// Evt Escenarios Publicos - Privados
		// Cuando se pagina en el listado de escenarios se va informa el estado de la combo de tipo para que se mantenga
		if ("ESCENARIOS".equals(menu) && "Autorización".equals(campos[i][0])) {
					out.println("var combo = document.getElementById(\"tipoAutorizacion\");");										
					out.println("if ((\"Autorizado\" == combo.options[combo.selectedIndex].value) )");					
					out.println("tmp = tmp + '&tipoAutorizacion=Autorizado';");					
					out.println("if ((\"Publico\" == combo.options[combo.selectedIndex].value))");										
					out.println("tmp = tmp + '&tipoAutorizacion=Publico';");
					out.println("if ((\"Privado\" == combo.options[combo.selectedIndex].value))");										
					out.println("tmp = tmp + '&tipoAutorizacion=Privado';");
					out.println("if ((\"Sin tipo\" == combo.options[combo.selectedIndex].value)) ");					
					out.println("tmp = tmp + '&tipoAutorizacion=Sin tipo';");
					out.println("tmp = tmp + '&" + campos[i][0] + "=' + document.getElementById(\"" + campos[i][0] + "\").value;");
		}
		else		
			out.println("tmp = tmp + '&" + campos[i][0] + "=' + document.getElementById(\"" + campos[i][0] + "\").value;");
	}
	%>
	window.navigate(tmp);	
}
//
// Evt Escenarios Publicos - Privados
// Actualiza el campo de autorizacion y su icono asociado en funcion del tipo de autorizacion seleccionado 
//
function actualizarTipoAutorizacion() {
	// Para el tipo autorizado se habilita el cuadro de texto y el boton de seleccion		
	if ("Autorizado" == tipoAutorizacion.options[tipoAutorizacion.selectedIndex].value) {				
		document.getElementById('Autorización').value="";	
		document.getElementById('Autorización').disabled=false;	
		//document.getElementById('Autorización').className="CampoObligatorio";	
		document.getElementById('iconoSeleccionAutorizacion').disabled=false;
		document.getElementById('iconoSeleccionAutorizacion').src="images/BotonPrismatico.gif";		
	}	
	else if ("Publico" == tipoAutorizacion.options[tipoAutorizacion.selectedIndex].value) {			
		document.getElementById('Autorización').value="PUBLICO";	
		document.getElementById('Autorización').disabled=true;	
		//document.getElementById('Autorización').className='CampoSalida';	
		document.getElementById('iconoSeleccionAutorizacion').disabled=true;
		document.getElementById('iconoSeleccionAutorizacion').src="images/BotonPrismaticoD.gif";		
	}
	// En caso contrario  (Sin tipo o privado) se deshabilita
	else  {		
		document.getElementById('Autorización').value="";	
		document.getElementById('Autorización').disabled=true;	
		//document.getElementById('Autorización').className="CampoSalida";	
		document.getElementById('iconoSeleccionAutorizacion').disabled=true;
		document.getElementById('iconoSeleccionAutorizacion').src="images/BotonPrismaticoD.gif";		
	}	
}
</script>

<body>
<script> window.setTimeout("alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';", <%=""+request.getSession().getMaxInactiveInterval()*1000%>);</script>
<table width="100%"  border="0" cellspacing="8" cellpadding="0">
  <tr>
    <td height="22" class="CabeceraRelacionada2">&nbsp;&nbsp;<%=menu%></td>
  </tr>
  <tr>
    <td align="right"><fieldset><legend>Búsqueda</legend>
        <table width="100%"  border="0" cellspacing="8" cellpadding="0">
          <tr>
		<%
		String longitud=null;
		for(int i=0; i<campos.length; i++) {
			if ("ESCENARIOS".equals(menu)){
					if ("Código".equals(campos[i][0])){
						longitud="7";
					} else if ("Nombre".equals(campos[i][0])){
						longitud="40";
					} else if ("Autorización".equals(campos[i][0])){
						longitud="8";
					} else if ("Icono".equals(campos[i][0])){
						longitud="30";
					} else if ("Fecha modificación".equals(campos[i][0])){
						longitud="10";
					} else {
						longitud="8";
					}	
				} else if ("AUTORIZACIONES".equals(menu) || "PERFILES".equals(menu)){
					if ("Código".equals(campos[i][0])){
						longitud="8";
					} else if ("Descripción".equals(campos[i][0])){
						longitud="50";
					} else {
						longitud="8";
					}
				} else if ("VERSIONES".equals(menu)){
					if("Código".equals(campos[i][0])){
						longitud="10";
					} else if ("Descripción".equals(campos[i][0])){
						longitud="50";
					}
				} else if ("JERARQUIA".equals(menu)){
					longitud="8";
				} else if ("ICONOS".equals(menu)){
					if ("Nombre".equals(campos[i][0])){
						longitud="30";
					} else {
						longitud="30";
					}
				} else {
					longitud="8";
				}
			if ("ICONOS".equals(menu) && i==1){
				// No hace nada, debe pintarse el campo
			} else {
				// Genera la fila con la descripcion de los distintos campos
				out.println("<td width=\"1%\">");
				if ("ESCENARIOS".equals(menu) && (campos[i][0].startsWith("Fecha modificación") || campos[i][0].startsWith("Op. Home"))){
					out.print(" ");
				} else {
					out.print("<label>");
					out.print(campos[i][0]);
					out.print("</label><br>");
				}

				// Evt Escenarios Publicos - Privados
				// Genera la fila con los campos en sí	
				// Si el campo es la fecha de modificacion --> se crea un campo oculto
				if ("ESCENARIOS".equals(menu) && campos[i][0].startsWith("Fecha modificación")){
					out.print("<input name=\"");
					out.print(campos[i][0]);
					out.print("\" type=\"hidden\" size=\"" + ("ESCENARIOS".equals(menu)?"18":"35") + "\" maxlength=\"" + longitud + "\"" + (("ESCENARIOS".equals(menu) && ("Icono".equals(campos[i][0]) || "Autorización".equals(campos[i][0])))?"class=\"CampoSalida\" ":"class=\"CampoEntrada\"") + "></td>");
				} else if ("ESCENARIOS".equals(menu) && "Op. Home".equals(campos[i][0])){
					out.print("<input name=\"");
					out.print(campos[i][0]);
					out.print("\" type=\"hidden\" size=\"" + ("ESCENARIOS".equals(menu)?"18":"35") + "\" maxlength=\"" + longitud + "\"" + (("ESCENARIOS".equals(menu) && ("Icono".equals(campos[i][0]) || "Autorización".equals(campos[i][0])))?"class=\"CampoSalida\" ":"class=\"CampoEntrada\"") + "></td>");
				} else if ("ESCENARIOS".equals(menu) && "Autorización".equals(campos[i][0])){
					// Se crea la combo con los tipos de autorizacion
					out.print("<select name=\"tipoAutorizacion\" class=\"campoEntrada\" onchange=\"actualizarTipoAutorizacion();\">");					
					out.print("<option value=\"Sin tipo\" >Sin tipo</option>");
					out.print("<option value=\"Privado\" >Privado</option>");
					out.print("<option value=\"Publico\" >Público</option>");
					out.print("<option value=\"Autorizado\" >Autorizado</option>");
					out.print("</select>");
					
					out.print("&nbsp;&nbsp;&nbsp;");

					String valorCombo = request.getParameter("tipoAutorizacion");															
					String habilitado =  ("Autorizado".equals(valorCombo)) ?"":"disabled";					
					String val = campos[i][0] + "Busc";
					String valor = (  request.getParameter(val)!=null && request.getParameter(val)!= "" && !("CUALQUIERA".equals(request.getParameter(val))) && !("AUTORIZADO".equals(request.getParameter(val)))  )?request.getParameter(val).toString():"";					

					// Se crea el campo de texto autorizacion, inicialmente deshabilitado porque la combo aparece de tipo Privado
					out.print("<input name=\"" + campos[i][0] + "\" type=\"text\" size=\"" + ("ESCENARIOS".equals(menu)?"10":"35") + "\" maxlength=\"" + longitud + "\" class=\"CampoSalida\" value=\"" + valor + "\"" +  habilitado +" ></td>");					
					
				} else if ("ESCENARIOS".equals(menu) && "Código".equals(campos[i][0])){
					out.print("<input name=\"");
					out.print(campos[i][0]);
					String val = campos[i][0] + "Busc";
					String valor = (request.getParameter(val)!=null && request.getParameter(val)!= "")?request.getParameter(val).toString():"";					
					out.print("\" type=\"text\" size=\"" + ("ESCENARIOS".equals(menu)?"10":"35") + "\" maxlength=\"" + longitud + "\"" + (("ESCENARIOS".equals(menu) && ("Icono".equals(campos[i][0]) || "Autorización".equals(campos[i][0])))?"class=\"CampoSalida\" ":"class=\"CampoEntrada\"") + " value='" + valor + "'" + "></td>");

				} else {
					out.print("<input name=\"");
					out.print(campos[i][0]);
					String val = campos[i][0] + "Busc";
					String valor = (request.getParameter(val)!=null && request.getParameter(val)!= "")?request.getParameter(val).toString():"";
					out.print("\" type=\"text\" size=\"" + ("ESCENARIOS".equals(menu)?"18":"35") + "\" maxlength=\"" + longitud + "\"" + (("ESCENARIOS".equals(menu) && ("Icono".equals(campos[i][0]) || "Autorización".equals(campos[i][0])))?"class=\"CampoSalida\" ":"class=\"CampoEntrada\"") + " value='" + valor + "'" + "></td>");
				}
				
				// Se genera el icono de seleccion de autorizacion
				if ("ESCENARIOS".equals(menu)) {
					switch(i) {
						case 2:							
							String valorCombo = request.getParameter("tipoAutorizacion");	
							String habilitado = ("Autorizado".equals(valorCombo))?"":"disabled";
							String nombreIcono =  ("Autorizado".equals(valorCombo))? "images/BotonPrismatico.gif" : "images/BotonPrismaticoD.gif";
							out.print("<td><br><img name=\"iconoSeleccionAutorizacion\" src=\" " + nombreIcono + "\" width=\"19\" height=\"18\" onClick=\"seleccionarAutorizacion();\" " + habilitado + ">");
						break;
						
						case 3:
							out.print("<td><br><img src=\"images/BotonPrismatico.gif\" width=\"19\" height=\"18\" onClick=\"seleccionarIcono();\">");
						break;
					}
				}
				out.print("</td>");
			}
		}
		%>
		<td width="98%" align="right" valign="bottom">&nbsp;</td>
          </tr>
        </table>
    </fieldset></td>
  </tr>
  <tr>
    <td align="right"><input type="button" name="Button3" value="Buscar" class="Boton" onclick="buscar();"></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="98%" class="TituloTabla">Listado de <%=menu.toLowerCase()%></td>
        <td width="1%" nowrap>
		<img src="images/Izq1.gif" width="17" height="17" onClick="paginar(1)">
		<img src="images/Izq2.gif" width="17" height="17" onClick="paginar(<%=(p==null?1:p.getPaginaActual())%>)">
		<img src="images/Dere2.gif" width="17" height="17" onClick="paginar(<%=(p==null?1:p.getPaginaActual() + 2)%>)">
		<img src="images/Dere1.gif" width="17" height="17" onClick="paginar(<%=(p==null?1:p.getTotalPaginas())%>)">
	</td>
        <td width="1%" nowrap>&nbsp;&nbsp;P&aacute;gina:
            <input name="paginaActual" type="text" class="CampoEntradaImporte" value=<%=(p==null?1:(data==null)?0:p.getPaginaActual()+1)%> size="3">
      /
      <input name="paginaTotal" type="text" class="CampoSalidaImporte" value=<%=(p==null?1:p.getTotalPaginas())%> size="3" disabled>
&nbsp;
      <input type="button" name="Button22" value="Ir" class="Boton" style="width:40px" onClick="paginar(document.getElementById('paginaActual').value);"></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="100%" colspan="3"><table width="100%" cellpadding="0" cellspacing="2" class="TablaDatos">
          <tr class="CabeceraTabla">
            <td width="1%">&nbsp;</td>
	    <%
	    int lim=-1;
	    if ("ESCENARIOS".equals(menu)){
	    	//lim=campos.length -1;
	    	lim=campos.length -2;
	    } else {
	    	lim = campos.length;
	    }
	    int ancho = 100/lim;
	    if ("JERARQUIA".equals(menu)){
	    	ancho = 33;
	    }
	    
	    for(int i=0; i<lim; i++) {
				out.println("<td valign=\"top\" width=\"" + ancho + "%\">");
				out.print(campos[i][0]);
				out.print("<img src=\"images/AT_ExcluirVert.gif\" onClick=\"ordenarTabla(" + i + ",true)\">");
				out.print("<img src=\"images/AT_IncluirVert.gif\" onClick=\"ordenarTabla(" + i + ",false)\">");
				out.print("</td>");
	    }
	    %>
          </tr>

	  <%
	  
	  if (data != null) {
		// hay datos que cargar en la lista
		for (int i=0; i<data.length; i++) {
			out.println("<tr class=\"Pijama" + (i%2==0?"1":"2") + "\">");
			out.print("<td class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"");
			for (int x=0; x<data[i].length; x++) {
				if(x>0) out.print("<sep>");
				out.print(data[i][x]);
			}
			out.print("\" onClick='seleccion(this.value)'></td>");
			int lim2=-1;
			if ("ESCENARIOS".equals(menu)){
	    	lim2 = data[i].length -1;
	    } else {
	    	lim2 = data[i].length;
	    }
			for (int j=0; j<lim2; j++) {
				out.print("<td class=\"TextoTablaN\" valign=\"top\">");
				if (iconos != null && j==3) {
					out.print(iconos[i]);
				} else {
					if ("ESCENARIOS".equals(menu) && j==5) {
						if (j==5){
							out.print(atad.defina.pres.Utils.formatTimeStamp((java.sql.Timestamp)data[i][5]));
						} else {
							out.print(data[i][j]);
						}
					} else if ("ICONOS".equals(menu)){
						if (j==0){
							out.print(data[i][j]);
						} else if (j==1){
							out.print("<img border=\"0\" src=\"images/" + data[i][j] + "\" width=\"17\" height=\"17\">");
						}	 
					} else {
						if (j==0 && ("ESCENARIOS".equals(menu) || "VERSIONES".equals(menu) || "PERFILES".equals(menu) || "JERARQUIA".equals(menu) || "AUTORIZACIONES".equals(menu))){
							java.lang.StringBuffer cadenaSel = new java.lang.StringBuffer();
							for (int y=0; y<data[i].length; y++) {
								if(y>0) cadenaSel.append("<sep>");
								cadenaSel.append(data[i][y]);
							}
							if (!dialog){
								out.print("<a href=\"#\" target=\"_self\" onclick=\"seleccion('" + cadenaSel.toString() + "');modificar()\">" + data[i][j] + "</a>");
							} else {
								out.print(data[i][j]);
							}
						} else {
							out.print(data[i][j]);
						}
					}
				}
				out.print("</td>");
			}
	  }
	  }
	  %>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="right">
	<%
	String disabled = null;
	if (atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
		disabled = "disabled=\"true\"";
	} else {
		disabled = "";
	}
	if (dialog) {
		out.println("<input type=\"button\" name=\"Button1\" value=\"Aceptar\" class=\"Boton\" onClick=\"aceptarDialogo();\">");
		out.println("<input type=\"button\" name=\"Button2\" value=\"Cancelar\" class=\"Boton\" onClick=\"cancelarDialogo();\">");
		out.println("<script language=\"javascript\" type=\"text/javascript\">fixDialog();</script>");
	} else {
		out.println("<input type=\"button\" name=\"Button1\" value=\"A&ntilde;adir\" class=\"Boton\" onClick=\"nuevo();\"" + disabled + ">");
		out.println("<input type=\"button\" name=\"Button2\" value=\"Modificar\" class=\"Boton\" onClick=\"modificar();\">");
		out.println("<input type=\"button\" name=\"Button3\" value=\"Suprimir\" class=\"Boton\" onClick=\"borrar();\"" + disabled + ">");
		if ("ESCENARIOS".equals(menu)) {
			out.println("<input type=\"button\" name=\"Button4\" value=\"Ordenar\" class=\"Boton\" onClick=\"ordenar();\"" + disabled + ">");
			out.println("<input type=\"button\" name=\"Button5\" value=\"Ver &aacute;rbol\" class=\"Boton\" onClick=\"verArbol();\">");
		}
	}
	%>
	</td>
  </tr>
</table>

<%=atad.defina.pres.Utils.rellenarCampos(request)%>

</body>
</html>

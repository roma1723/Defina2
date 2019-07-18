<%! atad.defina.datos.AccesoDatosBase dao = null;%>
<%
	// Asegura que se limpia la información de la sesión, relativa a operaciones a modificar
	session.putValue("codigoModificar",null);
	session.putValue("opRelacionadas",null);
	session.putValue("borraOperacion","false");
	session.putValue("buscadoDato","false");
	// Recupera el tipo de perfil de la sesión
	String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();
%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
<script src="js/defina.js"></script>
<%
	boolean dialog = "true".equalsIgnoreCase(request.getParameter("dialog"));
	boolean opfinal = (request.getParameter("opfinal")!=null && !request.getParameter("opfinal").equals("null"))?"true".equalsIgnoreCase(request.getParameter("opfinal")):false;
	atad.defina.pres.Paginacion p = (atad.defina.pres.Paginacion) session.getValue("operacionesPaginadas");
	if (dialog) out.println("<script language=\"javascript\" type=\"text/javascript\" src=\"js/dialogs.js\"></script>");
%>

<script language="JavaScript">
var filaSel = null;
var locat = null;
var b=null;
function ordenarTabla(col,ascendente){
	var pagina = <%=(p==null?1:p.getPaginaActual())%>;
	// <ETIQUETA>
	// Si estoy en un dialogo se notifica a la siguiente ventana, para q no pinte las operaciones de tipo etiqueta
	//var tmp = 'operacion.jsp?accion=ORDENAR&columna='+col+'&ascendente='+ascendente+generaCadena();
	if (<%=dialog%>)	
		var tmp = 'operacion.jsp?dialog=true&accion=ORDENAR&columna='+col+'&ascendente='+ascendente+generaCadena();
	else
		var tmp = 'operacion.jsp?dialog=false&accion=ORDENAR&columna='+col+'&ascendente='+ascendente+generaCadena();	
	tmp = tmp + '&pagina=' + Number(pagina);
	window.navigate(tmp);	
}
function paginar(pagina) {
	var tmp = 'operacion.jsp?accion=PAGINAR&dialog=<%=dialog%>';
	tmp = tmp + '&pagina=' + pagina;
	tmp = tmp + '&dialog=' + <%=dialog%>;
	window.navigate(tmp);
}
function seleccion(str) {
	filaSel = str.split("<sep>");
}
function nuevo(){
	//limpiaCampos();
	var tmp = 'operacionDetalle.jsp?accion=NUEVA';
	location.href=tmp + generaCadena();
}
function limpiaCampos(){
	// Limpia los campos de la selección que no sean combos
	document.getElementById("codigo").value="";
	document.getElementById("descripcion").value="";
	document.getElementById("icono").value="";
	document.getElementById("parametro1").value="";
	document.getElementById("autorizacion").value="";
	document.getElementById("fecModificacion").value="";
}
function borrar(){
	if (filaSel == null){
		alert('Debe seleccionar un registro');
	} else if (confirm('¿Desea borrar el registro ' + filaSel[0] + '?')){
		<%
		String [][] msg = new String[][] {{"codigo", "filaSel[0]"},{"nombre","filaSel[1]"},
			{"entorno","filaSel[2]"},{"icono","filaSel[3]"},{"tipoOperacion","filaSel[4]"},
			{"parametro1","filaSel[5]"},{"autorizacion","filaSel[6]"},{"fecModificacion","filaSel[7]"}};
		out.println(atad.defina.pres.ProcesadorAjax.getJavascript(17,msg));
		%>		
	}
}
function verArbol(){
	var tmp = '<%=atad.defina.entorno.Configuracion.getCfg("cfg.aplicacion")%>/operacionArbol.jsp?accion=CONSULTAR';
	if (filaSel == null) {
		alert('Debe seleccionar un registro');
	} else {
		location.href = tmp + "&operacion=" + filaSel[0] + "&nombre=" + filaSel[1] + "&entorno=" + filaSel[2] + "&autorizacion=" + filaSel[6] + generaCadena();
	}
}
function generaCadena(){
	var tmp = '&codigo=';
	if (b != null){
		tmp = tmp + document.getElementById("codigo").value;
	} else {
		if (filaSel != null){
			tmp = tmp + filaSel[0];
		} else {
			tmp = tmp + "";
		}
	}
	tmp = tmp + '&descripcion=' + document.getElementById("descripcion").value;
	tmp = tmp + '&entorno=' + document.getElementById("entorno").value;
	tmp = tmp + '&icono=' + document.getElementById("icono").value;
	tmp = tmp + '&tipoOperacion=' + document.getElementById("tipoOperacion").value;
	tmp = tmp + '&parametro1=' + document.getElementById("parametro1").value;
	tmp = tmp + '&autorizacion=' + document.getElementById("autorizacion").value;
	tmp = tmp + '&fecModificacion=' + document.getElementById("fecModificacion").value;
	tmp = tmp + '&tipoOperacionFinal=' + document.getElementById("tipoOperacionFinal").value;
	tmp = tmp + '&codigoBusc=' + document.getElementById("codigo").value;
	tmp = tmp + '&descripcionBusc=' + document.getElementById("descripcion").value;
	tmp = tmp + '&entornoBusc=' + document.getElementById("entorno").value;
	tmp = tmp + '&iconoBusc=' + document.getElementById("icono").value;
	tmp = tmp + '&tipoOperacionBusc=' + document.getElementById("tipoOperacion").value;
	tmp = tmp + '&parametro1Busc=' + document.getElementById("parametro1").value;
	tmp = tmp + '&autorizacionBusc=' + document.getElementById("autorizacion").value;
	tmp = tmp + '&fecModificacionBusc=' + document.getElementById("fecModificacion").value;
	tmp = tmp + '&tipoOperacionFinalBusc=' + document.getElementById("tipoOperacionFinal").value;
	if (filaSel != null){
		tmp = tmp + '&fecModificacionIn=' + filaSel[7];
	}
	return tmp;
}

function modificar(){
	var tmp = '<%=atad.defina.entorno.Configuracion.getCfg("cfg.aplicacion")%>/operacionDetalle.jsp?accion=MODIFICAR';
	if (filaSel == null) {
		alert('Debe seleccionar un registro');
	} else {
		var s ="";
		//if (filaSel != null || document.getElementById("codigo").value == ""){
			//s = '&codigo=' + filaSel[0];
		//} else {
			s = generaCadena();
		//}
		location.href = tmp + s;
	}
}
function buscar(){
	b = 'true';
	var tmp = '<%=atad.defina.entorno.Configuracion.getCfg("cfg.aplicacion")%>/operacion.jsp?accion=BUSCAR&dialog=<%=dialog%>';
	window.navigate(tmp + generaCadena());
}

	function seleccionarAutorizacion() {
		var rc = window.showModalDialog('<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.autorizaciones.link")+"&dialog=true"%>','','dialogWidth:600px;dialogHeight:680px');
		if (rc != null) document.getElementById("autorizacion").value = rc[0];
	}
	
	function seleccionarIcono() {
		var rc = window.showModalDialog('<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.iconos.link")+"&dialog=true"%>','','dialogWidth:500px;dialogHeight:580px');
		if (rc != null) document.getElementById("icono").value = rc[0];
	}

</script>
</head>
<%
	Object [][] data = null;
	dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
	Object accion = request.getParameter("accion");

	// Flag indicador de si debe rellenar los campos
	boolean flag=true;
	if (accion == null) accion = "INICIAR";

	if ("INICIAR".equals(accion)){
		flag=false;
		if (p != null) {
			session.removeValue("operacionesPaginadas");
			p.liberar();
			p = null;
		}
		// Vacía la sesión
		session.putValue("busqueda.operacion.codigo",null);
		session.putValue("busqueda.operacion.descripcion",null);
		session.putValue("busqueda.operacion.icono",null);
		session.putValue("busqueda.operacion.tipoOperacion",null);
		session.putValue("busqueda.operacion.parametro1",null);
		session.putValue("busqueda.operacion.autorizacion",null);
		session.putValue("busqueda.operacion.fecModificacion",null);
		session.putValue("busqueda.operacion.tipoOperacionFinal",null);
		// Ejecuta la búsqueda sobre la tabla de operaciones, considerando el entorno y el canal
		//
		// <ETIQUETA>
		// Si las operaciones se van a mostrar en dialogo --> es q se van a colgar, por lo que no hay que mostrar la de tipo etiqueta
		if (dialog) {
			if (!opfinal){
				data = dao.getOperacionesModoDialogo(session.getValue("entorno"),session.getValue("canal"));		
			} else {
				data = dao.getOperacionesFinalesModoDialogo(session.getValue("entorno"),session.getValue("canal"));		
			}
		} else { 
			data = dao.getOperaciones(session.getValue("entorno"),session.getValue("canal"));
		}
		if (data != null && data.length > 0){
			p = new atad.defina.pres.Paginacion();
			p.setDatos(data);
			p.setFilasPorPagina(Integer.parseInt(atad.defina.entorno.Configuracion.getCfg("cfg.paginacion.filasPorPagina").toString()));
			data = p.getPaginaPrimera();
			session.putValue("operacionesPaginadas", p);
		}	
	} else if ("PAGINAR".equals(accion) && p != null) {
		int pagina = 1;
		try {
			pagina = Integer.parseInt(request.getParameter("pagina"));
		} catch(NumberFormatException e) {
		}
		data = p.getPagina(pagina - 1);
	} else if ("BUSCAR".equals(accion)){
		// Recupera todos los datos teniendo en cuenta los parámetros de búsqueda
		if (session.getValue("busqueda.operacion.codigo") == null){
			if (request.getParameter("codigoBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.codigo",request.getParameter("codigoBusc"));
			}
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.codigo") != request.getParameter("codigoBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.codigo",request.getParameter("codigoBusc"));
			}
		}
		if (session.getValue("busqueda.operacion.descripcion") == null){
			if (request.getParameter("descripcionBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.descripcion",request.getParameter("descripcionBusc"));
			}
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.descripcion") != request.getParameter("descripcionBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.descripcion",request.getParameter("descripcionBusc"));
			}
		}
		if (session.getValue("busqueda.operacion.icono") == null){
			if (request.getParameter("iconoBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.icono",request.getParameter("iconoBusc"));
			}	
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.icono") != request.getParameter("iconoBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.icono",request.getParameter("iconoBusc"));
			}
		}
		if (session.getValue("busqueda.operacion.tipoOperacion") == null){
			if (request.getParameter("tipoOperacionBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.tipoOperacion",request.getParameter("tipoOperacionBusc"));
			}
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.tipoOperacion") != request.getParameter("tipoOperacionBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.tipoOperacion",request.getParameter("tipoOperacionBusc"));
			}
		}
		if (session.getValue("busqueda.operacion.parametro1") == null){
			if (request.getParameter("parametro1Busc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.parametro1",request.getParameter("parametro1Busc"));
			}
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.parametro1") != request.getParameter("parametro1Busc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.parametro1",request.getParameter("parametro1Busc"));
			}
		}
		if (session.getValue("busqueda.operacion.autorizacion") == null){
			if (request.getParameter("autorizacionBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.autorizacion",request.getParameter("autorizacionBusc"));
			}	
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.autorizacion") != request.getParameter("autorizacionBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.autorizacion",request.getParameter("autorizacionBusc"));
			}
		}
		if (session.getValue("busqueda.operacion.fecModificacion") == null){
			if (request.getParameter("fecModificacionBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.fecModificacion",request.getParameter("fecModificacionBusc"));
			}
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.fecModificacion") != request.getParameter("fecModificacionBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.fecModificacion",request.getParameter("fecModificacionBusc"));
			}
		}
		if (session.getValue("busqueda.operacion.tipoOperacionFinal") == null){
			if (request.getParameter("tipoOperacionFinalBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.tipoOperacionFinal",request.getParameter("tipoOperacionFinalBusc"));
			}
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.tipoOperacionFinal") != request.getParameter("tipoOperacionFinalBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.tipoOperacionFinal",request.getParameter("tipoOperacionFinalBusc"));
			}
		}
		
		//
		// <ETIQUETA>
		// Cuando el listado de operaciones se realiza en modo dialogo no se debe tener en cuenta las operaciones de tipo
		// etiqueta en las busquedas
		if (dialog) {
			Object tipoOp = null;
			data = dao.buscaOperacionesModoDialogo(new Object[]{session.getValue("canal"),
			session.getValue("busqueda.operacion.codigo"),
			session.getValue("busqueda.operacion.descripcion"),
			(request.getParameter("entorno")==null || request.getParameter("entorno").toString().trim().length()==0)?session.getValue("entorno"):request.getParameter("entorno"),
			session.getValue("busqueda.operacion.icono"),
			session.getValue("busqueda.operacion.tipoOperacion"),
			session.getValue("busqueda.operacion.parametro1"),
			session.getValue("busqueda.operacion.autorizacion"),
			session.getValue("busqueda.operacion.fecModificacion"),
			session.getValue("busqueda.operacion.tipoOperacionFinal")});
		}
		else {
			data = dao.buscaOperaciones(new Object[]{session.getValue("canal"),
			session.getValue("busqueda.operacion.codigo"),
			session.getValue("busqueda.operacion.descripcion"),
			(request.getParameter("entorno")==null || request.getParameter("entorno").toString().trim().length()==0)?session.getValue("entorno"):request.getParameter("entorno"),
			session.getValue("busqueda.operacion.icono"),
			session.getValue("busqueda.operacion.tipoOperacion"),
			session.getValue("busqueda.operacion.parametro1"),
			session.getValue("busqueda.operacion.autorizacion"),
			session.getValue("busqueda.operacion.fecModificacion"),
			session.getValue("busqueda.operacion.tipoOperacionFinal")});
		}
		
			
		p = new atad.defina.pres.Paginacion();
		if (data != null && data.length > 0){
			p.setDatos(data);
		} else {
			// Vacía el objeto paginación de la memoria
			session.removeValue("operacionesPaginadas");
			p.setDatos(null);
		}
		p.setFilasPorPagina(Integer.parseInt(atad.defina.entorno.Configuracion.getCfg("cfg.paginacion.filasPorPagina").toString()));
		data = p.getPaginaPrimera();
		session.putValue("operacionesPaginadas", p);			
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
%>
<body>
<script> window.setTimeout("alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';", <%=""+request.getSession().getMaxInactiveInterval()*1000%>);</script>
<table width="100%"  border="0" cellspacing="8" cellpadding="0">
  <tr>
    <td height="22" class="CabeceraRelacionada2">&nbsp;&nbsp;OPERACIONES</td>
  </tr>
  <tr>
    <td class="TextoTablaN"><fieldset><legend>B&uacute;squeda</legend>
    <table width="100%"  border="0" cellspacing="8" cellpadding="0">
      <tr>
        <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="1%" nowrap><label>C&oacute;digo&nbsp;&nbsp;&nbsp;</label>
                <br>
                <input name="codigo" type="text" maxlength="8" class="CampoEntrada" size="9" value="<%=(session.getValue("busqueda.operacion.codigo")!=null)?session.getValue("busqueda.operacion.codigo"):((flag && request.getParameter("codigo")!=null)?request.getParameter("codigo"):"")%>">&nbsp;&nbsp;&nbsp;</td>
            <td width="1%" nowrap><label>Nombre&nbsp;&nbsp;&nbsp;</label>
                <br>
                <input name="descripcion" type="text" maxlength="40" class="CampoEntrada" size="35" value="<%=(session.getValue("busqueda.operacion.descripcion")!=null)?session.getValue("busqueda.operacion.descripcion"):((flag && request.getParameter("descripcion")!=null)?request.getParameter("descripcion"):"")%>">
                &nbsp;&nbsp;&nbsp;</td>
            <td width="1%" nowrap><label>Entorno&nbsp;&nbsp;&nbsp;</label>
                <br>
                <select size="1" name="entorno" class="CampoEntrada">
                <option value=" ">Sin c&oacute;digo</option>
                <%
                	// Recupera los entornos de la sesión
                	Object[] entornos = (Object[])session.getValue("entornos");
                	Object dato = null;
                	if (entornos != null && entornos.length > 0){
                		String cadena = null;
                		dato = session.getValue("entorno");
                		for (int i=0;i<entornos.length;i++){
											cadena = entornos[i].toString().substring(0, 1);
                			if (dato == null){
                				out.print("<option value=\"" + cadena + "\"" + ((session.getValue("entorno")==cadena)?"selected":"") + ">" + entornos[i] + "</option>");
                			} else {
                				out.print("<option value=\"" + cadena + "\"" + ((session.getValue("entorno").equals(cadena))?"selected":"") + ">" + entornos[i] + "</option>");
                			}
                		}
                	}
                %>
              </select>&nbsp;&nbsp;&nbsp;</td>
            <td width="1%" nowrap><label>Icono&nbsp;&nbsp;&nbsp;</label>
            	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr>
                <td><input name="icono" type="text" maxlength="30" class="CampoSalida" size="15" value="<%=(session.getValue("busqueda.operacion.icono")!=null)?session.getValue("busqueda.operacion.icono"):((flag && request.getParameter("icono")!=null)?request.getParameter("icono"):"")%>">&nbsp;&nbsp;&nbsp;</td>
                <td><img src="images/BotonPrismatico.gif" width="19" height="18" onClick="seleccionarIcono();">&nbsp;&nbsp;&nbsp;</td>
                </tr>
              </table>
            </td>
            <td width="96%" nowrap><label>Tipo elemen.&nbsp;&nbsp;&nbsp;</label>
                <br>
                <select size="1" name="tipoOperacion" class="CampoEntrada">
                  <option value=" ">Sin tipo</option>
                  <%
                  	// Recupera los tipos de operación
                  	Object[][] datos = dao.getTiposOperacion();
                  	dato = null;
                  	if (datos != null && datos.length > 0){
                  		dato = request.getParameter("tipoOperacion");
                  		for (int i=0;i<datos.length;i++){
							// 
							// <ETIQUETA>
							// Se comprueba si el canal es pesados para NO mostrar el tipo de operacion Etiqueta
							// Si es un dialogo tampoco hay q mostrar en la combo el tipo etiqueta xq se trata de la operacon "Colgar"
							if  (("Ligeros".equals(session.getValue("canal")) && "A".equals(datos[i][0])) || (dialog && "A".equals(datos[i][0]))) 
								continue;
                  			if (dato == null){
                  				if(session.getValue("busqueda.operacion.tipoOperacion")!=null && session.getValue("busqueda.operacion.tipoOperacion")==datos[i][0]){
                  					out.print("<option value=\"" + datos[i][0] + "\" selected>" + datos[i][1] + "</option>");
                  				} else {
                  					out.print("<option value=\"" + datos[i][0] + "\"" + ((flag && request.getParameter("tipoOperacion")==datos[i][0])?"selected":"") + ">" + datos[i][1] + "</option>");
                  				}
                  			} else {
                  				if(session.getValue("busqueda.operacion.tipoOperacion")!=null && session.getValue("busqueda.operacion.tipoOperacion")==datos[i][0]){
                  					out.print("<option value=\"" + datos[i][0] + "\" selected>" + datos[i][1] + "</option>");
                  				} else {
                  					out.print("<option value=\"" + datos[i][0] + "\"" + ((flag && request.getParameter("tipoOperacion").equals(datos[i][0]))?"selected":"") + ">" + datos[i][1] + "</option>");
                  				}
                  			}
                  		}
                  	}
                  %>
              </select>&nbsp;&nbsp;&nbsp;</td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="1%" nowrap><label>Par&aacute;metro 1&nbsp;&nbsp;&nbsp;</label>
                <br>
                <input name="parametro1" type="text" maxlength="200" class="CampoEntrada" size="15" value="<%=(session.getValue("busqueda.operacion.parametro1")!=null)?session.getValue("busqueda.operacion.parametro1"):((flag && request.getParameter("parametro1")!=null)?request.getParameter("parametro1"):"")%>">&nbsp;&nbsp;&nbsp;</td>
            <td width="1%" nowrap><label></label>
              C&oacute;digo autorizaci&oacute;n <br>
              <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr>
									<!-- Fix: Se reduce el ancho del campo de 15 a 12 para eliminar el scroll horizontal -->  									 
                  <td><input name="autorizacion" type="text" maxlength="8" class="CampoSalida" size="12" value="<%=(session.getValue("busqueda.operacion.autorizacion")!=null)?session.getValue("busqueda.operacion.autorizacion"):((flag && request.getParameter("autorizacion")!=null)?request.getParameter("autorizacion"):"")%>">&nbsp;</td>
                  <td><img src="images/BotonPrismatico.gif" width="19" height="18" onClick="seleccionarAutorizacion();">&nbsp;&nbsp;&nbsp;</td>
                </tr>
              </table></td>
            <td width="1%" nowrap><label>Fecha modificaci&oacute;n&nbsp;&nbsp;&nbsp;</label>
                <br>
								<!-- Fix: Se reduce el ancho del campo de 20 a 15 para eliminar el scroll horizontal -->  									 
                <input name="fecModificacion" type="text" maxlength="10" class="CampoEntrada" size="15" value="<%=(session.getValue("busqueda.operacion.fecModificacion")!=null)?session.getValue("busqueda.operacion.fecModificacion"):((flag && request.getParameter("fecModificacion")!=null)?request.getParameter("fecModificacion"):"")%>">&nbsp;&nbsp;&nbsp;</td>
            <td width="97%" nowrap><label>Operaci&oacute;n final &nbsp;&nbsp;&nbsp;</label>
              <br>
              <select size="1" name="tipoOperacionFinal" class="CampoEntrada">
                <option> </option>
                <%
                	// Recupera los tipos de operaciones finales
                	dato = null;
                	Object[][] tipos = dao.getTipoOperacionFinal();
                	if (tipos != null && tipos.length > 0){
                		dato = request.getParameter("tipoOperacionFinal");
                		for (int i=0;i<tipos.length;i++){
                			if (dato == null){
                				if(session.getValue("busqueda.operacion.tipoOperacionFinal")!=null && session.getValue("busqueda.operacion.tipoOperacionFinal")==tipos[i][0]){
                					out.print("<option value=\"" + tipos[i][0] + "\" selected>" + tipos[i][0] + "-" + tipos[i][1] + " [" + tipos[i][2] + " parms (" + tipos[i][3] + ")]</option>");
                				} else {
                					out.print("<option value=\"" + tipos[i][0] + "\"" + ((flag && request.getParameter("tipoOperacionFinal")==tipos[i][0].toString())?"selected":"") + ">" + tipos[i][0] + "-" + tipos[i][1] + " [" + tipos[i][2] + " parms (" + tipos[i][3] + ")]</option>");
                				}
                			} else {
                				if(session.getValue("busqueda.operacion.tipoOperacionFinal")!=null && session.getValue("busqueda.operacion.tipoOperacionFinal")==tipos[i][0]){
                					out.print("<option value=\"" + tipos[i][0] + "\" selected>" + tipos[i][0] + "-" + tipos[i][1] + " [" + tipos[i][2] + " parms (" + tipos[i][3] + ")]</option>");
                				} else {
                					out.print("<option value=\"" + tipos[i][0] + "\"" + ((flag && request.getParameter("tipoOperacionFinal").equals(tipos[i][0]))?"selected":"") + ">" + tipos[i][0] + "-" + tipos[i][1] + " [" + tipos[i][2] + " parms (" + tipos[i][3] + ")]</option>");
                				}
                			}
                		}
                	}	
                %>
              </select>
              &nbsp;&nbsp;&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table>
    </fieldset></td>
  </tr>
  <tr>
    <td align="right"><input type="button" name="Button3" value="Buscar" class="Boton" onClick="buscar();"></td>
  </tr>
  <tr>
    <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="98%" class="TituloTabla">Listado de operaciones </td>
        <td width="1%" nowrap>
        	<img src="images/Izq1.gif" width="17" height="17" onClick="paginar(1);">
        	<img src="images/Izq2.gif" width="17" height="17" onClick="paginar(<%=(p==null?1:p.getPaginaActual())%>);">
        	<img src="images/Dere2.gif" width="17" height="17" onClick="paginar(<%=(p==null?1:p.getPaginaActual() + 2)%>);">
        	<img src="images/Dere1.gif" width="17" height="17" onClick="paginar(<%=(p==null?1:p.getTotalPaginas())%>);">
        </td>
        <td width="1%" nowrap>&nbsp;&nbsp;P&aacute;gina:
            <input name="paginaActual" type="text" class="CampoEntradaImporte" value=<%=(p==null?1:(data==null)?0:p.getPaginaActual()+1)%> size="3">
      /
      <input name="paginaTotal" type="text" class="CampoSalidaImporte" value=<%=(p==null?1:p.getTotalPaginas())%> size="3">
&nbsp;
      <input type="button" name="Button2" value="Ir" class="Boton" style="width:40px" onClick="paginar(document.getElementById('paginaActual').value);"></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="100%" colspan="3">
          <table width="100%" border="0" cellpadding="0" cellspacing="2" class="TablaDatos">
            <tr class="CabeceraTabla">
              <td width="1%" class="CabeceraTabla">&nbsp;</td>
              <td width="7%" nowrap class="CabeceraTabla">C&oacute;digo <img width="13" height="13" src="images/AT_ExcluirVert.gif" onClick="ordenarTabla(0,true)"><img width="13" height="13" src="images/AT_IncluirVert.gif" onClick="ordenarTabla(0,false)"></td>
              <td width="30%" class="CabeceraTabla">Nombre de Operaci&oacute;n <img width="13" height="13" src="images/AT_ExcluirVert.gif" onClick="ordenarTabla(1,true)"><img width="13" height="13" src="images/AT_IncluirVert.gif" onClick="ordenarTabla(1,false)"></td>
              <td width="5%" nowrap class="CabeceraTabla">Entorno <img width="13" height="13" src="images/AT_ExcluirVert.gif" onClick="ordenarTabla(2,true)"><img width="13" height="13" src="images/AT_IncluirVert.gif" onClick="ordenarTabla(2,false)"></td>
              <td width="5%" nowrap class="CabeceraTabla">Icono <img width="13" height="13" src="images/AT_ExcluirVert.gif" onClick="ordenarTabla(3,true)"><img width="13" height="13" src="images/AT_IncluirVert.gif" onClick="ordenarTabla(3,false)"></td>
              <td width="5%" nowrap class="CabeceraTabla">Tipo Elem. <img width="13" height="13" src="images/AT_ExcluirVert.gif" onClick="ordenarTabla(4,true)"><img width="13" height="13" src="images/AT_IncluirVert.gif" onClick="ordenarTabla(4,false)"></td>
              <td width="30%" class="CabeceraTabla">Par&aacute;metro 1 <img width="13" height="13" src="images/AT_ExcluirVert.gif" onClick="ordenarTabla(5,true)"><img width="13" height="13" src="images/AT_IncluirVert.gif" onClick="ordenarTabla(5,false)"></td>
              <td width="7%" nowrap class="CabeceraTabla">Autorizaci&oacute;n <img width="13" height="13" src="images/AT_ExcluirVert.gif" onClick="ordenarTabla(6,true)"><img width="13" height="13" src="images/AT_IncluirVert.gif" onClick="ordenarTabla(6,false)"></td>
               <td width="10%" class="CabeceraTabla">Fecha Modificaci&oacute;n <img width="13" height="13" src="images/AT_ExcluirVert.gif" onClick="ordenarTabla(7,true)"><img width="13" height="13" src="images/AT_IncluirVert.gif" onClick="ordenarTabla(7,false)"></td>
            </tr>
            <%
            	int ind=2;
            	if (data != null && data.length > 0){
            		// Hay datos que cargar en la tabla
            		for (int i=0;i<data.length;i++){
															
	            		// Establece el pijama
	            		if (ind==2){
	            			ind = 1;
	            		} else if (ind ==1){
	            			ind = 2;
	            		}
            			out.println("<tr class=\"Pijama" + ind + "\">");
            			out.println("<td width=\"1%\" class=\"TextoTablaN\"><input name=\"radiobutton\" type= \"radio\" onClick='seleccion(\"" + data[i][0] + "<sep>" + data[i][1] + "<sep>" + data[i][2] + "<sep>" + data[i][3] + "<sep>" + data[i][4] + "<sep>" + atad.defina.pres.Utils.escapaComillas((String)data[i][5]) + "<sep>" + data[i][6] + "<sep>" + data[i][7] + "\");'></td>");
            			out.println("<td class=\"TextoTablaN\">");
            			// Solo establece el enlace cuando no se trata de un diálogo
            			if(!dialog){
            				//out.print("<a href=\"operacionDetalle.jsp?accion=MODIFICAR&referrer=operacion.jsp&codigo=" + data[i][0] + "&fecModificacion=" + data[i][7] + "\" target=\"_self\">");
            					out.print("<a href=\"#\" target=\"_self\" onClick='seleccion(\"" + data[i][0] + "<sep>" + data[i][1] + "<sep>" + data[i][2] + "<sep>" + data[i][3] + "<sep>" + data[i][4] + "<sep>" + atad.defina.pres.Utils.escapaComillas((String)data[i][5]) + "<sep>" + data[i][6] + "<sep>" + data[i][7] + "\");modificar();'>");
            			}
            			out.print(data[i][0]);
            			if (!dialog){
            				out.print("</a>");
            			}
            			out.println("</td>");
            			out.println("<td class=\"TextoTablaN\">" + (data[i][1]==null?"":data[i][1]) + "</td>");
            			out.println("<td nowrap class=\"TextoTablaN\">" + data[i][2] + " </td>");
            			out.println("<td nowrap class=\"TextoTablaN\"><img src=\"images/" + data[i][3] + ".gif\" width=\"17\" height=\"17\"></td>");
            			out.println("<td nowrap class=\"TextoTablaN\">" + data[i][4] + " </td>");
            			out.println("<td class=\"TextoTablaN\">" + (data[i][5]==null?"":data[i][5]) + "</td>");
            			//out.println("<td nowrap class=\"TextoTablaN\">" + (data[i][5]==null?"":data[i][5]) + "</td>");
            			out.println("<td class=\"TextoTablaN\">" + (data[i][6]==null?"":data[i][6]) + "</td>");
            			//out.println("<td nowrap class=\"TextoTablaN\">" + atad.defina.pres.Utils.formatTimeStamp((java.sql.Timestamp) data[i][7]) + " </td>");
            			out.println("<td nowrap class=\"TextoTablaN\">" + (data[i][7]==null?"":data[i][7].toString().substring(0,data[i][7].toString().indexOf(" "))) + " </td>");
            			out.println("</tr>");
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
	String disabled=null;
	if (atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
		disabled = "disabled=\"true\"";
	} else {
		disabled = "";
	}
	if (dialog) {
		out.println("<input type=\"button\" name=\"Button1\" value=\"Aceptar\" class=\"Boton\" onClick=\"aceptarDialogo();\" " + disabled + ">");
		out.println("<input type=\"button\" name=\"Button2\" value=\"Cancelar\" class=\"Boton\" onClick=\"cancelarDialogo();\">");
		out.println("<script language=\"javascript\" type=\"text/javascript\">fixDialog();</script>");
	} else {
		out.println("<input type=\"button\" name=\"Button\" value=\"A&ntilde;adir\" class=\"Boton\" onClick=\"nuevo();\"" + disabled + ">");
		out.println("<input type=\"button\" name=\"Button4\" value=\"Modificar\" class=\"Boton\" onClick=\"modificar();\">");
		out.println("<input type=\"button\" name=\"Button5\" value=\"Suprimir\" class=\"Boton\" onClick=\"borrar();\"" + disabled + ">");
		out.println("<input type=\"button\" name=\"Button62\" value=\"Ver árbol\" class=\"Boton\" onClick=\"verArbol();\">");
	}
	%>
	</td>
  </tr>
</table>
</body>
</html>

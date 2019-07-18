<%! atad.defina.datos.AccesoDatosBase dao = null;%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
<script language="JavaScript">
var sel = null;
<%
	atad.defina.pres.Paginacion p = (atad.defina.pres.Paginacion) session.getValue("operacionesPaginadas");
	// Recupera el tipo de perfil de la sesión
	String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();
%>
function ordenarTabla(col,ascendente){
	var pagina = <%=(p==null?1:p.getPaginaActual())%>;
	//var tmp = 'operacion.jsp?accion=ORDENAR&columna='+col+'&ascendente='+ascendente+generaCadena();
	var tmp = 'operacionesRelacionadas.jsp?accion=ORDENAR&columna='+col+'&ascendente='+ascendente+generaCadena()+ generaCadenaRet();
	tmp = tmp + '&pagina=' + Number(pagina);
	//window.navigate(tmp);	
	location.href=tmp;
}
function habilitaBoton(){
	if (sel != null){
		document.getElementById("Button").disabled=false;
	} else {
		document.getElementById("Button").disabled=true;
	}
}
function clickEnlace(codOp,fecha){
	// Genera la cadena con los datos que hay que transmitir entre las distintas pantallas
  var cadena = "&codigoprev=" + document.getElementById('codigodos').value;
  cadena = cadena + "&codigoBusc=" + document.getElementById('codigo').value;
  cadena = cadena + "&fecModificacionprev=" + document.getElementById('fecModificaciondos').value;
  cadena = cadena + "&fecModificacionBusc=" + document.getElementById('fecModificacion').value;
  cadena = cadena + "&descripcionprev=" + document.getElementById('descripciondos').value;
  cadena = cadena + "&descripcionBusc=" + document.getElementById('descripcion').value;
  cadena = cadena + "&entornoprev=" + document.getElementById('entornodos').value;
  cadena = cadena + "&entornoBusc=" + document.getElementById('entorno').value;
  cadena = cadena + "&tooltipprev=" + document.getElementById('tooltipdos').value;
  cadena = cadena + "&descAdprev=" + document.getElementById('descAddos').value;
  cadena = cadena + "&iconoprev=" + document.getElementById('iconodos').value;
  cadena = cadena + "&iconoBusc=" + document.getElementById('icono').value;
  cadena = cadena + "&autorizacionprev=" + document.getElementById('autorizaciondos').value;
  cadena = cadena + "&autorizacionBusc=" + document.getElementById('autorizacion').value;
  cadena = cadena + "&tipoOperacionprev=" + document.getElementById('tipoOperaciondos').value;
  cadena = cadena + "&tipoOperacionBusc=" + document.getElementById('tipoOperacion').value;
  cadena = cadena + "&aplicacionprev=" + document.getElementById('aplicaciondos').value;
  cadena = cadena + "&teclaMenuprev=" + document.getElementById('teclaMenudos').value;
  cadena = cadena + "&pseudocodigo1prev=" + document.getElementById('pseudocodigo1dos').value;
  cadena = cadena + "&pseudocodigo2prev=" + document.getElementById('pseudocodigo2dos').value;
  cadena = cadena + "&atributosprev=" + document.getElementById('atributosdos').value;
  cadena = cadena + "&codCanalprev=" + document.getElementById('codCanaldos').value;
  cadena = cadena + "&tipoOperacionFinalprev=" + document.getElementById('tipoOperacionFinaldos').value;
  cadena = cadena + "&parametro1prev=" + document.getElementById('parametro1dos').value;
  cadena = cadena + "&parametro1Busc=" + document.getElementById('parametro1').value;
  cadena = cadena + "&parametro2prev=" + document.getElementById('parametro2dos').value;
  cadena = cadena + "&parametro3prev=" + document.getElementById('parametro3dos').value;
  cadena = cadena + "&accprev=" + document.getElementById('accprev').value;
  cadena = cadena + "&usarSesion=false";
	//cadena = "operacionDetalle.jsp?accion=MODIFICAR&referrer=operacionesRelacionadas.jsp&accionref=INICIAR&codigo=" + codOp + cadena + "&fecModificacion=" + fecha;
	cadena = "operacionDetalle.jsp?accion=MODIFICAR&referrer=operacionesRelacionadas.jsp&accionref=BUSCAR&codigo=" + codOp + cadena + "&fecModificacion=" + fecha;
	location.href=cadena;
}
function seleccion(str){
	nombre = str.substring(0,str.indexOf(" - "));
	str = str.substring(str.indexOf(" - ") + 3,str.length);
	// Cuando se selecciona la checkbox
	if (document.getElementById(nombre).checked){
		if (sel == null){
			sel = str;
		} else {
			sel = sel + "<obj>" + str;
		}
	// Cuando se deselecciona la checkbox
	} else {
		if (sel.indexOf(str)!=-1){
			var prev = sel.substring(0,sel.indexOf(str));
			if (prev.length > 0 && prev.lastIndexOf("<obj>")==(prev.length-"<obj>".length)){
				prev = prev.substring(0,prev.length-"<obj>".length);
			}
			var post = sel.substring(sel.indexOf(str)+str.length,sel.length);
			if (prev.length == 0){
				post = post.substring(5,post.length);
			}
			sel = prev + post;
		}
	}
	if (sel.length==0){
		sel = null;
	}
	habilitaBoton();
}
function seleccionarAutorizacion() {
	var rc = window.showModalDialog('<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.autorizaciones.link")+"&dialog=true"%>','','dialogWidth:600px;dialogHeight:680px');
	if (rc != null) document.getElementById("autorizacion").value = rc[0];
}

function seleccionarIcono() {
	var rc = window.showModalDialog('<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.iconos.link")+"&dialog=true"%>','','dialogWidth:500px;dialogHeight:580px');
	if (rc != null) document.getElementById("icono").value = rc[0];
}

function cancelar(){
 location.href='operacionDetalle.jsp?accion=CANCELADO&ret=opRel&' + generaCadenaRet();;
}
function aceptar(){
	var tmp = 'operacionDetalle.jsp?accion=RELACIONAR';
	tmp = tmp + '&sel=' + sel + generaCadenaRet();
	location.href = tmp;
}
function paginar(pagina) {
	var tmp = 'operacionesRelacionadas.jsp?accion=PAGINAR';
	tmp = tmp + '&pagina=' + pagina + generaCadenaRet();
	location.href = tmp;	
}
function buscar(){
	var tmp = '<%=atad.defina.entorno.Configuracion.getCfg("cfg.aplicacion")%>/operacionesRelacionadas.jsp?accion=BUSCAR';
	location.href=tmp+generaCadena() + generaCadenaRet();
}
function generaCadena(){
	var tmp = '&codigoBusc=' + document.getElementById("codigo").value;
	tmp = tmp + '&descripcionBusc=' + document.getElementById("descripcion").value;
	tmp = tmp + '&entornoBusc=' + document.getElementById("entorno").value;
	tmp = tmp + '&iconoBusc=' + document.getElementById("icono").value;
	tmp = tmp + '&tipoOperacionBusc=' + document.getElementById("tipoOperacion").value;
	tmp = tmp + '&parametro1Busc=' + document.getElementById("parametro1").value;
	tmp = tmp + '&autorizacionBusc=' + document.getElementById("autorizacion").value;
	tmp = tmp + '&fecModificacionBusc=' + document.getElementById("fecModificacion").value;
	return tmp;
}
function generaCadenaRet(){
	var tmp = '&codigo=' + document.getElementById("codigodos").value;
	tmp = tmp + '&fecModificacion=' + document.getElementById("fecModificaciondos").value;
	tmp = tmp + '&fecModificacionIn=' + document.getElementById("fecModificacionIn").value;
	tmp = tmp + '&descripcion=' + document.getElementById("descripciondos").value;
	tmp = tmp + '&entorno=' + document.getElementById("entornodos").value;
	tmp = tmp + '&tooltip=' + document.getElementById("tooltipdos").value;
	tmp = tmp + '&descAd=' + document.getElementById("descAddos").value;
	tmp = tmp + '&icono=' + document.getElementById("iconodos").value;
	tmp = tmp + '&autorizacion=' + document.getElementById("autorizaciondos").value;
	tmp = tmp + '&tipoOperacion=' + document.getElementById("tipoOperaciondos").value;
	tmp = tmp + '&aplicacion=' + document.getElementById("aplicaciondos").value;
	tmp = tmp + '&teclaMenu=' + document.getElementById("teclaMenudos").value;
	tmp = tmp + '&pseudocodigo1=' + document.getElementById("pseudocodigo1dos").value;
	tmp = tmp + '&pseudocodigo2=' + document.getElementById("pseudocodigo2dos").value;
	tmp = tmp + '&atributos=' + document.getElementById("atributosdos").value;
	tmp = tmp + '&codCanal=' + document.getElementById("codCanaldos").value;
	tmp = tmp + '&tipoOperacionFinal=' + document.getElementById("tipoOperacionFinaldos").value;
	tmp = tmp + '&parametro1=' + document.getElementById("parametro1dos").value;
	tmp = tmp + '&parametro2=' + document.getElementById("parametro2dos").value;
	tmp = tmp + '&parametro3=' + document.getElementById("parametro3dos").value;
	tmp = tmp + '&accprev=' + document.getElementById("accprev").value;
	return tmp;
}
</script>
</head>
<%
	Object [][] data = null;
	dao = atad.defina.datos.AccesoDatosBase.getInstance(session);

	Object accion = request.getParameter("accion");
	if (accion != null){
		if ("INICIAR".equals(accion)){
			// Elimina la información almacenada en la sesión
			session.putValue("busqueda.operacionrel.codigo",null);
			session.putValue("busqueda.operacionrel.descripcion",null);
			session.putValue("busqueda.operacionrel.entorno",null);
			session.putValue("busqueda.operacionrel.icono",null);
			session.putValue("busqueda.operacionrel.tipoOperacion",null);
			session.putValue("busqueda.operacionrel.parametro1",null);
			session.putValue("busqueda.operacionrel.autorizacion",null);
			session.putValue("busqueda.operacionrel.fecModificacion",null);
			if (p != null) {
				session.removeValue("operacionesPaginadas");
				p.liberar();
				p = null;
			}
			//
			// <ETIQUETA>
			// En lugar de recuperar todas las operaciones se obtienen solo las relacionadas (F y A)
			// 
			data = dao.getOperacionesRelacionadas(session.getValue("entorno"),session.getValue("canal"));
			if (data != null && data.length > 0){
				p = new atad.defina.pres.Paginacion();
				p.setDatos(data);
				p.setFilasPorPagina(Integer.parseInt(atad.defina.entorno.Configuracion.getCfg("cfg.paginacion.filasPorPagina").toString()));
				data = p.getPaginaPrimera();
				session.putValue("operacionesPaginadas", p);
			}
		} else if ("PAGINAR".equals(accion)){
			int pagina = 1;
			try {
				pagina = Integer.parseInt(request.getParameter("pagina"));
			} catch(NumberFormatException e) {
			}
			data = p.getPagina(pagina - 1);
		} else if ("BUSCAR".equals(accion)){
			// Recupera todos los datos teniendo en cuenta los parámetros de búsqueda
			if (session.getValue("busqueda.operacionrel.codigo") == null){
				if (request.getParameter("codigoBusc") != null){
					// No hay dato actualizado en la sesión ==> Actualizo la información
					session.putValue("busqueda.operacionrel.codigo",request.getParameter("codigoBusc"));
				}
			} else {
				// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
				if (session.getValue("busqueda.operacionrel.codigo") != request.getParameter("codigoBusc")){
					// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
					session.putValue("busqueda.operacionrel.codigo",request.getParameter("codigoBusc"));
				}
			}
			if (session.getValue("busqueda.operacionrel.descripcion") == null){
				if (request.getParameter("descripcionBusc") != null){
					// No hay dato actualizado en la sesión ==> Actualizo la información
					session.putValue("busqueda.operacionrel.descripcion",request.getParameter("descripcionBusc"));
				}
			} else {
				// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
				if (session.getValue("busqueda.operacionrel.descripcion") != request.getParameter("descripcionBusc")){
					// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
					session.putValue("busqueda.operacionrel.descripcion",request.getParameter("descripcionBusc"));
				}
			}
			if (session.getValue("busqueda.operacionrel.entorno") == null){
				if (request.getParameter("entornoBusc") != null){
					// No hay dato actualizado en la sesión ==> Actualizo la información
					session.putValue("busqueda.operacionrel.entorno",request.getParameter("entornoBusc"));
				}
			} else {
				// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
				if (session.getValue("busqueda.operacionrel.entorno") != request.getParameter("entornoBusc")){
					// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
					session.putValue("busqueda.operacionrel.entorno",request.getParameter("entornoBusc"));
				}
			}			
			if (session.getValue("busqueda.operacionrel.icono") == null){
				if (request.getParameter("iconoBusc") != null){
					// No hay dato actualizado en la sesión ==> Actualizo la información
					session.putValue("busqueda.operacionrel.icono",request.getParameter("iconoBusc"));
				}
			} else {
				// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
				if (session.getValue("busqueda.operacionrel.icono") != request.getParameter("iconoBusc")){
					// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
					session.putValue("busqueda.operacionrel.icono",request.getParameter("iconoBusc"));
				}
			}
			if (session.getValue("busqueda.operacionrel.tipoOperacion") == null){
				if (request.getParameter("tipoOperacionBusc") != null){
					// No hay dato actualizado en la sesión ==> Actualizo la información
					session.putValue("busqueda.operacionrel.tipoOperacion",request.getParameter("tipoOperacionBusc"));
				}
			} else {
				// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
				if (session.getValue("busqueda.operacionrel.tipoOperacion") != request.getParameter("tipoOperacionBusc")){
					// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
					session.putValue("busqueda.operacionrel.tipoOperacion",request.getParameter("tipoOperacionBusc"));
				}
			}
			if (session.getValue("busqueda.operacionrel.parametro1") == null){
				if (request.getParameter("parametro1Busc") != null){
					// No hay dato actualizado en la sesión ==> Actualizo la información
					session.putValue("busqueda.operacionrel.parametro1",request.getParameter("parametro1Busc"));
				}
			} else {
				// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
				if (session.getValue("busqueda.operacionrel.parametro1") != request.getParameter("parametro1Busc")){
					// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
					session.putValue("busqueda.operacionrel.parametro1",request.getParameter("parametro1Busc"));
				}
			}
			if (session.getValue("busqueda.operacionrel.autorizacion") == null){
				if (request.getParameter("autorizacionBusc") != null){
					// No hay dato actualizado en la sesión ==> Actualizo la información
					session.putValue("busqueda.operacionrel.autorizacion",request.getParameter("autorizacionBusc"));
				}
			} else {
				// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
				if (session.getValue("busqueda.operacionrel.autorizacion") != request.getParameter("autorizacionBusc")){
					// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
					session.putValue("busqueda.operacionrel.autorizacion",request.getParameter("autorizacionBusc"));
				}
			}
			if (session.getValue("busqueda.operacionrel.fecModificacion") == null){
				if (request.getParameter("fecModificacionBusc") != null){
					// No hay dato actualizado en la sesión ==> Actualizo la información
					session.putValue("busqueda.operacionrel.fecModificacion",request.getParameter("fecModificacionBusc"));
				}
			} else {
				// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
				if (session.getValue("busqueda.operacionrel.fecModificacion") != request.getParameter("fecModificacionBusc")){
					// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
					session.putValue("busqueda.operacionrel.fecModificacion",request.getParameter("fecModificacionBusc"));
				}
			}
			//
			// <ETIQUETA>
			// La busqueda ahora considera solo los tipos que pueden añadirse como operaciones relacionadas, es decir, F y A
			data = dao.buscaOperacionesRelacionadas(new Object[]{session.getValue("canal"),
				session.getValue("busqueda.operacionrel.codigo"),
				session.getValue("busqueda.operacionrel.descripcion"),
				session.getValue("busqueda.operacionrel.entorno"),
				session.getValue("busqueda.operacionrel.icono"),
				session.getValue("busqueda.operacionrel.tipoOperacion"),
				session.getValue("busqueda.operacionrel.parametro1"),
				session.getValue("busqueda.operacionrel.autorizacion"),
				session.getValue("busqueda.operacionrel.fecModificacion"),""});
			p = new atad.defina.pres.Paginacion();
			if (data != null && data.length > 0){
				p.setDatos(data);
			}	else {
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
	}
%>
<body>
<script> window.setTimeout("alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';", <%=""+request.getSession().getMaxInactiveInterval()*1000%>);</script>
<input type="hidden" name="codigodos" value="<%=(request.getParameter("codigo")==null?"":request.getParameter("codigo"))%>">
<input type="hidden" name="fecModificaciondos" value="<%=(request.getParameter("fecModificacion")==null?"":request.getParameter("fecModificacion"))%>">
<input type="hidden" name="fecModificacionIn" value="<%=(request.getParameter("fecModificacionIn")==null?"":request.getParameter("fecModificacionIn"))%>">	
<input type="hidden" name="descripciondos" value="<%=(request.getParameter("descripcion")==null?"":request.getParameter("descripcion"))%>">
<input type="hidden" name="entornodos" value="<%=(request.getParameter("entorno")==null?"":request.getParameter("entorno"))%>">
<input type="hidden" name="tooltipdos" value="<%=(request.getParameter("tooltip")==null?"":request.getParameter("tooltip"))%>">
<input type="hidden" name="descAddos" value="<%=(request.getParameter("descAd")==null?"":request.getParameter("descAd"))%>">
<input type="hidden" name="iconodos" value="<%=(request.getParameter("icono")==null?"":request.getParameter("icono"))%>">
<input type="hidden" name="autorizaciondos" value="<%=(request.getParameter("autorizacion")==null?"":request.getParameter("autorizacion"))%>">
<input type="hidden" name="tipoOperaciondos" value="<%=(request.getParameter("tipoOperacion")==null?"":request.getParameter("tipoOperacion"))%>">
<input type="hidden" name="aplicaciondos" value="<%=(request.getParameter("aplicacion")==null?"":request.getParameter("aplicacion"))%>">
<input type="hidden" name="teclaMenudos" value="<%=(request.getParameter("teclaMenu")==null?"":request.getParameter("teclaMenu"))%>">
<input type="hidden" name="pseudocodigo1dos" value="<%=(request.getParameter("pseudocodigo1")==null?"":request.getParameter("pseudocodigo1"))%>">
<input type="hidden" name="pseudocodigo2dos" value="<%=(request.getParameter("pseudocodigo2")==null?"":request.getParameter("pseudocodigo2"))%>">
<input type="hidden" name="atributosdos" value="<%=(request.getParameter("atributos")==null?"":request.getParameter("atributos"))%>">
<input type="hidden" name="codCanaldos" value="<%=(request.getParameter("codCanal")==null?"":request.getParameter("codCanal"))%>">
<input type="hidden" name="tipoOperacionFinaldos" value="<%=(request.getParameter("tipoOperacionFinal")==null?"":request.getParameter("tipoOperacionFinal"))%>">
<input type="hidden" name="parametro1dos" value="<%=(request.getParameter("parametro1")==null?"":request.getParameter("parametro1"))%>">
<input type="hidden" name="parametro2dos" value="<%=(request.getParameter("parametro2")==null?"":request.getParameter("parametro2"))%>">
<input type="hidden" name="parametro3dos" value="<%=(request.getParameter("parametro3")==null?"":request.getParameter("parametro3"))%>">
<input type="hidden" name="accprev" value="<%=(request.getParameter("accprev")==null?"":request.getParameter("accprev"))%>">

<table width="100%"  border="0" cellspacing="8" cellpadding="0">
  <tr>
    <td height="22" class="CabeceraRelacionada2">&nbsp;&nbsp;OPERACIONES :: A&ntilde;adir :: Operaciones relacionadas</td>
  </tr>
  <tr>
    <td><fieldset>
    <legend>B&uacute;squeda</legend>
    <table width="100%"  border="0" cellspacing="8" cellpadding="0">
      <tr>
        <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="1%" nowrap><label>C&oacute;digo&nbsp;&nbsp;&nbsp;</label>
                <br>
                <input name="codigo" type="text" maxlength="8" class="CampoEntrada" size="9" value="<%=session.getValue("busqueda.operacionrel.codigo")!=null?session.getValue("busqueda.operacionrel.codigo"):((request.getParameter("codigoBusc")==null)?"":request.getParameter("codigoBusc"))%>">
                &nbsp;&nbsp;&nbsp;</td>
            <td width="1%" nowrap><label>Nombre&nbsp;&nbsp;&nbsp;</label>
                <br>
                <input name="descripcion" type="text" maxlength="40" class="CampoEntrada" size="20" value="<%=session.getValue("busqueda.operacionrel.descripcion")!=null?session.getValue("busqueda.operacionrel.descripcion"):((request.getParameter("descripcionBusc")==null)?"":request.getParameter("descripcionBusc"))%>">
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
                			//cadena = entornos[i].toString().substring(entornos[i].toString().indexOf("[")+1,entornos[i].toString().indexOf("]"));
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
                  <td><input name="icono" type="text" maxlength="30" class="CampoSalida" size="15" value="<%=session.getValue("busqueda.operacionrel.icono")!= null?session.getValue("busqueda.operacionrel.icono"):((request.getParameter("iconoBusc")==null)?"":request.getParameter("iconoBusc"))%>">&nbsp;</td>
                  <td><img src="images/BotonPrismatico.gif" width="19" height="18" onClick="seleccionarIcono();">&nbsp;&nbsp;&nbsp;</td>
                </tr>
              </table>
            <td width="96%" nowrap><label>Tipo elemen.&nbsp;&nbsp;&nbsp;</label>
                <br>
                <select size="1" name="tipoOperacion" class="CampoEntrada">
                  <option value=" ">Sin tipo</option>
				  <!-- 
				  <!-- ETIQUETA -->
				  <!-- Se ha quitado el acceso a la bd para recuperar los tipos de las operaciones porque ahora solo tienen sentido -->
				  <!-- dos tipos, las finales y las etiquetas -->
				  <option value="F">Operación final</option>
				  <option value="A">Etiqueta</option>                  
              </select>&nbsp;&nbsp;&nbsp;</td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="1%" nowrap><label>Par&aacute;metro 1&nbsp;&nbsp;&nbsp;</label>
                <br>
                <input name="parametro1" type="text" maxlength="200" class="CampoEntrada" size="15" value="<%=session.getValue("busqueda.operacionrel.parametro1")!=null?session.getValue("busqueda.operacionrel.parametro1"):((request.getParameter("parametro1Busc")==null)?"":request.getParameter("parametro1Busc"))%>">&nbsp;&nbsp;&nbsp;</td>
            <td width="1%" nowrap><label>C&oacute;digo autorizaci&oacute;n </label><br>
              <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><input name="autorizacion" type="text" maxlength="8" class="CampoSalida" size="15" value="<%=session.getValue("busqueda.operacionrel.autorizacion")!=null?session.getValue("busqueda.operacionrel.autorizacion"):((request.getParameter("autorizacionBusc")==null)?"":request.getParameter("autorizacionBusc"))%>">&nbsp;</td>
                  <td><img src="images/BotonPrismatico.gif" width="19" height="18" onClick="seleccionarAutorizacion();">&nbsp;&nbsp;&nbsp;</td>
                </tr>
              </table></td>
            <td width="98%" nowrap><label>Fecha modificaci&oacute;n&nbsp;&nbsp;&nbsp;</label>
                <br>
                <input name="fecModificacion" type="text" maxlength="10" class="CampoEntrada" size="20" value="<%=session.getValue("busqueda.operacionrel.fecModificacion")!=null?session.getValue("busqueda.operacionrel.fecModificacion"):((request.getParameter("fecModificacionBusc")==null)?"":request.getParameter("fecModificacionBusc"))%>">&nbsp;&nbsp;&nbsp;</td>
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
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="98%" class="TituloTabla">Listado de operaciones </td>
        <td width="1%" nowrap>
        	<img src="images/Izq1.gif" width="17" height="17" onClick="paginar(1);">
        	<img src="images/Izq2.gif" width="17" height="17" onClick="paginar(<%=(p==null?1:p.getPaginaActual())%>);">
        	<img src="images/Dere2.gif" width="17" height="17" onClick="paginar(<%=(p==null?1:p.getPaginaActual() + 2)%>);">
        	<img src="images/Dere1.gif" width="17" height="17" onClick="paginar(<%=(p==null?1:p.getTotalPaginas())%>);"></td>
        <td width="1%" nowrap>&nbsp;&nbsp;P&aacute;gina:
            <input name="paginaActual" type="text" class="CampoEntradaImporte" value=<%=(p==null?1:(data==null)?0:p.getPaginaActual()+1)%> size="3">
      /
      <input name="paginaTotal" type="text" class="CampoSalidaImporte" value=<%=(p==null?1:p.getTotalPaginas())%> size="3">
&nbsp;
      <input type="button" name="Button22" value="Ir" class="Boton" style="width:40px" onClick="paginar(document.getElementById('paginaActual').value);"></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="2" class="TablaDatos">
      <tr class="CabeceraTabla">
        <td width="1%" valign="top" class="CabeceraTabla">&nbsp;</td>
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
            			out.print("<tr class=\"Pijama" + ind + "\">");
            			out.print("<td width=\"1%\" class=\"TextoTablaN\"><input name=\"check" + i + "\" type= \"checkbox\" onClick=\"seleccion(this.name + ' - " + data[i][0] + "<sep>" + data[i][1] + "<sep>" + data[i][2] + "<sep>" + data[i][3] + "<sep>" + data[i][4] + "<sep>" + data[i][5] + "<sep>" + data[i][6] + "<sep>" + data[i][7] + "')\"></td>");
            			//out.print("<td class=\"TextoTablaN\"><a href=\"operacionDetalle.jsp?accion=MODIFICAR&referrer=operacionesRelacionadas.jsp?accionref=INICIAR&codigo=" + data[i][0] + cadena.toString() + "\" target=\"_self\">" + data[i][0] + "</a></td>");
            			out.print("<td class=\"TextoTablaN\"><a href=\"#\" onClick=\"clickEnlace('" + data[i][0] + "','" + data[i][7] + "')\" target=\"_self\">" + data[i][0] + "</a></td>");
            			out.print("<td class=\"TextoTablaN\">" + (data[i][1]==null?"":data[i][1]) + "</td>");
            			out.print("<td nowrap class=\"TextoTablaN\">" + data[i][2] + " </td>");
            			out.print("<td nowrap class=\"TextoTablaN\"><img src=\"images/" + data[i][3] + ".gif\" width=\"17\" height=\"17\"></td>");
            			out.print("<td nowrap class=\"TextoTablaN\">" + data[i][4] + " </td>");
            			out.print("<td class=\"TextoTablaN\">" + (data[i][5]==null?"":data[i][5]) + "</td>");
            			out.print("<td nowrap class=\"TextoTablaN\">" + (data[i][6]==null?"":data[i][6]) + "</td>");
            			out.print("<td nowrap class=\"TextoTablaN\">" + (data[i][7]==null?"":data[i][7].toString().substring(0,data[i][7].toString().indexOf(" "))) + " </td>");
            			//out.print("<td nowrap class=\"TextoTablaN\">" + atad.defina.pres.Utils.formatTimeStamp((java.sql.Timestamp)data[i][7]) + " </td>");
            			out.print("</tr>");
            		}
            	}
       %>
    </table></td>
  </tr>
  <tr>
  	<%
  		String disabled = null;
  		if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
  			disabled = "disabled=\"true\"";
  		} else {
  			disabled ="";
  		}
  	%>
    <td align="right"><input type="button" name="Button" value="Aceptar" class="Boton" disabled=true onClick="aceptar();" <%=disabled%>>
      <input type="button" name="Button4" value="Cancelar" class="Boton" onClick="cancelar();">
    </td>
  </tr>
</table>
</body>
</html>

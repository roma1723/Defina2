<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="js/defina.js"></script>
<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
<%
	String referrer = request.getRequestURI() + "?" + request.getQueryString();
	boolean p = true;
	String codigo = (request.getParameter("codigoEsc")!=null)?request.getParameter("codigoEsc"):request.getParameter("codigo");
	String nombre = (request.getParameter("nombreEsc")!=null)?request.getParameter("nombreEsc"):request.getParameter("nombre");
	String autorizacion = (request.getParameter("autorizacionEsc")!=null)?request.getParameter("autorizacionEsc"):request.getParameter("autorizacion");
	String icono = (request.getParameter("iconoEsc")!=null)?request.getParameter("iconoEsc"):request.getParameter("icono");
	String orden = (request.getParameter("ordenEsc")!=null)?request.getParameter("ordenEsc"):request.getParameter("orden");
	boolean bExisteMas = false;
	String strNombreMenu = null;
	// Recupera el tipo de perfil de la sesión
	String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();
	String disabled = null;
	if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
		disabled = "disabled=\"true\"";
	} else {
		disabled ="";
	}
%>
<script>
	var filaSel = null;
	var arrayElementos = new Array();
	var numeroElementos = 0;
	
	function deseleccionar(radios,str){
		if (radios != null){
			if (radios.length > 1){
				for (var i=0;i<radios.length;i++){
					if (str != radios[i].value){
						radios[i].checked=false;
					}
				}
			} else {
				if (str != radios.value){
					radios.checked=false;
				}
			}
		}	
	}

	function deseleccionPrev(str){
		// Menús de primer nivel
		var radios = document.all.radiobutton0;
		deseleccionar(radios,str);
		// Menús de cualquier nivel excepto el primero
		radios = document.all.radiobutton;
		deseleccionar(radios,str);
		// Accesos Directos
		radios = document.all.radiobutton7;
		deseleccionar(radios,str);
		// Objetos de negocio
		radios = document.all.radiobutton8;
		deseleccionar(radios,str);
	}

	function seleccion(str) {
		deseleccionPrev(str);
		filaSel = str.split('<sep>');
		// el 0 es el padre y el 1 el código de la operacion
	}
	
	function posicionar(){
		window.location.hash='<%=request.getParameter("opPosicion")%>';
	}

	function ordenar() {
		if (filaSel == null) {
			alert('Debe seleccionar una operación');
		} else {
			location.href = '<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.escenarios.arbol.ordenar.link")%>' + '&padre=' + filaSel[0] + '&referrer=<%=java.net.URLEncoder.encode(referrer)%>';
		}
	}

	function modificar(){
		if (filaSel == null){
			alert("Debe seleccionar un registro")
		} else {
			//location.href='operacionDetalle.jsp?accion=MODIFICAR&referrer=<%=java.net.URLEncoder.encode(referrer)%>&codigo=' + filaSel[1] + '&usarSesion=false';
			location.href='operacionDetalle.jsp?accion=MODIFICAR&referrer=/defina/escenariosArbol.jsp&codigo=' + filaSel[1] + '&usarSesion=false&cod_escenario=<%=codigo%>&nombre_escenario=<%=nombre%>&aut_escenario=<%=autorizacion%>&icono_escenario=<%=icono%>&orden_escenario=<%=orden%>';
		}
	}
	
	
	function esMenu(strCodigoComparacion){
	
		var bEsOperacionFinal = false;
		var iRecorrido = 0;
		var strSeleccion = "";
	
		for(iRecorrido = 0; iRecorrido < arrayElementos.length && !bEsOperacionFinal; iRecorrido++){
			strSeleccion = arrayElementos[iRecorrido].split('<sep>');
			if (strSeleccion[0] == strCodigoComparacion && (strSeleccion[1] == "M" || strSeleccion[1] == "MN")){
				bEsOperacionFinal = true;
			}
		}
	
		return bEsOperacionFinal;
			
	}
	
	function devuelveElemento(strCodigoComparacion){
		
		var strSeleccion = "";
		var strComparador = "";
	
		for(iRecorrido = 0; iRecorrido < arrayElementos.length; iRecorrido++){
			strSeleccion = arrayElementos[iRecorrido].split('<sep>');
			if (strSeleccion[0] == strCodigoComparacion){
				strComparador = arrayElementos[iRecorrido].split('<sep>');
			}
		}

		return strComparador;

	}
	function colgar(tipo,codigo){
		if (filaSel != null){
			if (!esMenu(filaSel[1])){
				alert("Sólo se pueden colgar elementos en Menús. [" + filaSel[1] + "] no es un Menú.");
				return;
			}
		}
		var rc = null;
		if(tipo=="NM")
		{
			rc = mostrarDialogoOperacionesFueraMenu();
		}else{		
			rc = mostrarDialogoOperaciones();
		}
		if (rc != null) {
			// Posición 4 es el tipo de operación
			
			var referrer = '<%=request.getRequestURI() + "?" + request.getQueryString()%>';
			var cod="";
			if (codigo != null && codigo.length>0){
				cod = tipo+codigo.substring(1);
			} else {
				//cod=filaSel[0];
				cod=filaSel[1];
			}
			if(cod==("MN"+codigo.substring(1,codigo.length)) && "F"==rc[4]){
				alert("Una operación final no puede colgarse como menú de primer nivel.");
			} else {
			
				if (filaSel != null){
					var strSeleccionado = devuelveElemento(filaSel[1]);
					if (strSeleccionado[1] == "MN")
						cod = cod;
					else
						cod=filaSel[1];
				}
				<%=atad.defina.pres.ProcesadorAjax.getJavascript(18, new String[][] {{"0", "cod"}, {"1", "rc[0]"}, {"2", "referrer"}})%>;
			}
		}
	}
	
	function mostrarDialogoOperacionesFueraMenu() {
		
		// Obligamos a recargar la página para que no la coja de caché.
		var miFecha = new Date();
		var cadenaPasada = 'operacion.jsp?opfinal=true&dialog=true&newHour=' + miFecha.getTime();
		
		return window.showModalDialog(cadenaPasada,'','dialogWidth:900px;dialogHeight:690px');
	}

	function descolgar(tipo,codigo){
		if (filaSel == null){
			alert("Debe seleccionar un registro")
		} else if (filaSel[0] == '<%=codigo%>') {
			alert('No puede descolgarse una operación de primer nivel');
		} else {
			var c = confirm('Se va a descolgar la operacion ' + filaSel[1] + ' del padre ' + filaSel[0] + '. ¿Desea continuar?');
			if (c) {
				var referrer = '<%=request.getRequestURI() + "?" + request.getQueryString()%>';
				<%=atad.defina.pres.ProcesadorAjax.getJavascript(19, new String[][] {{"0", "filaSel[0]"}, {"1", "filaSel[1]"}, {"2", "referrer"}})%>
			}
		}
	}
</script>

</head>
<body onload="posicionar()";>
<script> window.setTimeout("alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';", <%=""+request.getSession().getMaxInactiveInterval()*1000%>);</script>
<form name="formulario" action="">
<table width="100%"  border="0" align="left" cellpadding="0" cellspacing="8">
  <tr>
    <td height="22" class="CabeceraRelacionada2">&nbsp;&nbsp;ESCENARIOS :: Ver &aacute;rbol</td>
  </tr>
<%
  	// Genera el título para el escenario
  	out.print("<tr>");
  	out.print("<td class=\"TextoTablaCen\"><strong><img src=\"images/" + icono + ".gif\">[" + codigo + "] &nbsp:&nbsp;" + nombre + "</strong></td>");
  	out.print("</tr>");
%>
  <tr>
    <td class="TituloTabla">Men&uacute;s de primer nivel:</td>
  </tr>
  <tr>
    <td class="TituloTabla">
      <table width="100%" border="0" cellpadding="0" cellspacing="2" class="TablaDatos">
        <tr>
          <td width="1%" nowrap class="CabeceraTabla">&nbsp;</td>
          <td width="87%" nowrap class="CabeceraTabla">Operaciones</td>
          <td width="5%" nowrap class="CabeceraTabla">Entorno</td>
          <td width="7%" nowrap class="CabeceraTabla">C&oacute;digo autorizaci&oacute;n </td>
        </tr>
        <%
        	// Genera los menús de primer nivel para el escenarios
        	atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
		Object[] dat = new Object[]{codigo,session.getValue("canal")};
		Object[][] menusPrimoNivel = dao.getMenusPrimerNivel(dat);

		String mnpadre = "MN" + codigo.toString().substring(1,codigo.toString().length());
		if (menusPrimoNivel != null && menusPrimoNivel.length > 0){
			// Incluye la fila del escenario
			out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
			out.print("<td class=\"TextoTablaN\"><input name=\"radiobutton0\" type=\"radio\" value=\"" + mnpadre + "<sep>" + codigo + "\" onClick=\"seleccion(this.value);\"></td>");
			out.print("<script>arrayElementos[numeroElementos++] = \"" + codigo + "\" + \"<sep>\" + \"" + "MN" + "\";</script>");
			out.print("<td class=\"TextoTablaN\"><span class=\"TextoTablaN\"><img src=\"images/" + icono + ".gif\">[" + codigo + "] " + nombre + "</span></td>");
			out.print("<td class=\"TextoTablaN\">&nbsp;</td>");
   		out.print("<td class=\"TextoTablaN\">&nbsp;</td>");
 			out.print("</tr>");
 			// Incluye las filas de los menús de primer nivel
 			for (int i=0;i<menusPrimoNivel.length;i++){
 				out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
 				out.print("<td width=\"1%\" class=\"TextoTablaN\"><input name=\"radiobutton0\" type=\"radio\" value=\"" + mnpadre + "<sep>" + menusPrimoNivel[i][0] + "\" onClick=\"seleccion(this.value);\"></td>");
 				out.print("<td width=\"87%\" class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/organigrama.gif\" border=\"0\">[" + menusPrimoNivel[i][0] + "] " + menusPrimoNivel[i][1] + "</td>");
 				out.print("<td width=\"5%\" class=\"TextoTablaN\">" + menusPrimoNivel[i][3] + " </td>");
 				out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (menusPrimoNivel[i][4]==null?"":menusPrimoNivel[i][4].toString()) + " </td>");
 				out.print("</tr>");
 			}
		}
        %>
      </table>
    </td>
  </tr>
  <tr>
	<td align="right">
		<input type="button" name="Button1" value="Modificar" class="Boton" onclick="modificar();">
		<input type="button" name="Button2" value="Ordenar" class="Boton" onclick="ordenar();">
		<input type="button" name="Button3" value="Colgar" class="Boton" onclick="colgar('MN','<%=codigo%>');" <%=disabled%>>
		<input type="button" name="Button4" value="Descolgar" class="Boton" onclick="descolgar('MN','<%=codigo%>');" <%=disabled%>>
	</td>
  </tr>      
  <%
  	
  	// Construye las tablas para cada menú
  	for (int i=0;i<menusPrimoNivel.length;i++){
  		out.print("<tr>");
  		out.print("<td class=\"TituloTabla\">");
  		out.print("Men&uacute; " + (i+1) + ":");
  		out.print("</td>");
  		out.print("</tr>");
  		out.print("<tr>");
  		out.print("<td>");
  		// Busca los datos para cada menú

			Object[] dat2 = new Object[]{menusPrimoNivel[i][0],session.getValue("canal")};
			Object[][] menusCualquierNivel = dao.getMenusCualquierNivel(dat2);
	
			// Siempre pinta la tabla
			out.print("<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"2\" class=\"TablaDatos\">");
			out.print("<tr>");
			out.print("<td width=\"1%\" nowrap class=\"CabeceraTabla\">&nbsp;</td>");
      out.print("<td width=\"87%\" nowrap class=\"CabeceraTabla\">Operaciones</td>");
      out.print("<td width=\"5%\" nowrap class=\"CabeceraTabla\">Entorno</td>");
      out.print("<td width=\"7%\" nowrap class=\"CabeceraTabla\">C&oacute;digo autorizaci&oacute;n </td>");
      out.print("</tr>");
			out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
      out.print("<a name=\"" + menusPrimoNivel[i][0] + "\"><td width=\"1%\" class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"" + menusPrimoNivel[i][0] + "<sep>" + menusPrimoNivel[i][0] + "\" onClick=\"seleccion(this.value);\"></td></a>");
      out.print("<script>arrayElementos[numeroElementos++] = \"" + menusPrimoNivel[i][0] + "\" + \"<sep>\" + \"" + "M" + "\";</script>");
			out.print("<td width=\"87%\"  class=\"TextoTablaN\"><img src=\"images/organigrama.gif\" border=\"0\">[" + menusPrimoNivel[i][0]+ "] " + menusPrimoNivel[i][1] + "</td>");
			out.print("<td width=\"5%\" class=\"TextoTablaN\">" + menusPrimoNivel[i][3] + " </td>");
      out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (menusPrimoNivel[i][4]==null?"":menusPrimoNivel[i][4].toString()) + " </td>");
      out.print("</tr>");
			
			
			if (menusCualquierNivel != null && menusCualquierNivel.length > 0){
				// Recorre los datos obtenidos, generando la fila correspondiente de la tabla
				String cadIndex=null;
				for (int j=0;j<menusCualquierNivel.length;j++){
					// Antes de empezar, comprueba si este menú, tiene hijos
	
					Object[] dat3 = new Object[]{menusCualquierNivel[j][0],session.getValue("canal")};
					Object[][] menusNivel = dao.getMenusCualquierNivel(dat3);
	
					out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
				  out.print("<td width=\"1%\"  class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"" + menusPrimoNivel[i][0] + "<sep>" + menusCualquierNivel[j][0]+ "\" onClick=\"seleccion(this.value);\"></td>");			        
				  out.print("<script>arrayElementos[numeroElementos++] = \"" + menusCualquierNivel[j][0] + "\" + \"<sep>\" + \"" + menusCualquierNivel[j][5] + "\";</script>");
				  if ((j+1) < 10){
				   	cadIndex = "0" + (j+1);
				  } else {
				   	cadIndex = "" + (j+1);
				  }
				  out.print("<a name=\"" + menusCualquierNivel[j][0] + "\"><td width=\"87%\"  class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/" + ((menusNivel.length==0)?"operacion":"organigrama") + ".gif\" width=\"17\" height=\"17\"> " + cadIndex + "[" + menusCualquierNivel[j][0] + "] " + menusCualquierNivel[j][1] + "</td></a>");
				  out.print("<td width=\"5%\" class=\"TextoTablaN\">" + menusCualquierNivel[j][3] + " </td>");
				  out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (menusCualquierNivel[j][4]==null?"":menusCualquierNivel[j][4].toString()) + " </td>");
				  out.print("</tr>");
				  if (menusNivel != null && menusNivel.length > 0){
				   	// Genera las filas para los últimos menús hijos
				   	for (int k=0;k<menusNivel.length;k++){
				   		// Recupera las operaciones de un nivel inferior (4o nivel)
 							Object[][] opsCuat = dao.getMenusCualquierNivel(new Object[]{menusNivel[k][0],session.getValue("canal")});
				   		out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
					    out.print("<a name=\"" +  menusNivel[k][0] + "\"><td width=\"1%\"  class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"" + menusCualquierNivel[j][0] + "<sep>" +  menusNivel[k][0] + "\" onClick=\"seleccion(this.value);\"></td></a>");
						out.print("<script>arrayElementos[numeroElementos++] = \"" + menusNivel[k][0] + "\" + \"<sep>\" + \"" + menusNivel[k][5] + "\";</script>");
					    if ((k+1) < 10){
					    	cadIndex="0" + (k+1);
					    } else {
					    	cadIndex="" + (k+1);
					    }
					    out.print("<td width=\"87%\"  class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/" + ((opsCuat.length==0)?"operacion":"organigrama") + ".gif\" width=\"17\" height=\"17\"> " + cadIndex + " [" + menusNivel[k][0] + "] " + menusNivel[k][1] + "</td>");
					    out.print("<td width=\"5%\" class=\"TextoTablaN\">" + menusNivel[k][3] + " </td>");
					    out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (menusNivel[k][4]==null?"":menusNivel[k][4].toString()) + " </td>");
					    out.print("</tr>");
					    if (opsCuat != null){
								for(int l=0;l<opsCuat.length;l++){
									// Recupera las operaciones de un nivel inferior (5o nivel)
 									Object[][] opsCinc = dao.getMenusCualquierNivel(new Object[]{opsCuat[l][0],session.getValue("canal")});
									out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
	     							out.print("<a name=\"" + opsCuat[l][0] + "\"><td class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"" + menusNivel[k][0] + "<sep>" + opsCuat[l][0] + "\" onClick=\"seleccion(this.value);\"</td></a>");
	     							out.print("<script>arrayElementos[numeroElementos++] = \"" + opsCuat[l][0] + "\" + \"<sep>\" + \"" + opsCuat[l][5] + "\";</script>");
				     				if ((l+1) < 10){
		      							orden = "0" + (l+1);
	     							} else {
		     							orden = "" + (l+1);
		      						}
				     			out.print("<td class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/" + ((opsCinc.length==0)?"operacion":"organigrama") + ".gif\" border=\"0\">" + orden + "[" + opsCuat[l][0] + "]  " + opsCuat[l][1] + "</td>");
				     			out.print("<td class=\"TextoTablaN\">" + opsCuat[l][3] + " </td>");
	    						out.print("<td class=\"TextoTablaN\">" + (opsCuat[l][4]!=null?opsCuat[l][4]:"") + " </td>");
	     						out.print("</tr>");
	     						if (opsCinc != null){
										for(int m=0;m<opsCinc.length;m++){
											out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
			     							out.print("<a name=\"" + opsCinc[m][0] + "\"><td class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"" + opsCuat[l][0] + "<sep>" + opsCinc[m][0] + "\" onClick=\"seleccion(this.value);\"></td></a>");
			     							out.print("<script>arrayElementos[numeroElementos++] = \"" + opsCinc[m][0] + "\" + \"<sep>\" + \"" + opsCinc[m][5] + "\";</script>");
						     				if ((m+1) < 10){
					      						orden = "0" + (m+1);
			     							} else {
				     							orden = "" + (m+1);
				      						}
						     				out.print("<td class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/operacion.gif\" border=\"0\">" + orden + "[" + opsCinc[m][0] + "]  " + opsCinc[m][1] + "</td>");
						     				out.print("<td class=\"TextoTablaN\">" + opsCinc[m][3] + " </td>");
			    							out.print("<td class=\"TextoTablaN\">" + (opsCinc[m][4]!=null?opsCinc[m][4]:"") + " </td>");
			     							out.print("</tr>");					
	      									Object[][] opsSix = dao.getMenusCualquierNivel(new Object[]{opsCinc[m][0],session.getValue("canal")});
	      									if (!bExisteMas) strNombreMenu = "\\n[" + opsCinc[m][0] + "]: " + opsCinc[m][1];
	      									if (opsSix != null && opsSix.length > 0) bExisteMas = true;
										}
									}	
								}
							}
				   		}
				  	}
				}
			}
			out.print("</table>");
	  	out.print("</td>");
	  	out.print("</tr>");
	  	// Genera la botonera para esta tabla
			out.println("  <tr>");
			out.println("	<td align=\"right\">");
			out.println("		<input type=\"button\" name=\"Button1\" value=\"Modificar\" class=\"Boton\" onclick=\"modificar();\">");
			out.println("		<input type=\"button\" name=\"Button2\" value=\"Ordenar\" class=\"Boton\" onclick=\"ordenar();\">");
			out.println("		<input type=\"button\" name=\"Button3\" value=\"Colgar\" class=\"Boton\" onclick=\"colgar('','');\"" + disabled + " >");
			out.println("		<input type=\"button\" name=\"Button4\" value=\"Descolgar\" class=\"Boton\" onclick=\"descolgar('','');\"" + disabled + " >");
			out.println("	</td>");
			out.println("  </tr>");

  	}
  %>
  <tr>
    <td class="TituloTabla">Accesos directos </td>
  </tr>
    <tr>
      <td>
        <table width="100%" border="0" cellpadding="0" cellspacing="2" class="TablaDatos">  
        <tr>
          <td width="1%" nowrap class="CabeceraTabla">&nbsp;</td>
          <td width="97%" nowrap class="CabeceraTabla">Operaciones</td>
          <td nowrap class="CabeceraTabla">Entorno</td>
          <td nowrap class="CabeceraTabla">C&oacute;digo autorizaci&oacute;n </td>
        </tr>
        <%
        	// Genera los accesos directos

					Object[] dat4 = new Object[]{codigo,session.getValue("canal")};
					Object[][] accesosDirectos = dao.getAccesosDirectos(dat4);
			
					String adpadre = "AD" + codigo.toString().substring(1,codigo.toString().length());
					if (accesosDirectos != null && accesosDirectos.length>0){
						for (int i=0;i<accesosDirectos.length;i++){
										out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
						        out.print("<a name=\"" + accesosDirectos[i][0] + "\"><td width=\"1%\"  class=\"TextoTablaN\"><input name=\"radiobutton7\" type=\"radio\" value=\"" + adpadre + "<sep>" + accesosDirectos[i][0] + "\" onClick=\"seleccion(this.value);\"></td></a>");
						        out.print("<td width=\"87%\"  class=\"TextoTablaN\"> <img src=\"images/" + accesosDirectos[i][2] + ".gif\" width=\"17\" height=\"17\">[" + accesosDirectos[i][0] + "] " + accesosDirectos[i][1] + "</td>");
						        out.print("<td width=\"5%\" class=\"TextoTablaN\">" + accesosDirectos[i][3] + " </td>");
						        out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (accesosDirectos[i][4]!=null?accesosDirectos[i][4]:"") + "</td>");
						        out.print("</tr>");
						}
					}
        %>
        </table>
      </td>
    </tr>
    <tr>
      <td align="right">
        <input type="button" name="Button1" value="Modificar" class="Boton" onclick="modificar();">
        <input type="button" name="Button2" value="Ordenar" class="Boton" onclick="ordenar();">
        <input type="button" name="Button3" value="Colgar" class="Boton" onclick="colgar('AD','<%=codigo%>');" <%=disabled%>>
        <input type="button" name="Button4" value="Descolgar" class="Boton" onclick="descolgar('AD','<%=codigo%>');" <%=disabled%>>
      </td>
    </tr>
    <tr>
      <td class="TituloTabla">Objetos de negocio </td>
    </tr>
    <tr>
      <td>
        <table width="100%" border="0" cellpadding="0" cellspacing="2" class="TablaDatos">
          <tr>
            <td width="1%" nowrap class="CabeceraTabla">&nbsp;</td>
            <td width="97%" nowrap class="CabeceraTabla">Operaciones</td>
            <td nowrap class="CabeceraTabla">Entorno</td>
            <td nowrap class="CabeceraTabla">C&oacute;digo autorizaci&oacute;n </td>
          </tr>
          <%
          	// Recupera los objetos de negocio
		Object[] dat5 = new Object[]{codigo,session.getValue("canal")};
		Object[][] objetosNegocio = dao.getObjetosNegocio(dat5);

		String onpadre = "ON" + codigo.toString().substring(1,codigo.toString().length());
		if (objetosNegocio != null && objetosNegocio.length > 0){
			for(int i=0;i<objetosNegocio.length;i++){
				out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
			        out.print("<a name=\"" + objetosNegocio[i][0] + "\"><td width=\"1%\"  class=\"TextoTablaN\"><input name=\"radiobutton8\" type=\"radio\" value=\"" + onpadre + "<sep>" + objetosNegocio[i][0] + "\" onClick=\"seleccion(this.value);\"></td></a>");
			        out.print("<td width=\"87%\"  class=\"TextoTablaN\">[" + objetosNegocio[i][0] + "] " + objetosNegocio[i][1] + "</td>");
			        out.print("<td width=\"5%\" class=\"TextoTablaN\">" + objetosNegocio[i][3] + "</td>");
			        out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (objetosNegocio[i][4]!=null?objetosNegocio[i][4]:"") + "</td>");
			        out.print("</tr>");
			}
		}
          %>
        </table>
      </td>
    </tr>
    <tr>
      <td class="TituloTabla">Operaci&oacute;n Home: 
      	<%
      		if (dao != null){
      			Object[][] opHome = dao.getOpHomeEscenario(codigo);
      			if(opHome != null && opHome.length > 0){
      				out.print(" " + (opHome[0][0]!=null?opHome[0][0]:""));
      			}
      		}
      	%>
      </td>
    </tr>
    <tr>
      <td align="right"><input type="button" name="Button1" value="Modificar" class="Boton" onclick="modificar();">
        <input type="button" name="Button2" value="Ordenar" class="Boton" onclick="ordenar();">
        <input type="button" name="Button3" value="Colgar" class="Boton" onclick="colgar('ON','<%=codigo%>');" <%=disabled%>>
        <input type="button" name="Button4" value="Descolgar" class="Boton" onclick="descolgar('ON','<%=codigo%>');" <%=disabled%>></td>
    </tr>
    <tr>
    <td class="TituloTabla">Operaciones Fuera de Menú </td>
  </tr>
    <tr>
      <td>
        <table width="100%" border="0" cellpadding="0" cellspacing="2" class="TablaDatos">  
        <tr>
          <td width="1%" nowrap class="CabeceraTabla">&nbsp;</td>
          <td width="97%" nowrap class="CabeceraTabla">Operaciones</td>
          <td nowrap class="CabeceraTabla">Entorno</td>
          <td nowrap class="CabeceraTabla">C&oacute;digo autorizaci&oacute;n </td>
        </tr>
        <%
        	// Genera las operaciones fuera de menu ligadas a escenarios

					Object[] dat6 = new Object[]{codigo,session.getValue("canal")};
					Object[][] opFueraMenu = dao.getOperacionesFueraMenuEscenario(dat6);
			
					String nmpadre = "NM" + codigo.toString().substring(1,codigo.toString().length());
					if (opFueraMenu != null && opFueraMenu.length>0){
						for (int i=0;i<opFueraMenu.length;i++){
										out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
						        out.print("<a name=\"" + opFueraMenu[i][0] + "\"><td width=\"1%\"  class=\"TextoTablaN\"><input name=\"radiobutton7\" type=\"radio\" value=\"" + nmpadre + "<sep>" + opFueraMenu[i][0] + "\" onClick=\"seleccion(this.value);\"></td></a>");
						        out.print("<td width=\"87%\"  class=\"TextoTablaN\"> <img src=\"images/" + opFueraMenu[i][2] + ".gif\" width=\"17\" height=\"17\">[" + opFueraMenu[i][0] + "] " + opFueraMenu[i][1] + "</td>");
						        out.print("<td width=\"5%\" class=\"TextoTablaN\">" + opFueraMenu[i][3] + " </td>");
						        out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (opFueraMenu[i][4]!=null?opFueraMenu[i][4]:"") + "</td>");
						        out.print("</tr>");
						}
					}
        %>
        </table>
      </td>
    </tr>
    <tr>
      <td align="right">
        <input type="button" name="Button1" value="Modificar" class="Boton" onclick="modificar();">
        <input type="button" name="Button2" value="Ordenar" class="Boton" onclick="ordenar();">
        <input type="button" name="Button3" value="Colgar" class="Boton" onclick="colgar('NM','<%=codigo%>');" <%=disabled%>>
        <input type="button" name="Button4" value="Descolgar" class="Boton" onclick="descolgar('NM','<%=codigo%>');" <%=disabled%>>
      </td>
    </tr>
    <tr>
      <td align="right" class="Global">&nbsp;</td>
    </tr>        
    <tr>
      <td align="right">      
        <input type="button" name="Button2" value="Cerrar" class="Boton" onclick="history.back()">
      </td>
    </tr>
</table>
</form>
<%
	if (bExisteMas)	out.println("<script>alert('De la operacion" + strNombreMenu + "\\ncuelgan menus de 5º nivel o superior que no se mostraran.\\nPueden existir otras operaciones de las cuales cuelguen menus de 5º nivel o superior.');</script>");
%>
</body>
</html>
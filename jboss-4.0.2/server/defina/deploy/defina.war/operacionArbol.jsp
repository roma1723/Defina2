<%! atad.defina.datos.AccesoDatosBase dao = null;Object codOperacion=null;%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="js/defina.js"></script>
<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>

<%
	String referrer = request.getRequestURI() + "?" + request.getQueryString();
	codOperacion = request.getParameter("operacion");
	boolean p = true;
	// Recupera el tipo de perfil de la sesión
	String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();
	String disabled = null;
  if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
  	disabled = "disabled=\"true\"";
  } else {
  	disabled ="";
  }
%>

<script language="JavaScript">
var filaSel = null;

var arrayElementos = new Array();
var numeroElementos = 0;
function seleccionar(str){
	filaSel = str.split("<sep>");
}

function cargaOps(){
	location.href='operacion.jsp?accion=BUSCAR' + generaCadenaCancelar();
}

function generaCadenaCancelar(){
	var aut="";
	var tmp = '&codigoBusc=' + document.getElementById("codigoBusc").value;
	tmp = tmp + '&descripcionBusc=' + document.getElementById("descripcionBusc").value;
	tmp = tmp + '&entornoBusc=' + document.getElementById("entornoBusc").value;
	tmp = tmp + '&iconoBusc=' + document.getElementById("iconoBusc").value;
	if (document.getElementById("autorizacionBusc").value != "null"){
		aut = document.getElementById("autorizacionBusc").value;
	}
	tmp = tmp + '&autorizacionBusc=' + aut;
	tmp = tmp + '&tipoOperacionBusc=' + document.getElementById("tipoOperacionBusc").value;
	tmp = tmp + '&parametro1Busc=' + document.getElementById("parametro1Busc").value;
	tmp = tmp + '&fecModificacionBusc=' + document.getElementById("fecModificacionBusc").value;
	tmp = tmp + '&tipoOperacionFinalBusc=' + document.getElementById("tipoOperacionFinalBusc").value;
	return tmp;
}

function modificar(){
	if (filaSel == null){
		alert("Debe seleccionar un registro")
	} else {
		location.href='operacionDetalle.jsp?accion=MODIFICAR&referrer=<%=java.net.URLEncoder.encode(referrer)%>&codigo=' + filaSel[0];
	}
}

function ordenar(){
	if (filaSel == null) {
		alert('Debe seleccionar una operación');
	} else {
		var padre = (filaSel.length == 1?filaSel[0]:filaSel[4]);
		location.href = '<%=(String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.operaciones.arbol.ordenar.link")%>' + '&padre=' + padre + '&referrer=<%=java.net.URLEncoder.encode(referrer)%>';
	}
}

	// BEGIN DEFINA
	function esOpcionColgable(strCodigoComparacion){
	
		var bEsOpcionColgable = false;
		var iRecorrido = 0;
		var strSeleccion = "";
	
		for(iRecorrido = 0; iRecorrido < arrayElementos.length && !bEsOpcionColgable; iRecorrido++){
			strSeleccion = arrayElementos[iRecorrido].split('<sep>');
			if (strSeleccion[0] == strCodigoComparacion && (strSeleccion[1] == "M" || strSeleccion[1] == "MN")){
				bEsOpcionColgable = true;
			}
		}
	
		return bEsOpcionColgable;
			
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

	// END DEFINA
function colgar(){
	// BEGIN DEFINA
	if (filaSel != null){
		if (!esOpcionColgable(filaSel[0])){
			alert("Sólo se pueden colgar elementos en Menús. [" + filaSel[1] + "] no es un Menú.");
			return;
		}
	}
	// END DEFINA
	var rc = mostrarDialogoOperaciones();
	if (filaSel == null){
		alert("Debe seleccionar la operación a colgar");
	} else {
		var padre = (filaSel.length == 1?filaSel[0]:filaSel[0]);
		var referrer = '<%=referrer%>';
		if (rc != null && padre != null) {
			var op = rc[0];
			<%=atad.defina.pres.ProcesadorAjax.getJavascript(18, new String[][] {{"0", "padre"}, {"1", "op"}, {"2", "referrer"}})%>
		}
	}
}

function descolgar(){
	if (filaSel == null){
		alert("Debe seleccionar un registro")
	} else if (filaSel[0] == '<%=codOperacion%>') {
		alert('No puede descolgarse una operación de primer nivel');
	} else {
		var padre = (filaSel.length == 1?filaSel[0]:filaSel[4]);
		var c = confirm('Se va a descolgar la operacion ' + filaSel[0] + ' del padre ' + padre + '. ¿Desea continuar?');
		if (c) {
			var referrer = '<%=request.getRequestURI() + "?" + request.getQueryString()%>';
			if (filaSel[0] != null && padre != null) {
				<%=atad.defina.pres.ProcesadorAjax.getJavascript(19, new String[][] {{"0", "padre"}, {"1", "filaSel[0]"}, {"2", "referrer"}})%>
			}
		}
	}
}

</script>
</head>
<%
    boolean estaEnMisAccesos=false;
	boolean estaEnFueraMenu=false;
	Object [][] esc = null;
	Object [][] data = null;
	Object [][] misAcc = null;
	Object [][] opFueraMenu = null;
	dao = atad.defina.datos.AccesoDatosBase.getInstance(session);

	Object accion = request.getParameter("accion");
	if (accion != null){
		if ("CONSULTAR".equals(accion)){
			// Obtiene los escenarios que la contienen
			dao.liberarStb();
			esc = dao.getEscenariosImpacto(codOperacion,session.getValue("entorno"),session.getValue("canal"));
			//Ahora comprobamos si la opercaion se encuentra en el bloque de Mis Accesos
			misAcc=dao.existeOperacionEnMisAccesos(codOperacion,session.getValue("entorno"),session.getValue("canal"));
			if (misAcc != null && misAcc.length > 0 )
			{
//				System.out.println("Mis accesos?="+misAcc[0][1].toString());
				estaEnMisAccesos=true;
			}else{
				estaEnMisAccesos=false;
			}
			//Ahora comprobamos si la operacion se encuentra en el bloque de Operaciones Fuera de Menu
			opFueraMenu=dao.existeOperacionEnFueraMenu(codOperacion,session.getValue("entorno"),session.getValue("canal"));
			if (opFueraMenu != null && opFueraMenu.length > 0 )
			{
				estaEnFueraMenu=true;
			}
		}
	}
%>
<body>
<script> window.setTimeout("alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';", <%=""+request.getSession().getMaxInactiveInterval()*1000%>);</script>
<table width="100%"  border="0" cellspacing="8" cellpadding="0">
  <tr>
    <td height="22" class="CabeceraRelacionada2">&nbsp;&nbsp;OPERACIONES :: Modificar :: Ver &aacute;rbol </td>
  </tr>
  <tr>
    <td><fieldset>
    <legend>Escenarios que la utilizan</legend>
    <table width="100%"  border="0" cellspacing="8" cellpadding="0">
    <%
    	if (esc != null && esc.length==1 && 
				esc[0][0].toString().indexOf("Se ha detectado un error en la definición del menú en que está involucrada la operación") != -1){
					out.print("<tr>");
    			out.print("<td><b>");
					out.print(esc[0][0].toString());
					out.print("</b></tr>");
    			out.print("</td>");
					esc= null;
			} else if (esc != null && esc.length > 0){
    		// Recupera la información para crear el enlace con el árbol de escenarios (nombre, autorización, icono y orden
    		java.util.HashMap infoEsc = (java.util.HashMap)dao.getInfoEscenario(esc);
    		for (int i=0;i<esc.length;i++){
    			out.print("<tr>");
    			out.print("<td>");
    			Object[][] info = (Object[][])infoEsc.get(esc[i][0]);
    			if (info != null && info.length > 0){
    				out.print("<a href=\"escenariosArbol.jsp?codigo=");
    				out.print(esc[i][0]);
    				out.print("&nombre=");
    				out.print(esc[i][1]);
    				out.print("&autorizacion=");
    				out.print(info[0][2]);
    				out.print("&icono=");
    				out.print(info[0][1]);
    				out.print("&orden=");
    				out.print(info[0][3]);
    				out.print("&opPosicion=");
    				out.print(codOperacion);
    				out.print("&referrer=");
    				out.print(java.net.URLEncoder.encode(referrer));	
    				out.print("\">[");
    			} else {
						out.print("<a href=\"operacionDetalle.jsp?accion=MODIFICAR&referrer=");
						out.print(java.net.URLEncoder.encode(referrer));
						out.print("&codigo=");
						out.print(esc[i][0]);
					  out.print("\">[");
					}
					out.print(esc[i][0]);
					out.print("] ");
					out.print(esc[i][1]);
					out.print("</a></td>");
    			    out.print("</tr>");
    		 }
			}
    		%>
           
    </table>
    </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
    <legend>Presente en Mis Accesos</legend>
    <table width="100%"  border="0" cellspacing="8" cellpadding="0">
    <tr><td>
    
    <%    if(estaEnMisAccesos){
    %>
    		   <input type="checkbox" name="check1" disabled=true checked> Si		
       		   <input type="checkbox" name="check2"disabled=true> No
     <%
         }else{
    %>
    		   <input type="checkbox" name="check1" disabled> Si		
       		   <input type="checkbox" name="check2" disabled checked> No
    <%}%>   
    </td></tr>
    </table>
    </fieldset></td>
  </tr>
  <tr>
    <td><fieldset>
    <legend>Presente en Operaciones Fuera de Menú</legend>
    <table width="100%"  border="0" cellspacing="8" cellpadding="0">
    <tr><td>
    
    <%    if(estaEnFueraMenu){
    %>
    		   <input type="checkbox" name="check3" disabled checked> Si		
       		   <input type="checkbox" name="check4"disabled> No
     <%
         }else{
    %>
    		   <input type="checkbox" name="check3" disabled> Si		
       		   <input type="checkbox" name="check4" disabled checked> No
    <%}%>   
    </td></tr>
    </table>
    </fieldset></td>
  </tr>  
  <tr>
    <td class="TituloTabla">&Aacute;rbol de la operaci&oacute;n </td>
  </tr>
  <tr>
    <td align="right"><table width="100%" border="0" cellpadding="0" cellspacing="2" class="TablaDatos">
      <tr>
        <td width="1%" nowrap class="CabeceraTabla">&nbsp;</td>
        <td width="97%" nowrap class="CabeceraTabla">Operaciones</td>
        <td width="1%" nowrap class="CabeceraTabla">Entorno</td>
        <td width="1%" nowrap class="CabeceraTabla">C&oacute;digo autorizaci&oacute;n </td>
      </tr>
      <tr class="Pijama<%=((p=!p)?"1":"2")%>">
        <td width="1%" class="TextoTablaN"><input name="radiobutton" type="radio" value="radiobutton" onClick="seleccionar('<%=codOperacion%>');"></td>
       <%
		out.print("<script>arrayElementos[numeroElementos++] = \"" + codOperacion + "\" + \"<sep>\" + \"" + "MN" + "\";</script>");
       Object[][] primoNivel = dao.getOpPrimerNivel(codOperacion,session.getValue("entorno"),session.getValue("canal"));
       boolean bExisteMas = false;
       String strNombreMenu = null;
       %>
        <td width="97%"  class="TextoTablaN"><img src="images/<%=(primoNivel.length==0)?"operacion.gif":"organigrama.gif"%>" border="0">[<%=request.getParameter("operacion")%>]  <%=request.getParameter("nombre")%> </td>
        <td width="1%" class="TextoTablaN"><%=request.getParameter("entorno")%> </td>
        <td width="1%" class="TextoTablaN"><%=(!request.getParameter("autorizacion").equals("null")?request.getParameter("autorizacion"):"")%> </td>
      </tr>
            <%
      	// Genera la tabla con el árbol de la operación
      	if (primoNivel != null && primoNivel.length > 0){
      		String orden = null;
      		for (int i=0;i<primoNivel.length;i++){
      			// Recupera las operaciones de un nivel inferior
      			Object[][] ops = dao.getMenusCualquierNivel(new Object[]{primoNivel[i][0],session.getValue("canal")});
      			out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
      			out.print("<td class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"radiobutton\" onClick=\"seleccionar('" + primoNivel[i][0] + "<sep>" + primoNivel[i][1] + "<sep>" + primoNivel[i][2] + "<sep>" + primoNivel[i][3] + "<sep>" + codOperacion + "')\"></td>");
      			out.print("<script>arrayElementos[numeroElementos++] = \"" + primoNivel[i][0] + "\" + \"<sep>\" + \"" + primoNivel[i][4] + "\";</script>");
      			
      			
      			if ((i+1) < 10){
      				orden = "0" + (i+1);
      			} else {
      				orden = "" + (i+1);
      			}
      			out.print("<td class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/" +  (ops.length == 0?"operacion.gif":"organigrama.gif") + "\" border=\"0\">" + orden + "[" + primoNivel[i][0] + "]  " + primoNivel[i][1] + "</td>");
      			out.print("<td class=\"TextoTablaN\">" + primoNivel[i][2] + " </td>");
      			out.print("<td class=\"TextoTablaN\">" + (primoNivel[i][3]!=null?primoNivel[i][3]:"") + " </td>");
      			out.print("</tr>");
      			if (ops != null){
      				for (int j=0;j<ops.length;j++){
      					// Recupera las operaciones de un nivel inferior
      					Object[][] opsCuatr = dao.getMenusCualquierNivel(new Object[]{ops[j][0],session.getValue("canal")});
      					out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
		      			out.print("<td class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"radiobutton\" onClick=\"seleccionar('" + ops[j][0] + "<sep>" + ops[j][1] + "<sep>" + ops[j][2] + "<sep>" + ops[j][3] + "<sep>" + primoNivel[i][0] + "')\"></td>");
						out.print("<script>arrayElementos[numeroElementos++] = \"" + ops[j][0] + "\" + \"<sep>\" + \"" + ops[j][5] + "\";</script>");
		      			if ((j+1) < 10){
		      				orden = "0" + (j+1);
		      			} else {
		      				orden = "" + (j+1);
		      			}
		      			out.print("<td class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/" +  (opsCuatr.length == 0?"operacion.gif":"organigrama.gif") + "\" border=\"0\">" + orden + "[" + ops[j][0] + "]  " + ops[j][1] + "</td>");
		      			out.print("<td class=\"TextoTablaN\">" + ops[j][3] + " </td>");
		      			out.print("<td class=\"TextoTablaN\">" + (ops[j][4]!=null?ops[j][4]:"") + " </td>");
		      			out.print("</tr>");		
		      			if (opsCuatr != null && opsCuatr.length > 0){
		      				// Genera las filas para los siguientes menús
		      				for (int k=0;k<opsCuatr.length;k++){
		      					Object[][] opsCinc = dao.getMenusCualquierNivel(new Object[]{opsCuatr[k][0],session.getValue("canal")});
				      			out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
				      			out.print("<td class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"radiobutton\" onClick=\"seleccionar('" + opsCuatr[k][0] + "<sep>" + opsCuatr[k][1] + "<sep>" + opsCuatr[k][2] + "<sep>" + opsCuatr[k][3] + "<sep>" + ops[j][0] + "')\"></td>");
				      			out.print("<script>arrayElementos[numeroElementos++] = \"" + opsCuatr[k][0] + "\" + \"<sep>\" + \"" + opsCuatr[k][5] + "\";</script>");
				      			if ((k+1) < 10){
				      				orden = "0" + (k+1);
				      			} else {
				      				orden = "" + (k+1);
				      			}
				      			out.print("<td class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/" +  (opsCinc.length == 0?"operacion.gif":"organigrama.gif") + "\" border=\"0\">" + orden + "[" + opsCuatr[k][0] + "]  " + opsCuatr[k][1] + "</td>");
				      			out.print("<td class=\"TextoTablaN\">" + opsCuatr[k][3] + " </td>");
				      			out.print("<td class=\"TextoTablaN\">" + (opsCuatr[k][4]!=null?opsCuatr[k][4]:"") + " </td>");
				      			out.print("</tr>");		
						      	if (opsCinc != null && opsCinc.length > 0){
				      				// Genera las filas para los siguientes menús
				      				for (int l=0;l<opsCinc.length;l++){
						      			out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
						      			out.print("<td class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"radiobutton\" onClick=\"seleccionar('" + opsCinc[l][0] + "<sep>" + opsCinc[l][1] + "<sep>" + opsCinc[l][2] + "<sep>" + opsCinc[l][3] + "<sep>" + opsCuatr[k][0] + "')\"></td>");
						      			out.print("<script>arrayElementos[numeroElementos++] = \"" + opsCinc[l][0] + "\" + \"<sep>\" + \"" + opsCinc[l][5] + "\";</script>");
						      			if ((l+1) < 10){
						      				orden = "0" + (l+1);
						      			} else {
						      				orden = "" + (l+1);
						      			}
						      			out.print("<td class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/operacion.gif\" border=\"0\">" + orden + "[" + opsCinc[l][0] + "]  " + opsCinc[l][1] + "</td>");
						      			out.print("<td class=\"TextoTablaN\">" + opsCinc[l][3] + " </td>");
						      			out.print("<td class=\"TextoTablaN\">" + (opsCinc[l][4]!=null?opsCinc[l][4]:"") + " </td>");
						      			out.print("</tr>");		
		      							Object[][] opsSix = dao.getMenusCualquierNivel(new Object[]{opsCinc[l][0],session.getValue("canal")});
		      							if (!bExisteMas) strNombreMenu = "\\n[" + opsCinc[l][0] + "]: " + opsCinc[l][1];
		      							if (opsSix != null && opsSix.length > 0) bExisteMas = true;
				      				}
				      			}
		      				}
		      			}
      				}
      			}
      		}
      	}
      %>
    </table></td>
  </tr>
  <tr>
    <td align="right"><input type="button" name="Button22" value="Modificar" class="Boton" onclick="modificar();">
      <input type="button" name="Button222" value="Ordenar" class="Boton" onclick="ordenar();" <%=disabled%>>
      <input type="button" name="Button2222" value="Colgar" class="Boton" onclick="colgar();" <%=disabled%>>
      <input type="button" name="Button222222" value="Descolgar" class="Boton" onclick="descolgar()" <%=disabled%>></td>
  </tr>
  <tr>
    <td align="right" class="Global">&nbsp;</td>
  </tr>
  <tr>
    <td align="right"><input type="button" name="Button223" value="Cerrar" class="Boton" onclick="cargaOps();"></td>
  </tr>
</table>
<input type="hidden" name="codigoBusc" value="<%=(request.getParameter("codigoBusc")==null?"":request.getParameter("codigoBusc"))%>">
<input type="hidden" name="fecModificacionBusc" value="<%=(request.getParameter("fecModificacionBusc")==null?"":request.getParameter("fecModificacionBusc"))%>">	
<input type="hidden" name="descripcionBusc" value="<%=(request.getParameter("descripcionBusc")==null?"":request.getParameter("descripcionBusc"))%>">		
<input type="hidden" name="entornoBusc" value="<%=(request.getParameter("entornoBusc")==null?"":request.getParameter("entornoBusc"))%>">	
<input type="hidden" name="iconoBusc" value="<%=(request.getParameter("iconoBusc")==null?"":request.getParameter("iconoBusc"))%>">	
<input type="hidden" name="autorizacionBusc" value="<%=(request.getParameter("autorizacionBusc")==null?"":request.getParameter("autorizacionBusc"))%>">	
<input type="hidden" name="tipoOperacionBusc" value="<%=(request.getParameter("tipoOperacionBusc")==null?"":request.getParameter("tipoOperacionBusc"))%>">	
<input type="hidden" name="parametro1Busc" value="<%=(request.getParameter("parametro1Busc")==null?"":request.getParameter("parametro1Busc"))%>">						
<input type="hidden" name="tipoOperacionFinalBusc" value="<%=(request.getParameter("tipoOperacionFinalBusc")==null?"":request.getParameter("tipoOperacionFinalBusc"))%>">		
<%
if (bExisteMas)	out.println("<script>alert('De la operacion" + strNombreMenu + "\\ncuelgan menus de 5º nivel o superior que no se mostraran.\\nPueden existir otras operaciones de las cuales cuelguen menus de 5º nivel o superior.');</script>");
%>
</body>
</html>

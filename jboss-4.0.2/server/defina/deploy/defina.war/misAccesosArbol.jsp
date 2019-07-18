<%! atad.defina.datos.AccesoDatosBase dao = null;Object codOperacion=null; Object codOperacionLigera=null;%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="js/defina.js"></script>
<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>

<%
	dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
	String referrer = request.getRequestURI() + "?" + request.getQueryString();
	codOperacion = "MISACC";
	codOperacionLigera = "MISACCL";
	boolean isPesado = true;
	String can = session.getValue("canal").toString();
	System.out.println("JDBC :: Canal es " + can);
	if ("Ligeros".equals(can)){
		isPesado = false;
	}
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
	}else if(esOpcionColgable(filaSel[0])){//Si intenta modificar la operación Mis accessos
		alert("No se puede modificar la operacion Mis accesos.");
	} else {
		location.href='operacionDetalle.jsp?accion=MODIFICAR&referrer=<%=java.net.URLEncoder.encode(referrer)%>&codigo=' + filaSel[0] + '&usarSesion=false';
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

	if (strCodigoComparacion == "MISACC" || strCodigoComparacion == "MISACCL"){
		bEsOpcionColgable = true;
	}

	return bEsOpcionColgable;
}


	// END DEFINA
function colgar(){
	// BEGIN DEFINA
	if (filaSel != null){
		if (!esOpcionColgable(filaSel[0])){
			alert("Sólo se pueden colgar elementos en 'Mis Accesos'.");
			return;
		}
	}
	// END DEFINA
	var rc = mostrarDialogoMisAccesos();
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

function mostrarDialogoMisAccesos() {
	
	// Obligamos a recargar la página para que no la coja de caché.
	var miFecha = new Date();
	var cadenaPasada = 'operacion.jsp?opfinal=true&dialog=true&newHour=' + miFecha.getTime();
	
	return window.showModalDialog(cadenaPasada,'','dialogWidth:900px;dialogHeight:690px');
}

function descolgar(){
	if (filaSel == null){
		alert("Debe seleccionar un registro")
	} else if (filaSel[0] == '<%=(isPesado)?codOperacion:codOperacionLigera%>') {
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
<body>
<script> window.setTimeout("alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';", <%=""+request.getSession().getMaxInactiveInterval()*1000%>);</script>
<table width="100%"  border="0" cellspacing="8" cellpadding="0">
  <tr>
    <td height="22" class="CabeceraRelacionada2">&nbsp;&nbsp;MIS ACCESOS :: Modificar :: Ver &aacute;rbol </td>
  </tr>
  <tr>
    <td class="TituloTabla">&Aacute;rbol de Mis Accesos </td>
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
        <td width="1%" class="TextoTablaN"><input name="radiobutton" type="radio" value="radiobutton" onClick="seleccionar('<%=((isPesado)?codOperacion:codOperacionLigera)%>');"></td>
       <%
       String codOpMA = null;
       if (isPesado){
       	codOpMA = "MISACC";
       } else {
       	codOpMA = "MISACCL";
       }
			 out.print("<script>arrayElementos[numeroElementos++] = \"" + codOpMA + "\" + \"<sep>\" + \"" + codOpMA + "\";</script>");
       Object[][] primoNivel = dao.getOpPrimerNivel((isPesado)?codOperacion:codOperacionLigera,session.getValue("entorno"),session.getValue("canal"));
       String strNombreMenu = null;
       %>
        <td width="97%"  class="TextoTablaN"><img src="images/organigrama.gif" border="0">[<%=(isPesado)?codOperacion:codOperacionLigera%>]  Mis Accesos </td>
        <td width="1%" class="TextoTablaN"><%=session.getValue("entorno").toString()%> </td>
        <td width="1%" class="TextoTablaN"><%=((request.getParameter("autorizacion")!=null && !request.getParameter("autorizacion").equals("null"))?request.getParameter("autorizacion"):"")%> </td>
      </tr>
            <%
      	// Genera la tabla con el árbol de la operación
      	if (primoNivel != null && primoNivel.length > 0){
      		String orden = null;
      		for (int i=0;i<primoNivel.length;i++){
      			out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
      			out.print("<td class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"radiobutton\" onClick=\"seleccionar('" + primoNivel[i][0] + "<sep>" + primoNivel[i][1] + "<sep>" + primoNivel[i][2] + "<sep>" + primoNivel[i][3] + "<sep>" + codOpMA + "')\"></td>");
      			out.print("<script>arrayElementos[numeroElementos++] = \"" + primoNivel[i][0] + "\" + \"<sep>\" + \"" + primoNivel[i][4] + "\";</script>");
      			if ((i+1) < 10){
      				orden = "0" + (i+1);
      			} else {
      				orden = "" + (i+1);
      			}
      			out.print("<td class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/operacion.gif" + "\" border=\"0\">" + orden + "[" + primoNivel[i][0] + "]  " + primoNivel[i][1] + "</td>");
      			out.print("<td class=\"TextoTablaN\">" + primoNivel[i][2] + " </td>");
      			out.print("<td class=\"TextoTablaN\">" + (primoNivel[i][3]!=null?primoNivel[i][3]:"") + " </td>");
      			out.print("</tr>");
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
</body>
</html>

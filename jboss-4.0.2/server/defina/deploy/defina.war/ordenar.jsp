<%
	// Recupera el tipo de perfil de la sesión
	String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();
	String disabled = null;
	if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
		disabled = "disabled=\"true\"";
	} else {
		disabled ="";
	}
%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="js/defina.js"></script>
<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
<script language="JavaScript">

	function aceptar() {
		<%
        	atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
		Object canal = session.getValue("canal");
		Object tipoOper = request.getParameter("tipoOper");
		Object padre = request.getParameter("padre");
		if ("E".equals(tipoOper)) {
			if ("Pesados".equals(canal.toString())) {
				padre = "ESCRITOR";
			} else if ("Ligeros".equals(canal.toString())) {
				padre = "PORTAL";
			}
		}

		Object [][] operaciones = dao.getTablaOrdenacion(session.getValue("entorno"), canal, tipoOper, padre);

		for(int i=0; i<operaciones.length; i++) {
			out.print("var var");
			out.print(i);
			out.print(" = document.getElementById(\"");
			out.print("var"+i);
			out.println("\");");
			out.println("if (var" + i + ".value == '') {");
			out.println("alert('Por favor, introduzca el orden para la operación \"" + operaciones[i][1] + "\"');");
			out.println("var" + i + ".focus();");
			out.println("return;");
			out.println("}");
		}		
		%>

		// todos los campos estan informados
		// se procede al envio al servidor
		<%
		String [][] msg = new String[(operaciones.length*2 + 2)][2];
		int j = 0;
		for (int i=0; i<msg.length - 2; i++) {
			msg[i][0] = "" + i;
			if (i%2==0) {
				msg[i][1] = "'" + operaciones[j][0] + "'";
			} else {
				msg[i][1] = "var" + j + ".value";
				j++;
			}
		}
		String referrer = request.getParameter("referrer");
		if (referrer == null) referrer = "";
		msg[msg.length - 1][0] = "" + (msg.length - 1);
		msg[msg.length - 1][1] = "'" + padre + "'";
		msg[msg.length - 2][0] = "" + (msg.length - 2);
		msg[msg.length - 2][1] = "'" + referrer + "'";
		out.println(atad.defina.pres.ProcesadorAjax.getJavascript("ORDENAR", true, false, msg));
		%>
	}


	function cerrar() {
		history.go(-1);
	}
</script>
</head>
<body>
<script> window.setTimeout("alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';", <%=""+request.getSession().getMaxInactiveInterval()*1000%>);</script>
<form name="formulario" action="" onSubmit="valida(this);">
<table width="100%"  border="0" align="left" cellpadding="0" cellspacing="8">
  <tr>
    <td height="22" class="CabeceraRelacionada2">&nbsp;&nbsp;<%=(String)request.getParameter("titulo")%></td>
  </tr>
  <tr><td>
    <table width="100%" border="0" cellpadding="0" cellspacing="8">
      <tr>
      	<%
      		Object entorno = session.getValue("canal");
		out.print("<td width=\"97%\" class=\"TextoTablaN\"><img src=\"images/organigrama.gif\" border=\"0\"><strong> Escritorio de " + ("Pesados".equals(entorno.toString())?"oficinas":"canal ligero") + " - " + padre + "</strong></td>");
        %>
        </tr>
        <%
		// Sube los datos ordenados a la sesión
		if (operaciones != null && operaciones.length> 0) {
			for (int i=0;i<operaciones.length;i++){
				out.print("<tr>");
				out.print("<td width=\"97%\" class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\">");
				// El nombre del campo de texto incluye el índice de la posición en el array que contiene la información
				out.print("<a title=\"Editar el Escenario " + operaciones[i][1] + "\" onclick=\"estableceDatos('" + operaciones[i][0] + "','" + operaciones[i][1] + "','" + operaciones[i][2] + "','" + operaciones[i][3] + "','" + operaciones[i][4] + "','escenariosMod.jsp','CONSULTAR');\" href=\"#\"><img border=\"0\" src=\"images/" + operaciones[i][2] + ".gif\" width=\"17\" height=\"17\"></a><input name=\"var" + i + "\" type=\"text\" class=\"CampoEntradaImporte\" value=\"" + operaciones[i][3] + "\" size=\"3\">");
				out.print("<strong>  [" + operaciones[i][0] + "] </strong> " + operaciones[i][1]);
				out.print("</td>");
				out.print("</tr>");
			}
		}
        %>
    </table>
	</td></tr>

  <tr>
    <td align="right">
    <input type="button" name="button1" value="Aceptar" class="Boton" onclick="aceptar();" <%=disabled%>/>
    <input type="button" name="button2" value="Cerrar" class="Boton" onclick="cerrar();"/>
    </td>
  </tr>
</table>
</form>
</body>
</html>

<html>
	<head>
		<title>Untitled Document</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
	<script language="JavaScript">
		function volver(){
			location.href='validar.jsp';
		}
	</script>			
  </head>

			<body>
<script> window.setTimeout("alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';", <%=""+request.getSession().getMaxInactiveInterval()*1000%>);</script>
				<table width="100%"  border="0" cellspacing="8" cellpadding="0">
					<tr>
						<td height="22" class="CabeceraRelacionada2">&nbsp;&nbsp;VALIDAR</td>
					</tr>
					<tr>
						<td class="TituloTabla">Operaciones que NO cumplen la validaci&oacuten</td>
					</tr>

<%
	atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
	String query = (String) request.getParameter("query");
	String medio = atad.defina.pres.Utils.getMedio(session);
	if (query != null) {
		Object [][] data = dao.validarQuery(query);
		String descripcion = "";
		String gravedad = "";
		String tabla = "";
		if (data != null) {
			descripcion = (String) data[0][0];
			gravedad = (String) data[0][1];
			tabla = (String)data[0][2];
		}

%>

					<tr>
						<td class="dataTD"><b>Validacion: </b><%=descripcion%></td> 
					</tr>

					<tr>
						<td class="dataTD"><b>Gravedad: </b><%=gravedad%></td> 
					</tr>

					<tr>
						<td>
							<table width="100%"  border="0" cellspacing="2" cellpadding="0" class="TablaDatos">
								<tr class="CabeceraTabla">
									<td width="2%" nowrap class="CabeceraTabla">C&oacutedigo de operaci&oacuten</td>
									<td width="48%" class="CabeceraTabla">Descripci&oacuten del error</td>
								</tr>


<%
		data = dao.validarDetalle(tabla, medio);
		for (int i=0; i<data.length; i++) {
			String estilo = "Pijama";
			if (i%2==0) {
				estilo += "1";
			} else {
				estilo +="2";
			}
			out.println("<tr class=\"" + estilo + "\">");
			out.println("<td class=\"TextoTablaN\">");
			out.println(data[i][0]);
			out.println("</td>");
			out.println("<td class=\"TextoTablaN\" width=\"48%\"> ");

			if (data[i][2] != null) {
				//String referrer = request.getRequestURI() + "?" + request.getQueryString();
				String referrer = "detalle_validar.jsp";
				String cod = "";
				if ("6".equals(query)){
					out.println("<a href='#'>");
				} else{
					if(data[i][1].toString().indexOf("no existe") == -1){
						out.println("<a href='operacionDetalle.jsp?accion=MODIFICAR&referrer=" + referrer + "&codigo=" + data[i][0] + "&query=" + query + "&usarSesion=false'>");
					} else {
						out.println("<a href='#'>");
					}
				}
			}
			out.println("<span class=\"TextoTablaN\">");
			out.println(data[i][1]);
			out.println("</span></a>");
			out.println("</td></tr>");
		}
	}
%>

						</table>
					</td>
					</tr>
					<tr>
						<td align="right">
							<%
							String requester = request.getParameter("requester");
							if (requester == null) requester = "history.back";
							else requester = "window.navigate('" + requester + "')";
							%>
							<input type="button" name="Button2" value="Volver" class="Boton" onclick="volver();">
						</td>
					</tr>
				</table>
</body>
</html>

<html>
	<head>
		<title>Untitled Document</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
                                                </head>

			<body>
			<script> window.setTimeout("alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';", <%=""+request.getSession().getMaxInactiveInterval()*1000%>);</script>
				<table width="100%"  border="0" cellspacing="8" cellpadding="0">
					<tr>
						<td height="22" class="CabeceraRelacionada2">&nbsp;&nbsp;VALIDAR</td>
					</tr>
										<tr>
										  <td class="TituloTabla">Listado de  validaciones</td>
				  </tr>
										<tr>
										  <td><table width="100%"  border="0" cellspacing="2" cellpadding="0" class="TablaDatos">
                                            <tr class="CabeceraTabla">
                                              <td width="2%" nowrap class="CabeceraTabla">Error en la validaci&oacute;n </td>
                                              <td width="48%" class="CabeceraTabla">Validaci&oacute;n</td>
                                              <td width="48%" class="CabeceraTabla">Gravedad</td>
                                            </tr>


<%
	atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
	Object [][] data = dao.validar();
	//String requester = request.getRequestURI() + "?" + request.getQueryString();
	String requester = "validar.jsp";
	for (int i=0; i<data.length; i++) {
		String estilo = "Pijama";
		if (i%2==0) {
			estilo += "1";
		} else {
			estilo +="2";
		}
		out.println("<tr class=\"" + estilo + "\">");
		out.println("<td class=\"TextoTablaN\">");
		if (data[i][3] == null) {
			out.println("No");
		} else {
			out.println("S&iacute");
		}
		out.println("</td>");
		out.println("<td class=\"TextoTablaN\" width=\"48%\"> ");

		if (data[i][3] != null) {
			out.println("<a href=\"detalle_validar.jsp?requester=" + requester + "&query=" + data[i][0] + "\">");
		}
		out.println("<span class=\"TextoTablaN\">");
			
		out.println(data[i][1]);
		out.println("</span></a></td>");
		out.println("<td class=\"TextoTablaN\" width=\"48%\">");
		out.println(data[i][2]);
		out.println("</td></tr>");
	}
%>

                                         </table></td>
				  </tr>
									</table>
</body>
</html>

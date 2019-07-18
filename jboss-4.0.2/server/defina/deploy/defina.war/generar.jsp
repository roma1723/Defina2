<%
// Recupera el tipo de perfil de la sesión
String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();
%>
<html>
	<head>
		<title>Untitled Document</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
		<script src="js/defina.js"></script>
	</head>
	<script language="JavaScript">
		function seleccionTodo(value) {
			for (i=1; i<6; i++) {
				document.getElementById('cb' + i).checked=value;
			}
			// Si se trata de canal pesado, se deselecciona la checkbox del fichero entorno aplicacioninstancia.cfg
			<%
				if ("Pesados".equals(session.getValue("canal"))){
			%>
				document.getElementById('cb5').checked=false;
			<%
				}
			%>
		}

		function generaFicheros(){
			var numFicherosGenerar = 0;
			var cadena = '';
			var tmp;
			for (i=1; i<6; i++) {
				tmp = document.getElementById('cb' + i);
				if (tmp.checked) {
					var valor = tmp.value
					if(i==3){
						if (document.getElementById('cbNPG').checked){
							valor = valor+"NPG";
						}
					}
					numFicherosGenerar++;
					cadena += valor + "*";
				}
			}
			// Añade el entorno a la cadena
			cadena = cadena + "#" + document.getElementById("entornoGeneracion").value;
			if (numFicherosGenerar == 0){
				alert('Debe seleccionar ficheros a generar');
			}else if (document.getElementById("entornoGeneracion").value == 0){
				alert('Debe seleccionar un entorno para generar los ficheros');
			} else {
				var usr = document.getElementById('usuario').value;
				var generarAplicas = document.getElementById('cb1').checked;
				
				if (generarAplicas && usr == 0) {
					alert('Debe seleccionar un usuario');
				} else {
					<%
						String[][] msg=null;
						// Obtiene la ruta de configuración
						String ruta = atad.defina.entorno.Configuracion.getCfg("cfg.ficheros.directorio").toString();
						%>
						var ruta ='<%=ruta%>';
						<%
						msg = new String[][]{{"0","cadena"},{"1","usr"},{"2","ruta"}};
						out.println(atad.defina.pres.ProcesadorAjax.getJavascript("GENERAR",true,false,msg));
					%>
				}
			}
		}
	</script>

	<body>
<script> window.setTimeout("alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';", <%=""+request.getSession().getMaxInactiveInterval()*1000%>);</script>
		<table width="100%"  border="0" cellspacing="5" cellpadding="0">
			<tr>
				<td height="22" class="CabeceraRelacionada2">&nbsp;&nbsp;GENERAR</td>
			</tr>
								<tr>
								  <td class="TituloTabla">Ficheros</td>
		  </tr>
		  					<tr>
		  						<td class="TextoTablaN">Entorno de generación&nbsp;
		  							<select name="entornoGeneracion" class="CampoEntrada" style="width:180px">
		  								<option value=0 selected>Seleccione entorno</option>
		  								<%
		  									Object[] entornos = (Object[])session.getValue("entornos");
								      	if (entornos != null && entornos.length > 0){
								      		for (int i=0;i<entornos.length;i++){
								      			out.print("<option value=\"" + entornos[i].toString().substring(0,1) + "\"" + (entornos[i].toString().substring(0,1).equals(request.getParameter("entorno"))?"selected":"") + ">" + entornos[i] + "</option>");
								      		}
								      	}
								      %>
		  					  </td>
		  </tr>
								<tr>
								  <td class="TextoTablaN"><table width="100%"  border="0" cellspacing="2" cellpadding="0" class="TablaDatos">
			    <tr class="CabeceraTabla">
				<td valign="top" class="CabeceraTabla">
					<input type=checkbox  name="ac" onclick="seleccionTodo(this.checked)">
				</td>
			      <td  class="CabeceraTabla">Descripci&oacute;n del fichero</td>
			      <td  class="CabeceraTabla">Enlace para descarga del fichero</td>
			      <td nowrap  class="CabeceraTabla">&Uacuteltima generaci&oacute;n </td>
			    </tr>


				<tr class="Pijama1">
					<td valign="top">
						<input type=checkbox class="TextoTabla" name="cb1" value='aplicas.dat*aplicas.asc'" >
					</td>
					<td class="TextoTablaN">
						<b>APLICAS.ASC (ASCII) y APLICAS.DAT (EBCDIC)</b><br>(se guarda en <b>UCM</b> HOST para ser procesado por el activador)
						<select name="usuario" class="CampoEntrada">
							<option value="0">--- Seleccione un usuario ---</option>
							<%// Recupera los usuarios de la base de datos
							Object[][] data = null;
							atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstanceVersiones(session);
							data = dao.getUsuarios();
							String ultimoUsuario = (String) session.getAttribute("ficheros.usuario");
							if (data != null && data.length > 0){
								for (int i=0;i<data.length;i++){
									out.print("<option value=\"" + data[i][0] + "\"" + (data[i][0].toString().equals(ultimoUsuario)?" selected":"") + ">[" + data[i][0] + "] " + data[i][2] + " " + data[i][3] + ", " + data[i][1] + "</option>");
								}
							}
							%>
						</select>
					</td>
					<%Object ts = session.getAttribute("ficheros.aplicas.dat.timestamp");%>
                                        <td class="TextoTablaN">
						<span class="TextoTablaN">aplicas.dat</span>
						<br><span class="TextoTablaN">aplicas.asc</span>
					</td>
					<td nowrap class="TextoTablaN"><%=(ts==null?"no generado":ts)%></td>
				</tr>

				<tr class="Pijama2">
					<td valign="top">
						<input type=checkbox class="TextoTabla" name="cb2" value='pseudocodigos.properties'" >
					</td>
					<td class="TextoTablaN">
						<b>PSEUDOCODIGOS.properties</b><br>que contiene los c&oacute;digos para arrancar las operaciones desde el campo operaci&oacute;n
					</td>
					<%ts = session.getAttribute("ficheros.pseudocodigos.properties.timestamp");%>
                                        <td class="TextoTablaN">
						<span class="TextoTablaN">pseudocodigos.properties</span>
					</td>
					<td nowrap class="TextoTablaN"><%=(ts==null?"no generado":ts)%></td>
				</tr>

				<tr class="Pijama1">
					<td valign="top">
						<input type=checkbox class="TextoTabla" name="cb3" value='escritorio.xml'" >
					</td>
					<td class="TextoTablaN">
						<b>ESCRITORIO.XML</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=checkbox class="TextoTabla" name="cbNPG">Entorno NPG<br>que utiliza el escritorio NACAR para su configuraci&oacute;n
					</td>
						<%ts = session.getAttribute("ficheros.escritorio.xml.timestamp");%>
                                        <td class="TextoTablaN">
						<span class="TextoTablaN">escritorio.xml</span>
					</td>
					<td nowrap class="TextoTablaN"><%=(ts==null?"no generado":ts)%></td>
				</tr>

				<tr class="Pijama2">
					<td valign="top">
						<input type=checkbox class="TextoTabla" name="cb4" value='AtaeOperacionesEscritorio.properties'" >
					</td>
					<td class="TextoTablaN">
						<b>AtaeOperacionesEscritorio.properties</b><br>que contiene los flujos o programas que arranca cada c&oacute;digo de operaci&oacute;n o cada objeto de negocio
					</td>
					<%ts = session.getAttribute("ficheros.AtaeOperacionesEscritorio.properties.timestamp");%>
                                        <td class="TextoTablaN">
						<span class="TextoTablaN">ataeoperacionesescritorio.properties</span>
					</td>
					<td nowrap class="TextoTablaN"><%=(ts==null?"no generado":ts)%></td>
				</tr>

				<tr class="Pijama1">
					<%String ent = atad.defina.pres.Utils.getEntornoGenerar(session);%>
					<td valign="top">
						<input type=checkbox class="TextoTabla" name="cb5" value='<%=ent%>aplicacioninstancia.cfg'" <%=("Pesados".equals(session.getValue("canal")))?"disabled":"enabled"%>>
					</td>

					<td class="TextoTablaN">
						<b><%=ent%>aplicacioninstancia.cfg</b><br>para los distintos entornos que contienen para cada operaci&oacute;n la instancia WAS en la que se ejecuta
					</td>
					<%ts = session.getAttribute("ficheros." + ent + "aplicacioninstancia.cfg.timestamp");%>

                                        <td class="TextoTablaN">
						<span class="TextoTablaN"><%=ent%>aplicacioninstancia.cfg</span>
					</td>
					<td nowrap valign=center class="TextoTablaN"><%=(ts==null?"no generado":ts)%></td>
				</tr>

			</table>
			
			
			</td>
				  </tr>
										<tr>
										  <td class="TextoTablaN"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                              <td width="100%"></td>
                                              <%
                                              	String disabled = null;
																					  		if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
																					  			disabled = "disabled=\"true\"";
																					  		} else {
																					  			disabled ="";
																					  		}
                                              %>
                                              <td width="1%" nowrap><input type="button" class="Boton" value="Generar ficheros" onClick="generaFicheros()" name="submit2" <%=disabled%>></td>
					      <td>&nbsp;</td>
                                              <td width="1%" nowrap><input type="button" class="Boton" value="Ayuda" onClick="mostrarAyuda('generar');" name="bt1"></td>
                                            </tr>
                                          </table></td>
				  </tr>
										<tr>
										<td class="TextoTablaN">&nbsp;										  </td>
										</tr>
									</table>
</body>
							</html>

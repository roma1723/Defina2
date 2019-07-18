<%atad.defina.datos.AccesoDatosBase dao = null;String escDefecto = null; int[] nMenu = new int[6];String[] sMenu = new String[6];String[] sMenuPadre = new String[6];String nombreEscenario=null;int numVez=0;String htmlObjetoNegocio = null;%>
<html>
<script language="JavaScript" src="./js/HM_Config.js"></script>
<script language="JavaScript">
<%
	if(request.getParameter("CodEscenario")!=null){
		escDefecto = request.getParameter("CodEscenario").toString();
	}
	// Recupera todos los escenarios
	dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
	Object[][] oonn = null;
	Object[][] esc = dao.getEscenariosSimulacion(session.getValue("canal"));
	java.util.HashMap sMenuAD = new java.util.HashMap(9);
	String s = "HM_Array1 = [ [,,,,,,,,,,,,,,,,,,,1,1],";
	if (esc != null && esc.length > 0){
		if (escDefecto==null){
			escDefecto = esc[0][0].toString();
		}
		for (int i=0;i<esc.length;i++){
			s = s +  "[\"<img border=0 src='images/"  + esc[i][2] + ".gif" + "' width='17' height='17'>&nbsp;" + esc[i][1] + "\",\"simulacion.jsp?CodEscenario=" + esc[i][0] + "&CodEntorno=" + session.getValue("entorno") + "\",1,0,0],";
		}
		s = s.substring(0,s.length()-1) + "]";
		out.println(s);
		// Recupera los Objetos de Negocio para el escenario seleccionado
		oonn = dao.getOONNEscenario(escDefecto);
		if (oonn != null && oonn.length > 0){
			htmlObjetoNegocio = "<td width=" + oonn[0][4] + " class=\"EscSeparador\"><a href=\"operacionDetalle.jsp?accion=MODIFICAR&referrer=simulacion.jsp&codigo=" + oonn[0][1] + "\" target=\"_self\">";
			if (oonn[0][3] != null){
				htmlObjetoNegocio = htmlObjetoNegocio+ "<img border=\"0\" src=\"images/" + oonn[0][3].toString().toUpperCase() + ".gif\" alt=\"Objeto " + oonn[0][2] + "\"></a></td>";
			} else {
				htmlObjetoNegocio = htmlObjetoNegocio + "</a></td>";
			}
			htmlObjetoNegocio = htmlObjetoNegocio + "<td width=" + oonn[0][7] + " class=\"EscSeparador\"><a href=\"operacionDetalle.jsp?accion=MODIFICAR&referrer=simulacion.jsp&codigo=" + oonn[0][4] + "\" target=\"_self\">";
			if (oonn[0][6] != null){
				htmlObjetoNegocio = htmlObjetoNegocio + "<img border=\"0\" src=\"images/" + oonn[0][6].toString().toUpperCase() + ".gif\" alt=\"Objeto " + oonn[0][5] + "\"></a></td>";
			} else {
				htmlObjetoNegocio = htmlObjetoNegocio + "</a></td>";
			}
			htmlObjetoNegocio = htmlObjetoNegocio + "<td width=" + oonn[0][10] + " class=\"EscSeparador\"><a href=\"operacionDetalle.jsp?accion=MODIFICAR&referre=simulacion.jsp&codigo=" + oonn[0][7] + "\" target=\"_self\">";
			if (oonn[0][9] != null){
				htmlObjetoNegocio = htmlObjetoNegocio + "<img border=\"0\" src=\"images/" + oonn[0][9].toString().toUpperCase() + ".gif\" alt=\"Objeto " + oonn[0][8] + "\"></a></td>";
			} else {
				htmlObjetoNegocio = htmlObjetoNegocio + "</a></td>";
			}
			nombreEscenario = oonn[0][0].toString();
		} else {
			out.print("No hay OONNs");
		}
		// Recupera los menús para el escenario
		Object[][] menus = dao.getMenusEscenario(escDefecto,session.getValue("entorno"));
		if (menus != null && menus.length > 0){
			// Inicializa contadores y strings
			for (int i=0;i<6;i++){
				nMenu[i]=0;
				sMenu[i]="";
				sMenuPadre[i]="zzzzzzzzzzz";
			}
			// El primer elemento lo inicializa a 1 porque ya se había pintado el escenario por defecto
			nMenu[1]=1;
			int contador=0;
			String codMenu=null;String descripcion=null; int tieneHijos=0; int nivel=-1; String menuPadre=null;
			for (int i=0;i<menus.length;i++){
				codMenu=menus[i][0].toString();
				descripcion=(menus[i][3]==null?"":menus[i][3].toString());
				tieneHijos=Integer.parseInt(menus[i][4].toString());
				nivel=Integer.parseInt(menus[i][1].toString());
				menuPadre=(menus[i][2]==null?"":menus[i][2].toString());
				if (nivel >= 6){
					// Ignora el menú 
					continue;
				} else {
					// Incrementa el contador primero
					nMenu[nivel]++;
					// Pone a 0 los restantes
					for (int j=(nivel+1);j<6;j++){
						nMenu[j]=0;
					}
					// Trata el primer nivel de forma especial
					if (nivel==1){
						contador++;
						if (tieneHijos!=0){
							if ("AD".equals(menuPadre.substring(0,2))){
								sMenuAD.put(codMenu,"onmouseover=\"popUp('elMenu" + nMenu[1] + "',event)\" onmouseout=\"popDown('elMenu" + nMenu[1] + "')\"");
							} else {
					  			sMenu[1] = sMenu[1] +
  			 						"<a onmouseover=\"popUp('elMenu" + nMenu[1] + "',event)\" onmouseout=\"popDown('elMenu" + nMenu[1] + "')\" href=\"operacionDetalle.jsp?referrer=javascript:history.back()&accion=MODIFICAR&codigo=" + codMenu + "\" class=\"EscriFont\">" + descripcion + "</a>&nbsp;&nbsp;&nbsp;";
							}
						} else {
							if (!"AD".equals(menuPadre.substring(0,2))){
				 	  			sMenu[1] = sMenu[1] +
	  								"<a href=\"operacionDetalle.jsp?referrer=javascript:history.back()&accion=MODIFICAR&codigo=" + codMenu + "\" class=\"EscriFont\">" + descripcion + "</a>&nbsp;&nbsp;&nbsp;";
							}
						}
					} else if (nivel > 1){
						if (contador > 0){
							// Trata menús de nivel 2 en adelante
							if(!menuPadre.equals(sMenuPadre[nivel].toString())){
								String nombreArray = "HM_Array";
								for (int k=0;k<nivel-1;k++){
									nombreArray = nombreArray + nMenu[k+1] + "_";
								}
								sMenu[nivel] = sMenu[nivel] + "\r\n\r\n" + "// Array con todas las opciones del menu " + menuPadre + "\r\n" + nombreArray.substring(0,nombreArray.length()-1) + " = [ [,,,,,,,,,,,,,,,,,,,1,1]]";
							}
							sMenu[nivel] = sMenu[nivel].substring(0,sMenu[nivel].length()-1) + ",\r\n[\"" + descripcion + "\",\"operacionDetalle.jsp?referrer=javascript:history.back()&accion=MODIFICAR&codigo=" + codMenu + "\",1,0," + tieneHijos + "]]";
						}
					}
					sMenuPadre[nivel] = menuPadre;
				}
			}
			// Vuelca el código JavaScript para definir Menús
			for (int i=2;i<6;i++){
				out.print("\r\n// ************************************");
				out.print("\r\n// DEFINICION DE LOS MENUS DE NIVEL " + i);
				out.print("\r\n// ************************************");
				out.print(sMenu[i]);
				
			}
		}
	} else {
		out.print("No hay nada que hacer");
	}
%>
</script>
<script language="JavaScript" src="./js/HM_Loader.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
<%
	String ent = session.getValue("canal").toString();
	if ("Pesados".equals(ent)){	
%>
<link rel="stylesheet" type="text/css" href="css/canaljava.css">
<%
	}else if ("Ligeros".equals(ent)){
%>
<link rel="stylesheet" type="text/css" href="css/canalhtml.css">
<%
	}
%>
<script>
function actualiza(){
	
}
</script>
</head>
<body>
<table>
	<tr>
		<td colspan="100%" align="right">
			<font class="HeaderFont3">Entornos:</font> 
			<input type="hidden" name="CodEscenario" value="<%=escDefecto%>">
			<select name="codEntorno" onchange="actualiza();">
			<%
				String cadena = null;
				Object[] entornos = (Object[])session.getValue("entornos");
				for (int i=0;i<entornos.length;i++){
		      			cadena=entornos[i].toString();
		      			if(session.getValue("entorno").equals(cadena.substring(1,2))){
		      				out.print("<option value=\"" + cadena + "\" selected>" + entornos[i] + "</option>");
		      			} else {
		      				out.print("<option value=\"" + cadena + "\">" + entornos[i] + "</option>");
		      			}      			
		      		}		
			%>
			</select>
		</td>
	</tr>
</table>
<table border="0" width="750" class="EscTabla" cellspacing="0" cellpadding="0">
	<tr>
		<td width="100%" height="17" class="EscTitulo">
			<table border="0" width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="19" class="EscTitulo"><a href="escenarioDetalle.jsp?accion=MODIFICAR&CodEscenario=<%=escDefecto%>"><img border="0" src="images/<%=(oonn!=null)?oonn[0][1]:"blanck"%> + ".gif" width="17" height="17"></a></td>
					<td class="EscTitulo">BBVA - Escenario de <%=nombreEscenario%></td>
					<td width="20" class="EscTitulo" align="right">X</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="100%" height="17" class="EscArea">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<%=sMenu[1]%>
					</td>
					<td>
						<p align="right"><span style="letter-spacing: -0">Escenario</span>
					</td>
					<td width="20" height="17">
						<p align="right"><a onmouseover="HM_f_PopUp('elMenu1',event)" onmouseout="popDown('elMenu1')" href="simulacion.jsp?CodEscenario=<%=escDefecto%>"><img border="0" src="images/<%=(oonn!=null)?oonn[0][1]:"blanck"%> + ".gif" width="17" height="17"></a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="EscArea">
			<table border="0" width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<%
						// Monta los accesos directos
						Object[][] aadd = dao.getAADDEscenario(escDefecto,session.getValue("entorno"));
						if (aadd != null && aadd.length > 0){
							String codAD = null;
							for (int i=0;i<aadd.length;i++){
								codAD = aadd[i][0].toString();
								if ("ATLP".equals(codAD)){
									numVez++;
									if (numVez==1){
										%>
										<script language="JavaScript">
											function ver_Operacion(Operacion)
											{
												if (Operacion != "?" && Operacion != "") {
													location.href="operacionDetalle.jsp?referrer=javascript:history.back()&accion=MODIFICAR&codigo=" + Operacion;
												}
											}
										</script>
										<%
									}
									%>
									<td width="80" class="EscSeparador">
										<form name="PS<%=numVez%>">
											<table style="width: 92" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td align="center" width="100%">
														<a href="operacionDetalle.jsp?referrer=javascript:history.back()&accion=CONSULTAR&codigo=ATLP" class="EscriFont">Acceso por código</a>
													</td>
												</tr>
												<tr>
													<td align="center">
														<select size="1" name="Pseudocodigo<%=numVez%>" onchange="ver_Operacion(document.PS<%=numVez%>.Pseudocodigo<%=numVez%>.value)">
															<option selected value="?">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
															<%
																Object[][] pseudocodigo = dao.getPsEscenario(session.getValue("canal"),session.getValue("entorno"));
																if (pseudocodigo != null && pseudocodigo.length > 0){
																	for (int l = 0;l<pseudocodigo.length;l++){
																		out.print("<option value=\"" + pseudocodigo[l][0] + "\">" + pseudocodigo[l][0] + " </option>");
																		out.print("<option value=\"" + pseudocodigo[l][1] + "\">" + pseudocodigo[l][1] + " </option>");
																	}
																}
															%>
														</select>
													</td>
												</tr>
											</table>
										</form>
									</td>
						
									<%
								} else {
								%>
		<td width="55">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center">
						<a onclick="location.href='operacionDetalle.jsp?referrer=javascript:history.back()&accion=MODIFICAR&codigo=<%=codAD%>'" href='#' target="_self"><img border="0" src="images/<%=aadd[i][1]%>.gif" width="17" height="17" alt="<%=aadd[i][3]%>"></a>
					</td>
				</tr>
				<tr>
					<td align="center"><a 
								<%
									if (sMenuAD.containsKey(codAD)){
										out.print(sMenuAD.get(codAD));
									}
								%>
								onclick="location.href='operacionDetalle.jsp?referrer=javascript:history.back()&accion=MODIFICAR&codigo=<%=codAD%>'" href='#' target="_self" class="EscriFont"><%=aadd[i][2]%></a>
					</td>
				</tr>
			</table>
		</td>
								<%
								}
							}
						}
								// Concluye los accesos directos
								%>
		<td>
		</td>
		<td width="60" valign="middle" align="right" class="EscSeparaIzq">
			<img border="0" src="images/logo0182.gif" width="49" height="20" align="bottom">
		</td>
	</tr>
	</table></td></tr>
	<tr>
		<td width="100%" class="EscArea">
			<table border="0" width="100%" cellspacing="0" cellpadding="0">
				<tr><%=htmlObjetoNegocio%>
					<td>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="100%" background="images/BBVAfondoCortado.jpg" height="300" class="EscArea">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td width="100%" height="17" class="EscArea">Area de Informacion</td>
	</tr>
</table>

		</td>
	</tr>
	
</body>
</html>
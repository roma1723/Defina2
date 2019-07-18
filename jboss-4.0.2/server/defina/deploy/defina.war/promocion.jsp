<html>
<head>
	<script src="js/defina.js"></script>
	<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
	<script>
		var ascii = "true";
		function seleccion(a) {
			ascii = a;
		}

		function promocionarFichero() {
			var ruta = document.getElementById("ruta").value;
			var servidor = document.getElementById("servidor").value;
			var directorio = document.getElementById("directorio").value;
			var usuario = document.getElementById("usuario").value;
			var password = document.getElementById("password").value;
			if (ruta == '' || servidor == '' || directorio == '' || usuario == '' || password == '' || ascii == '') {
				alert("Debe informar todos los campos para proceder al envio");
			} else {
				document.forms[0].submit();
			}
		}
	</script>
</head>

<body>
<form action="promocion.jsp" method="POST" enctype="multipart/form-data">
<%
		defina.ficheros.promocion.PromocionFTP p = new defina.ficheros.promocion.PromocionFTP();
		int rc = p.promocionaFromRequest(request);
		if (rc != -1) {
			String sBack = null;
			out.print("<script>alert('");
			if (rc == 0) {
				out.print("Operación realizada correctamente");
			} else {
				out.print("Se ha producido un error: " + p.getError());
				sBack = "history.back();";
			}
			out.print("');");
			if (sBack != null) out.print(sBack);
			out.print("</script>");
		}
		
		// Recupera el tipo de perfil de la sesión
		String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();
%>
<script> window.setTimeout("alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';", <%=""+request.getSession().getMaxInactiveInterval()*1000%>);</script>
<table width="100%"  border="0" cellspacing="8" cellpadding="0">
	<tr>
		<td height="22" class="CabeceraRelacionada2">&nbsp;&nbsp;PROMOCION FTP DE FICHEROS</td>
	</tr>
<tr>
    <td>
	<fieldset>
		<legend>Fichero a promocionar</legend>
		<table width="100%"  border="0" cellspacing="8" cellpadding="0">
			<tr>
				<td width=175>
					<label>Ruta al fichero</label>
				</td>
				<td>
					<input type="file" name="ruta" size=35/>
				</td>
			</tr>
		</table>
	</fieldset>
	&nbsp;&nbsp;&nbsp;
	<fieldset>
		<legend>Datos del servidor</legend>
		<table width="100%"  border="0" cellspacing="8" cellpadding="0">
			<tr>
				<td width=175>
					<label>Nombre de red del servidor</label>
				</td>
				<td>
					<input type="text" name="servidor" size=20/>
				</td>
				<td>
					<label>Directorio destino en el servidor</label>
				</td>
				<td>
					<input type="text" name="directorio" size=20/>
				</td>

			</tr>
			<tr>
				<td>
					<label>Usuario FTP</label>
				</td>
				<td>
					<input type="text" name="usuario" size=20/>
				</td>
				<td>
					<label>Contraseña FTP</label>
				</td>
				<td>
					<input type="password" name="password" size=20/>
				</td>
			</tr>
		</table>
	</fieldset>
	&nbsp;&nbsp;&nbsp;
	<fieldset>
		<legend>Modo de transferencia</legend>
		<table width="100%"  border="0" cellspacing="8" cellpadding="0">
			<tr>
				<td>
					<input type="radio" name="ascii" value="true" onClick="seleccion(this.value)" checked>Ascii</input>
					<BR>
					<input type="radio" name="ascii" value="false" onClick="seleccion(this.value)">Binario</input>
				</td>
			</tr>
		</table>
	</fieldset>
   </td>
</tr>
<tr>
    <td align="right">
    	<%
    		String disabled = null;
	  		if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
	  			disabled = "disabled=\"true\"";
	  		} else {
	  			disabled ="";
	  		}
    	%>
	<input type="button" name="ib1" value="Enviar" class="Boton" onClick="promocionarFichero();" <%=disabled%>/>
   </td>
</tr>

</table>
</form>
</body>

</html>
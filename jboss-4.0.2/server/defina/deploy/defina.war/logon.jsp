<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="js/defina.js"></script>
<%
	String accion = request.getParameter("accion");
	System.out.println("JAVA JSP :: Página de Logon");
	int hecho = -1;
	if("LOGON".equals(accion)){
		System.out.println("JAVA JSP :: Acción de LOGON");
		// Recupera el código de usuario y lo coloca en la sesión
		session.putValue("codUsuario",request.getParameter("usuario"));
		System.out.println("JAVA JSP :: Usuario " + request.getParameter("usuario"));
		hecho = 1;
	} else if ("NOLOGON".equals(accion)){	
		// Obtiene el usuario que llega en la petición. Para ello, obtiene la cabecera "Authorization"
		String cabecera=request.getHeader("Authorization");
		if (cabecera != null && cabecera.length() != 0){
			cabecera = cabecera.substring("Basic ".length(),cabecera.length());
			cabecera = atad.defina.util.Base64Coder.decodeString(cabecera);
			if (cabecera != null && cabecera.indexOf(":") != -1){
				cabecera = cabecera.substring(0,cabecera.indexOf(":"));
				// Sube a sesión el dato
				session.putValue("codUsuario",cabecera);
			}
		} else {
			session.putValue("codUsuario","defecto");
		}
		System.out.println("JAVA JSP :: Datos " + session.getValue("codUsuario"));
		hecho = 2;
	}
	if(hecho != -1 && hecho > 0){
		// Redirige a la página index.jsp con la URL defina/index.jsp
		System.out.println("JAVA JSP :: Va a index.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request,response);
	}
%>
<script>
	function aceptar(){
		// Redirije sobre esta misma URL, y actualiza los datos
		var stringPwd="**********";
		var tmp = "logon.jsp?accion=LOGON&usuario=" + document.getElementById("usuario").value + "&password=" + stringPwd;
		window.navigate(tmp);
	}
	function cancelar(){
		// Interrumpe la navegación
	}
</script>
<LINK href="css/NacarIE1024v01.css" type=text/css rel=stylesheet>
</head>
<body>
	<table width="100%" height="50%" border="0">
		<tr height="40%">
			<td colspan="2"></td>
		</tr>
		<tr height="5%">
			<td align="right"><label>Usuario: </label></td>
			<td align="left"><input name="usuario" type="text" maxlength="10" class="CampoObligatorio" size="9"></td>
		</tr>
		<tr height="5%">
			<td align="right"><label>Password: </label></td>
			<td align="left"><input name="password" type="password" maxlength="15" class="CampoObligatorio" size="9"></td>
		</tr>
	</table>
	<table width = "100%" height="50%" border="0">
		<tr>
			<td valign="top" align="right"><input type="button" name="aceptar" value="Aceptar" class="Boton" onclick="aceptar();"></td>
				&nbsp;&nbsp;&nbsp;
			<td valign="top" align="left"><input type="button" name="cancelar" value="Cancelar" class="Boton" onclick="cancelar();"></td>
		</tr>
	</table>
</body>
</html>
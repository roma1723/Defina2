<html>
<head>
<title>Defina</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head>
<%
	// carga en la sesión las variables de configuración de la herramienta
	atad.defina.entorno.Configuracion.load(application, request, response);

	// obtención de un objeto de acceso a la base de datos de versiones
	atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstanceVersiones(session);
	
	session.putValue("version", request.getParameter("version"));
	session.putValue("canal", request.getParameter("canal"));
	session.putValue("entorno", request.getParameter("entorno"));
	session.putValue("mostrar",request.getParameter("mostrar"));

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
%>

<frameset cols="190,*" frameborder="NO" border="0" framespacing="0">
  <frame src="cabecera.jsp" name="izquierdo" scrolling="auto" noresize>
  <frame src="contenido.html" name="contenido">
</frameset>
<noframes><body>
</body></noframes>
</html>

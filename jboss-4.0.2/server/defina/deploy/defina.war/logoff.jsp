<html>
<head>
</head>
<%
	try{
		session.invalidate();
	} catch (java.lang.IllegalStateException isE){
		// Si entra por aqu�, la sesi�n se ha invalidado previamente por el servidor de aplicaciones.
		// En este caso, no se puede invalidar de nuevo la sesi�n por c�digo
	} 
	request.getSession(true);
	String cad = "/index.jsp?version=" + request.getParameter("version") + "&canal=" + request.getParameter("canal") + "&entorno=" + request.getParameter("entorno") + "&mostrar=" + request.getParameter("mostrar");
	application.getRequestDispatcher(cad).forward(request, response);
%>
</html>

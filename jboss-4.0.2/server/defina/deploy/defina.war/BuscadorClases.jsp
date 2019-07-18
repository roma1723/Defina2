<%@ page import="java.io.*,java.util.*,java.sql.*,javax.naming.*,javax.sql.*,java.net.URL,java.net.URLClassLoader"%>

//ejemplo de busqueda ibi/srv/test/GraphTestObj.class

<%
   String mensaje = "";
   String miClase=request.getParameter("clase");

   if (miClase != null && miClase.length() != 0)
   {
       URL url = (getClass().getClassLoader().getResource(miClase));
       if (url == null)
       {
          mensaje = "No se ha encontrado la clase " + "\"" +  miClase + "\"";
       }else{
          mensaje =  url.getFile();
       }
   }

%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<html>
<HEAD>
<TITLE> ClassPath </TITLE>
</HEAD>
<BODY>
<FORM action="BuscadorClases.jsp" method="GET" enctype=text/plain>
<h1>Búsqueda en el cargador de clases </h1>
<table border=2>
<tbody>
<tr><td><th>Nombre de la clase:</th></td><td><INPUT TYPE = "TEXT" NAME = "clase" SIZE=48>
<INPUT TYPE="submit" value="Buscar">
</tbody>
</table>
</FORM>
<%=mensaje%>
</BODY>
</html>


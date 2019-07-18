package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class detalle_005fvalidar_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.Vector _jspx_dependants;

  public java.util.List getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<title>Untitled Document</title>\r\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n");
      out.write("\t\t\t<LINK href=\"css/NacarIE1024v01.css\" type=text/css rel=stylesheet>\r\n");
      out.write("\t<script language=\"JavaScript\">\r\n");
      out.write("\t\tfunction volver(){\r\n");
      out.write("\t\t\tlocation.href='validar.jsp';\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\t\t\t\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<body>\r\n");
      out.write("<script> window.setTimeout(\"alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';\", ");
      out.print(""+request.getSession().getMaxInactiveInterval()*1000);
      out.write(");</script>\r\n");
      out.write("\t\t\t\t<table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td height=\"22\" class=\"CabeceraRelacionada2\">&nbsp;&nbsp;VALIDAR</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"TituloTabla\">Operaciones que NO cumplen la validaci&oacuten</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\r\n");

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


      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"dataTD\"><b>Validacion: </b>");
      out.print(descripcion);
      out.write("</td> \r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td class=\"dataTD\"><b>Gravedad: </b>");
      out.print(gravedad);
      out.write("</td> \r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<table width=\"100%\"  border=\"0\" cellspacing=\"2\" cellpadding=\"0\" class=\"TablaDatos\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr class=\"CabeceraTabla\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td width=\"2%\" nowrap class=\"CabeceraTabla\">C&oacutedigo de operaci&oacuten</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<td width=\"48%\" class=\"CabeceraTabla\">Descripci&oacuten del error</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td align=\"right\">\r\n");
      out.write("\t\t\t\t\t\t\t");

							String requester = request.getParameter("requester");
							if (requester == null) requester = "history.back";
							else requester = "window.navigate('" + requester + "')";
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"button\" name=\"Button2\" value=\"Volver\" class=\"Boton\" onclick=\"volver();\">\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

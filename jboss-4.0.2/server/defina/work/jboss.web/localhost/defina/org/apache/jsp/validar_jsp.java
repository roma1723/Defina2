package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class validar_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("                                                </head>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<body>\r\n");
      out.write("\t\t\t<script> window.setTimeout(\"alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';\", ");
      out.print(""+request.getSession().getMaxInactiveInterval()*1000);
      out.write(");</script>\r\n");
      out.write("\t\t\t\t<table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td height=\"22\" class=\"CabeceraRelacionada2\">&nbsp;&nbsp;VALIDAR</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t  <td class=\"TituloTabla\">Listado de  validaciones</td>\r\n");
      out.write("\t\t\t\t  </tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t  <td><table width=\"100%\"  border=\"0\" cellspacing=\"2\" cellpadding=\"0\" class=\"TablaDatos\">\r\n");
      out.write("                                            <tr class=\"CabeceraTabla\">\r\n");
      out.write("                                              <td width=\"2%\" nowrap class=\"CabeceraTabla\">Error en la validaci&oacute;n </td>\r\n");
      out.write("                                              <td width=\"48%\" class=\"CabeceraTabla\">Validaci&oacute;n</td>\r\n");
      out.write("                                              <td width=\"48%\" class=\"CabeceraTabla\">Gravedad</td>\r\n");
      out.write("                                            </tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("                                         </table></td>\r\n");
      out.write("\t\t\t\t  </tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</table>\r\n");
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

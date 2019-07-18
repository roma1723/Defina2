package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class logon_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<head>\r\n");
      out.write("<title>Untitled Document</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n");
      out.write("<script src=\"js/defina.js\"></script>\r\n");

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

      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\tfunction aceptar(){\r\n");
      out.write("\t\t// Redirije sobre esta misma URL, y actualiza los datos\r\n");
      out.write("\t\tvar stringPwd=\"**********\";\r\n");
      out.write("\t\tvar tmp = \"logon.jsp?accion=LOGON&usuario=\" + document.getElementById(\"usuario\").value + \"&password=\" + stringPwd;\r\n");
      out.write("\t\twindow.navigate(tmp);\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction cancelar(){\r\n");
      out.write("\t\t// Interrumpe la navegación\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<LINK href=\"css/NacarIE1024v01.css\" type=text/css rel=stylesheet>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<table width=\"100%\" height=\"50%\" border=\"0\">\r\n");
      out.write("\t\t<tr height=\"40%\">\r\n");
      out.write("\t\t\t<td colspan=\"2\"></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr height=\"5%\">\r\n");
      out.write("\t\t\t<td align=\"right\"><label>Usuario: </label></td>\r\n");
      out.write("\t\t\t<td align=\"left\"><input name=\"usuario\" type=\"text\" maxlength=\"10\" class=\"CampoObligatorio\" size=\"9\"></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr height=\"5%\">\r\n");
      out.write("\t\t\t<td align=\"right\"><label>Password: </label></td>\r\n");
      out.write("\t\t\t<td align=\"left\"><input name=\"password\" type=\"password\" maxlength=\"15\" class=\"CampoObligatorio\" size=\"9\"></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<table width = \"100%\" height=\"50%\" border=\"0\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td valign=\"top\" align=\"right\"><input type=\"button\" name=\"aceptar\" value=\"Aceptar\" class=\"Boton\" onclick=\"aceptar();\"></td>\r\n");
      out.write("\t\t\t\t&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t\t\t<td valign=\"top\" align=\"left\"><input type=\"button\" name=\"cancelar\" value=\"Cancelar\" class=\"Boton\" onclick=\"cancelar();\"></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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

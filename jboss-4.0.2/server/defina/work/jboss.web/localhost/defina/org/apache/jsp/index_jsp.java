package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<title>Defina</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<frameset cols=\"190,*\" frameborder=\"NO\" border=\"0\" framespacing=\"0\">\r\n");
      out.write("  <frame src=\"cabecera.jsp\" name=\"izquierdo\" scrolling=\"auto\" noresize>\r\n");
      out.write("  <frame src=\"contenido.html\" name=\"contenido\">\r\n");
      out.write("</frameset>\r\n");
      out.write("<noframes><body>\r\n");
      out.write("</body></noframes>\r\n");
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

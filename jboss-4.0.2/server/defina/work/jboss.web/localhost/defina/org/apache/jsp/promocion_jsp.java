package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class promocion_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<script src=\"js/defina.js\"></script>\r\n");
      out.write("\t<LINK href=\"css/NacarIE1024v01.css\" type=text/css rel=stylesheet>\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\tvar ascii = \"true\";\r\n");
      out.write("\t\tfunction seleccion(a) {\r\n");
      out.write("\t\t\tascii = a;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tfunction promocionarFichero() {\r\n");
      out.write("\t\t\tvar ruta = document.getElementById(\"ruta\").value;\r\n");
      out.write("\t\t\tvar servidor = document.getElementById(\"servidor\").value;\r\n");
      out.write("\t\t\tvar directorio = document.getElementById(\"directorio\").value;\r\n");
      out.write("\t\t\tvar usuario = document.getElementById(\"usuario\").value;\r\n");
      out.write("\t\t\tvar password = document.getElementById(\"password\").value;\r\n");
      out.write("\t\t\tif (ruta == '' || servidor == '' || directorio == '' || usuario == '' || password == '' || ascii == '') {\r\n");
      out.write("\t\t\t\talert(\"Debe informar todos los campos para proceder al envio\");\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tdocument.forms[0].submit();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<form action=\"promocion.jsp\" method=\"POST\" enctype=\"multipart/form-data\">\r\n");

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

      out.write("\r\n");
      out.write("<script> window.setTimeout(\"alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';\", ");
      out.print(""+request.getSession().getMaxInactiveInterval()*1000);
      out.write(");</script>\r\n");
      out.write("<table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td height=\"22\" class=\"CabeceraRelacionada2\">&nbsp;&nbsp;PROMOCION FTP DE FICHEROS</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("    <td>\r\n");
      out.write("\t<fieldset>\r\n");
      out.write("\t\t<legend>Fichero a promocionar</legend>\r\n");
      out.write("\t\t<table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td width=175>\r\n");
      out.write("\t\t\t\t\t<label>Ruta al fichero</label>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input type=\"file\" name=\"ruta\" size=35/>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</fieldset>\r\n");
      out.write("\t&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t<fieldset>\r\n");
      out.write("\t\t<legend>Datos del servidor</legend>\r\n");
      out.write("\t\t<table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td width=175>\r\n");
      out.write("\t\t\t\t\t<label>Nombre de red del servidor</label>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" name=\"servidor\" size=20/>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<label>Directorio destino en el servidor</label>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" name=\"directorio\" size=20/>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<label>Usuario FTP</label>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input type=\"text\" name=\"usuario\" size=20/>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<label>Contraseña FTP</label>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input type=\"password\" name=\"password\" size=20/>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</fieldset>\r\n");
      out.write("\t&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t<fieldset>\r\n");
      out.write("\t\t<legend>Modo de transferencia</legend>\r\n");
      out.write("\t\t<table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"ascii\" value=\"true\" onClick=\"seleccion(this.value)\" checked>Ascii</input>\r\n");
      out.write("\t\t\t\t\t<BR>\r\n");
      out.write("\t\t\t\t\t<input type=\"radio\" name=\"ascii\" value=\"false\" onClick=\"seleccion(this.value)\">Binario</input>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</fieldset>\r\n");
      out.write("   </td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("    <td align=\"right\">\r\n");
      out.write("    \t");

    		String disabled = null;
	  		if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
	  			disabled = "disabled=\"true\"";
	  		} else {
	  			disabled ="";
	  		}
    	
      out.write("\r\n");
      out.write("\t<input type=\"button\" name=\"ib1\" value=\"Enviar\" class=\"Boton\" onClick=\"promocionarFichero();\" ");
      out.print(disabled);
      out.write("/>\r\n");
      out.write("   </td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
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

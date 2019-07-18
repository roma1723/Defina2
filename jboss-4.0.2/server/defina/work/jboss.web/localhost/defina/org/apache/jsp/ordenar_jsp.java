package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ordenar_jsp extends org.apache.jasper.runtime.HttpJspBase
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


	// Recupera el tipo de perfil de la sesión
	String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();
	String disabled = null;
	if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
		disabled = "disabled=\"true\"";
	} else {
		disabled ="";
	}

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>Untitled Document</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n");
      out.write("<script src=\"js/defina.js\"></script>\r\n");
      out.write("<LINK href=\"css/NacarIE1024v01.css\" type=text/css rel=stylesheet>\r\n");
      out.write("<script language=\"JavaScript\">\r\n");
      out.write("\r\n");
      out.write("\tfunction aceptar() {\r\n");
      out.write("\t\t");

        	atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
		Object canal = session.getValue("canal");
		Object tipoOper = request.getParameter("tipoOper");
		Object padre = request.getParameter("padre");
		if ("E".equals(tipoOper)) {
			if ("Pesados".equals(canal.toString())) {
				padre = "ESCRITOR";
			} else if ("Ligeros".equals(canal.toString())) {
				padre = "PORTAL";
			}
		}

		Object [][] operaciones = dao.getTablaOrdenacion(session.getValue("entorno"), canal, tipoOper, padre);

		for(int i=0; i<operaciones.length; i++) {
			out.print("var var");
			out.print(i);
			out.print(" = document.getElementById(\"");
			out.print("var"+i);
			out.println("\");");
			out.println("if (var" + i + ".value == '') {");
			out.println("alert('Por favor, introduzca el orden para la operación \"" + operaciones[i][1] + "\"');");
			out.println("var" + i + ".focus();");
			out.println("return;");
			out.println("}");
		}		
		
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t// todos los campos estan informados\r\n");
      out.write("\t\t// se procede al envio al servidor\r\n");
      out.write("\t\t");

		String [][] msg = new String[(operaciones.length*2 + 2)][2];
		int j = 0;
		for (int i=0; i<msg.length - 2; i++) {
			msg[i][0] = "" + i;
			if (i%2==0) {
				msg[i][1] = "'" + operaciones[j][0] + "'";
			} else {
				msg[i][1] = "var" + j + ".value";
				j++;
			}
		}
		String referrer = request.getParameter("referrer");
		if (referrer == null) referrer = "";
		msg[msg.length - 1][0] = "" + (msg.length - 1);
		msg[msg.length - 1][1] = "'" + padre + "'";
		msg[msg.length - 2][0] = "" + (msg.length - 2);
		msg[msg.length - 2][1] = "'" + referrer + "'";
		out.println(atad.defina.pres.ProcesadorAjax.getJavascript("ORDENAR", true, false, msg));
		
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\tfunction cerrar() {\r\n");
      out.write("\t\thistory.go(-1);\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<script> window.setTimeout(\"alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';\", ");
      out.print(""+request.getSession().getMaxInactiveInterval()*1000);
      out.write(");</script>\r\n");
      out.write("<form name=\"formulario\" action=\"\" onSubmit=\"valida(this);\">\r\n");
      out.write("<table width=\"100%\"  border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"8\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td height=\"22\" class=\"CabeceraRelacionada2\">&nbsp;&nbsp;");
      out.print((String)request.getParameter("titulo"));
      out.write("</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr><td>\r\n");
      out.write("    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"8\">\r\n");
      out.write("      <tr>\r\n");
      out.write("      \t");

      		Object entorno = session.getValue("canal");
		out.print("<td width=\"97%\" class=\"TextoTablaN\"><img src=\"images/organigrama.gif\" border=\"0\"><strong> Escritorio de " + ("Pesados".equals(entorno.toString())?"oficinas":"canal ligero") + " - " + padre + "</strong></td>");
        
      out.write("\r\n");
      out.write("        </tr>\r\n");
      out.write("        ");

		// Sube los datos ordenados a la sesión
		if (operaciones != null && operaciones.length> 0) {
			for (int i=0;i<operaciones.length;i++){
				out.print("<tr>");
				out.print("<td width=\"97%\" class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\">");
				// El nombre del campo de texto incluye el índice de la posición en el array que contiene la información
				out.print("<a title=\"Editar el Escenario " + operaciones[i][1] + "\" onclick=\"estableceDatos('" + operaciones[i][0] + "','" + operaciones[i][1] + "','" + operaciones[i][2] + "','" + operaciones[i][3] + "','" + operaciones[i][4] + "','escenariosMod.jsp','CONSULTAR');\" href=\"#\"><img border=\"0\" src=\"images/" + operaciones[i][2] + ".gif\" width=\"17\" height=\"17\"></a><input name=\"var" + i + "\" type=\"text\" class=\"CampoEntradaImporte\" value=\"" + operaciones[i][3] + "\" size=\"3\">");
				out.print("<strong>  [" + operaciones[i][0] + "] </strong> " + operaciones[i][1]);
				out.print("</td>");
				out.print("</tr>");
			}
		}
        
      out.write("\r\n");
      out.write("    </table>\r\n");
      out.write("\t</td></tr>\r\n");
      out.write("\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td align=\"right\">\r\n");
      out.write("    <input type=\"button\" name=\"button1\" value=\"Aceptar\" class=\"Boton\" onclick=\"aceptar();\" ");
      out.print(disabled);
      out.write("/>\r\n");
      out.write("    <input type=\"button\" name=\"button2\" value=\"Cerrar\" class=\"Boton\" onclick=\"cerrar();\"/>\r\n");
      out.write("    </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
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

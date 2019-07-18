package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class cabecera_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<LINK href=\"css/NacarIE1024v01.css\" type=text/css rel=stylesheet>\r\n");
      out.write("<style>\r\n");
      out.write("   body { margin: 0% 0% 0% 3%; background-color: #F2EDDF}\r\n");
      out.write("</style>\r\n");
      out.write("<title>Defina - Iniciar sesi&oacute;n</title>\r\n");

	// obtención de un objeto de acceso a la base de datos de versiones
	atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstanceVersiones(session);

	String[] canalesTecnicos = (String []) atad.defina.entorno.Configuracion.getCfg("cfg.canales");
	Object[] entornos = (String []) atad.defina.entorno.Configuracion.getCfg("cfg.entornos");

	Object[][] versiones = dao.getTablaVersiones();

	session.putValue("canales",canalesTecnicos);
	session.putValue("versiones",versiones);
	session.putValue("entornos", entornos);

      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\tfunction comenzar() {\r\n");
      out.write("\r\n");
      out.write("\t\tvar strCanal = document.getElementById(\"canal\").options[document.getElementById(\"canal\").selectedIndex].text\r\n");
      out.write("\t\tvar strVersion = document.getElementById(\"version\").options[document.getElementById(\"version\").selectedIndex].text;\r\n");
      out.write("\t\tvar strEntorno = document.getElementById(\"entorno\").options[document.getElementById(\"entorno\").selectedIndex].text;\r\n");
      out.write("\r\n");
      out.write("\t\tvar strMensaje = \"Va a conectarse a:\\n  Version: \" + strVersion + \"\\n  Canal: \" + strCanal + \"\\n  Entorno: \" + strEntorno + \"\\n¿Desea continuar?\";\r\n");
      out.write("\r\n");
      out.write("\t\tif (confirm(strMensaje)){\r\n");
      out.write("\t\t\ttop.location.href='logoff.jsp?canal='+document.getElementById(\"canal\").value+'&version='+document.getElementById(\"version\").value+'&entorno='+document.getElementById(\"entorno\").value+'&mostrar=true';\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("  function mostrarFrame(){\r\n");
      out.write("  \tvar mostrar = ");
      out.print(session.getValue("mostrar"));
      out.write(";\r\n");
      out.write("  \tif (mostrar != null && mostrar==true){\r\n");
      out.write("  \t\tdocument.getElementById('version').value='");
      out.print(session.getValue("version"));
      out.write("';\r\n");
      out.write("  \t\tdocument.getElementById('canal').value='");
      out.print(session.getValue("canal"));
      out.write("';\r\n");
      out.write("  \t\tdocument.getElementById('entorno').value='");
      out.print(session.getValue("entorno"));
      out.write("';\r\n");
      out.write("\t\t\tvalidar();  \t\t\r\n");
      out.write("  \t}\r\n");
      out.write("  }\r\n");
      out.write("  \r\n");
      out.write("  function validar(){\r\n");
      out.write("  \tvar ver = document.getElementById('version');\r\n");
      out.write("\t\tvar can = document.getElementById('canal');\r\n");
      out.write("\t\tvar ent = document.getElementById('entorno');\r\n");
      out.write("\t\tif (ver.value != 0 && can.value != 0 && ent.value != 0) {\r\n");
      out.write("  \t\tvar fr = document.getElementById('ifr1');\r\n");
      out.write("\t\t\tfr.src = 'menu.jsp?version='+ver.value+\"&canal=\"+can.value+\"&entorno=\"+ent.value;\r\n");
      out.write("\t\t\tdocument.getElementById('boton').value='Conectar';\r\n");
      out.write("\t\t\tfr.style.visibility=\"visible\";\r\n");
      out.write("\t\t\tpulsado=true;\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\talert(\"Seleccione todas las opciones para iniciar la sesión de trabajo en defina\");\r\n");
      out.write("\t\t}\r\n");
      out.write("  }\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body onload=\"mostrarFrame();\">\r\n");
      out.write("\r\n");
      out.write("<form name=\"formulario\" action=\"\">\r\n");
      out.write("<input type=\"hidden\" name=\"literal\" value=\"Conectar\">\r\n");
      out.write("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><img src=\"images/defina.jpg\"></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td>&nbsp;</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><select name=\"version\" class=\"CampoEntrada\" style=\"width:180px\"\">\r\n");
      out.write("      <option value=0 selected>Seleccione versi&oacute;n</option>\r\n");
      out.write("      ");

      	if (versiones != null && versiones.length > 0){
      		for (int i=0;i<versiones.length;i++){
      			out.print("<option value=\"" + versiones[i][0] + "\"" + (versiones[i][0].equals(request.getParameter("version"))?"selected":"") + ">" + versiones[i][1] + "</option>");
      		}
      	};
      
      out.write("\r\n");
      out.write("    </select></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><img src=\"images/ftv2blank.gif\" width=\"100\" height=\"5\"></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><select name=\"canal\" class=\"CampoEntrada\" style=\"width:180px\">\r\n");
      out.write("      <option value=0 selected>Seleccione canal</option>\r\n");
      out.write("      ");

      	if (canalesTecnicos != null && canalesTecnicos.length > 0){
      		for (int i=0;i<canalesTecnicos.length;i++){
      			out.print("<option value=\"" + canalesTecnicos[i] + "\"" + (canalesTecnicos[i].equals(request.getParameter("canal"))?"selected":"") + ">" + canalesTecnicos[i] + "</option>");
      		}
      	}
      
      out.write("\r\n");
      out.write("    </select></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><img src=\"images/ftv2blank.gif\" width=\"100\" height=\"5\"></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><select name=\"entorno\" class=\"CampoEntrada\" style=\"width:180px\">\r\n");
      out.write("      <option value=0 selected>Seleccione entorno</option>\r\n");
      out.write("      ");

      	if (entornos != null && entornos.length > 0){
      		for (int i=0;i<entornos.length;i++){
      			out.print("<option value=\"" + entornos[i].toString().substring(0,1) + "\"" + (entornos[i].toString().substring(0,1).equals(request.getParameter("entorno"))?"selected":"") + ">" + entornos[i] + "</option>");
      		}
      	}
      
      out.write("\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><img src=\"images/ftv2blank.gif\" width=\"100\" height=\"5\"></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td align=\"right\"><input type=\"button\" name=\"boton\" value=\"Conectar\" class=\"Boton\" style=\"align:right\" onClick=\"comenzar();\"></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td>&nbsp;</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("<iframe id=\"ifr1\" width=180 height=400 FRAMEBORDER=\"0\" style=\"visibility:hidden\" ></frame>\r\n");
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

package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.text.*;

public final class menu_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<LINK href=\"css/NacarIE1024v01.css\" type=text/css rel=stylesheet>\r\n");
      out.write("<style>\r\n");
      out.write("  body { margin: 0% 0% 0% 3%; background-color: #F2EDDF}\r\n");
      out.write("  a {  color: #0000EF; font-family: Arial, Helvetica, sans-serif; font-size: 9pt; text-decoration: underline}\r\n");
      out.write("\ta:link {  color: #0000EF; font-family: Arial, Helvetica, sans-serif; font-size: 9pt; text-decoration: underline}\r\n");
      out.write("\ta:visited {  color: #0000EF; font-family: Arial, Helvetica, sans-serif; font-size: 9pt; text-decoration: underline}\r\n");
      out.write("\r\n");
      out.write("</style>\r\n");
      out.write("<script>\r\n");
      out.write("top.frames['contenido'].location.href=\"contenido.html\";\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");

	// Obligamos a recargar la página jsp para que no la coja de caché.
	Date ahora = new Date();
	String miFecha = "?dialog=false&newTime=" + ahora.getTime();
		
	// Guarda en la sesión tanto la base de datos a que se ha conectado, como el canal seleccionado
	String ver = request.getParameter("version");
	String can = request.getParameter("canal");
	String ent = request.getParameter("entorno");

	atad.defina.entorno.Log.log(atad.defina.entorno.Log.LEVEL_INFO, "Conexion a version: " + ver);
	atad.defina.entorno.Log.log(atad.defina.entorno.Log.LEVEL_INFO, "Canal activo: " + can);
	atad.defina.entorno.Log.log(atad.defina.entorno.Log.LEVEL_INFO, "Entorno activo: " + ent);

	session.putValue("version", ver);
	session.putValue("canal", can);
	session.putValue("entorno", ent);

	// Inicialización de la conexión a la base de datos para esta sesión
	atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
	//out.println(atad.defina.entorno.Configuracion.getCfg("cfg.prefijo") + "ANAP" + ver + ".mdb");
	dao.setNombreOrigenDatos(atad.defina.entorno.Configuracion.getCfg("cfg.prefijo") + "ANAP" + ver + ".mdb");
	
	String codUsuario=session.getValue("codUsuario").toString();
	if (!"defecto".equals(codUsuario) && codUsuario != null && codUsuario.length() > 0){
		// Una vez establecido el acceso a los datos, se valida el permiso del usuario contra la base de datos seleccionada
		Object[][] codPerfil = dao.recuperaPerfil(session.getValue("codUsuario").toString());
		if (codPerfil != null && codPerfil.length > 0){
			// Sube a sesión el código de perfil del usuario
			String permiso=null;
			if ("adm".equals(codPerfil[0][0].toString())){
				permiso="0"; // Permisos de acceso totales ==> Perfil de administrador
			} else {
				permiso="1"; // Permisos de acceso sólo de consulta ==> Perfil de usuario
			}
			session.putValue("tipoAcceso",permiso);
		}
	} else {
		session.putValue("tipoAcceso","0");
	}
	
	// Evolución "Mis Accesos" NACAR 2.0
	// Comprueba si, en la combinación de Versión / Canal, existe la operación ficticia "MISACC" en la base de datos
	// Si no existe, la genera y deja todo preparado para poder trabajar con este tipo de agrupaciones. Si existe, no hace 
	// nada más con ella.
	if (!dao.isOpMisAccesos()){
		// La op. no existe, así que se da de alta
		dao.insertarOpMisAccesos(can);
	}
	// Evolución "Operaciones Fuera de Menú" NACAR 2.0
	// Comprueba si, en la combinación de Versión / Canal, existe la operación ficticia "OPFMENU" en la base de datos
	// Si no existe, la genera y deja todo preparado para poder trabajar con este tipo de agrupaciones. Si existe, no hace 
	// nada más con ella.
	if (!dao.isOpFueraMenu()){
		// La op. no existe, así que se da de alta
		dao.insertarOpFueraMenu(can);
	}

      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<form name=\"formulario\" action=\"\">\r\n");
      out.write("\r\n");
      out.write("<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"middle\"><img src=\"images/IIRGTreeItem_s.gif\"><a href=\"");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.escenarios.link"));
      out.write("\" target=\"contenido\">Escenarios</a></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"middle\"><img src=\"images/IIRGTreeItem_s.gif\"><a href=\"");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.operaciones.link") + miFecha);
      out.write("\" target=\"contenido\">Operaciones</a></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"middle\"><img src=\"images/IIRGTreeItem_s.gif\"><a href=\"");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.misAccesosArbol.link"));
      out.write("\" target=\"contenido\">Mis Accesos</a></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"middle\"><img src=\"images/IIRGTreeItem_s.gif\"><a href=\"");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.opFueraMenuArbol.link"));
      out.write("\" target=\"contenido\">Op. No Menú SE</a></td>\r\n");
      out.write("  </tr>     \r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"middle\"><img src=\"images/IIRGTreeItem_s.gif\"><a href=\"");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.autorizaciones.link"));
      out.write("\" target=\"contenido\">Autorizaciones</a></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"middle\"><img src=\"images/IIRGTreeItem_s.gif\"><a href=\"");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.jerarquia.link"));
      out.write("\" target=\"contenido\">Jerarquia</a></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"middle\"><img src=\"images/IIRGTreeItem_s.gif\"><a href=\"");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.simulacion.link"));
      out.write("\" target=\"contenido\">Simulaci&oacuten escritorio</a></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td>&nbsp;</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"middle\"><img src=\"images/IIRGTreeItem_s.gif\"><a href=\"");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.versiones.link"));
      out.write("\" target=\"contenido\">Versiones</a></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"middle\"><img src=\"images/IIRGTreeItem_s.gif\"><a href=\"");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.perfiles.link"));
      out.write("\" target=\"contenido\">Perfiles</a></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"middle\"><img src=\"images/IIRGTreeItem_s.gif\"><a href=\"");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.validar.link"));
      out.write("\" target=\"contenido\">Validar</a></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"middle\"><img src=\"images/IIRGTreeItem_s.gif\"><a href=\"");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.generar.link"));
      out.write("\" target=\"contenido\">Generar</a></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"middle\"><img src=\"images/IIRGTreeItem_s.gif\"><a href=\"");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.promocion.link"));
      out.write("\" target=\"contenido\">Promoci&oacuten</a></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td>&nbsp;</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td valign=\"middle\"><img src=\"images/IIRGTreeItem_s.gif\"><a href=\"");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.documentacion.link"));
      out.write("\" target=\"contenido\">Descarga Documentaci&oacuten</a></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td>&nbsp;</td>\r\n");
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

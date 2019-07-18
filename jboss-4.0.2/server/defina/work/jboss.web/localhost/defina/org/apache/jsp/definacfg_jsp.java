package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class definacfg_jsp extends org.apache.jasper.runtime.HttpJspBase
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


	application.setAttribute("cfg.aplicacion", "/defina");
	application.setAttribute("cfg.prefijo", "D:/Defina/jboss-4.0.2/server/defina/deploy/defina.war/BD/");
	application.setAttribute("cfg.bd.conector", "JDBC");
	application.setAttribute("cfg.bd.versiones", "versionesBD.mdb");
	application.setAttribute("cfg.bd.pool.capacidad", "1");
	application.setAttribute("cfg.bd.conexion.cierre", "true");
	application.setAttribute("cfg.canales", new String[] {"Pesados", "Ligeros"});
	application.setAttribute("cfg.entornos", new String[] {"Desarrollo", "Integrado", "Producción"});
	application.setAttribute("cfg.paginacion.filasPorPagina", "20");
	application.setAttribute("cfg.menus.autorizaciones.link", "mantenimiento.jsp?menu=AUTORIZACIONES&campos=Código,Descripción");
	application.setAttribute("cfg.menus.jerarquia.link", "mantenimiento.jsp?menu=JERARQUIA&campos=Código de operación padre,Código de operación hijo,Número de orden");
	application.setAttribute("cfg.menus.versiones.link", "mantenimiento.jsp?menu=VERSIONES&campos=Código,Descripción");
	application.setAttribute("cfg.menus.generar.link", "generar.jsp");
	application.setAttribute("cfg.menus.validar.link", "validar.jsp");
	application.setAttribute("cfg.menus.operaciones.link", "operacion.jsp");
	application.setAttribute("cfg.menus.perfiles.link", "mantenimiento.jsp?menu=PERFILES&campos=Código,Descripción");
	application.setAttribute("cfg.menus.escenarios.link", "mantenimiento.jsp?menu=ESCENARIOS&campos=Código,Nombre,Autorización,Icono,Orden,Op. Home,Fecha modificación");
	application.setAttribute("cfg.menus.escenarios.ordenar.link", "ordenar.jsp?tipoOper=E&titulo=ESCENARIOS :: ORDENAR");
	application.setAttribute("cfg.menus.escenarios.arbol.link", "escenariosArbol.jsp?");
	application.setAttribute("cfg.menus.escenarios.arbol.ordenar.link", "ordenar.jsp?titulo=ESCENARIOS :: VER ARBOL :: ORDENAR");
	application.setAttribute("cfg.menus.operaciones.arbol.ordenar.link", "ordenar.jsp?titulo=OPERACIONES :: VER ARBOL :: ORDENAR");
	application.setAttribute("cfg.menus.simulacion.link", "simulacion.jsp");
	application.setAttribute("cfg.menus.promocion.link", "promocion.jsp");
	application.setAttribute("cfg.menus.iconos.link", "mantenimiento.jsp?menu=ICONOS&campos=Nombre,Icono");
	application.setAttribute("cfg.menus.documentacion.link", "ayuda/Guia_Defina.zip");
	application.setAttribute("cfg.menus.misAccesosArbol.link", "misAccesosArbol.jsp");
	application.setAttribute("cfg.menus.opFueraMenuArbol.link", "opFueraMenuArbol.jsp");
	// 0: nivel de información (para depuración)
	// 1: nivel de producción (sólo errores)
	application.setAttribute("cfg.trazas.nivel", "0");

	// variable que indica dos cosas: si está informada a un valor diferente de ""
	// se generan en el directorio indicado por su contenido los ficheros
	application.setAttribute("cfg.ficheros.directorio", "D:/Defina/jboss-4.0.2/server/defina/deploy/defina.war/ficheros/%CANAL%/%ENTORNO%/");
	
	// variable utilizada para guardar los ficheros de bases de datos access generados
	application.setAttribute("cfg.directorio.versionado","D:/Defina/jboss-4.0.2/server/defina/deploy/defina.war/BD/Papelera/");
	
	// variable utilizada para recuperar los iconos
	application.setAttribute("cfg.directorio.iconos","D:/Defina/jboss-4.0.2/server/defina/deploy/defina.war/images/");

	// variable que especifica la constante para el acceso de tipo más restrictivo (perfil de usuario)
	application.setAttribute("cfg.acceso.usuario", "1");

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

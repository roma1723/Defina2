package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class operacion_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

 atad.defina.datos.AccesoDatosBase dao = null;
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

      out.write('\r');
      out.write('\n');

	// Asegura que se limpia la información de la sesión, relativa a operaciones a modificar
	session.putValue("codigoModificar",null);
	session.putValue("opRelacionadas",null);
	session.putValue("borraOperacion","false");
	session.putValue("buscadoDato","false");
	// Recupera el tipo de perfil de la sesión
	String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>Untitled Document</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n");
      out.write("<LINK href=\"css/NacarIE1024v01.css\" type=text/css rel=stylesheet>\r\n");
      out.write("<script src=\"js/defina.js\"></script>\r\n");

	boolean dialog = "true".equalsIgnoreCase(request.getParameter("dialog"));
	boolean opfinal = (request.getParameter("opfinal")!=null && !request.getParameter("opfinal").equals("null"))?"true".equalsIgnoreCase(request.getParameter("opfinal")):false;
	atad.defina.pres.Paginacion p = (atad.defina.pres.Paginacion) session.getValue("operacionesPaginadas");
	if (dialog) out.println("<script language=\"javascript\" type=\"text/javascript\" src=\"js/dialogs.js\"></script>");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<script language=\"JavaScript\">\r\n");
      out.write("var filaSel = null;\r\n");
      out.write("var locat = null;\r\n");
      out.write("var b=null;\r\n");
      out.write("function ordenarTabla(col,ascendente){\r\n");
      out.write("\tvar pagina = ");
      out.print((p==null?1:p.getPaginaActual()));
      out.write(";\r\n");
      out.write("\t// <ETIQUETA>\r\n");
      out.write("\t// Si estoy en un dialogo se notifica a la siguiente ventana, para q no pinte las operaciones de tipo etiqueta\r\n");
      out.write("\t//var tmp = 'operacion.jsp?accion=ORDENAR&columna='+col+'&ascendente='+ascendente+generaCadena();\r\n");
      out.write("\tif (");
      out.print(dialog);
      out.write(")\t\r\n");
      out.write("\t\tvar tmp = 'operacion.jsp?dialog=true&accion=ORDENAR&columna='+col+'&ascendente='+ascendente+generaCadena();\r\n");
      out.write("\telse\r\n");
      out.write("\t\tvar tmp = 'operacion.jsp?dialog=false&accion=ORDENAR&columna='+col+'&ascendente='+ascendente+generaCadena();\t\r\n");
      out.write("\ttmp = tmp + '&pagina=' + Number(pagina);\r\n");
      out.write("\twindow.navigate(tmp);\t\r\n");
      out.write("}\r\n");
      out.write("function paginar(pagina) {\r\n");
      out.write("\tvar tmp = 'operacion.jsp?accion=PAGINAR&dialog=");
      out.print(dialog);
      out.write("';\r\n");
      out.write("\ttmp = tmp + '&pagina=' + pagina;\r\n");
      out.write("\ttmp = tmp + '&dialog=' + ");
      out.print(dialog);
      out.write(";\r\n");
      out.write("\twindow.navigate(tmp);\r\n");
      out.write("}\r\n");
      out.write("function seleccion(str) {\r\n");
      out.write("\tfilaSel = str.split(\"<sep>\");\r\n");
      out.write("}\r\n");
      out.write("function nuevo(){\r\n");
      out.write("\t//limpiaCampos();\r\n");
      out.write("\tvar tmp = 'operacionDetalle.jsp?accion=NUEVA';\r\n");
      out.write("\tlocation.href=tmp + generaCadena();\r\n");
      out.write("}\r\n");
      out.write("function limpiaCampos(){\r\n");
      out.write("\t// Limpia los campos de la selección que no sean combos\r\n");
      out.write("\tdocument.getElementById(\"codigo\").value=\"\";\r\n");
      out.write("\tdocument.getElementById(\"descripcion\").value=\"\";\r\n");
      out.write("\tdocument.getElementById(\"icono\").value=\"\";\r\n");
      out.write("\tdocument.getElementById(\"parametro1\").value=\"\";\r\n");
      out.write("\tdocument.getElementById(\"autorizacion\").value=\"\";\r\n");
      out.write("\tdocument.getElementById(\"fecModificacion\").value=\"\";\r\n");
      out.write("}\r\n");
      out.write("function borrar(){\r\n");
      out.write("\tif (filaSel == null){\r\n");
      out.write("\t\talert('Debe seleccionar un registro');\r\n");
      out.write("\t} else if (confirm('¿Desea borrar el registro ' + filaSel[0] + '?')){\r\n");
      out.write("\t\t");

		String [][] msg = new String[][] {{"codigo", "filaSel[0]"},{"nombre","filaSel[1]"},
			{"entorno","filaSel[2]"},{"icono","filaSel[3]"},{"tipoOperacion","filaSel[4]"},
			{"parametro1","filaSel[5]"},{"autorizacion","filaSel[6]"},{"fecModificacion","filaSel[7]"}};
		out.println(atad.defina.pres.ProcesadorAjax.getJavascript(17,msg));
		
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function verArbol(){\r\n");
      out.write("\tvar tmp = '");
      out.print(atad.defina.entorno.Configuracion.getCfg("cfg.aplicacion"));
      out.write("/operacionArbol.jsp?accion=CONSULTAR';\r\n");
      out.write("\tif (filaSel == null) {\r\n");
      out.write("\t\talert('Debe seleccionar un registro');\r\n");
      out.write("\t} else {\r\n");
      out.write("\t\tlocation.href = tmp + \"&operacion=\" + filaSel[0] + \"&nombre=\" + filaSel[1] + \"&entorno=\" + filaSel[2] + \"&autorizacion=\" + filaSel[6] + generaCadena();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function generaCadena(){\r\n");
      out.write("\tvar tmp = '&codigo=';\r\n");
      out.write("\tif (b != null){\r\n");
      out.write("\t\ttmp = tmp + document.getElementById(\"codigo\").value;\r\n");
      out.write("\t} else {\r\n");
      out.write("\t\tif (filaSel != null){\r\n");
      out.write("\t\t\ttmp = tmp + filaSel[0];\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\ttmp = tmp + \"\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\ttmp = tmp + '&descripcion=' + document.getElementById(\"descripcion\").value;\r\n");
      out.write("\ttmp = tmp + '&entorno=' + document.getElementById(\"entorno\").value;\r\n");
      out.write("\ttmp = tmp + '&icono=' + document.getElementById(\"icono\").value;\r\n");
      out.write("\ttmp = tmp + '&tipoOperacion=' + document.getElementById(\"tipoOperacion\").value;\r\n");
      out.write("\ttmp = tmp + '&parametro1=' + document.getElementById(\"parametro1\").value;\r\n");
      out.write("\ttmp = tmp + '&autorizacion=' + document.getElementById(\"autorizacion\").value;\r\n");
      out.write("\ttmp = tmp + '&fecModificacion=' + document.getElementById(\"fecModificacion\").value;\r\n");
      out.write("\ttmp = tmp + '&tipoOperacionFinal=' + document.getElementById(\"tipoOperacionFinal\").value;\r\n");
      out.write("\ttmp = tmp + '&codigoBusc=' + document.getElementById(\"codigo\").value;\r\n");
      out.write("\ttmp = tmp + '&descripcionBusc=' + document.getElementById(\"descripcion\").value;\r\n");
      out.write("\ttmp = tmp + '&entornoBusc=' + document.getElementById(\"entorno\").value;\r\n");
      out.write("\ttmp = tmp + '&iconoBusc=' + document.getElementById(\"icono\").value;\r\n");
      out.write("\ttmp = tmp + '&tipoOperacionBusc=' + document.getElementById(\"tipoOperacion\").value;\r\n");
      out.write("\ttmp = tmp + '&parametro1Busc=' + document.getElementById(\"parametro1\").value;\r\n");
      out.write("\ttmp = tmp + '&autorizacionBusc=' + document.getElementById(\"autorizacion\").value;\r\n");
      out.write("\ttmp = tmp + '&fecModificacionBusc=' + document.getElementById(\"fecModificacion\").value;\r\n");
      out.write("\ttmp = tmp + '&tipoOperacionFinalBusc=' + document.getElementById(\"tipoOperacionFinal\").value;\r\n");
      out.write("\tif (filaSel != null){\r\n");
      out.write("\t\ttmp = tmp + '&fecModificacionIn=' + filaSel[7];\r\n");
      out.write("\t}\r\n");
      out.write("\treturn tmp;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function modificar(){\r\n");
      out.write("\tvar tmp = '");
      out.print(atad.defina.entorno.Configuracion.getCfg("cfg.aplicacion"));
      out.write("/operacionDetalle.jsp?accion=MODIFICAR';\r\n");
      out.write("\tif (filaSel == null) {\r\n");
      out.write("\t\talert('Debe seleccionar un registro');\r\n");
      out.write("\t} else {\r\n");
      out.write("\t\tvar s =\"\";\r\n");
      out.write("\t\t//if (filaSel != null || document.getElementById(\"codigo\").value == \"\"){\r\n");
      out.write("\t\t\t//s = '&codigo=' + filaSel[0];\r\n");
      out.write("\t\t//} else {\r\n");
      out.write("\t\t\ts = generaCadena();\r\n");
      out.write("\t\t//}\r\n");
      out.write("\t\tlocation.href = tmp + s;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function buscar(){\r\n");
      out.write("\tb = 'true';\r\n");
      out.write("\tvar tmp = '");
      out.print(atad.defina.entorno.Configuracion.getCfg("cfg.aplicacion"));
      out.write("/operacion.jsp?accion=BUSCAR&dialog=");
      out.print(dialog);
      out.write("';\r\n");
      out.write("\twindow.navigate(tmp + generaCadena());\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\tfunction seleccionarAutorizacion() {\r\n");
      out.write("\t\tvar rc = window.showModalDialog('");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.autorizaciones.link")+"&dialog=true");
      out.write("','','dialogWidth:600px;dialogHeight:680px');\r\n");
      out.write("\t\tif (rc != null) document.getElementById(\"autorizacion\").value = rc[0];\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction seleccionarIcono() {\r\n");
      out.write("\t\tvar rc = window.showModalDialog('");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.iconos.link")+"&dialog=true");
      out.write("','','dialogWidth:500px;dialogHeight:580px');\r\n");
      out.write("\t\tif (rc != null) document.getElementById(\"icono\").value = rc[0];\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");

	Object [][] data = null;
	dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
	Object accion = request.getParameter("accion");

	// Flag indicador de si debe rellenar los campos
	boolean flag=true;
	if (accion == null) accion = "INICIAR";

	if ("INICIAR".equals(accion)){
		flag=false;
		if (p != null) {
			session.removeValue("operacionesPaginadas");
			p.liberar();
			p = null;
		}
		// Vacía la sesión
		session.putValue("busqueda.operacion.codigo",null);
		session.putValue("busqueda.operacion.descripcion",null);
		session.putValue("busqueda.operacion.icono",null);
		session.putValue("busqueda.operacion.tipoOperacion",null);
		session.putValue("busqueda.operacion.parametro1",null);
		session.putValue("busqueda.operacion.autorizacion",null);
		session.putValue("busqueda.operacion.fecModificacion",null);
		session.putValue("busqueda.operacion.tipoOperacionFinal",null);
		// Ejecuta la búsqueda sobre la tabla de operaciones, considerando el entorno y el canal
		//
		// <ETIQUETA>
		// Si las operaciones se van a mostrar en dialogo --> es q se van a colgar, por lo que no hay que mostrar la de tipo etiqueta
		if (dialog) {
			if (!opfinal){
				data = dao.getOperacionesModoDialogo(session.getValue("entorno"),session.getValue("canal"));		
			} else {
				data = dao.getOperacionesFinalesModoDialogo(session.getValue("entorno"),session.getValue("canal"));		
			}
		} else { 
			data = dao.getOperaciones(session.getValue("entorno"),session.getValue("canal"));
		}
		if (data != null && data.length > 0){
			p = new atad.defina.pres.Paginacion();
			p.setDatos(data);
			p.setFilasPorPagina(Integer.parseInt(atad.defina.entorno.Configuracion.getCfg("cfg.paginacion.filasPorPagina").toString()));
			data = p.getPaginaPrimera();
			session.putValue("operacionesPaginadas", p);
		}	
	} else if ("PAGINAR".equals(accion) && p != null) {
		int pagina = 1;
		try {
			pagina = Integer.parseInt(request.getParameter("pagina"));
		} catch(NumberFormatException e) {
		}
		data = p.getPagina(pagina - 1);
	} else if ("BUSCAR".equals(accion)){
		// Recupera todos los datos teniendo en cuenta los parámetros de búsqueda
		if (session.getValue("busqueda.operacion.codigo") == null){
			if (request.getParameter("codigoBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.codigo",request.getParameter("codigoBusc"));
			}
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.codigo") != request.getParameter("codigoBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.codigo",request.getParameter("codigoBusc"));
			}
		}
		if (session.getValue("busqueda.operacion.descripcion") == null){
			if (request.getParameter("descripcionBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.descripcion",request.getParameter("descripcionBusc"));
			}
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.descripcion") != request.getParameter("descripcionBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.descripcion",request.getParameter("descripcionBusc"));
			}
		}
		if (session.getValue("busqueda.operacion.icono") == null){
			if (request.getParameter("iconoBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.icono",request.getParameter("iconoBusc"));
			}	
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.icono") != request.getParameter("iconoBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.icono",request.getParameter("iconoBusc"));
			}
		}
		if (session.getValue("busqueda.operacion.tipoOperacion") == null){
			if (request.getParameter("tipoOperacionBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.tipoOperacion",request.getParameter("tipoOperacionBusc"));
			}
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.tipoOperacion") != request.getParameter("tipoOperacionBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.tipoOperacion",request.getParameter("tipoOperacionBusc"));
			}
		}
		if (session.getValue("busqueda.operacion.parametro1") == null){
			if (request.getParameter("parametro1Busc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.parametro1",request.getParameter("parametro1Busc"));
			}
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.parametro1") != request.getParameter("parametro1Busc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.parametro1",request.getParameter("parametro1Busc"));
			}
		}
		if (session.getValue("busqueda.operacion.autorizacion") == null){
			if (request.getParameter("autorizacionBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.autorizacion",request.getParameter("autorizacionBusc"));
			}	
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.autorizacion") != request.getParameter("autorizacionBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.autorizacion",request.getParameter("autorizacionBusc"));
			}
		}
		if (session.getValue("busqueda.operacion.fecModificacion") == null){
			if (request.getParameter("fecModificacionBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.fecModificacion",request.getParameter("fecModificacionBusc"));
			}
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.fecModificacion") != request.getParameter("fecModificacionBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.fecModificacion",request.getParameter("fecModificacionBusc"));
			}
		}
		if (session.getValue("busqueda.operacion.tipoOperacionFinal") == null){
			if (request.getParameter("tipoOperacionFinalBusc") != null){
				// No hay dato actualizado en la sesión ==> Actualizo la información
				session.putValue("busqueda.operacion.tipoOperacionFinal",request.getParameter("tipoOperacionFinalBusc"));
			}
		} else {
			// Si existe dato en la sesión ==> Compruebo si es igual al que recibo para la búsqueda
			if (session.getValue("busqueda.operacion.tipoOperacionFinal") != request.getParameter("tipoOperacionFinalBusc")){
				// Es diferente, por lo tanto se actualiza la información de la sesión con la de la request
				session.putValue("busqueda.operacion.tipoOperacionFinal",request.getParameter("tipoOperacionFinalBusc"));
			}
		}
		
		//
		// <ETIQUETA>
		// Cuando el listado de operaciones se realiza en modo dialogo no se debe tener en cuenta las operaciones de tipo
		// etiqueta en las busquedas
		if (dialog) {
			Object tipoOp = null;
			data = dao.buscaOperacionesModoDialogo(new Object[]{session.getValue("canal"),
			session.getValue("busqueda.operacion.codigo"),
			session.getValue("busqueda.operacion.descripcion"),
			(request.getParameter("entorno")==null || request.getParameter("entorno").toString().trim().length()==0)?session.getValue("entorno"):request.getParameter("entorno"),
			session.getValue("busqueda.operacion.icono"),
			session.getValue("busqueda.operacion.tipoOperacion"),
			session.getValue("busqueda.operacion.parametro1"),
			session.getValue("busqueda.operacion.autorizacion"),
			session.getValue("busqueda.operacion.fecModificacion"),
			session.getValue("busqueda.operacion.tipoOperacionFinal")});
		}
		else {
			data = dao.buscaOperaciones(new Object[]{session.getValue("canal"),
			session.getValue("busqueda.operacion.codigo"),
			session.getValue("busqueda.operacion.descripcion"),
			(request.getParameter("entorno")==null || request.getParameter("entorno").toString().trim().length()==0)?session.getValue("entorno"):request.getParameter("entorno"),
			session.getValue("busqueda.operacion.icono"),
			session.getValue("busqueda.operacion.tipoOperacion"),
			session.getValue("busqueda.operacion.parametro1"),
			session.getValue("busqueda.operacion.autorizacion"),
			session.getValue("busqueda.operacion.fecModificacion"),
			session.getValue("busqueda.operacion.tipoOperacionFinal")});
		}
		
			
		p = new atad.defina.pres.Paginacion();
		if (data != null && data.length > 0){
			p.setDatos(data);
		} else {
			// Vacía el objeto paginación de la memoria
			session.removeValue("operacionesPaginadas");
			p.setDatos(null);
		}
		p.setFilasPorPagina(Integer.parseInt(atad.defina.entorno.Configuracion.getCfg("cfg.paginacion.filasPorPagina").toString()));
		data = p.getPaginaPrimera();
		session.putValue("operacionesPaginadas", p);			
	} else if ("ORDENAR".equals(accion) && p!= null){
		int columna = 0;
		try {
			columna = Integer.parseInt(request.getParameter("columna"));
		} catch(NumberFormatException e) {
		}
		boolean ascendente=true;
		if ("false".equals(request.getParameter("ascendente").toString())){
			ascendente=false;
		}	
		p.ordenar(columna,ascendente);
		int pagina = 1;
		try {
			pagina = Integer.parseInt(request.getParameter("pagina"));
		} catch(NumberFormatException e) {
		}
		data = p.getPagina(0);
	}

      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<script> window.setTimeout(\"alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';\", ");
      out.print(""+request.getSession().getMaxInactiveInterval()*1000);
      out.write(");</script>\r\n");
      out.write("<table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td height=\"22\" class=\"CabeceraRelacionada2\">&nbsp;&nbsp;OPERACIONES</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td class=\"TextoTablaN\"><fieldset><legend>B&uacute;squeda</legend>\r\n");
      out.write("    <table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td width=\"1%\" nowrap><label>C&oacute;digo&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                <br>\r\n");
      out.write("                <input name=\"codigo\" type=\"text\" maxlength=\"8\" class=\"CampoEntrada\" size=\"9\" value=\"");
      out.print((session.getValue("busqueda.operacion.codigo")!=null)?session.getValue("busqueda.operacion.codigo"):((flag && request.getParameter("codigo")!=null)?request.getParameter("codigo"):""));
      out.write("\">&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("            <td width=\"1%\" nowrap><label>Nombre&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                <br>\r\n");
      out.write("                <input name=\"descripcion\" type=\"text\" maxlength=\"40\" class=\"CampoEntrada\" size=\"35\" value=\"");
      out.print((session.getValue("busqueda.operacion.descripcion")!=null)?session.getValue("busqueda.operacion.descripcion"):((flag && request.getParameter("descripcion")!=null)?request.getParameter("descripcion"):""));
      out.write("\">\r\n");
      out.write("                &nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("            <td width=\"1%\" nowrap><label>Entorno&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                <br>\r\n");
      out.write("                <select size=\"1\" name=\"entorno\" class=\"CampoEntrada\">\r\n");
      out.write("                <option value=\" \">Sin c&oacute;digo</option>\r\n");
      out.write("                ");

                	// Recupera los entornos de la sesión
                	Object[] entornos = (Object[])session.getValue("entornos");
                	Object dato = null;
                	if (entornos != null && entornos.length > 0){
                		String cadena = null;
                		dato = session.getValue("entorno");
                		for (int i=0;i<entornos.length;i++){
											cadena = entornos[i].toString().substring(0, 1);
                			if (dato == null){
                				out.print("<option value=\"" + cadena + "\"" + ((session.getValue("entorno")==cadena)?"selected":"") + ">" + entornos[i] + "</option>");
                			} else {
                				out.print("<option value=\"" + cadena + "\"" + ((session.getValue("entorno").equals(cadena))?"selected":"") + ">" + entornos[i] + "</option>");
                			}
                		}
                	}
                
      out.write("\r\n");
      out.write("              </select>&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("            <td width=\"1%\" nowrap><label>Icono&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("            \t<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                <tr>\r\n");
      out.write("                <td><input name=\"icono\" type=\"text\" maxlength=\"30\" class=\"CampoSalida\" size=\"15\" value=\"");
      out.print((session.getValue("busqueda.operacion.icono")!=null)?session.getValue("busqueda.operacion.icono"):((flag && request.getParameter("icono")!=null)?request.getParameter("icono"):""));
      out.write("\">&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("                <td><img src=\"images/BotonPrismatico.gif\" width=\"19\" height=\"18\" onClick=\"seleccionarIcono();\">&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("                </tr>\r\n");
      out.write("              </table>\r\n");
      out.write("            </td>\r\n");
      out.write("            <td width=\"96%\" nowrap><label>Tipo elemen.&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                <br>\r\n");
      out.write("                <select size=\"1\" name=\"tipoOperacion\" class=\"CampoEntrada\">\r\n");
      out.write("                  <option value=\" \">Sin tipo</option>\r\n");
      out.write("                  ");

                  	// Recupera los tipos de operación
                  	Object[][] datos = dao.getTiposOperacion();
                  	dato = null;
                  	if (datos != null && datos.length > 0){
                  		dato = request.getParameter("tipoOperacion");
                  		for (int i=0;i<datos.length;i++){
							// 
							// <ETIQUETA>
							// Se comprueba si el canal es pesados para NO mostrar el tipo de operacion Etiqueta
							// Si es un dialogo tampoco hay q mostrar en la combo el tipo etiqueta xq se trata de la operacon "Colgar"
							if  (("Ligeros".equals(session.getValue("canal")) && "A".equals(datos[i][0])) || (dialog && "A".equals(datos[i][0]))) 
								continue;
                  			if (dato == null){
                  				if(session.getValue("busqueda.operacion.tipoOperacion")!=null && session.getValue("busqueda.operacion.tipoOperacion")==datos[i][0]){
                  					out.print("<option value=\"" + datos[i][0] + "\" selected>" + datos[i][1] + "</option>");
                  				} else {
                  					out.print("<option value=\"" + datos[i][0] + "\"" + ((flag && request.getParameter("tipoOperacion")==datos[i][0])?"selected":"") + ">" + datos[i][1] + "</option>");
                  				}
                  			} else {
                  				if(session.getValue("busqueda.operacion.tipoOperacion")!=null && session.getValue("busqueda.operacion.tipoOperacion")==datos[i][0]){
                  					out.print("<option value=\"" + datos[i][0] + "\" selected>" + datos[i][1] + "</option>");
                  				} else {
                  					out.print("<option value=\"" + datos[i][0] + "\"" + ((flag && request.getParameter("tipoOperacion").equals(datos[i][0]))?"selected":"") + ">" + datos[i][1] + "</option>");
                  				}
                  			}
                  		}
                  	}
                  
      out.write("\r\n");
      out.write("              </select>&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        </table></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td width=\"1%\" nowrap><label>Par&aacute;metro 1&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                <br>\r\n");
      out.write("                <input name=\"parametro1\" type=\"text\" maxlength=\"200\" class=\"CampoEntrada\" size=\"15\" value=\"");
      out.print((session.getValue("busqueda.operacion.parametro1")!=null)?session.getValue("busqueda.operacion.parametro1"):((flag && request.getParameter("parametro1")!=null)?request.getParameter("parametro1"):""));
      out.write("\">&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("            <td width=\"1%\" nowrap><label></label>\r\n");
      out.write("              C&oacute;digo autorizaci&oacute;n <br>\r\n");
      out.write("              <table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                <tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<!-- Fix: Se reduce el ancho del campo de 15 a 12 para eliminar el scroll horizontal -->  \t\t\t\t\t\t\t\t\t \r\n");
      out.write("                  <td><input name=\"autorizacion\" type=\"text\" maxlength=\"8\" class=\"CampoSalida\" size=\"12\" value=\"");
      out.print((session.getValue("busqueda.operacion.autorizacion")!=null)?session.getValue("busqueda.operacion.autorizacion"):((flag && request.getParameter("autorizacion")!=null)?request.getParameter("autorizacion"):""));
      out.write("\">&nbsp;</td>\r\n");
      out.write("                  <td><img src=\"images/BotonPrismatico.gif\" width=\"19\" height=\"18\" onClick=\"seleccionarAutorizacion();\">&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("                </tr>\r\n");
      out.write("              </table></td>\r\n");
      out.write("            <td width=\"1%\" nowrap><label>Fecha modificaci&oacute;n&nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("                <br>\r\n");
      out.write("\t\t\t\t\t\t\t\t<!-- Fix: Se reduce el ancho del campo de 20 a 15 para eliminar el scroll horizontal -->  \t\t\t\t\t\t\t\t\t \r\n");
      out.write("                <input name=\"fecModificacion\" type=\"text\" maxlength=\"10\" class=\"CampoEntrada\" size=\"15\" value=\"");
      out.print((session.getValue("busqueda.operacion.fecModificacion")!=null)?session.getValue("busqueda.operacion.fecModificacion"):((flag && request.getParameter("fecModificacion")!=null)?request.getParameter("fecModificacion"):""));
      out.write("\">&nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("            <td width=\"97%\" nowrap><label>Operaci&oacute;n final &nbsp;&nbsp;&nbsp;</label>\r\n");
      out.write("              <br>\r\n");
      out.write("              <select size=\"1\" name=\"tipoOperacionFinal\" class=\"CampoEntrada\">\r\n");
      out.write("                <option> </option>\r\n");
      out.write("                ");

                	// Recupera los tipos de operaciones finales
                	dato = null;
                	Object[][] tipos = dao.getTipoOperacionFinal();
                	if (tipos != null && tipos.length > 0){
                		dato = request.getParameter("tipoOperacionFinal");
                		for (int i=0;i<tipos.length;i++){
                			if (dato == null){
                				if(session.getValue("busqueda.operacion.tipoOperacionFinal")!=null && session.getValue("busqueda.operacion.tipoOperacionFinal")==tipos[i][0]){
                					out.print("<option value=\"" + tipos[i][0] + "\" selected>" + tipos[i][0] + "-" + tipos[i][1] + " [" + tipos[i][2] + " parms (" + tipos[i][3] + ")]</option>");
                				} else {
                					out.print("<option value=\"" + tipos[i][0] + "\"" + ((flag && request.getParameter("tipoOperacionFinal")==tipos[i][0].toString())?"selected":"") + ">" + tipos[i][0] + "-" + tipos[i][1] + " [" + tipos[i][2] + " parms (" + tipos[i][3] + ")]</option>");
                				}
                			} else {
                				if(session.getValue("busqueda.operacion.tipoOperacionFinal")!=null && session.getValue("busqueda.operacion.tipoOperacionFinal")==tipos[i][0]){
                					out.print("<option value=\"" + tipos[i][0] + "\" selected>" + tipos[i][0] + "-" + tipos[i][1] + " [" + tipos[i][2] + " parms (" + tipos[i][3] + ")]</option>");
                				} else {
                					out.print("<option value=\"" + tipos[i][0] + "\"" + ((flag && request.getParameter("tipoOperacionFinal").equals(tipos[i][0]))?"selected":"") + ">" + tipos[i][0] + "-" + tipos[i][1] + " [" + tipos[i][2] + " parms (" + tipos[i][3] + ")]</option>");
                				}
                			}
                		}
                	}	
                
      out.write("\r\n");
      out.write("              </select>\r\n");
      out.write("              &nbsp;&nbsp;&nbsp;</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        </table></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("    </fieldset></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td align=\"right\"><input type=\"button\" name=\"Button3\" value=\"Buscar\" class=\"Boton\" onClick=\"buscar();\"></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td width=\"98%\" class=\"TituloTabla\">Listado de operaciones </td>\r\n");
      out.write("        <td width=\"1%\" nowrap>\r\n");
      out.write("        \t<img src=\"images/Izq1.gif\" width=\"17\" height=\"17\" onClick=\"paginar(1);\">\r\n");
      out.write("        \t<img src=\"images/Izq2.gif\" width=\"17\" height=\"17\" onClick=\"paginar(");
      out.print((p==null?1:p.getPaginaActual()));
      out.write(");\">\r\n");
      out.write("        \t<img src=\"images/Dere2.gif\" width=\"17\" height=\"17\" onClick=\"paginar(");
      out.print((p==null?1:p.getPaginaActual() + 2));
      out.write(");\">\r\n");
      out.write("        \t<img src=\"images/Dere1.gif\" width=\"17\" height=\"17\" onClick=\"paginar(");
      out.print((p==null?1:p.getTotalPaginas()));
      out.write(");\">\r\n");
      out.write("        </td>\r\n");
      out.write("        <td width=\"1%\" nowrap>&nbsp;&nbsp;P&aacute;gina:\r\n");
      out.write("            <input name=\"paginaActual\" type=\"text\" class=\"CampoEntradaImporte\" value=");
      out.print((p==null?1:(data==null)?0:p.getPaginaActual()+1));
      out.write(" size=\"3\">\r\n");
      out.write("      /\r\n");
      out.write("      <input name=\"paginaTotal\" type=\"text\" class=\"CampoSalidaImporte\" value=");
      out.print((p==null?1:p.getTotalPaginas()));
      out.write(" size=\"3\">\r\n");
      out.write("&nbsp;\r\n");
      out.write("      <input type=\"button\" name=\"Button2\" value=\"Ir\" class=\"Boton\" style=\"width:40px\" onClick=\"paginar(document.getElementById('paginaActual').value);\"></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("    </table></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td width=\"100%\" colspan=\"3\">\r\n");
      out.write("          <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"2\" class=\"TablaDatos\">\r\n");
      out.write("            <tr class=\"CabeceraTabla\">\r\n");
      out.write("              <td width=\"1%\" class=\"CabeceraTabla\">&nbsp;</td>\r\n");
      out.write("              <td width=\"7%\" nowrap class=\"CabeceraTabla\">C&oacute;digo <img width=\"13\" height=\"13\" src=\"images/AT_ExcluirVert.gif\" onClick=\"ordenarTabla(0,true)\"><img width=\"13\" height=\"13\" src=\"images/AT_IncluirVert.gif\" onClick=\"ordenarTabla(0,false)\"></td>\r\n");
      out.write("              <td width=\"30%\" class=\"CabeceraTabla\">Nombre de Operaci&oacute;n <img width=\"13\" height=\"13\" src=\"images/AT_ExcluirVert.gif\" onClick=\"ordenarTabla(1,true)\"><img width=\"13\" height=\"13\" src=\"images/AT_IncluirVert.gif\" onClick=\"ordenarTabla(1,false)\"></td>\r\n");
      out.write("              <td width=\"5%\" nowrap class=\"CabeceraTabla\">Entorno <img width=\"13\" height=\"13\" src=\"images/AT_ExcluirVert.gif\" onClick=\"ordenarTabla(2,true)\"><img width=\"13\" height=\"13\" src=\"images/AT_IncluirVert.gif\" onClick=\"ordenarTabla(2,false)\"></td>\r\n");
      out.write("              <td width=\"5%\" nowrap class=\"CabeceraTabla\">Icono <img width=\"13\" height=\"13\" src=\"images/AT_ExcluirVert.gif\" onClick=\"ordenarTabla(3,true)\"><img width=\"13\" height=\"13\" src=\"images/AT_IncluirVert.gif\" onClick=\"ordenarTabla(3,false)\"></td>\r\n");
      out.write("              <td width=\"5%\" nowrap class=\"CabeceraTabla\">Tipo Elem. <img width=\"13\" height=\"13\" src=\"images/AT_ExcluirVert.gif\" onClick=\"ordenarTabla(4,true)\"><img width=\"13\" height=\"13\" src=\"images/AT_IncluirVert.gif\" onClick=\"ordenarTabla(4,false)\"></td>\r\n");
      out.write("              <td width=\"30%\" class=\"CabeceraTabla\">Par&aacute;metro 1 <img width=\"13\" height=\"13\" src=\"images/AT_ExcluirVert.gif\" onClick=\"ordenarTabla(5,true)\"><img width=\"13\" height=\"13\" src=\"images/AT_IncluirVert.gif\" onClick=\"ordenarTabla(5,false)\"></td>\r\n");
      out.write("              <td width=\"7%\" nowrap class=\"CabeceraTabla\">Autorizaci&oacute;n <img width=\"13\" height=\"13\" src=\"images/AT_ExcluirVert.gif\" onClick=\"ordenarTabla(6,true)\"><img width=\"13\" height=\"13\" src=\"images/AT_IncluirVert.gif\" onClick=\"ordenarTabla(6,false)\"></td>\r\n");
      out.write("               <td width=\"10%\" class=\"CabeceraTabla\">Fecha Modificaci&oacute;n <img width=\"13\" height=\"13\" src=\"images/AT_ExcluirVert.gif\" onClick=\"ordenarTabla(7,true)\"><img width=\"13\" height=\"13\" src=\"images/AT_IncluirVert.gif\" onClick=\"ordenarTabla(7,false)\"></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");

            	int ind=2;
            	if (data != null && data.length > 0){
            		// Hay datos que cargar en la tabla
            		for (int i=0;i<data.length;i++){
															
	            		// Establece el pijama
	            		if (ind==2){
	            			ind = 1;
	            		} else if (ind ==1){
	            			ind = 2;
	            		}
            			out.println("<tr class=\"Pijama" + ind + "\">");
            			out.println("<td width=\"1%\" class=\"TextoTablaN\"><input name=\"radiobutton\" type= \"radio\" onClick='seleccion(\"" + data[i][0] + "<sep>" + data[i][1] + "<sep>" + data[i][2] + "<sep>" + data[i][3] + "<sep>" + data[i][4] + "<sep>" + atad.defina.pres.Utils.escapaComillas((String)data[i][5]) + "<sep>" + data[i][6] + "<sep>" + data[i][7] + "\");'></td>");
            			out.println("<td class=\"TextoTablaN\">");
            			// Solo establece el enlace cuando no se trata de un diálogo
            			if(!dialog){
            				//out.print("<a href=\"operacionDetalle.jsp?accion=MODIFICAR&referrer=operacion.jsp&codigo=" + data[i][0] + "&fecModificacion=" + data[i][7] + "\" target=\"_self\">");
            					out.print("<a href=\"#\" target=\"_self\" onClick='seleccion(\"" + data[i][0] + "<sep>" + data[i][1] + "<sep>" + data[i][2] + "<sep>" + data[i][3] + "<sep>" + data[i][4] + "<sep>" + atad.defina.pres.Utils.escapaComillas((String)data[i][5]) + "<sep>" + data[i][6] + "<sep>" + data[i][7] + "\");modificar();'>");
            			}
            			out.print(data[i][0]);
            			if (!dialog){
            				out.print("</a>");
            			}
            			out.println("</td>");
            			out.println("<td class=\"TextoTablaN\">" + (data[i][1]==null?"":data[i][1]) + "</td>");
            			out.println("<td nowrap class=\"TextoTablaN\">" + data[i][2] + " </td>");
            			out.println("<td nowrap class=\"TextoTablaN\"><img src=\"images/" + data[i][3] + ".gif\" width=\"17\" height=\"17\"></td>");
            			out.println("<td nowrap class=\"TextoTablaN\">" + data[i][4] + " </td>");
            			out.println("<td class=\"TextoTablaN\">" + (data[i][5]==null?"":data[i][5]) + "</td>");
            			//out.println("<td nowrap class=\"TextoTablaN\">" + (data[i][5]==null?"":data[i][5]) + "</td>");
            			out.println("<td class=\"TextoTablaN\">" + (data[i][6]==null?"":data[i][6]) + "</td>");
            			//out.println("<td nowrap class=\"TextoTablaN\">" + atad.defina.pres.Utils.formatTimeStamp((java.sql.Timestamp) data[i][7]) + " </td>");
            			out.println("<td nowrap class=\"TextoTablaN\">" + (data[i][7]==null?"":data[i][7].toString().substring(0,data[i][7].toString().indexOf(" "))) + " </td>");
            			out.println("</tr>");
            		}
            	}
            
      out.write("\r\n");
      out.write("          </table></td>\r\n");
      out.write("      </tr>\r\n");
      out.write("    </table></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("\t<td align=\"right\">\r\n");
      out.write("\t");

	String disabled=null;
	if (atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
		disabled = "disabled=\"true\"";
	} else {
		disabled = "";
	}
	if (dialog) {
		out.println("<input type=\"button\" name=\"Button1\" value=\"Aceptar\" class=\"Boton\" onClick=\"aceptarDialogo();\" " + disabled + ">");
		out.println("<input type=\"button\" name=\"Button2\" value=\"Cancelar\" class=\"Boton\" onClick=\"cancelarDialogo();\">");
		out.println("<script language=\"javascript\" type=\"text/javascript\">fixDialog();</script>");
	} else {
		out.println("<input type=\"button\" name=\"Button\" value=\"A&ntilde;adir\" class=\"Boton\" onClick=\"nuevo();\"" + disabled + ">");
		out.println("<input type=\"button\" name=\"Button4\" value=\"Modificar\" class=\"Boton\" onClick=\"modificar();\">");
		out.println("<input type=\"button\" name=\"Button5\" value=\"Suprimir\" class=\"Boton\" onClick=\"borrar();\"" + disabled + ">");
		out.println("<input type=\"button\" name=\"Button62\" value=\"Ver árbol\" class=\"Boton\" onClick=\"verArbol();\">");
	}
	
      out.write("\r\n");
      out.write("\t</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
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

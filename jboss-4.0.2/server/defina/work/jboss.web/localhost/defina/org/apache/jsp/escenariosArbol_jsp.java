package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class escenariosArbol_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<LINK href=\"css/NacarIE1024v01.css\" type=text/css rel=stylesheet>\r\n");

	String referrer = request.getRequestURI() + "?" + request.getQueryString();
	boolean p = true;
	String codigo = (request.getParameter("codigoEsc")!=null)?request.getParameter("codigoEsc"):request.getParameter("codigo");
	String nombre = (request.getParameter("nombreEsc")!=null)?request.getParameter("nombreEsc"):request.getParameter("nombre");
	String autorizacion = (request.getParameter("autorizacionEsc")!=null)?request.getParameter("autorizacionEsc"):request.getParameter("autorizacion");
	String icono = (request.getParameter("iconoEsc")!=null)?request.getParameter("iconoEsc"):request.getParameter("icono");
	String orden = (request.getParameter("ordenEsc")!=null)?request.getParameter("ordenEsc"):request.getParameter("orden");
	boolean bExisteMas = false;
	String strNombreMenu = null;
	// Recupera el tipo de perfil de la sesión
	String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();
	String disabled = null;
	if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
		disabled = "disabled=\"true\"";
	} else {
		disabled ="";
	}

      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\tvar filaSel = null;\r\n");
      out.write("\tvar arrayElementos = new Array();\r\n");
      out.write("\tvar numeroElementos = 0;\r\n");
      out.write("\t\r\n");
      out.write("\tfunction deseleccionar(radios,str){\r\n");
      out.write("\t\tif (radios != null){\r\n");
      out.write("\t\t\tif (radios.length > 1){\r\n");
      out.write("\t\t\t\tfor (var i=0;i<radios.length;i++){\r\n");
      out.write("\t\t\t\t\tif (str != radios[i].value){\r\n");
      out.write("\t\t\t\t\t\tradios[i].checked=false;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tif (str != radios.value){\r\n");
      out.write("\t\t\t\t\tradios.checked=false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\t\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction deseleccionPrev(str){\r\n");
      out.write("\t\t// Menús de primer nivel\r\n");
      out.write("\t\tvar radios = document.all.radiobutton0;\r\n");
      out.write("\t\tdeseleccionar(radios,str);\r\n");
      out.write("\t\t// Menús de cualquier nivel excepto el primero\r\n");
      out.write("\t\tradios = document.all.radiobutton;\r\n");
      out.write("\t\tdeseleccionar(radios,str);\r\n");
      out.write("\t\t// Accesos Directos\r\n");
      out.write("\t\tradios = document.all.radiobutton7;\r\n");
      out.write("\t\tdeseleccionar(radios,str);\r\n");
      out.write("\t\t// Objetos de negocio\r\n");
      out.write("\t\tradios = document.all.radiobutton8;\r\n");
      out.write("\t\tdeseleccionar(radios,str);\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction seleccion(str) {\r\n");
      out.write("\t\tdeseleccionPrev(str);\r\n");
      out.write("\t\tfilaSel = str.split('<sep>');\r\n");
      out.write("\t\t// el 0 es el padre y el 1 el código de la operacion\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction posicionar(){\r\n");
      out.write("\t\twindow.location.hash='");
      out.print(request.getParameter("opPosicion"));
      out.write("';\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction ordenar() {\r\n");
      out.write("\t\tif (filaSel == null) {\r\n");
      out.write("\t\t\talert('Debe seleccionar una operación');\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\tlocation.href = '");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.escenarios.arbol.ordenar.link"));
      out.write("' + '&padre=' + filaSel[0] + '&referrer=");
      out.print(java.net.URLEncoder.encode(referrer));
      out.write("';\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction modificar(){\r\n");
      out.write("\t\tif (filaSel == null){\r\n");
      out.write("\t\t\talert(\"Debe seleccionar un registro\")\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\t//location.href='operacionDetalle.jsp?accion=MODIFICAR&referrer=");
      out.print(java.net.URLEncoder.encode(referrer));
      out.write("&codigo=' + filaSel[1] + '&usarSesion=false';\r\n");
      out.write("\t\t\tlocation.href='operacionDetalle.jsp?accion=MODIFICAR&referrer=/defina/escenariosArbol.jsp&codigo=' + filaSel[1] + '&usarSesion=false&cod_escenario=");
      out.print(codigo);
      out.write("&nombre_escenario=");
      out.print(nombre);
      out.write("&aut_escenario=");
      out.print(autorizacion);
      out.write("&icono_escenario=");
      out.print(icono);
      out.write("&orden_escenario=");
      out.print(orden);
      out.write("';\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\tfunction esMenu(strCodigoComparacion){\r\n");
      out.write("\t\r\n");
      out.write("\t\tvar bEsOperacionFinal = false;\r\n");
      out.write("\t\tvar iRecorrido = 0;\r\n");
      out.write("\t\tvar strSeleccion = \"\";\r\n");
      out.write("\t\r\n");
      out.write("\t\tfor(iRecorrido = 0; iRecorrido < arrayElementos.length && !bEsOperacionFinal; iRecorrido++){\r\n");
      out.write("\t\t\tstrSeleccion = arrayElementos[iRecorrido].split('<sep>');\r\n");
      out.write("\t\t\tif (strSeleccion[0] == strCodigoComparacion && (strSeleccion[1] == \"M\" || strSeleccion[1] == \"MN\")){\r\n");
      out.write("\t\t\t\tbEsOperacionFinal = true;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\r\n");
      out.write("\t\treturn bEsOperacionFinal;\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction devuelveElemento(strCodigoComparacion){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar strSeleccion = \"\";\r\n");
      out.write("\t\tvar strComparador = \"\";\r\n");
      out.write("\t\r\n");
      out.write("\t\tfor(iRecorrido = 0; iRecorrido < arrayElementos.length; iRecorrido++){\r\n");
      out.write("\t\t\tstrSeleccion = arrayElementos[iRecorrido].split('<sep>');\r\n");
      out.write("\t\t\tif (strSeleccion[0] == strCodigoComparacion){\r\n");
      out.write("\t\t\t\tstrComparador = arrayElementos[iRecorrido].split('<sep>');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\treturn strComparador;\r\n");
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction colgar(tipo,codigo){\r\n");
      out.write("\t\tif (filaSel != null){\r\n");
      out.write("\t\t\tif (!esMenu(filaSel[1])){\r\n");
      out.write("\t\t\t\talert(\"Sólo se pueden colgar elementos en Menús. [\" + filaSel[1] + \"] no es un Menú.\");\r\n");
      out.write("\t\t\t\treturn;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar rc = null;\r\n");
      out.write("\t\tif(tipo==\"NM\")\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\trc = mostrarDialogoOperacionesFueraMenu();\r\n");
      out.write("\t\t}else{\t\t\r\n");
      out.write("\t\t\trc = mostrarDialogoOperaciones();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif (rc != null) {\r\n");
      out.write("\t\t\t// Posición 4 es el tipo de operación\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tvar referrer = '");
      out.print(request.getRequestURI() + "?" + request.getQueryString());
      out.write("';\r\n");
      out.write("\t\t\tvar cod=\"\";\r\n");
      out.write("\t\t\tif (codigo != null && codigo.length>0){\r\n");
      out.write("\t\t\t\tcod = tipo+codigo.substring(1);\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\t//cod=filaSel[0];\r\n");
      out.write("\t\t\t\tcod=filaSel[1];\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(cod==(\"MN\"+codigo.substring(1,codigo.length)) && \"F\"==rc[4]){\r\n");
      out.write("\t\t\t\talert(\"Una operación final no puede colgarse como menú de primer nivel.\");\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\tif (filaSel != null){\r\n");
      out.write("\t\t\t\t\tvar strSeleccionado = devuelveElemento(filaSel[1]);\r\n");
      out.write("\t\t\t\t\tif (strSeleccionado[1] == \"MN\")\r\n");
      out.write("\t\t\t\t\t\tcod = cod;\r\n");
      out.write("\t\t\t\t\telse\r\n");
      out.write("\t\t\t\t\t\tcod=filaSel[1];\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t");
      out.print(atad.defina.pres.ProcesadorAjax.getJavascript(18, new String[][] {{"0", "cod"}, {"1", "rc[0]"}, {"2", "referrer"}}));
      out.write(";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction mostrarDialogoOperacionesFueraMenu() {\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// Obligamos a recargar la página para que no la coja de caché.\r\n");
      out.write("\t\tvar miFecha = new Date();\r\n");
      out.write("\t\tvar cadenaPasada = 'operacion.jsp?opfinal=true&dialog=true&newHour=' + miFecha.getTime();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\treturn window.showModalDialog(cadenaPasada,'','dialogWidth:900px;dialogHeight:690px');\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction descolgar(tipo,codigo){\r\n");
      out.write("\t\tif (filaSel == null){\r\n");
      out.write("\t\t\talert(\"Debe seleccionar un registro\")\r\n");
      out.write("\t\t} else if (filaSel[0] == '");
      out.print(codigo);
      out.write("') {\r\n");
      out.write("\t\t\talert('No puede descolgarse una operación de primer nivel');\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\tvar c = confirm('Se va a descolgar la operacion ' + filaSel[1] + ' del padre ' + filaSel[0] + '. ¿Desea continuar?');\r\n");
      out.write("\t\t\tif (c) {\r\n");
      out.write("\t\t\t\tvar referrer = '");
      out.print(request.getRequestURI() + "?" + request.getQueryString());
      out.write("';\r\n");
      out.write("\t\t\t\t");
      out.print(atad.defina.pres.ProcesadorAjax.getJavascript(19, new String[][] {{"0", "filaSel[0]"}, {"1", "filaSel[1]"}, {"2", "referrer"}}));
      out.write("\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body onload=\"posicionar()\";>\r\n");
      out.write("<script> window.setTimeout(\"alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';\", ");
      out.print(""+request.getSession().getMaxInactiveInterval()*1000);
      out.write(");</script>\r\n");
      out.write("<form name=\"formulario\" action=\"\">\r\n");
      out.write("<table width=\"100%\"  border=\"0\" align=\"left\" cellpadding=\"0\" cellspacing=\"8\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td height=\"22\" class=\"CabeceraRelacionada2\">&nbsp;&nbsp;ESCENARIOS :: Ver &aacute;rbol</td>\r\n");
      out.write("  </tr>\r\n");

  	// Genera el título para el escenario
  	out.print("<tr>");
  	out.print("<td class=\"TextoTablaCen\"><strong><img src=\"images/" + icono + ".gif\">[" + codigo + "] &nbsp:&nbsp;" + nombre + "</strong></td>");
  	out.print("</tr>");

      out.write("\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td class=\"TituloTabla\">Men&uacute;s de primer nivel:</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td class=\"TituloTabla\">\r\n");
      out.write("      <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"2\" class=\"TablaDatos\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"1%\" nowrap class=\"CabeceraTabla\">&nbsp;</td>\r\n");
      out.write("          <td width=\"87%\" nowrap class=\"CabeceraTabla\">Operaciones</td>\r\n");
      out.write("          <td width=\"5%\" nowrap class=\"CabeceraTabla\">Entorno</td>\r\n");
      out.write("          <td width=\"7%\" nowrap class=\"CabeceraTabla\">C&oacute;digo autorizaci&oacute;n </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        ");

        	// Genera los menús de primer nivel para el escenarios
        	atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
		Object[] dat = new Object[]{codigo,session.getValue("canal")};
		Object[][] menusPrimoNivel = dao.getMenusPrimerNivel(dat);

		String mnpadre = "MN" + codigo.toString().substring(1,codigo.toString().length());
		if (menusPrimoNivel != null && menusPrimoNivel.length > 0){
			// Incluye la fila del escenario
			out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
			out.print("<td class=\"TextoTablaN\"><input name=\"radiobutton0\" type=\"radio\" value=\"" + mnpadre + "<sep>" + codigo + "\" onClick=\"seleccion(this.value);\"></td>");
			out.print("<script>arrayElementos[numeroElementos++] = \"" + codigo + "\" + \"<sep>\" + \"" + "MN" + "\";</script>");
			out.print("<td class=\"TextoTablaN\"><span class=\"TextoTablaN\"><img src=\"images/" + icono + ".gif\">[" + codigo + "] " + nombre + "</span></td>");
			out.print("<td class=\"TextoTablaN\">&nbsp;</td>");
   		out.print("<td class=\"TextoTablaN\">&nbsp;</td>");
 			out.print("</tr>");
 			// Incluye las filas de los menús de primer nivel
 			for (int i=0;i<menusPrimoNivel.length;i++){
 				out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
 				out.print("<td width=\"1%\" class=\"TextoTablaN\"><input name=\"radiobutton0\" type=\"radio\" value=\"" + mnpadre + "<sep>" + menusPrimoNivel[i][0] + "\" onClick=\"seleccion(this.value);\"></td>");
 				out.print("<td width=\"87%\" class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/organigrama.gif\" border=\"0\">[" + menusPrimoNivel[i][0] + "] " + menusPrimoNivel[i][1] + "</td>");
 				out.print("<td width=\"5%\" class=\"TextoTablaN\">" + menusPrimoNivel[i][3] + " </td>");
 				out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (menusPrimoNivel[i][4]==null?"":menusPrimoNivel[i][4].toString()) + " </td>");
 				out.print("</tr>");
 			}
		}
        
      out.write("\r\n");
      out.write("      </table>\r\n");
      out.write("    </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("\t<td align=\"right\">\r\n");
      out.write("\t\t<input type=\"button\" name=\"Button1\" value=\"Modificar\" class=\"Boton\" onclick=\"modificar();\">\r\n");
      out.write("\t\t<input type=\"button\" name=\"Button2\" value=\"Ordenar\" class=\"Boton\" onclick=\"ordenar();\">\r\n");
      out.write("\t\t<input type=\"button\" name=\"Button3\" value=\"Colgar\" class=\"Boton\" onclick=\"colgar('MN','");
      out.print(codigo);
      out.write("');\" ");
      out.print(disabled);
      out.write(">\r\n");
      out.write("\t\t<input type=\"button\" name=\"Button4\" value=\"Descolgar\" class=\"Boton\" onclick=\"descolgar('MN','");
      out.print(codigo);
      out.write("');\" ");
      out.print(disabled);
      out.write(">\r\n");
      out.write("\t</td>\r\n");
      out.write("  </tr>      \r\n");
      out.write("  ");

  	
  	// Construye las tablas para cada menú
  	for (int i=0;i<menusPrimoNivel.length;i++){
  		out.print("<tr>");
  		out.print("<td class=\"TituloTabla\">");
  		out.print("Men&uacute; " + (i+1) + ":");
  		out.print("</td>");
  		out.print("</tr>");
  		out.print("<tr>");
  		out.print("<td>");
  		// Busca los datos para cada menú

			Object[] dat2 = new Object[]{menusPrimoNivel[i][0],session.getValue("canal")};
			Object[][] menusCualquierNivel = dao.getMenusCualquierNivel(dat2);
	
			// Siempre pinta la tabla
			out.print("<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"2\" class=\"TablaDatos\">");
			out.print("<tr>");
			out.print("<td width=\"1%\" nowrap class=\"CabeceraTabla\">&nbsp;</td>");
      out.print("<td width=\"87%\" nowrap class=\"CabeceraTabla\">Operaciones</td>");
      out.print("<td width=\"5%\" nowrap class=\"CabeceraTabla\">Entorno</td>");
      out.print("<td width=\"7%\" nowrap class=\"CabeceraTabla\">C&oacute;digo autorizaci&oacute;n </td>");
      out.print("</tr>");
			out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
      out.print("<a name=\"" + menusPrimoNivel[i][0] + "\"><td width=\"1%\" class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"" + menusPrimoNivel[i][0] + "<sep>" + menusPrimoNivel[i][0] + "\" onClick=\"seleccion(this.value);\"></td></a>");
      out.print("<script>arrayElementos[numeroElementos++] = \"" + menusPrimoNivel[i][0] + "\" + \"<sep>\" + \"" + "M" + "\";</script>");
			out.print("<td width=\"87%\"  class=\"TextoTablaN\"><img src=\"images/organigrama.gif\" border=\"0\">[" + menusPrimoNivel[i][0]+ "] " + menusPrimoNivel[i][1] + "</td>");
			out.print("<td width=\"5%\" class=\"TextoTablaN\">" + menusPrimoNivel[i][3] + " </td>");
      out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (menusPrimoNivel[i][4]==null?"":menusPrimoNivel[i][4].toString()) + " </td>");
      out.print("</tr>");
			
			
			if (menusCualquierNivel != null && menusCualquierNivel.length > 0){
				// Recorre los datos obtenidos, generando la fila correspondiente de la tabla
				String cadIndex=null;
				for (int j=0;j<menusCualquierNivel.length;j++){
					// Antes de empezar, comprueba si este menú, tiene hijos
	
					Object[] dat3 = new Object[]{menusCualquierNivel[j][0],session.getValue("canal")};
					Object[][] menusNivel = dao.getMenusCualquierNivel(dat3);
	
					out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
				  out.print("<td width=\"1%\"  class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"" + menusPrimoNivel[i][0] + "<sep>" + menusCualquierNivel[j][0]+ "\" onClick=\"seleccion(this.value);\"></td>");			        
				  out.print("<script>arrayElementos[numeroElementos++] = \"" + menusCualquierNivel[j][0] + "\" + \"<sep>\" + \"" + menusCualquierNivel[j][5] + "\";</script>");
				  if ((j+1) < 10){
				   	cadIndex = "0" + (j+1);
				  } else {
				   	cadIndex = "" + (j+1);
				  }
				  out.print("<a name=\"" + menusCualquierNivel[j][0] + "\"><td width=\"87%\"  class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/" + ((menusNivel.length==0)?"operacion":"organigrama") + ".gif\" width=\"17\" height=\"17\"> " + cadIndex + "[" + menusCualquierNivel[j][0] + "] " + menusCualquierNivel[j][1] + "</td></a>");
				  out.print("<td width=\"5%\" class=\"TextoTablaN\">" + menusCualquierNivel[j][3] + " </td>");
				  out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (menusCualquierNivel[j][4]==null?"":menusCualquierNivel[j][4].toString()) + " </td>");
				  out.print("</tr>");
				  if (menusNivel != null && menusNivel.length > 0){
				   	// Genera las filas para los últimos menús hijos
				   	for (int k=0;k<menusNivel.length;k++){
				   		// Recupera las operaciones de un nivel inferior (4o nivel)
 							Object[][] opsCuat = dao.getMenusCualquierNivel(new Object[]{menusNivel[k][0],session.getValue("canal")});
				   		out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
					    out.print("<a name=\"" +  menusNivel[k][0] + "\"><td width=\"1%\"  class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"" + menusCualquierNivel[j][0] + "<sep>" +  menusNivel[k][0] + "\" onClick=\"seleccion(this.value);\"></td></a>");
						out.print("<script>arrayElementos[numeroElementos++] = \"" + menusNivel[k][0] + "\" + \"<sep>\" + \"" + menusNivel[k][5] + "\";</script>");
					    if ((k+1) < 10){
					    	cadIndex="0" + (k+1);
					    } else {
					    	cadIndex="" + (k+1);
					    }
					    out.print("<td width=\"87%\"  class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/" + ((opsCuat.length==0)?"operacion":"organigrama") + ".gif\" width=\"17\" height=\"17\"> " + cadIndex + " [" + menusNivel[k][0] + "] " + menusNivel[k][1] + "</td>");
					    out.print("<td width=\"5%\" class=\"TextoTablaN\">" + menusNivel[k][3] + " </td>");
					    out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (menusNivel[k][4]==null?"":menusNivel[k][4].toString()) + " </td>");
					    out.print("</tr>");
					    if (opsCuat != null){
								for(int l=0;l<opsCuat.length;l++){
									// Recupera las operaciones de un nivel inferior (5o nivel)
 									Object[][] opsCinc = dao.getMenusCualquierNivel(new Object[]{opsCuat[l][0],session.getValue("canal")});
									out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
	     							out.print("<a name=\"" + opsCuat[l][0] + "\"><td class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"" + menusNivel[k][0] + "<sep>" + opsCuat[l][0] + "\" onClick=\"seleccion(this.value);\"</td></a>");
	     							out.print("<script>arrayElementos[numeroElementos++] = \"" + opsCuat[l][0] + "\" + \"<sep>\" + \"" + opsCuat[l][5] + "\";</script>");
				     				if ((l+1) < 10){
		      							orden = "0" + (l+1);
	     							} else {
		     							orden = "" + (l+1);
		      						}
				     			out.print("<td class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/" + ((opsCinc.length==0)?"operacion":"organigrama") + ".gif\" border=\"0\">" + orden + "[" + opsCuat[l][0] + "]  " + opsCuat[l][1] + "</td>");
				     			out.print("<td class=\"TextoTablaN\">" + opsCuat[l][3] + " </td>");
	    						out.print("<td class=\"TextoTablaN\">" + (opsCuat[l][4]!=null?opsCuat[l][4]:"") + " </td>");
	     						out.print("</tr>");
	     						if (opsCinc != null){
										for(int m=0;m<opsCinc.length;m++){
											out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
			     							out.print("<a name=\"" + opsCinc[m][0] + "\"><td class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"" + opsCuat[l][0] + "<sep>" + opsCinc[m][0] + "\" onClick=\"seleccion(this.value);\"></td></a>");
			     							out.print("<script>arrayElementos[numeroElementos++] = \"" + opsCinc[m][0] + "\" + \"<sep>\" + \"" + opsCinc[m][5] + "\";</script>");
						     				if ((m+1) < 10){
					      						orden = "0" + (m+1);
			     							} else {
				     							orden = "" + (m+1);
				      						}
						     				out.print("<td class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/operacion.gif\" border=\"0\">" + orden + "[" + opsCinc[m][0] + "]  " + opsCinc[m][1] + "</td>");
						     				out.print("<td class=\"TextoTablaN\">" + opsCinc[m][3] + " </td>");
			    							out.print("<td class=\"TextoTablaN\">" + (opsCinc[m][4]!=null?opsCinc[m][4]:"") + " </td>");
			     							out.print("</tr>");					
	      									Object[][] opsSix = dao.getMenusCualquierNivel(new Object[]{opsCinc[m][0],session.getValue("canal")});
	      									if (!bExisteMas) strNombreMenu = "\\n[" + opsCinc[m][0] + "]: " + opsCinc[m][1];
	      									if (opsSix != null && opsSix.length > 0) bExisteMas = true;
										}
									}	
								}
							}
				   		}
				  	}
				}
			}
			out.print("</table>");
	  	out.print("</td>");
	  	out.print("</tr>");
	  	// Genera la botonera para esta tabla
			out.println("  <tr>");
			out.println("	<td align=\"right\">");
			out.println("		<input type=\"button\" name=\"Button1\" value=\"Modificar\" class=\"Boton\" onclick=\"modificar();\">");
			out.println("		<input type=\"button\" name=\"Button2\" value=\"Ordenar\" class=\"Boton\" onclick=\"ordenar();\">");
			out.println("		<input type=\"button\" name=\"Button3\" value=\"Colgar\" class=\"Boton\" onclick=\"colgar('','');\"" + disabled + " >");
			out.println("		<input type=\"button\" name=\"Button4\" value=\"Descolgar\" class=\"Boton\" onclick=\"descolgar('','');\"" + disabled + " >");
			out.println("	</td>");
			out.println("  </tr>");

  	}
  
      out.write("\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td class=\"TituloTabla\">Accesos directos </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td>\r\n");
      out.write("        <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"2\" class=\"TablaDatos\">  \r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"1%\" nowrap class=\"CabeceraTabla\">&nbsp;</td>\r\n");
      out.write("          <td width=\"97%\" nowrap class=\"CabeceraTabla\">Operaciones</td>\r\n");
      out.write("          <td nowrap class=\"CabeceraTabla\">Entorno</td>\r\n");
      out.write("          <td nowrap class=\"CabeceraTabla\">C&oacute;digo autorizaci&oacute;n </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        ");

        	// Genera los accesos directos

					Object[] dat4 = new Object[]{codigo,session.getValue("canal")};
					Object[][] accesosDirectos = dao.getAccesosDirectos(dat4);
			
					String adpadre = "AD" + codigo.toString().substring(1,codigo.toString().length());
					if (accesosDirectos != null && accesosDirectos.length>0){
						for (int i=0;i<accesosDirectos.length;i++){
										out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
						        out.print("<a name=\"" + accesosDirectos[i][0] + "\"><td width=\"1%\"  class=\"TextoTablaN\"><input name=\"radiobutton7\" type=\"radio\" value=\"" + adpadre + "<sep>" + accesosDirectos[i][0] + "\" onClick=\"seleccion(this.value);\"></td></a>");
						        out.print("<td width=\"87%\"  class=\"TextoTablaN\"> <img src=\"images/" + accesosDirectos[i][2] + ".gif\" width=\"17\" height=\"17\">[" + accesosDirectos[i][0] + "] " + accesosDirectos[i][1] + "</td>");
						        out.print("<td width=\"5%\" class=\"TextoTablaN\">" + accesosDirectos[i][3] + " </td>");
						        out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (accesosDirectos[i][4]!=null?accesosDirectos[i][4]:"") + "</td>");
						        out.print("</tr>");
						}
					}
        
      out.write("\r\n");
      out.write("        </table>\r\n");
      out.write("      </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td align=\"right\">\r\n");
      out.write("        <input type=\"button\" name=\"Button1\" value=\"Modificar\" class=\"Boton\" onclick=\"modificar();\">\r\n");
      out.write("        <input type=\"button\" name=\"Button2\" value=\"Ordenar\" class=\"Boton\" onclick=\"ordenar();\">\r\n");
      out.write("        <input type=\"button\" name=\"Button3\" value=\"Colgar\" class=\"Boton\" onclick=\"colgar('AD','");
      out.print(codigo);
      out.write("');\" ");
      out.print(disabled);
      out.write(">\r\n");
      out.write("        <input type=\"button\" name=\"Button4\" value=\"Descolgar\" class=\"Boton\" onclick=\"descolgar('AD','");
      out.print(codigo);
      out.write("');\" ");
      out.print(disabled);
      out.write(">\r\n");
      out.write("      </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td class=\"TituloTabla\">Objetos de negocio </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td>\r\n");
      out.write("        <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"2\" class=\"TablaDatos\">\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td width=\"1%\" nowrap class=\"CabeceraTabla\">&nbsp;</td>\r\n");
      out.write("            <td width=\"97%\" nowrap class=\"CabeceraTabla\">Operaciones</td>\r\n");
      out.write("            <td nowrap class=\"CabeceraTabla\">Entorno</td>\r\n");
      out.write("            <td nowrap class=\"CabeceraTabla\">C&oacute;digo autorizaci&oacute;n </td>\r\n");
      out.write("          </tr>\r\n");
      out.write("          ");

          	// Recupera los objetos de negocio
		Object[] dat5 = new Object[]{codigo,session.getValue("canal")};
		Object[][] objetosNegocio = dao.getObjetosNegocio(dat5);

		String onpadre = "ON" + codigo.toString().substring(1,codigo.toString().length());
		if (objetosNegocio != null && objetosNegocio.length > 0){
			for(int i=0;i<objetosNegocio.length;i++){
				out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
			        out.print("<a name=\"" + objetosNegocio[i][0] + "\"><td width=\"1%\"  class=\"TextoTablaN\"><input name=\"radiobutton8\" type=\"radio\" value=\"" + onpadre + "<sep>" + objetosNegocio[i][0] + "\" onClick=\"seleccion(this.value);\"></td></a>");
			        out.print("<td width=\"87%\"  class=\"TextoTablaN\">[" + objetosNegocio[i][0] + "] " + objetosNegocio[i][1] + "</td>");
			        out.print("<td width=\"5%\" class=\"TextoTablaN\">" + objetosNegocio[i][3] + "</td>");
			        out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (objetosNegocio[i][4]!=null?objetosNegocio[i][4]:"") + "</td>");
			        out.print("</tr>");
			}
		}
          
      out.write("\r\n");
      out.write("        </table>\r\n");
      out.write("      </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td class=\"TituloTabla\">Operaci&oacute;n Home: \r\n");
      out.write("      \t");

      		if (dao != null){
      			Object[][] opHome = dao.getOpHomeEscenario(codigo);
      			if(opHome != null && opHome.length > 0){
      				out.print(" " + (opHome[0][0]!=null?opHome[0][0]:""));
      			}
      		}
      	
      out.write("\r\n");
      out.write("      </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td align=\"right\"><input type=\"button\" name=\"Button1\" value=\"Modificar\" class=\"Boton\" onclick=\"modificar();\">\r\n");
      out.write("        <input type=\"button\" name=\"Button2\" value=\"Ordenar\" class=\"Boton\" onclick=\"ordenar();\">\r\n");
      out.write("        <input type=\"button\" name=\"Button3\" value=\"Colgar\" class=\"Boton\" onclick=\"colgar('ON','");
      out.print(codigo);
      out.write("');\" ");
      out.print(disabled);
      out.write(">\r\n");
      out.write("        <input type=\"button\" name=\"Button4\" value=\"Descolgar\" class=\"Boton\" onclick=\"descolgar('ON','");
      out.print(codigo);
      out.write("');\" ");
      out.print(disabled);
      out.write("></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("    <td class=\"TituloTabla\">Operaciones Fuera de Menú </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td>\r\n");
      out.write("        <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"2\" class=\"TablaDatos\">  \r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"1%\" nowrap class=\"CabeceraTabla\">&nbsp;</td>\r\n");
      out.write("          <td width=\"97%\" nowrap class=\"CabeceraTabla\">Operaciones</td>\r\n");
      out.write("          <td nowrap class=\"CabeceraTabla\">Entorno</td>\r\n");
      out.write("          <td nowrap class=\"CabeceraTabla\">C&oacute;digo autorizaci&oacute;n </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        ");

        	// Genera las operaciones fuera de menu ligadas a escenarios

					Object[] dat6 = new Object[]{codigo,session.getValue("canal")};
					Object[][] opFueraMenu = dao.getOperacionesFueraMenuEscenario(dat6);
			
					String nmpadre = "NM" + codigo.toString().substring(1,codigo.toString().length());
					if (opFueraMenu != null && opFueraMenu.length>0){
						for (int i=0;i<opFueraMenu.length;i++){
										out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
						        out.print("<a name=\"" + opFueraMenu[i][0] + "\"><td width=\"1%\"  class=\"TextoTablaN\"><input name=\"radiobutton7\" type=\"radio\" value=\"" + nmpadre + "<sep>" + opFueraMenu[i][0] + "\" onClick=\"seleccion(this.value);\"></td></a>");
						        out.print("<td width=\"87%\"  class=\"TextoTablaN\"> <img src=\"images/" + opFueraMenu[i][2] + ".gif\" width=\"17\" height=\"17\">[" + opFueraMenu[i][0] + "] " + opFueraMenu[i][1] + "</td>");
						        out.print("<td width=\"5%\" class=\"TextoTablaN\">" + opFueraMenu[i][3] + " </td>");
						        out.print("<td width=\"7%\" class=\"TextoTablaN\">" + (opFueraMenu[i][4]!=null?opFueraMenu[i][4]:"") + "</td>");
						        out.print("</tr>");
						}
					}
        
      out.write("\r\n");
      out.write("        </table>\r\n");
      out.write("      </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td align=\"right\">\r\n");
      out.write("        <input type=\"button\" name=\"Button1\" value=\"Modificar\" class=\"Boton\" onclick=\"modificar();\">\r\n");
      out.write("        <input type=\"button\" name=\"Button2\" value=\"Ordenar\" class=\"Boton\" onclick=\"ordenar();\">\r\n");
      out.write("        <input type=\"button\" name=\"Button3\" value=\"Colgar\" class=\"Boton\" onclick=\"colgar('NM','");
      out.print(codigo);
      out.write("');\" ");
      out.print(disabled);
      out.write(">\r\n");
      out.write("        <input type=\"button\" name=\"Button4\" value=\"Descolgar\" class=\"Boton\" onclick=\"descolgar('NM','");
      out.print(codigo);
      out.write("');\" ");
      out.print(disabled);
      out.write(">\r\n");
      out.write("      </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td align=\"right\" class=\"Global\">&nbsp;</td>\r\n");
      out.write("    </tr>        \r\n");
      out.write("    <tr>\r\n");
      out.write("      <td align=\"right\">      \r\n");
      out.write("        <input type=\"button\" name=\"Button2\" value=\"Cerrar\" class=\"Boton\" onclick=\"history.back()\">\r\n");
      out.write("      </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");

	if (bExisteMas)	out.println("<script>alert('De la operacion" + strNombreMenu + "\\ncuelgan menus de 5º nivel o superior que no se mostraran.\\nPueden existir otras operaciones de las cuales cuelguen menus de 5º nivel o superior.');</script>");

      out.write("\r\n");
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

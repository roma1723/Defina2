package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class opFueraMenuArbol_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

 atad.defina.datos.AccesoDatosBase dao = null;Object codOperacion=null; Object codOperacionLigera=null;
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
      out.write("<title>Untitled Document</title>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n");
      out.write("<script src=\"js/defina.js\"></script>\r\n");
      out.write("<LINK href=\"css/NacarIE1024v01.css\" type=text/css rel=stylesheet>\r\n");
      out.write("\r\n");

	dao = atad.defina.datos.AccesoDatosBase.getInstance(session);
	String referrer = request.getRequestURI() + "?" + request.getQueryString();
	codOperacion = "OPFMENU";
	codOperacionLigera = "OPFMENUL";
	boolean isPesado = true;
	String can = session.getValue("canal").toString();
	System.out.println("JDBC :: Canal es " + can);
	if ("Ligeros".equals(can)){
		isPesado = false;
	}
	boolean p = true;
	// Recupera el tipo de perfil de la sesión
	String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();
	String disabled = null;
  if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
  	disabled = "disabled=\"true\"";
  } else {
  	disabled ="";
  }

      out.write("\r\n");
      out.write("\r\n");
      out.write("<script language=\"JavaScript\">\r\n");
      out.write("var filaSel = null;\r\n");
      out.write("\r\n");
      out.write("var arrayElementos = new Array();\r\n");
      out.write("var numeroElementos = 0;\r\n");
      out.write("function seleccionar(str){\r\n");
      out.write("\tfilaSel = str.split(\"<sep>\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function cargaOps(){\r\n");
      out.write("\tlocation.href='operacion.jsp?accion=BUSCAR' + generaCadenaCancelar();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function generaCadenaCancelar(){\r\n");
      out.write("\tvar aut=\"\";\r\n");
      out.write("\tvar tmp = '&codigoBusc=' + document.getElementById(\"codigoBusc\").value;\r\n");
      out.write("\ttmp = tmp + '&descripcionBusc=' + document.getElementById(\"descripcionBusc\").value;\r\n");
      out.write("\ttmp = tmp + '&entornoBusc=' + document.getElementById(\"entornoBusc\").value;\r\n");
      out.write("\ttmp = tmp + '&iconoBusc=' + document.getElementById(\"iconoBusc\").value;\r\n");
      out.write("\tif (document.getElementById(\"autorizacionBusc\").value != \"null\"){\r\n");
      out.write("\t\taut = document.getElementById(\"autorizacionBusc\").value;\r\n");
      out.write("\t}\r\n");
      out.write("\ttmp = tmp + '&autorizacionBusc=' + aut;\r\n");
      out.write("\ttmp = tmp + '&tipoOperacionBusc=' + document.getElementById(\"tipoOperacionBusc\").value;\r\n");
      out.write("\ttmp = tmp + '&parametro1Busc=' + document.getElementById(\"parametro1Busc\").value;\r\n");
      out.write("\ttmp = tmp + '&fecModificacionBusc=' + document.getElementById(\"fecModificacionBusc\").value;\r\n");
      out.write("\ttmp = tmp + '&tipoOperacionFinalBusc=' + document.getElementById(\"tipoOperacionFinalBusc\").value;\r\n");
      out.write("\treturn tmp;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function modificar(){\r\n");
      out.write("\tif (filaSel == null){\r\n");
      out.write("\t\talert(\"Debe seleccionar un registro\")\r\n");
      out.write("\t}else if(esOpcionColgable(filaSel[0])){//Si intenta modificar la operación fuera de menú\r\n");
      out.write("\t\talert(\"No se puede modificar la operacion fuera de menú.\");\r\n");
      out.write("\t}else {\r\n");
      out.write("\t\tlocation.href='operacionDetalle.jsp?accion=MODIFICAR&referrer=");
      out.print(java.net.URLEncoder.encode(referrer));
      out.write("&codigo=' + filaSel[0] + '&usarSesion=false';\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function ordenar(){\r\n");
      out.write("\tif (filaSel == null) {\r\n");
      out.write("\t\talert('Debe seleccionar una operación');\r\n");
      out.write("\t} else {\r\n");
      out.write("\t\tvar padre = (filaSel.length == 1?filaSel[0]:filaSel[4]);\r\n");
      out.write("\t\tlocation.href = '");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.operaciones.arbol.ordenar.link"));
      out.write("' + '&padre=' + padre + '&referrer=");
      out.print(java.net.URLEncoder.encode(referrer));
      out.write("';\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t// BEGIN DEFINA\r\n");
      out.write("function esOpcionColgable(strCodigoComparacion){\r\n");
      out.write("\tvar bEsOpcionColgable = false;\r\n");
      out.write("\r\n");
      out.write("\tif (strCodigoComparacion == \"OPFMENU\" || strCodigoComparacion == \"OPFMENUL\"){\r\n");
      out.write("\t\tbEsOpcionColgable = true;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\treturn bEsOpcionColgable;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\t// END DEFINA\r\n");
      out.write("function colgar(){\r\n");
      out.write("\t// BEGIN DEFINA\r\n");
      out.write("\tif (filaSel != null){\r\n");
      out.write("\t\tif (!esOpcionColgable(filaSel[0])){\r\n");
      out.write("\t\t\talert(\"Sólo se pueden colgar elementos en 'Operaciones Fuera de Menú'.\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t// END DEFINA\r\n");
      out.write("\tvar rc = mostrarDialogoOperacionesFueraMenu();\r\n");
      out.write("\tif (filaSel == null){\r\n");
      out.write("\t\talert(\"Debe seleccionar la operación a colgar\");\r\n");
      out.write("\t} else {\r\n");
      out.write("\t\tvar padre = (filaSel.length == 1?filaSel[0]:filaSel[0]);\r\n");
      out.write("\t\tvar referrer = '");
      out.print(referrer);
      out.write("';\r\n");
      out.write("\t\tif (rc != null && padre != null) {\r\n");
      out.write("\t\t\tvar op = rc[0];\r\n");
      out.write("\t\t\t");
      out.print(atad.defina.pres.ProcesadorAjax.getJavascript(18, new String[][] {{"0", "padre"}, {"1", "op"}, {"2", "referrer"}}));
      out.write("\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function mostrarDialogoOperacionesFueraMenu() {\r\n");
      out.write("\t\r\n");
      out.write("\t// Obligamos a recargar la página para que no la coja de caché.\r\n");
      out.write("\tvar miFecha = new Date();\r\n");
      out.write("\tvar cadenaPasada = 'operacion.jsp?opfinal=true&dialog=true&newHour=' + miFecha.getTime();\r\n");
      out.write("\t\r\n");
      out.write("\treturn window.showModalDialog(cadenaPasada,'','dialogWidth:900px;dialogHeight:690px');\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function descolgar(){\r\n");
      out.write("\tif (filaSel == null){\r\n");
      out.write("\t\talert(\"Debe seleccionar un registro\")\r\n");
      out.write("\t} else if (filaSel[0] == '");
      out.print((isPesado)?codOperacion:codOperacionLigera);
      out.write("') {\r\n");
      out.write("\t\talert('No puede descolgarse una operación de primer nivel');\r\n");
      out.write("\t} else {\r\n");
      out.write("\t\tvar padre = (filaSel.length == 1?filaSel[0]:filaSel[4]);\r\n");
      out.write("\t\tvar c = confirm('Se va a descolgar la operacion ' + filaSel[0] + ' del padre ' + padre + '. ¿Desea continuar?');\r\n");
      out.write("\t\tif (c) {\r\n");
      out.write("\t\t\tvar referrer = '");
      out.print(request.getRequestURI() + "?" + request.getQueryString());
      out.write("';\r\n");
      out.write("\t\t\tif (filaSel[0] != null && padre != null) {\r\n");
      out.write("\t\t\t\t");
      out.print(atad.defina.pres.ProcesadorAjax.getJavascript(19, new String[][] {{"0", "padre"}, {"1", "filaSel[0]"}, {"2", "referrer"}}));
      out.write("\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<script> window.setTimeout(\"alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';\", ");
      out.print(""+request.getSession().getMaxInactiveInterval()*1000);
      out.write(");</script>\r\n");
      out.write("<table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td height=\"22\" class=\"CabeceraRelacionada2\">&nbsp;&nbsp;OPERACIONES FUERA DE MENÚ SIN ESCENARIO :: Modificar :: Ver &aacute;rbol </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td class=\"TituloTabla\">&Aacute;rbol de Operaciones Fuera de Menú Sin Escenario</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td align=\"right\"><table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"2\" class=\"TablaDatos\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td width=\"1%\" nowrap class=\"CabeceraTabla\">&nbsp;</td>\r\n");
      out.write("        <td width=\"97%\" nowrap class=\"CabeceraTabla\">Operaciones</td>\r\n");
      out.write("        <td width=\"1%\" nowrap class=\"CabeceraTabla\">Entorno</td>\r\n");
      out.write("        <td width=\"1%\" nowrap class=\"CabeceraTabla\">C&oacute;digo autorizaci&oacute;n </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr class=\"Pijama");
      out.print(((p=!p)?"1":"2"));
      out.write("\">\r\n");
      out.write("        <td width=\"1%\" class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"radiobutton\" onClick=\"seleccionar('");
      out.print(((isPesado)?codOperacion:codOperacionLigera));
      out.write("');\"></td>\r\n");
      out.write("       ");

       String codOpFM = null;
       if (isPesado){
    	   codOpFM = "OPFMENU";
       } else {
    	   codOpFM = "OPFMENUL";
       }
			 out.print("<script>arrayElementos[numeroElementos++] = \"" + codOpFM + "\" + \"<sep>\" + \"" + codOpFM + "\";</script>");
       Object[][] primoNivel = dao.getOpPrimerNivel((isPesado)?codOperacion:codOperacionLigera,session.getValue("entorno"),session.getValue("canal"));
       String strNombreMenu = null;
       
      out.write("\r\n");
      out.write("        <td width=\"97%\"  class=\"TextoTablaN\"><img src=\"images/organigrama.gif\" border=\"0\">[");
      out.print((isPesado)?codOperacion:codOperacionLigera);
      out.write("]  Operaciones Fuera de Menú </td>\r\n");
      out.write("        <td width=\"1%\" class=\"TextoTablaN\">");
      out.print(session.getValue("entorno").toString());
      out.write(" </td>\r\n");
      out.write("        <td width=\"1%\" class=\"TextoTablaN\">");
      out.print(((request.getParameter("autorizacion")!=null && !request.getParameter("autorizacion").equals("null"))?request.getParameter("autorizacion"):""));
      out.write(" </td>\r\n");
      out.write("      </tr>\r\n");
      out.write("            ");

      	// Genera la tabla con el árbol de la operación
      	if (primoNivel != null && primoNivel.length > 0){
      		String orden = null;
      		for (int i=0;i<primoNivel.length;i++){
      			out.print("<tr class=\"Pijama" + ((p=!p)?"1":"2") + "\">");
      			out.print("<td class=\"TextoTablaN\"><input name=\"radiobutton\" type=\"radio\" value=\"radiobutton\" onClick=\"seleccionar('" + primoNivel[i][0] + "<sep>" + primoNivel[i][1] + "<sep>" + primoNivel[i][2] + "<sep>" + primoNivel[i][3] + "<sep>" + codOpFM + "')\"></td>");
      			out.print("<script>arrayElementos[numeroElementos++] = \"" + primoNivel[i][0] + "\" + \"<sep>\" + \"" + primoNivel[i][4] + "\";</script>");
      			if ((i+1) < 10){
      				orden = "0" + (i+1);
      			} else {
      				orden = "" + (i+1);
      			}
      			out.print("<td class=\"TextoTablaN\"><img src=\"images/ftv2blank.gif\" border=\"0\" width=\"17px\" height=\"17px\"><img src=\"images/operacion.gif" + "\" border=\"0\">" + orden + "[" + primoNivel[i][0] + "]  " + primoNivel[i][1] + "</td>");
      			out.print("<td class=\"TextoTablaN\">" + primoNivel[i][2] + " </td>");
      			out.print("<td class=\"TextoTablaN\">" + (primoNivel[i][3]!=null?primoNivel[i][3]:"") + " </td>");
      			out.print("</tr>");
      		}
      	}
      
      out.write("\r\n");
      out.write("    </table></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td align=\"right\"><input type=\"button\" name=\"Button22\" value=\"Modificar\" class=\"Boton\" onclick=\"modificar();\">\r\n");
      out.write("      <input type=\"button\" name=\"Button222\" value=\"Ordenar\" class=\"Boton\" onclick=\"ordenar();\" ");
      out.print(disabled);
      out.write(">\r\n");
      out.write("      <input type=\"button\" name=\"Button2222\" value=\"Colgar\" class=\"Boton\" onclick=\"colgar();\" ");
      out.print(disabled);
      out.write(">\r\n");
      out.write("      <input type=\"button\" name=\"Button222222\" value=\"Descolgar\" class=\"Boton\" onclick=\"descolgar()\" ");
      out.print(disabled);
      out.write("></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td align=\"right\" class=\"Global\">&nbsp;</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td align=\"right\"><input type=\"button\" name=\"Button223\" value=\"Cerrar\" class=\"Boton\" onclick=\"cargaOps();\"></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("</table>\r\n");
      out.write("<input type=\"hidden\" name=\"codigoBusc\" value=\"");
      out.print((request.getParameter("codigoBusc")==null?"":request.getParameter("codigoBusc")));
      out.write("\">\r\n");
      out.write("<input type=\"hidden\" name=\"fecModificacionBusc\" value=\"");
      out.print((request.getParameter("fecModificacionBusc")==null?"":request.getParameter("fecModificacionBusc")));
      out.write("\">\t\r\n");
      out.write("<input type=\"hidden\" name=\"descripcionBusc\" value=\"");
      out.print((request.getParameter("descripcionBusc")==null?"":request.getParameter("descripcionBusc")));
      out.write("\">\t\t\r\n");
      out.write("<input type=\"hidden\" name=\"entornoBusc\" value=\"");
      out.print((request.getParameter("entornoBusc")==null?"":request.getParameter("entornoBusc")));
      out.write("\">\t\r\n");
      out.write("<input type=\"hidden\" name=\"iconoBusc\" value=\"");
      out.print((request.getParameter("iconoBusc")==null?"":request.getParameter("iconoBusc")));
      out.write("\">\t\r\n");
      out.write("<input type=\"hidden\" name=\"autorizacionBusc\" value=\"");
      out.print((request.getParameter("autorizacionBusc")==null?"":request.getParameter("autorizacionBusc")));
      out.write("\">\t\r\n");
      out.write("<input type=\"hidden\" name=\"tipoOperacionBusc\" value=\"");
      out.print((request.getParameter("tipoOperacionBusc")==null?"":request.getParameter("tipoOperacionBusc")));
      out.write("\">\t\r\n");
      out.write("<input type=\"hidden\" name=\"parametro1Busc\" value=\"");
      out.print((request.getParameter("parametro1Busc")==null?"":request.getParameter("parametro1Busc")));
      out.write("\">\t\t\t\t\t\t\r\n");
      out.write("<input type=\"hidden\" name=\"tipoOperacionFinalBusc\" value=\"");
      out.print((request.getParameter("tipoOperacionFinalBusc")==null?"":request.getParameter("tipoOperacionFinalBusc")));
      out.write("\">\t\t\r\n");
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

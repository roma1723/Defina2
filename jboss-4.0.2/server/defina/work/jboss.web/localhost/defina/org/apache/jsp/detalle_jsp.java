package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class detalle_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");

	String menu = (String) request.getParameter("menu");
	boolean modificar = "modificar".equals(request.getParameter("accion"));
	String [][] campos = atad.defina.pres.Utils.getCampos(request);
	atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstanceVersiones(session);
	
	// En el caso de tratarse de un escenario, se localiza el código de operación "Home" asociado
	if ("ESCENARIOS".equals(menu)){
		Object[][] opHome = dao.getOpHomeEscenario(campos[0][1]);
		if (opHome != null && opHome.length > 0){
			// Solo se considera el primero de los datos, dado que un escenario sólo puede tener una operación "Home" asociada
			for(int z=0;z<campos.length;z++){
				if ("Op. Home".equals(campos[z][0]) && campos[z].length == 2){
					if (opHome[0][0] != null)
						campos[z][1] = opHome[0][0].toString();
					else
						campos[z][1] = " ";
				}
			}
		}
	}

	// Recupera el tipo de perfil de la sesión
	String tipoAcceso = (session.getValue("tipoAcceso")!=null)?session.getValue("tipoAcceso").toString():atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString();

      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\tfunction seleccionarAutorizacion() {\r\n");
      out.write("\t\tvar rc = window.showModalDialog('");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.autorizaciones.link")+"&dialog=true&accion=BUSCAR");
      out.write("','','dialogWidth:600px;dialogHeight:680px');\r\n");
      out.write("\t\tif (rc != null) document.getElementById(\"Autorización\").value = rc[0];\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction seleccionarIcono() {\r\n");
      out.write("\tvar rc = window.showModalDialog('");
      out.print((String)atad.defina.entorno.Configuracion.getCfg("cfg.menus.iconos.link")+"&dialog=true");
      out.write("','','dialogWidth:500px;dialogHeight:580px');\r\n");
      out.write("\tif (rc != null){\r\n");
      out.write("\t\tdocument.getElementById(\"Icono\").value = rc[0];\r\n");
      out.write("\t\tdocument.getElementById(\"iconoimg\").src=\"images/\" + rc[0] + \".gif\";\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\tfunction seleccionarOperacion(){\r\n");
      out.write("\t\t// Muestra el diálogo de selección de operaciones apoyándose en los módulos existentes en la herramienta.\r\n");
      out.write("\t\t// Recupera la selección efectuada, y establece el código seleccionado en el campo de texto.\r\n");
      out.write("\t\tvar rc = mostrarDialogoOperaciones();\r\n");
      out.write("\t\tif (rc != null) {\r\n");
      out.write("\t\t\t// Comprueba que se haya seleccionado una operación final. Si se trata de una operación de ese tipo, se actualiza\r\n");
      out.write("\t\t\t// el campo de texto.\r\n");
      out.write("\t\t\tif(\"F\"!=rc[4]){\r\n");
      out.write("\t\t\t\talert(\"No se puede seleccionar una operación que no sea de tipo Final.\");\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"Op. Home\").value = rc[0];\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction cancelar() {\r\n");
      out.write("\t\thistory.go(-1);\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction aceptar() {\r\n");
      out.write("\t\t");
      out.print((modificar || !"VERSIONES".equals(menu)?"":"var var2 = document.getElementById('version');"));
      out.write("\r\n");
      out.write("\t\t");
for(int i=0; i<campos.length  - ("ESCENARIOS".equals(menu)&&modificar?1:0); i++) {
			out.print("var var");
			out.print(i);
			out.print(" = document.getElementById(\"");
			out.print(campos[i][0]);
			out.println("\");");
			//
			// Evt Escenarios Publicos - Privados
			// El campo 5 == timestamp es optativo
			// El campo 2 == autorizacion es optativo para los escenarios publicos y privados. Para los autorizados es obligatorio
			if ("ESCENARIOS".equals(menu) && i==5) {
					// no se realiza ninguna validacion sobre el timestamp
			} else if ("ESCENARIOS".equals(menu) && i==2) {
					// si el escenario es de tipo autorizado --> debe haber informado un codigo de autorizacion
					
					out.println("var combo = document.getElementById(\"tipoAutorizacion\");");					
					out.println("if ((\"Autorizado\" == combo.options[combo.selectedIndex].value) && (var" + i + ".value == '')) {");
					out.println("alert('Para los escenarios de tipo Autorizado debe informarse el un código de autorización');");
					out.println("var" + i + ".focus();");
					out.println("return;");
					out.println("}");
					out.println("if ((\"Autorizado\" == combo.options[combo.selectedIndex].value) && ( (var" + i + ".value == 'PUBLICO') || (var" + i + ".value == 'publico'))) {");					
					out.println("alert('El codigo establecido solo es valido para los escenarios de tipo Publico. Introduzca otro codigo o cambie el tipo del escenario.');");					
					out.println("var" + i + ".focus();");
					out.println("return;");
					out.println("}");
					out.println("if ((\"Autorizado\" == combo.options[combo.selectedIndex].value) && (var" + i + ".value != '')) {");					
					out.println("valorAut = var2.value;");					
					out.println("}");
					out.println("if ((\"Publico\" == combo.options[combo.selectedIndex].value) && (var" + i + ".value == 'PUBLICO')) {");					
					out.println("valorAut = \"PUBLICO\";");
					out.println("}");
					out.println("if ((\"Privado\" == combo.options[combo.selectedIndex].value) && (var" + i + ".value == '')) {");					
					out.println("valorAut = \"\";");
					out.println("}");
			} else {
					out.println("if (var" + i + ".value == '') {");
					out.println("alert('Debe informar el campo \"" + campos[i][0] + "\"');");
					out.println("var" + i + ".focus();");
					out.println("return;");
					out.println("}");
					if ((i == 0) && ("ESCENARIOS".equals(menu))){
							out.println("if (var" + i + ".value.substring(0,1).toUpperCase() != 'E') {");
							out.println("alert('El código de escenario debe empezar por E');");
							out.println("var" + i + ".focus();");
							out.println("return;");
							out.println("}");
				}
			}
			//
			// Incidencia : Excepcion con ordenes no numericos
			if("Orden".equals(campos[i][0])) 		{	
				out.println("var orden = document.getElementById(\"Orden\");");
				out.println("if(isNaN(orden.value)){");
				out.println("alert('El campo orden debe ser un valor numérico');");
				out.println("return; }");
			}
		}
		int id=campos.length  - ("ESCENARIOS".equals(menu)&&modificar?1:0);
		for(int i=0;i<campos.length  - ("ESCENARIOS".equals(menu)&&modificar?1:0);i++){
			out.print("var var");
			out.print(i+id);
			out.print(" = document.getElementById(\"");
			out.print(campos[i][0]);
			out.println("Busc\");");
		}
		
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t// todos los campos estan informados\r\n");
      out.write("\t\t// se procede al envio al servidor\r\n");
      out.write("\t\t");

		int longit = campos.length+("VERSIONES".equals(menu)&&!modificar?1:0)-("ESCENARIOS".equals(menu)&&modificar?1:0);
		String [][] msg = new String[longit*2][2];
		for (int i=0; i<msg.length; i++) {
			msg[i][0] = "" + i;
			// 
			// Evt Escenarios Publicos - Privados
			// 
		  if ("ESCENARIOS".equals(menu) && (i==2)) 								
					msg[i][1] = "valorAut";								
			else  
				msg[i][1] = "var" + i + ".value";									
		}
		out.println(atad.defina.pres.ProcesadorAjax.getJavascript(menu, modificar, false, msg));
		
      out.write("\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("//\r\n");
      out.write("// Evt Escenarios Publicos - Privados\r\n");
      out.write("// Actualiza el campo de autorizacion y su icono asociado en funcion del tipo de autorizacion seleccionado \r\n");
      out.write("//\r\n");
      out.write("function actualizarTipoAutorizacion() {\r\n");
      out.write("\t\t// Para el tipo autorizado se habilita el cuadro de texto y el boton de seleccion\t\t\r\n");
      out.write("\tif (\"Autorizado\" == tipoAutorizacion.options[tipoAutorizacion.selectedIndex].value) {\t\t\t\t\r\n");
      out.write("\t\tdocument.getElementById('Autorización').value=\"\";\t\r\n");
      out.write("\t\tdocument.getElementById('Autorización').disabled=false;\t\r\n");
      out.write("\t\tdocument.getElementById('Autorización').className=\"CampoObligatorio\";\t\r\n");
      out.write("\t\tdocument.getElementById('iconoSeleccionAutorizacion').disabled=false;\r\n");
      out.write("\t\tdocument.getElementById('iconoSeleccionAutorizacion').src=\"images/BotonPrismatico.gif\";\t\t\r\n");
      out.write("\t}\t\r\n");
      out.write("\telse if (\"Publico\" == tipoAutorizacion.options[tipoAutorizacion.selectedIndex].value) {\t\t\t\r\n");
      out.write("\t\tdocument.getElementById('Autorización').value=\"PUBLICO\";\t\r\n");
      out.write("\t\tdocument.getElementById('Autorización').disabled=true;\t\r\n");
      out.write("\t\tdocument.getElementById('Autorización').className='CampoSalida';\t\r\n");
      out.write("\t\tdocument.getElementById('iconoSeleccionAutorizacion').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('iconoSeleccionAutorizacion').src=\"images/BotonPrismaticoD.gif\";\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\t// En caso contrario se deshabilita\r\n");
      out.write("\telse  {\t\t\r\n");
      out.write("\t\tdocument.getElementById('Autorización').value=\"\";\t\r\n");
      out.write("\t\tdocument.getElementById('Autorización').disabled=true;\t\r\n");
      out.write("\t\tdocument.getElementById('Autorización').className=\"CampoSalida\";\t\r\n");
      out.write("\t\tdocument.getElementById('iconoSeleccionAutorizacion').disabled=true;\r\n");
      out.write("\t\tdocument.getElementById('iconoSeleccionAutorizacion').src=\"images/BotonPrismaticoD.gif\";\t\t\r\n");
      out.write("\t}\t\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<script> window.setTimeout(\"alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';\", ");
      out.print(""+request.getSession().getMaxInactiveInterval()*1000);
      out.write(");</script>\r\n");
      out.write("<table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td height=\"22\" class=\"CabeceraRelacionada2\">&nbsp;&nbsp;");
      out.print(menu);
      out.write(" :: ");
      out.print((modificar?"Modificar":"A&ntilde;adir"));
      out.write(" </td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("    <td><fieldset>\r\n");
      out.write("    <legend>Detalle</legend>\r\n");
      out.write("        <table width=\"100%\"  border=\"0\" cellspacing=\"8\" cellpadding=\"0\">\r\n");
      out.write("          <tr>\r\n");
      out.write("\t    ");
	    
	    String longitud=null;
	    String requerido=null;
	    for(int i=0; i<campos.length - ("ESCENARIOS".equals(menu)&&modificar?1:0); i++) {
	    	
	    if ("ESCENARIOS".equals(menu)){
				if ("Código".equals(campos[i][0])){
					longitud="7";
					requerido="class='CampoObligatorio'";
				} else if ("Nombre".equals(campos[i][0])){
					longitud="40";
					requerido="class='CampoObligatorio'";
				} else if ("Autorización".equals(campos[i][0])){
					longitud="8";
					requerido="class='CampoSalida'";
				} else if ("Icono".equals(campos[i][0])){
					longitud="30";
					requerido="class='CampoSalida'";
				} else if ("Op. Home".equals(campos[i][0])){
					longitud="7";
					requerido="class='CampoSalida'";
				} else {
					longitud="8";
					requerido="class='CampoEntrada'";
				}	
			} else if ("AUTORIZACIONES".equals(menu) || "PERFILES".equals(menu)){
				if ("Código".equals(campos[i][0])){
					longitud="8";
					requerido="class='CampoObligatorio'";
				} else if ("Descripción".equals(campos[i][0])){
					longitud="50";
					requerido="class='CampoObligatorio'";
				} else {
					longitud="8";
					requerido="class='CampoObligatorio'";
				}
			} else if ("VERSIONES".equals(menu)){
				if("Código".equals(campos[i][0])){
					longitud="10";
					requerido="class='CampoObligatorio'";
				} else if ("Descripción".equals(campos[i][0])){
					longitud="50";
					requerido="class='CampoObligatorio'";
				}
			} else if ("JERARQUIA".equals(menu)){
				longitud="8";
				requerido="class='CampoObligatorio'";
			} else if ("ICONOS".equals(menu)){
				if ("Nombre".equals(campos[i][0])){
					longitud="30";
				} else {
					longitud="30";
				}
			} else {
				longitud="8";
				requerido="class='CampoEntrada'";
			}		    
			
		if ("ESCENARIOS".equals(menu)){
			if("Autorización".equals(campos[i][0])) {		
			// Crea la etiqueta
			out.println("<td width=\"1%\"><label>");
			out.print(campos[i][0]);
			out.print("</label><br>");
			
			// Crea la combo de tipos
			out.print("<select name=\"tipoAutorizacion\" class=\"campoEntrada\" onchange=\"actualizarTipoAutorizacion();\">");
			// se establece como elemento seleccionado la opcion que diga el campo autorizacion
			out.print("<option value=\"Privado\" " + ((campos[i][1]==null) ||("".equals(campos[i][1]))?"selected":"") + ">Privado</option>");
			out.print("<option value=\"Publico\" " + (("PUBLICO".equals(campos[i][1]))?"selected":"") + ">Público</option>");
			out.print("<option value=\"Autorizado\" " + (    ( (campos[i][1]!=null) && (campos[i][1].toString().length()>0) && (!"PUBLICO".equals(campos[i][1])) ) ?"selected":""    ) + ">Autorizado</option>");
			out.print("</select>");
					
			out.print("&nbsp;&nbsp;&nbsp;");
					
			// Crea el cuadro de texto
			out.print("<input name=\"");
			out.print(campos[i][0]);
			out.print("\" type=\"text\" ");
			// Cuando se va a añadir un escenario el campo viene a null --> es optativo
			requerido =  ( (campos[i][1]!=null) && (!"".equals(campos[i][1])) && (!"PUBLICO".equals(campos[i][1])))? "class='CampoObligatorio'" : "class='CampoSalida'";
			out.print(requerido);
			out.print(" size=\"10\"");
			out.print((modificar&&i==0||modificar&&"JERARQUIA".equals(menu)&&i==1?"disabled":""));
			// Incluido para controlar el tamaño máximo
			out.print(" maxlength=\"" + longitud + "\"");
			// Cuando se va a añadir un escenario el campo viene a null --> tiene q estar inhabilitado
			String deshabilitado =  ((campos[i][1]!=null) && (!"".equals(campos[i][1])) && (!"PUBLICO".equals(campos[i][1])))? "" : "disabled";
			out.print(" " + deshabilitado + " ");
			out.print(" ></td>");					

			// Crea el icono de seleccion de autorizacion
			// Cuando se va a añadir un escenario el campo viene a null --> tiene q mostrar el icono de deshabilitado
			String iconoMostrar = ( (campos[i][1]!=null) && (!"".equals(campos[i][1])) && (!"PUBLICO".equals(campos[i][1])))? "images/BotonPrismatico.gif" : "images/BotonPrismaticoD.gif";
			out.print("<td><br><img name=\"iconoSeleccionAutorizacion\" src=\"" + iconoMostrar + "\"" + deshabilitado + " width=\"19\" height=\"18\" onClick=\"seleccionarAutorizacion();\" >");
			} else if ("Op. Home".equals(campos[i][0])){
				// Crea la etiqueta
				out.println("<td width=\"1%\"><label>");
				out.print(campos[i][0]);
				out.print("</label><br>");
				
				// Crea el cuadro de texto
				out.print("<input name=\"");
				out.print(campos[i][0]);
				out.print("\" id=\"");
				out.print(campos[i][0]);
				// El campo no es requerido
				out.print("\" type=\"text\" class='CampoSalida'");
				out.print(" size=\"8\"");
				// Siempre estará deshabilitado
				out.print("disabled");
				// Incluido para controlar el tamaño máximo
				out.print(" maxlength=\"8\"");
				out.print(" value=\"");
				out.print(campos[i][1]==null?"":campos[i][1]);
				out.print("\" ></td>");					
				
				// Incluye el botón de búsqueda de operaciones
				out.print("<td><br><img name=\"iconoSeleccionAutorizacion\" src=\"images/BotonPrismatico.gif\" width=\"19\" height=\"18\" onClick=\"seleccionarOperacion();\" >");
			} else {
				out.println("<td width=\"1%\"><label>");
				out.print(campos[i][0]);
				out.print("</label><br>");
				out.print("<input name=\"");
				out.print(campos[i][0]);
				out.print("\" type=\"text\" ");
				out.print(requerido);
				// Evt Escenarios Publicos - Privados
				// Para el campo Codigo de Escenarios se reduce el tamaño al tener q añadir la combo de tipos de autorizacion
				// Para el resto de campos de Escenarios se deja el valor que ya tenia 
				String tamanio = "";
				if ("ESCENARIOS".equals(menu)) {
					tamanio=("Código".equals(campos[i][0]))?"10":"18";							
					if("Orden".equals(campos[i][0])){
						tamanio="8";
					}
				} else {  
					tamanio = "35";			
				}
				
				out.print(" size=\"" + tamanio + "\"");			
				out.print((modificar&&i==0||modificar&&"JERARQUIA".equals(menu)&&i==1?"disabled":""));
				// Incluido para controlar el tamaño máximo
				out.print(" maxlength=\"" + longitud + "\"");
				out.print("></td>");
			}
		} else {
				out.println("<td width=\"1%\"><label>");
				out.print(campos[i][0]);
				out.print("</label><br>");
				out.print("<input name=\"");
				out.print(campos[i][0]);
				out.print("\" type=\"text\" ");
				out.print(requerido);
				
				// Evt Escenarios Publicos - Privados
				// Para el campo Codigo de Escenarios se reduce el tamaño al tener q añadir la combo de tipos de autorizacion
				// Para el resto de campos de Escenarios se deja el valor que ya tenia 
				String tamanio = "";
				if ("ESCENARIOS".equals(menu)) 
					tamanio=("Código".equals(campos[i][0]))?"10":"18";							
				else  
					tamanio = "35";			
				out.print(" size=\"" + tamanio + "\"");			
				out.print((modificar&&i==0||modificar&&"JERARQUIA".equals(menu)&&i==1?"disabled":""));
				// Incluido para controlar el tamaño máximo
				out.print(" maxlength=\"" + longitud + "\"");
				out.print("></td>");
			}	
				
			if ("Icono".equals(campos[i][0])){
					out.print("<td><br><img name=\"iconoimg\" width=\"17\" height=\"17\" src=\"images/" + (campos[i][1]==null?"defecto":campos[i][1]) + ".gif\"><img src=\"images/BotonPrismatico.gif\" width=\"19\" height=\"18\" onClick=\"seleccionarIcono();\">");
			}
				
	    }
      out.write("\r\n");
      out.write("\t    ");

		out.print("<td>");
		for (int i=0;i<campos.length - ("ESCENARIOS".equals(menu)&&modificar?1:0);i++){
			// EScribe cada uno de los campos hidden
			out.print("<input type=\"hidden\" name=\"" + campos[i][0] + "Busc\" value=\"" + request.getParameter(campos[i][0] + "Busc") + "\">");
		}
		out.print("</td>");
	    
      out.write("\r\n");
      out.write("\t    ");
      out.print((modificar || !"VERSIONES".equals(menu)?"<!-- ":""));
      out.write("\t\r\n");
      out.write("            <td width=\"1%\" nowrap>Versi&oacute;n origen<br>\r\n");
      out.write("              <select name=\"version\" class=\"CampoEntrada\">\r\n");
      out.write("\t\t");

		//atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstanceVersiones(session);
		if (dao == null){
			dao = atad.defina.datos.AccesoDatosBase.getInstanceVersiones(session);
		}
		Object[][] versiones = dao.getTablaVersiones();
		if (versiones != null) {
			for (int i=0; i<versiones.length; i++) {
				out.println("<option " + (i==0?"selected":"") + " value=\"" + versiones[i][0] + "\">" + versiones[i][1] + "</option>");
			}
		}
		
      out.write("\r\n");
      out.write("            </select> </td>\r\n");
      out.write("\t    ");
      out.print((modificar || !"VERSIONES".equals(menu)?" -->":""));
      out.write("\r\n");
      out.write("            <td width=\"97%\" align=\"right\" valign=\"bottom\">&nbsp;</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("    </fieldset></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr>\r\n");
      out.write("  \t");

  		String disabled = null;
  		if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
  			disabled = "disabled=\"true\"";
  		} else {
  			disabled ="";
  		}
  	
      out.write("\r\n");
      out.write("    <td align=\"right\"><input type=\"button\" name=\"Button\" value=\"Aceptar\" class=\"Boton\" onclick=\"aceptar();\" ");
      out.print(disabled);
      out.write(">\r\n");
      out.write("    <input type=\"button\" name=\"Button2\" value=\"Cancelar\" class=\"Boton\" onclick=\"cancelar();\"></td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  ");
      out.print((modificar?atad.defina.pres.Utils.rellenarCampos(campos):""));
      out.write("\r\n");
      out.write("  ");

	if (modificar) {
		out.println("<script>");
		out.println("do {");
		for (int i=0; i<campos.length; i++) {
			out.println("var tmp=document.getElementById('" + campos[i][0] + "');");
			out.println("if(!tmp.disabled) {");
			out.println("tmp.focus(); break;");
			out.println("}");
		}
		out.println("} while(false);");
		out.println("</script>");
	} else {
		// es nuevo
		out.println("<script>document.getElementById('" + campos[0][0] + "').focus();</script>");
	}
  
      out.write("\r\n");
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

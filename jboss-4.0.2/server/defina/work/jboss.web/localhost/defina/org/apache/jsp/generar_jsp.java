package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class generar_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<title>Untitled Document</title>\r\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n");
      out.write("\t\t<LINK href=\"css/NacarIE1024v01.css\" type=text/css rel=stylesheet>\r\n");
      out.write("\t\t<script src=\"js/defina.js\"></script>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<script language=\"JavaScript\">\r\n");
      out.write("\t\tfunction seleccionTodo(value) {\r\n");
      out.write("\t\t\tfor (i=1; i<6; i++) {\r\n");
      out.write("\t\t\t\tdocument.getElementById('cb' + i).checked=value;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t// Si se trata de canal pesado, se deselecciona la checkbox del fichero entorno aplicacioninstancia.cfg\r\n");
      out.write("\t\t\t");

				if ("Pesados".equals(session.getValue("canal"))){
			
      out.write("\r\n");
      out.write("\t\t\t\tdocument.getElementById('cb5').checked=false;\r\n");
      out.write("\t\t\t");

				}
			
      out.write("\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tfunction generaFicheros(){\r\n");
      out.write("\t\t\tvar numFicherosGenerar = 0;\r\n");
      out.write("\t\t\tvar cadena = '';\r\n");
      out.write("\t\t\tvar tmp;\r\n");
      out.write("\t\t\tfor (i=1; i<6; i++) {\r\n");
      out.write("\t\t\t\ttmp = document.getElementById('cb' + i);\r\n");
      out.write("\t\t\t\tif (tmp.checked) {\r\n");
      out.write("\t\t\t\t\tvar valor = tmp.value\r\n");
      out.write("\t\t\t\t\tif(i==3){\r\n");
      out.write("\t\t\t\t\t\tif (document.getElementById('cbNPG').checked){\r\n");
      out.write("\t\t\t\t\t\t\tvalor = valor+\"NPG\";\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tnumFicherosGenerar++;\r\n");
      out.write("\t\t\t\t\tcadena += valor + \"*\";\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t// Añade el entorno a la cadena\r\n");
      out.write("\t\t\tcadena = cadena + \"#\" + document.getElementById(\"entornoGeneracion\").value;\r\n");
      out.write("\t\t\tif (numFicherosGenerar == 0){\r\n");
      out.write("\t\t\t\talert('Debe seleccionar ficheros a generar');\r\n");
      out.write("\t\t\t}else if (document.getElementById(\"entornoGeneracion\").value == 0){\r\n");
      out.write("\t\t\t\talert('Debe seleccionar un entorno para generar los ficheros');\r\n");
      out.write("\t\t\t} else {\r\n");
      out.write("\t\t\t\tvar usr = document.getElementById('usuario').value;\r\n");
      out.write("\t\t\t\tvar generarAplicas = document.getElementById('cb1').checked;\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tif (generarAplicas && usr == 0) {\r\n");
      out.write("\t\t\t\t\talert('Debe seleccionar un usuario');\r\n");
      out.write("\t\t\t\t} else {\r\n");
      out.write("\t\t\t\t\t");

						String[][] msg=null;
						// Obtiene la ruta de configuración
						String ruta = atad.defina.entorno.Configuracion.getCfg("cfg.ficheros.directorio").toString();
						
      out.write("\r\n");
      out.write("\t\t\t\t\t\tvar ruta ='");
      out.print(ruta);
      out.write("';\r\n");
      out.write("\t\t\t\t\t\t");

						msg = new String[][]{{"0","cadena"},{"1","usr"},{"2","ruta"}};
						out.println(atad.defina.pres.ProcesadorAjax.getJavascript("GENERAR",true,false,msg));
					
      out.write("\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\r\n");
      out.write("\t<body>\r\n");
      out.write("<script> window.setTimeout(\"alert('Se ha alcanzado el máximo tiempo de inactividad'); top.location = 'logoff.jsp';\", ");
      out.print(""+request.getSession().getMaxInactiveInterval()*1000);
      out.write(");</script>\r\n");
      out.write("\t\t<table width=\"100%\"  border=\"0\" cellspacing=\"5\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td height=\"22\" class=\"CabeceraRelacionada2\">&nbsp;&nbsp;GENERAR</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t  <td class=\"TituloTabla\">Ficheros</td>\r\n");
      out.write("\t\t  </tr>\r\n");
      out.write("\t\t  \t\t\t\t\t<tr>\r\n");
      out.write("\t\t  \t\t\t\t\t\t<td class=\"TextoTablaN\">Entorno de generación&nbsp;\r\n");
      out.write("\t\t  \t\t\t\t\t\t\t<select name=\"entornoGeneracion\" class=\"CampoEntrada\" style=\"width:180px\">\r\n");
      out.write("\t\t  \t\t\t\t\t\t\t\t<option value=0 selected>Seleccione entorno</option>\r\n");
      out.write("\t\t  \t\t\t\t\t\t\t\t");

		  									Object[] entornos = (Object[])session.getValue("entornos");
								      	if (entornos != null && entornos.length > 0){
								      		for (int i=0;i<entornos.length;i++){
								      			out.print("<option value=\"" + entornos[i].toString().substring(0,1) + "\"" + (entornos[i].toString().substring(0,1).equals(request.getParameter("entorno"))?"selected":"") + ">" + entornos[i] + "</option>");
								      		}
								      	}
								      
      out.write("\r\n");
      out.write("\t\t  \t\t\t\t\t  </td>\r\n");
      out.write("\t\t  </tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t  <td class=\"TextoTablaN\"><table width=\"100%\"  border=\"0\" cellspacing=\"2\" cellpadding=\"0\" class=\"TablaDatos\">\r\n");
      out.write("\t\t\t    <tr class=\"CabeceraTabla\">\r\n");
      out.write("\t\t\t\t<td valign=\"top\" class=\"CabeceraTabla\">\r\n");
      out.write("\t\t\t\t\t<input type=checkbox  name=\"ac\" onclick=\"seleccionTodo(this.checked)\">\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t      <td  class=\"CabeceraTabla\">Descripci&oacute;n del fichero</td>\r\n");
      out.write("\t\t\t      <td  class=\"CabeceraTabla\">Enlace para descarga del fichero</td>\r\n");
      out.write("\t\t\t      <td nowrap  class=\"CabeceraTabla\">&Uacuteltima generaci&oacute;n </td>\r\n");
      out.write("\t\t\t    </tr>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<tr class=\"Pijama1\">\r\n");
      out.write("\t\t\t\t\t<td valign=\"top\">\r\n");
      out.write("\t\t\t\t\t\t<input type=checkbox class=\"TextoTabla\" name=\"cb1\" value='aplicas.dat*aplicas.asc'\" >\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td class=\"TextoTablaN\">\r\n");
      out.write("\t\t\t\t\t\t<b>APLICAS.ASC (ASCII) y APLICAS.DAT (EBCDIC)</b><br>(se guarda en <b>UCM</b> HOST para ser procesado por el activador)\r\n");
      out.write("\t\t\t\t\t\t<select name=\"usuario\" class=\"CampoEntrada\">\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"0\">--- Seleccione un usuario ---</option>\r\n");
      out.write("\t\t\t\t\t\t\t");
// Recupera los usuarios de la base de datos
							Object[][] data = null;
							atad.defina.datos.AccesoDatosBase dao = atad.defina.datos.AccesoDatosBase.getInstanceVersiones(session);
							data = dao.getUsuarios();
							String ultimoUsuario = (String) session.getAttribute("ficheros.usuario");
							if (data != null && data.length > 0){
								for (int i=0;i<data.length;i++){
									out.print("<option value=\"" + data[i][0] + "\"" + (data[i][0].toString().equals(ultimoUsuario)?" selected":"") + ">[" + data[i][0] + "] " + data[i][2] + " " + data[i][3] + ", " + data[i][1] + "</option>");
								}
							}
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t");
Object ts = session.getAttribute("ficheros.aplicas.dat.timestamp");
      out.write("\r\n");
      out.write("                                        <td class=\"TextoTablaN\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"TextoTablaN\">aplicas.dat</span>\r\n");
      out.write("\t\t\t\t\t\t<br><span class=\"TextoTablaN\">aplicas.asc</span>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td nowrap class=\"TextoTablaN\">");
      out.print((ts==null?"no generado":ts));
      out.write("</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<tr class=\"Pijama2\">\r\n");
      out.write("\t\t\t\t\t<td valign=\"top\">\r\n");
      out.write("\t\t\t\t\t\t<input type=checkbox class=\"TextoTabla\" name=\"cb2\" value='pseudocodigos.properties'\" >\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td class=\"TextoTablaN\">\r\n");
      out.write("\t\t\t\t\t\t<b>PSEUDOCODIGOS.properties</b><br>que contiene los c&oacute;digos para arrancar las operaciones desde el campo operaci&oacute;n\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t");
ts = session.getAttribute("ficheros.pseudocodigos.properties.timestamp");
      out.write("\r\n");
      out.write("                                        <td class=\"TextoTablaN\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"TextoTablaN\">pseudocodigos.properties</span>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td nowrap class=\"TextoTablaN\">");
      out.print((ts==null?"no generado":ts));
      out.write("</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<tr class=\"Pijama1\">\r\n");
      out.write("\t\t\t\t\t<td valign=\"top\">\r\n");
      out.write("\t\t\t\t\t\t<input type=checkbox class=\"TextoTabla\" name=\"cb3\" value='escritorio.xml'\" >\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td class=\"TextoTablaN\">\r\n");
      out.write("\t\t\t\t\t\t<b>ESCRITORIO.XML</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=checkbox class=\"TextoTabla\" name=\"cbNPG\">Entorno NPG<br>que utiliza el escritorio NACAR para su configuraci&oacute;n\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t");
ts = session.getAttribute("ficheros.escritorio.xml.timestamp");
      out.write("\r\n");
      out.write("                                        <td class=\"TextoTablaN\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"TextoTablaN\">escritorio.xml</span>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td nowrap class=\"TextoTablaN\">");
      out.print((ts==null?"no generado":ts));
      out.write("</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<tr class=\"Pijama2\">\r\n");
      out.write("\t\t\t\t\t<td valign=\"top\">\r\n");
      out.write("\t\t\t\t\t\t<input type=checkbox class=\"TextoTabla\" name=\"cb4\" value='AtaeOperacionesEscritorio.properties'\" >\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td class=\"TextoTablaN\">\r\n");
      out.write("\t\t\t\t\t\t<b>AtaeOperacionesEscritorio.properties</b><br>que contiene los flujos o programas que arranca cada c&oacute;digo de operaci&oacute;n o cada objeto de negocio\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t");
ts = session.getAttribute("ficheros.AtaeOperacionesEscritorio.properties.timestamp");
      out.write("\r\n");
      out.write("                                        <td class=\"TextoTablaN\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"TextoTablaN\">ataeoperacionesescritorio.properties</span>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td nowrap class=\"TextoTablaN\">");
      out.print((ts==null?"no generado":ts));
      out.write("</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<tr class=\"Pijama1\">\r\n");
      out.write("\t\t\t\t\t");
String ent = atad.defina.pres.Utils.getEntornoGenerar(session);
      out.write("\r\n");
      out.write("\t\t\t\t\t<td valign=\"top\">\r\n");
      out.write("\t\t\t\t\t\t<input type=checkbox class=\"TextoTabla\" name=\"cb5\" value='");
      out.print(ent);
      out.write("aplicacioninstancia.cfg'\" ");
      out.print(("Pesados".equals(session.getValue("canal")))?"disabled":"enabled");
      out.write(">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<td class=\"TextoTablaN\">\r\n");
      out.write("\t\t\t\t\t\t<b>");
      out.print(ent);
      out.write("aplicacioninstancia.cfg</b><br>para los distintos entornos que contienen para cada operaci&oacute;n la instancia WAS en la que se ejecuta\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t");
ts = session.getAttribute("ficheros." + ent + "aplicacioninstancia.cfg.timestamp");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                                        <td class=\"TextoTablaN\">\r\n");
      out.write("\t\t\t\t\t\t<span class=\"TextoTablaN\">");
      out.print(ent);
      out.write("aplicacioninstancia.cfg</span>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td nowrap valign=center class=\"TextoTablaN\">");
      out.print((ts==null?"no generado":ts));
      out.write("</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t\t  </tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t  <td class=\"TextoTablaN\"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("                                            <tr>\r\n");
      out.write("                                              <td width=\"100%\"></td>\r\n");
      out.write("                                              ");

                                              	String disabled = null;
																					  		if(atad.defina.entorno.Configuracion.getCfg("cfg.acceso.usuario").toString().equals(tipoAcceso)){
																					  			disabled = "disabled=\"true\"";
																					  		} else {
																					  			disabled ="";
																					  		}
                                              
      out.write("\r\n");
      out.write("                                              <td width=\"1%\" nowrap><input type=\"button\" class=\"Boton\" value=\"Generar ficheros\" onClick=\"generaFicheros()\" name=\"submit2\" ");
      out.print(disabled);
      out.write("></td>\r\n");
      out.write("\t\t\t\t\t      <td>&nbsp;</td>\r\n");
      out.write("                                              <td width=\"1%\" nowrap><input type=\"button\" class=\"Boton\" value=\"Ayuda\" onClick=\"mostrarAyuda('generar');\" name=\"bt1\"></td>\r\n");
      out.write("                                            </tr>\r\n");
      out.write("                                          </table></td>\r\n");
      out.write("\t\t\t\t  </tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td class=\"TextoTablaN\">&nbsp;\t\t\t\t\t\t\t\t\t\t  </td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("</body>\r\n");
      out.write("\t\t\t\t\t\t\t</html>\r\n");
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
